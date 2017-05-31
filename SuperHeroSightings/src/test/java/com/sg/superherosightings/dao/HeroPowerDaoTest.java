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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author yingy
 */
public class HeroPowerDaoTest {

    SightingDao siDao;
    HeroDao heroDao;
    PowerDao powerDao;
    LocationDao loDao;
    AddressDao adDao;
    OrganizationDao orgDao;
    MemberDao memDao;
    HeroPowerDao hpDao;

    public HeroPowerDaoTest() {
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
    public void addGetHeroPowerByPowerIdandByHeroId() {
        Hero h = new Hero();
        h.setAlias("Feng The Wisiest of Them All");
        h.setFirstName("Feng");
        h.setLastName("Mao");
        h.setDescription("Class 5 Mutant, kills those who are smarter than him, beware his feng attack!");
        heroDao.addHero(h);       //add hero

        Power p = new Power();
        p.setDescription("Feng Attack, Physic Chop, Mind Blast, Mao Dung Ho");
        powerDao.addPower(p);       //add power

        HeroPower hp = new HeroPower();
        hp.setHero(h);
        hp.setPower(p);
        hpDao.addHeroPower(hp);

        List<HeroPower> fromDao = hpDao.getPowersByHeroId(h.getHeroId());
        assertEquals(fromDao.size(), 1);

        List<HeroPower> fromDao2 = hpDao.getHeroesByPowerId(p.getPowerId());
        assertEquals(fromDao2.size(), 1);
    }

    @Test
    public void deleteHeroPower() {

        Hero h = new Hero();
        h.setAlias("Feng The Wisiest of Them All");
        h.setFirstName("Feng");
        h.setLastName("ChiDung");
        h.setDescription("Class 5 Mutant, kills those who are smarter than him, beware his feng attack!");
        heroDao.addHero(h);       //add hero

        Power p = new Power();
        p.setDescription("Feng Attack, Physic Chop, Mind Blast, Mao Dung Ho");
        powerDao.addPower(p);       //add power

        HeroPower hp = new HeroPower();
        hp.setHero(h);
        hp.setPower(p);
        hpDao.addHeroPower(hp);

//        Hero fromDao = heroDao.getHeroById(h.getHeroId());
//        Power fromDao2 = powerDao.getPowerById(p.getPowerId());
        hpDao.deleteHeroPower(h.getHeroId(), p.getPowerId());
        List<HeroPower> heroPowerList = hpDao.getPowersByHeroId(h.getHeroId());
        assertEquals(0, heroPowerList.size());
    }

    @Test
    public void getAllHeroAndPower() {

        Hero h = new Hero();
        h.setAlias("Feng The Wisiest of Them All");
        h.setFirstName("Feng");
        h.setLastName("Mao");
        h.setDescription("Class 5 Mutant, kills those who are smarter than him, beware his feng attack!");
        heroDao.addHero(h);       //add hero

        Power p = new Power();
        p.setDescription("Feng Attack, Physic Chop, Mind Blast, Mao Dung Ho");
        powerDao.addPower(p);       //add power

        HeroPower hp = new HeroPower();
        hp.setHero(h);
        hp.setPower(p);
        hpDao.addHeroPower(hp);

        Hero h2 = new Hero();
        h2.setAlias("Chrisopher");
        h2.setFirstName("Chris");
        h2.setLastName("Andrews");
        h2.setDescription("Class 5 Mutant, very smart and powerful, but evil he is Feng ChiDung's rival");
        heroDao.addHero(h2);       //add hero2

        Power p2 = new Power();
        p2.setDescription("Beer drown, Rain of Knives, unemployment attack, physic");
        powerDao.addPower(p2);       //add power2

        HeroPower hp2 = new HeroPower();
        hp2.setHero(h2);
        hp2.setPower(p2);
        hpDao.addHeroPower(hp2);

        List<HeroPower> fromDaoHp = hpDao.getAllHeroAndPowers();

        assertEquals(fromDaoHp.size(), 2);
    }

}
