package be.civadis.plamob.service;

import be.civadis.plamob.domain.Authority;
import be.civadis.plamob.domain.Ressource;
import be.civadis.plamob.domain.User;
import be.civadis.plamob.domain.enumeration.TYPE_RESSOURCE;
import be.civadis.plamob.repository.RessourceRepository;
import be.civadis.plamob.repository.UserRepository;
import be.civadis.plamob.web.rest.vm.RessourceVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RessourceVMService {

    private RessourceRepository ressourceRepository;
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RessourceVMService(RessourceRepository ressourceRepository, UserRepository userRepository,
                              PasswordEncoder passwordEncoder) {
        this.ressourceRepository = ressourceRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Save a ressource and the user.
     *
     * @param ressourceVM the entity to save
     * @return
     */
    public RessourceVM createRessourceVM(RessourceVM ressourceVM) {
        // Create User
        User user = new User();
        Set<Authority> authorities = new HashSet<>();
        ressourceVM.getAuthorities().forEach(
            e -> {
                Authority authority = new Authority();
                authority.setName(e);
                authorities.add(authority);
            });
        user.setLogin(ressourceVM.getLogin());
        user.setLastName(ressourceVM.getLastName());
        user.setFirstName(ressourceVM.getFirstName());
        user.setEmail(ressourceVM.getEmail());
        user.setAuthorities(authorities);
        user.setLangKey("fr");
        // Setting default password
        String password = "123";
        String encryptedPassword = passwordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        user = this.userRepository.save(user);

        // Create the ressource associatied with the user
        Ressource ressource = new Ressource();
        ressource.setTrigramme(ressourceVM.getTigramme());
        ressource.setTel(ressourceVM.getTelephone());
        TYPE_RESSOURCE typeRessource = getTypeRessource(ressourceVM.getTypeRessource());
        if( typeRessource != null)
            ressource.setTypeRess(typeRessource);
        ressource.setUser(user);
        this.ressourceRepository.save(ressource);
        ressourceVM.setId(user.getId());

        return ressourceVM;
    }

    public List<RessourceVM> getAllRessourceVM() {
        List<RessourceVM> ressourceVMs = new ArrayList<>();
        List<Ressource> ressources = this.ressourceRepository.findAll();
        RessourceVM ressourceVM;
        User user;
        for(Ressource ressource : ressources) {
            user = userRepository.findOne(ressource.getUser().getId());
            ressourceVM = getRessourceVMFromRessourceAndUser(user, ressource);

            ressourceVMs.add(ressourceVM);
        }

        return ressourceVMs;
    }


    /********************* Private Methods ****************************************************************************/

    private TYPE_RESSOURCE getTypeRessource(String typeRess) {
        TYPE_RESSOURCE typeRessource = null;

        if(typeRess != null) {
            switch (typeRess) {
                case "DOM": typeRessource = TYPE_RESSOURCE.DOM;
                    break;
                case "MOB": typeRessource = TYPE_RESSOURCE.MOB;
                    break;
            }
        }

        return typeRessource;
    }

    private RessourceVM getRessourceVMFromRessourceAndUser(User user, Ressource ressource) {
        RessourceVM ressourceVM = new RessourceVM();

        ressourceVM.setId(user.getId());
        ressourceVM.setFirstName(user.getFirstName());
        ressourceVM.setLastName(user.getLastName());
        ressourceVM.setLogin(user.getLogin());
        ressourceVM.setAuthorities(getAuthorities(user));
        ressourceVM.setEmail(user.getEmail());
        ressourceVM.setImageUrl(user.getImageUrl());
        ressourceVM.setActivated(user.getActivated());
        ressourceVM.setLangKey(user.getLangKey());
        ressourceVM.setCreatedBy(user.getCreatedBy());
        ressourceVM.setCreatedDate(user.getCreatedDate());
        ressourceVM.setLastModifiedBy(user.getLastModifiedBy());
        ressourceVM.setLastModifiedDate(user.getLastModifiedDate());

        ressourceVM.setTelephone(ressource.getTel());
        ressourceVM.setTigramme(ressource.getTrigramme());
        ressourceVM.setTypeRessource(ressource.getTypeRess().name());

        return ressourceVM;
    }

    private Set<String> getAuthorities(User user) {
        Set<String> authorities = new HashSet<>();
        user.getAuthorities().forEach(authority -> authorities.add(authority.getName()));

        return authorities;
    }

}
