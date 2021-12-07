package jpabook.jpashop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ORDERS") // DB마다 Order이 안 되는 경우가 있어서 Orders로 많이
public class Order {

  @Id
  @GeneratedValue
  @Column(name = "ORDER_ID")
  private Long id;

  /**
   * 연관관계 매핑
   */
  @ManyToOne
  @JoinColumn(name = "MEMBER_ID")
  private Member member;

  @OneToMany(mappedBy = "order")
  private List<OrderItem> orderItems = new ArrayList<>();

  @OneToOne
  @JoinColumn(name="DELIVERY_ID") // order이 연관관계 주인, delivery_id 컬럼을 Insert 하는 주체
  private Delivery delivery;

  private LocalDateTime orderDate; // ORDER_DATE, order_date

  @Enumerated(EnumType.STRING) // Ordinal 쓰면 안 됨 - 나중에 순서 꼬일 수 있어서
  private  OrderStatus status;

  public void addOrderItem(){

  }
}

/**
 *  일대일 관계는 외래키를 양쪽 어디나 둘 수 있다.
 *  - orders에 두면 성능(바로확인 가능, 나중에 프록시) + 객체 입장에서 편리함
 *  - delivery에 두면 1 -> N으로 확장이 편리함(DB 컬럼 변경없이 N으로 변경 가능)
 */