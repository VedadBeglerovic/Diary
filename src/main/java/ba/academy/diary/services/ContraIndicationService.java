package ba.academy.diary.services;

import ba.academy.diary.dto.ContraIndicationDto;
import java.util.List;

public interface ContraIndicationService {
  List<ContraIndicationDto> getContraIndicatons();

  ContraIndicationDto addContraIndications(ContraIndicationDto input);

  ContraIndicationDto updateContraIndicationInput(int id, ContraIndicationDto contraIndicationDto);

  ContraIndicationDto getContraIndicationById(int id);

  boolean deleteContraIndicationById(int id);
}
