import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Domaine } from './domaine.model';
import { DomainePopupService } from './domaine-popup.service';
import { DomaineService } from './domaine.service';

@Component({
    selector: 'jhi-domaine-delete-dialog',
    templateUrl: './domaine-delete-dialog.component.html'
})
export class DomaineDeleteDialogComponent {

    domaine: Domaine;

    constructor(
        private domaineService: DomaineService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.domaineService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'domaineListModification',
                content: 'Deleted an domaine'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-domaine-delete-popup',
    template: ''
})
export class DomaineDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private domainePopupService: DomainePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.domainePopupService
                .open(DomaineDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
