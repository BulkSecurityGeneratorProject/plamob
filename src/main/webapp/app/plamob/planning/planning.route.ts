import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PlanningComponent } from './planning.component';

export const planningRoute: Routes = [
    {
        path: 'planning',
        component: PlanningComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'planning'
        },
        canActivate: [UserRouteAccessService]
    }
];
