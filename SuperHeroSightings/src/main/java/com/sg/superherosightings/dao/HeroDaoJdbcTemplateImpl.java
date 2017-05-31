/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Member;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author yingy
 */
public class HeroDaoJdbcTemplateImpl implements HeroDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_HERO
            = "insert into Hero (Alias, firstname, lastname, description) "
            + "values (?, ?, ?, ?)";

    private static final String SQL_DELETE_HERO
            = "delete from Hero where heroid = ?";

    private static final String SQL_UPDATE_HERO
            = "update Hero set Alias = ?, firstname = ?, lastname = ?, "
            + "description = ? where heroid = ?";

    private static final String SQL_SELECT_HERO
            = "select * from hero h where heroid = ?";

    private static final String SQL_SELECT_ALL_HEROES
            = "select * from hero h";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addHero(Hero hero) {
        jdbcTemplate.update(SQL_INSERT_HERO,
                hero.getAlias(),
                hero.getFirstName(),
                hero.getLastName(),
                hero.getDescription());

        int heroID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        hero.setHeroId(heroID);
    }

    @Override
    public void deleteHero(int heroId) {
        jdbcTemplate.update(SQL_DELETE_HERO, heroId);
    }

    @Override
    public void updateHero(Hero hero) {
        jdbcTemplate.update(SQL_UPDATE_HERO,
                hero.getAlias(),
                hero.getFirstName(),
                hero.getLastName(),
                hero.getDescription(),
                hero.getHeroId());
    }

    @Override
    public Hero getHeroById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_HERO,
                    new HeroDaoJdbcTemplateImpl.HeroMapper(),
                    id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Hero> getAllHeroes() {
        return jdbcTemplate.query(SQL_SELECT_ALL_HEROES,
                new HeroDaoJdbcTemplateImpl.HeroMapper());
    }

    private static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int i) throws SQLException {

            Hero hero = new Hero();
            hero.setAlias(rs.getString("Alias"));
            hero.setFirstName(rs.getString("firstname"));
            hero.setLastName(rs.getString("lastname"));
            hero.setDescription(rs.getString("description"));
            hero.setHeroId(rs.getInt("HeroId"));
            

            return hero;
        }
    }
}
