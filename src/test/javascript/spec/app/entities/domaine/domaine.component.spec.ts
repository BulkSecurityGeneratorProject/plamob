/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlamobTestModule } from '../../../test.module';
import { DomaineComponent } from '../../../../../../main/webapp/app/entities/domaine/domaine.component';
import { DomaineService } from '../../../../../../main/webapp/app/entities/domaine/domaine.service';
import { Domaine } from '../../../../../../main/webapp/app/entities/domaine/domaine.model';

describe('Component Tests', () => {

    describe('Domaine Management Component', () => {
        let comp: DomaineComponent;
        let fixture: ComponentFixture<DomaineComponent>;
        let service: DomaineService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PlamobTestModule],
                declarations: [DomaineComponent],
                providers: [
                    DomaineService
                ]
            })
            .overrideTemplate(DomaineComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DomaineComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DomaineService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Domaine(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.domaines[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
