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
public class OrganizationDaoTest {

    SightingDao siDao;
    HeroDao heroDao;
    PowerDao powerDao;
    LocationDao loDao;
    AddressDao adDao;
    OrganizationDao orgDao;
    MemberDao memDao;
    HeroPowerDao hpDao;

    public OrganizationDaoTest() {
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
    public void addGetOrganization() {

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

        Organization org = new Organization();
        org.setOrganizationName("Victory Heroes");
        org.setDescription("description");
        org.setLocation(lo);
        orgDao.addOrganization(org);      //add organization

        Organization fromDao = orgDao.getOrganizationById(org.getOrganizationId());

        assertEquals(fromDao.getLocation().getLocationName(), "locationName");
        assertEquals(fromDao.getLocation().getDescription(), "description");

        assertEquals(fromDao.getOrganizationName(), "Victory Heroes");
        assertEquals(fromDao.getDescription(), "description");
        //compare dao sighting with object values
    }

    @Test
    public void deleteOrganization() {

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

        Organization org = new Organization();
        org.setOrganizationName("Victory Heroes");
        org.setDescription("description");
        org.setLocation(lo);
        orgDao.addOrganization(org);      //add organization

        orgDao.deleteOrganization(org.getOrganizationId()); //delete location object
        assertNull(orgDao.getOrganizationById(org.getOrganizationId())); //see if sighting in mememory is null
    }

    @Test
    public void addUpdateOrganization() {
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

        Organization org = new Organization();
        org.setOrganizationName("Victory Heroes");
        org.setDescription("description");
        org.setLocation(lo);
        orgDao.addOrganization(org);      //add organization

        org.setOrganizationName("Bad Guy Corp");
        orgDao.updateOrganization(org);  //update

        Organization fromDao = orgDao.getOrganizationById(org.getOrganizationId());    //get

        assertEquals(fromDao.getOrganizationName(), "Bad Guy Corp"); //
    }

    @Test
    public void getAllOrganizations() {

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

        Organization org = new Organization();
        org.setOrganizationName("Victory Heroes");
        org.setDescription("description");
        org.setLocation(lo);
        orgDao.addOrganization(org);      //add organization

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

        Organization org2 = new Organization();
        org2.setOrganizationName("Bad Heroes");
        org2.setDescription("description2");
        org2.setLocation(lo2);
        orgDao.addOrganization(org2);      //add organization

        List<Organization> organizations = orgDao.getAllOrganizations();

        assertEquals(organizations.size(), 2);
    }

}
