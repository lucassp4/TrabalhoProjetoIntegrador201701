package controller;

import dao.EquipamentoDAO;
import dao.SalaDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Window;
import model.CadEquipamento;
import model.ReservaEquipamento;
import model.Sala;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

/**
 * Created by Ritchely on 01/06/2017.
 */
public class PesquisarEquipamento implements Initializable {
    EditarEquipamento editarEquipamento = new EditarEquipamento();
    @FXML
    private Pane painelPrincipal;
    private ObservableList<String> originalItems;
    String filter = "";


    @FXML
    private TableView<CadEquipamento> tableView;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnReserva;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnVerifica;
    @FXML
    private TableColumn<CadEquipamento, String> colunaTipo;
    @FXML
    private TableColumn<CadEquipamento, String> colunaMarca;
    @FXML
    private TableColumn<CadEquipamento, String> colunaUnidade;
    @FXML
    private TableColumn<CadEquipamento, String> colunaModelo;
    @FXML
    private ComboBox<String> comboEquipamento;

    EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
    List<CadEquipamento> cadEquipamento = new ArrayList<CadEquipamento>();

    ObservableList<CadEquipamento> equipamento = null;

    CadEquipamento cadeEquipamento = new CadEquipamento();

    public PesquisarEquipamento() throws SQLException {
    }

    public void preencherComboEquipamento() {

        List<String> teste = new ArrayList<String>();
        List<CadEquipamento> unidade = new ArrayList<CadEquipamento>();
        unidade = equipamentoDAO.listarEquipamento();
        unidade.forEach((CadEquipamento cliente) -> {
                    teste.add(cliente.getTipo());
                }


        );
        teste.forEach((String testes) -> {
                    comboEquipamento.getItems().add(testes);
                }
        );
    }


    public void populaView(List<CadEquipamento> equipamentos) {


        colunaTipo.setCellValueFactory(new PropertyValueFactory<CadEquipamento, String>("tipo"));
        colunaMarca.setCellValueFactory(new PropertyValueFactory<CadEquipamento, String>("marca"));
        colunaModelo.setCellValueFactory(new PropertyValueFactory<CadEquipamento, String>("modelo"));
        colunaUnidade.setCellValueFactory(new PropertyValueFactory<CadEquipamento, String>("unidade"));

        equipamento = FXCollections.observableArrayList(cadeEquipamento);
        tableView.setItems(equipamento);
    }

    public void AutoCompleteCliente() {
        originalItems = FXCollections.observableArrayList(comboEquipamento.getItems());
        comboEquipamento.setTooltip(new Tooltip());
        comboEquipamento.setOnKeyPressed(this::handleOnKeyPressed);
        comboEquipamento.setOnHidden(this::handleOnHiding);
    }

    public void handleOnHiding(Event e) {
        filter = "";
        comboEquipamento.getTooltip().hide();
        String s = comboEquipamento.getSelectionModel().getSelectedItem();
        comboEquipamento.getItems().setAll(originalItems);
        comboEquipamento.getSelectionModel().select(s);
    }

    public void handleOnKeyPressed(KeyEvent e) {
        ObservableList<String> filteredList = FXCollections.observableArrayList();
        KeyCode code = e.getCode();

        if (code.isLetterKey()) {
            filter += e.getText();
        }
        if (code == KeyCode.BACK_SPACE && filter.length() > 0) {
            filter = filter.substring(0, filter.length() - 1);
            comboEquipamento.getItems().setAll(originalItems);
        }
        if (code == KeyCode.ESCAPE) {
            filter = "";
        }
        if (filter.length() == 0) {
            filteredList = originalItems;
            comboEquipamento.getTooltip().hide();
        } else {
            Stream<String> itens = comboEquipamento.getItems().stream();
            String txtUsr = filter.toString().toLowerCase();
            itens.filter(el -> el.toString().toLowerCase().contains(txtUsr)).forEach(filteredList::add);
            comboEquipamento.getTooltip().setText(txtUsr);
            Window stage = comboEquipamento.getScene().getWindow();
            double posX = stage.getX() + comboEquipamento.getBoundsInParent().getMinX();
            double posY = stage.getY() + comboEquipamento.getBoundsInParent().getMinY();
            comboEquipamento.getTooltip().show(stage, posX, posY);
            comboEquipamento.show();
        }
        comboEquipamento.getItems().setAll(filteredList);
    }


    public CadEquipamento selecionarEquipamento() {
        String nome = comboEquipamento.getSelectionModel().getSelectedItem();
        cadEquipamento = equipamentoDAO.listarEquipamento();
        cadEquipamento.forEach(numero -> {
            if (nome.equals(numero.getTipo())) {
                cadeEquipamento = numero;
            }
        });
        return cadeEquipamento;

    }


    public void verificar() {
        populaView(cadEquipamento);
    }

    public void exluir(ActionEvent actionEvent) {

            equipamentoDAO.excluir(cadeEquipamento);

                URL arquivoFXML;
                arquivoFXML = getClass().getResource("/View/PaginaPrincipal.fxml");
                Parent fxmlParent;
                try {
                    fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
                    painelPrincipal.getChildren().clear();
                    painelPrincipal.getChildren().add(fxmlParent);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }




    public void editar(ActionEvent actionEvent) {

        URL arquivoFXML;
        arquivoFXML = getClass().getResource("/View/EditarEquipamento.fxml");
        Parent fxmlParent;
        try {
            fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
            painelPrincipal.getChildren().clear();
            painelPrincipal.getChildren().add(fxmlParent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }









    public void reserva(ActionEvent actionEvent) {
        URL arquivoFXML;
        arquivoFXML = getClass().getResource("/View/ReservarEquipamento.fxml");
        Parent fxmlParent;
        try {
            fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
            painelPrincipal.getChildren().clear();
            painelPrincipal.getChildren().add(fxmlParent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void cancelar(ActionEvent actionEvent) { URL arquivoFXML;
        arquivoFXML = getClass().getResource("/View/PaginaPrincipal.fxml");
        Parent fxmlParent;
        try {
            fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
            painelPrincipal.getChildren().clear();
            painelPrincipal.getChildren().add(fxmlParent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherComboEquipamento();
    }

}





