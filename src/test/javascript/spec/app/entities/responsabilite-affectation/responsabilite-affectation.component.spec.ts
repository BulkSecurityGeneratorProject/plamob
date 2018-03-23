/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlamobTestModule } from '../../../test.module';
import { ResponsabiliteAffectationComponent } from '../../../../../../main/webapp/app/entities/responsabilite-affectation/responsabilite-affectation.component';
import { ResponsabiliteAffectationService } from '../../../../../../main/webapp/app/entities/responsabilite-affectation/responsabilite-affectation.service';
import { ResponsabiliteAffectation } from '../../../../../../main/webapp/app/entities/responsabilite-affectation/responsabilite-affectation.model';

describe('Component Tests', () => {

    describe('ResponsabiliteAffectation Management Component', () => {
        let comp: ResponsabiliteAffectationComponent;
        let fixture: ComponentFixture<ResponsabiliteAffectationComponent>;
        let service: ResponsabiliteAffectationService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PlamobTestModule],
                declarations: [ResponsabiliteAffectationComponent],
                providers: [
                    ResponsabiliteAffectationService
                ]
            })
            .overrideTemplate(ResponsabiliteAffectationComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ResponsabiliteAffectationComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ResponsabiliteAffectationService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new ResponsabiliteAffectation(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.responsabiliteAffectations[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
