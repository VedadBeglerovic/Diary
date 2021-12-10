package ba.academy.diary.services;

import ba.academy.diary.dto.ContraIndicationDto;
import ba.academy.diary.repository.ContraIndicationRepository;
import ba.academy.diary.repository.entities.ContraIndicationEntity;
import ba.academy.diary.repository.transformer.ContraIndicationDtoTransformer;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ContraIndicationServiceImpl implements ContraIndicationService{

  private ContraIndicationDtoTransformer ctTransformer = new ContraIndicationDtoTransformer();

  @Inject ContraIndicationRepository contraIndicationRepository;

  @Override
  public List<ContraIndicationDto> getContraIndicatons() {
    return ctTransformer.toDtoList(contraIndicationRepository.findAll().stream().collect(Collectors.toList()));
  }

  @Override
  public ContraIndicationDto addContraIndications(ContraIndicationDto input) {

    final ContraIndicationEntity contraIndicationEntity = ctTransformer.toEntity(input, new ContraIndicationEntity());
    contraIndicationRepository.persist(contraIndicationEntity);

    return ctTransformer.toDto(contraIndicationEntity);
  }

  @Override
  public ContraIndicationDto updateContraIndicationInput(int id, ContraIndicationDto contraIndicationDto) {
    ContraIndicationEntity contraIndicationWithId = contraIndicationRepository.findById(id);
    contraIndicationWithId.setDescription(contraIndicationDto.getDescription());
    return ctTransformer.toDto(contraIndicationWithId);
  }

  @Override
  public ContraIndicationDto getContraIndicationById(int id) {
    return ctTransformer.toDto(contraIndicationRepository.findById(id));
  }

  @Override
  public boolean deleteContraIndicationById(int id) {
    return contraIndicationRepository.deleteById(id);
  }


}
