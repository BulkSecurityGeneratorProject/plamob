import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlamobSharedModule } from '../../shared';
import {
    LivrableService,
    LivrablePopupService,
    LivrableComponent,
    LivrableDetailComponent,
    LivrableDialogComponent,
    LivrablePopupComponent,
    LivrableDeletePopupComponent,
    LivrableDeleteDialogComponent,
    livrableRoute,
    livrablePopupRoute,
} from './';

const ENTITY_STATES = [
    ...livrableRoute,
    ...livrablePopupRoute,
];

@NgModule({
    imports: [
        PlamobSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        LivrableComponent,
        LivrableDetailComponent,
        LivrableDialogComponent,
        LivrableDeleteDialogComponent,
        LivrablePopupComponent,
        LivrableDeletePopupComponent,
    ],
    entryComponents: [
        LivrableComponent,
        LivrableDialogComponent,
        LivrablePopupComponent,
        LivrableDeleteDialogComponent,
        LivrableDeletePopupComponent,
    ],
    providers: [
        LivrableService,
        LivrablePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlamobLivrableModule {}
