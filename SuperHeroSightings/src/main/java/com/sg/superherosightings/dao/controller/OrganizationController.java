/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao.controller;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
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
@RequestMapping({"/organizationCRUD"})
public class OrganizationController {

    Service service;

    @Inject
    public OrganizationController(Service service) {
        this.service = service;
    }

    //***********************************************************************///
    @RequestMapping(value = "/displayOrganizationPage", method = RequestMethod.GET)
    public String displayOrganizationPage(Model model) { 
        List<Location> locationList = service.getAllLocations();
        List<Organization> organizationList = service.getAllOrganizations();

        model.addAttribute("locationList", locationList);
        model.addAttribute("organizationList", organizationList);

        return "organization";
    }

    @RequestMapping(value = "/createOrganization", method = RequestMethod.POST)
    public String createOrganization(HttpServletRequest request) {
        Organization o = new Organization();

        o.setOrganizationName(request.getParameter("organizationName"));
        o.setDescription(request.getParameter("description"));
        
        String locationIDparameter = request.getParameter("location");
        int locationID = Integer.parseInt(locationIDparameter);
        Location loc = service.getLocationById(locationID);
        o.setLocation(loc);

        service.addOrganization(o);       //add organization

        return "redirect:displayOrganizationPage";
    }

    @RequestMapping(value = "/displayOrganizationDetails", method = RequestMethod.GET)
    public String displayOrganizationDetails(HttpServletRequest request, Model model) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);

        Organization organization = service.getOrganizationById(organizationId);
        Location location = service.getLocationById(organization.getLocation().getLocation());
        model.addAttribute("organization_model", organization);
        model.addAttribute("location_model", location);
        return "organizationDetails";
    }

    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);

        service.deleteOrganization(organizationId);
        return "redirect:displayOrganizationPage";
    }

    @RequestMapping(value = "/displayEditOrganizationForm", method = RequestMethod.GET)
    public String displayEditOrganizationForm(HttpServletRequest request, Model model) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);
        Organization organization = service.getOrganizationById(organizationId);
             
        List<Location> locationList = service.getAllLocations();
        
        model.addAttribute("organization", organization);
        model.addAttribute("locationList", locationList);
        return "organizationEditForm";
    }

    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(@Valid @ModelAttribute("organization") Organization organization, BindingResult result) {

        if (result.hasErrors()) {
            return "organizationEditForm";
        }

        service.updateOrganization(organization);

        return "redirect:displayOrganizationPage";
    }
    
}
