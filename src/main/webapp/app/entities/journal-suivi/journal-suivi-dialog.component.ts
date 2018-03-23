import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { JournalSuivi } from './journal-suivi.model';
import { JournalSuiviPopupService } from './journal-suivi-popup.service';
import { JournalSuiviService } from './journal-suivi.service';
import { Mission, MissionService } from '../mission';

@Component({
    selector: 'jhi-journal-suivi-dialog',
    templateUrl: './journal-suivi-dialog.component.html'
})
export class JournalSuiviDialogComponent implements OnInit {

    journalSuivi: JournalSuivi;
    isSaving: boolean;

    missions: Mission[];
    dateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private journalSuiviService: JournalSuiviService,
        private missionService: MissionService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.missionService.query()
            .subscribe((res: HttpResponse<Mission[]>) => { this.missions = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.journalSuivi.id !== undefined) {
            this.subscribeToSaveResponse(
                this.journalSuiviService.update(this.journalSuivi));
        } else {
            this.subscribeToSaveResponse(
                this.journalSuiviService.create(this.journalSuivi));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<JournalSuivi>>) {
        result.subscribe((res: HttpResponse<JournalSuivi>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: JournalSuivi) {
        this.eventManager.broadcast({ name: 'journalSuiviListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackMissionById(index: number, item: Mission) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-journal-suivi-popup',
    template: ''
})
export class JournalSuiviPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private journalSuiviPopupService: JournalSuiviPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.journalSuiviPopupService
                    .open(JournalSuiviDialogComponent as Component, params['id']);
            } else {
                this.journalSuiviPopupService
                    .open(JournalSuiviDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
