package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.rest.ApiUris;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 445720 Martin Hamernik
 * @version 11/29/2017.
 */
@RestController
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, String> getResources() {

        Map<String, String> resourceMap = new HashMap<>();

        resourceMap.put("teams_uri", ApiUris.ROOT_URI_PLAYERS);
        resourceMap.put("players_uri", ApiUris.ROOT_URI_PLAYERS);
        resourceMap.put("managers_uri", ApiUris.ROOT_URI_MANAGERS);
        resourceMap.put("leagues_uri", ApiUris.ROOT_URI_LEAGUES);
        resourceMap.put("matches_uri", ApiUris.ROOT_URI_MATCHES);

        return Collections.unmodifiableMap(resourceMap);

    }
}
