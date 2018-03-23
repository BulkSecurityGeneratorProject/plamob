import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Ressource } from './ressource.model';
import { RessourcePopupService } from './ressource-popup.service';
import { RessourceService } from './ressource.service';
import { Profil, ProfilService } from '../profil';

@Component({
    selector: 'jhi-ressource-dialog',
    templateUrl: './ressource-dialog.component.html'
})
export class RessourceDialogComponent implements OnInit {

    ressource: Ressource;
    isSaving: boolean;

    profils: Profil[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private ressourceService: RessourceService,
        private profilService: ProfilService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.profilService
            .query({filter: 'ressource-is-null'})
            .subscribe((res: HttpResponse<Profil[]>) => {
                if (!this.ressource.profilId) {
                    this.profils = res.body;
                } else {
                    this.profilService
                        .find(this.ressource.profilId)
                        .subscribe((subRes: HttpResponse<Profil>) => {
                            this.profils = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.ressource.id !== undefined) {
            this.subscribeToSaveResponse(
                this.ressourceService.update(this.ressource));
        } else {
            this.subscribeToSaveResponse(
                this.ressourceService.create(this.ressource));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Ressource>>) {
        result.subscribe((res: HttpResponse<Ressource>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Ressource) {
        this.eventManager.broadcast({ name: 'ressourceListModification', content: 'OK'});
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
}

@Component({
    selector: 'jhi-ressource-popup',
    template: ''
})
export class RessourcePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private ressourcePopupService: RessourcePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.ressourcePopupService
                    .open(RessourceDialogComponent as Component, params['id']);
            } else {
                this.ressourcePopupService
                    .open(RessourceDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
