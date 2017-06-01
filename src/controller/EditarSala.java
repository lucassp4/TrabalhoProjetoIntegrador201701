package controller;

import application.Main;
import dao.BlocoDao;
import dao.SalaDao;
import dao.UnidadeDao;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.CadastroBloco;
import model.Sala;
import model.Unidade;
import negocio.SalaNegocio;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Ritchely on 28/05/2017.
 */
public class EditarSala  implements Initializable{
    static Sala sala = new Sala();

    @FXML
    private Pane painelPrincipal;

    @FXML
    private ComboBox<String> comboUnidade;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtCapacidade;
    @FXML
    private ComboBox<String> comboBloco;
    @FXML
    private ComboBox<String> comboTipo;


    @FXML
    private Button btnEditar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Pane panielSala;

    @FXML
    private TableColumn colunaNome;
    @FXML
    private TableColumn colunaid;
    @FXML
    private TableColumn colunaUnidade;
    @FXML
    private TableColumn colunaTipo;

    @FXML
    private TableColumn colunaBloco;
    @FXML
    private  TableView tableSalas;


    ObservableList<Sala> preencherTabela = null;
    Main main = null;
    BlocoDao blocoDao = new BlocoDao();
    SalaDao salaDao = new SalaDao();
    List<Sala> listaSala = new ArrayList<Sala>();
    UnidadeDao unidadeDao = new UnidadeDao();
   SalaNegocio salaNegocio = new SalaNegocio();
    public EditarSala() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        preencherComboSala();
        preencherComboBloco();
        preencherUnidade();
        usuarioPesquisa(sala);

        setarValores(sala);



        listaSala = salaDao.listarSalas();
        populaView(listaSala);


    }

    public void editar() throws SQLException {

        boolean validar;
        boolean validarCapacidade;
        pegarValores();
        validarCapacidade = salaNegocio.verificarCampo(sala.getCapacidade());
        validar =  validarCampos();
        if (validar) {
            if(validarCapacidade){

                salaDao.editar(sala);

                exibeMensagem("Salvo com sucesso");

                URL arquivoFXML;
                arquivoFXML = getClass().getResource("/View/PaginaPrincipal.fxml");
                Parent fxmlParent;
                try {
                    fxmlParent = FXMLLoader.load(arquivoFXML);
                    painelPrincipal.getChildren().clear();
                    painelPrincipal.getChildren().add(fxmlParent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                exibeMensagem("Por favor digite uma capacidade valido!");

            }


        }}
    public void cancelar(){

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
    public void pegarValores(){
        sala.setId(Integer.parseInt(txtId.getText()));
        sala.setBloco(comboBloco.getValue());
        sala.setCapacidade(txtCapacidade.getText());
        sala.setNome(txtNome.getText());
        sala.setUnidade(comboUnidade.getValue());
        sala.setTipo(comboTipo.getValue());

    }

    public void exibeMensagem(String msg){
        Notifications.create()
                .title("Atensão")
                .text(String.valueOf(msg))
                .owner(main)
                .hideAfter(Duration.seconds(3))
                .darkStyle()
                .position(Pos.TOP_RIGHT)
                .showInformation();


    }
    public void preencherUnidade(){
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
    public void preencherComboSala(){

        comboTipo.getItems().add("Sala de aula");
        comboTipo.getItems().add("Laboratorio");
        comboTipo.getItems().add("Auditorio");

    }


    public boolean validarCampos(){

        String nome =  sala.getNome();
        String unidade = sala.getUnidade();
        String capacidade = sala.getCapacidade();
        String tipo = sala.getTipo();
        String bloco = sala.getBloco();

        List<Control> controls = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("");
        if(nome.equals("") || nome == null){
            sb.append("O nome não pode ser vazio!. \n");
            controls.add(txtNome);
        }
        if(unidade == null){
            sb.append(" A por favor selecione uma unidade !. \n");
            controls.add(comboUnidade);
        }
        if(capacidade.equals("") || capacidade == null){
            sb.append("O Numero de sala não pode ser vazio!. \n");
            controls.add(txtCapacidade);
        }
        if(unidade == null){
            sb.append(" A por favor selecione uma unidade !. \n");
            controls.add(comboUnidade);
        }
        if(tipo == null){
            sb.append("O Numero de sala não pode ser vazio!. \n");
            controls.add(comboTipo);
        }
        if(bloco == null){
            sb.append("O Numero de sala não pode ser vazio!. \n");
            controls.add(comboBloco);
        }

        if(!sb.equals("")) {
            exibeMensagem(sb.toString());
            animaCamposValidados(controls);
        }

        return sb.toString().isEmpty();
    }

    public void preencherComboBloco() {
        List<String> teste = new ArrayList<String>();
        List<CadastroBloco> salas = new ArrayList<CadastroBloco>();
        salas = blocoDao.listarClientes();
        salas.forEach((CadastroBloco cliente) -> {
                    teste.add(cliente.getNome());
                }


        );
        teste.forEach((String testes) -> {
                    comboBloco.getItems().add(testes);
                }
        );
    }

    public void animaCamposValidados(List<Control> controls) {
        controls.forEach(control -> {
            RotateTransition rotateTransition = new RotateTransition(Duration.millis(60), control);
            rotateTransition.setFromAngle(-4);
            rotateTransition.setToAngle(4);
            rotateTransition.setCycleCount(8);
            rotateTransition.setAutoReverse(true);
            rotateTransition.setOnFinished((ActionEvent event1) ->{
                control.setRotate(0);
            });
            rotateTransition.play();
        });
        if(!controls.isEmpty()){
            controls.get(0).requestFocus();
        }
    }

    public void setarValores(Sala sala) {

        txtId.setText(String.valueOf(sala.getId()));
        txtCapacidade.setText(sala.getCapacidade());
        txtNome.setText(sala.getNome());
        comboBloco.setValue(sala.getBloco());
        comboTipo.setValue(sala.getTipo());
        comboUnidade.setValue(sala.getUnidade());
    }
    public void populaView(List< Sala > salas){


            colunaNome.setCellValueFactory(new PropertyValueFactory<Sala, String>("nome"));
            colunaBloco.setCellValueFactory(new PropertyValueFactory<Sala, String>("bloco"));
            colunaTipo.setCellValueFactory(new PropertyValueFactory<Sala, String>("tipo"));
            colunaUnidade.setCellValueFactory(new PropertyValueFactory<Sala, String>("unidade"));
            colunaid.setCellValueFactory(new PropertyValueFactory<Sala, Integer>("id"));
            preencherTabela = FXCollections.observableArrayList(sala);
            tableSalas.setItems(preencherTabela);

        }



    public void selecionarSala() {
        sala = (Sala) tableSalas.getSelectionModel().getSelectedItem();
        setarValores(sala);
    }
    @FXML
    public void usuarioPesquisa(Sala se){
    sala = se;
    }
    public static void setSala(Sala sa){
        sala = sa;
    }
}

