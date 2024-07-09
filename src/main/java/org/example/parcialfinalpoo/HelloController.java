package org.example.parcialfinalpoo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.example.parcialfinalpoo.Clases.Cliente;
import org.example.parcialfinalpoo.Clases.Compra;
import org.example.parcialfinalpoo.Clases.Tarjeta;
import org.example.parcialfinalpoo.DB.DBController;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private List<Compra> compras = DBController.getDBInstance().getCompras();

    private List<Tarjeta> tarjetas = DBController.getDBInstance().getTarjetas();

    private List<Cliente> clientes = DBController.getDBInstance().getClientes();

    @FXML
    private DatePicker Fechafinal;

    @FXML
    private DatePicker Fechainico;

    @FXML
    private Button buttonAddCliente;

    @FXML
    private Button buttonAddTarjeta;

    @FXML
    private Button buttonChangeCliente;

    @FXML
    private Button buttonChangeCliente1;

    @FXML
    private Button buttonCrearCompra;

    @FXML
    private Button buttonDeleteCompra;

    @FXML
    private Button buttonDeleteTarjeta;

    @FXML
    private AnchorPane buttonEraseCliente;

    @FXML
    private Button buttonGenerarA;

    @FXML
    private Button buttonGenerarB;

    @FXML
    private Button buttonGenerarC;

    @FXML
    private Button buttonGenerarD;

    @FXML
    private Button buttonModificarCompra;

    @FXML
    private Button buttonModificarTarjeta;

    @FXML
    private ComboBox<String> comboFacilitadorTarjeta;

    @FXML
    private ComboBox<Integer> comboMes;

    @FXML
    private TextField idCLienteb;

    @FXML
    private TextField idClienteA;

    @FXML
    private TextField idClienteC;

    @FXML
    private TextField idClienteM;

    @FXML
    private TextField idCompraM;

    @FXML
    private TextField idTarjetaM;

    @FXML
    private RadioButton radioAmerican;

    @FXML
    private RadioButton radioMasterCard;

    @FXML
    private RadioButton radioTipoCredito;

    @FXML
    private RadioButton radioTipoDebito;

    @FXML
    private RadioButton radioVisa;

    @FXML
    private TextField textAnio;

    @FXML
    private TextField textClienteTarjeta;

    @FXML
    private TextField textDescripcionCompra;

    @FXML
    private TextField textDireccion;

    @FXML
    private TextField textExpDate;

    @FXML
    private TextField textFechaCompra;

    @FXML
    private TextField textIdCliente;

    @FXML
    private TextField textIdCompra;

    @FXML
    private TextField textIdTarjeta;

    @FXML
    private TextField textMontoCompra;

    @FXML
    private Label textMontoCompra1;

    @FXML
    private TextField textNombreCliente;

    @FXML
    private TextField textNumTarjeta;

    @FXML
    private TextField textTarjetaCompra;

    @FXML
    private TextField textTel;

    @FXML
    private Label total;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void addCliente(ActionEvent event) {

    }

    @FXML
    void addCompra(ActionEvent event) {

    }

    @FXML
    void addTarjeta(ActionEvent event) {

    }

    @FXML
    void createAReport(ActionEvent event) {

    }

    @FXML
    void createBReport(ActionEvent event) {

    }

    @FXML
    void createCReport(ActionEvent event) {

    }

    @FXML
    void createDReport(ActionEvent event) {

    }

    @FXML
    void deleteCliente(ActionEvent event) {

    }

    @FXML
    void deleteCompra(ActionEvent event) {

    }

    @FXML
    void deleteTarjeta(ActionEvent event) {

    }

    @FXML
    void updateCliente(ActionEvent event) {

    }

    @FXML
    void updateCompra(ActionEvent event) {

    }

    @FXML
    void updateTarjeta(ActionEvent event) {

    }

}

