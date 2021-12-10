package ba.academy.diary.repository.entities;

import ba.academy.diary.dto.MedicineType;
import ba.academy.diary.dto.Medicines;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "codecta", name = "THERAPY")
public class TherapyEntity extends AbstractEntity {

  @SequenceGenerator(
      name = "therapySeq",
      sequenceName = "THERAPY_SEQ",
      schema = "codecta",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "therapySeq")
  @Id
  @Column(name = "ID", nullable = false)
  private Integer id;

  @ManyToMany(mappedBy = "therapy")
  private Set<DoctorEntity> doctors = new HashSet<>();

  @NotNull
  @Column(name = "DESCRIPTION", length = 200, nullable = false)
  private String description;

  @NotNull
  @Column(name = "MEDICINE", length = 40, nullable = false)
  private Medicines medicine;

  @NotNull
  @Column(name = "MEDICINE_TYPE", length = 40, nullable = false)
  private MedicineType medicineType;

  @NotNull
  @Column(name = "COUNT", length = 40, nullable = false)
  private Integer count;


  @OneToOne(mappedBy = "therapy", cascade = CascadeType.ALL)
  private DiaryEntity diary;

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

  public Medicines getMedicine() {
    return medicine;
  }

  public void setMedicine(Medicines medicine) {
    this.medicine = medicine;
  }

  public MedicineType getMedicineType() {
    return medicineType;
  }

  public void setMedicineType(MedicineType medicineType) {
    this.medicineType = medicineType;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public DiaryEntity getDiary() {
    return diary;
  }

  public void setDiary(DiaryEntity diary) {
    this.diary = diary;
  }
}
