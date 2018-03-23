package be.civadis.plamob.service.mapper;

import be.civadis.plamob.domain.*;
import be.civadis.plamob.service.dto.LivrableDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Livrable and its DTO LivrableDTO.
 */
@Mapper(componentModel = "spring", uses = {MissionMapper.class})
public interface LivrableMapper extends EntityMapper<LivrableDTO, Livrable> {

    @Mapping(source = "mission.id", target = "missionId")
    LivrableDTO toDto(Livrable livrable);

    @Mapping(source = "missionId", target = "mission")
    Livrable toEntity(LivrableDTO livrableDTO);

    default Livrable fromId(Long id) {
        if (id == null) {
            return null;
        }
        Livrable livrable = new Livrable();
        livrable.setId(id);
        return livrable;
    }
}
