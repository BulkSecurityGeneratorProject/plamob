package be.civadis.plamob.service.mapper;

import be.civadis.plamob.domain.*;
import be.civadis.plamob.service.dto.ResponsabiliteAffectationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ResponsabiliteAffectation and its DTO ResponsabiliteAffectationDTO.
 */
@Mapper(componentModel = "spring", uses = {AffectationMapper.class, RessourceMapper.class})
public interface ResponsabiliteAffectationMapper extends EntityMapper<ResponsabiliteAffectationDTO, ResponsabiliteAffectation> {

    @Mapping(source = "affectation.id", target = "affectationId")
    @Mapping(source = "ressourceMobile.id", target = "ressourceMobileId")
    @Mapping(source = "responsableRessourceMobile.id", target = "responsableRessourceMobileId")
    ResponsabiliteAffectationDTO toDto(ResponsabiliteAffectation responsabiliteAffectation);

    @Mapping(source = "affectationId", target = "affectation")
    @Mapping(source = "ressourceMobileId", target = "ressourceMobile")
    @Mapping(source = "responsableRessourceMobileId", target = "responsableRessourceMobile")
    ResponsabiliteAffectation toEntity(ResponsabiliteAffectationDTO responsabiliteAffectationDTO);

    default ResponsabiliteAffectation fromId(Long id) {
        if (id == null) {
            return null;
        }
        ResponsabiliteAffectation responsabiliteAffectation = new ResponsabiliteAffectation();
        responsabiliteAffectation.setId(id);
        return responsabiliteAffectation;
    }
}
