import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlanningComponent } from './planning.component';
import { planningRoute } from './planning.route';
import { PlanningService } from './planning.service';

@NgModule({
    imports: [
        //PlamobSharedModule,
        RouterModule.forChild(planningRoute)
    ],
    declarations: [
        PlanningComponent
    ],
    entryComponents: [
        PlanningComponent
    ],
    providers: [
        PlanningService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]

})
export class PlanningModule {

}