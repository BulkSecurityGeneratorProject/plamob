import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JournalSuiviComponent } from './journal-suivi.component';
import { JournalSuiviDetailComponent } from './journal-suivi-detail.component';
import { JournalSuiviPopupComponent } from './journal-suivi-dialog.component';
import { JournalSuiviDeletePopupComponent } from './journal-suivi-delete-dialog.component';

export const journalSuiviRoute: Routes = [
    {
        path: 'journal-suivi',
        component: JournalSuiviComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.journalSuivi.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'journal-suivi/:id',
        component: JournalSuiviDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.journalSuivi.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const journalSuiviPopupRoute: Routes = [
    {
        path: 'journal-suivi-new',
        component: JournalSuiviPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.journalSuivi.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'journal-suivi/:id/edit',
        component: JournalSuiviPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.journalSuivi.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'journal-suivi/:id/delete',
        component: JournalSuiviDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plamobApp.journalSuivi.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
