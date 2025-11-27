package com.dxc.remoteSeaProbe.service;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class PostgresTestService {

    private final DataSource dataSource;

    public static final Logger logger = LoggerFactory.getLogger(PostgresTestService.class);

    public PostgresTestService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void init() {
        try (var conn = dataSource.getConnection()) {
            logger.info("Postgres Connected successfully: {}", conn.getMetaData().getURL());

            var stmt = conn.createStatement();
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS remote_sea_probe (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(255),
                    direction_facing VARCHAR(255)
                )
            """);

            logger.info("Table created or exists.");
        } catch (Exception e) {
            logger.error("error getting postgres connection: {}", e.getMessage());
        }
    }
}

