package pl.javastyle.fitcare.startup;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class LoadDataOnStartup implements ApplicationListener<ApplicationReadyEvent> {

    private StartupData startupData;

    public LoadDataOnStartup(StartupData startupData) {
        this.startupData = startupData;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        startupData.loadSampleUser();
    }
}
