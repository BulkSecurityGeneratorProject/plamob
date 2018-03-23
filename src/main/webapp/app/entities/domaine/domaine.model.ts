import { BaseEntity } from './../../shared';

export class Domaine implements BaseEntity {
    constructor(
        public id?: number,
        public libelle?: string,
        public responsableId?: number,
    ) {
    }
}
