package com.dxc.remoteSeaProbe.cron;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReportServiceTest {

    @Autowired
    ReportService reportService;

    @Test
    void shouldGenerateReport() {
        reportService.generateReport();
    }
}

