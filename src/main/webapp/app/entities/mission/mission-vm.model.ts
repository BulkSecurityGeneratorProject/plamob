import { Domaine } from '../domaine';
import { Mission } from '.';
import { Ressource } from '../ressource';
import { RessourceVM } from '../../admin/user-management/ressource-vm/ressource-vm';

 export class MissionVM {
     domaine:Domaine;
     mission:Mission;
     ressources: RessourceVM[]; 
 }
