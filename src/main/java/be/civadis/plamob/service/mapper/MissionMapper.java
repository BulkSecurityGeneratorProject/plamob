package be.civadis.plamob.service.mapper;

import be.civadis.plamob.domain.*;
import be.civadis.plamob.service.dto.MissionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Mission and its DTO MissionDTO.
 */
@Mapper(componentModel = "spring", uses = {DomaineMapper.class, RessourceMapper.class})
public interface MissionMapper extends EntityMapper<MissionDTO, Mission> {

    @Mapping(source = "domaineConcerne.id", target = "domaineConcerneId")
    @Mapping(source = "domaineConcerne.libelle", target = "domaineConcerneLibelle")
    @Mapping(source = "missionDefinitPar.id", target = "missionDefinitParId")
    MissionDTO toDto(Mission mission);

    @Mapping(source = "domaineConcerneId", target = "domaineConcerne")
    @Mapping(source = "missionDefinitParId", target = "missionDefinitPar")
    Mission toEntity(MissionDTO missionDTO);

    default Mission fromId(Long id) {
        if (id == null) {
            return null;
        }
        Mission mission = new Mission();
        mission.setId(id);
        return mission;
    }
}
