package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class PaginaPrincipal implements Initializable {

	@FXML
	private Pane painelPrincipal;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	// Metodos da parte de cadastro

	public void cadUsuario() {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/CadastroUsuario.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = FXMLLoader.load(arquivoFXML);
			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cadEquipamento() {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/CadastroEquipamento.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = FXMLLoader.load(arquivoFXML);

			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cadAuditorio() {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/CadastroAuditorio.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void cadLaboratorio() {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/CadastroLaboratorio.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void cadSala() {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/CadastroSala.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cadAutoridade() {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/CadastroAutoridade.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = FXMLLoader.load(arquivoFXML);

			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cadBloco() {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/CadastroBloco.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = FXMLLoader.load(arquivoFXML);

			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cadUnidade() {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/CadastroNovasUnidades.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = FXMLLoader.load(arquivoFXML);

			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void kits() {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/CriarKit.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Metodos da parte de reserva

	public void reservaCancelar() {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/CancelarReserva.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cancelarReservaADM() {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/CancelarReservaAdm.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reservarAuditorio() {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/ReservarAuditorio.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reservarEquipamento() {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/ReservarEquipamento.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reservaLaboratorio() {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/ReservarLaboratorio.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reservaSala() {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/ReservarSala.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Metodos da parte de alterar os cadastros

	public void alterarUsuario() {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/CadastroUsuario.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void alterarEquipamento() {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/ReservarLaboratorio.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);

			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void alterarSala() {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/CadastroUsuario.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
			painelPrincipal.getChildren().clear();
			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Metodos de Relatorios

	public void relatorioCadastro() {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/RelatorioCadastro.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
			painelPrincipal.getChildren().clear();
			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void relatorioReserva() {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/RelatorioReserva.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
			painelPrincipal.getChildren().clear();
			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void relatorioReservaADM() {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/RelatorioReservaADM.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
			painelPrincipal.getChildren().clear();
			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void relatorioCancelamento() {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/RelatorioCancelamento.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
			painelPrincipal.getChildren().clear();
			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
