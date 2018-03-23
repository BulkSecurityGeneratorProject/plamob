import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlamobSharedModule } from '../../shared';
import {
    JournalSuiviService,
    JournalSuiviPopupService,
    JournalSuiviComponent,
    JournalSuiviDetailComponent,
    JournalSuiviDialogComponent,
    JournalSuiviPopupComponent,
    JournalSuiviDeletePopupComponent,
    JournalSuiviDeleteDialogComponent,
    journalSuiviRoute,
    journalSuiviPopupRoute,
} from './';

const ENTITY_STATES = [
    ...journalSuiviRoute,
    ...journalSuiviPopupRoute,
];

@NgModule({
    imports: [
        PlamobSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        JournalSuiviComponent,
        JournalSuiviDetailComponent,
        JournalSuiviDialogComponent,
        JournalSuiviDeleteDialogComponent,
        JournalSuiviPopupComponent,
        JournalSuiviDeletePopupComponent,
    ],
    entryComponents: [
        JournalSuiviComponent,
        JournalSuiviDialogComponent,
        JournalSuiviPopupComponent,
        JournalSuiviDeleteDialogComponent,
        JournalSuiviDeletePopupComponent,
    ],
    providers: [
        JournalSuiviService,
        JournalSuiviPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlamobJournalSuiviModule {}
