package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member {
  @Id @GeneratedValue
  @Column(name="MEMBER_ID") // 요즘 소문자에 언더스코어 방식을 많이 씀
  private Long id;
  @Column(length = 10) // 그냥 추가하면 제약을 DB 확인하지 않아도 파악할 수 있는 장점이 있음
  private String name;
  private String city;
  private String street;
  private String zipcode;
}
