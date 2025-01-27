package at.spengergasse.bookecho;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;


@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration {

    @Value("${app.test.docker.db.image.name}")
    private String imageName;

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgreSQLContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse(imageName));
    }
}
