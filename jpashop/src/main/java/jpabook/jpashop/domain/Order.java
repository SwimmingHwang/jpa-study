package jpabook.jpashop.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

  @Column(name = "MEMBER_ID")
  private Long memberId; // 관계형 DB를 객체에 맞추는 형식 (데이터 중심 설계)

  private LocalDateTime orderDate; // ORDER_DATE, order_date

  @Enumerated(EnumType.STRING) // Ordinal 쓰면 안 됨 - 나중에 순서 꼬일 수 있어서
  private  OrderStatus status;

}
