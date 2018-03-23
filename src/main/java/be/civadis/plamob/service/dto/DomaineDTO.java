package be.civadis.plamob.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Domaine entity.
 */
public class DomaineDTO implements Serializable {

    private Long id;

    private String libelle;

    private Long responsableId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Long getResponsableId() {
        return responsableId;
    }

    public void setResponsableId(Long ressourceId) {
        this.responsableId = ressourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DomaineDTO domaineDTO = (DomaineDTO) o;
        if(domaineDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), domaineDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DomaineDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
