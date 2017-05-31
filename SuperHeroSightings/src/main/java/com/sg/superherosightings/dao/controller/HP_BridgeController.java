/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao.controller;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.HeroPower;
import com.sg.superherosightings.model.Power;
import com.sg.superherosightings.service.Service;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author yingy
 */
@Controller
@RequestMapping({"/HPCRUD"})
public class HP_BridgeController {

    Service service;

    @Inject
    public HP_BridgeController(Service service) {
        this.service = service;
    }

    //***********************************************************************///
    @RequestMapping(value = "/displayHPBridgePage", method = RequestMethod.GET)
    public String displayHPBridgePage(Model model) { 
        List<Hero> heroList = service.getAllHeroes();
        List<Power> powerList = service.getAllPowers();
        List<HeroPower> hpList = service.getAllHeroAndPowers();

        model.addAttribute("heroList", heroList);
        model.addAttribute("powerList", powerList);
        model.addAttribute("hpList", hpList);
        
        return "HP_Bridge";
    }

    @RequestMapping(value = "/createHPBridge", method = RequestMethod.POST)
    public String createHPBridge(HttpServletRequest request) {
        HeroPower hp = new HeroPower();
 
        String heroIDparameter = request.getParameter("hero");
        int heroID = Integer.parseInt(heroIDparameter);
        Hero hero = service.getHeroById(heroID);
        
        hp.setHero(hero);   //*********************************
        
        String powerIDparameter = request.getParameter("power");
        int powerID = Integer.parseInt(powerIDparameter);
        Power power = service.getPowerById(powerID);
        
        hp.setPower(power); //*********************************

        service.addHeroPower(hp);       //add HP object into mememory database

        return "redirect:displayHPBridgePage";
    }

    @RequestMapping(value = "/displayHPBridgeDetails", method = RequestMethod.GET)
    public String displayHeroPowerDetails(HttpServletRequest request, Model model) {
        
        String HPIdParameter = request.getParameter("HPId");
        int HPId = Integer.parseInt(HPIdParameter);
        
        Hero hero = service.getHeroById(HPId);
        model.addAttribute("hero_model", hero);
        
        List<HeroPower> heroPower = service.getPowersByHeroId(HPId);
        model.addAttribute("hpList", heroPower);
        
        List<Hero> heroList = service.getAllHeroes();
        model.addAttribute("heroList", heroList);
        return "HP_BridgeDetails";
        
    }

    
    @RequestMapping(value = "/deleteHeroPower", method = RequestMethod.GET)
    public String deleteHeroPower(HttpServletRequest request) {
        String HPIdParameter = request.getParameter("HPDelete");
        int HPDelete = Integer.parseInt(HPIdParameter);

        String HPIdParameter2 = request.getParameter("HPDelete2");
        int HPDelete2 = Integer.parseInt(HPIdParameter2);
        
        service.deleteHeroPower(HPDelete,HPDelete2);
        return "redirect:displayHPBridgePage";
    }
    
    
   
}
