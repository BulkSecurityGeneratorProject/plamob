import { BaseEntity } from './../../shared';

export class ResponsabiliteAffectation implements BaseEntity {
    constructor(
        public id?: number,
        public affectationId?: number,
        public ressourceMobileId?: number,
        public responsableRessourceMobileId?: number,
    ) {
    }
}
