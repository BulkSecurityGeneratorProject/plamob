package be.civadis.plamob.my.app;

import be.civadis.plamob.web.rest.vm.RessourceVM;

public class RessourceVMFactory {

    public static RessourceVM getCompleteRessourceVM() {
        RessourceVM ressourceVM = new RessourceVM();

        return ressourceVM;
    }

    public static RessourceVM getRessourceVMWithoutTypeRess() {
        RessourceVM ressourceVM = new RessourceVM();


        return ressourceVM;
    }

    public static RessourceVM getRessourceVMWithoutProfile() {
        RessourceVM ressourceVM = new RessourceVM();

        return ressourceVM;
    }

    public static RessourceVM getEmptyRessourceVM() {
        return new RessourceVM();
    }
}
