/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
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
public class OrganizationDaoJdbcTemplateImpl implements OrganizationDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_ORGANIZATION
            = "insert into organization (OrganizationName, description, locationid) "
            + "values (?, ?, ?)";

    private static final String SQL_DELETE_ORGANIZATION
            = "delete from organization where organizationid = ?";

    private static final String SQL_UPDATE_ORGANIZATION
            = "update organization set OrganizationName = ?, description = ?, locationid = ? "
            + "where organizationid = ?";

    private static final String SQL_SELECT_ORGANIZATION
            = "select * "
            + "from organization o "
            + "inner join location l on o.locationid = l.locationid where organizationid = ?";

    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "select * "
            + "from organization o "
            + "inner join location l on o.locationid = l.locationid ";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganization(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getDescription(),
                organization.getLocation().getLocation());

        int organizationID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        organization.setOrganizationId(organizationID);
    }

    @Override
    public void deleteOrganization(int organizationId) {
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, organizationId);
    }

    @Override
    public void updateOrganization(Organization organization) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getDescription(),
                organization.getLocation().getLocation(),
                organization.getOrganizationId());
    }

    @Override
    public Organization getOrganizationById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION,
                    new OrganizationDaoJdbcTemplateImpl.OrganizationMapper(),
                    id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS,
                new OrganizationDaoJdbcTemplateImpl.OrganizationMapper());
    }

    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
           
            Location lc = new Location();
            lc.setLocationName(rs.getString("locationname"));
            lc.setDescription(rs.getString("description"));
            lc.setLocation(rs.getInt("locationid"));
            
             ///IMPORTANT IMPORTANT IMPORTANT IMPORTANT


            Organization org = new Organization();
            org.setOrganizationName(rs.getString("OrganizationName"));
            org.setDescription(rs.getString("description"));
            org.setOrganizationId(rs.getInt("OrganizationId"));
            lc.setLocation(rs.getInt("locationid"));
            org.setLocation(lc); //sets the location inside the organization dto object

            return org;
        }
    }
}
