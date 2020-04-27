/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corbos.fieldagent.controllers;

import corbos.fieldagent.entities.Agency;
import corbos.fieldagent.entities.Agent;
import corbos.fieldagent.entities.Assignment;
import corbos.fieldagent.entities.Country;
import corbos.fieldagent.entities.SecurityClearance;
import corbos.fieldagent.service.AgencyService;
import corbos.fieldagent.service.AgentService;
import corbos.fieldagent.service.AssignmentService;
import corbos.fieldagent.service.LookupService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Jake
 */

@Controller
public class MainController {
    
    @Autowired
    private LookupService lookupService;
    
    @Autowired
    private AgentService agentService;
    
    @Autowired
    private AgencyService agencyService;
    
    @Autowired
    private AssignmentService assignmentService;
    
    Set<ConstraintViolation<Assignment>> violations = new HashSet<>();
    
    
    @GetMapping("/")
    public String index(Model model) {
        List<Agent> agents = agentService.findAll();
        for (Agent agent : agents) {
            if (agent.getMiddleName() == null) {
                agent.setMiddleName("");
            }
        }
        model.addAttribute("agents", agentService.findAll());
        return "index";
    }
    
    @GetMapping("/addAgent")
    public String displayAddAgent(Model model) {
        
        model.addAttribute("clearances", lookupService.findAllSecurityClearances());
        model.addAttribute("agencies", agencyService.findAll());
        return "displayAgent";
    }
    
    @PostMapping("/addAgent")
    public String addAgent(@Valid Agent agent, BindingResult result, HttpServletRequest request, Model model) {
        
        SecurityClearance sc = new SecurityClearance();
        Agency agency = new Agency();
        
        try {
        agency = agencyService.getAgencyById(Integer.parseInt(request.getParameter("agency")));
        sc = lookupService.findSecurityClearanceById(Integer.parseInt(request.getParameter("securityClearance")));
        } catch(NumberFormatException e) {}
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
        
        String date = request.getParameter("birthDate");
        LocalDate birthDate = LocalDate.now();
        LocalDate min = LocalDate.parse("1900-01-01", formatter);
        if (date.isEmpty()) {
            agent.setBirthDate(null);
        } else {
            birthDate = LocalDate.parse(date);
            System.out.println(birthDate.isBefore(LocalDate.now().minusYears(10)));
            if (birthDate.isBefore(min) || birthDate.isAfter(LocalDate.now().minusYears(10))) {
                FieldError fe = new FieldError("agent", "birthDate", "Birthdate must be between 1/1/1900 and ten years from today.");
                result.addError(fe);
            }
        }

        if (result.hasErrors()) {
            model.addAttribute("agencyName", agency);
            model.addAttribute("clearanceSec", sc);
            model.addAttribute("clearances", lookupService.findAllSecurityClearances());
            model.addAttribute("agencies", agencyService.findAll());
            return"/addAgent";
        }
        
        agent.setActive(Boolean.parseBoolean(request.getParameter("isActive")));
        agent.setAgency(agency);
        agent.setSecurityClearance(sc);
        agentService.save(agent);
        return "redirect:/";
    }
    
    @GetMapping("/deleteAgent")
    public String deleteAgent(String identifier, Model model) {
        List<Assignment> assignments = assignmentService.getByAgentIdentifier(identifier);
        model.addAttribute("assignments", assignments.size());
        
        Agent agent = agentService.findAgentByIdentifier(identifier);
        model.addAttribute("agent", agent);
    
        return "/deleteAgent";
    }
    
    @GetMapping("/deleteAgentAndAssignments")
    public String deleteAgentAndAssignments(String identifier) {
        assignmentService.deleteAll(assignmentService.getByAgentIdentifier(identifier));
        agentService.deleteAgent(identifier);
       return "redirect:/";
    }
    
    @GetMapping("/displayAgent")
    public String displayAgent(String identifier, Model model) {
        Agent agent = agentService.findAgentByIdentifier(identifier);
        model.addAttribute("clearances", lookupService.findAllSecurityClearances());
        model.addAttribute("agencies", agencyService.findAll());
        model.addAttribute("clearanceSec", agent.getSecurityClearance());
        model.addAttribute("agencyName", agent.getAgency());
        
        model.addAttribute("agent", agent);
        model.addAttribute("isActive", agent.isActive());
        return "/displayAgent";
    }
    
    @PostMapping("/displayAgent")
    public String editAgent(Agent agent, Model model) {
        agentService.save(agent);
        return "redirect:/";
    }
    
    @GetMapping("/cancel")
    public String cancel() {
        return "redirect:/";
    }
    
    @GetMapping("/displayAssignments")
    public String viewAssignments(String identifier, Model model) {
        model.addAttribute("assignments", assignmentService.getByAgentIdentifier(identifier));
        model.addAttribute("agent", agentService.findAgentByIdentifier(identifier));
        return "displayAssignments";
    }
    
    @GetMapping("/deleteAssignment")
    public String deleteAssignment(String identifier, Model model) {
        assignmentService.deleteById(Integer.parseInt(identifier));
        
        return "redirect:/";
    }
    
    @GetMapping("/displayAddAssignment")
    public String addAssignment(String identifier, Model model, String country) {
        model.addAttribute("agent", agentService.findAgentByIdentifier(identifier));
        model.addAttribute("countries", lookupService.findAllCountries());
        return "/displayAssignment";
    }
    
    @PostMapping("/addAssignment")
    @GetMapping("/addAssignment")
    public String addAssignment(@Valid Assignment assignment, BindingResult result, HttpServletRequest request, Model model) {
        
        try {
            if (assignment.getStartDate().isAfter(assignment.getProjectedEndDate())) {
                FieldError fe = new FieldError("assignment", "projectedEndDate", "Projected end date cannot come before start date.");
                result.addError(fe);
            }
        } catch(NullPointerException e) {}
        try {
            if (assignment.getStartDate().isAfter(assignment.getActualEndDate())) {
                FieldError fe = new FieldError("assignment", "actualEndDate", "Actual end date cannot come before start date.");
                result.addError(fe);
            }
        } catch(NullPointerException e) {}
        
        Agent agent = agentService.findAgentByIdentifier(request.getParameter("agent"));
        List<Assignment> assignments = assignmentService.getAllById(agent.getIdentifier());
        try {
            for(Assignment a : assignments) {
                if(assignment.getStartDate().isBefore(a.getProjectedEndDate())) {
                    if (a.getAssignmentId() != assignment.getAssignmentId()) {
                        FieldError fe = new FieldError("assignment", "startDate", "Agent is already on assignment for that day.");
                        result.addError(fe);
                        break;
                    }
                }
            }
        } catch(NullPointerException e) {}
        
        String c = request.getParameter("country");
        Country country = lookupService.findCountryByCode(c);
        
        if (result.hasErrors()) {
           model.addAttribute("agent", agentService.findAgentByIdentifier(request.getParameter("agent")));
           model.addAttribute("countryNew", assignment.getCountry());
           model.addAttribute("countries", lookupService.findAllCountries());
           return "/addAssignment";
        }
        
        String countryCode = request.getParameter("country");
        assignment.setCountry(country);
        assignmentService.save(assignment);
        return "redirect:/";
    }
    
    @GetMapping("/editAssignment")
    public String displayEditditAssignment(Integer assignmentId, String country, Model model) {
        System.out.println(assignmentId);
        Assignment assignment = assignmentService.getById(Integer.toString(assignmentId));
        System.out.println(assignment.toString());
        model.addAttribute("countries", lookupService.findAllCountries());
        model.addAttribute("countryNew", assignment.getCountry());
        model.addAttribute("fieldAgent", assignment.getAgent());
        model.addAttribute("agents", agentService.findAll());
        model.addAttribute("assignment", assignment);
        return "/editAssignment";
    }
    
    @PostMapping("/editAssignment")
    public String editAssignment(@Valid Assignment a, BindingResult result, HttpServletRequest req, Model model) {
        model.addAttribute("countries", lookupService.findAllCountries());
        String identifier = req.getParameter("agentIdentifier");
        String assignmentId = req.getParameter("assignmentId");
        Agent agent = agentService.findAgentByIdentifier(identifier);
        a.setAgent(agent);
        
        try {
            if (a.getStartDate().isAfter(a.getProjectedEndDate())) {
                FieldError fe = new FieldError("assignment", "projectedEndDate", "Projected end date cannot come before start date.");
                result.addError(fe);
            }
        } catch(NullPointerException e) {}
        try {
            if (a.getStartDate().isAfter(a.getActualEndDate())) {
                FieldError fe = new FieldError("assignment", "actualEndDate", "Actual end date cannot come before start date.");
                result.addError(fe);
            }
        } catch(NullPointerException e) {}
        
        List<Assignment> assignments = assignmentService.getAllById(identifier);
        try {                                               
            for(Assignment c : assignments) {
                if(a.getStartDate().isBefore(c.getProjectedEndDate())) {
                    if (a.getAssignmentId() != c.getAssignmentId()) {
                        System.out.println(a.getAssignmentId());             //Note for later -> These two IDs are different for some reason
                        System.out.println(c.getAssignmentId());
                        FieldError fe = new FieldError("assignment", "startDate", "Agent is already on assignment for that day.");
                        result.addError(fe);
                        break;
                    }
                }
            }
        } catch(NullPointerException e) {}
        
        
        if (result.getErrorCount() > 1) {
            model.addAttribute("agent", identifier);
            model.addAttribute("countryNew", a.getCountry());
            return "/editAssignment";
        }
        Assignment assignment = assignmentService.getById(assignmentId.toString());
        assignment.setNotes(req.getParameter("notes"));
        
        Country country = lookupService.findCountryByCode(req.getParameter("country"));
        assignment.setCountry(country);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
        
        String start = req.getParameter("startDate");
        if (start.isEmpty()) {
            assignment.setStartDate(null);
        } else {
            LocalDate startDate = LocalDate.parse(start, formatter);
            assignment.setStartDate(startDate);
        }
        
        String project = req.getParameter("projectedEndDate");
        if(project.isEmpty()) {
            assignment.setProjectedEndDate(null);
        } else {
            LocalDate projectedEndDate = LocalDate.parse(req.getParameter("projectedEndDate"), formatter);
            assignment.setProjectedEndDate(projectedEndDate);
        }
        
        String actual = req.getParameter("actualEndDate");
        if (actual.isEmpty()) {
            assignment.setActualEndDate(null);
        } else {
            LocalDate actualEndDate = LocalDate.parse(actual, formatter);
            assignment.setActualEndDate(actualEndDate);
        }

        assignmentService.save(assignment);
        return "redirect:/displayAssignments?identifier=" +agent.getIdentifier();
    }
}
