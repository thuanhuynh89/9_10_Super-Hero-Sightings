/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

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
public class ServiceImplTest {
    
    Service service;
    
    public ServiceImplTest() {
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

        service = ctx.getBean("serviceLayer", Service.class);

        List<Sighting> sighting = service.getAllSightings();
        for (Sighting currentSighting : sighting) {
            service.deleteSighting(currentSighting.getSightingId());
        }

        List<HeroPower> hp = service.getAllHeroAndPowers();
        for (HeroPower currentHP : hp) {
            service.deleteHeroPower(currentHP.getHero().getHeroId(), currentHP.getPower().getPowerId());
        }

        List<Member> mem = service.getAllMembers();
        for (Member currentMember : mem) {
            service.deleteMember(currentMember.getMemberId());
        }

        List<Hero> heroes = service.getAllHeroes();
        for (Hero currentHero : heroes) {
            service.deleteHero(currentHero.getHeroId());
        }

        List<Power> power = service.getAllPowers();
        for (Power currentPower : power) {
            service.deletePower(currentPower.getPowerId());
        }

        List<Organization> org = service.getAllOrganizations();
        for (Organization currentOrg : org) {
            service.deleteOrganization(currentOrg.getOrganizationId());
        }

        List<Location> locations = service.getAllLocations();
        for (Location currentLocation : locations) {
            service.deleteLocation(currentLocation.getLocation());
        }

        List<Address> address = service.getAllAddresses();
        for (Address currentAddress : address) {
            service.deleteAddress(currentAddress.getAddressId());
        }
        
    }
    
    @After
    public void tearDown() {
    }
    
   ///ADDRESS*********************************************************************************************
   @Test
    public void addGetAddress() {
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

        service.addAddress(ad);     //add

        Address fromDao = service.getAddressById(ad.getAddressId());    //get
        assertEquals(fromDao.getStreetNumber(), "401");
        assertEquals(fromDao.getStreetName(), "S Main St");
        assertEquals(fromDao.getCity(), "Akron");
        assertEquals(fromDao.getState_Province(), "Ohio");
        assertEquals(fromDao.getCountry(), "USA");
        assertEquals(fromDao.getPlanet(), "Earth");
        assertEquals(fromDao.getGalaxy(), "Milky Way");
        assertEquals(fromDao.getLongitude(), "41.074879");
        assertEquals(fromDao.getLatitude(), "81.522841");
    }

    @Test
    public void deleteAddress() {
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

        service.addAddress(ad);     //add

        service.deleteAddress(ad.getAddressId());
        assertNull(service.getAddressById(ad.getAddressId()));
    }

    @Test
    public void addUpdateAddress() {
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
        service.addAddress(ad);     //add

        ad.setStreetNumber("201");
        service.updateAddress(ad);  //update

        Address fromDao = service.getAddressById(ad.getAddressId());    //get
        assertEquals(fromDao.getStreetNumber(), "201");
    }

    @Test
    public void getAllAddresses() {
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

        service.addAddress(ad);     //add first element

        Address ad2 = new Address();
        ad2.setStreetNumber("101");
        ad2.setStreetName("E Main St");
        ad2.setCity("Erie");
        ad2.setState_Province("PA");
        ad2.setCountry("USA");
        ad2.setPlanet("Hoho");
        ad2.setGalaxy("Phoenix");
        ad2.setLongitude("41.074879");
        ad2.setLatitude("81.522841");

        service.addAddress(ad2);    //add second element

        List<Address> addresses = service.getAllAddresses();

        assertEquals(addresses.size(), 2);
    }
    
     ///LOCATION*********************************************************************************************
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

        service.addAddress(ad);     //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);

        service.addLocation(lo);      //add location

        Location fromDao = service.getLocationById(lo.getLocation());
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

        service.addAddress(ad);     //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);

        service.addLocation(lo);      //add location

        service.deleteLocation(lo.getLocation()); //delete location object
        assertNull(service.getLocationById(lo.getLocation())); //see if location in mememory is null
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
        service.addAddress(ad);     //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);
        service.addLocation(lo);      //add location

//        ad.setCity("Erie");
//        adDao.updateAddress(ad);
        lo.setLocationName("Erie");
        service.updateLocation(lo);  //update

        Location fromDao = service.getLocationById(lo.getLocation());    //get

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
        service.addAddress(ad);     //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);
        service.addLocation(lo);      //add location

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
        service.addAddress(ad2);     //add address

        Location lo2 = new Location();
        lo2.setLocationName("locationName");
        lo2.setDescription("description");
        lo2.setAddress(ad2);
        service.addLocation(lo2);      //add location

        List<Location> locations = service.getAllLocations();

        assertEquals(locations.size(), 2);
    }
     ///HERO*********************************************************************************************
    @Test
    public void addGetHero() {
        Hero h = new Hero();
        h.setAlias("Feng The Wisiest of Them All");
        h.setFirstName("Feng");
        h.setLastName("Mao");
        h.setDescription("Class 5 Mutant, kills those who are smarter than him, beware his feng attack!");
        service.addHero(h);       //add hero

        Hero fromDao = service.getHeroById(h.getHeroId());
        assertEquals(fromDao.getAlias(), "Feng The Wisiest of Them All");
        assertEquals(fromDao.getFirstName(), "Feng");
        assertEquals(fromDao.getLastName(), "Mao");
        assertEquals(fromDao.getDescription(), "Class 5 Mutant, kills those who are smarter than him, beware his feng attack!");

        //compare dao sighting with object values
    }

    @Test
    public void deleteHero() {

        Hero h = new Hero();
        h.setAlias("Feng The Wisiest of Them All");
        h.setFirstName("Feng");
        h.setLastName("ChiDung");
        h.setDescription("Class 5 Mutant, kills those who are smarter than him, beware his feng attack!");
        service.addHero(h);       //add hero

        service.deleteHero(h.getHeroId()); //delete location object
        assertNull(service.getHeroById(h.getHeroId())); //see if sighting in mememory is null
    }

    @Test
    public void addUpdateHero() {

        Hero h = new Hero();
        h.setAlias("Feng The Wisiest of Them All");
        h.setFirstName("Feng");
        h.setLastName("Mao");
        h.setDescription("Class 5 Mutant, kills those who are smarter than him, beware his feng attack!");
        service.addHero(h);       //add hero

        h.setLastName("AsianXavier");
        service.updateHero(h);  //update

        Hero fromDao = service.getHeroById(h.getHeroId());    //get

        assertEquals(fromDao.getLastName(), "AsianXavier"); //
    }

    @Test
    public void getAllHeroes() {

        Hero h = new Hero();
        h.setAlias("Feng The Wisiest of Them All");
        h.setFirstName("Feng");
        h.setLastName("Mao");
        h.setDescription("Class 5 Mutant, kills those who are smarter than him, beware his feng attack!");
        service.addHero(h);       //add hero

        Hero h2 = new Hero();
        h2.setAlias("Chrisopher");
        h2.setFirstName("Chris");
        h2.setLastName("Andrews");
        h2.setDescription("Class 5 Mutant, very smart and powerful, but evil he is Feng ChiDung's rival");
        service.addHero(h2);       //add hero2

        List<Hero> heroes = service.getAllHeroes();

        assertEquals(heroes.size(), 2);
    }
     ///POWERS*********************************************************************************************
    @Test
    public void addGetPower() {
        Power p = new Power();
        p.setDescription("Feng Attack, Physic Chop, Mind Blast, Mao Dung Ho");
        service.addPower(p);       //add power

        Power fromDao = service.getPowerById(p.getPowerId());
        assertEquals(fromDao.getDescription(), "Feng Attack, Physic Chop, Mind Blast, Mao Dung Ho");

        //compare dao sighting with object values
    }

    @Test
    public void deletePower() {

        Power p = new Power();
        p.setDescription("Feng The Wisiest of Them All");
        service.addPower(p);       //add power

        service.deletePower(p.getPowerId()); //delete location object
        assertNull(service.getPowerById(p.getPowerId())); //see if sighting in mememory is null
    }

    @Test
    public void addUpdatePower() {

        Power p = new Power();
        p.setDescription("Feng Attack, Physic Chop, Mind Blast, Mao Dung Ho");
        service.addPower(p);       //add power

        p.setDescription("Facepalm, Honey Drown, Bullet Seed, Shrinkage");
        service.updatePower(p);  //update

        Power fromDao = service.getPowerById(p.getPowerId());
        assertEquals(fromDao.getDescription(), "Facepalm, Honey Drown, Bullet Seed, Shrinkage");
    }

    @Test
    public void getAllPowers() {

        Power p = new Power();
        p.setDescription("Feng Attack, Physic Chop, Mind Blast, Mao Dung Ho");
        service.addPower(p);       //add power

        Power p2 = new Power();
        p2.setDescription("Beer drown, Rain of Knives, unemployment attack, physic");
        service.addPower(p2);       //add power2

        List<Power> powers = service.getAllPowers();

        assertEquals(powers.size(), 2);
    }
     ///HEROPOWER*********************************************************************************************
    @Test
    public void addGetHeroPowerByPowerIdandByHeroId() {
        Hero h = new Hero();
        h.setAlias("Feng The Wisiest of Them All");
        h.setFirstName("Feng");
        h.setLastName("Mao");
        h.setDescription("Class 5 Mutant, kills those who are smarter than him, beware his feng attack!");
        service.addHero(h);       //add hero

        Power p = new Power();
        p.setDescription("Feng Attack, Physic Chop, Mind Blast, Mao Dung Ho");
        service.addPower(p);       //add power

        HeroPower hp = new HeroPower();
        hp.setHero(h);
        hp.setPower(p);
        service.addHeroPower(hp);

        List<HeroPower> fromDao = service.getPowersByHeroId(h.getHeroId());
        assertEquals(fromDao.size(), 1);

        List<HeroPower> fromDao2 = service.getHeroesByPowerId(p.getPowerId());
        assertEquals(fromDao2.size(), 1);
    }

    @Test
    public void deleteHeroPower() {

        Hero h = new Hero();
        h.setAlias("Feng The Wisiest of Them All");
        h.setFirstName("Feng");
        h.setLastName("ChiDung");
        h.setDescription("Class 5 Mutant, kills those who are smarter than him, beware his feng attack!");
        service.addHero(h);       //add hero

        Power p = new Power();
        p.setDescription("Feng Attack, Physic Chop, Mind Blast, Mao Dung Ho");
        service.addPower(p);       //add power

        HeroPower hp = new HeroPower();
        hp.setHero(h);
        hp.setPower(p);
        service.addHeroPower(hp);

//        Hero fromDao = heroDao.getHeroById(h.getHeroId());
//        Power fromDao2 = powerDao.getPowerById(p.getPowerId());
        service.deleteHeroPower(h.getHeroId(), p.getPowerId());
        List<HeroPower> heroPowerList = service.getPowersByHeroId(h.getHeroId());
        assertEquals(0, heroPowerList.size());
    }

    @Test
    public void getAllHeroAndPower() {

        Hero h = new Hero();
        h.setAlias("Feng The Wisiest of Them All");
        h.setFirstName("Feng");
        h.setLastName("Mao");
        h.setDescription("Class 5 Mutant, kills those who are smarter than him, beware his feng attack!");
        service.addHero(h);       //add hero

        Power p = new Power();
        p.setDescription("Feng Attack, Physic Chop, Mind Blast, Mao Dung Ho");
        service.addPower(p);       //add power

        HeroPower hp = new HeroPower();
        hp.setHero(h);
        hp.setPower(p);
        service.addHeroPower(hp);

        Hero h2 = new Hero();
        h2.setAlias("Chrisopher");
        h2.setFirstName("Chris");
        h2.setLastName("Andrews");
        h2.setDescription("Class 5 Mutant, very smart and powerful, but evil he is Feng ChiDung's rival");
        service.addHero(h2);       //add hero2

        Power p2 = new Power();
        p2.setDescription("Beer drown, Rain of Knives, unemployment attack, physic");
        service.addPower(p2);       //add power2

        HeroPower hp2 = new HeroPower();
        hp2.setHero(h2);
        hp2.setPower(p2);
        service.addHeroPower(hp2);

        List<HeroPower> fromDaoHp = service.getAllHeroAndPowers();

        assertEquals(fromDaoHp.size(), 2);
    }

     ///SIGHTING*********************************************************************************************
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
        service.addAddress(ad);       //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);
        service.addLocation(lo);      //add location

        Hero hero = new Hero();
        hero.setAlias("alias");
        hero.setFirstName("firstName");
        hero.setLastName("lastName");
        hero.setDescription("description");
        service.addHero(hero);       //add hero

        Sighting si = new Sighting();
        si.setSightingDate("03/19/1987");
        si.setDescription("description");
        si.setLocation(lo);
        si.setHero(hero);
        service.addSighting(si);      //add sighting

        Sighting fromDao = service.getSightingById(si.getSightingId());

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
        service.addAddress(ad);       //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);
        service.addLocation(lo);      //add location

        Hero hero = new Hero();
        hero.setAlias("alias");
        hero.setFirstName("firstName");
        hero.setLastName("lastName");
        hero.setDescription("description");
        service.addHero(hero);      //add hero

        Sighting si = new Sighting();
        si.setSightingDate("03/19/1987");
        si.setDescription("description");
        si.setLocation(lo);
        si.setHero(hero);
        service.addSighting(si);      //add sighting

        service.deleteSighting(si.getSightingId()); //delete location object
        assertNull(service.getSightingById(si.getSightingId())); //see if sighting in mememory is null
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
        service.addAddress(ad);       //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);
        service.addLocation(lo);      //add location

        Hero hero = new Hero();
        hero.setAlias("alias");
        hero.setFirstName("firstName");
        hero.setLastName("lastName");
        hero.setDescription("description");
        service.addHero(hero);      //add hero

        Sighting si = new Sighting();
        si.setSightingDate("03/19/1987");
        si.setDescription("description");
        si.setLocation(lo);
        si.setHero(hero);
        service.addSighting(si);      //add sighting

        si.setSightingDate("04/14/2017");
        service.updateSighting(si);  //update

        Sighting fromDao = service.getSightingById(si.getSightingId());    //get

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
        service.addAddress(ad);       //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);
        service.addLocation(lo);      //add location

        Hero hero = new Hero();
        hero.setAlias("alias");
        hero.setFirstName("firstName");
        hero.setLastName("lastName");
        hero.setDescription("description");
        service.addHero(hero);      //add hero

        Sighting si = new Sighting();
        si.setSightingDate("03/19/1987");
        si.setDescription("description");
        si.setLocation(lo);
        si.setHero(hero);
        service.addSighting(si);      //add sighting

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
        service.addAddress(ad2);       //add address2

        Location lo2 = new Location();
        lo2.setLocationName("locationName2");
        lo2.setDescription("description2");
        lo2.setAddress(ad2);
        service.addLocation(lo2);      //add location2

        Hero hero2 = new Hero();
        hero2.setAlias("alias2");
        hero2.setFirstName("firstName2");
        hero2.setLastName("lastName2");
        hero2.setDescription("description2");
        service.addHero(hero2);      //add hero2

        Sighting si2 = new Sighting();
        si2.setSightingDate("03/19/2007");
        si2.setDescription("description2");
        si2.setLocation(lo2);
        si2.setHero(hero);
        service.addSighting(si2);      //add sighting2

        List<Sighting> sightings = service.getAllSightings();

        assertEquals(sightings.size(), 2);
    }
    ///ORGANIZATION*********************************************************************************************
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
        service.addAddress(ad);       //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);
        service.addLocation(lo);      //add location

        Organization org = new Organization();
        org.setOrganizationName("Victory Heroes");
        org.setDescription("description");
        org.setLocation(lo);
        service.addOrganization(org);      //add organization

        Organization fromDao = service.getOrganizationById(org.getOrganizationId());

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
        service.addAddress(ad);       //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);
        service.addLocation(lo);      //add location

        Organization org = new Organization();
        org.setOrganizationName("Victory Heroes");
        org.setDescription("description");
        org.setLocation(lo);
        service.addOrganization(org);      //add organization

        service.deleteOrganization(org.getOrganizationId()); //delete location object
        assertNull(service.getOrganizationById(org.getOrganizationId())); //see if sighting in mememory is null
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
        service.addAddress(ad);       //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);
        service.addLocation(lo);      //add location

        Organization org = new Organization();
        org.setOrganizationName("Victory Heroes");
        org.setDescription("description");
        org.setLocation(lo);
        service.addOrganization(org);      //add organization

        org.setOrganizationName("Bad Guy Corp");
        service.updateOrganization(org);  //update

        Organization fromDao = service.getOrganizationById(org.getOrganizationId());    //get

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
        service.addAddress(ad);       //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);
        service.addLocation(lo);      //add location

        Organization org = new Organization();
        org.setOrganizationName("Victory Heroes");
        org.setDescription("description");
        org.setLocation(lo);
        service.addOrganization(org);      //add organization

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
        service.addAddress(ad2);       //add address2

        Location lo2 = new Location();
        lo2.setLocationName("locationName2");
        lo2.setDescription("description2");
        lo2.setAddress(ad2);
        service.addLocation(lo2);      //add location2

        Organization org2 = new Organization();
        org2.setOrganizationName("Bad Heroes");
        org2.setDescription("description2");
        org2.setLocation(lo2);
        service.addOrganization(org2);      //add organization

        List<Organization> organizations = service.getAllOrganizations();

        assertEquals(organizations.size(), 2);
    }

     ///MEMBER*********************************************************************************************
    
    @Test
    public void addGetMember() {
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
        service.addAddress(ad);       //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);
        service.addLocation(lo);      //add location

        Hero hero = new Hero();
        hero.setAlias("alias");
        hero.setFirstName("firstName");
        hero.setLastName("lastName");
        hero.setDescription("description");
        service.addHero(hero);       //add hero

        Sighting si = new Sighting();
        si.setSightingDate("03/19/1987");
        si.setDescription("description");
        si.setLocation(lo);
        si.setHero(hero);
        service.addSighting(si);      //add sighting

        Organization org = new Organization();
        org.setOrganizationName("Victory Heroes");
        org.setDescription("description");
        org.setLocation(lo);
        service.addOrganization(org);      //add organization

        Member mem = new Member();
        mem.setStartDate("03/20/2000");
        mem.setEndDate("06/20/2000");
        mem.setHero(hero);
        mem.setOrganization(org);
        service.addMember(mem);
        
        Member fromDao = service.getMemberById(mem.getMemberId());

        assertEquals(fromDao.getStartDate(), "03/20/2000");
        assertEquals(fromDao.getEndDate(), "06/20/2000");
        //compare dao sighting with object values
    }

    @Test
    public void deleteMember() {

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
        service.addAddress(ad);       //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);
        service.addLocation(lo);      //add location

        Hero hero = new Hero();
        hero.setAlias("alias");
        hero.setFirstName("firstName");
        hero.setLastName("lastName");
        hero.setDescription("description");
        service.addHero(hero);       //add hero

        Organization org = new Organization();
        org.setOrganizationName("Victory Heroes");
        org.setDescription("description");
        org.setLocation(lo);
        service.addOrganization(org);      //add organization

        Member mem = new Member();
        mem.setStartDate("03/20/2000");
        mem.setEndDate("06/20/2000");
        mem.setHero(hero);
        mem.setOrganization(org);
        service.addMember(mem);

        service.deleteMember(mem.getMemberId()); //delete location object
        assertNull(service.getMemberById(mem.getMemberId())); //see if sighting in mememory is null
    }

    @Test
    public void addUpdateMember() {
        
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
        service.addAddress(ad);       //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);
        service.addLocation(lo);      //add location

        Hero hero = new Hero();
        hero.setAlias("alias");
        hero.setFirstName("firstName");
        hero.setLastName("lastName");
        hero.setDescription("description");
        service.addHero(hero);       //add hero

        Organization org = new Organization();
        org.setOrganizationName("Victory Heroes");
        org.setDescription("description");
        org.setLocation(lo);
        service.addOrganization(org);      //add organization

        Member mem = new Member();
        mem.setStartDate("03/20/2000");
        mem.setEndDate("06/20/2000");
        mem.setHero(hero);
        mem.setOrganization(org);
        service.addMember(mem);
        
        mem.setEndDate("08/20/2000");
        service.updateMember(mem);  //update
        
        Member fromDao = service.getMemberById(mem.getMemberId());    //get
        
        assertEquals(fromDao.getEndDate(), "08/20/2000"); //
    }
    
    
    @Test
    public void getAllMembers() {
        
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
        service.addAddress(ad);       //add address

        Location lo = new Location();
        lo.setLocationName("locationName");
        lo.setDescription("description");
        lo.setAddress(ad);
        service.addLocation(lo);      //add location

        Hero hero = new Hero();
        hero.setAlias("alias");
        hero.setFirstName("firstName");
        hero.setLastName("lastName");
        hero.setDescription("description");
        service.addHero(hero);      //add hero

        
        Organization org = new Organization();
        org.setOrganizationName("Victory Heroes");
        org.setDescription("description");
        org.setLocation(lo);
        service.addOrganization(org);      //add organization
        
        Member mem = new Member();
        mem.setStartDate("03/20/2000");
        mem.setEndDate("06/20/2000");
        mem.setHero(hero);
        mem.setOrganization(org);
        service.addMember(mem);
        
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
        service.addAddress(ad2);       //add address2

        Location lo2 = new Location();
        lo2.setLocationName("locationName2");
        lo2.setDescription("description2");
        lo2.setAddress(ad2);
        service.addLocation(lo2);      //add location2

        Hero hero2 = new Hero();
        hero2.setAlias("alias2");
        hero2.setFirstName("firstName2");
        hero2.setLastName("lastName2");
        hero2.setDescription("description2");
        service.addHero(hero2);      //add hero2
        
        Organization org2 = new Organization();
        org2.setOrganizationName("Bad Heroes");
        org2.setDescription("description2");
        org2.setLocation(lo2);
        service.addOrganization(org2);      //add organization
        
        Member mem2 = new Member();
        mem2.setStartDate("01/20/2000");
        mem2.setEndDate("10/20/2000");
        mem2.setHero(hero2);
        mem2.setOrganization(org2);
        service.addMember(mem2);
        
        List<Member> members = service.getAllMembers(); 

        assertEquals(members.size(), 2);
    }
    
}
