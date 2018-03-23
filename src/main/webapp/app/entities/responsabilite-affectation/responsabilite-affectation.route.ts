import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ResponsabiliteAffectationComponent } from './responsabilite-affectation.component';
import { ResponsabiliteAffectationDetailComponent } from './responsabilite-affectation-detail.component';
import { ResponsabiliteAffectationPopupComponent } from './responsabilite-affectation-dialog.component';
import { ResponsabiliteAffectationDeletePopupComponent } from './responsabilite-affectation-delete-dialog.component';

export const responsabiliteAffectationRoute: Routes = [
    {
        path: 'responsabilite-affectation',
        component: ResponsabiliteAffectationComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.responsabiliteAffectation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'responsabilite-affectation/:id',
        component: ResponsabiliteAffectationDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.responsabiliteAffectation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const responsabiliteAffectationPopupRoute: Routes = [
    {
        path: 'responsabilite-affectation-new',
        component: ResponsabiliteAffectationPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.responsabiliteAffectation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'responsabilite-affectation/:id/edit',
        component: ResponsabiliteAffectationPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.responsabiliteAffectation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'responsabilite-affectation/:id/delete',
        component: ResponsabiliteAffectationDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.responsabiliteAffectation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
