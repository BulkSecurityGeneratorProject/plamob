import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { JournalSuivi } from './journal-suivi.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<JournalSuivi>;

@Injectable()
export class JournalSuiviService {

    private resourceUrl =  SERVER_API_URL + 'api/journal-suivis';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(journalSuivi: JournalSuivi): Observable<EntityResponseType> {
        const copy = this.convert(journalSuivi);
        return this.http.post<JournalSuivi>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(journalSuivi: JournalSuivi): Observable<EntityResponseType> {
        const copy = this.convert(journalSuivi);
        return this.http.put<JournalSuivi>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<JournalSuivi>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<JournalSuivi[]>> {
        const options = createRequestOption(req);
        return this.http.get<JournalSuivi[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<JournalSuivi[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: JournalSuivi = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<JournalSuivi[]>): HttpResponse<JournalSuivi[]> {
        const jsonResponse: JournalSuivi[] = res.body;
        const body: JournalSuivi[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to JournalSuivi.
     */
    private convertItemFromServer(journalSuivi: JournalSuivi): JournalSuivi {
        const copy: JournalSuivi = Object.assign({}, journalSuivi);
        copy.date = this.dateUtils
            .convertLocalDateFromServer(journalSuivi.date);
        return copy;
    }

    /**
     * Convert a JournalSuivi to a JSON which can be sent to the server.
     */
    private convert(journalSuivi: JournalSuivi): JournalSuivi {
        const copy: JournalSuivi = Object.assign({}, journalSuivi);
        copy.date = this.dateUtils
            .convertLocalDateToServer(journalSuivi.date);
        return copy;
    }
}
