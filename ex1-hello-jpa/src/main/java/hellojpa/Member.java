package hellojpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Fetch;

@Entity
public class Member {
  @Id @GeneratedValue
  @Column(name="MEMBER_ID")
  private Long id;
  private String username;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="TEAM_ID")
  private Team team;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "LOCKER_ID")
  private Locker locker;

  @ManyToMany
  @JoinTable(name = "MEMBER_PRODUCT")
  private List<Product> products = new ArrayList<>();

  @OneToMany(mappedBy = "member")
  private List<MemberProduct> memberProducts = new ArrayList<>();


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

  public Locker getLocker() {
    return locker;
  }

  public void setLocker(Locker locker) {
    this.locker = locker;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
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