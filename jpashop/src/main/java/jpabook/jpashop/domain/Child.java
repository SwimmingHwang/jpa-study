package jpabook.jpashop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

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
