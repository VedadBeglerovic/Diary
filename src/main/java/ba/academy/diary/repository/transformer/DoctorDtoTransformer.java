package ba.academy.diary.repository.transformer;


import ba.academy.diary.dto.DoctorDto;
import ba.academy.diary.repository.entities.ContraIndicationEntity;
import ba.academy.diary.repository.entities.DoctorEntity;
import ba.academy.diary.repository.entities.TherapyEntity;

import java.util.Set;

public class DoctorDtoTransformer implements DtoTransformer<DoctorEntity, DoctorDto> {
    private TherapyDtoTransformer therapyTransformer = new TherapyDtoTransformer();
    @Override
    public DoctorDto toDto(DoctorEntity entity) {
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(entity.getId());
        doctorDto.setHospital(entity.getHospital());
        doctorDto.setName(entity.getName());
        final Set<TherapyEntity> therapies = entity.getTherapy();
        doctorDto.setTherapy(therapyTransformer.toDtoList(therapies));
        return doctorDto;
    }

    @Override
    public DoctorEntity toEntity(DoctorDto dto, DoctorEntity entityInstance) {
        entityInstance.setName(dto.getName());
        entityInstance.setHospital(dto.getHospital());
        return entityInstance;
    }
}
