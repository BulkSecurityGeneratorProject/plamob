package be.civadis.plamob.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ResponsabiliteAffectation.
 */
@Entity
@Table(name = "responsabilite_affectation")
public class ResponsabiliteAffectation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    private Affectation affectation;

    @ManyToOne
    private Ressource ressourceMobile;

    @ManyToOne
    private Ressource responsableRessourceMobile;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Affectation getAffectation() {
        return affectation;
    }

    public ResponsabiliteAffectation affectation(Affectation affectation) {
        this.affectation = affectation;
        return this;
    }

    public void setAffectation(Affectation affectation) {
        this.affectation = affectation;
    }

    public Ressource getRessourceMobile() {
        return ressourceMobile;
    }

    public ResponsabiliteAffectation ressourceMobile(Ressource ressource) {
        this.ressourceMobile = ressource;
        return this;
    }

    public void setRessourceMobile(Ressource ressource) {
        this.ressourceMobile = ressource;
    }

    public Ressource getResponsableRessourceMobile() {
        return responsableRessourceMobile;
    }

    public ResponsabiliteAffectation responsableRessourceMobile(Ressource ressource) {
        this.responsableRessourceMobile = ressource;
        return this;
    }

    public void setResponsableRessourceMobile(Ressource ressource) {
        this.responsableRessourceMobile = ressource;
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
        ResponsabiliteAffectation responsabiliteAffectation = (ResponsabiliteAffectation) o;
        if (responsabiliteAffectation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), responsabiliteAffectation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResponsabiliteAffectation{" +
            "id=" + getId() +
            "}";
    }
}
