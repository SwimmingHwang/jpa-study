package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Member {
  @Id @GeneratedValue
  @Column(name="MEMBER_ID")
  private Long id;
  private String username;

  @ManyToOne
  @JoinColumn(name="TEAM_ID")
  private Team team;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  //  /**
//   * java getter setter 관례에 의한 메소드가 아님을 강조하기 위해 setTeam - >change Team
//   * @param team
//   */
//  public void changeTeam(Team team) {
//    this.team = team;
//    team.getMembers().add(this); // 연관관계 편의 메소드
//  }
}