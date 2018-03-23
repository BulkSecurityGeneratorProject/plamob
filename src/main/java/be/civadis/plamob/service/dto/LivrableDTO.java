package be.civadis.plamob.service.dto;


import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Livrable entity.
 */
public class LivrableDTO implements Serializable {

    private Long id;

    private Long numSeq;

    private String description;

    private LocalDate dateFin;

    private String remarques;

    private Long missionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumSeq() {
        return numSeq;
    }

    public void setNumSeq(Long numSeq) {
        this.numSeq = numSeq;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getRemarques() {
        return remarques;
    }

    public void setRemarques(String remarques) {
        this.remarques = remarques;
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

        LivrableDTO livrableDTO = (LivrableDTO) o;
        if(livrableDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), livrableDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LivrableDTO{" +
            "id=" + getId() +
            ", numSeq=" + getNumSeq() +
            ", description='" + getDescription() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", remarques='" + getRemarques() + "'" +
            "}";
    }
}
