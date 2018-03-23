package be.civadis.plamob.service.mapper;

import be.civadis.plamob.domain.*;
import be.civadis.plamob.service.dto.RessourceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Ressource and its DTO RessourceDTO.
 */
@Mapper(componentModel = "spring", uses = {ProfilMapper.class})
public interface RessourceMapper extends EntityMapper<RessourceDTO, Ressource> {

    @Mapping(source = "profil.id", target = "profilId")
    RessourceDTO toDto(Ressource ressource);

    @Mapping(source = "profilId", target = "profil")
    Ressource toEntity(RessourceDTO ressourceDTO);

    default Ressource fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ressource ressource = new Ressource();
        ressource.setId(id);
        return ressource;
    }
}
