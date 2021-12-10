package ba.academy.diary.services;

import ba.academy.diary.dto.TherapyDto;
import java.util.List;

public interface TherapyService {
  List<TherapyDto> getTherapies();

  TherapyDto addTherapy(TherapyDto input);

}
