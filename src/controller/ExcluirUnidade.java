package controller;

import application.Main;
import dao.UnidadeDAO;
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
import model.Unidade;
import org.controlsfx.control.Notifications;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Ritchely on 28/05/2017.
 */
public class ExcluirUnidade implements Initializable {
    @FXML
    private Pane painelPrincipal;

    @FXML
    private TextField txtNomeUnidade;

    @FXML
    private TextField txtFantacia;

    @FXML
    private TextField txtCep;
    @FXML
    private TextField txtEstado;
    @FXML
    private TextField txtCidade;

    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtRazao;

    @FXML
    private TextField txtCnpj;

    @FXML
    private TextField txtTelefone;


    @FXML
    private TextField txtId;
    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnCancelar;
    @FXML
    private TableView tableUnidade;
    @FXML
    private TableColumn colunaNome;
    @FXML
    private TableColumn colunaId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        listaUnidade = unidadeDao.listarClientes();

        populaView(listaUnidade);
    }

    @FXML
    private TableColumn colunaEndereco;
    @FXML
    private TableColumn colunaTelefone;
    ObservableList<Unidade> preencherTabela = null;
    UnidadeDAO unidadeDao = new UnidadeDAO();
    List<Unidade> listaUnidade = new ArrayList<Unidade>();
    Unidade unidadeM = new Unidade();
    Main main = null;

    public ExcluirUnidade() throws SQLException {
    }


    public void btnExcluir() throws SQLException {

        pegarValores();

        boolean confirmarExlusão;
        boolean confirmarMais;
        confirmarExlusão = confirmar();
        confirmarMais = confirmarMaisExclusao();
                        if (confirmarExlusão) {
                            unidadeDao.excluir(unidadeM);

                            if(confirmarMais) {
                                preencherT();

                            }else {
                                exibeMensagem("Unidade Excluida com sucesso");
                            }
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
                            }else{

                        }

                        }



    public void btnCancelar() {

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
    }

    public void pegarValores() {
        unidadeM.setId(Integer.parseInt(txtId.getText()));
        unidadeM.setNome(txtNomeUnidade.getText());
        unidadeM.setEndereco(txtEndereco.getText());
        unidadeM.setCnpj(txtCnpj.getText());
        unidadeM.setRazaoSocial(txtRazao.getText());
        unidadeM.setTelefone(txtTelefone.getText());
        unidadeM.setCep(txtCep.getText());
        unidadeM.setCidade(txtCidade.getText());
        unidadeM.setEstado(txtEstado.getText());
        unidadeM.setFantacia(txtFantacia.getText());


    }

    public void populaView(List<Unidade> clientes) {
        colunaId.setCellValueFactory(new PropertyValueFactory<Unidade, String>("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<Unidade, String>("nome"));
        colunaEndereco.setCellValueFactory(new PropertyValueFactory<Unidade, String>("endereco"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<Unidade, String>("telefone"));

        preencherTabela = FXCollections.observableArrayList(clientes);
        tableUnidade.setItems(preencherTabela);

    }


    public void exibeMensagem(String msg) {
        Notifications.create()
                .title("Atensão")
                .text(String.valueOf(msg))
                .owner(main)
                .hideAfter(Duration.seconds(1))
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

    public void setarValor(Unidade unidadeM) {
        txtId.setText(String.valueOf(unidadeM.getId()));
        txtCep.setText(unidadeM.getCep());
        txtCidade.setText(unidadeM.getCidade());
        txtCnpj.setText(unidadeM.getCnpj());
        txtEndereco.setText(unidadeM.getEndereco());
        txtEstado.setText(unidadeM.getEstado());
        txtFantacia.setText(unidadeM.getFantacia());
        txtNomeUnidade.setText(unidadeM.getNome());
        txtRazao.setText(unidadeM.getRazaoSocial());
        txtTelefone.setText(unidadeM.getTelefone());
    }

    public void mouseClieked() {

        unidadeM = (Unidade) tableUnidade.getSelectionModel().getSelectedItem();
        setarValor(unidadeM);
    }

    public boolean confirmar() {
        boolean teste= false;

        Object[] options = {"Sim", "Não"};

        int opcao = JOptionPane.showOptionDialog(null, "Tem certeza da exclusao dessa unidade?",
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
    public boolean confirmarMaisExclusao(){

            boolean teste= false;

            Object[] options = {"Sim", "Não"};

            int opcao = JOptionPane.showOptionDialog(null, "Exluir mais unidade?",
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
        public void preencherT(){
            listaUnidade = unidadeDao.listarClientes();

            populaView(listaUnidade);
        }
    }

