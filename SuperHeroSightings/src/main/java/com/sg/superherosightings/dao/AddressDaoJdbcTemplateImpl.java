/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.HeroPower;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Member;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Power;
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
public class AddressDaoJdbcTemplateImpl implements AddressDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_ADDRESS
            = "insert into address (StreetNumber, StreetName, City, State_Province, "
            + "country, planet, galaxy, latitude, longitude) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_ADDRESS
            = "delete from address where addressid = ?";

    private static final String SQL_UPDATE_ADDRESS
            = "update address set StreetNumber = ?, StreetName = ?, City = ?, "
            + "State_Province = ?, country = ?, planet = ?, galaxy = ?, latitude = ?, longitude = ? "
            + "where addressid =  ?";

    private static final String SQL_SELECT_ADDRESS
            = "select * from address a "
            + "where addressid = ?";

    private static final String SQL_SELECT_ALL_ADDRESSES
            = "select * from address a ";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addAddress(Address address) {
        jdbcTemplate.update(SQL_INSERT_ADDRESS,
                address.getStreetNumber(),
                address.getStreetName(),
                address.getCity(),
                address.getState_Province(),
                address.getCountry(),
                address.getPlanet(),
                address.getGalaxy(),
                address.getLongitude(),
                address.getLatitude());

        int addressID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        address.setAddressId(addressID);
    }

    @Override
    public void deleteAddress(int addressId) {
        jdbcTemplate.update(SQL_DELETE_ADDRESS, addressId);
    }

    @Override
    public void updateAddress(Address address) {
        jdbcTemplate.update(SQL_UPDATE_ADDRESS,
                address.getStreetNumber(),
                address.getStreetName(),
                address.getCity(),
                address.getState_Province(),
                address.getCountry(),
                address.getPlanet(),
                address.getGalaxy(),
                address.getLongitude(),
                address.getLatitude(),
                address.getAddressId());
    }

    @Override
    public Address getAddressById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS,
                    new AddressMapper(),
                    id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Address> getAllAddresses() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ADDRESSES,
                new AddressMapper());
    }

//    private static final class AddressMapper2 implements RowMapper<Address> {
//
//        @Override
//        public Address mapRow(ResultSet rs, int i) throws SQLException {
//
//            Address ad = new Address();
//            ad.setStreetNumber(rs.getString("StreetNumber"));
//            ad.setStreetName(rs.getString("StreetName"));
//            ad.setCity(rs.getString("City"));
//            ad.setState_Province(rs.getString("State_Province"));
//            ad.setCountry(rs.getString("country"));
//            ad.setPlanet(rs.getString("planet"));
//            ad.setGalaxy(rs.getString("galaxy"));
//            ad.setLongitude(rs.getString("latitude"));
//            ad.setLatitude(rs.getString("longitude"));
//            ad.setAddressId(rs.getInt("addressid"));
//
//            Location lc = new Location();
//            lc.setLocationName(rs.getString("locationname"));
//            lc.setDescription(rs.getString("description"));
//            lc.setLocation(rs.getInt("locationid"));
//            lc.setAddress(ad); ///IMPORTANT IMPORTANT IMPORTANT IMPORTANT
//
//            Hero hero = new Hero();
//            hero.setAlias(rs.getString("Alias"));
//            hero.setFirstName(rs.getString("firstname"));
//            hero.setLastName(rs.getString("lastname"));
//            hero.setDescription(rs.getString("description"));
//            hero.setHeroId(rs.getInt("HeroId"));
//
//            Power power = new Power();
//            power.setDescription(rs.getString("description"));
//            power.setPowerId(rs.getInt("powersid"));
//
//            HeroPower hp = new HeroPower();
//            hero.setHeroId(rs.getInt("HeroId"));
//            power.setPowerId(rs.getInt("powersid"));
//            hp.setHero(hero);     //sets the location and hero inside sighting dto object
//            hp.setPower(power);
//
//            Sighting si = new Sighting();
//            si.setSightingDate(rs.getString("sightingdate"));
//            si.setDescription(rs.getString("description"));
//            si.setSightingId(rs.getInt("sightingid"));
//            hero.setHeroId(rs.getInt("HeroId"));
//            lc.setLocation(rs.getInt("locationid"));
//            si.setLocation(lc);     //sets the location and hero inside sighting dto object
//            si.setHero(hero);
//
//            Organization org = new Organization();
//            org.setOrganizationName(rs.getString("OrganizationName"));
//            org.setDescription(rs.getString("description"));
//            org.setOrganizationId(rs.getInt("OrganizationId"));
//            lc.setLocation(rs.getInt("locationid"));
//            org.setLocation(lc); //sets the location inside the organization dto object
//
//            Member mb = new Member();
//            mb.setMemberId(rs.getInt("memberid"));
//            mb.setStartDate(rs.getString("StartDate"));
//            mb.setEndDate(rs.getString("EndDate"));
//            hero.setHeroId(rs.getInt("heroid"));
//            org.setOrganizationId(rs.getInt("organizationid"));
//            mb.setHero(hero);
//            mb.setOrganization(org);
//
//            return ad;
//        }
//    }
     
    private static final class AddressMapper implements RowMapper<Address> {

        @Override
        public Address mapRow(ResultSet rs, int i) throws SQLException {

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
            
            return ad;
        }
        
    }

}
