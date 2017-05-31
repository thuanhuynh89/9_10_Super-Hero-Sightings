/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.AddressDao;
import com.sg.superherosightings.dao.HeroDao;
import com.sg.superherosightings.dao.HeroPowerDao;
import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.MemberDao;
import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dao.PowerDao;
import com.sg.superherosightings.dao.SightingDao;
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
public class ServiceImpl implements Service {

    SightingDao siDao;
    HeroDao heroDao;
    PowerDao powerDao;
    LocationDao loDao;
    AddressDao adDao;
    OrganizationDao orgDao;
    MemberDao memDao;
    HeroPowerDao hpDao;

    public ServiceImpl(SightingDao siDao, HeroDao heroDao, PowerDao powerDao, LocationDao loDao, AddressDao adDao, OrganizationDao orgDao, MemberDao memDao, HeroPowerDao hpDao) {
        this.siDao = siDao;
        this.heroDao = heroDao;
        this.powerDao = powerDao;
        this.loDao = loDao;
        this.adDao = adDao;
        this.orgDao = orgDao;
        this.memDao = memDao;
        this.hpDao = hpDao;
    }

    //ADDRESS *******************************************
    @Override
    public void addAddress(Address address) {
        adDao.addAddress(address);
    }

    @Override
    public void deleteAddress(int addressId) {
        adDao.deleteAddress(addressId);
    }

    @Override
    public void updateAddress(Address address) {
        adDao.updateAddress(address);
    }

    @Override
    public Address getAddressById(int id) {
        return adDao.getAddressById(id);
    }

    @Override
    public List<Address> getAllAddresses() {
        return adDao.getAllAddresses();
    }

    //LOCATION ******************************************
    public void addLocation(Location location) {
        loDao.addLocation(location);
    }

    public void deleteLocation(int locationId) {
        loDao.deleteLocation(locationId);
    }

    public void updateLocation(Location location) {
        loDao.updateLocation(location);
    }

    public Location getLocationById(int id) {
        return loDao.getLocationById(id);
    }

    public List<Location> getAllLocations() {
        return loDao.getAllLocations();
    }

    //HERO **********************************************
    public void addHero(Hero hero) {
        heroDao.addHero(hero);
    }

    public void deleteHero(int heroId) {
        heroDao.deleteHero(heroId);
    }

    public void updateHero(Hero hero) {
        heroDao.updateHero(hero);
    }

    public Hero getHeroById(int id) {
        return heroDao.getHeroById(id);
    }

    public List<Hero> getAllHeroes() {
        return heroDao.getAllHeroes();
    }

    //POWER **********************************************
    public void addPower(Power power) {
        powerDao.addPower(power);
    }

    public void deletePower(int powerId) {
        powerDao.deletePower(powerId);
    }

    public void updatePower(Power power) {
        powerDao.updatePower(power);
    }

    public Power getPowerById(int id) {
        return powerDao.getPowerById(id);
    }

    public List<Power> getAllPowers() {
        return powerDao.getAllPowers();
    }

    //HEROPOWER *******************************************
    public void addHeroPower(HeroPower heroPower) {
        hpDao.addHeroPower(heroPower);
    }

    public void deleteHeroPower(int heroId, int powerId) {
        hpDao.deleteHeroPower(heroId, powerId);
    }

    public List<HeroPower> getHeroesByPowerId(int powerId) {
        return hpDao.getHeroesByPowerId(powerId);
    }

    public List<HeroPower> getPowersByHeroId(int heroId) {
        return hpDao.getPowersByHeroId(heroId);
    }

    public List<HeroPower> getAllHeroAndPowers() {
        return hpDao.getAllHeroAndPowers();
    }

    //SIGHTING ********************************************
    public void addSighting(Sighting sighting) {
        siDao.addSighting(sighting);
    }

    public void deleteSighting(int sightingId) {
        siDao.deleteSighting(sightingId);
    }

    public void updateSighting(Sighting sighting) {
        siDao.updateSighting(sighting);
    }

    public Sighting getSightingById(int id) {
        return siDao.getSightingById(id);
    }

    public List<Sighting> getAllSightings() {
        return siDao.getAllSightings();
    }
    
    //ORGANIZATION *****************************************
    public void addOrganization(Organization organization) {
        orgDao.addOrganization(organization);
    }

    public void deleteOrganization(int organizationId) {
        orgDao.deleteOrganization(organizationId);
    }

    public void updateOrganization(Organization organization) {
        orgDao.updateOrganization(organization);
    }

    public Organization getOrganizationById(int id) {
        return orgDao.getOrganizationById(id);
    }

    public List<Organization> getAllOrganizations() {
        return orgDao.getAllOrganizations();
    }

    //MEMBER ***********************************************
    public void addMember(Member member) {
        memDao.addMember(member);
    }

    public void deleteMember(int memberId) {
        memDao.deleteMember(memberId);
    }

    public void updateMember(Member member) {
        memDao.updateMember(member);
    }

    public Member getMemberById(int id) {
        return memDao.getMemberById(id);
    }

    public List<Member> getAllMembers() {
        return memDao.getAllMembers();
    }

}
