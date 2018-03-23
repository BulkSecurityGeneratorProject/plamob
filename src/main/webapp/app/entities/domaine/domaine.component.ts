import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Domaine } from './domaine.model';
import { DomaineService } from './domaine.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-domaine',
    templateUrl: './domaine.component.html'
})
export class DomaineComponent implements OnInit, OnDestroy {
domaines: Domaine[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private domaineService: DomaineService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.domaineService.query().subscribe(
            (res: HttpResponse<Domaine[]>) => {
                this.domaines = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInDomaines();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Domaine) {
        return item.id;
    }
    registerChangeInDomaines() {
        this.eventSubscriber = this.eventManager.subscribe('domaineListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
