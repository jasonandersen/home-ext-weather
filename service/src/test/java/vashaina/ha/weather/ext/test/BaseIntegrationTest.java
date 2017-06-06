package vashaina.ha.weather.ext.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Provides Spring application context awareness and dependency injection.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseIntegrationTest {
    //NOOP - just allows subclasses to inherit Spring test context configuration
}
