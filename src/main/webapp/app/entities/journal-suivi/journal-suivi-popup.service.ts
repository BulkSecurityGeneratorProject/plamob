import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { JournalSuivi } from './journal-suivi.model';
import { JournalSuiviService } from './journal-suivi.service';

@Injectable()
export class JournalSuiviPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private journalSuiviService: JournalSuiviService

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
                this.journalSuiviService.find(id)
                    .subscribe((journalSuiviResponse: HttpResponse<JournalSuivi>) => {
                        const journalSuivi: JournalSuivi = journalSuiviResponse.body;
                        if (journalSuivi.date) {
                            journalSuivi.date = {
                                year: journalSuivi.date.getFullYear(),
                                month: journalSuivi.date.getMonth() + 1,
                                day: journalSuivi.date.getDate()
                            };
                        }
                        this.ngbModalRef = this.journalSuiviModalRef(component, journalSuivi);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.journalSuiviModalRef(component, new JournalSuivi());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    journalSuiviModalRef(component: Component, journalSuivi: JournalSuivi): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.journalSuivi = journalSuivi;
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
