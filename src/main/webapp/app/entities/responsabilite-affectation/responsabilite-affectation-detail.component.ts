import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { ResponsabiliteAffectation } from './responsabilite-affectation.model';
import { ResponsabiliteAffectationService } from './responsabilite-affectation.service';

@Component({
    selector: 'jhi-responsabilite-affectation-detail',
    templateUrl: './responsabilite-affectation-detail.component.html'
})
export class ResponsabiliteAffectationDetailComponent implements OnInit, OnDestroy {

    responsabiliteAffectation: ResponsabiliteAffectation;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private responsabiliteAffectationService: ResponsabiliteAffectationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInResponsabiliteAffectations();
    }

    load(id) {
        this.responsabiliteAffectationService.find(id)
            .subscribe((responsabiliteAffectationResponse: HttpResponse<ResponsabiliteAffectation>) => {
                this.responsabiliteAffectation = responsabiliteAffectationResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInResponsabiliteAffectations() {
        this.eventSubscriber = this.eventManager.subscribe(
            'responsabiliteAffectationListModification',
            (response) => this.load(this.responsabiliteAffectation.id)
        );
    }
}
