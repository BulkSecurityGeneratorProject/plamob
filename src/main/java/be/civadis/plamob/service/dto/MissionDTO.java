package be.civadis.plamob.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import be.civadis.plamob.domain.enumeration.ETAT_MISSION;

/**
 * A DTO for the Mission entity.
 */
public class MissionDTO implements Serializable {

    private Long id;

    private String resume;

    private String objectif;

    private String delai;

    private String technologie;

    private String autre;

    private ETAT_MISSION etat;

    private Long domaineConcerneId;

    private String domaineConcerneLibelle;

    private Long missionDefinitParId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public String getDelai() {
        return delai;
    }

    public void setDelai(String delai) {
        this.delai = delai;
    }

    public String getTechnologie() {
        return technologie;
    }

    public void setTechnologie(String technologie) {
        this.technologie = technologie;
    }

    public String getAutre() {
        return autre;
    }

    public void setAutre(String autre) {
        this.autre = autre;
    }

    public ETAT_MISSION getEtat() {
        return etat;
    }

    public void setEtat(ETAT_MISSION etat) {
        this.etat = etat;
    }

    public Long getDomaineConcerneId() {
        return domaineConcerneId;
    }

    public void setDomaineConcerneId(Long domaineId) {
        this.domaineConcerneId = domaineId;
    }

    public String getDomaineConcerneLibelle() {
        return domaineConcerneLibelle;
    }

    public void setDomaineConcerneLibelle(String domaineLibelle) {
        this.domaineConcerneLibelle = domaineLibelle;
    }

    public Long getMissionDefinitParId() {
        return missionDefinitParId;
    }

    public void setMissionDefinitParId(Long ressourceId) {
        this.missionDefinitParId = ressourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MissionDTO missionDTO = (MissionDTO) o;
        if(missionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), missionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MissionDTO{" +
            "id=" + getId() +
            ", resume='" + getResume() + "'" +
            ", objectif='" + getObjectif() + "'" +
            ", delai='" + getDelai() + "'" +
            ", technologie='" + getTechnologie() + "'" +
            ", autre='" + getAutre() + "'" +
            ", etat='" + getEtat() + "'" +
            "}";
    }
}
