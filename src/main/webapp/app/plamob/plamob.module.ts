import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { PlanningModule } from '../plamob/planning/planning.module';

@NgModule({
    imports: [
        PlanningModule,
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlamobModule {
    
}
