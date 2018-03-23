/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { PlamobTestModule } from '../../../test.module';
import { ResponsabiliteAffectationDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/responsabilite-affectation/responsabilite-affectation-delete-dialog.component';
import { ResponsabiliteAffectationService } from '../../../../../../main/webapp/app/entities/responsabilite-affectation/responsabilite-affectation.service';

describe('Component Tests', () => {

    describe('ResponsabiliteAffectation Management Delete Component', () => {
        let comp: ResponsabiliteAffectationDeleteDialogComponent;
        let fixture: ComponentFixture<ResponsabiliteAffectationDeleteDialogComponent>;
        let service: ResponsabiliteAffectationService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PlamobTestModule],
                declarations: [ResponsabiliteAffectationDeleteDialogComponent],
                providers: [
                    ResponsabiliteAffectationService
                ]
            })
            .overrideTemplate(ResponsabiliteAffectationDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ResponsabiliteAffectationDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ResponsabiliteAffectationService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
