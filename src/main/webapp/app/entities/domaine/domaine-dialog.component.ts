import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Domaine } from './domaine.model';
import { DomainePopupService } from './domaine-popup.service';
import { DomaineService } from './domaine.service';
import { Ressource, RessourceService } from '../ressource';

@Component({
    selector: 'jhi-domaine-dialog',
    templateUrl: './domaine-dialog.component.html'
})
export class DomaineDialogComponent implements OnInit {

    domaine: Domaine;
    isSaving: boolean;

    ressources: Ressource[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private domaineService: DomaineService,
        private ressourceService: RessourceService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.ressourceService.query()
            .subscribe((res: HttpResponse<Ressource[]>) => { this.ressources = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.domaine.id !== undefined) {
            this.subscribeToSaveResponse(
                this.domaineService.update(this.domaine));
        } else {
            this.subscribeToSaveResponse(
                this.domaineService.create(this.domaine));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Domaine>>) {
        result.subscribe((res: HttpResponse<Domaine>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Domaine) {
        this.eventManager.broadcast({ name: 'domaineListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackRessourceById(index: number, item: Ressource) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-domaine-popup',
    template: ''
})
export class DomainePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private domainePopupService: DomainePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.domainePopupService
                    .open(DomaineDialogComponent as Component, params['id']);
            } else {
                this.domainePopupService
                    .open(DomaineDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
