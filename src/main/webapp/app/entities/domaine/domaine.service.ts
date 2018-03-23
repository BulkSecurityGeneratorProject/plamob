import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Domaine } from './domaine.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Domaine>;

@Injectable()
export class DomaineService {

    private resourceUrl =  SERVER_API_URL + 'api/domaines';

    constructor(private http: HttpClient) { }

    create(domaine: Domaine): Observable<EntityResponseType> {
        const copy = this.convert(domaine);
        return this.http.post<Domaine>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(domaine: Domaine): Observable<EntityResponseType> {
        const copy = this.convert(domaine);
        return this.http.put<Domaine>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Domaine>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Domaine[]>> {
        const options = createRequestOption(req);
        return this.http.get<Domaine[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Domaine[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Domaine = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Domaine[]>): HttpResponse<Domaine[]> {
        const jsonResponse: Domaine[] = res.body;
        const body: Domaine[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Domaine.
     */
    private convertItemFromServer(domaine: Domaine): Domaine {
        const copy: Domaine = Object.assign({}, domaine);
        return copy;
    }

    /**
     * Convert a Domaine to a JSON which can be sent to the server.
     */
    private convert(domaine: Domaine): Domaine {
        const copy: Domaine = Object.assign({}, domaine);
        return copy;
    }
}
