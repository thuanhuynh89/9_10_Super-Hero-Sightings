/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao.controller;

import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
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
@RequestMapping({"/sightingCRUD"})
public class SightingController {

    Service service;

    @Inject
    public SightingController(Service service) {
        this.service = service;
    }

    //***********************************************************************///
    @RequestMapping(value = "/displaySightingPage", method = RequestMethod.GET)
    public String displaySightingPage(Model model) {
        List<Location> locationList = service.getAllLocations();
        List<Hero> heroList = service.getAllHeroes();
        List<Sighting> sightingList = service.getAllSightings();

        model.addAttribute("locationList", locationList);
        model.addAttribute("heroList", heroList);
        model.addAttribute("sightingList", sightingList);

        return "sighting";
    }

    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String createSighting(HttpServletRequest request) {
        Sighting s = new Sighting();

        s.setSightingDate(request.getParameter("sightingDate"));
        s.setDescription(request.getParameter("description"));
        
        String heroIDparameter = request.getParameter("hero");
        int heroID = Integer.parseInt(heroIDparameter);
        Hero hero = service.getHeroById(heroID);
        s.setHero(hero);
        
        String locationIDparameter = request.getParameter("location");
        int locationID = Integer.parseInt(locationIDparameter);
        Location location = service.getLocationById(locationID);
        s.setLocation(location);

        service.addSighting(s);       //add sighting

        return "redirect:displaySightingPage";
    }

    @RequestMapping(value = "/displaySightingDetails", method = RequestMethod.GET)
    public String displaySightingDetails(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);

        Sighting sighting = service.getSightingById(sightingId);
        Hero hero = service.getHeroById(sighting.getHero().getHeroId());
        Location location = service.getLocationById(sighting.getLocation().getLocation());
        
        model.addAttribute("sighting_model", sighting);
        model.addAttribute("hero_model", hero);
        model.addAttribute("location_model", location);
        return "sightingDetails";
    }

    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);

        service.deleteSighting(sightingId);
        return "redirect:displaySightingPage";
    }

    @RequestMapping(value = "/displayEditSightingForm", method = RequestMethod.GET)
    public String displayEditSightingForm(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);
        Sighting sighting = service.getSightingById(sightingId);
             
        List<Hero> heroList = service.getAllHeroes();
        List<Location> locationList = service.getAllLocations();
        
        model.addAttribute("sighting", sighting);
        model.addAttribute("heroList", heroList);
        model.addAttribute("locationList", locationList);
        return "sightingEditForm";
    }

    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(@Valid @ModelAttribute("sighting") Sighting sighting, BindingResult result) {

        if (result.hasErrors()) {
            return "sightingEditForm";
        }

        service.updateSighting(sighting);

        return "redirect:displaySightingPage";
    }
    
}
