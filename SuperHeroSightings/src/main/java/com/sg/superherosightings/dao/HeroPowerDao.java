/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.HeroPower;
import java.util.List;

/**
 *
 * @author yingy
 */
public interface HeroPowerDao {

    public void addHeroPower(HeroPower heroPower);

    public void deleteHeroPower(int heroId, int powerId);

    public List<HeroPower> getHeroesByPowerId(int powerId);

    public List<HeroPower> getPowersByHeroId(int heroId);
    
    public List<HeroPower> getAllHeroAndPowers();

}
