package negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.EquipamentoDAO;
import model.CadEquipamento;

public class EquipamentoNegocio {
	EquipamentoDAO equipamentoDAO = new EquipamentoDAO();

	public String salvar(CadEquipamento equipamento) throws SQLException {

		boolean validacao = false;
		String salvo = "falha";
		StringBuilder sb = new StringBuilder();
		validacao = validaData(equipamento);
		if (!validacao) {
			sb.append("Ano não pode ser menor que 2005");
		}
		
		if (sb.toString().isEmpty()) {
			salvo = equipamentoDAO.salvar(equipamento);
		} else {
			sb.append(salvo);
			return sb.toString();
		}

		return salvo;
	}

	public String editar(CadEquipamento equipamento) throws SQLException {
		
        String salvo = "falha";
        StringBuilder sb = new StringBuilder();
        if (sb.toString().isEmpty()) {
            salvo = equipamentoDAO.editarr(equipamento);
        } else {
            sb.append(salvo);
            return sb.toString();
        }
        sb.append(salvo);
        return sb.toString();
    }

	 public List<CadEquipamento> listarEquipamento(){
	        List<CadEquipamento> equipamento = new ArrayList<CadEquipamento>();
	        equipamento = equipamentoDAO.listarEquipamento();
	        return equipamento;
	    }
	
	public boolean validaData(CadEquipamento equipamento) {
		Boolean anoValido = false;
		if (equipamento.getData().getYear() > 2005) {
			anoValido = true;
		}
		return anoValido;
	}
}
