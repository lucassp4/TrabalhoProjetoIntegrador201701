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
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.CadastroBloco;
import model.Sala;
import model.Unidade;
import negocio.SalaNegocio;
import org.controlsfx.control.Notifications;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Ritchely on 29/05/2017.
 */
public class ExcluirSala implements Initializable {

    Sala sala = new Sala();
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
    private TableView tableSalas;

    ObservableList<Sala> preencherTabela = null;
    Main main = null;
    SalaDao salaDao = new SalaDao();
    List<Sala> listaSala = new ArrayList<Sala>();
    SalaNegocio salaNegocio = new SalaNegocio();

    public ExcluirSala() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        listaSala = salaDao.listarSalas();
        populaView(listaSala);


    }

    public void editar() throws SQLException {
        boolean confirmaS;
        boolean confirmaCon ;
        pegarValores();
        confirmaS = confirmar();
        confirmaCon = confirmarConfirmar();


        if(confirmaS) {

            if(confirmaCon) {

                salaDao.excluir(sala);
                listaSala = salaDao.listarSalas();
                populaView(listaSala);

            }else{
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

        }else{

        }
    }
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

    public void setarValores(Sala sala) {
        txtId.setText(String.valueOf(sala.getId()));
        txtCapacidade.setText(sala.getCapacidade());
        txtNome.setText(sala.getNome());
        comboBloco.setValue(sala.getBloco());
        comboTipo.setValue(sala.getTipo());
        comboUnidade.setValue(sala.getUnidade());
    }


    public void populaView(List< Sala > sala){


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


    public boolean confirmar() {
        boolean teste= false;

        Object[] options = {"Sim", "Não"};

        int opcao = JOptionPane.showOptionDialog(null, "Tem certeza que dezeja excluir essa sala?",
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

    public boolean confirmarConfirmar() {
        boolean teste= false;

        Object[] options = {"Sim", "Não"};

        int opcao = JOptionPane.showOptionDialog(null, "Deseja excluir mais salas?",
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
    @FXML
    public void usuarioPesquisa(Sala se){
        sala = se;
    }
}




