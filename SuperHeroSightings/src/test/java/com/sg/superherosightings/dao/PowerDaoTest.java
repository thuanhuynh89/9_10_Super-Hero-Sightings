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
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author yingy
 */
public class PowerDaoTest {

    SightingDao siDao;
    HeroDao heroDao;
    PowerDao powerDao;
    LocationDao loDao;
    AddressDao adDao;
    OrganizationDao orgDao;
    MemberDao memDao;
    HeroPowerDao hpDao;

    public PowerDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        siDao = ctx.getBean("sightingDao", SightingDao.class);
        heroDao = ctx.getBean("heroDao", HeroDao.class);
        powerDao = ctx.getBean("powerDao", PowerDao.class);
        loDao = ctx.getBean("locationDao", LocationDao.class);
        adDao = ctx.getBean("addressDao", AddressDao.class);
        orgDao = ctx.getBean("organizationDao", OrganizationDao.class);
        memDao = ctx.getBean("memberDao", MemberDao.class);
        hpDao = ctx.getBean("heroPowerDao", HeroPowerDao.class);

        List<Sighting> sighting = siDao.getAllSightings();
        for (Sighting currentSighting : sighting) {
            siDao.deleteSighting(currentSighting.getSightingId());
        }

        List<HeroPower> hp = hpDao.getAllHeroAndPowers();
        for (HeroPower currentHP : hp) {
            hpDao.deleteHeroPower(currentHP.getHero().getHeroId(), currentHP.getPower().getPowerId());
        }

        List<Member> mem = memDao.getAllMembers();
        for (Member currentMember : mem) {
            memDao.deleteMember(currentMember.getMemberId());
        }

        List<Hero> heroes = heroDao.getAllHeroes();
        for (Hero currentHero : heroes) {
            heroDao.deleteHero(currentHero.getHeroId());
        }

        List<Power> power = powerDao.getAllPowers();
        for (Power currentPower : power) {
            powerDao.deletePower(currentPower.getPowerId());
        }

        List<Organization> org = orgDao.getAllOrganizations();
        for (Organization currentOrg : org) {
            orgDao.deleteOrganization(currentOrg.getOrganizationId());
        }

        List<Location> locations = loDao.getAllLocations();
        for (Location currentLocation : locations) {
            loDao.deleteLocation(currentLocation.getLocation());
        }

        List<Address> address = adDao.getAllAddresses();
        for (Address currentAddress : address) {
            adDao.deleteAddress(currentAddress.getAddressId());
        }

    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetPower() {
        Power p = new Power();
        p.setDescription("Feng Attack, Physic Chop, Mind Blast, Mao Dung Ho");
        powerDao.addPower(p);       //add power

        Power fromDao = powerDao.getPowerById(p.getPowerId());
        assertEquals(fromDao.getDescription(), "Feng Attack, Physic Chop, Mind Blast, Mao Dung Ho");

        //compare dao sighting with object values
    }

    @Test
    public void deletePower() {

        Power p = new Power();
        p.setDescription("Feng The Wisiest of Them All");
        powerDao.addPower(p);       //add power

        powerDao.deletePower(p.getPowerId()); //delete location object
        assertNull(powerDao.getPowerById(p.getPowerId())); //see if sighting in mememory is null
    }

    @Test
    public void addUpdatePower() {

        Power p = new Power();
        p.setDescription("Feng Attack, Physic Chop, Mind Blast, Mao Dung Ho");
        powerDao.addPower(p);       //add power

        p.setDescription("Facepalm, Honey Drown, Bullet Seed, Shrinkage");
        powerDao.updatePower(p);  //update

        Power fromDao = powerDao.getPowerById(p.getPowerId());
        assertEquals(fromDao.getDescription(), "Facepalm, Honey Drown, Bullet Seed, Shrinkage");
    }

    @Test
    public void getAllPowers() {

        Power p = new Power();
        p.setDescription("Feng Attack, Physic Chop, Mind Blast, Mao Dung Ho");
        powerDao.addPower(p);       //add power

        Power p2 = new Power();
        p2.setDescription("Beer drown, Rain of Knives, unemployment attack, physic");
        powerDao.addPower(p2);       //add power2

        List<Power> powers = powerDao.getAllPowers();

        assertEquals(powers.size(), 2);
    }
}
