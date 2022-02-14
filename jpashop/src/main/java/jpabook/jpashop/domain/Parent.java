package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Parent {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @OneToMany (mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)  // 양방향으로 설정
  private List<Child> childList = new ArrayList<>();

  // 연관관계 편의 메소드
  public void  addChild(Child child){
    childList.add(child);
    child.setParent(this);
  }

}
