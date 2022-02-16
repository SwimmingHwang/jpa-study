package jpabook.jpashop.domain;

import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
  private String createdBy;
  private LocalDateTime createdDate;
  private String lastModifiedBy;
  private LocalDateTime lastModifiedDate;
}