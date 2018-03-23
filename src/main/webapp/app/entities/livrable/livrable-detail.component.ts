import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Livrable } from './livrable.model';
import { LivrableService } from './livrable.service';

@Component({
    selector: 'jhi-livrable-detail',
    templateUrl: './livrable-detail.component.html'
})
export class LivrableDetailComponent implements OnInit, OnDestroy {

    livrable: Livrable;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private livrableService: LivrableService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInLivrables();
    }

    load(id) {
        this.livrableService.find(id)
            .subscribe((livrableResponse: HttpResponse<Livrable>) => {
                this.livrable = livrableResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInLivrables() {
        this.eventSubscriber = this.eventManager.subscribe(
            'livrableListModification',
            (response) => this.load(this.livrable.id)
        );
    }
}
