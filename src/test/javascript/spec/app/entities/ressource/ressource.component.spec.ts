/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlamobTestModule } from '../../../test.module';
import { RessourceComponent } from '../../../../../../main/webapp/app/entities/ressource/ressource.component';
import { RessourceService } from '../../../../../../main/webapp/app/entities/ressource/ressource.service';
import { Ressource } from '../../../../../../main/webapp/app/entities/ressource/ressource.model';

describe('Component Tests', () => {

    describe('Ressource Management Component', () => {
        let comp: RessourceComponent;
        let fixture: ComponentFixture<RessourceComponent>;
        let service: RessourceService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PlamobTestModule],
                declarations: [RessourceComponent],
                providers: [
                    RessourceService
                ]
            })
            .overrideTemplate(RessourceComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RessourceComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RessourceService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Ressource(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.ressources[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
