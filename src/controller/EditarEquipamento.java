package controller;

import dao.EquipamentoDAO;
import dao.UnidadeDao;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Window;
import javafx.util.Duration;
import model.CadEquipamento;
import model.Unidade;
import negocio.EquipamentoNegocio;
import org.controlsfx.control.Notifications;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

/**
 * Created by Ritchely on 01/06/2017.
 */
public class EditarEquipamento implements Initializable{
    @FXML
  CadEquipamento equipamento;

    @FXML
    private TextField txtTipo;

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtModelo;

    @FXML
    private Pane painelPrincipal;

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
    private TableColumn<CadEquipamento, String>  colunaDataCadastro;

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

    UnidadeDao unidadeDao = new UnidadeDao();

    public EditarEquipamento() throws SQLException {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherComboUnidade();
        equipamento= new CadEquipamento();
        CadEquipamento = listarEquipamento();
        populaView(CadEquipamento);

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
        equipamento.setTipo(txtTipo.getText());
        equipamento.setModelo(txtModelo.getText());
        equipamento.setMarca(txtMarca.getText());
        equipamento.setDataCadastro(dataCadastro.getValue());
        equipamento.setUnidade(comboUnidade.getValue());

    }

    private void setaValores(CadEquipamento equipamento) {

        txtId.setText(String.valueOf(equipamento.getId()));
        txtTipo.setText(equipamento.getTipo());
        txtModelo.setText(equipamento.getModelo());
        txtMarca.setText(equipamento.getModelo());
        dataCadastro.setValue(equipamento.getDataCadastro());
        comboUnidade.setValue(equipamento.getUnidade());

    }

    public void AutoCompleteCliente(Event e){
        originalItems = FXCollections.observableArrayList(comboUnidade.getItems());
        comboUnidade.setTooltip(new Tooltip());
        comboUnidade.setOnKeyPressed(this::handleOnKeyPressed);
        comboUnidade.setOnHidden(this::handleOnHiding);

    }

    public void limpaCampos() {
        txtId.setText("");
        txtTipo.setText("");
        txtModelo.setText("");
        txtMarca.setText("");
        dataCadastro.setValue(null);
        comboUnidade.setValue("");
        btnSalvar.setDisable(false);
        btnCancelar.setDisable(true);

    }



    EquipamentoDAO equipamentoDAO = new EquipamentoDAO();

    @FXML
    private void salvar(ActionEvent event) throws SQLException {

        boolean validacao = false;
        boolean validar = false;
        equipamento = new CadEquipamento();

        pegaValores(equipamento);
        validacao = validarCampos();

        if (validacao) {
            validar = confirmar();
            equipamentoDAO.salvar(equipamento);

            CadEquipamento = listarEquipamento();
            populaView(CadEquipamento);
        } else {
            equipamentoDAO.salvar(equipamento);

            String msg = "Equipamento inserido!";
            exibeMensagem(msg);
            limpaCampos();

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

    }



    public void populaView(List<CadEquipamento> equipamento){
        colunaTipo.setCellValueFactory(new PropertyValueFactory<CadEquipamento, String>("tipo"));
        colunaModelo.setCellValueFactory(new PropertyValueFactory<CadEquipamento, String>("modelo"));
        colunaMarca.setCellValueFactory(new PropertyValueFactory<CadEquipamento, String>("marca"));
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
        String msg  = "Equipamento pronto para edi��o!";
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
        String unidade =  comboUnidade.getValue();
        LocalDate date = dataCadastro.getValue();
        List<Control>  controls = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("");
        if(tipo.equals("") || tipo == null){
            sb.append("O tipo nao pode ser vazio!. \n");
            controls.add(txtTipo);
        }
        if(modelo.equals("") || modelo == null){
            sb.append("O modelo nao pode ser vazio!. \n");
            controls.add(txtModelo);
        }
        if(marca.equals("") || marca == null){
            sb.append("A Marca nao pode ser vazia!. \n");
            controls.add(txtMarca);
        }
        if(unidade == null){
            sb.append("A Unidade nao pode ser vazia!. \n");
            controls.add(comboUnidade);
        }
        if(date == null){
            sb.append("A data nao pode ser vazia!. \n");
            controls.add(dataCadastro);
        }
        if(!sb.equals("")) {
            exibeMensagem(sb.toString());
            animaCamposValidados(controls);

        }

        return sb.toString().isEmpty();
    }

    public void exibeMensagem(String msg){
        Notifications.create()
                .title("Atençao")
                .text(String.valueOf(msg))
                .owner(main)
                .hideAfter(Duration.seconds(3))
                .darkStyle()
                .position(Pos.TOP_RIGHT)
                .showInformation();
    }
    public void animaCamposValidados(List<Control> controls) {
        controls.forEach(control -> {
            RotateTransition rotateTransition = new RotateTransition(Duration.millis(60), control);
            rotateTransition.setFromAngle(-4);
            rotateTransition.setToAngle(4);
            rotateTransition.setCycleCount(8);
            rotateTransition.setAutoReverse(true);
            rotateTransition.setOnFinished((ActionEvent event1) -> {
                control.setRotate(0);
            });
            rotateTransition.play();
        });
        if (!controls.isEmpty()) {
            controls.get(0).requestFocus();
        }
    }
    public void preencherComboUnidade(){

        List<String> teste = new ArrayList<String>();
        List<Unidade> unidade = new ArrayList<Unidade>();
        unidade = unidadeDao.listarClientes();
        unidade.forEach( (Unidade cliente) -> {
                    teste.add(cliente.getNome());
                }


        );
        teste.forEach((String testes)->{
                    comboUnidade.getItems().add(testes);
                }
        );
    }
    public boolean confirmar() {
        boolean teste= false;

        Object[] options = {"Sim", "Não"};

        int opcao = JOptionPane.showOptionDialog(null, "Derteza que dezeja salvar a alteraçao feitas nessa unidade?",
                "Confirmaçao", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        for (int i = -1; i<options.length;i++ ) {
            if (opcao == 0) {
                teste = true;
            } else {
                teste = false;
            }
        }

        return teste;
    }
}


