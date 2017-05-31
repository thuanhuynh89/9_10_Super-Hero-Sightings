/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao.controller;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.service.Service;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author yingy
 */
@Controller
@RequestMapping({"/heroCRUD"})
public class HeroController {

    Service service;

    @Inject
    public HeroController(Service service) {
        this.service = service;
    }

    

    //***********************************************************************///
    @RequestMapping(value = "/displayHeroPage", method = RequestMethod.GET)
    public String displayHeroPage(Model model) {

        List<Hero> heroList = service.getAllHeroes();

        model.addAttribute("heroList", heroList);
        return "hero";
    }

    @RequestMapping(value = "/createHero", method = RequestMethod.POST)
    public String createHero(HttpServletRequest request) {
        Hero h = new Hero();
        h.setAlias(request.getParameter("alias"));
        h.setFirstName(request.getParameter("firstName"));
        h.setLastName(request.getParameter("lastName"));
        h.setDescription(request.getParameter("heroDescription"));
        service.addHero(h);       //add hero

        return "redirect:displayHeroPage";
    }

    @RequestMapping(value = "/displayHeroDetails", method = RequestMethod.GET)
    public String displayHeroDetails(HttpServletRequest request, Model model) {
        String heroIdParameter = request.getParameter("heroId");
        int heroId = Integer.parseInt(heroIdParameter);

        Hero hero = service.getHeroById(heroId);

        model.addAttribute("hero_model", hero);

        return "heroDetails";
    }

    @RequestMapping(value = "/deleteHero", method = RequestMethod.GET)
    public String deleteHero(HttpServletRequest request) {
        String heroIdParameter = request.getParameter("heroId");
        int heroId = Integer.parseInt(heroIdParameter);
        
        service.deleteHero(heroId);
        return "redirect:displayHeroPage";
    }
    
    @RequestMapping(value = "/displayEditHeroForm", method = RequestMethod.GET)
    public String displayEditHeroForm(HttpServletRequest request, Model model) {
        String heroIdParameter = request.getParameter("heroId");
        int heroId = Integer.parseInt(heroIdParameter);
        Hero hero = service.getHeroById(heroId);
        model.addAttribute("hero", hero);
        return "heroEditForm";
    }
    
    @RequestMapping(value = "/editHero", method = RequestMethod.POST)
    public String editHero(@Valid @ModelAttribute("hero") Hero hero, BindingResult result) {

        if (result.hasErrors()) {
            return "heroEditForm";
        }

        service.updateHero(hero);

        return "redirect:displayHeroPage";
    }
    //***********************************************************************///
}
