package guice;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.inject.persist.PersistService;

/**
 * This class initialize the Java Persistence API.
 */
@Singleton
public class JpaInitializer {

    /**
     * {@code JpaInitializer} constructor.
     *
     * @param persistService Persistent service
     */
    @Inject
    public JpaInitializer (PersistService persistService) {
        persistService.start();
    }

}
