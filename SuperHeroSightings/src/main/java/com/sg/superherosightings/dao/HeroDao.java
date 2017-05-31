/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Hero;
import java.util.List;

/**
 *
 * @author yingy
 */
public interface HeroDao {

    public void addHero(Hero hero);

    public void deleteHero(int heroId);

    public void updateHero(Hero hero);

    public Hero getHeroById(int id);

    public List<Hero> getAllHeroes();

}
