package at.spengergasse.bookecho;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration
public class BookEchoApplicationTestMain {
    @Value("${app.test.docker.db.image.name}")
    private String imageName;

    @Value("${app.test.docker.db.container.name}")
    private String containerName;

    @Value("${app.test.db.name}")
    private String dbName;

    @Value("${app.test.db.owner}")
    private String dbOwner;

    @Value("${app.test.db.password}")
    private String dbOwnerPassword;

    @Value("${app.test.db.port}")
    private Integer dbPort;

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgreSQLContainer() {

        final Integer containerPort = 5432;

        PortBinding portBinding = new PortBinding(Ports.Binding.bindPort(dbPort), new ExposedPort(containerPort));

        return new PostgreSQLContainer<>(DockerImageName.parse(imageName))
                .withCreateContainerCmdModifier(cmd -> {
                    cmd.withName(containerName); //container name
                    cmd.withHostConfig(new HostConfig().withPortBindings(portBinding));
                })
                .withDatabaseName(dbName)
                .withUsername(dbOwner)
                .withPassword(dbOwnerPassword)
                .withReuse(true);
    }

    public static void main(String[] args) {
        SpringApplication.from(BookEchoApplication::main)
                .with(BookEchoApplicationTestMain.class)
                .run(args);
    }
}


