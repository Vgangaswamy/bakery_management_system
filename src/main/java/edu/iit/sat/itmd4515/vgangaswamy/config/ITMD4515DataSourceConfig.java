package edu.iit.sat.itmd4515.vgangaswamy.config;

import jakarta.annotation.sql.DataSourceDefinition;

@DataSourceDefinition(
        name = "java:app/jdbc/itmd4515DS",
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        serverName = "localhost",
        portNumber = 3306,
        databaseName = "itmd4515",
        user = "itmd4515",
        password = "itmd4515",
        properties = {"useSSL=false", "serverTimezone=UTC"}
)
public class ITMD4515DataSourceConfig {
}
