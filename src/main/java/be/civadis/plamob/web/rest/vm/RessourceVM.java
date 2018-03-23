package be.civadis.plamob.web.rest.vm;

import be.civadis.plamob.service.dto.UserDTO;

import javax.validation.constraints.Size;

public class RessourceVM extends UserDTO{

    @Size(min = 3, max = 3)
    private String tigramme;
    private String telephone;
    private String typeRessource;

    public String getTigramme() {
        return tigramme;
    }

    public void setTigramme(String tigramme) {
        this.tigramme = tigramme;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTypeRessource() {
        return typeRessource;
    }

    public void setTypeRessource(String typeRessource) {
        this.typeRessource = typeRessource;
    }
}
