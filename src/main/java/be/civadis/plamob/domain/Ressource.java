package be.civadis.plamob.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import be.civadis.plamob.domain.enumeration.TYPE_RESSOURCE;

/**
 * A Ressource.
 */
@Entity
@Table(name = "ressource")
public class Ressource implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "trigramme")
    private String trigramme;

    @Column(name = "tel")
    private String tel;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_ress")
    private TYPE_RESSOURCE typeRess;

    @OneToOne
    @JoinColumn(unique = true)
    private Profil profil;

    @OneToOne
    @MapsId
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrigramme() {
        return trigramme;
    }

    public Ressource trigramme(String trigramme) {
        this.trigramme = trigramme;
        return this;
    }

    public void setTrigramme(String trigramme) {
        this.trigramme = trigramme;
    }

    public String getTel() {
        return tel;
    }

    public Ressource tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public TYPE_RESSOURCE getTypeRess() {
        return typeRess;
    }

    public Ressource typeRess(TYPE_RESSOURCE typeRess) {
        this.typeRess = typeRess;
        return this;
    }

    public void setTypeRess(TYPE_RESSOURCE typeRess) {
        this.typeRess = typeRess;
    }

    public Profil getProfil() {
        return profil;
    }

    public Ressource profil(Profil profil) {
        this.profil = profil;
        return this;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        Ressource ressource = (Ressource) o;
        if (ressource.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ressource.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Ressource{" +
            "id=" + getId() +
            ", trigramme='" + getTrigramme() + "'" +
            ", tel='" + getTel() + "'" +
            ", typeRess='" + getTypeRess() + "'" +
            "}";
    }
}
