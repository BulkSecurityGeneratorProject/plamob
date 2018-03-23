package be.civadis.plamob.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import be.civadis.plamob.domain.enumeration.ETAT_MISSION;

/**
 * A Mission.
 */
@Entity
@Table(name = "mission")
public class Mission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "resume")
    private String resume;

    @Column(name = "objectif")
    private String objectif;

    @Column(name = "delai")
    private String delai;

    @Column(name = "technologie")
    private String technologie;

    @Column(name = "autre")
    private String autre;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private ETAT_MISSION etat;

    @ManyToOne
    private Domaine domaineConcerne;

    @ManyToOne
    private Ressource missionDefinitPar;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResume() {
        return resume;
    }

    public Mission resume(String resume) {
        this.resume = resume;
        return this;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getObjectif() {
        return objectif;
    }

    public Mission objectif(String objectif) {
        this.objectif = objectif;
        return this;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public String getDelai() {
        return delai;
    }

    public Mission delai(String delai) {
        this.delai = delai;
        return this;
    }

    public void setDelai(String delai) {
        this.delai = delai;
    }

    public String getTechnologie() {
        return technologie;
    }

    public Mission technologie(String technologie) {
        this.technologie = technologie;
        return this;
    }

    public void setTechnologie(String technologie) {
        this.technologie = technologie;
    }

    public String getAutre() {
        return autre;
    }

    public Mission autre(String autre) {
        this.autre = autre;
        return this;
    }

    public void setAutre(String autre) {
        this.autre = autre;
    }

    public ETAT_MISSION getEtat() {
        return etat;
    }

    public Mission etat(ETAT_MISSION etat) {
        this.etat = etat;
        return this;
    }

    public void setEtat(ETAT_MISSION etat) {
        this.etat = etat;
    }

    public Domaine getDomaineConcerne() {
        return domaineConcerne;
    }

    public Mission domaineConcerne(Domaine domaine) {
        this.domaineConcerne = domaine;
        return this;
    }

    public void setDomaineConcerne(Domaine domaine) {
        this.domaineConcerne = domaine;
    }

    public Ressource getMissionDefinitPar() {
        return missionDefinitPar;
    }

    public Mission missionDefinitPar(Ressource ressource) {
        this.missionDefinitPar = ressource;
        return this;
    }

    public void setMissionDefinitPar(Ressource ressource) {
        this.missionDefinitPar = ressource;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Mission mission = (Mission) o;
        if (mission.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mission.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Mission{" +
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
