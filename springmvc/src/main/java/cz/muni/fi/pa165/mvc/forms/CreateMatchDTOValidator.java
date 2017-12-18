package cz.muni.fi.pa165.mvc.forms;

import cz.muni.fi.pa165.soccermanager.dto.CreateMatchDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author 456519 Filip Lux
 * @version 12/8/2017.
 */
public class CreateMatchDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> _class) {
        return CreateMatchDTO.class.isAssignableFrom(_class);
    }

    @Override
    public void validate (Object object, Errors errors) {
        CreateMatchDTO createMatchDTO = (CreateMatchDTO) object;

        if (createMatchDTO.getLeagueId() == null)
            errors.rejectValue( "league", "leagueNull");

        if (createMatchDTO.getDate() == null)
            errors.rejectValue( "date", "dateNull");

        if (createMatchDTO.getHomeTeamId() == null)
            errors.rejectValue( "homeTeamId", "homeTeamNull");

        if (createMatchDTO.getAwayTeamId() == null)
            errors.rejectValue( "awayTeamId", "awayTeamNull");

        if (createMatchDTO.getHomeTeamId().equals(createMatchDTO.getAwayTeamId()))
            errors.rejectValue( "awayTeamId", "sameTeams");

 }

}
