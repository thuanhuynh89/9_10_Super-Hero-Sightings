/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao.controller;

import com.sg.superherosightings.model.Address;
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
@RequestMapping({"/addressCRUD"})
public class AddressController {

    Service service;

    @Inject
    public AddressController(Service service) {
        this.service = service;
    }

    

    //***********************************************************************///
    @RequestMapping(value = "/displayAddressPage", method = RequestMethod.GET)
    public String displayAddressPage(Model model) {

        List<Address> addressList = service.getAllAddresses();

        model.addAttribute("addressList", addressList);
        return "address";
    }

    @RequestMapping(value = "/createAddress", method = RequestMethod.POST)
    public String createAddress(HttpServletRequest request) {
        Address a = new Address();
        a.setStreetNumber(request.getParameter("StreetNumber"));
        a.setStreetName(request.getParameter("StreetName"));
        a.setCity(request.getParameter("City"));
        a.setState_Province(request.getParameter("State_Province"));
        a.setCountry(request.getParameter("Country"));
        a.setPlanet(request.getParameter("Planet"));
        a.setGalaxy(request.getParameter("Galaxy"));
        a.setLatitude(request.getParameter("Latitude"));
        a.setLongitude(request.getParameter("Longitude"));
        service.addAddress(a);       //add address

        return "redirect:displayAddressPage";
    }

    @RequestMapping(value = "/displayAddressDetails", method = RequestMethod.GET)
    public String displayAddressDetails(HttpServletRequest request, Model model) {
        String addressIdParameter = request.getParameter("addressId");
        int addressId = Integer.parseInt(addressIdParameter);

        Address address = service.getAddressById(addressId);

        model.addAttribute("address_model", address);

        return "addressDetails";
    }

    @RequestMapping(value = "/deleteAddress", method = RequestMethod.GET)
    public String deleteAddress(HttpServletRequest request) {
        String addressIdParameter = request.getParameter("addressId");
        int addressId = Integer.parseInt(addressIdParameter);
        
        service.deleteAddress(addressId);
        return "redirect:displayAddressPage";
    }
    
    @RequestMapping(value = "/displayEditAddressForm", method = RequestMethod.GET)
    public String displayEditAddressForm(HttpServletRequest request, Model model) {
        String addressIdParameter = request.getParameter("addressId");
        int addressId = Integer.parseInt(addressIdParameter);
        Address address = service.getAddressById(addressId);
        model.addAttribute("address", address);
        return "addressEditForm";
    }
    
    @RequestMapping(value = "/editAddress", method = RequestMethod.POST)
    public String editAddress(@Valid @ModelAttribute("address") Address address, BindingResult result) {

        if (result.hasErrors()) {
            return "addressEditForm";
        }

        service.updateAddress(address);

        return "redirect:displayAddressPage";
    }
    //***********************************************************************///
}
