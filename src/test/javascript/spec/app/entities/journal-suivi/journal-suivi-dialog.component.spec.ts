/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { PlamobTestModule } from '../../../test.module';
import { JournalSuiviDialogComponent } from '../../../../../../main/webapp/app/entities/journal-suivi/journal-suivi-dialog.component';
import { JournalSuiviService } from '../../../../../../main/webapp/app/entities/journal-suivi/journal-suivi.service';
import { JournalSuivi } from '../../../../../../main/webapp/app/entities/journal-suivi/journal-suivi.model';
import { MissionService } from '../../../../../../main/webapp/app/entities/mission';

describe('Component Tests', () => {

    describe('JournalSuivi Management Dialog Component', () => {
        let comp: JournalSuiviDialogComponent;
        let fixture: ComponentFixture<JournalSuiviDialogComponent>;
        let service: JournalSuiviService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PlamobTestModule],
                declarations: [JournalSuiviDialogComponent],
                providers: [
                    MissionService,
                    JournalSuiviService
                ]
            })
            .overrideTemplate(JournalSuiviDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(JournalSuiviDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JournalSuiviService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new JournalSuivi(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.journalSuivi = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'journalSuiviListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new JournalSuivi();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.journalSuivi = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'journalSuiviListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
