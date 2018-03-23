import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Livrable } from './livrable.model';
import { LivrablePopupService } from './livrable-popup.service';
import { LivrableService } from './livrable.service';

@Component({
    selector: 'jhi-livrable-delete-dialog',
    templateUrl: './livrable-delete-dialog.component.html'
})
export class LivrableDeleteDialogComponent {

    livrable: Livrable;

    constructor(
        private livrableService: LivrableService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.livrableService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'livrableListModification',
                content: 'Deleted an livrable'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-livrable-delete-popup',
    template: ''
})
export class LivrableDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private livrablePopupService: LivrablePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.livrablePopupService
                .open(LivrableDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
