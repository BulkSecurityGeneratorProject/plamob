package be.civadis.plamob.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import be.civadis.plamob.domain.enumeration.ETAT_AFFECTATION;

/**
 * A Affectation.
 */
@Entity
@Table(name = "affectation")
public class Affectation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "taux")
    private Long taux;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private ETAT_AFFECTATION etat;

    @OneToOne
    @JoinColumn(unique = true)
    private Profil profil;

    @ManyToOne
    private Ressource affectationDemandeePar;

    @ManyToOne
    private Ressource affectationValideePar;

    @ManyToOne
    private Ressource ressourceAffectee;

    @ManyToOne
    private Livrable livrable;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public Affectation dateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public Affectation dateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Long getTaux() {
        return taux;
    }

    public Affectation taux(Long taux) {
        this.taux = taux;
        return this;
    }

    public void setTaux(Long taux) {
        this.taux = taux;
    }

    public ETAT_AFFECTATION getEtat() {
        return etat;
    }

    public Affectation etat(ETAT_AFFECTATION etat) {
        this.etat = etat;
        return this;
    }

    public void setEtat(ETAT_AFFECTATION etat) {
        this.etat = etat;
    }

    public Profil getProfil() {
        return profil;
    }

    public Affectation profil(Profil profil) {
        this.profil = profil;
        return this;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public Ressource getAffectationDemandeePar() {
        return affectationDemandeePar;
    }

    public Affectation affectationDemandeePar(Ressource ressource) {
        this.affectationDemandeePar = ressource;
        return this;
    }

    public void setAffectationDemandeePar(Ressource ressource) {
        this.affectationDemandeePar = ressource;
    }

    public Ressource getAffectationValideePar() {
        return affectationValideePar;
    }

    public Affectation affectationValideePar(Ressource ressource) {
        this.affectationValideePar = ressource;
        return this;
    }

    public void setAffectationValideePar(Ressource ressource) {
        this.affectationValideePar = ressource;
    }

    public Ressource getRessourceAffectee() {
        return ressourceAffectee;
    }

    public Affectation ressourceAffectee(Ressource ressource) {
        this.ressourceAffectee = ressource;
        return this;
    }

    public void setRessourceAffectee(Ressource ressource) {
        this.ressourceAffectee = ressource;
    }

    public Livrable getLivrable() {
        return livrable;
    }

    public Affectation livrable(Livrable livrable) {
        this.livrable = livrable;
        return this;
    }

    public void setLivrable(Livrable livrable) {
        this.livrable = livrable;
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
        Affectation affectation = (Affectation) o;
        if (affectation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), affectation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Affectation{" +
            "id=" + getId() +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", taux=" + getTaux() +
            ", etat='" + getEtat() + "'" +
            "}";
    }
}
