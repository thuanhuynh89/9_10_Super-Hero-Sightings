/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao.controller;

import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Member;
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
@RequestMapping({"/memberCRUD"})
public class MemberController {

    Service service;

    @Inject
    public MemberController(Service service) {
        this.service = service;
    }

    //***********************************************************************///
    @RequestMapping(value = "/displayMemberPage", method = RequestMethod.GET)
    public String displayMemberPage(Model model) { 
        List<Hero> heroList = service.getAllHeroes();
        List<Organization> organizationList = service.getAllOrganizations();
        List<Member> memberList = service.getAllMembers();
        

        model.addAttribute("heroList", heroList);
        model.addAttribute("organizationList", organizationList);
        model.addAttribute("memberList", memberList);

        return "member";
    }

    @RequestMapping(value = "/createMember", method = RequestMethod.POST)
    public String createMember(HttpServletRequest request) {
        Member m = new Member();

        m.setStartDate(request.getParameter("startDate"));
        m.setEndDate(request.getParameter("endDate"));
        
        String heroIDparameter = request.getParameter("hero");
        int heroID = Integer.parseInt(heroIDparameter);   
        Hero hero = service.getHeroById(heroID);
        m.setHero(hero);
        
        String organizationIDparameter = request.getParameter("organization");
        int organizationID = Integer.parseInt(organizationIDparameter);   
        Organization org = service.getOrganizationById(organizationID);
        m.setOrganization(org);

        service.addMember(m);       //add member

        return "redirect:displayMemberPage";
    }

    @RequestMapping(value = "/displayMemberDetails", method = RequestMethod.GET)
    public String displayMemberDetails(HttpServletRequest request, Model model) {
        String memberIdParameter = request.getParameter("memberId");
        int memberId = Integer.parseInt(memberIdParameter);

        Member member = service.getMemberById(memberId);
        Hero hero = service.getHeroById(member.getHero().getHeroId());
        Organization org = service.getOrganizationById(member.getOrganization().getOrganizationId());
        model.addAttribute("member_model", member);
        model.addAttribute("hero_model", hero);
        model.addAttribute("organization_model", org);
        return "memberDetails";
    }

    @RequestMapping(value = "/deleteMember", method = RequestMethod.GET)
    public String deleteMember(HttpServletRequest request) {
        String memberIdParameter = request.getParameter("memberId");
        int memberId = Integer.parseInt(memberIdParameter);

        service.deleteMember(memberId);
        return "redirect:displayMemberPage";
    }

    @RequestMapping(value = "/displayEditMemberForm", method = RequestMethod.GET)
    public String displayEditMemberForm(HttpServletRequest request, Model model) {
        String memberIdParameter = request.getParameter("memberId");
        int memberId = Integer.parseInt(memberIdParameter);
        Member member = service.getMemberById(memberId);
             
        List<Hero> heroList = service.getAllHeroes();
        List<Organization> organizationList = service.getAllOrganizations();
        
        model.addAttribute("member", member);
        model.addAttribute("heroList", heroList);
        model.addAttribute("organizationList", organizationList);
        return "memberEditForm";
    }

    @RequestMapping(value = "/editMember", method = RequestMethod.POST)
    public String editMember(@Valid @ModelAttribute("member") Member member, BindingResult result) {

        if (result.hasErrors()) {
            return "memberEditForm";
        }

        service.updateMember(member);

        return "redirect:displayMemberPage";
    }
    
}
