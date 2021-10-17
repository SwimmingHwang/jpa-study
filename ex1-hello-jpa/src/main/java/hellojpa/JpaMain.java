package hellojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain{
  public static void main(String[] args){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();

    tx.begin();

    try{
      // 객체를 생성한 상테(비영속)
      Member member = new Member();
      member.setId(1L);
      member.setName("회원1");

      // 객체를 저장한 상태(영속)
      em.persist(member);

      // 준영속 detached
      em.detach(member);

      // 객체를 삭제한 상태
      em.remove(member);

      tx.commit();
    } catch (Exception e){
      tx.rollback();
    } finally {
      em.close();
    }
    emf.close();
  }
}