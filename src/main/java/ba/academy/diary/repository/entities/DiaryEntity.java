package ba.academy.diary.repository.entities;

import ba.academy.diary.dto.MedicineType;
import ba.academy.diary.dto.Medicines;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "codecta", name = "DIARY")
public class DiaryEntity extends AbstractEntity {

  @SequenceGenerator(
      name = "diarySeq",
      sequenceName = "DIARY_SEQ",
      schema = "codecta",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diarySeq")
  @Id
  @Column(name = "ID", nullable = false)
  private Integer id;

  @Override
  public Integer getId() {
    return id;
  }

  @NotNull
  @Column(name = "DATE",  nullable = false)
  private LocalDateTime usageDateTime;

  @ManyToMany
  @JoinTable(
      schema = "codecta",
      name = "DIARY_CONTRA_INDICATION",
      joinColumns = @JoinColumn(name = "DIARY_ID"),
      inverseJoinColumns = @JoinColumn(name = "CONTRA_INDICATION_ID"))
  private Set<ContraIndicationEntity> contraIndications = new HashSet<>();


  @OneToOne
  @JoinColumn(name = "THERAPY_ID")
  private TherapyEntity therapy;

  public void setId(Integer id) {
    this.id = id;
  }

  public LocalDateTime getUsageDateTime() {
    return usageDateTime;
  }

  public void setUsageDateTime(LocalDateTime usageDateTime) {
    this.usageDateTime = usageDateTime;
  }

  public Set<ContraIndicationEntity> getContraIndications() {
    return contraIndications;
  }

  public void setContraIndications(Set<ContraIndicationEntity> contraIndications) {
    this.contraIndications = contraIndications;
  }

  public TherapyEntity getTherapy() {
    return therapy;
  }

  public void setTherapy(TherapyEntity therapy) {
    this.therapy = therapy;
  }
}
