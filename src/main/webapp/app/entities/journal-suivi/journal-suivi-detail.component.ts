import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { JournalSuivi } from './journal-suivi.model';
import { JournalSuiviService } from './journal-suivi.service';

@Component({
    selector: 'jhi-journal-suivi-detail',
    templateUrl: './journal-suivi-detail.component.html'
})
export class JournalSuiviDetailComponent implements OnInit, OnDestroy {

    journalSuivi: JournalSuivi;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private journalSuiviService: JournalSuiviService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInJournalSuivis();
    }

    load(id) {
        this.journalSuiviService.find(id)
            .subscribe((journalSuiviResponse: HttpResponse<JournalSuivi>) => {
                this.journalSuivi = journalSuiviResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInJournalSuivis() {
        this.eventSubscriber = this.eventManager.subscribe(
            'journalSuiviListModification',
            (response) => this.load(this.journalSuivi.id)
        );
    }
}
