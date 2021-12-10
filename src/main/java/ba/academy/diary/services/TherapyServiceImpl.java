package ba.academy.diary.services;


import ba.academy.diary.dto.TherapyDto;
import ba.academy.diary.repository.TherapyRepository;
import ba.academy.diary.repository.entities.TherapyEntity;
import ba.academy.diary.repository.transformer.TherapyDtoTransformer;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class TherapyServiceImpl implements TherapyService {

  private TherapyDtoTransformer therapyDtoTransformer = new TherapyDtoTransformer();

  @Inject TherapyRepository therapyRepository;

  @Override
  public List<TherapyDto> getTherapies() {
    return therapyDtoTransformer.toDtoList(therapyRepository.findAll().stream().collect(Collectors.toList()));
  }

  @Override
  public TherapyDto addTherapy(TherapyDto input) {

    final TherapyEntity therapyEntity = therapyDtoTransformer.toEntity(input, new TherapyEntity());
    therapyRepository.persist(therapyEntity);

    return therapyDtoTransformer.toDto(therapyEntity);
  }
}
