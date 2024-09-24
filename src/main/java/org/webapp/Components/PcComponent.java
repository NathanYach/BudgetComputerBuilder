package org.webapp.Components;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.webapp.Controllers.RunScrappers;
import org.webapp.Services.HardwareService;

@Component
public class PcComponent {

    private final HardwareService hardwareService;

    public PcComponent(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void run(){
        // Initialization code to be executed after the application has started
        RunScrappers scrappers = new RunScrappers();
        scrappers.run(hardwareService);
    }
}