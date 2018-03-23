import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ResponsabiliteAffectation } from './responsabilite-affectation.model';
import { ResponsabiliteAffectationPopupService } from './responsabilite-affectation-popup.service';
import { ResponsabiliteAffectationService } from './responsabilite-affectation.service';

@Component({
    selector: 'jhi-responsabilite-affectation-delete-dialog',
    templateUrl: './responsabilite-affectation-delete-dialog.component.html'
})
export class ResponsabiliteAffectationDeleteDialogComponent {

    responsabiliteAffectation: ResponsabiliteAffectation;

    constructor(
        private responsabiliteAffectationService: ResponsabiliteAffectationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.responsabiliteAffectationService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'responsabiliteAffectationListModification',
                content: 'Deleted an responsabiliteAffectation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-responsabilite-affectation-delete-popup',
    template: ''
})
export class ResponsabiliteAffectationDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private responsabiliteAffectationPopupService: ResponsabiliteAffectationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.responsabiliteAffectationPopupService
                .open(ResponsabiliteAffectationDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
