package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.CadEquipamento;

/**
 * Created by Ritchely on 01/06/2017.
 */
public class PesquisarUnidade {
    @FXML
    private TableView <CadEquipamento> tableView;
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
    private TableColumn <CadEquipamento,String> colunaTipo;
    @FXML
    private TableColumn <CadEquipamento,String> colunaMarca;
    @FXML
    private TableColumn <CadEquipamento,String> colunaUnidade;
    @FXML
    private TableColumn <CadEquipamento,String> colunaModelo;
    @FXML
    private ComboBox <String> comboEquipamento;




    public void selecionarEquipamento(ActionEvent actionEvent) {
    }

    public void verificar(ActionEvent actionEvent) {
    }

    public void exluir(ActionEvent actionEvent) {
    }

    public void editar(ActionEvent actionEvent) {
    }

    public void reserva(ActionEvent actionEvent) {
    }

    public void cancelar(ActionEvent actionEvent) {
    }
}
