package be.civadis.plamob.service.mapper;

import be.civadis.plamob.domain.*;
import be.civadis.plamob.service.dto.AffectationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Affectation and its DTO AffectationDTO.
 */
@Mapper(componentModel = "spring", uses = {ProfilMapper.class, RessourceMapper.class, LivrableMapper.class})
public interface AffectationMapper extends EntityMapper<AffectationDTO, Affectation> {

    @Mapping(source = "profil.id", target = "profilId")
    @Mapping(source = "affectationDemandeePar.id", target = "affectationDemandeeParId")
    @Mapping(source = "affectationValideePar.id", target = "affectationValideeParId")
    @Mapping(source = "ressourceAffectee.id", target = "ressourceAffecteeId")
    @Mapping(source = "livrable.id", target = "livrableId")
    AffectationDTO toDto(Affectation affectation);

    @Mapping(source = "profilId", target = "profil")
    @Mapping(source = "affectationDemandeeParId", target = "affectationDemandeePar")
    @Mapping(source = "affectationValideeParId", target = "affectationValideePar")
    @Mapping(source = "ressourceAffecteeId", target = "ressourceAffectee")
    @Mapping(source = "livrableId", target = "livrable")
    Affectation toEntity(AffectationDTO affectationDTO);

    default Affectation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Affectation affectation = new Affectation();
        affectation.setId(id);
        return affectation;
    }
}
