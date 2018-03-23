import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Livrable } from './livrable.model';
import { LivrableService } from './livrable.service';

@Injectable()
export class LivrablePopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private livrableService: LivrableService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.livrableService.find(id)
                    .subscribe((livrableResponse: HttpResponse<Livrable>) => {
                        const livrable: Livrable = livrableResponse.body;
                        if (livrable.dateFin) {
                            livrable.dateFin = {
                                year: livrable.dateFin.getFullYear(),
                                month: livrable.dateFin.getMonth() + 1,
                                day: livrable.dateFin.getDate()
                            };
                        }
                        this.ngbModalRef = this.livrableModalRef(component, livrable);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.livrableModalRef(component, new Livrable());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    livrableModalRef(component: Component, livrable: Livrable): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.livrable = livrable;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
