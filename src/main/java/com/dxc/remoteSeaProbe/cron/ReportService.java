package com.dxc.remoteSeaProbe.cron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    private static final Logger log = LoggerFactory.getLogger(ReportService.class);

    public void generateReport() {
        log.info("Generating report");
    }
}

