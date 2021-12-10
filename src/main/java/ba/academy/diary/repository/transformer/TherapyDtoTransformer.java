package ba.academy.diary.repository.transformer;

import ba.academy.diary.dto.TherapyDto;
import ba.academy.diary.repository.entities.TherapyEntity;

public class TherapyDtoTransformer  implements DtoTransformer<TherapyEntity, TherapyDto> {

  @Override
  public TherapyDto toDto(TherapyEntity entity) {
    TherapyDto therapyDto = new TherapyDto();
    therapyDto.setId(entity.getId());
    therapyDto.setDescription(entity.getDescription());
    therapyDto.setMedicineType(entity.getMedicineType());
    therapyDto.setMedicines(entity.getMedicine());
    therapyDto.setCount(entity.getCount());
    return therapyDto;
  }

  @Override
  public TherapyEntity toEntity(TherapyDto dto, TherapyEntity entityInstance) {
    entityInstance.setDescription(dto.getDescription());
    entityInstance.setMedicineType(dto.getMedicineType());
    entityInstance.setMedicine(dto.getMedicines());
    entityInstance.setCount(dto.getCount());
    return entityInstance;
  }
}
