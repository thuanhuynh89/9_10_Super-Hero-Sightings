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
public class MemberDaoJdbcTemplateImpl implements MemberDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_MEMBER
            = "insert into member (StartDate, EndDate, heroid, organizationid) "
            + "values (?, ?, ?, ?)";

    private static final String SQL_DELETE_MEMBER
            = "delete from member where memberid = ?";

    private static final String SQL_UPDATE_MEMBER
            = "update member set StartDate = ?, EndDate = ?, heroid = ?, "
            + "organizationid = ? where memberid = ?";

    private static final String SQL_SELECT_MEMBER
            = "select * from member m "
            + "inner join organization o on o.organizationid = m.organizationid "
            + "inner join hero h on h.heroid = m.heroid "
            + "where memberid = ?";

    private static final String SQL_SELECT_ALL_MEMBERS
            = "select * from member m "
            + "inner join organization o on o.organizationid = m.organizationid "
            + "inner join hero h on h.heroid = m.heroid ";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addMember(Member member) {
        jdbcTemplate.update(SQL_INSERT_MEMBER,
                member.getStartDate(),
                member.getEndDate(),
                member.getHero().getHeroId(),
                member.getOrganization().getOrganizationId());

        int memberID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        member.setMemberId(memberID);
    }

    @Override
    public void deleteMember(int memberId) {
        jdbcTemplate.update(SQL_DELETE_MEMBER, memberId);
    }

    @Override
    public void updateMember(Member member) {
        jdbcTemplate.update(SQL_UPDATE_MEMBER,
                member.getStartDate(),
                member.getEndDate(),
                member.getHero().getHeroId(),
                member.getOrganization().getOrganizationId(),
                member.getMemberId());
    }

    @Override
    public Member getMemberById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_MEMBER,
                    new MemberDaoJdbcTemplateImpl.MemberMapper(),
                    id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Member> getAllMembers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_MEMBERS,
                new MemberDaoJdbcTemplateImpl.MemberMapper());
    }

    private static final class MemberMapper implements RowMapper<Member> {

        @Override
        public Member mapRow(ResultSet rs, int i) throws SQLException {
            
            Hero hero = new Hero();
            hero.setAlias(rs.getString("Alias"));
            hero.setFirstName(rs.getString("firstname"));
            hero.setLastName(rs.getString("lastname"));
            hero.setDescription(rs.getString("description"));
            hero.setHeroId(rs.getInt("HeroId"));

            Organization org = new Organization();
            org.setOrganizationName(rs.getString("OrganizationName"));
            org.setDescription(rs.getString("description"));
            org.setOrganizationId(rs.getInt("OrganizationId"));
            
            Member mb = new Member();
            mb.setMemberId(rs.getInt("memberid"));
            mb.setStartDate(rs.getString("StartDate"));
            mb.setEndDate(rs.getString("EndDate"));
            hero.setHeroId(rs.getInt("heroid"));
            org.setOrganizationId(rs.getInt("organizationid"));
            mb.setHero(hero);
            mb.setOrganization(org);
            
            return mb;
        }
    }
}
