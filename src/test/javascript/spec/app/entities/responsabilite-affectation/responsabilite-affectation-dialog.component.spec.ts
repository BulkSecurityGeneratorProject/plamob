/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { PlamobTestModule } from '../../../test.module';
import { ResponsabiliteAffectationDialogComponent } from '../../../../../../main/webapp/app/entities/responsabilite-affectation/responsabilite-affectation-dialog.component';
import { ResponsabiliteAffectationService } from '../../../../../../main/webapp/app/entities/responsabilite-affectation/responsabilite-affectation.service';
import { ResponsabiliteAffectation } from '../../../../../../main/webapp/app/entities/responsabilite-affectation/responsabilite-affectation.model';
import { AffectationService } from '../../../../../../main/webapp/app/entities/affectation';
import { RessourceService } from '../../../../../../main/webapp/app/entities/ressource';

describe('Component Tests', () => {

    describe('ResponsabiliteAffectation Management Dialog Component', () => {
        let comp: ResponsabiliteAffectationDialogComponent;
        let fixture: ComponentFixture<ResponsabiliteAffectationDialogComponent>;
        let service: ResponsabiliteAffectationService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PlamobTestModule],
                declarations: [ResponsabiliteAffectationDialogComponent],
                providers: [
                    AffectationService,
                    RessourceService,
                    ResponsabiliteAffectationService
                ]
            })
            .overrideTemplate(ResponsabiliteAffectationDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ResponsabiliteAffectationDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ResponsabiliteAffectationService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ResponsabiliteAffectation(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.responsabiliteAffectation = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'responsabiliteAffectationListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ResponsabiliteAffectation();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.responsabiliteAffectation = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'responsabiliteAffectationListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
