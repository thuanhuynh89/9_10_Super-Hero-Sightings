/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.HeroPower;
import com.sg.superherosightings.model.Power;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author yingy
 */
public class HeroPowerDaoJdbcTemplateImpl implements HeroPowerDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_HEROPOWER
            = "insert into HeroPower (heroid, powersid) "
            + "values (?, ?)";

    private static final String SQL_DELETE_HEROPOWER
            = "delete from HeroPower where heroid = ? and powersid = ?";

    

    private static final String SQL_SELECT_POWER_BY_HEROID
            = "select * from heropower hp "
            + "inner join hero h on hp.heroid =  h.heroid "
            + "inner join powers p on hp.powersid = p.powersid "
            + " where hp.heroid = ?";

    private static final String SQL_SELECT_HERO_BY_POWERID
            = "select * from heropower hp "
            + "inner join hero h on hp.heroid =  h.heroid "
            + "inner join powers p on hp.powersid = p.powersid "
            + " where hp.powersid = ?";

    private static final String SQL_SELECT_ALL_HEROPOWER
            = "select h.*, p.* from heropower hp "
            + "inner join hero h on hp.heroid =  h.heroid "
            + "inner join powers p on hp.powersid = p.powersid "
            + "order by HeroId asc";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addHeroPower(HeroPower heroPower) {
        jdbcTemplate.update(SQL_INSERT_HEROPOWER,
                heroPower.getHero().getHeroId(),
                heroPower.getPower().getPowerId());
    }

    @Override
    public void deleteHeroPower(int heroId, int powerId) {
        jdbcTemplate.update(SQL_DELETE_HEROPOWER, heroId, powerId);
    }

    @Override
    public List<HeroPower> getPowersByHeroId(int heroId) {
        return jdbcTemplate.query(SQL_SELECT_POWER_BY_HEROID,
                new HeroPowerDaoJdbcTemplateImpl.HeroPowerMapper(), heroId);
    }

    @Override
    public List<HeroPower> getHeroesByPowerId(int powerId) {
        return jdbcTemplate.query(SQL_SELECT_HERO_BY_POWERID,
                new HeroPowerDaoJdbcTemplateImpl.HeroPowerMapper(), powerId);
    }

    @Override
    public List<HeroPower> getAllHeroAndPowers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_HEROPOWER,
                new HeroPowerDaoJdbcTemplateImpl.HeroPowerMapper());
    }

    private static final class HeroPowerMapper implements RowMapper<HeroPower> {

        @Override
        public HeroPower mapRow(ResultSet rs, int i) throws SQLException {

            Hero hero = new Hero();
            hero.setAlias(rs.getString("h.Alias"));
            hero.setFirstName(rs.getString("h.firstname"));
            hero.setLastName(rs.getString("h.lastname"));
            hero.setDescription(rs.getString("h.description"));
            hero.setHeroId(rs.getInt("h.HeroId"));

            Power power = new Power();
            power.setDescription(rs.getString("p.description"));
            power.setPowerId(rs.getInt("p.powersid"));

            HeroPower hp = new HeroPower();
            hero.setHeroId(rs.getInt("h.HeroId"));
            power.setPowerId(rs.getInt("p.powersid"));
            hp.setHero(hero);     //sets the location and hero inside sighting dto object
            hp.setPower(power);

            return hp;
        }
    }

}
