/*
 * Copyright (c) 2020 Daimler AG. All rights reserved.
 * Implemented 2020 by DCCS GmbH.
 */

package ba.academy.diary.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.inject.Vetoed;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Helper for restriction building to avoid totally blown base entity service.
 *
 * @param <E> Entity type
 * @param <PK> Primary key type
 * @author pgradwo
 */
@Vetoed
public abstract class Repository<E, PK extends Serializable>
    implements PanacheRepositoryBase<E, PK> {

    private Class<E> entityClass;

    protected Repository(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    /** @return Entity manager instance */
    protected EntityManager entityManager() {
        return getEntityManager();
    }

    /** @return Criteria builder */
    protected CriteriaBuilder cb() {
        return entityManager().getCriteriaBuilder();
    }

    protected CriteriaQuery<E> criteriaQuery() {
        return cb().createQuery(entityClass());
    }


    protected Class<E> entityClass() {
        return this.entityClass;
    }


    public E findBy(PK pk) {
        return entityManager().find(entityClass(), pk);
    }


    /** Flushed the complete entity cache. */
    public void flush() {
        entityManager().flush();
    }

    /**
     * Default method for saving new entity object. Persist takes an entity instance, adds it to the
     * context and makes that instance managed (in future updates to the entity will be tracked).
     *
     * @param entity E
     */
    public void persist(E entity) {
        entityManager().persist(entity);
    }


    /**
     * removes the given entity, entity will be deleted at the end of transaction.
     *
     * @param entity To detach
     * @return Detached instance
     */
    public void remove(E entity) {
        entityManager().remove(entity);
    }


}
