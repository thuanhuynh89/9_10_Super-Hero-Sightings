/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao.controller;

import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Location;
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
@RequestMapping({"/locationCRUD"})
public class LocationController {

    Service service;

    @Inject
    public LocationController(Service service) {
        this.service = service;
    }

    //***********************************************************************///
    @RequestMapping(value = "/displayLocationPage", method = RequestMethod.GET)
    public String displayLocationPage(Model model) { 
        List<Address> addressList = service.getAllAddresses();
        List<Location> locationList = service.getAllLocations();

        model.addAttribute("addressList", addressList);
        model.addAttribute("locationList", locationList);

        return "location";
    }

    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)
    public String createLocation(HttpServletRequest request) {
        Location l = new Location();

        l.setLocationName(request.getParameter("locationName"));
        l.setDescription(request.getParameter("description"));
        
        String addressIDparameter = request.getParameter("address");
        int addressID = Integer.parseInt(addressIDparameter);
        
        Address add = service.getAddressById(addressID);
        l.setAddress(add);

        service.addLocation(l);       //add location

        return "redirect:displayLocationPage";
    }

    @RequestMapping(value = "/displayLocationDetails", method = RequestMethod.GET)
    public String displayLocationDetails(HttpServletRequest request, Model model) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);

        Location location = service.getLocationById(locationId);
        Address address = service.getAddressById(location.getAddress().getAddressId());
        model.addAttribute("location_model", location);
        model.addAttribute("address_model", address);
        return "locationDetails";
    }

    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);

        service.deleteLocation(locationId);
        return "redirect:displayLocationPage";
    }

    @RequestMapping(value = "/displayEditLocationForm", method = RequestMethod.GET)
    public String displayEditLocationForm(HttpServletRequest request, Model model) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        Location location = service.getLocationById(locationId);
             
        List<Address> addressList = service.getAllAddresses();
        
        model.addAttribute("location", location);
        model.addAttribute("addressList", addressList);
        return "locationEditForm";
    }

    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(@Valid @ModelAttribute("location") Location location, BindingResult result) {

        if (result.hasErrors()) {
            return "locationEditForm";
        }

        service.updateLocation(location);

        return "redirect:displayLocationPage";
    }
    //***********************************************************************///
}
