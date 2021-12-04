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
    EntityTransaction tx = em.getTransaction();//엔티티 매니저는 데이터 변경시 트랜잭션을 시작해야 한다.

    tx.begin();

    try{
      Team team = new Team();
      team.setName("TeamA");
      em.persist(team);

      Member member = new Member();
      member.setUsername("member1");
      em.persist(member);

      team.addMember(member);

//      em.flush();
//      em.clear();

      Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시
      List<Member> members = findTeam.getMembers();

      System.out.println(members.size()); // 0

      for(Member m :members){
        System.out.println(m.getUsername());
      }

    } catch(Exception e ){

    } finally {
      tx.commit();
      emf.close();
    }


  }
}