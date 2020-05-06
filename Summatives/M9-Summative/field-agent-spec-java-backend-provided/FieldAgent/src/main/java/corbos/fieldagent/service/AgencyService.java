/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corbos.fieldagent.service;

import corbos.fieldagent.data.AgencyRepository;
import corbos.fieldagent.entities.Agency;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jake
 */

@Service
public class AgencyService {
    
    @Autowired
    AgencyRepository agencyRepo;
    
    public List<Agency> findAll() {
        return agencyRepo.findAll();
    }
    
    public void save(Agency agency) {
        try {
            agencyRepo.save(agency);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Agency getAgencyById(int id) {
        return agencyRepo.getOne(id);
    }
    
}
