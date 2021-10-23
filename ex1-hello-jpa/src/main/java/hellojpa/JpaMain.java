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

    transaction.begin(); // [트랜잭션] 시작

    Member memberA = new Member(102L,"member2");
    Member memberB = new Member(103L,"member3");

    em.persist(memberA); // == 1차 캐시에 저장 & insert SQL 생성
    em.persist(memberB); // == 1차 캐시에 저장 & insert SQL 생성
    ///여기까지 INSERT SQL을 데이터베이스에 보내지 않는다.

    //커밋하는 순간 데이터베이스에 INSERT SQL을 보낸다.
    transaction.commit(); // [트랜잭션] 커밋
    // ==> flush -> db transaction commit

//    영속성 컨텍스트의 변경내용을 데이터베이스에 반

    transaction.commit();

    emf.close();
  }
}