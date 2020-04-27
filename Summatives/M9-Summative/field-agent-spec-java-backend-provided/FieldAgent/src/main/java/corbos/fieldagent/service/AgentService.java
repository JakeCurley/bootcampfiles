/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corbos.fieldagent.service;

import corbos.fieldagent.data.AgentRepository;
import corbos.fieldagent.entities.Agent;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jake
 */
@Service
public class AgentService {
    
    @Autowired
    AgentRepository agentRepo;
    
    public List<Agent> findAll() {
        return agentRepo.findAll();
    }
    
    public void save(Agent agent) {
        agentRepo.save(agent);
    }
    
    public Agent findAgentByIdentifier(String id) {
        return agentRepo.getOne(id);
    }
    
    public void deleteAgent(String id) {
        agentRepo.deleteById(id);
    }
    
}
