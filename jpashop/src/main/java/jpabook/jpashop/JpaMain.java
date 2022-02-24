package jpabook.jpashop;

import com.sun.org.apache.xpath.internal.operations.Or;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.AddressEntity;
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
      Member member = new Member();
      member.setName("member1");
      member.setAddress(new Address("homeCity", "street", "10000"));

      member.getFavoriteFoods().add("치킨");
      member.getFavoriteFoods().add("족발");
      member.getFavoriteFoods().add("피자");

      member.getAddressHistory().add(new AddressEntity("old1", "street", "10000"));
      member.getAddressHistory().add(new AddressEntity("old2", "street", "10000"));

      em.persist(member);

      em.flush(); // DB엔 넣고
      em.clear(); // 깔끔한 상태에서 조회하려고 clear

      Member findMember = em.find(Member.class, member.getId());
      Address a = findMember.getAddress();

//      findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));
//
//      // 컬렉션에 있는 치킨을 한식으로 바꾸고 싶다
//      findMember.getFavoriteFoods().remove("치킨");
//      findMember.getFavoriteFoods().add("한식");

//      findMember.getAddressHistory().remove(new Address("old1", "street", "10000")); // equals 함수 쓰이니 equals 재정의 잘 해야 함
//      findMember.getAddressHistory().add(new Address("newCity1", "street", "10000"));

      tx.commit();

    } catch (Exception e){
      e.printStackTrace();
      tx.rollback();
    } finally {
      emf.close();
    }
  }
}