# Soccer manager REST
To test REST API you need to run tomcat7 on REST API module. 
You can do this from Intelij Idea Maven Projects sidebar by clicking on
    REST API -> Plugins -> tomcat7 -> tomcat7: run 
or from command line by mvn install and mvn tomcat7:run in module folder.
This tutorial describes basic workflow with our project using Postman but you can use any similiar software.
Data are loaded from sample data model on start of tomcat.

Default url is: http://localhost:8080/pa165/rest/
You need to set Content-Type: application/json to your headers in Postman before sending request.

## Teams
http://localhost:8080/pa165/rest/teams/
Some attributes are ommited to from produces JSON for readability and smaller size of response.

### Get all
Method type: GET
http://localhost:8080/pa165/rest/teams/

### Get by ID
Method type: GET
http://localhost:8080/pa165/rest/teams/{id}
where id is id of team you want to get.

example: http://localhost:8080/pa165/rest/teams/1

### Delete
Method type: DELETE
http://localhost:8080/pa165/rest/teams/delete/{id}
where id is id of team you want to delete.

example: http://localhost:8080/pa165/rest/teams/delete/1

### Get by league
Method type: GET
http://localhost:8080/pa165/rest/teams/league/{id}
where id is id of league you want to use to fetch teams.

example: http://localhost:8080/pa165/rest/teams/league/1

### Get by origin
Method type: GET
http://localhost:8080/pa165/rest/teams/origin/{origin}
where origin is country you want to use to fetch teams. Must match NationalityEnum.

example: http://localhost:8080/pa165/rest/teams/origin/England

### Get by manager
Method type: GET
http://localhost:8080/pa165/rest/teams/manager/{id}
where id is id of manager you want to use to fetch teams.

example: http://localhost:8080/pa165/rest/teams/manager/1

### Get by name
Method type: GET
http://localhost:8080/pa165/rest/teams/name/{name}
where name is name of team(case sensitive).

example: http://localhost:8080/pa165/rest/teams/name/Manchester City

### Add player to team
Method type: POST
http://localhost:8080/pa165/rest/teams/{id}/add_player/{playerId}
where id is id of team and playerId is id of player you want to add to team.

example: http://localhost:8080/pa165/rest/teams/1/add_player/5

### Remove player from team
Method type: POST
http://localhost:8080/pa165/rest/teams/{id}/remove_player/{playerId}
where id is id of team and playerId is id of player you want to remove from team.

example: http://localhost:8080/pa165/rest/teams/1/remove_player/5

### Assign manager to team
Method type: POST
http://localhost:8080/pa165/rest/teams/{id}/assign_manager/{managerId}
where id is id of team and managerId is id of manager you want to assign to team.

example: http://localhost:8080/pa165/rest/teams/1/assign_manager/1

### Remove manager from team
Method type: POST
http://localhost:8080/pa165/rest/teams/{id}/remove_manager
where id is id of team which manager you want to remove.

example: http://localhost:8080/pa165/rest/teams/1/remove_manager

### Join league
Method type: POST
http://localhost:8080/pa165/rest/teams/{id}/join_league/{leagueId}
where id is id of team and leagueId is id of league you want to join.

example: http://localhost:8080/pa165/rest/teams/1/join_league/1


### Leavue league
Method type: POST
http://localhost:8080/pa165/rest/teams/{id}/leave_league
where id is id of team which wants to leavue current league.

example: http://localhost:8080/pa165/rest/teams/1/leavue_league

## Players
http://localhost:8080/pa165/rest/players/

### Get all
Method type: GET
http://localhost:8080/pa165/rest/players/

### Get by ID
Method type: GET
http://localhost:8080/pa165/rest/players/{id}
where id is id of player you want to get.

example: http://localhost:8080/pa165/rest/players/1

### Delete
Method type: DELETE
http://localhost:8080/pa165/rest/players/delete/{id}
where id is id of player you want to delete.

example: http://localhost:8080/pa165/rest/players/delete/1

### Get by team
Method type: GET
http://localhost:8080/pa165/rest/players/team/{teamId}
where teamId is id of team you want to use to fetch players.

example: http://localhost:8080/pa165/rest/players/team/1

### Get by nationality
Method type: GET
http://localhost:8080/pa165/rest/teams/nationality/{nationality}
where nationality is country you want to use to fetch players. Must match NationalityEnum.

example: http://localhost:8080/pa165/rest/players/nationality/France

### Get by position
Method type: GET
http://localhost:8080/pa165/rest/teams/position/{position}
where position is position you want to use to fetch players. Must match PositionEnum.

example: http://localhost:8080/pa165/rest/players/position/ATTACKER

### Get freeagents
Method type: GET
http://localhost:8080/pa165/rest/teams/freeagents
Returns all free agents (players without team)

example: http://localhost:8080/pa165/rest/players/freeagents

## Leagues
http://localhost:8080/pa165/rest/leagues/ Some attributes are omitted to from produces JSON for readability and smaller size of response.

### Get all
Method type: GET http://localhost:8080/pa165/rest/leagues/

### Get by ID
Method type: GET http://localhost:8080/pa165/rest/leagues/{id} where id is id of league you want to get.

example: http://localhost:8080/pa165/rest/leagues/1

### Delete
Method type: DELETE http://localhost:8080/pa165/rest/leagues/delete/{id} where id is id of league you want to delete.

example: http://localhost:8080/pa165/rest/leagues/delete/1

### Get by country
Method type: GET http://localhost:8080/pa165/rest/leagues/country/{country} where country is that you want to use to fetch leagues. Must match NationalityEnum.

example: http://localhost:8080/pa165/rest/leagues/country/England

### Get by name
Method type: GET http://localhost:8080/pa165/rest/leagues/name/{name} where name is name of league (case sensitive).

example: http://localhost:8080/pa165/rest/leagues/name/ Pro League

### Add match to league
Method type: POST http://localhost:8080/pa165/rest/league/{id}/add_match/{matchId} where id is id of league and matchId is id of match you want to add to league.

example: http://localhost:8080/pa165/rest/leagues/1/add_match/5

### Remove match from league
Method type: POST http://localhost:8080/pa165/rest/leagues/{id}/remove_match/{matchId} where id is id of league and matchId is id of match you want to remove from league.

example: http://localhost:8080/pa165/rest/leagues/1/remove_match/5


## Managers
http://localhost:8080/pa165/rest/managers/
Some attributes are omitted to from produces JSON for readability and smaller size of response.

### Get all
Method type: GET
http://localhost:8080/pa165/rest/managers/

### Get by ID
Method type: GET
http://localhost:8080/pa165/rest/managers/{id}
where id is id of manager you want to get.

example: http://localhost:8080/pa165/rest/managers/1

### Delete
Method type: DELETE
http://localhost:8080/pa165/rest/managers/delete/{id}
where id is id of manager you want to delete.

example: http://localhost:8080/pa165/rest/managers/delete/1

### Get by team
Method type: GET
http://localhost:8080/pa165/rest/managers/team/{id}
where id is id id team you want to get manager of.

example: http://localhost:8080/pa165/rest/managers/team/1

### Get by name
Method type: GET
http://localhost:8080/pa165/rest/managers/name/{name}
where name is name of manager (case sensitive).

example: http://localhost:8080/pa165/rest/managers/name/Jose%20Mourinho

### Get by email
Method type: GET
http://localhost:8080/pa165/rest/managers/email/{email}
where email is email of manager (case sensitive).

example: http://localhost:8080/pa165/rest/managers/email/jose@mail.com

### Get by nationality
Method type: GET
http://localhost:8080/pa165/rest/managers/nationality/{nationality}
where nationality is country you want to use to fetch managers. Must match NationalityEnum.

example: http://localhost:8080/pa165/rest/manager/nationality/Spain


## Matches
http://localhost:8080/pa165/rest/matches/
Some attributes are omitted to from produces JSON for readability and smaller size of response.

### Get all
Method type: GET
http://localhost:8080/pa165/rest/matches/

### Get by ID
Method type: GET
http://localhost:8080/pa165/rest/matches/{id}
where id is id of match you want to get.

example: http://localhost:8080/pa165/rest/matches/1

### Get by team
Method type: GET
http://localhost:8080/pa165/rest/matches/team/{id}
where id is id id team you want to get matches of.

example: http://localhost:8080/pa165/rest/matches/team/1

### Delete
Method type: DELETE
http://localhost:8080/pa165/rest/matches/delete/{id}
where id is id of match you want to delete.

example: http://localhost:8080/pa165/rest/matches/delete/1

### Get finished
Method type: GET
http://localhost:8080/pa165/rest/matches/finished

example: http://localhost:8080/pa165/rest/matches/finished

### Get by league
Method type: GET
http://localhost:8080/pa165/rest/matches/league/{leagueId}
where leagueId is id of the league

example: http://localhost:8080/pa165/rest/matches/league/1


