import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SERVER_API_URL } from '../../../app.constants';
import { RessourceVM } from './ressource-vm';
import { UserService } from '../../../shared';
import { createRequestOption } from '../../../shared/model/request-util';

@Injectable()
export class RessourceVMService {
    private resourceUrl = SERVER_API_URL + 'api/users/ressourceVM';

    constructor(private http: HttpClient) { }

    create(ressourceVM: RessourceVM): Observable<HttpResponse<RessourceVM>> {
        return this.http.post<RessourceVM>(this.resourceUrl, ressourceVM, { observe: 'response' });
    }

    log(ressourceVM: RessourceVM) {
        console.log('<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>');
        console.log('ressourceVM.id: ' + ressourceVM.id);
        console.log('ressourceVM.login: ' + ressourceVM.login);
        console.log('ressourceVM.firstName: ' + ressourceVM.firstName);
        console.log('ressourceVM.lastName: ' + ressourceVM.lastName);
        console.log('ressourceVM.tigramme: ' + ressourceVM.tigramme);
        console.log('ressourceVM.telephone: ' + ressourceVM.telephone);
        console.log('ressourceVM.email: ' + ressourceVM.email);
        console.log('ressourceVM.typeRessource: ' + ressourceVM.typeRessource);
        console.log('ressourceVM.langKey: ' + ressourceVM.langKey);
        console.log('ressourceVM.authorities: ' + ressourceVM.authorities);
        console.log('ressourceVM.tigramme: ' + ressourceVM.tigramme);
        console.log('<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>');
    }

    /*update(user: User): Observable<HttpResponse<User>> {
        return this.http.put<User>(this.resourceUrl, user, { observe: 'response' });
    }

    find(login: string): Observable<HttpResponse<User>> {
        return this.http.get<User>(`${this.resourceUrl}/${login}`, { observe: 'response' });
    }

    query(req?: any): Observable<HttpResponse<User[]>> {
        const options = createRequestOption(req);
        return this.http.get<User[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(login: string): Observable<HttpResponse<any>> {
        return this.http.delete(`${this.resourceUrl}/${login}`, { observe: 'response' });
    }

    authorities(): Observable<string[]> {
        return this.http.get<string[]>(SERVER_API_URL + 'api/users/authorities');
    }*/

}
