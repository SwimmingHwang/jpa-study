package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain{
  public static void main(String[] args){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();//엔티티 매니저는 데이터 변경시 트랜잭션을 시작해야 한다.

    transaction.begin();

    Member memberA = new Member(102L,"member102");
    em.persist(memberA);
    em.flush();

    transaction.commit();
    emf.close();
  }
}