/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlamobTestModule } from '../../../test.module';
import { LivrableComponent } from '../../../../../../main/webapp/app/entities/livrable/livrable.component';
import { LivrableService } from '../../../../../../main/webapp/app/entities/livrable/livrable.service';
import { Livrable } from '../../../../../../main/webapp/app/entities/livrable/livrable.model';

describe('Component Tests', () => {

    describe('Livrable Management Component', () => {
        let comp: LivrableComponent;
        let fixture: ComponentFixture<LivrableComponent>;
        let service: LivrableService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PlamobTestModule],
                declarations: [LivrableComponent],
                providers: [
                    LivrableService
                ]
            })
            .overrideTemplate(LivrableComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LivrableComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LivrableService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Livrable(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.livrables[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
