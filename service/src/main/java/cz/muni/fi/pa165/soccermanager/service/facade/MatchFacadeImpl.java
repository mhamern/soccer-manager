package cz.muni.fi.pa165.soccermanager.service.facade;

import cz.muni.fi.pa165.soccermanager.dto.CreateMatchDTO;
import cz.muni.fi.pa165.soccermanager.dto.MatchDTO;
import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Match;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.facade.MatchFacade;
import cz.muni.fi.pa165.soccermanager.service.BeanMappingService;
import cz.muni.fi.pa165.soccermanager.service.LeagueService;
import cz.muni.fi.pa165.soccermanager.service.MatchService;
import cz.muni.fi.pa165.soccermanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

/**
 * @author 456519 Filip Lux
 * @version 11/20/2017
 */
public class MatchFacadeImpl implements MatchFacade {

    @Inject
    private MatchService matchService;

    @Inject
    private TeamService teamService;

    @Inject
    private LeagueService leagueService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createMatch(CreateMatchDTO match) {
        Match mappedMatch = beanMappingService.mapTo(match, Match.class);

        Team homeTeam = teamService.fetchByName(match.getHomeTeamName());
        Team awayTeam = teamService.fetchByName(match.getAwayTeamName());

        mappedMatch.setHomeTeam(homeTeam);
        mappedMatch.setAwayTeam(awayTeam);
        mappedMatch.setDate(match.getDate());

        //save Match
        Match newMatch = matchService.createMatch(mappedMatch);

        return newMatch.getId();
    }

    @Override
    public List<MatchDTO> getMatchesByTeam(Long teamId) {

        Team team = teamService.fetchById(teamId);

        List<MatchDTO> mappedMatches = beanMappingService.mapTo(
                matchService.fetchByTeam(team),
                MatchDTO.class);
        return mappedMatches;
    }

    @Override
    public List<MatchDTO> getMatchesByLeague(Long leagueId) {

        League league = leagueService.fetchById(leagueId);
        List<MatchDTO> mappedMatches = beanMappingService.mapTo(league.getMatches(), MatchDTO.class);
        return mappedMatches;
    }

    @Override
    public List<MatchDTO> getFinishedMatches() {
    List<Match> matchList = matchService.fetchFinished();
        return beanMappingService.mapTo(matchList, MatchDTO.class);

    }

    @Override
    public MatchDTO getMatchById(Long id) {

        Match match = matchService.fetchById(id);
        return beanMappingService.mapTo(match, MatchDTO.class);
    }

    @Override
    public void addHomeTeam(Long matchId, Long teamId) {
        Match match = matchService.fetchById(matchId);
        Team homeTeam = teamService.fetchById(teamId);
        match.setHomeTeam(homeTeam);
        matchService.updateMatch(match);
    }

    @Override
    public void addAwayTeam(Long matchId, Long teamId) {
        Match match = matchService.fetchById(matchId);
        Team awayTeam = teamService.fetchById(teamId);
        match.setAwayTeam(awayTeam);
        matchService.updateMatch(match);

    }

    @Override
    public boolean isFinished(Long matchId) {
        Match match = matchService.fetchById(matchId);
        return match.isFinished();
    }

    @Override
    public void play(Long matchId) {
        Match match = matchService.fetchById(matchId);
        match.playMatch();
        matchService.updateMatch(match);

    }
}
