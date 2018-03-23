import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Livrable } from './livrable.model';
import { LivrablePopupService } from './livrable-popup.service';
import { LivrableService } from './livrable.service';
import { Mission, MissionService } from '../mission';

@Component({
    selector: 'jhi-livrable-dialog',
    templateUrl: './livrable-dialog.component.html'
})
export class LivrableDialogComponent implements OnInit {

    livrable: Livrable;
    isSaving: boolean;

    missions: Mission[];
    dateFinDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private livrableService: LivrableService,
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
        if (this.livrable.id !== undefined) {
            this.subscribeToSaveResponse(
                this.livrableService.update(this.livrable));
        } else {
            this.subscribeToSaveResponse(
                this.livrableService.create(this.livrable));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Livrable>>) {
        result.subscribe((res: HttpResponse<Livrable>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Livrable) {
        this.eventManager.broadcast({ name: 'livrableListModification', content: 'OK'});
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
    selector: 'jhi-livrable-popup',
    template: ''
})
export class LivrablePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private livrablePopupService: LivrablePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.livrablePopupService
                    .open(LivrableDialogComponent as Component, params['id']);
            } else {
                this.livrablePopupService
                    .open(LivrableDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
