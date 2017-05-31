/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao.controller;

import com.sg.superherosightings.model.Power;
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
@RequestMapping({"/powerCRUD"})
public class PowerController {

    Service service;

    @Inject
    public PowerController(Service service) {
        this.service = service;
    }

    //***********************************************************************///
    @RequestMapping(value = "/displayPowerPage", method = RequestMethod.GET)
    public String displayPowerPage(Model model) {

        List<Power> powerList = service.getAllPowers();

        model.addAttribute("powerList", powerList);
        return "power";
    }

    @RequestMapping(value = "/createPower", method = RequestMethod.POST)
    public String createPower(HttpServletRequest request) {
        Power p = new Power();
        p.setDescription(request.getParameter("description"));
        service.addPower(p);       //add power

        return "redirect:displayPowerPage";
    }

    @RequestMapping(value = "/displayPowerDetails", method = RequestMethod.GET)
    public String displayPowerDetails(HttpServletRequest request, Model model) {
        String powersIdParameter = request.getParameter("powersId");
        int powersId = Integer.parseInt(powersIdParameter);

        Power power = service.getPowerById(powersId);

        model.addAttribute("power_model", power);

        return "powerDetails";
    }

    @RequestMapping(value = "/deletePower", method = RequestMethod.GET)
    public String deletePower(HttpServletRequest request) {
        String powerIdParameter = request.getParameter("powersId");
        int powerId = Integer.parseInt(powerIdParameter);
        
        service.deletePower(powerId);
        return "redirect:displayPowerPage";
    }
    
    @RequestMapping(value = "/displayEditPowerForm", method = RequestMethod.GET)
    public String displayEditPowerForm(HttpServletRequest request, Model model) {
        String powerIdParameter = request.getParameter("powersId");
        int powersId = Integer.parseInt(powerIdParameter);
        Power power = service.getPowerById(powersId);
        model.addAttribute("power", power);
        return "powerEditForm";
    }
    
    @RequestMapping(value = "/editPower", method = RequestMethod.POST)
    public String editPower(@Valid @ModelAttribute("power") Power power, BindingResult result) {

        if (result.hasErrors()) {
            return "powerEditForm";
        }

        service.updatePower(power);

        return "redirect:displayPowerPage";
    }
    //***********************************************************************///
}
  

