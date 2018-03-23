package be.civadis.plamob.service.mapper;

import be.civadis.plamob.domain.*;
import be.civadis.plamob.service.dto.ProfilDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Profil and its DTO ProfilDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProfilMapper extends EntityMapper<ProfilDTO, Profil> {



    default Profil fromId(Long id) {
        if (id == null) {
            return null;
        }
        Profil profil = new Profil();
        profil.setId(id);
        return profil;
    }
}
