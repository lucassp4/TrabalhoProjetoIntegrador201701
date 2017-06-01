package negocio;

import dao.BlocoDao;
import model.CadastroBloco;

/**
 * Created by Ritchely on 25/05/2017.
 */
public class BlocoNegocio {

    public boolean string(String e){
        int cont=0;

        for(int i = 1 ; i <e.length();i++){
            cont = i;
        }
        if (cont !=0){
            return false;
        }else{
            return true;
        }


    }


}
