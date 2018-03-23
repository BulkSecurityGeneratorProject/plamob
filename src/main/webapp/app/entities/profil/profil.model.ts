import { BaseEntity } from './../../shared';

export class Profil implements BaseEntity {
    constructor(
        public id?: number,
        public description?: string,
    ) {
    }
}
