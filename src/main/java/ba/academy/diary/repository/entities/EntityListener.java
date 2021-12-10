package ba.academy.diary.repository.entities;

import java.time.LocalDateTime;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class EntityListener {


  @PrePersist
  public void prePersist(AbstractEntity entity) {
    entity.setCreatedOn(LocalDateTime.now());
    entity.setModifiedOn(LocalDateTime.now());
  }

  @PreUpdate
  public void preUpdate(AbstractEntity entity) {
    entity.setModifiedOn(LocalDateTime.now());
  }

}
