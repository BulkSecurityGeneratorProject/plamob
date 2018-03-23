import { BaseEntity } from './../../shared';

export class JournalSuivi implements BaseEntity {
    constructor(
        public id?: number,
        public date?: any,
        public evenement?: string,
        public missionId?: number,
    ) {
    }
}
