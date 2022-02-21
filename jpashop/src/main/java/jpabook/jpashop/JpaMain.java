package jpabook.jpashop;

import com.sun.org.apache.xpath.internal.operations.Or;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.Peroid;

public class JpaMain{
  public static void main(String[] args){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();//엔티티 매니저는 데이터 변경시 트랜잭션을 시작해야 한다.

    tx.begin();

    try {
      Address address = new Address("city","street","10000");

      Member member = new Member();
      member.setName("member1");
      member.setHomeAddress(address);
      em.persist(member);

      Address newAddress = new Address("newCity", address.getStreet(), address.getZipcode());
      member.setHomeAddress(newAddress);

      tx.commit();

    } catch (Exception e){
      e.printStackTrace();
      tx.rollback();
    } finally {
      emf.close();
    }
  }
}