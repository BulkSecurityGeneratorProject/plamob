import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Ressource } from './ressource.model';
import { RessourceService } from './ressource.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-ressource',
    templateUrl: './ressource.component.html'
})
export class RessourceComponent implements OnInit, OnDestroy {
ressources: Ressource[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private ressourceService: RessourceService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.ressourceService.query().subscribe(
            (res: HttpResponse<Ressource[]>) => {
                this.ressources = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInRessources();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Ressource) {
        return item.id;
    }
    registerChangeInRessources() {
        this.eventSubscriber = this.eventManager.subscribe('ressourceListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
