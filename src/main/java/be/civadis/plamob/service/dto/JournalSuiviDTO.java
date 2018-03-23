package be.civadis.plamob.service.dto;


import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the JournalSuivi entity.
 */
public class JournalSuiviDTO implements Serializable {

    private Long id;

    private LocalDate date;

    private String evenement;

    private Long missionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEvenement() {
        return evenement;
    }

    public void setEvenement(String evenement) {
        this.evenement = evenement;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JournalSuiviDTO journalSuiviDTO = (JournalSuiviDTO) o;
        if(journalSuiviDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), journalSuiviDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JournalSuiviDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", evenement='" + getEvenement() + "'" +
            "}";
    }
}
