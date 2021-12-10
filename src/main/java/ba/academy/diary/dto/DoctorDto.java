package ba.academy.diary.dto;

import java.util.ArrayList;
import java.util.List;

public class DoctorDto {
    private Integer id;
    private String name;
    private String hospital;
    private List<TherapyDto> therapy;

    public List<TherapyDto> getTherapy() {
        return therapy;
    }

    public void setTherapy(List<TherapyDto> therapy) {
        this.therapy = therapy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
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
