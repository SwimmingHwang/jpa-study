package jpabook.jpashop.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member {

  @Id
  @GeneratedValue
  @Column(name = "MEMBER_ID") // 요즘 소문자에 언더스코어 방식을 많이 씀
  private Long id;
  @Column(length = 10) // 그냥 추가하면 제약을 DB 확인하지 않아도 파악할 수 있는 장점이 있음
  private String name;


  @Embedded // 양쪽에 다 Embedded 표기를 추천 함
  private Peroid workPeroid;


  @Embedded
  private Address homeAddress;

  @ElementCollection
  @CollectionTable(name = "FAVORITE_FOOD", joinColumns = {
      @JoinColumn(name = "MEMBER_ID") // 외래키로 member_id 를 설정한다
  })
  @Column(name = "FOOD_NAME") // favorite food 테이블을 만들 때 예외적으로 food_name 이라는 이름으로 컬럼을 만든다
  private Set<String> favoriteFoods = new HashSet<>();

  @ElementCollection
  @CollectionTable(name = "ADDRESS", joinColumns = {
      @JoinColumn(name = "MEMBER_ID")
  })
  private List<Address> addressHistory = new ArrayList<>(); // 임베디드 타입이라서 address 테이블에 컬럼들은 안에 속성 명으로 컬렴이 생성된다
}

