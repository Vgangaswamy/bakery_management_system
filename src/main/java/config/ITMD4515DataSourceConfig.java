package config;

import jakarta.annotation.sql.DataSourceDefinition;

@DataSourceDefinition(
        name = "java:app/jdbc/itmd4515DS",
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        serverName = "localhost",
        portNumber = 3306,
        databaseName = "sakila",
        user = "itmd4515",
        password = "itmd4515",
        properties = {"useSSL=false", "serverTimezone=UTC"}
)
public class ITMD4515DataSourceConfig {
}
