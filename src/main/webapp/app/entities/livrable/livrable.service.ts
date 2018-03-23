import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Livrable } from './livrable.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Livrable>;

@Injectable()
export class LivrableService {

    private resourceUrl =  SERVER_API_URL + 'api/livrables';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(livrable: Livrable): Observable<EntityResponseType> {
        const copy = this.convert(livrable);
        return this.http.post<Livrable>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(livrable: Livrable): Observable<EntityResponseType> {
        const copy = this.convert(livrable);
        return this.http.put<Livrable>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Livrable>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Livrable[]>> {
        const options = createRequestOption(req);
        return this.http.get<Livrable[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Livrable[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Livrable = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Livrable[]>): HttpResponse<Livrable[]> {
        const jsonResponse: Livrable[] = res.body;
        const body: Livrable[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Livrable.
     */
    private convertItemFromServer(livrable: Livrable): Livrable {
        const copy: Livrable = Object.assign({}, livrable);
        copy.dateFin = this.dateUtils
            .convertLocalDateFromServer(livrable.dateFin);
        return copy;
    }

    /**
     * Convert a Livrable to a JSON which can be sent to the server.
     */
    private convert(livrable: Livrable): Livrable {
        const copy: Livrable = Object.assign({}, livrable);
        copy.dateFin = this.dateUtils
            .convertLocalDateToServer(livrable.dateFin);
        return copy;
    }
}
