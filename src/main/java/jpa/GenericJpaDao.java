package jpa;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.inject.persist.Transactional;

/**
 * Abstract class for the DAOs with Java Persistence API.
 * @param <T>
 */
public abstract class GenericJpaDao<T> {

    protected Class<T> entityClass;
    protected EntityManager entityManager;

    /**
     * Creates a {@code GenericJpaCao} entity.
     */
    public GenericJpaDao() {
        Class clazz = ! getClass().getName().contains("$$EnhancerByGuice$$") ? getClass() : getClass().getSuperclass(); // dirty Guice trick
        entityClass = (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Get the entity manager.
     *
     * @return The entity manager.
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }


    /**
     * Sets the entity manager.
     * @param entityManager The entity manager.
     */
    @Inject
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Save the entity.
     *
     * @param entity The entity object.
     */
    @Transactional
    public void persist(T entity) {
        entityManager.persist(entity);
    }

    /**
     * Optionally finds the entity by the primary key.
     *
     * @param primaryKey Primary key of the entity.
     * @return Optionally returns the entity.
     */
    @Transactional
    public Optional<T> find(Object primaryKey) {
        return Optional.ofNullable(entityManager.find(entityClass, primaryKey));
    }

    /**
     * Find all of the entities.
     *
     * @return List of the entities.
     */
    @Transactional
    public List<T> findAll() {
        TypedQuery<T> typedQuery = entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass);
        return typedQuery.getResultList();
    }

    /**
     * Remove an entity.
     *
     * @param entity The entity.
     */
    @Transactional
    public void remove(T entity) {
        entityManager.remove(entity);
    }

    /**
     * Update an entity.
     *
     * @param entity The entity.
     */
    @Transactional
    public void update(T entity) {
        entityManager.merge(entity);
    }

}
