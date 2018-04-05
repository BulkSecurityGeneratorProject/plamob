package be.civadis.plamob.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Domaine.
 */
@Entity
@Table(name = "domaine")
public class Domaine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @ManyToOne
    @JoinColumn(name = "responsable_id")
    private Ressource responsable;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public Domaine libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Ressource getResponsable() {
        return responsable;
    }

    public Domaine responsable(Ressource ressource) {
        this.responsable = ressource;
        return this;
    }

    public void setResponsable(Ressource ressource) {
        this.responsable = ressource;
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
        Domaine domaine = (Domaine) o;
        if (domaine.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), domaine.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Domaine{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
