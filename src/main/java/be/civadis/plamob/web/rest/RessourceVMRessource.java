package be.civadis.plamob.web.rest;

import be.civadis.plamob.service.RessourceVMService;
import be.civadis.plamob.web.rest.vm.RessourceVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RessourceVMRessource {

    private final Logger log = LoggerFactory.getLogger(RessourceVM.class);

    private static final String ENTITY_NAME = "RessourceVM";
    private RessourceVMService ressourceVMService;

    @Autowired
    public RessourceVMRessource(RessourceVMService ressourceVMService) {
        this.ressourceVMService = ressourceVMService;
    }

    /**
     * POST /ressourceVM Create a new Ressource and the new User associated with.
     *
     * @return
     */
    @PostMapping("/ressourceVM")
    public ResponseEntity<RessourceVM> createRessourceVM(@RequestBody RessourceVM ressourceVM) {
        ressourceVM = this.ressourceVMService.createRessourceVM(ressourceVM);

        return ResponseEntity.ok().build();
    }

    /**
     * GET /ressourceVMs.
     *
     * @return
     */
    @GetMapping("/ressourceVMs")
    public List<RessourceVM> getAllRessourceVM() {

        return this.ressourceVMService.getAllRessourceVM();
    }

    public ResponseEntity<RessourceVM> updateRessourceVM() {

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<RessourceVM> getRessource() {

        return ResponseEntity.ok().build();
    }



    public ResponseEntity<Void> deleteRessourceVM() {

        return ResponseEntity.ok().build();
    }
}
