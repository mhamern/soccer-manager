package cz.muni.fi.pa165.soccermanager.service.config;

import cz.muni.fi.pa165.soccermanager.dto.PlayerDTO;
import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.service.PlayerServiceImpl;
import cz.muni.fi.pa165.soccermanager.service.facade.PlayerFacadeImpl;
import cz.muni.fi.pa165.soccermanager.service.facade.TeamFacadeImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import cz.muni.fi.pa165.soccermanager.PersistentContext;


/**
 * @author 445720 Martin Hamernik
 * @version 11/16/2017.
 */

@Configuration
@Import(PersistentContext.class)
@ComponentScan(basePackageClasses = {PlayerServiceImpl.class, TeamFacadeImpl.class})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }

    public class DozerCustomConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(Player.class, PlayerDTO.class);
        }
    }
}
