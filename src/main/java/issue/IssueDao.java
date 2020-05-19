package issue;

import com.google.inject.persist.Transactional;
import jpa.GenericJpaDao;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

/**
 * DAO class for the {@link Issue} entity.
 * Extends of GenericJpaDao class.
 *
 * @author Csipkes Gergo
 * @author csipkesgeri@gmail.com
 * @version 0.3
 */
public class IssueDao extends GenericJpaDao<Issue> {
    /**
     * Returns the list of issues where the finished date is empty.
     *
     * @return the list of issues where the finished date is empty.
     */
    @Transactional
    public List<Issue> findUnfinished() {
        return entityManager.createQuery("SELECT t FROM Issue t WHERE t.dateFinished IS NULL ORDER BY t.dateCreated", Issue.class).getResultList();
    }

    /**
     * Optionally returns an issue with the given title and details.
     *
     * @param title The title of the issue.
     * @param details The details of the issue.
     *
     * @return Issue (optional) returns an issue with the given title and details.
     */
    @Transactional
    public Optional<Issue> findByTitleAndDetails(String title, String details) {
        try {
            return Optional.of(entityManager.createQuery("SELECT i FROM Issue i WHERE i.title = :title AND i.details = :details ORDER BY i.dateCreated", Issue.class)
                .setParameter("title", title)
                .setParameter("details", details)
                .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
