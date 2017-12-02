package cz.muni.fi.pa165.sampledata;

import cz.muni.fi.pa165.sampledata.SampleDataLoadingFacade;
import cz.muni.fi.pa165.sampledata.SampleDataLoadingFacadeImpl;
import cz.muni.fi.pa165.soccermanager.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author 445720 Martin Hamernik
 * @version 12/2/2017.
 */

@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {SampleDataLoadingFacadeImpl.class})
public class SoccerManagerSampleDataConfiguration {

    @Autowired
    SampleDataLoadingFacade sampleDataLoadingFacade;

    @PostConstruct
    public void dataLoading() throws IOException {
        sampleDataLoadingFacade.loadData();
    }
}
