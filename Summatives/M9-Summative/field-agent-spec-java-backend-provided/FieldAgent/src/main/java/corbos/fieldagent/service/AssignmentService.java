/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corbos.fieldagent.service;

import corbos.fieldagent.data.AssignmentRepository;
import corbos.fieldagent.entities.Assignment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jake
 */

@Service
public class AssignmentService {
    
    @Autowired
    AssignmentRepository assignmentRepo;
    
    public List<Assignment> getByAgentIdentifier(String identifier) {
        return assignmentRepo.findByAgentIdentifier(identifier);
    }
    
    public void deleteAll(List<Assignment> assignments) {
        assignmentRepo.deleteAll(assignments);
    }
    
    public void deleteById(int assignmentId) {
        assignmentRepo.deleteById(assignmentId);
    }
    
    public void save(Assignment assignment) {
        assignmentRepo.save(assignment);
    }
    
    public Assignment getById(String id) {
       return assignmentRepo.getOne(Integer.parseInt(id));
    }
    
    public List<Assignment> getAllById(String id) {
        return assignmentRepo.findByAgentIdentifier(id);
    }
}
