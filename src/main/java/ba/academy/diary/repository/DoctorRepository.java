package ba.academy.diary.repository;

import ba.academy.diary.repository.entities.DiaryEntity;
import ba.academy.diary.repository.entities.DoctorEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class DoctorRepository extends Repository<DoctorEntity,Integer> {
    public DoctorRepository() {
        super(DoctorEntity.class);
    }

    public List<DoctorEntity> findAllAsList() {
        CriteriaQuery<DoctorEntity> query = criteriaQuery();
        Root<DoctorEntity> table = query.from(DoctorEntity.class);
        query.select(table);
        return entityManager()
                .createQuery(query).getResultList();
    }
}
