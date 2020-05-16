package issue;

import com.google.inject.persist.Transactional;
import issue.Issue;
import jpa.GenericJpaDao;

import java.util.List;

public class IssueDao extends GenericJpaDao<Issue> {
    @Transactional
    public List<Issue> findUnfinished() {
        return entityManager.createQuery("SELECT t FROM Issue t WHERE t.dateFinished = false ORDER BY t.dateCreated", Issue.class).getResultList();
    }
}
