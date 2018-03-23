import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Affectation } from './affectation.model';
import { AffectationPopupService } from './affectation-popup.service';
import { AffectationService } from './affectation.service';
import { Profil, ProfilService } from '../profil';
import { Ressource, RessourceService } from '../ressource';
import { Livrable, LivrableService } from '../livrable';

@Component({
    selector: 'jhi-affectation-dialog',
    templateUrl: './affectation-dialog.component.html'
})
export class AffectationDialogComponent implements OnInit {

    affectation: Affectation;
    isSaving: boolean;

    profils: Profil[];

    ressources: Ressource[];

    livrables: Livrable[];
    dateDebutDp: any;
    dateFinDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private affectationService: AffectationService,
        private profilService: ProfilService,
        private ressourceService: RessourceService,
        private livrableService: LivrableService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.profilService
            .query({filter: 'affectation-is-null'})
            .subscribe((res: HttpResponse<Profil[]>) => {
                if (!this.affectation.profilId) {
                    this.profils = res.body;
                } else {
                    this.profilService
                        .find(this.affectation.profilId)
                        .subscribe((subRes: HttpResponse<Profil>) => {
                            this.profils = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.ressourceService.query()
            .subscribe((res: HttpResponse<Ressource[]>) => { this.ressources = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.livrableService.query()
            .subscribe((res: HttpResponse<Livrable[]>) => { this.livrables = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.affectation.id !== undefined) {
            this.subscribeToSaveResponse(
                this.affectationService.update(this.affectation));
        } else {
            this.subscribeToSaveResponse(
                this.affectationService.create(this.affectation));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Affectation>>) {
        result.subscribe((res: HttpResponse<Affectation>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Affectation) {
        this.eventManager.broadcast({ name: 'affectationListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackProfilById(index: number, item: Profil) {
        return item.id;
    }

    trackRessourceById(index: number, item: Ressource) {
        return item.id;
    }

    trackLivrableById(index: number, item: Livrable) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-affectation-popup',
    template: ''
})
export class AffectationPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private affectationPopupService: AffectationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.affectationPopupService
                    .open(AffectationDialogComponent as Component, params['id']);
            } else {
                this.affectationPopupService
                    .open(AffectationDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
