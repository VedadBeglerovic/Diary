package ba.academy.diary.repository.entities;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners({EntityListener.class})
public abstract class AbstractEntity<K> {

  @Column(name = "CREATED_ON")
  private LocalDateTime createdOn;

  @Column(name = "MODIFIED_ON")
  private LocalDateTime modifiedOn;


  /**
   * Returns the unique value object ID. This is either the real object ID if this object is already
   * persisted by JPA or null if this object is transient.
   *
   * @return The value object ID
   */
  public abstract K getId();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AbstractEntity)) {
      return false;
    }
    AbstractEntity that = (AbstractEntity) o;
    if (getId() == null && that.getId() == null) {
      return false;
    }
    return Objects.equals(getId(), that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }

  public LocalDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(LocalDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public LocalDateTime getModifiedOn() {
    return modifiedOn;
  }

  public void setModifiedOn(LocalDateTime modifiedOn) {
    this.modifiedOn = modifiedOn;
  }
}
