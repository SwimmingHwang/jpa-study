package jpabook.jpashop.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable // 값타입인까 붙여줘야 함
public class Address {
  @Column(length = 10) // 이런 규칙 코드를 공통적으로 적용할 때 편하다
  private String city;
  @Column(length = 20)
  private String street;
  @Column(length = 5)
  private String zipcode;

  public String fullAddress(){ // 이런 함수도 작성할 수 있고 객체 지향쩍으로 설계할 수 있다다    return getCity() + getStreet() + getZipcode();
  }

  // 기본생성자를 만들어 줘야 한다. 왜?
  public Address() {
  }

  public Address(String city, String street, String zipcode) {
    this.city = city;
    this.street = street;
    this.zipcode = zipcode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address = (Address) o;
    return Objects.equals(getCity(), address.getCity()) &&
        Objects.equals(getStreet(), address.getStreet()) &&
        Objects.equals(getZipcode(), address.getZipcode());
  }
  // getter 체크 암하면 필드에 직접 호출하는데 getter 그 체크 안하면 프록시 객체일 때 작동을 안 함
  // getter 체크 해줘야 프록시일 때도 실제 target entity가서 값을 가져온다

  @Override
  public int hashCode() {
    return Objects.hash(getCity(), getStreet(), getZipcode());
  }
}
