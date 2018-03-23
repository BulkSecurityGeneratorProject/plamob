package be.civadis.plamob.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A JournalSuivi.
 */
@Entity
@Table(name = "journal_suivi")
public class JournalSuivi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "jhi_date")
    private LocalDate date;

    @Column(name = "evenement")
    private String evenement;

    @ManyToOne
    private Mission mission;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public JournalSuivi date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEvenement() {
        return evenement;
    }

    public JournalSuivi evenement(String evenement) {
        this.evenement = evenement;
        return this;
    }

    public void setEvenement(String evenement) {
        this.evenement = evenement;
    }

    public Mission getMission() {
        return mission;
    }

    public JournalSuivi mission(Mission mission) {
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
        JournalSuivi journalSuivi = (JournalSuivi) o;
        if (journalSuivi.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), journalSuivi.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JournalSuivi{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", evenement='" + getEvenement() + "'" +
            "}";
    }
}
