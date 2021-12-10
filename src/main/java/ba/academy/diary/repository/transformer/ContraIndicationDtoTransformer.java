package ba.academy.diary.repository.transformer;

import ba.academy.diary.dto.ContraIndicationDto;
import ba.academy.diary.repository.entities.ContraIndicationEntity;

public class ContraIndicationDtoTransformer implements DtoTransformer<ContraIndicationEntity, ContraIndicationDto> {

  @Override
  public ContraIndicationDto toDto(ContraIndicationEntity entity) {
    ContraIndicationDto contraIndicationDto = new ContraIndicationDto();
    contraIndicationDto.setId(entity.getId());
    contraIndicationDto.setDescription(entity.getDescription());
    return contraIndicationDto;
  }

  @Override
  public ContraIndicationEntity toEntity(ContraIndicationDto dto, ContraIndicationEntity entityInstance) {
    entityInstance.setDescription(dto.getDescription());
    return entityInstance;
  }
}
