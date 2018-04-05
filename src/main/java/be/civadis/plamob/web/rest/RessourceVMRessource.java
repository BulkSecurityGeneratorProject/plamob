package be.civadis.plamob.web.rest;

import be.civadis.plamob.domain.User;
import be.civadis.plamob.repository.UserRepository;
import be.civadis.plamob.service.MailService;
import be.civadis.plamob.service.RessourceVMService;
import be.civadis.plamob.service.dto.RessourceDTO;
import be.civadis.plamob.service.dto.UserDTO;
import be.civadis.plamob.web.rest.errors.BadRequestAlertException;
import be.civadis.plamob.web.rest.errors.EmailAlreadyUsedException;
import be.civadis.plamob.web.rest.errors.LoginAlreadyUsedException;
import be.civadis.plamob.web.rest.util.HeaderUtil;
import be.civadis.plamob.web.rest.vm.RessourceVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RessourceVMRessource {

    private final Logger log = LoggerFactory.getLogger(RessourceVM.class);
    private static final String ENTITY_NAME = "RessourceVM";

    private RessourceVMService ressourceVMService;
    private UserRepository userRepository;
    private MailService mailService;

    @Autowired
    public RessourceVMRessource(RessourceVMService ressourceVMService, UserRepository userRepository,
                                MailService mailService) {
        this.ressourceVMService = ressourceVMService;
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    /**
     * POST /ressourceVM : Create a new Ressource and the new User associated with.
     * <p>
     * Creates a new user and new ressource if the login and email are not already used, and sends an
     * mail with an activation link.
     *
     * @param ressourceVM the view model containing data about the User and Ressource to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ressourceVM, or with status 400 (Bad Request) if the login or email is already in use
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws BadRequestAlertException 400 (Bad Request) if the login or email is already in use
     */
    @PostMapping("/ressourceVM")
    public ResponseEntity<RessourceVM> createRessourceVM(@RequestBody RessourceVM ressourceVM) throws URISyntaxException {
        log.debug("REST request to save User and Ressource : {}", ressourceVM);

        if (ressourceVM.getId() != null) {
            throw new BadRequestAlertException("A new user cannot already have an ID", "userManagement", "idexists");
            // Lowercase the user login before comparing with database
        } else if (userRepository.findOneByLogin(ressourceVM.getLogin().toLowerCase()).isPresent()) {
            throw new LoginAlreadyUsedException();
        } else if (userRepository.findOneByEmailIgnoreCase(ressourceVM.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException();
        } else {
            RessourceVM  newRessourceVM = ressourceVMService.createRessourceVM(ressourceVM);
            User userCreated = ressourceVMService.getUser();
            mailService.sendCreationEmail(userCreated);

            return ResponseEntity.created(new URI("/api/users/" + ressourceVM.getLogin()))
                .headers(HeaderUtil.createAlert( "userManagement.created", ressourceVM.getLogin()))
                .body(newRessourceVM);
        }
    }

    /**
     * GET /ressourceVMs. ret
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
