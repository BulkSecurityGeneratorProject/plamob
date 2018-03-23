import { BaseEntity } from './../../shared';

export class Livrable implements BaseEntity {
    constructor(
        public id?: number,
        public numSeq?: number,
        public description?: string,
        public dateFin?: any,
        public remarques?: string,
        public missionId?: number,
    ) {
    }
}
