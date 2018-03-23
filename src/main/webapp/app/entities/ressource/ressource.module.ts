import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlamobSharedModule } from '../../shared';
import {
    RessourceService,
    RessourcePopupService,
    RessourceComponent,
    RessourceDetailComponent,
    RessourceDialogComponent,
    RessourcePopupComponent,
    RessourceDeletePopupComponent,
    RessourceDeleteDialogComponent,
    ressourceRoute,
    ressourcePopupRoute,
} from './';

const ENTITY_STATES = [
    ...ressourceRoute,
    ...ressourcePopupRoute,
];

@NgModule({
    imports: [
        PlamobSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        RessourceComponent,
        RessourceDetailComponent,
        RessourceDialogComponent,
        RessourceDeleteDialogComponent,
        RessourcePopupComponent,
        RessourceDeletePopupComponent,
    ],
    entryComponents: [
        RessourceComponent,
        RessourceDialogComponent,
        RessourcePopupComponent,
        RessourceDeleteDialogComponent,
        RessourceDeletePopupComponent,
    ],
    providers: [
        RessourceService,
        RessourcePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlamobRessourceModule {}
