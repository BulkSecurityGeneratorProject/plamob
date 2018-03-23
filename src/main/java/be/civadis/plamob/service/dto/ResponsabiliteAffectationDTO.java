package be.civadis.plamob.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the ResponsabiliteAffectation entity.
 */
public class ResponsabiliteAffectationDTO implements Serializable {

    private Long id;

    private Long affectationId;

    private Long ressourceMobileId;

    private Long responsableRessourceMobileId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAffectationId() {
        return affectationId;
    }

    public void setAffectationId(Long affectationId) {
        this.affectationId = affectationId;
    }

    public Long getRessourceMobileId() {
        return ressourceMobileId;
    }

    public void setRessourceMobileId(Long ressourceId) {
        this.ressourceMobileId = ressourceId;
    }

    public Long getResponsableRessourceMobileId() {
        return responsableRessourceMobileId;
    }

    public void setResponsableRessourceMobileId(Long ressourceId) {
        this.responsableRessourceMobileId = ressourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResponsabiliteAffectationDTO responsabiliteAffectationDTO = (ResponsabiliteAffectationDTO) o;
        if(responsabiliteAffectationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), responsabiliteAffectationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResponsabiliteAffectationDTO{" +
            "id=" + getId() +
            "}";
    }
}
