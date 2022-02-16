# 영속성 전이 

### 예시

```java
@Getter
@Setter
@Entity
public class Parent {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  // 양방향으로 설정
  @OneToMany (mappedBy = "parent", cascade = CascadeType.ALL)  
  private List<Child> childList = new ArrayList<>();

  // 연관관계 편의 메소드
  public void  addChild(Child child){
    childList.add(child);
    child.setParent(this);
  }

}

```

```java
@Getter
@Setter
@Entity
public class Child {
  @Id
  @GeneratedValue
  private Long id;

  private String name;

  // child가 연관관계의 주인 (외래키가 있는)
  @ManyToOne
  @JoinColumn(name = "parent_id")
  Parent parent;
}
```

```java
Child child1 = new Child();
Child child2 = new Child();

Parent parent = new Parent();
parent.addChild(child1);
parent.addChild(child2);

em.persist(parent);

tx.commit();
```

- cascade 있으면 parent만 persist 해 줘도 insert가 된다 
  - 연관된 애도 저장된다 

#### 옵션

- **ALL : 모두 적용**

  - 라이프 사이클 동일하게 하려면 

- **PERSIST : 저장할 때만** - 삭제는 안 되게

- REMOVE

- MERGE

- REFRESH

- DETACH

  

### 주의사항

- 영속성 전이는 연관관계를 매핑하는 것과 아무 관련이 없음
- 영속화 할 때 같이 영속화 하는 편리함을 제공



### 언제쓰느냐

- 단일 소유자일 때 사용 가능 
  - 하나의 부모가 여러 자식을 관리할 때 의미가 있다 
  - 즉, 소유자가 하나 일 때 자식의 소유자가 하나 (연관관계가 하나) 일 때만 써야 한다
    - 이유 : 운영이 어려워 짐 
- 라이프사이클이 동일할 때 사용 가능



# 고아 객체

`orphanRemoval = true`

- 고아 객체 제거 
  - 부모 엔터티와 연관관계가 끊어진 자식 엔터티 자동으로 삭제

```java
em.flush();
em.clear();

Parent findParent = em.find(Parent.class, parent.getId()); 
findParent.getChildList().remove(0);

tx.commit();
```

- `delete from Child where id=?` 쿼리가 실행 됨



### 주의사항

- 참조하는 곳이 하나일 때 사용해야 함
- **특정 엔티티가 개인 소유할 때 사용**
- 참고: 개념적으로 부모를 제거하면 자식은 고아가 된다. 따라서 고
  아 객체 제거 기능을 활성화 하면, **부모를 제거할 때 자식도 함께**
  **제거**된다. 이것은 CascadeType.REMOVE처럼 동작한다.



### 결론

- `CascadeType.ALL + orphanRemovel=true ` 
  - 스스로 생명주기를 관리하는 엔티티는 em.persist()로 영속화,  em.remove()로 제거 
  - 두 옵션을 모두 활성화 하면 부모 엔티티를 통해서 자식의 생명 주기를 관리할 수 있음
  - 도메인 주도 설계(DDD)의 Aggregate Root개념을 구현할 때 유용
    - **Aggregate Root** 는 자식 repo를 만들지 않는게 낫다 이런거임 