import { BaseEntity } from './../../shared';

export const enum ETAT_MISSION {
    'EN_ATTENTE',
    'EN_COURS',
    'TERMINEE'
}

export class Mission implements BaseEntity {
    constructor(
        public id?: number,
        public resume?: string,
        public objectif?: string,
        public delai?: string,
        public technologie?: string,
        public autre?: string,
        public etat?: ETAT_MISSION,
        public domaineConcerneLibelle?: string,
        public domaineConcerneId?: number,
        public missionDefinitParId?: number,
    ) {
    }
}
