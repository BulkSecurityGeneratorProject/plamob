import { BaseEntity } from './../../shared';

export const enum ETAT_AFFECTATION {
    'ATTENTE_VALIDATION',
    'VALIDEE'
}

export class Affectation implements BaseEntity {
    constructor(
        public id?: number,
        public dateDebut?: any,
        public dateFin?: any,
        public taux?: number,
        public etat?: ETAT_AFFECTATION,
        public profilId?: number,
        public affectationDemandeeParId?: number,
        public affectationValideeParId?: number,
        public ressourceAffecteeId?: number,
        public livrableId?: number,
    ) {
    }
}
