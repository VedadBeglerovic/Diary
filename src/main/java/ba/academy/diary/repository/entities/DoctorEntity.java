package ba.academy.diary.repository.entities;

import javax.persistence.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "codecta", name = "DOCTOR")
public class DoctorEntity extends AbstractEntity{
    @SequenceGenerator(
            name = "doctorSeq",
            sequenceName = "DOCTOR_SEQ",
            schema = "codecta",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctorSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;


    @Column(name = "NAME", length = 200)
    private String name;


    @Column(name = "HOSPITAL", length = 200)
    private String hospital;

    @ManyToMany
    @JoinTable(
            schema = "codecta",
            name = "DOCTOR_THERAPY",
            joinColumns = @JoinColumn(name = "DOCTOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "THERAPY_ID"))
    private Set<TherapyEntity> therapy = new HashSet<>();

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Set<TherapyEntity> getTherapy() {
        return therapy;
    }

    public void setTherapy(Set<TherapyEntity> therapy) {
        this.therapy = therapy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
}
