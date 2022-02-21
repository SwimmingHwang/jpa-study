package jpabook.jpashop.domain;

import java.time.LocalDateTime;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class Peroid {

  private LocalDateTime startDate;
  private LocalDateTime endDate;

  public boolean isWork(){
    return true;
  }
}
