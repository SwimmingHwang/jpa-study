package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Delivery extends BaseEntity{
  @Id
  @GeneratedValue
  @Column(name = "DELIVERY_ID")
  private Long id;
  private String city;
  private String street;
  private String zipcode;

  @Enumerated(EnumType.STRING)
  private DeliveryStatus status;

  @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY) // 주인이 아니면 mapped by 속성으로 주인을 지정
  private Order order; // 어떤 주문에 의한 배달인지 조회할 수 있다

}
