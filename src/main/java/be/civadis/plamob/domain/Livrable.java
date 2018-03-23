package be.civadis.plamob.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Livrable.
 */
@Entity
@Table(name = "livrable")
public class Livrable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "num_seq")
    private Long numSeq;

    @Column(name = "description")
    private String description;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "remarques")
    private String remarques;

    @ManyToOne
    private Mission mission;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumSeq() {
        return numSeq;
    }

    public Livrable numSeq(Long numSeq) {
        this.numSeq = numSeq;
        return this;
    }

    public void setNumSeq(Long numSeq) {
        this.numSeq = numSeq;
    }

    public String getDescription() {
        return description;
    }

    public Livrable description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public Livrable dateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getRemarques() {
        return remarques;
    }

    public Livrable remarques(String remarques) {
        this.remarques = remarques;
        return this;
    }

    public void setRemarques(String remarques) {
        this.remarques = remarques;
    }

    public Mission getMission() {
        return mission;
    }

    public Livrable mission(Mission mission) {
        this.mission = mission;
        return this;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
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
        Livrable livrable = (Livrable) o;
        if (livrable.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), livrable.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Livrable{" +
            "id=" + getId() +
            ", numSeq=" + getNumSeq() +
            ", description='" + getDescription() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", remarques='" + getRemarques() + "'" +
            "}";
    }
}
