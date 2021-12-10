package ba.academy.diary.services;

import ba.academy.diary.dto.ContraIndicationDto;
import ba.academy.diary.dto.DoctorDto;
import ba.academy.diary.dto.TherapyDto;
import ba.academy.diary.repository.DoctorRepository;
import ba.academy.diary.repository.TherapyRepository;
import ba.academy.diary.repository.entities.ContraIndicationEntity;
import ba.academy.diary.repository.entities.DiaryEntity;
import ba.academy.diary.repository.entities.DoctorEntity;
import ba.academy.diary.repository.entities.TherapyEntity;
import ba.academy.diary.repository.transformer.DoctorDtoTransformer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class DoctorServiceImpl implements DoctorService{
    private DoctorDtoTransformer doctorDtoTransformer = new DoctorDtoTransformer();

    @Inject
    TherapyRepository therapyRepository;
    @Inject
    DoctorRepository doctorRepository;

    @Override
    public List<DoctorDto> getDoctors() {
        return doctorDtoTransformer.toDtoList(doctorRepository.findAll().stream().collect(Collectors.toList()));
    }

    @Override
    public DoctorDto addDoctorInput(DoctorDto input) {
        final DoctorEntity doctorEntity = doctorDtoTransformer.toEntity(input, new DoctorEntity());

        Set<TherapyEntity> therapyEntities = new HashSet<>();
        for (TherapyDto th : input.getTherapy()) {
            final TherapyEntity therapyEntity = therapyRepository.findById(th.getId());
            therapyEntities.add(therapyEntity);
        }
        doctorEntity.setTherapy(therapyEntities);

        doctorRepository.persist(doctorEntity);

        return doctorDtoTransformer.toDto(doctorEntity);
    }

    @Override
    public DoctorDto updateDoctorInput(int id, DoctorDto doctorDto) {
        DoctorEntity doctorWithId= doctorRepository.findById(id);
        doctorWithId.setName(doctorDto.getName());
        doctorWithId.setHospital(doctorDto.getHospital());
        return doctorDtoTransformer.toDto(doctorWithId);
    }

    @Override
    public DoctorDto getCDoctorById(int id) {
        return doctorDtoTransformer.toDto(doctorRepository.findById(id));
    }

    @Override
    public boolean deleteDoctorById(int id) {
        return doctorRepository.deleteById(id);
    }

}
