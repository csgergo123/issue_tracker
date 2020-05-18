package guice;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;

/**
 * Class for Persistent data storage.
 */
public class PersistenceModule extends AbstractModule {

    private String jpaUnit;

    /**
     * Create a {@code PersistentModule} entity.
     *
     * @param jpaUnit Persistence unit.
     */
    public PersistenceModule(String jpaUnit) {
        this.jpaUnit = jpaUnit;
    }

    @Override
    protected void configure() {
        install(new JpaPersistModule(jpaUnit));
        bind(JpaInitializer.class).asEagerSingleton();
    }

}
