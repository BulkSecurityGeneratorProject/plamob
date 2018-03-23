import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { DomaineComponent } from './domaine.component';
import { DomaineDetailComponent } from './domaine-detail.component';
import { DomainePopupComponent } from './domaine-dialog.component';
import { DomaineDeletePopupComponent } from './domaine-delete-dialog.component';

export const domaineRoute: Routes = [
    {
        path: 'domaine',
        component: DomaineComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.domaine.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'domaine/:id',
        component: DomaineDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.domaine.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const domainePopupRoute: Routes = [
    {
        path: 'domaine-new',
        component: DomainePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.domaine.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'domaine/:id/edit',
        component: DomainePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.domaine.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'domaine/:id/delete',
        component: DomaineDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.domaine.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
