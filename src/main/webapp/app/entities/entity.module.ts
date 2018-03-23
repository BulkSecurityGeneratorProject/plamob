import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { PlamobDomaineModule } from './domaine/domaine.module';
import { PlamobMissionModule } from './mission/mission.module';
import { PlamobLivrableModule } from './livrable/livrable.module';
import { PlamobJournalSuiviModule } from './journal-suivi/journal-suivi.module';
import { PlamobAffectationModule } from './affectation/affectation.module';
import { PlamobRessourceModule } from './ressource/ressource.module';
import { PlamobProfilModule } from './profil/profil.module';
import { PlamobResponsabiliteAffectationModule } from './responsabilite-affectation/responsabilite-affectation.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        PlamobDomaineModule,
        PlamobMissionModule,
        PlamobLivrableModule,
        PlamobJournalSuiviModule,
        PlamobAffectationModule,
        PlamobRessourceModule,
        PlamobProfilModule,
        PlamobResponsabiliteAffectationModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlamobEntityModule {}
