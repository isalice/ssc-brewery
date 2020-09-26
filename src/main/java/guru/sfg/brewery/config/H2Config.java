package guru.sfg.brewery.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.h2.tools.Server;

import java.sql.SQLException;
@Configuration
public class H2Config {
    @Value("${h2.db.port}")
    private String h2TcpPort;

    /**
     * TCP connection to connect with SQL clients to the embedded h2 database.
     *
     * @see Server
     * @throws SQLException if something went wrong during startup the server.
     * @return h2 db Server
     */
    @Bean
    public Server server() throws SQLException {
//        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", h2TcpPort).start();
        try {
            Server h2Server = Server.createTcpServer().start();
            if (h2Server.isRunning(true)) {
                System.out.println("H2 server was started and is running.");
                return h2Server;
            } else {
                throw new RuntimeException("Could not start H2 server.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to start H2 server: ", e);
        }

    }

    private static void startH2Server() {
        try {
            Server h2Server = Server.createTcpServer().start();
            if (h2Server.isRunning(true)) {
                System.out.println("H2 server was started and is running.");
            } else {
                throw new RuntimeException("Could not start H2 server.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to start H2 server: ", e);
        }
    }

}
