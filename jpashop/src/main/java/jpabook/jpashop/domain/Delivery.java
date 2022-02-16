package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

  @OneToOne(mappedBy = "delivery")
  private Order order; // 어떤 주문에 의한 배달인지 조회할 수 있다

}
