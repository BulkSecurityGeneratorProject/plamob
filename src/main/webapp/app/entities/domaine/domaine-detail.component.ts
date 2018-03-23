import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Domaine } from './domaine.model';
import { DomaineService } from './domaine.service';

@Component({
    selector: 'jhi-domaine-detail',
    templateUrl: './domaine-detail.component.html'
})
export class DomaineDetailComponent implements OnInit, OnDestroy {

    domaine: Domaine;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private domaineService: DomaineService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDomaines();
    }

    load(id) {
        this.domaineService.find(id)
            .subscribe((domaineResponse: HttpResponse<Domaine>) => {
                this.domaine = domaineResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInDomaines() {
        this.eventSubscriber = this.eventManager.subscribe(
            'domaineListModification',
            (response) => this.load(this.domaine.id)
        );
    }
}
