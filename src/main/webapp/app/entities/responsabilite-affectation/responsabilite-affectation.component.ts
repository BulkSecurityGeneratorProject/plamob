import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ResponsabiliteAffectation } from './responsabilite-affectation.model';
import { ResponsabiliteAffectationService } from './responsabilite-affectation.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-responsabilite-affectation',
    templateUrl: './responsabilite-affectation.component.html'
})
export class ResponsabiliteAffectationComponent implements OnInit, OnDestroy {
responsabiliteAffectations: ResponsabiliteAffectation[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private responsabiliteAffectationService: ResponsabiliteAffectationService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.responsabiliteAffectationService.query().subscribe(
            (res: HttpResponse<ResponsabiliteAffectation[]>) => {
                this.responsabiliteAffectations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInResponsabiliteAffectations();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ResponsabiliteAffectation) {
        return item.id;
    }
    registerChangeInResponsabiliteAffectations() {
        this.eventSubscriber = this.eventManager.subscribe('responsabiliteAffectationListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
