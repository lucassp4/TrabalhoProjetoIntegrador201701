package negocio;

import model.Unidade;

/**
 * Created by Ritchely on 25/05/2017.
 */
public class UnidadeNegocio {
    boolean salvarT ;
    boolean salvarC ;
    public boolean verificarCampo(String teste) {
        try {
            int verifica = Integer.parseInt(teste);

            return true;

        } catch (Exception e) {

            return false;

        }
    }

    public boolean validarTelefone(String telefone) {
        int cont = 0;

        for (int i = 1; i <= telefone.length(); i++) {
            cont++;
        }
        if (cont == 9) {
            return true;
        } else {
            return false;
        }


    }

    public boolean validarCep(String cep) {
        int cont = 0;

        for (int i = 1; i <= cep.length(); i++) {
            cont++;
        }
        if (cont == 8) {
            return true;
        } else {
            return false;
        }


    }



    public boolean salvar(Unidade unidade) {

        boolean cep = validarCep(unidade.getCep());
        boolean telefone = validarTelefone(unidade.getTelefone());
        boolean validarC = verificarCampo(unidade.getCnpj());

        if (telefone){
            salvarT=true;
        }else{
            salvarT = false;
        }
        if (cep) {

            salvarC = true;
        }else{
            salvarC = false;
        }

        if(validarC){
            return true;
        }else{
            return false;
        }

    }
    }




