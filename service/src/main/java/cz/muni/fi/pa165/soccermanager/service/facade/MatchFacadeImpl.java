package cz.muni.fi.pa165.soccermanager.service.facade;

import cz.muni.fi.pa165.soccermanager.dto.CreateMatchDTO;
import cz.muni.fi.pa165.soccermanager.dto.MatchDTO;
import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Match;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.facade.MatchFacade;
import cz.muni.fi.pa165.soccermanager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author 456519 Filip Lux
 * @version 11/20/2017
 */

@Service
@Transactional
public class MatchFacadeImpl implements MatchFacade {

    private MatchService matchService;

    private TeamService teamService;

    private LeagueService leagueService;

    private BeanMappingService beanMappingService;

    @Inject
    public MatchFacadeImpl(
            LeagueService leagueService,
            MatchService matchService,
            TeamService teamService,
            BeanMappingService beanMappingService
    ) {
        this.leagueService = leagueService;
        this.matchService = matchService;
        this.teamService = teamService;
        this.beanMappingService = beanMappingService;
    }

    @Override
    public Long createMatch(CreateMatchDTO match) {
        Match mappedMatch = beanMappingService.mapTo(match, Match.class);

        Team homeTeam = teamService.fetchById(match.getHomeTeamId());
        Team awayTeam = teamService.fetchById(match.getAwayTeamId());
        League league = leagueService.fetchById(match.getLeagueId());

        mappedMatch.setHomeTeam(homeTeam);
        mappedMatch.setAwayTeam(awayTeam);
        mappedMatch.setDate(match.getDate());
        mappedMatch.setLeague(league);

        //save Match
        Match newMatch = matchService.createMatch(mappedMatch);
        Long id = newMatch.getId();

        return id;
    }

    @Override
    public List<MatchDTO> getAllMatches() {
        List<Match> matchList = matchService.fetchAll();
        return beanMappingService.mapTo(matchList, MatchDTO.class);
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
        List<MatchDTO> mappedMatches = beanMappingService.mapTo(matchService.fetchByLeague(league), MatchDTO.class);
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
    public void deleteMatch(Long id) {

        matchService.deleteMatch(id);
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

    @Override
    public void removeMatch(Long matchId, Long leagueId) {
        Match removedMatch = matchService.fetchById(matchId);
        League league = leagueService.fetchById(leagueId);
        leagueService.removeMatch(removedMatch, league);
    }

    @Override
    public void addMatch(Long matchId, Long leagueId) {
        Match addedMatch = matchService.fetchById(matchId);
        League league = leagueService.fetchById(leagueId);
        leagueService.addMatch(addedMatch, league);

    }
}
