#### 도입

- **임베디드 타입과 값 타입 컬렉션이 중요하다**



## JPA 의 데이터 타입 분류

1. 엔티티 타입

   - `@Entity`로 정의하는 객체 
   - 데이터가 변해도 식별자로 지속해서 **추적 가능**
     - 회원 엔티티의 키나 나이 값을 변경해도 식별자로 인식 가능

2. 값 타입

   - `int`, `Integer`, `String` 처럼 단순히 값으로 사용하는 자바 기본 타입이나 객체
   - 식별자가 없고 값만 있으므로 변경시 추적 불가 
     - 숫자 100을 200으로 변경하면 완전히 다른 값으로 대체

   값 타입에는 

   1. **기본값 타입** 
      - 자바 기본 타입 (int, double - primitive type)
      - 래퍼 클래스 (Integer, Long - reference type)
      - String
   2. **임베디드 타입**(embedded type, 복합 값 타입)
   3. **컬렌션 값 타입** (collection value type)





### 기본값 타입

- `String name;`
  `int age;`

- 생명주기를 엔티티에 의존한다

  - 회원을 삭제하면 이름, 나이 필드도 함께 삭제

- 값 타입은 공유하면 X

  - 회원 이름 변경 시 다른 회원의 이름도 함께 변경되면 안 됨

  

> 참고 : 자바의 기본 타입(primitive type)은 절대 공유 X 
> primitive type은 항상 값을 복사함 -> side effect가 있다
> Integer같은 래퍼 클래스나 String 같은 특수한 클래스는 공유 가능한 객체 이지만 변경 X 
>
> Integer a = new Integer(10);
> Integer b = a ; // 공유 가능하다
> b.setValue // 변경이 안 된다 이런 함수 없음 -> side effect 가 없다



### 임베디드 타입(복합 값 타입)

- 새로운 값 타입을 직접 정의할 수 있음
  - startDate, endDate -> Peroid 값 으로 
- 주로 기본 값 타입을 모아서 만들어서 복합 값 타입이라고도 함
- int, String 과 같은 값 타입
- 예시
  - 회원 엔티티는 이름, 근무시작일, 근무 종료일, 주소 도시, 주소 번지, 주소 우편번호를 가진다 -> 회원 엔티티는 이름, 근무 기간, 집 주소를 가진다. 
  - 이렇게 묶어낼 수 있는게 임베디드 타입이다
    - 모델링이 깔끔해짐
  - ![image-20220221113020695](..\image\image-20220221113020695.png)

#### 임베디드 타입 장점

- 재사용이 가능하다 
- 클래스 내에서 응집도가 높다 
- `Period.isWork()` 처럼 해당 값 타입만 사용한는 의미있는 메소드를 만들 수 있음
- 임베디드 타입을 포함한 모든 값 타입은, 값 타입을 소유한  엔티티에 생명주기 의존함



#### 임베디드 타입 테이블 매핑

![image-20220221113216543](..\image\image-20220221113216543.png)



#### 예제 코드

```java
@Getter
@Setter
@Entity
public class Member extends BaseEntity{
  @Id @GeneratedValue
  @Column(name="MEMBER_ID") 
  private Long id;
  @Column(length = 10) 
  private String name;
    
  @Embedded // 양쪽에 다 Embedded 표기를 추천 함
  private Peroid workPeroid;
    
  @Embedded
  private Address homeAddress;
}
```

```java

@Setter
@Getter
@Embeddable
public class Address {
  private String city;
  private String street;
  private String zipcode;

  // 기본생성자를 만들어 줘야 한다. 왜?
  public Address() {
  }

  public Address(String city, String street, String zipcode) {
    this.city = city;
    this.street = street;
    this.zipcode = zipcode;
  }
}

```

```java

@Setter
@Getter
@Embeddable
public class Peroid {

  private LocalDateTime startDate;
  private LocalDateTime endDate;

  public boolean isWork(){
    return true;
  }
}

```



```java
Member member = new Member();
member.setName("hello");
member.setHomeAddress(new Address("city","street","zipcode"));
member.setWorkPeroid(new Peroid());

em.persist(member);
tx.commit();
```



```sql
create table Member (
       MEMBER_ID bigint not null,
        createdBy varchar(255),
        createdDate timestamp,
        lastModifiedBy varchar(255),
        lastModifiedDate timestamp,
        city varchar(255),
        street varchar(255),
        zipcode varchar(255),
        name varchar(10),
        endDate timestamp,
        startDate timestamp,
        primary key (MEMBER_ID)
    )
    
insert  into
            Member
            (createdBy, createdDate, lastModifiedBy, lastModifiedDate, city, street, zipcode, name, endDate, startDate, MEMBER_ID) 
        values
            (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
```



- 임베디드 타입은 엔티티의 값일 뿐이다.
- **임베디드 타입을 사용하기 전과 후의 매핑하는 테이블은 같다**
- 객체와 테이블을 아주 세밀하게(fine-grained) 매핑하는 것이 가능
  - fine-grained
    - 잘개 쪼개는 것 
    - 타행 이체의 경우 [당행 잔액 조회]  => [타행입금계좌 확인] => [타행으로 송금] 으로 나눌 수 있고 각각 메소드로 구성할 수 있다. 이 기능들이 다양하게 활용되면 메소드를 모아 하나의 모듈로 만들 수도 있다. 변경이 발생했을 때 보다 유연하게 개발할 수 있다는 장점이 있다. 재사용이 가능하다 
  - Coarse-Grained
    - 덩어리로 작업한다
    - 타행이체의 경우 그냥 [타행이체] 하나를 만들어서 사용한다. 일반적으로 EnterpriseApplication Design 에서는 선호하지 않는 방식. Distributed System 상에서 유용하다
- 잘 설계한 ORM Application은 매핑한 테이블의 수 보다 클래스의 수가 더 많다



#### 임베디드 타입과 연관관계

![image-20220221115615940](..\image\image-20220221115615940.png)

- PhoneNumber에 PhoneEntity의 FK를 가지고 있는구조 ??? 

```java
@Setter
@Getter
@Embeddable
public class Address {
  private String city;
  private String street;
  @Column(name="ZIPCODE") // 이것도 가능하다
  private String zipcode;
    
  private Member member; // 가능하다 (Member에 대한 FK값 만 가지고 있으면 되기 때문에)

 ...생성자 생략
}
```



#### `@AttributeOverride` 속성 재정의

- 한 엔티티에서 같은 값 타입을 사용하면 에러남 
- `MappingException: Repeated column in mapping for entity` Exception 이 남

```java
@Getter
@Setter
@Entity
public class Member extends BaseEntity{
  @Id @GeneratedValue
  @Column(name="MEMBER_ID") 
  private Long id;
  @Column(length = 10) 
  private String name;
    
  @Embedded // 양쪽에 다 Embedded 표기를 추천 함
  private Peroid workPeroid;
    
  @Embedded
  private Address homeAddress;
  
  @Embedded
  private Address workAddress;
}
```

- 이 때 `@AttributeOverride` 를 쓰면 된다 
  - 주석 보면 사용법 나와있으니 참고하면 됨

```java
  @Embedded
  private Address homeAddress;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name="city",
      column = @Column(name="WORK_CITY")),
      @AttributeOverride(name="street",
          column = @Column(name="WORK_STREET")),
      @AttributeOverride(name="zipcode",
          column = @Column(name="WORK_ZIPCODE"))
  })
  private Address workAddress;

```

```sql
    create table Member (
       MEMBER_ID bigint not null,
        createdBy varchar(255),
        createdDate timestamp,
        lastModifiedBy varchar(255),
        lastModifiedDate timestamp,
        city varchar(255),
        street varchar(255),
        zipcode varchar(255),
        name varchar(10),
        WORK_CITY varchar(255),
        WORK_STREET varchar(255),
        WORK_ZIPCODE varchar(255),
        endDate timestamp,
        startDate timestamp,
        primary key (MEMBER_ID)
    )
```

