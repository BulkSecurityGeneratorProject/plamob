/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { PlamobTestModule } from '../../../test.module';
import { JournalSuiviDetailComponent } from '../../../../../../main/webapp/app/entities/journal-suivi/journal-suivi-detail.component';
import { JournalSuiviService } from '../../../../../../main/webapp/app/entities/journal-suivi/journal-suivi.service';
import { JournalSuivi } from '../../../../../../main/webapp/app/entities/journal-suivi/journal-suivi.model';

describe('Component Tests', () => {

    describe('JournalSuivi Management Detail Component', () => {
        let comp: JournalSuiviDetailComponent;
        let fixture: ComponentFixture<JournalSuiviDetailComponent>;
        let service: JournalSuiviService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PlamobTestModule],
                declarations: [JournalSuiviDetailComponent],
                providers: [
                    JournalSuiviService
                ]
            })
            .overrideTemplate(JournalSuiviDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(JournalSuiviDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JournalSuiviService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new JournalSuivi(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.journalSuivi).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
