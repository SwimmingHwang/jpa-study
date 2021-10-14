package hellojpa;

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
//      //저장
//      Member member = new Member();
//      member.setId(2L);
//      member.setName("HelloA");
//
//      em.persist(member);
      //조회
      Member findMember = em.find(Member.class, 2L);

      //삭제
//      em.remove(findMember);

      //수정

      tx.commit();
    } catch (Exception e){
      tx.rollback();
    } finally {
      em.close();
    }
    emf.close();
  }
}