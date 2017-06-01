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
import javafx.scene.input.MouseEvent;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

/**
 * Created by Ritchely on 01/06/2017.
 */
public class ExcluirEquipamento implements Initializable {


        @FXML
        private model.CadEquipamento equipamento;

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
        private TableView<model.CadEquipamento> tblEquipamentos;

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

        CadEquipamento cadEquipamento =  new CadEquipamento();
        ObservableList<CadEquipamento> equipamentoView = null;

        EquipamentoNegocio equipamentoNegocio = new EquipamentoNegocio();

        private ObservableList<String> originalItems;

        UnidadeDao unidadeDao = new UnidadeDao();

        public ExcluirEquipamento() throws SQLException {
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



        private void setaValores(CadEquipamento equipamento) {

            txtId.setText(String.valueOf(equipamento.getId()));
            txtTipo.setText(equipamento.getTipo());
            txtModelo.setText(equipamento.getModelo());
            txtMarca.setText(equipamento.getModelo());
            dataCadastro.setValue(equipamento.getDataCadastro());
            comboUnidade.setValue(equipamento.getUnidade());

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
                boolean confirmar;
                boolean sairT;

                confirmar = confirmar();
            sairT = sair();

                if(confirmar) {
                    equipamentoDAO.excluir(equipamento);
                    if(sairT) {

                        equipamentoDAO.excluir(equipamento);

                        String msg = "Equipamento excluido!";
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
                    }else{


                        CadEquipamento = listarEquipamento();

                        populaView(CadEquipamento);

                    }
                }else{

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

            int opcao = JOptionPane.showOptionDialog(null, "Tem certeza da exclusao desse equipamento?",
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
    public boolean sair() {
        boolean teste= false;

        Object[] options = {"Sim", "Não"};

        int opcao = JOptionPane.showOptionDialog(null, "Deseja sair da tela de exclusão?",
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
    public void excluir(MouseEvent mouseEvent) {
        equipamento = (CadEquipamento) tblEquipamentos.getSelectionModel().getSelectedItem();
        setaValores(equipamento);
    }
}




