package cz.muni.fi.pa165.soccermanager.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.PositionEnum;

import java.time.LocalDate;

/**
 * @author 445720 Martin Hamernik
 * @version 11/16/2017.
 */
public class PlayerDTO {
    private Long id;
    private String name;
    private PositionEnum position;
    private NationalityEnum nationality;
    private int number;
    private LocalDate birthDate;
    private int shooting;
    private int passing;
    private int speed;
    private int defence;
    private int strength;
    private int goalkeeping;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PositionEnum getPosition() {
        return position;
    }

    public void setPosition(PositionEnum position) {
        this.position = position;
    }

    public NationalityEnum getNationality() {
        return nationality;
    }

    public void setNationality(NationalityEnum nationality) {
        this.nationality = nationality;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getShooting() {
        return shooting;
    }

    public void setShooting(int shooting) {
        this.shooting = shooting;
    }

    public int getPassing() {
        return passing;
    }

    public void setPassing(int passing) {
        this.passing = passing;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getGoalkeeping() {
        return goalkeeping;
    }

    public void setGoalkeeping(int goalkeeping) {
        this.goalkeeping = goalkeeping;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerDTO playerDTO = (PlayerDTO) o;

        if (number != playerDTO.number) return false;
        if (!name.equals(playerDTO.name)) return false;
        if (position != playerDTO.position) return false;
        if (nationality != playerDTO.nationality) return false;
        return birthDate.equals(playerDTO.birthDate);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + nationality.hashCode();
        result = 31 * result + number;
        result = 31 * result + birthDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "name='" + name + '\'' +
                ", position=" + position +
                ", nationality=" + nationality +
                ", number=" + number +
                ", birthDate=" + birthDate +
                '}';
    }
}
