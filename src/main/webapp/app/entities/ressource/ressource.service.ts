import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Ressource } from './ressource.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Ressource>;

@Injectable()
export class RessourceService {

    private resourceUrl =  SERVER_API_URL + 'api/ressources';

    constructor(private http: HttpClient) { }

    create(ressource: Ressource): Observable<EntityResponseType> {
        const copy = this.convert(ressource);
        return this.http.post<Ressource>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(ressource: Ressource): Observable<EntityResponseType> {
        const copy = this.convert(ressource);
        return this.http.put<Ressource>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Ressource>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Ressource[]>> {
        const options = createRequestOption(req);
        return this.http.get<Ressource[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Ressource[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Ressource = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Ressource[]>): HttpResponse<Ressource[]> {
        const jsonResponse: Ressource[] = res.body;
        const body: Ressource[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Ressource.
     */
    private convertItemFromServer(ressource: Ressource): Ressource {
        const copy: Ressource = Object.assign({}, ressource);
        return copy;
    }

    /**
     * Convert a Ressource to a JSON which can be sent to the server.
     */
    private convert(ressource: Ressource): Ressource {
        const copy: Ressource = Object.assign({}, ressource);
        return copy;
    }
}
