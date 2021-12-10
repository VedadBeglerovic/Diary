package ba.academy.diary.services;

import ba.academy.diary.dto.DiaryDto;
import java.util.List;

public interface DiaryService {

  List<DiaryDto> getDiary();

  DiaryDto addDiaryInput(DiaryDto input);

  DiaryDto getDiaryById(int id);

  boolean deleteDiaryById(int id);

  DiaryDto updateDiary(int id, DiaryDto diaryDto);
}
