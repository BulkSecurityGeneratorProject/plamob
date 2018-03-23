package be.civadis.plamob.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import be.civadis.plamob.domain.enumeration.TYPE_RESSOURCE;

/**
 * A DTO for the Ressource entity.
 */
public class RessourceDTO implements Serializable {

    private Long id;

    private String trigramme;

    private String tel;

    private TYPE_RESSOURCE typeRess;

    private Long profilId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrigramme() {
        return trigramme;
    }

    public void setTrigramme(String trigramme) {
        this.trigramme = trigramme;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public TYPE_RESSOURCE getTypeRess() {
        return typeRess;
    }

    public void setTypeRess(TYPE_RESSOURCE typeRess) {
        this.typeRess = typeRess;
    }

    public Long getProfilId() {
        return profilId;
    }

    public void setProfilId(Long profilId) {
        this.profilId = profilId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RessourceDTO ressourceDTO = (RessourceDTO) o;
        if(ressourceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ressourceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RessourceDTO{" +
            "id=" + getId() +
            ", trigramme='" + getTrigramme() + "'" +
            ", tel='" + getTel() + "'" +
            ", typeRess='" + getTypeRess() + "'" +
            "}";
    }
}
