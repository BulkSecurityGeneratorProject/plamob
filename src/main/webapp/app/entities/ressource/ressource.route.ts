import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { RessourceComponent } from './ressource.component';
import { RessourceDetailComponent } from './ressource-detail.component';
import { RessourcePopupComponent } from './ressource-dialog.component';
import { RessourceDeletePopupComponent } from './ressource-delete-dialog.component';

export const ressourceRoute: Routes = [
    {
        path: 'ressource',
        component: RessourceComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.ressource.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'ressource/:id',
        component: RessourceDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.ressource.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ressourcePopupRoute: Routes = [
    {
        path: 'ressource-new',
        component: RessourcePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.ressource.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'ressource/:id/edit',
        component: RessourcePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.ressource.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'ressource/:id/delete',
        component: RessourceDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.ressource.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
