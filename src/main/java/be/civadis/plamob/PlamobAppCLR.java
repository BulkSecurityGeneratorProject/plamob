package be.civadis.plamob;

import be.civadis.plamob.domain.Authority;
import be.civadis.plamob.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

public class PlamobAppCLR implements CommandLineRunner {

    private AuthorityRepository authorityRepository;

    @Autowired
    public PlamobAppCLR(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        List<String> authorities = Arrays.asList("ROLE_RESP_DOMAINE", "ROLE_RESP_RESSOURCE_MOBILE",
            "ROLE_RESSOURCE_MOBILE", "ROLE_INTERVENANT");

        authorities.forEach(
            e -> {
                Authority authority = new Authority();
                authority.setName(e);
                authorityRepository.save(authority);
            });
    }
}
