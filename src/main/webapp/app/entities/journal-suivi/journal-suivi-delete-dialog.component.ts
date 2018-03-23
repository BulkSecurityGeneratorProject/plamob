import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { JournalSuivi } from './journal-suivi.model';
import { JournalSuiviPopupService } from './journal-suivi-popup.service';
import { JournalSuiviService } from './journal-suivi.service';

@Component({
    selector: 'jhi-journal-suivi-delete-dialog',
    templateUrl: './journal-suivi-delete-dialog.component.html'
})
export class JournalSuiviDeleteDialogComponent {

    journalSuivi: JournalSuivi;

    constructor(
        private journalSuiviService: JournalSuiviService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.journalSuiviService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'journalSuiviListModification',
                content: 'Deleted an journalSuivi'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-journal-suivi-delete-popup',
    template: ''
})
export class JournalSuiviDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private journalSuiviPopupService: JournalSuiviPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.journalSuiviPopupService
                .open(JournalSuiviDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
