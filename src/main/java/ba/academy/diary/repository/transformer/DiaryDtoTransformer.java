package ba.academy.diary.repository.transformer;

import ba.academy.diary.dto.ContraIndicationDto;
import ba.academy.diary.dto.DiaryDto;
import ba.academy.diary.repository.entities.ContraIndicationEntity;
import ba.academy.diary.repository.entities.DiaryEntity;
import ba.academy.diary.repository.entities.TherapyEntity;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class DiaryDtoTransformer implements DtoTransformer<DiaryEntity, DiaryDto> {
  private ContraIndicationDtoTransformer ctTransformer = new ContraIndicationDtoTransformer();
  private TherapyDtoTransformer therapyDtoTransformer = new TherapyDtoTransformer();

  @Override
  public DiaryDto toDto(DiaryEntity entity) {
    DiaryDto diaryInput = new DiaryDto();
    diaryInput.setId(entity.getId());
    if (entity.getUsageDateTime() != null) {
      diaryInput.setDate(java.sql.Timestamp.valueOf(entity.getUsageDateTime()));

    }
    final Set<ContraIndicationEntity> contraIndications = entity.getContraIndications();
    diaryInput.setContraIndications(ctTransformer.toDtoList(contraIndications));
    final TherapyEntity therapy = entity.getTherapy();
    diaryInput.setTherapyDto(therapyDtoTransformer.toDto(therapy));
    return diaryInput;
  }

  @Override
  public DiaryEntity toEntity(DiaryDto dto, DiaryEntity entityInstance) {
    entityInstance.setId(dto.getId());
    if (dto.getDate() != null) {
      entityInstance.setUsageDateTime(new java.sql.Timestamp(
          dto.getDate().getTime()).toLocalDateTime());
    }
    else {
      entityInstance.setUsageDateTime(LocalDateTime.now());
    }
    return entityInstance;
  }
}