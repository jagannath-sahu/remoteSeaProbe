package com.dxc.remoteSeaProbe.cron;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportCron {

    private final ReportService reportService;

    @Scheduled(cron = "0 */1 * * * *")
    public void run() {
        reportService.generateReport();
    }
}

