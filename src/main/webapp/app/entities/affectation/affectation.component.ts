import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Affectation } from './affectation.model';
import { AffectationService } from './affectation.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-affectation',
    templateUrl: './affectation.component.html'
})
export class AffectationComponent implements OnInit, OnDestroy {
affectations: Affectation[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private affectationService: AffectationService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.affectationService.query().subscribe(
            (res: HttpResponse<Affectation[]>) => {
                this.affectations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInAffectations();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Affectation) {
        return item.id;
    }
    registerChangeInAffectations() {
        this.eventSubscriber = this.eventManager.subscribe('affectationListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
