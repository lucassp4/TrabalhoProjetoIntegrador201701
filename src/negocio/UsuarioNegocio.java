package negocio;

import dao.UsuarioDAO;
import model.Usuario;


import java.sql.SQLException;

/**
 * Created by muril on 17/05/2017.
 */
public class UsuarioNegocio {
    //INSTANCIA DE CONEXÃO COM USUÁRIO
    UsuarioDAO usuarioDao = new UsuarioDAO();

    //REGRA DE NEGOCIO PARA VALIDAR O SALVAR USUÁRIO
    public String Salvar(Usuario usuario){
        boolean usuarioValido = false;
        boolean senhaValida = false;

        StringBuilder sb = new StringBuilder();
        usuarioValido = validaUsuario(usuario.getLogin());
        if (!usuarioValido) {
            sb.append("login precisa ter mais de 6 caracteres. \n");
        }
        senhaValida = validaSenha(usuario.getSenha());
        if (!senhaValida) {
            sb.append("Senha precisa ter mais de 6 caracteres. \n");
        }
        if (sb.toString().isEmpty()) {
            try {
                sb.append(usuarioDao.salvar(usuario));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            sb.append("falha");
            return sb.toString();
        }

        return sb.toString();
    }
    // REGRA DE NEGOCIO PARA VALIDAR SENHA A SENHA
    private boolean validaSenha(String senha) {
        if(senha.length() < 6){
            return false;
        }else {
            return true;
        }
    }
    //REGRA DE NEGOCIO PARA VALIDAR O USUARIO LOGIN
    private boolean validaUsuario(String login) {
        if(login.length() < 6){
            return false;
        }else{
            return true;
        }

    }
    // REGRA DE NEGOCIO PARA VALIDAR A BUSCA DO LOGIN
    public Usuario buscaPorLogin(String login){
        Usuario usuario = new Usuario();
        usuario = usuarioDao.buscarPorLogin(login);
        return usuario;
    }
}
