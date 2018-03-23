import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Profil } from './profil.model';
import { ProfilService } from './profil.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-profil',
    templateUrl: './profil.component.html'
})
export class ProfilComponent implements OnInit, OnDestroy {
profils: Profil[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private profilService: ProfilService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.profilService.query().subscribe(
            (res: HttpResponse<Profil[]>) => {
                this.profils = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInProfils();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Profil) {
        return item.id;
    }
    registerChangeInProfils() {
        this.eventSubscriber = this.eventManager.subscribe('profilListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
