import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { LivrableComponent } from './livrable.component';
import { LivrableDetailComponent } from './livrable-detail.component';
import { LivrablePopupComponent } from './livrable-dialog.component';
import { LivrableDeletePopupComponent } from './livrable-delete-dialog.component';

export const livrableRoute: Routes = [
    {
        path: 'livrable',
        component: LivrableComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.livrable.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'livrable/:id',
        component: LivrableDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.livrable.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const livrablePopupRoute: Routes = [
    {
        path: 'livrable-new',
        component: LivrablePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.livrable.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'livrable/:id/edit',
        component: LivrablePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.livrable.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'livrable/:id/delete',
        component: LivrableDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.livrable.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
