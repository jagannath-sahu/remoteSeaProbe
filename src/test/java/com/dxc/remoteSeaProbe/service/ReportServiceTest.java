package com.dxc.remoteSeaProbe.service;

import com.dxc.remoteSeaProbe.cron.ReportService;
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

