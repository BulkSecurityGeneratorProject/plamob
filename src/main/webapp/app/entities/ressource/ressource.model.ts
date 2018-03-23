import { BaseEntity } from './../../shared';

export const enum TYPE_RESSOURCE {
    'MOB',
    'DOM'
}

export class Ressource implements BaseEntity {
    constructor(
        public id?: number,
        public trigramme?: string,
        public tel?: string,
        public typeRess?: TYPE_RESSOURCE,
        public profilId?: number,
    ) {
    }
}
