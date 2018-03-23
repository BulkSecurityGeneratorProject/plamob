import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { ResponsabiliteAffectation } from './responsabilite-affectation.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<ResponsabiliteAffectation>;

@Injectable()
export class ResponsabiliteAffectationService {

    private resourceUrl =  SERVER_API_URL + 'api/responsabilite-affectations';

    constructor(private http: HttpClient) { }

    create(responsabiliteAffectation: ResponsabiliteAffectation): Observable<EntityResponseType> {
        const copy = this.convert(responsabiliteAffectation);
        return this.http.post<ResponsabiliteAffectation>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(responsabiliteAffectation: ResponsabiliteAffectation): Observable<EntityResponseType> {
        const copy = this.convert(responsabiliteAffectation);
        return this.http.put<ResponsabiliteAffectation>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ResponsabiliteAffectation>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<ResponsabiliteAffectation[]>> {
        const options = createRequestOption(req);
        return this.http.get<ResponsabiliteAffectation[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<ResponsabiliteAffectation[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: ResponsabiliteAffectation = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<ResponsabiliteAffectation[]>): HttpResponse<ResponsabiliteAffectation[]> {
        const jsonResponse: ResponsabiliteAffectation[] = res.body;
        const body: ResponsabiliteAffectation[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to ResponsabiliteAffectation.
     */
    private convertItemFromServer(responsabiliteAffectation: ResponsabiliteAffectation): ResponsabiliteAffectation {
        const copy: ResponsabiliteAffectation = Object.assign({}, responsabiliteAffectation);
        return copy;
    }

    /**
     * Convert a ResponsabiliteAffectation to a JSON which can be sent to the server.
     */
    private convert(responsabiliteAffectation: ResponsabiliteAffectation): ResponsabiliteAffectation {
        const copy: ResponsabiliteAffectation = Object.assign({}, responsabiliteAffectation);
        return copy;
    }
}
