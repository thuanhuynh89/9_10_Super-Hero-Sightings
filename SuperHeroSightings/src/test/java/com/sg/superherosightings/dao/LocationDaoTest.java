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
public class LocationDaoTest {

    SightingDao siDao;
    HeroDao heroDao;
    PowerDao powerDao;
    LocationDao loDao;
    AddressDao adDao;
    OrganizationDao orgDao;
    MemberDao memDao;
    HeroPowerDao hpDao;

    public LocationDaoTest() {
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
    public void addGetLocation() {
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

        adDao.addAddress(ad);     //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);

        loDao.addLocation(lo);      //add location

        Location fromDao = loDao.getLocationById(lo.getLocation());
        assertEquals(fromDao.getAddress().getStreetNumber(), "401");
        assertEquals(fromDao.getAddress().getStreetName(), "S Main St");
        assertEquals(fromDao.getAddress().getCity(), "Akron");
        assertEquals(fromDao.getAddress().getState_Province(), "Ohio");
        assertEquals(fromDao.getAddress().getCountry(), "USA");
        assertEquals(fromDao.getAddress().getPlanet(), "Earth");
        assertEquals(fromDao.getAddress().getGalaxy(), "Milky Way");
        assertEquals(fromDao.getAddress().getLongitude(), "41.074879");
        assertEquals(fromDao.getAddress().getLatitude(), "81.522841");

        assertEquals(fromDao.getLocationName(), "locationName");
        assertEquals(fromDao.getDescription(), "description");
    }

    @Test
    public void deleteLocation() {
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

        adDao.addAddress(ad);     //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);

        loDao.addLocation(lo);      //add location

        loDao.deleteLocation(lo.getLocation()); //delete location object
        assertNull(loDao.getLocationById(lo.getLocation())); //see if location in mememory is null
    }

    @Test
    public void addUpdateLocation() {
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
        adDao.addAddress(ad);     //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);
        loDao.addLocation(lo);      //add location

//        ad.setCity("Erie");
//        adDao.updateAddress(ad);
        lo.setLocationName("Erie");
        loDao.updateLocation(lo);  //update

        Location fromDao = loDao.getLocationById(lo.getLocation());    //get

        assertEquals(fromDao.getLocationName(), "Erie");
    }

    @Test
    public void getAllLocations() {
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
        adDao.addAddress(ad);     //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);
        loDao.addLocation(lo);      //add location

        Address ad2 = new Address();
        ad2.setStreetNumber("221");
        ad2.setStreetName("S Pain St");
        ad2.setCity("Gain");
        ad2.setState_Province("PA");
        ad2.setCountry("USA");
        ad2.setPlanet("Earth");
        ad2.setGalaxy("Milky Way");
        ad2.setLongitude("45.074879");
        ad2.setLatitude("51.522841");
        adDao.addAddress(ad2);     //add address

        Location lo2 = new Location();
        lo2.setLocationName("locationName");
        lo2.setDescription("description");
        lo2.setAddress(ad2);
        loDao.addLocation(lo2);      //add location

        List<Location> locations = loDao.getAllLocations();

        assertEquals(locations.size(), 2);
    }
}
