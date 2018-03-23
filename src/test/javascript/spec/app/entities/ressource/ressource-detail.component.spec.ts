/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { PlamobTestModule } from '../../../test.module';
import { RessourceDetailComponent } from '../../../../../../main/webapp/app/entities/ressource/ressource-detail.component';
import { RessourceService } from '../../../../../../main/webapp/app/entities/ressource/ressource.service';
import { Ressource } from '../../../../../../main/webapp/app/entities/ressource/ressource.model';

describe('Component Tests', () => {

    describe('Ressource Management Detail Component', () => {
        let comp: RessourceDetailComponent;
        let fixture: ComponentFixture<RessourceDetailComponent>;
        let service: RessourceService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PlamobTestModule],
                declarations: [RessourceDetailComponent],
                providers: [
                    RessourceService
                ]
            })
            .overrideTemplate(RessourceDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RessourceDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RessourceService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Ressource(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.ressource).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
