package cz.muni.fi.pa165.soccermanager.service.config;

import cz.muni.fi.pa165.soccermanager.dto.TeamDTO;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.service.TeamService;
import cz.muni.fi.pa165.soccermanager.service.TeamServiceImpl;
import cz.muni.fi.pa165.soccermanager.service.facade.TeamFacadeImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import cz.muni.fi.pa165.soccermanager.PersistentContext;

import java.util.ArrayList;
import java.util.List;


/**
 * @author 445720 Martin Hamernik
 * @version 11/16/2017.
 */

@Configuration
@Import(PersistentContext.class)
@ComponentScan(basePackageClasses = {TeamServiceImpl.class, TeamFacadeImpl.class},
        basePackages = "cz.muni.fi.pa165.soccermanager.service")
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {
        List<String> mappingFiles = new ArrayList<>();
        mappingFiles.add("dozerJdk8Converters.xml");

        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.setMappingFiles(mappingFiles);
        return dozerBeanMapper;
    }
}
