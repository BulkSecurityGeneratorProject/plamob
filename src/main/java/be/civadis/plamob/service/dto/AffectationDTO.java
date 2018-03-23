package be.civadis.plamob.service.dto;


import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import be.civadis.plamob.domain.enumeration.ETAT_AFFECTATION;

/**
 * A DTO for the Affectation entity.
 */
public class AffectationDTO implements Serializable {

    private Long id;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private Long taux;

    private ETAT_AFFECTATION etat;

    private Long profilId;

    private Long affectationDemandeeParId;

    private Long affectationValideeParId;

    private Long ressourceAffecteeId;

    private Long livrableId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Long getTaux() {
        return taux;
    }

    public void setTaux(Long taux) {
        this.taux = taux;
    }

    public ETAT_AFFECTATION getEtat() {
        return etat;
    }

    public void setEtat(ETAT_AFFECTATION etat) {
        this.etat = etat;
    }

    public Long getProfilId() {
        return profilId;
    }

    public void setProfilId(Long profilId) {
        this.profilId = profilId;
    }

    public Long getAffectationDemandeeParId() {
        return affectationDemandeeParId;
    }

    public void setAffectationDemandeeParId(Long ressourceId) {
        this.affectationDemandeeParId = ressourceId;
    }

    public Long getAffectationValideeParId() {
        return affectationValideeParId;
    }

    public void setAffectationValideeParId(Long ressourceId) {
        this.affectationValideeParId = ressourceId;
    }

    public Long getRessourceAffecteeId() {
        return ressourceAffecteeId;
    }

    public void setRessourceAffecteeId(Long ressourceId) {
        this.ressourceAffecteeId = ressourceId;
    }

    public Long getLivrableId() {
        return livrableId;
    }

    public void setLivrableId(Long livrableId) {
        this.livrableId = livrableId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AffectationDTO affectationDTO = (AffectationDTO) o;
        if(affectationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), affectationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AffectationDTO{" +
            "id=" + getId() +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", taux=" + getTaux() +
            ", etat='" + getEtat() + "'" +
            "}";
    }
}
