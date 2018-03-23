import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Ressource } from './ressource.model';
import { RessourceService } from './ressource.service';

@Component({
    selector: 'jhi-ressource-detail',
    templateUrl: './ressource-detail.component.html'
})
export class RessourceDetailComponent implements OnInit, OnDestroy {

    ressource: Ressource;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private ressourceService: RessourceService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInRessources();
    }

    load(id) {
        this.ressourceService.find(id)
            .subscribe((ressourceResponse: HttpResponse<Ressource>) => {
                this.ressource = ressourceResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInRessources() {
        this.eventSubscriber = this.eventManager.subscribe(
            'ressourceListModification',
            (response) => this.load(this.ressource.id)
        );
    }
}
