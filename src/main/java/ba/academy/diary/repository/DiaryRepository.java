package ba.academy.diary.repository;

import ba.academy.diary.dto.Medicines;
import ba.academy.diary.repository.entities.DiaryEntity;
import ba.academy.diary.repository.entities.TherapyEntity;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

@ApplicationScoped
@Transactional(TxType.MANDATORY)
public class DiaryRepository extends Repository<DiaryEntity, Integer> {
  public DiaryRepository() {
    super(DiaryEntity.class);
  }

  public List<DiaryEntity> findAllAsList() {
    CriteriaQuery<DiaryEntity> query = criteriaQuery();
    Root<DiaryEntity> table = query.from(DiaryEntity.class);
    query.select(table);
    return entityManager()
        .createQuery(query).getResultList();
  }

  public List<DiaryEntity> findWhereMedicine(Medicines medicine) {
    CriteriaQuery<DiaryEntity> query = criteriaQuery();
    Root<DiaryEntity> root = query.from(DiaryEntity.class);

    Join<DiaryEntity, TherapyEntity> therapyEntityJoin =
        root.join("therapy");

    List<Predicate> predicates = new ArrayList<>();
    predicates.add(cb().equal(therapyEntityJoin.get("medicine"), medicine));

    return entityManager()
        .createQuery(
            query
                .where(predicates.toArray(new Predicate[0]))).getResultList();
  }


}
