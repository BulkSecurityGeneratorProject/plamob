import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlamobSharedModule } from '../../shared';
import {
    ResponsabiliteAffectationService,
    ResponsabiliteAffectationPopupService,
    ResponsabiliteAffectationComponent,
    ResponsabiliteAffectationDetailComponent,
    ResponsabiliteAffectationDialogComponent,
    ResponsabiliteAffectationPopupComponent,
    ResponsabiliteAffectationDeletePopupComponent,
    ResponsabiliteAffectationDeleteDialogComponent,
    responsabiliteAffectationRoute,
    responsabiliteAffectationPopupRoute,
} from './';

const ENTITY_STATES = [
    ...responsabiliteAffectationRoute,
    ...responsabiliteAffectationPopupRoute,
];

@NgModule({
    imports: [
        PlamobSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ResponsabiliteAffectationComponent,
        ResponsabiliteAffectationDetailComponent,
        ResponsabiliteAffectationDialogComponent,
        ResponsabiliteAffectationDeleteDialogComponent,
        ResponsabiliteAffectationPopupComponent,
        ResponsabiliteAffectationDeletePopupComponent,
    ],
    entryComponents: [
        ResponsabiliteAffectationComponent,
        ResponsabiliteAffectationDialogComponent,
        ResponsabiliteAffectationPopupComponent,
        ResponsabiliteAffectationDeleteDialogComponent,
        ResponsabiliteAffectationDeletePopupComponent,
    ],
    providers: [
        ResponsabiliteAffectationService,
        ResponsabiliteAffectationPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlamobResponsabiliteAffectationModule {}
