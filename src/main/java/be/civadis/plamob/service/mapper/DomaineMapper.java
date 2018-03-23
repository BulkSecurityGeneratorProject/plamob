package be.civadis.plamob.service.mapper;

import be.civadis.plamob.domain.*;
import be.civadis.plamob.service.dto.DomaineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Domaine and its DTO DomaineDTO.
 */
@Mapper(componentModel = "spring", uses = {RessourceMapper.class})
public interface DomaineMapper extends EntityMapper<DomaineDTO, Domaine> {

    @Mapping(source = "responsable.id", target = "responsableId")
    DomaineDTO toDto(Domaine domaine);

    @Mapping(source = "responsableId", target = "responsable")
    Domaine toEntity(DomaineDTO domaineDTO);

    default Domaine fromId(Long id) {
        if (id == null) {
            return null;
        }
        Domaine domaine = new Domaine();
        domaine.setId(id);
        return domaine;
    }
}
