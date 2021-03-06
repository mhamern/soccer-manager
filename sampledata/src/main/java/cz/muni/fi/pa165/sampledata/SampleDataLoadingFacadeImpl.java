package cz.muni.fi.pa165.sampledata;
import cz.muni.fi.pa165.soccermanager.entity.*;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.PositionEnum;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
import cz.muni.fi.pa165.soccermanager.service.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 12/2/2017.
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    private final TeamService teamService;
    private final PlayerService playerService;
    private final LeagueService leagueService;
    private final ManagerService managerService;
    private final MatchService matchService;

    public SampleDataLoadingFacadeImpl(TeamService teamService,
                                       PlayerService playerService,
                                       LeagueService leagueService,
                                       ManagerService managerService,
                                       MatchService matchService) {
        this.teamService = teamService;
        this.playerService = playerService;
        this.leagueService = leagueService;
        this.managerService = managerService;
        this.matchService = matchService;
    }

    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException {
        List<League> leagues = new ArrayList<>();
        List<Manager> managers = new ArrayList<>();
        List<Player> players = new ArrayList<>();
        List<Team> teams = new ArrayList<>();
        List<Match> matches = new ArrayList<>();

        League premierLeague = new League.LeagueBuilder("Premier League", NationalityEnum.England).build();
        leagues.add(premierLeague);
        League ligaSantander = new League.LeagueBuilder("La Liga Santander", NationalityEnum.Spain).build();
        leagues.add(ligaSantander);
        League bundesliga = new League.LeagueBuilder("Bundesliga", NationalityEnum.Germany).build();
        leagues.add(bundesliga);

        Manager mourinho = new Manager.ManagerBuilder("Jose Mourinho", NationalityEnum.Portugal, "jose@mail.com").build();
        managers.add(mourinho);
        Manager guardiola = new Manager.ManagerBuilder("Pep Guardiola", NationalityEnum.Spain, "pepe@mail.com").build();
        managers.add(guardiola);
        Manager klopp = new Manager.ManagerBuilder("Jurgen Klopp", NationalityEnum.Germany, "jurgen@mail.com").build();
        managers.add(klopp);
        Manager admin = new Manager.ManagerBuilder("admin", NationalityEnum.Yemen, "admin@mail.com", true).build();
        managers.add(admin);

        Player pogba = new Player.PlayerBuilder(
                "Paul Pogba",
                PositionEnum.MIDFIELDER,
                NationalityEnum.France,
                LocalDate.of(1990,1,1))
                .number(8)
                .passing(90)
                .shooting(80)
                .speed(70)
                .defence(60)
                .strength(90)
                .goalkeeping(5)
                .build();
        players.add(pogba);

        Player ronaldo = new Player.PlayerBuilder(
                "Cristiano Ronaldo",
                PositionEnum.ATTACKER,
                NationalityEnum.Portugal,
                LocalDate.of(1990,1,1))
                .number(7)
                .passing(85)
                .shooting(95)
                .speed(85)
                .defence(40)
                .strength(85)
                .goalkeeping(5)
                .build();
        players.add(ronaldo);

        Player benzema = new Player.PlayerBuilder(
                "Karim Benzema",
                PositionEnum.ATTACKER,
                NationalityEnum.France,
                LocalDate.of(1990,1,1))
                .number(9)
                .passing(90)
                .shooting(85)
                .speed(70)
                .defence(60)
                .strength(85)
                .goalkeeping(5)
                .build();
        players.add(benzema);

        Player ramos = new Player.PlayerBuilder(
                "Sergio Ramos",
                PositionEnum.DEFENDER,
                NationalityEnum.Spain,
                LocalDate.of(1990,1,1))
                .number(4)
                .passing(70)
                .shooting(60)
                .speed(80)
                .defence(90)
                .strength(80)
                .goalkeeping(5)
                .build();
        players.add(ramos);

        Player lukaku = new Player.PlayerBuilder(
                "Romelu Lukaku",
                PositionEnum.ATTACKER,
                NationalityEnum.Belgium,
                LocalDate.of(1992,1,1))
                .number(9)
                .passing(60)
                .shooting(90)
                .speed(60)
                .defence(50)
                .strength(95)
                .goalkeeping(5)
                .build();
        players.add(lukaku);

        Player degea = new Player.PlayerBuilder(
                "David Degea",
                PositionEnum.GOALKEEPER,
                NationalityEnum.Spain,
                LocalDate.of(1989,1,1))
                .number(1)
                .passing(10)
                .shooting(5)
                .speed(50)
                .defence(10)
                .strength(30)
                .goalkeeping(95)
                .build();
        players.add(degea);

        Player debruyne = new Player.PlayerBuilder(
                "Kevin De Bruyne",
                PositionEnum.MIDFIELDER,
                NationalityEnum.Belgium,
                LocalDate.of(1990,1,1))
                .number(10)
                .passing(95)
                .shooting(90)
                .speed(65)
                .defence(60)
                .strength(70)
                .goalkeeping(5)
                .build();
        players.add(debruyne);

        Player aguero = new Player.PlayerBuilder(
                "Sergio Aguero",
                PositionEnum.ATTACKER,
                NationalityEnum.Argentina,
                LocalDate.of(1992,1,1))
                .number(9)
                .passing(70)
                .shooting(90)
                .speed(80)
                .defence(40)
                .strength(70)
                .goalkeeping(5)
                .build();
        players.add(aguero);

        Player hazard = new Player.PlayerBuilder(
                "Eden Hazard",
                PositionEnum.MIDFIELDER,
                NationalityEnum.Belgium,
                LocalDate.of(1982,1,1))
                .number(10)
                .passing(85)
                .shooting(90)
                .speed(80)
                .defence(70)
                .strength(70)
                .goalkeeping(10)
                .build();
        players.add(hazard);

        Player fabregas = new Player.PlayerBuilder(
                "Francesc Fàbregas",
                PositionEnum.MIDFIELDER,
                NationalityEnum.Spain,
                LocalDate.of(1987,5,4))
                .number(4)
                .passing(85)
                .shooting(90)
                .speed(80)
                .defence(70)
                .strength(70)
                .goalkeeping(10)
                .build();
        players.add(fabregas);

        Team united = new Team.TeamBuilder(
                "Manchester United",
                NationalityEnum.England,
                StadiumEnum.Old_Trafford,
                premierLeague
        ).build();
        united.addPlayer(pogba);
        united.addPlayer(lukaku);
        united.addPlayer(degea);
        united.setManager(mourinho);
        teams.add(united);

        Team city = new Team.TeamBuilder(
                "Manchester City",
                NationalityEnum.England,
                StadiumEnum.Etihad_Stadium,
                premierLeague
        ).build();
        city.addPlayer(aguero);
        city.addPlayer(debruyne);
        city.setManager(guardiola);
        teams.add(city);

        Team chelsea = new Team.TeamBuilder(
                "Chelsea",
                NationalityEnum.England,
                StadiumEnum.Stamford_Bridge,
                premierLeague
        ).build();
        city.addPlayer(hazard);
        city.addPlayer(fabregas);
        teams.add(chelsea);

        Match unitedcity = new Match.MatchBuilder(
                united,
                city,
                LocalDate.of(2017, 10, 10),
                premierLeague).build();
        matches.add(unitedcity);

        Match unitedcityPlayed = new Match.MatchBuilder(
                united,
                city,
                LocalDate.of(2017, 3, 2),
                premierLeague)
                .build();
        unitedcityPlayed.playMatch();
        matches.add(unitedcityPlayed);

        Match unitedcityPlayed2 = new Match.MatchBuilder(
                united,
                city,
                LocalDate.of(2016, 3, 2),
                premierLeague)
                .build();
        unitedcityPlayed2.playMatch();
        matches.add(unitedcityPlayed2);

        Match unitedcityPlayed3 = new Match.MatchBuilder(
                city,
                united,
                LocalDate.of(2015, 3, 2),
                premierLeague)
                .build();
        unitedcityPlayed3.playMatch();
        matches.add(unitedcityPlayed3);

        Match chelseacityNotPlayed1 = new Match.MatchBuilder(
                chelsea,
                city,
                LocalDate.of(2016, 3, 2),
                premierLeague)
                .build();
        matches.add(chelseacityNotPlayed1);

        Match chelseacityNotPlayed2 = new Match.MatchBuilder(
                city,
                chelsea,
                LocalDate.of(2017, 10, 8),
                premierLeague)
                .build();
        matches.add(chelseacityNotPlayed2);


        loadToDb(leagues, managers, players, teams, matches);
    }

    private void loadToDb(List<League> leagues,
        List<Manager> managers,
        List<Player> players,
        List<Team> teams,
        List<Match> matches) {

        for (Player player: players) {
            playerService.create(player);
        }

        for (Manager manager: managers) {
            managerService.create(manager, manager.getEmail());
        }

        for (League league: leagues) {
            leagueService.insert(league);
        }

        for (Team team : teams) {
            teamService.create(team);
        }

        for (Match match: matches) {
            matchService.createMatch(match);
        }

        for (League league: leagueService.fetchAll()) {
            league.setMatches(matchService.fetchByLeague(league));
        }
    }
}
