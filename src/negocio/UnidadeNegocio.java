package negocio;

/**
 * Created by Ritchely on 25/05/2017.
 */
public class UnidadeNegocio {

    public boolean verificarCampo(String teste){
        try{
        int   verifica = Integer.parseInt(teste);

            return true;

        }catch (Exception e){

            return false;

        }

    }

}
