import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlamobSharedModule } from '../../shared';
import {
    DomaineService,
    DomainePopupService,
    DomaineComponent,
    DomaineDetailComponent,
    DomaineDialogComponent,
    DomainePopupComponent,
    DomaineDeletePopupComponent,
    DomaineDeleteDialogComponent,
    domaineRoute,
    domainePopupRoute,
} from './';

const ENTITY_STATES = [
    ...domaineRoute,
    ...domainePopupRoute,
];

@NgModule({
    imports: [
        PlamobSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        DomaineComponent,
        DomaineDetailComponent,
        DomaineDialogComponent,
        DomaineDeleteDialogComponent,
        DomainePopupComponent,
        DomaineDeletePopupComponent,
    ],
    entryComponents: [
        DomaineComponent,
        DomaineDialogComponent,
        DomainePopupComponent,
        DomaineDeleteDialogComponent,
        DomaineDeletePopupComponent,
    ],
    providers: [
        DomaineService,
        DomainePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlamobDomaineModule {}
