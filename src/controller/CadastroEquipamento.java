package controller;

import model.CadEquipamento;
import negocio.EquipamentoNegocio;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import org.controlsfx.control.Notifications;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Window;
import javafx.util.Duration;

public class CadastroEquipamento implements Initializable {
	  
	
	@FXML
    private CadEquipamento equipamento;
	
	@FXML
	private TextField txtTipo;
	
	@FXML
	private TextField txtMarca;
	
	@FXML
	private TextField txtModelo;
	
	@FXML
	private Pane painelPrincipal;
	
	@FXML
	private TextArea txtareaDescricao;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnCancelar;
	
	@FXML 
	private ComboBox<String> comboUnidade;
		
	@FXML
    private TextField txtId;
	
	 @FXML
	 private DatePicker dataCadastro;
	 
	 @FXML
	 private TableView<CadEquipamento> tblEquipamentos;
	 
	 @FXML
	 private TableColumn<CadEquipamento, String> colunaTipo;
	 
	 @FXML
	 private TableColumn<CadEquipamento, String> colunaModelo;
	 
	 @FXML
	 private TableColumn<CadEquipamento, String> colunaMarca;
	 
	 @FXML
	 private TableColumn<CadEquipamento, String>  colunaData;
	 
	 @FXML
	 private TableColumn<CadEquipamento, String>  colunaUnidade;
	 
	 @FXML
	 private Button btnAcao;
	
	 
	 List<CadEquipamento> CadEquipamento = new ArrayList();
	 
	 Integer id = 0;
     CadEquipamento cadEquipamento =  new CadEquipamento();
     ObservableList<CadEquipamento> equipamentoView = null;
    
     EquipamentoNegocio equipamentoNegocio = new EquipamentoNegocio();
     String filter = "";
     private ObservableList<String> originalItems;

     
	 
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		equipamento= new CadEquipamento();
		CadEquipamento = listarEquipamento();
	
	}
	
	application.Main main = null;
		
		public void Cancelar(){
			URL arquivoFXML;
			arquivoFXML = getClass().getResource("/view/PaginaPrincipal.fxml");
			Parent fxmlParent;
			try {
				fxmlParent = FXMLLoader.load(arquivoFXML);
				painelPrincipal.getChildren().clear();
				painelPrincipal.getChildren().add(fxmlParent);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
	
		private void pegaValores(CadEquipamento equipamento) {
		       equipamento.setId(Integer.parseInt(txtId.getText()));
		       equipamento.setTipo(txtTipo.getText());
		        equipamento.setModelo(txtModelo.getText());
		        equipamento.setMarca(txtMarca.getText());
		        equipamento.setData(dataCadastro.getValue());
		        equipamento.setUnidade(comboUnidade.getValue());
		        
		}
		
		private void setaValores(CadEquipamento equipamento) {

	        txtId.setText(String.valueOf(equipamento.getId()));
	        txtTipo.setText(equipamento.getTipo());
	        txtModelo.setText(equipamento.getModelo());
	        txtMarca.setText(equipamento.getModelo());
	        dataCadastro.setValue(equipamento.getData());
	        comboUnidade.setValue(equipamento.getUnidade());
	       
	    }
		
		public void AutoCompleteCliente(Event e){
	        originalItems = FXCollections.observableArrayList(comboUnidade.getItems());
	        comboUnidade.setTooltip(new Tooltip());
	        comboUnidade.setOnKeyPressed(this::handleOnKeyPressed);
	        comboUnidade.setOnHidden(this::handleOnHiding);
		
	    }
		
		public void limpaCampos() {

	        txtId.setText("0");
	        txtTipo.setText("");
	        txtModelo.setText("");
	        txtMarca.setText("");
	        dataCadastro.setValue(null);
	        comboUnidade.setValue("");
	       
	    }
		    
		
		
		
		
		@FXML
		private void salvar(ActionEvent event) throws SQLException {
	        String validar;
	        boolean validacao = false;
	        equipamento = new CadEquipamento();
	        pegaValores(equipamento);
	        validacao = validarCampos();
	        if(validacao) {
	            if (equipamento.getId() == 0) {
	             validar = equipamentoNegocio.salvar(equipamento);
	                if(validar.equals("salvo")) {
	                  

	                    String msg = "Equipamento inserido!";
	                    exibeMensagem(msg);
	                    limpaCampos();
	                }else{
	                    exibeMensagem(validar);
	                }
	            } else {
	                validar = equipamentoNegocio.editar(equipamento);
	                if (validar.equals("salvo")) {
	                	CadEquipamento = listarEquipamento();
	                    populaView(CadEquipamento);
	                    String msg = "Objeto editado com sucesso!";
	                    exibeMensagem(msg);

	                    limpaCampos();
	                    btnAcao.setText("Salvar");
	                }else{
	                    exibeMensagem(validar);
	                }
	            }
	            
	        }

	    }

		
		
		
		 public void populaView(List<CadEquipamento> equipamento){
		        colunaTipo.setCellValueFactory(new PropertyValueFactory<CadEquipamento, String>("tipo"));
		        colunaModelo.setCellValueFactory(new PropertyValueFactory<CadEquipamento, String>("modelo"));
		        colunaMarca.setCellValueFactory(new PropertyValueFactory<CadEquipamento, String>("marca"));
		        colunaData.setCellValueFactory(new PropertyValueFactory<CadEquipamento, String>("data"));
		        colunaUnidade.setCellValueFactory(new PropertyValueFactory<CadEquipamento, String>("unidade"));
		        equipamentoView = FXCollections.observableArrayList(equipamento);
		        tblEquipamentos.setItems(equipamentoView);
		 }
		
		 public List<CadEquipamento> listarEquipamento(){
				CadEquipamento = equipamentoNegocio.listarEquipamento();
		        return CadEquipamento;
			}
		
		public void edit(){

	        equipamento = new CadEquipamento();
	        equipamento = (CadEquipamento) tblEquipamentos.getSelectionModel().getSelectedItem();
	        setaValores(equipamento);
	        btnAcao.setText("Editar");
	        btnCancelar.setText("Cancelar");
	        String msg  = "Equipamento pronto para edição!";
	        exibeMensagem(msg);
	    }
		
		public void handleOnKeyPressed(KeyEvent e) {
	        ObservableList<String> filteredList = FXCollections.observableArrayList();
	        KeyCode code = e.getCode();

	        if (code.isLetterKey()) {
	            filter += e.getText();
	        }
	        if (code == KeyCode.BACK_SPACE && filter.length() > 0) {
	            filter = filter.substring(0, filter.length() - 1);
	            comboUnidade.getItems().setAll(originalItems);
	        }
	        if (code == KeyCode.ESCAPE) {
	            filter = "";
	        }
	        if (filter.length() == 0) {
	            filteredList = originalItems;
	            comboUnidade.getTooltip().hide();
	        } else {
	            Stream<String> itens = comboUnidade.getItems().stream();
	            String txtUsr = filter.toString().toLowerCase();
	            itens.filter(el -> el.toString().toLowerCase().contains(txtUsr)).forEach(filteredList::add);
	            comboUnidade.getTooltip().setText(txtUsr);
	            Window stage = comboUnidade.getScene().getWindow();
	            double posX = stage.getX() + comboUnidade.getBoundsInParent().getMinX();
	            double posY = stage.getY() + comboUnidade.getBoundsInParent().getMinY();
	            comboUnidade.getTooltip().show(stage, posX, posY);
	            comboUnidade.show();
	        }
	        comboUnidade.getItems().setAll(filteredList);
	    }
		
		
		public void handleOnHiding(Event e) {
	        filter = "";
	        comboUnidade.getTooltip().hide();
	        String s = comboUnidade.getSelectionModel().getSelectedItem();
	        comboUnidade.getItems().setAll(originalItems);
	        comboUnidade.getSelectionModel().select(s);
	    }
		
		public boolean validarCampos(){

            String tipo =  txtTipo.getText();
            String modelo = txtModelo.getText();
            String marca =  txtMarca.getText();
            LocalDate data =  dataCadastro.getValue();
            String unidade =  comboUnidade.getValue();
           
            List<Control>  controls = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            sb.append("");
            if(tipo.equals("") || tipo == null){
                sb.append("O tipo não pode ser vazio!. \n");
                controls.add(txtTipo);
            }
            if(modelo.equals("") || modelo == null){
                sb.append("O modelo não pode ser vazio!. \n");
                controls.add(txtModelo);
            }
            if(marca.equals("") || marca == null){
                sb.append("A Marca não pode ser vazia!. \n");
                controls.add(txtMarca);
            }
            if(data.equals("") || data == null){
                sb.append("A Data não pode ser vazia!. \n");
                controls.add(txtMarca);
            }
            if(unidade.equals("") || unidade == null){
                sb.append("A Unidade não pode ser vazia!. \n");
                controls.add(txtMarca);
            }
            if(!sb.equals("")) {
                exibeMensagem(sb.toString());
             
            }

            return sb.toString().isEmpty();
    }

		public void exibeMensagem(String msg){
	        Notifications.create()
	                .title("Atenção")
	                .text(String.valueOf(msg))
	                .owner(main)
	                .hideAfter(Duration.seconds(3))
	                .darkStyle()
	                .position(Pos.TOP_RIGHT)
	                .showInformation();
		}
	}
			

