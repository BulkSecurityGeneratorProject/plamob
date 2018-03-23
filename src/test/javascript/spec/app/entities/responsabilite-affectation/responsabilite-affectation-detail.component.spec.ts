/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { PlamobTestModule } from '../../../test.module';
import { ResponsabiliteAffectationDetailComponent } from '../../../../../../main/webapp/app/entities/responsabilite-affectation/responsabilite-affectation-detail.component';
import { ResponsabiliteAffectationService } from '../../../../../../main/webapp/app/entities/responsabilite-affectation/responsabilite-affectation.service';
import { ResponsabiliteAffectation } from '../../../../../../main/webapp/app/entities/responsabilite-affectation/responsabilite-affectation.model';

describe('Component Tests', () => {

    describe('ResponsabiliteAffectation Management Detail Component', () => {
        let comp: ResponsabiliteAffectationDetailComponent;
        let fixture: ComponentFixture<ResponsabiliteAffectationDetailComponent>;
        let service: ResponsabiliteAffectationService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PlamobTestModule],
                declarations: [ResponsabiliteAffectationDetailComponent],
                providers: [
                    ResponsabiliteAffectationService
                ]
            })
            .overrideTemplate(ResponsabiliteAffectationDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ResponsabiliteAffectationDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ResponsabiliteAffectationService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new ResponsabiliteAffectation(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.responsabiliteAffectation).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
