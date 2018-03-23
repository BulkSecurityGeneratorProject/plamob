import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { JournalSuivi } from './journal-suivi.model';
import { JournalSuiviService } from './journal-suivi.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-journal-suivi',
    templateUrl: './journal-suivi.component.html'
})
export class JournalSuiviComponent implements OnInit, OnDestroy {
journalSuivis: JournalSuivi[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private journalSuiviService: JournalSuiviService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.journalSuiviService.query().subscribe(
            (res: HttpResponse<JournalSuivi[]>) => {
                this.journalSuivis = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInJournalSuivis();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: JournalSuivi) {
        return item.id;
    }
    registerChangeInJournalSuivis() {
        this.eventSubscriber = this.eventManager.subscribe('journalSuiviListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
