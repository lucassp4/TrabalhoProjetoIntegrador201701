package negocio;

/**
 * Created by Ritchely on 25/05/2017.
 */
public class SalaNegocio {

    public boolean verificarCampo(String teste) {

        try {
            int verifica = Integer.parseInt(teste);

            return true;

        } catch (Exception e) {

            return false;

        }
    }
    public boolean string(String e){
        int cont=0;
        boolean teste;
        teste = verificarCampo(e);
        for(int i = 1 ; i <=e.length();i++){
            cont = i;
        }

        if(teste){
        if (cont ==3){
            return true;
        }else{
            return false;
        }
        }else{
            return false;
        }


    }
}
