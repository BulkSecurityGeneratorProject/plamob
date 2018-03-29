import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlamobSharedModule } from '../../shared';
import {
    MissionService,
    MissionPopupService,
    MissionComponent,
    MissionDetailComponent,
    MissionDialogComponent,
    MissionPopupComponent,
    MissionDeletePopupComponent,
    MissionDeleteDialogComponent,
    missionRoute,
    missionPopupRoute,
    MissionVMComponent,
    MissionNewComponent
} from './';

const ENTITY_STATES = [
    ...missionRoute,
    ...missionPopupRoute,
];

@NgModule({
    imports: [
        PlamobSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        MissionComponent,
        MissionDetailComponent,
        MissionDialogComponent,
        MissionDeleteDialogComponent,
        MissionPopupComponent,
        MissionDeletePopupComponent,
        MissionVMComponent,
        MissionNewComponent
    ],
    entryComponents: [
        MissionComponent,
        MissionDialogComponent,
        MissionPopupComponent,
        MissionDeleteDialogComponent,
        MissionDeletePopupComponent,
        MissionVMComponent,
        MissionNewComponent
    ],
    providers: [
        MissionService,
        MissionPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlamobMissionModule {}
