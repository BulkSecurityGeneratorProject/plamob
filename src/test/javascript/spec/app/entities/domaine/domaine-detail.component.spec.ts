/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { PlamobTestModule } from '../../../test.module';
import { DomaineDetailComponent } from '../../../../../../main/webapp/app/entities/domaine/domaine-detail.component';
import { DomaineService } from '../../../../../../main/webapp/app/entities/domaine/domaine.service';
import { Domaine } from '../../../../../../main/webapp/app/entities/domaine/domaine.model';

describe('Component Tests', () => {

    describe('Domaine Management Detail Component', () => {
        let comp: DomaineDetailComponent;
        let fixture: ComponentFixture<DomaineDetailComponent>;
        let service: DomaineService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PlamobTestModule],
                declarations: [DomaineDetailComponent],
                providers: [
                    DomaineService
                ]
            })
            .overrideTemplate(DomaineDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DomaineDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DomaineService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Domaine(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.domaine).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
