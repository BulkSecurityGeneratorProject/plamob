/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { PlamobTestModule } from '../../../test.module';
import { LivrableDetailComponent } from '../../../../../../main/webapp/app/entities/livrable/livrable-detail.component';
import { LivrableService } from '../../../../../../main/webapp/app/entities/livrable/livrable.service';
import { Livrable } from '../../../../../../main/webapp/app/entities/livrable/livrable.model';

describe('Component Tests', () => {

    describe('Livrable Management Detail Component', () => {
        let comp: LivrableDetailComponent;
        let fixture: ComponentFixture<LivrableDetailComponent>;
        let service: LivrableService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PlamobTestModule],
                declarations: [LivrableDetailComponent],
                providers: [
                    LivrableService
                ]
            })
            .overrideTemplate(LivrableDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LivrableDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LivrableService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Livrable(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.livrable).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
