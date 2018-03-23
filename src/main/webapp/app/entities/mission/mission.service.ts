import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Mission } from './mission.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Mission>;

@Injectable()
export class MissionService {

    private resourceUrl =  SERVER_API_URL + 'api/missions';

    constructor(private http: HttpClient) { }

    create(mission: Mission): Observable<EntityResponseType> {
        const copy = this.convert(mission);
        return this.http.post<Mission>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(mission: Mission): Observable<EntityResponseType> {
        const copy = this.convert(mission);
        return this.http.put<Mission>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Mission>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Mission[]>> {
        const options = createRequestOption(req);
        return this.http.get<Mission[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Mission[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Mission = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Mission[]>): HttpResponse<Mission[]> {
        const jsonResponse: Mission[] = res.body;
        const body: Mission[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Mission.
     */
    private convertItemFromServer(mission: Mission): Mission {
        const copy: Mission = Object.assign({}, mission);
        return copy;
    }

    /**
     * Convert a Mission to a JSON which can be sent to the server.
     */
    private convert(mission: Mission): Mission {
        const copy: Mission = Object.assign({}, mission);
        return copy;
    }
}
