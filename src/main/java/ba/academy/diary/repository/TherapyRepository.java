package ba.academy.diary.repository;

import ba.academy.diary.repository.entities.TherapyEntity;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

@ApplicationScoped
@Transactional(TxType.MANDATORY)
public class TherapyRepository  extends Repository<TherapyEntity, Integer> {
  public TherapyRepository() {
    super(TherapyEntity.class);
  }

}
