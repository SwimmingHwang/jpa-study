package jpabook.jpashop;

import com.sun.org.apache.xpath.internal.operations.Or;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

public class JpaMain{
  public static void main(String[] args){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();//엔티티 매니저는 데이터 변경시 트랜잭션을 시작해야 한다.

    tx.begin();

    try {

      Order order = new Order();

      Member member = new Member();
      member.setName("황수영");


      Delivery delivery = new Delivery();
      delivery.setCity("Seoul");

      Item item = new Item();
      item.setName("허니콤보");

      OrderItem orderItem = new OrderItem();
      orderItem.setItem(item);
      orderItem.setCount(1);
      orderItem.setOrderPrice(12000);


      order.setMember(member);
      order.setDelivery(delivery);
      order.addOrderItem(orderItem);

      em.persist(member);
      em.persist(item);

      em.persist(order);


      tx.commit();
    } catch (Exception e){
      e.printStackTrace();
      tx.rollback();
    } finally {
      emf.close();
    }
  }
}