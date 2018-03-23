import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ResponsabiliteAffectation } from './responsabilite-affectation.model';
import { ResponsabiliteAffectationPopupService } from './responsabilite-affectation-popup.service';
import { ResponsabiliteAffectationService } from './responsabilite-affectation.service';
import { Affectation, AffectationService } from '../affectation';
import { Ressource, RessourceService } from '../ressource';

@Component({
    selector: 'jhi-responsabilite-affectation-dialog',
    templateUrl: './responsabilite-affectation-dialog.component.html'
})
export class ResponsabiliteAffectationDialogComponent implements OnInit {

    responsabiliteAffectation: ResponsabiliteAffectation;
    isSaving: boolean;

    affectations: Affectation[];

    ressources: Ressource[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private responsabiliteAffectationService: ResponsabiliteAffectationService,
        private affectationService: AffectationService,
        private ressourceService: RessourceService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.affectationService.query()
            .subscribe((res: HttpResponse<Affectation[]>) => { this.affectations = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.ressourceService.query()
            .subscribe((res: HttpResponse<Ressource[]>) => { this.ressources = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.responsabiliteAffectation.id !== undefined) {
            this.subscribeToSaveResponse(
                this.responsabiliteAffectationService.update(this.responsabiliteAffectation));
        } else {
            this.subscribeToSaveResponse(
                this.responsabiliteAffectationService.create(this.responsabiliteAffectation));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ResponsabiliteAffectation>>) {
        result.subscribe((res: HttpResponse<ResponsabiliteAffectation>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: ResponsabiliteAffectation) {
        this.eventManager.broadcast({ name: 'responsabiliteAffectationListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackAffectationById(index: number, item: Affectation) {
        return item.id;
    }

    trackRessourceById(index: number, item: Ressource) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-responsabilite-affectation-popup',
    template: ''
})
export class ResponsabiliteAffectationPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private responsabiliteAffectationPopupService: ResponsabiliteAffectationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.responsabiliteAffectationPopupService
                    .open(ResponsabiliteAffectationDialogComponent as Component, params['id']);
            } else {
                this.responsabiliteAffectationPopupService
                    .open(ResponsabiliteAffectationDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
