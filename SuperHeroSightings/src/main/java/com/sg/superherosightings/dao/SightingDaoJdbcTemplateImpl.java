/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Location;
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
public class SightingDaoJdbcTemplateImpl implements SightingDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_SIGHTING
            = "insert into sighting (sightingdate, description, heroid, locationid) "
            + "values (?, ?, ?, ?)";

    private static final String SQL_DELETE_SIGHTING
            = "delete from sighting where sightingid = ?";

    private static final String SQL_UPDATE_SIGHTING
            = "update sighting set sightingdate = ?, description = ?, heroid = ?, locationid = ? "
            + " where sightingid = ?";

    private static final String SQL_SELECT_SIGHTING
            = "select * from sighting s "
            + "inner join hero h on s.heroid = h.heroid "
            + "inner join location l on l.locationid = s.locationid "
            + "where sightingid = ?";

    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from sighting s "
            + "inner join hero h on s.heroid = h.heroid "
            + "inner join location l on l.locationid = s.locationid "
            + "order by sightingid DESC";

            
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getSightingDate(),
                sighting.getDescription(),
                sighting.getHero().getHeroId(),
                sighting.getLocation().getLocation());

        int sightingID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        sighting.setSightingId(sightingID);
    }

    @Override
    public void deleteSighting(int sightingId) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingId);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sighting.getSightingDate(),
                sighting.getDescription(),
                sighting.getHero().getHeroId(),
                sighting.getLocation().getLocation(),
                sighting.getSightingId());
    }

    @Override
    public Sighting getSightingById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING,
                    new SightingDaoJdbcTemplateImpl.SightingMapper(),
                    id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS,
                new SightingDaoJdbcTemplateImpl.SightingMapper());
    }
    
    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
           
            Location lc = new Location();
            lc.setLocationName(rs.getString("locationname"));
            lc.setDescription(rs.getString("description"));
            lc.setLocation(rs.getInt("locationid"));

            Hero hero = new Hero();
            hero.setAlias(rs.getString("Alias"));
            hero.setFirstName(rs.getString("firstname"));
            hero.setLastName(rs.getString("lastname"));
            hero.setDescription(rs.getString("description"));
            hero.setHeroId(rs.getInt("HeroId"));

            Sighting si = new Sighting();
            si.setSightingDate(rs.getString("sightingdate"));
            si.setDescription(rs.getString("description"));
            si.setSightingId(rs.getInt("sightingid"));
            hero.setHeroId(rs.getInt("HeroId"));
            lc.setLocation(rs.getInt("locationid"));
            si.setLocation(lc);     //sets the location and hero inside sighting dto object
            si.setHero(hero);

            return si;

        }
    }
}
