package ba.academy.diary.services;
import ba.academy.diary.dto.DoctorDto;

import java.util.List;

public interface DoctorService {
    List<DoctorDto> getDoctors();

    DoctorDto addDoctorInput(DoctorDto input);

    DoctorDto updateDoctorInput(int id, DoctorDto doctorDto);

    DoctorDto getCDoctorById(int id);

    boolean deleteDoctorById(int id);
}
