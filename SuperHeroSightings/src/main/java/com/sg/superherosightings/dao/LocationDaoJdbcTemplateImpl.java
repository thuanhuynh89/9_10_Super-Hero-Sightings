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
public class LocationDaoJdbcTemplateImpl implements LocationDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_LOCATION
            = "insert into location (locationname, description, addressid) "
            + "values (?, ?, ?)";

    private static final String SQL_DELETE_LOCATION
            = "delete from location where locationid = ?";

    private static final String SQL_UPDATE_LOCATION
            = "update location set locationname = ?, description = ?, addressid = ? "
            + "where locationid = ?";

    private static final String SQL_SELECT_LOCATION
            = "select * from location lo "
            + "inner join address ad on lo.addressid = ad.addressid "
            + "where locationid = ?";

    private static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from location lo "
            + "inner join address ad on lo.addressid = ad.addressid ";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getLocationName(),
                location.getDescription(),
                location.getAddress().getAddressId());

        location.setLocation(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteLocation(int locationId) {
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
    }

    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getLocationName(),
                location.getDescription(),
                location.getAddress().getAddressId(),
                location.getLocation());
    }

    @Override
    public Location getLocationById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION,
                    new LocationDaoJdbcTemplateImpl.LocationMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS,
                new LocationDaoJdbcTemplateImpl.LocationMapper());
    }

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {

            Address ad = new Address();
            ad.setStreetNumber(rs.getString("StreetNumber"));
            ad.setStreetName(rs.getString("StreetName"));
            ad.setCity(rs.getString("City"));
            ad.setState_Province(rs.getString("State_Province"));
            ad.setCountry(rs.getString("country"));
            ad.setPlanet(rs.getString("planet"));
            ad.setGalaxy(rs.getString("galaxy"));
            ad.setLongitude(rs.getString("latitude"));
            ad.setLatitude(rs.getString("longitude"));
            ad.setAddressId(rs.getInt("addressid"));

            Location lc = new Location();
            lc.setLocationName(rs.getString("locationname"));
            lc.setDescription(rs.getString("description"));
            lc.setLocation(rs.getInt("locationid"));
            lc.setAddress(ad);

            return lc;
        }
    }
}
