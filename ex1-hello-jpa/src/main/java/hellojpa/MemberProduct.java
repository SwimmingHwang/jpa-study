package hellojpa;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MemberProduct {

  @Id @GeneratedValue
  private String id;


  @ManyToOne
  @JoinColumn(name = "MEMBER_ID")
  private Member member;

  @ManyToOne
  @JoinColumn(name = "PRODUCT_ID")
  private Product product;

  private int count;
  private int price;

  private LocalDateTime orderDatetime;

  public void setId(String id) {
    this.id = id;
  }

  @Id
  public String getId() {
    return id;
  }
}
