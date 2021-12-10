package ba.academy.diary.services;

import ba.academy.diary.dto.ContraIndicationDto;
import ba.academy.diary.dto.DiaryDto;
import ba.academy.diary.repository.ContraIndicationRepository;
import ba.academy.diary.repository.DiaryRepository;
import ba.academy.diary.repository.TherapyRepository;
import ba.academy.diary.repository.entities.ContraIndicationEntity;
import ba.academy.diary.repository.entities.DiaryEntity;
import ba.academy.diary.repository.entities.TherapyEntity;
import ba.academy.diary.repository.transformer.DiaryDtoTransformer;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.scheduler.Scheduled;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Cascade;
import org.jboss.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@Transactional
public class DiaryServiceImpl implements DiaryService {
  private static final Logger LOG = Logger.getLogger(DiaryServiceImpl.class);

  private DiaryDtoTransformer diaryDtoTransformer = new DiaryDtoTransformer();

  @Inject DiaryRepository diaryRepository;

  @Inject ContraIndicationRepository contraIndicationRepository;

  @Inject TherapyRepository therapyRepository;

  @Inject Mailer mailer;


  @Scheduled(cron = "${myMethod.cron.expr}")
  void myMethod() {
   /* LOG.info("Scheduled expression myMethod.cron.expr");
    mailer.send(
        Mail.withText("xxxxxxx@gmail.com",
            "Ahoy from Quarkus",
            "A simple email sent from a Quarkus application."
        )
    );*/
  }

  @ConfigProperty(name = "prefix.message")
  String prefix;

  @Override
  public List<DiaryDto> getDiary() {
    return diaryDtoTransformer.toDtoList(diaryRepository.findAllAsList());
  }

  @Override
  public DiaryDto addDiaryInput(DiaryDto input) {

    final DiaryEntity diaryEntity = diaryDtoTransformer.toEntity(input, new DiaryEntity());

    final TherapyEntity byId = therapyRepository.findById(input.getTherapyDto().getId());
    diaryEntity.setTherapy(byId);

    Set<ContraIndicationEntity> contraIndicationEntities = new HashSet<>();
    for (ContraIndicationDto cdto : input.getContraIndications()) {
      final ContraIndicationEntity contraIndicationEntity = contraIndicationRepository.findById(cdto.getId());
      contraIndicationEntities.add(contraIndicationEntity);
    }
    diaryEntity.setContraIndications(contraIndicationEntities);

    diaryRepository.persist(diaryEntity);

    LOG.info(String.format("Created new Diary with id=%s and therapy=%s", diaryEntity.getId(), diaryEntity.getTherapy().getDescription()));


    return diaryDtoTransformer.toDto(diaryEntity);
  }

  @Override
  public DiaryDto getDiaryById(int id) {
    return diaryDtoTransformer.toDto(diaryRepository.findById(id));
  }

  @Override
  public boolean deleteDiaryById(int id) {
    DiaryEntity diaryEntity = diaryRepository.findById(id);
    diaryEntity.getContraIndications().removeAll(diaryEntity.getContraIndications());
    diaryEntity.setTherapy(null);
    diaryRepository.flush();
    diaryRepository.remove(diaryEntity);
    return true;
  }

  @Override
  public DiaryDto updateDiary(int id, DiaryDto diaryDto) {
    return diaryDto;
  }


}
