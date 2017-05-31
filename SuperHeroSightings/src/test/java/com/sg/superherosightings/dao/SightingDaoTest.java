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
public class SightingDaoTest {

    SightingDao siDao;
    HeroDao heroDao;
    PowerDao powerDao;
    LocationDao loDao;
    AddressDao adDao;
    OrganizationDao orgDao;
    MemberDao memDao;
    HeroPowerDao hpDao;

    public SightingDaoTest() {
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
    public void addGetSighting() {
        Address ad = new Address();
        ad.setStreetNumber("401");
        ad.setStreetName("S Main St");
        ad.setCity("Akron");
        ad.setState_Province("Ohio");
        ad.setCountry("USA");
        ad.setPlanet("Earth");
        ad.setGalaxy("Milky Way");
        ad.setLongitude("41.074879");
        ad.setLatitude("81.522841");
        adDao.addAddress(ad);       //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);
        loDao.addLocation(lo);      //add location

        Hero hero = new Hero();
        hero.setAlias("alias");
        hero.setFirstName("firstName");
        hero.setLastName("lastName");
        hero.setDescription("description");
        heroDao.addHero(hero);       //add hero

        Sighting si = new Sighting();
        si.setSightingDate("03/19/1987");
        si.setDescription("description");
        si.setLocation(lo);
        si.setHero(hero);
        siDao.addSighting(si);      //add sighting

        Sighting fromDao = siDao.getSightingById(si.getSightingId());

        assertEquals(fromDao.getSightingDate(), "03/19/1987");
        assertEquals(fromDao.getDescription(), "description");

        //compare dao sighting with object values
    }

    @Test
    public void deleteSighting() {
        Address ad = new Address();
        ad.setStreetNumber("401");
        ad.setStreetName("S Main St");
        ad.setCity("Akron");
        ad.setState_Province("Ohio");
        ad.setCountry("USA");
        ad.setPlanet("Earth");
        ad.setGalaxy("Milky Way");
        ad.setLongitude("41.074879");
        ad.setLatitude("81.522841");
        adDao.addAddress(ad);       //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);
        loDao.addLocation(lo);      //add location

        Hero hero = new Hero();
        hero.setAlias("alias");
        hero.setFirstName("firstName");
        hero.setLastName("lastName");
        hero.setDescription("description");
        heroDao.addHero(hero);      //add hero

        Sighting si = new Sighting();
        si.setSightingDate("03/19/1987");
        si.setDescription("description");
        si.setLocation(lo);
        si.setHero(hero);
        siDao.addSighting(si);      //add sighting

        siDao.deleteSighting(si.getSightingId()); //delete location object
        assertNull(siDao.getSightingById(si.getSightingId())); //see if sighting in mememory is null
    }

    @Test
    public void addUpdateSighting() {
        Address ad = new Address();
        ad.setStreetNumber("401");
        ad.setStreetName("S Main St");
        ad.setCity("Akron");
        ad.setState_Province("Ohio");
        ad.setCountry("USA");
        ad.setPlanet("Earth");
        ad.setGalaxy("Milky Way");
        ad.setLongitude("41.074879");
        ad.setLatitude("81.522841");
        adDao.addAddress(ad);       //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);
        loDao.addLocation(lo);      //add location

        Hero hero = new Hero();
        hero.setAlias("alias");
        hero.setFirstName("firstName");
        hero.setLastName("lastName");
        hero.setDescription("description");
        heroDao.addHero(hero);      //add hero

        Sighting si = new Sighting();
        si.setSightingDate("03/19/1987");
        si.setDescription("description");
        si.setLocation(lo);
        si.setHero(hero);
        siDao.addSighting(si);      //add sighting

        si.setSightingDate("04/14/2017");
        siDao.updateSighting(si);  //update

        Sighting fromDao = siDao.getSightingById(si.getSightingId());    //get

        assertEquals(fromDao.getSightingDate(), "04/14/2017"); //
    }

    @Test
    public void getAllSightings() {

        Address ad = new Address();
        ad.setStreetNumber("401");
        ad.setStreetName("S Main St");
        ad.setCity("Akron");
        ad.setState_Province("Ohio");
        ad.setCountry("USA");
        ad.setPlanet("Earth");
        ad.setGalaxy("Milky Way");
        ad.setLongitude("41.074879");
        ad.setLatitude("81.522841");
        adDao.addAddress(ad);       //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);
        loDao.addLocation(lo);      //add location

        Hero hero = new Hero();
        hero.setAlias("alias");
        hero.setFirstName("firstName");
        hero.setLastName("lastName");
        hero.setDescription("description");
        heroDao.addHero(hero);      //add hero

        Sighting si = new Sighting();
        si.setSightingDate("03/19/1987");
        si.setDescription("description");
        si.setLocation(lo);
        si.setHero(hero);
        siDao.addSighting(si);      //add sighting

        Address ad2 = new Address();
        ad2.setStreetNumber("201");
        ad2.setStreetName("S Gain St");
        ad2.setCity("Philly");
        ad2.setState_Province("PA");
        ad2.setCountry("USA");
        ad2.setPlanet("Earth");
        ad2.setGalaxy("Milky Way");
        ad2.setLongitude("65.074879");
        ad2.setLatitude("71.522841");
        adDao.addAddress(ad2);       //add address2

        Location lo2 = new Location();
        lo2.setLocationName("locationName2");
        lo2.setDescription("description2");
        lo2.setAddress(ad2);
        loDao.addLocation(lo2);      //add location2

        Hero hero2 = new Hero();
        hero2.setAlias("alias2");
        hero2.setFirstName("firstName2");
        hero2.setLastName("lastName2");
        hero2.setDescription("description2");
        heroDao.addHero(hero2);      //add hero2

        Sighting si2 = new Sighting();
        si2.setSightingDate("03/19/2007");
        si2.setDescription("description2");
        si2.setLocation(lo2);
        si2.setHero(hero);
        siDao.addSighting(si2);      //add sighting2

        List<Sighting> sightings = siDao.getAllSightings();

        assertEquals(sightings.size(), 2);
    }

}
