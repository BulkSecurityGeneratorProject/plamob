import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { MissionComponent } from './mission.component';
import { MissionDetailComponent } from './mission-detail.component';
import { MissionPopupComponent } from './mission-dialog.component';
import { MissionDeletePopupComponent } from './mission-delete-dialog.component';

import { MissionVMComponent } from './mission-vm.component';
import { MissionNewComponent } from './mission-new.component';

export const missionRoute: Routes = [
    {
        path: 'mission',
        component: MissionComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.mission.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'mission/:id',
        component: MissionDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.mission.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'fiche-mission',
        component: MissionVMComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.mission.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'fiche-mission'
    },
    {
        path: 'nouvelle-mission',
        component: MissionNewComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.mission.home.title'
        },
        canActivate: [UserRouteAccessService]
    }

];

export const missionPopupRoute: Routes = [
    {
        path: 'mission-new',
        component: MissionPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.mission.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'mission/:id/edit',
        component: MissionPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.mission.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'mission/:id/delete',
        component: MissionDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.mission.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
