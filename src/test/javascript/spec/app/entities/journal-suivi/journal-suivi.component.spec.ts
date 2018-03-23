/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlamobTestModule } from '../../../test.module';
import { JournalSuiviComponent } from '../../../../../../main/webapp/app/entities/journal-suivi/journal-suivi.component';
import { JournalSuiviService } from '../../../../../../main/webapp/app/entities/journal-suivi/journal-suivi.service';
import { JournalSuivi } from '../../../../../../main/webapp/app/entities/journal-suivi/journal-suivi.model';

describe('Component Tests', () => {

    describe('JournalSuivi Management Component', () => {
        let comp: JournalSuiviComponent;
        let fixture: ComponentFixture<JournalSuiviComponent>;
        let service: JournalSuiviService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PlamobTestModule],
                declarations: [JournalSuiviComponent],
                providers: [
                    JournalSuiviService
                ]
            })
            .overrideTemplate(JournalSuiviComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(JournalSuiviComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JournalSuiviService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new JournalSuivi(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.journalSuivis[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
