package ba.academy.diary.repository.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "codecta", name = "CONTRA_INDICATION")
public class ContraIndicationEntity extends AbstractEntity {

  @SequenceGenerator(
      name = "contraIndicationSeq",
      sequenceName = "CONTRA_INDICATION_SEQ",
      schema = "codecta",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contraIndicationSeq")
  @Id
  @Column(name = "ID", nullable = false)
  private Integer id;

  @NotNull
  @Column(name = "DESCRIPTION", length = 200, nullable = false)
  private String description;

  @ManyToMany(mappedBy = "contraIndications")
  private Set<DiaryEntity> diarySet = new HashSet<>();


  @Override
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public Set<DiaryEntity> getDiarySet() {
    return diarySet;
  }

  public void setDiarySet(Set<DiaryEntity> diarySet) {
    this.diarySet = diarySet;
  }
}
