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

/**
 *
 * @author yingy
 */
public interface Service {
    
    //ADDRESS *******************************************
    public void addAddress(Address address);

    public void deleteAddress(int addressId);

    public void updateAddress(Address address);
    
    public Address getAddressById(int id);

    public List<Address> getAllAddresses();
    
    //LOCATION ******************************************
    public void addLocation(Location location);

    public void deleteLocation(int locationId);

    public void updateLocation(Location location);

    public Location getLocationById(int id);

    public List<Location> getAllLocations();
    
    //HERO **********************************************
    public void addHero(Hero hero);

    public void deleteHero(int heroId);

    public void updateHero(Hero hero);

    public Hero getHeroById(int id);

    public List<Hero> getAllHeroes();
    
    //POWER **********************************************
    public void addPower(Power power);

    public void deletePower(int powerId);

    public void updatePower(Power power);

    public Power getPowerById(int id);

    public List<Power> getAllPowers();
    
    //HEROPOWER *******************************************
    public void addHeroPower(HeroPower heroPower);

    public void deleteHeroPower(int heroId, int powerId);

    public List<HeroPower> getHeroesByPowerId(int powerId);

    public List<HeroPower> getPowersByHeroId(int heroId);
    
    public List<HeroPower> getAllHeroAndPowers();
    
    //SIGHTING ********************************************
    public void addSighting(Sighting sighting);

    public void deleteSighting(int sightingId);

    public void updateSighting(Sighting sighting);

    public Sighting getSightingById(int id);

    public List<Sighting> getAllSightings();
    
    //ORGANIZATION *****************************************
    public void addOrganization(Organization organization);

    public void deleteOrganization(int organizationId);

    public void updateOrganization(Organization organization);

    public Organization getOrganizationById(int id);

    public List<Organization> getAllOrganizations();
    
    //MEMBER ***********************************************
    public void addMember(Member member);

    public void deleteMember(int memberId);

    public void updateMember(Member member);

    public Member getMemberById(int id);

    public List<Member> getAllMembers();
    
}
