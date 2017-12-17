# Soccer manager REST
To test REST API you need to run tomcat7 on REST API module. 
You can do this from Intelij Idea Maven Projects sidebar by clicking on
    REST API -> Plugins -> tomcat7 -> tomcat7: run 
or from command line by mvn install and mvn tomcat7:run in module folder.
This tutorial describes basic workflow with our project using Postman but you can use any similiar software.
Data are loaded from sample data model on start of tomcat.

Default url is: http://localhost:8080/pa165/rest/
You need to set Content-Type: application/json to your headers in Postman before sending request.

##Teams
http://localhost:8080/pa165/rest/teams/
Some attributes are ommited to from produces JSON for readability and smaller size of response.

###Get all
Method type: GET
http://localhost:8080/pa165/rest/teams/

###Get by ID
Method type: GET
http://localhost:8080/pa165/rest/teams/{id}
where id is id of team you want to get.

example: http://localhost:8080/pa165/rest/teams/1

###Delete
Method type: DELETE
http://localhost:8080/pa165/rest/teams/delete/{id}
where id is id of team you want to delete.

example: http://localhost:8080/pa165/rest/teams/delete/1

###Get by league
Method type: GET
http://localhost:8080/pa165/rest/teams/league/{id}
where id is id of league you want to use to fetch teams.

example: http://localhost:8080/pa165/rest/teams/league/1

###Get by origin
Method type: GET
http://localhost:8080/pa165/rest/teams/origin/{origin}
where origin is country you want to use to fetch teams. Must match NationalityEnum.

example: http://localhost:8080/pa165/rest/teams/origin/England

###Get by manager
Method type: GET
http://localhost:8080/pa165/rest/teams/manager/{id}
where id is id of manager you want to use to fetch teams.

example: http://localhost:8080/pa165/rest/teams/manager/1

###Get by name
Method type: GET
http://localhost:8080/pa165/rest/teams/name/{name}
where name is name of team(case sensitive).

example: http://localhost:8080/pa165/rest/teams/name/Manchester City

###Add player to team
Method type: POST
http://localhost:8080/pa165/rest/teams/{id}/add_player/{playerId}
where id is id of team and playerId is id of player you want to add to team.

example: http://localhost:8080/pa165/rest/teams/1/add_player/5

###Remove player from team
Method type: POST
http://localhost:8080/pa165/rest/teams/{id}/remove_player/{playerId}
where id is id of team and playerId is id of player you want to remove from team.

example: http://localhost:8080/pa165/rest/teams/1/remove_player/5

###Assign manager to team
Method type: POST
http://localhost:8080/pa165/rest/teams/{id}/assign_manager/{managerId}
where id is id of team and managerId is id of manager you want to assign to team.

example: http://localhost:8080/pa165/rest/teams/1/assign_manager/1

###Remove manager from team
Method type: POST
http://localhost:8080/pa165/rest/teams/{id}/remove_manager
where id is id of team which manager you want to remove.

example: http://localhost:8080/pa165/rest/teams/1/remove_manager

###Join league
Method type: POST
http://localhost:8080/pa165/rest/teams/{id}/join_league/{leagueId}
where id is id of team and leagueId is id of league you want to join.

example: http://localhost:8080/pa165/rest/teams/1/join_league/1


###Leavue league
Method type: POST
http://localhost:8080/pa165/rest/teams/{id}/leave_league
where id is id of team which wants to leavue current league.

example: http://localhost:8080/pa165/rest/teams/1/leavue_league

##Players
http://localhost:8080/pa165/rest/players/

###Get all
Method type: GET
http://localhost:8080/pa165/rest/players/

###Get by ID
Method type: GET
http://localhost:8080/pa165/rest/players/{id}
where id is id of player you want to get.

example: http://localhost:8080/pa165/rest/players/1

###Delete
Method type: DELETE
http://localhost:8080/pa165/rest/players/delete/{id}
where id is id of player you want to delete.

example: http://localhost:8080/pa165/rest/players/delete/1

###Get by team
Method type: GET
http://localhost:8080/pa165/rest/players/team/{teamId}
where teamId is id of team you want to use to fetch players.

example: http://localhost:8080/pa165/rest/players/team/1

###Get by nationality
Method type: GET
http://localhost:8080/pa165/rest/teams/nationality/{nationality}
where nationality is country you want to use to fetch players. Must match NationalityEnum.

example: http://localhost:8080/pa165/rest/players/nationality/France

###Get by position
Method type: GET
http://localhost:8080/pa165/rest/teams/position/{position}
where position is position you want to use to fetch players. Must match PositionEnum.

example: http://localhost:8080/pa165/rest/players/position/ATTACKER

###Get freeagents
Method type: GET
http://localhost:8080/pa165/rest/teams/freeagents
Returns all free agents (players without team)

example: http://localhost:8080/pa165/rest/players/freeagents
