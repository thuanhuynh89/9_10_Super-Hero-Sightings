/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao.controller;

import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.service.Service;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author yingy
 */
@Controller
public class HomeController {
    Service service;
    
    @Inject
    public HomeController(Service service){
        this.service = service;
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String RecentSightings(Model model) {
        List<Sighting> sightingList = service.getAllSightings();
        model.addAttribute("sighting", sightingList);
        return "index";
    }
    
}