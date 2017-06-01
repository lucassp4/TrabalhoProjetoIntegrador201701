package negocio;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.EquipamentoDAO;
import model.CadEquipamento;

public class EquipamentoNegocio {
	EquipamentoDAO equipamentoDAO = new EquipamentoDAO();


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
}
