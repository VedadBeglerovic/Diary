package ba.academy.diary.repository;

import ba.academy.diary.repository.entities.ContraIndicationEntity;
import ba.academy.diary.repository.entities.DiaryEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@ApplicationScoped
@Transactional(TxType.MANDATORY)
public class ContraIndicationRepository  extends Repository<ContraIndicationEntity, Integer> {
  public ContraIndicationRepository() {
    super(ContraIndicationEntity.class);
  }

/*
  public ContraIndicationEntity findContraIndicationById(String id) {
    CriteriaQuery<ContraIndicationEntity> query = criteriaQuery();
    CriteriaQuery cq = query.where(cb().equal(id,cb.))
    Root e = cq.from(ContraIndicationEntity.class);
    cq.where(cb.equal(e.type(), entityType));
    Query query = em.createQuery(cq);
    List<Entity> result = query.getResultList();
  }*/
}
