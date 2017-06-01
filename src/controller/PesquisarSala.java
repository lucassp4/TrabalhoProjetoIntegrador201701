package controller;

import dao.SalaDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Window;
import model.Sala;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

/**
 * Created by Ritchely on 01/06/2017.
 */
public class PesquisarSala implements Initializable{


    EditarSala editar = new EditarSala();
    Sala salaSelecionada = new Sala();
    ReservarSala reservarSala = new ReservarSala();
    SalaDao excluirS = new SalaDao();
    
    private ObservableList<String> originalItems;
    String filter = "";
    SalaDao salaDao = new SalaDao();
    List<Sala> salas = new ArrayList<Sala>();
    @FXML
    private Pane painelPrincipal;
    @FXML
    private ComboBox <String> comboSala;
    @FXML
    private TableView <Sala> tableSala;
    @FXML
    private TableColumn<Sala, String> colunaUnidade;
    @FXML
    private TableColumn<Sala, String> colunaTipo;
    @FXML
    private TableColumn <Sala, String> colunaNumero;
    @FXML
    private TableColumn <Sala, String> colunaCapacidade;
    @FXML
    private TableColumn <Sala, String> colunaBloco;
    @FXML
    private TableColumn<Sala, Integer> colunaId;
    ObservableList<Sala> salaObservableList=null;

    public PesquisarSala() throws SQLException {
    }


    public Sala comboSala() {

        String nome = comboSala.getSelectionModel().getSelectedItem();
        salas.forEach(numeroSala ->{
            if(nome.equals(numeroSala.getNome())){
                salaSelecionada = numeroSala;
            }
        } );
        return salaSelecionada;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    preencherCombo();
    

    }

    public void populaView(List< Sala > sala){


        colunaNumero.setCellValueFactory(new PropertyValueFactory<Sala, String>("nome"));
        colunaBloco.setCellValueFactory(new PropertyValueFactory<Sala, String>("bloco"));
        colunaCapacidade.setCellValueFactory(new PropertyValueFactory<Sala, String>("capacidade"));
        colunaTipo.setCellValueFactory(new PropertyValueFactory<Sala, String>("tipo"));
        colunaUnidade.setCellValueFactory(new PropertyValueFactory<Sala, String>("unidade"));
        colunaId.setCellValueFactory(new PropertyValueFactory<Sala, Integer>("id"));

        salaObservableList = FXCollections.observableArrayList(salaSelecionada);
        tableSala.setItems(salaObservableList);

    }

    public void excluir(ActionEvent actionEvent) throws Exception {
       boolean confirmar;
       boolean confirmarC;

       confirmar = confirmar();
       confirmarC = confirmarConfirmar();
        if(confirmar) {
            excluirS.excluir(salaSelecionada);
            if (confirmarC) {

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
        }
    }

    private void excluirS(int id) {
    }

    public void reserva(ActionEvent actionEvent) {

        reservarSala.usuarioPesquisa(salaSelecionada);

        URL arquivoFXML;
        arquivoFXML = getClass().getResource("/View/ReservarSala.fxml");
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
       editar.usuarioPesquisa(salaSelecionada);

        URL arquivoFXML;
        arquivoFXML = getClass().getResource("/View/EditarSala.fxml");
        Parent fxmlParent;
        try {
            fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
            painelPrincipal.getChildren().clear();
            painelPrincipal.getChildren().add(fxmlParent);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void cancelar(ActionEvent actionEvent) {

    }
        public void preencherCombo(){
            List<String> teste = new ArrayList<String>();

            salas = salaDao.listarSalas();
            salas.forEach( (Sala cliente) -> {
                        teste.add(cliente.getNome());
                    }


            );
            teste.forEach((String testes)->{
                        comboSala.getItems().add(testes);
                    }
            );

        }

    public void AutoCompleteCliente(){
        originalItems = FXCollections.observableArrayList(comboSala.getItems());
        comboSala.setTooltip(new Tooltip());
        comboSala.setOnKeyPressed(this::handleOnKeyPressed);
        comboSala.setOnHidden(this::handleOnHiding);
    }

    public void handleOnHiding(Event e) {
        filter = "";
        comboSala.getTooltip().hide();
        String s = comboSala.getSelectionModel().getSelectedItem();
        comboSala.getItems().setAll(originalItems);
        comboSala.getSelectionModel().select(s);
    }

    public void handleOnKeyPressed(KeyEvent e) {
        ObservableList<String> filteredList = FXCollections.observableArrayList();
        KeyCode code = e.getCode();

        if (code.isLetterKey()) {
            filter += e.getText();
        }
        if (code == KeyCode.BACK_SPACE && filter.length() > 0) {
            filter = filter.substring(0, filter.length() - 1);
            comboSala.getItems().setAll(originalItems);
        }
        if (code == KeyCode.ESCAPE) {
            filter = "";
        }
        if (filter.length() == 0) {
            filteredList = originalItems;
            comboSala.getTooltip().hide();
        } else {
            Stream<String> itens = comboSala.getItems().stream();
            String txtUsr = filter.toString().toLowerCase();
            itens.filter(el -> el.toString().toLowerCase().contains(txtUsr)).forEach(filteredList::add);
            comboSala.getTooltip().setText(txtUsr);
            Window stage = comboSala.getScene().getWindow();
            double posX = stage.getX() + comboSala.getBoundsInParent().getMinX();
            double posY = stage.getY() + comboSala.getBoundsInParent().getMinY();
            comboSala.getTooltip().show(stage, posX, posY);
            comboSala.show();
        }
        comboSala.getItems().setAll(filteredList);
    }


    public void selecionarSala(MouseEvent mouseEvent) {
    }

    public void verificar(ActionEvent actionEvent) {
        salaSelecionada = comboSala();

        populaView(salas);
    }
    public Sala pegar(Sala salas){
    salas = this.salaSelecionada;
    editar.usuarioPesquisa(salas);
    return salas;
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

        int opcao = JOptionPane.showOptionDialog(null, "Deseja sair da tela de pesquisa mais salas?",
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
