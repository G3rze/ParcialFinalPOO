package org.example.parcialfinalpoo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.example.parcialfinalpoo.Clases.Cliente;
import org.example.parcialfinalpoo.Clases.Compra;
import org.example.parcialfinalpoo.Clases.Tarjeta;
import org.example.parcialfinalpoo.DB.DBController;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private List<Compra> compras;

    private List<Tarjeta> tarjetas;

    private List<Cliente> clientes;

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

    @FXML
    private ToggleGroup typeToggleGroup;

    @FXML
    private ToggleGroup facilitatorToggleGroup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        typeToggleGroup = new ToggleGroup();
        facilitatorToggleGroup = new ToggleGroup();

        radioTipoDebito.setToggleGroup(typeToggleGroup);
        radioTipoCredito.setToggleGroup(typeToggleGroup);

        radioAmerican.setToggleGroup(facilitatorToggleGroup);
        radioVisa.setToggleGroup(facilitatorToggleGroup);
        radioMasterCard.setToggleGroup(facilitatorToggleGroup);

        compras = DBController.getDBInstance().getCompras();

        tarjetas = DBController.getDBInstance().getTarjetas();

        clientes = DBController.getDBInstance().getClientes();
    }

    @FXML
    void addCliente(ActionEvent event) {

        Cliente cliente = new Cliente(-1, textNombreCliente.getText(), textDireccion.getText(), textTel.getText());

        DBController.getDBInstance().insertClient(cliente);

        clientes = DBController.getDBInstance().getClientes();

    }

    @FXML
    void addCompra(ActionEvent event) {


    }

    @FXML
    void addTarjeta(ActionEvent event) {

        for (Cliente c: clientes) {
            if (c.getId() == Integer.getInteger(textClienteTarjeta.getText())) {
                SimpleDateFormat format = new SimpleDateFormat("MM-yyyy");

                try {
                    Date date = format.parse(textExpDate.getText());
                    Tarjeta tarjeta = new Tarjeta(-1, textNumTarjeta.getText(), date, getTipoTarjeta(), getFacilitadorTarjeta(), c);

                    DBController.getDBInstance().insertTarjeta(tarjeta);

                    tarjetas = DBController.getDBInstance().getTarjetas();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }

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

    public char getTipoTarjeta() {
        if (radioTipoCredito.isSelected()) {
            return 'C';
        } else if (radioTipoDebito.isSelected()) {
            return 'D';
        } else {
            throw new IllegalStateException("No se ha seleccionado ningún tipo de tarjeta");
        }
    }

    public String getFacilitadorTarjeta() {
        if (radioVisa.isSelected()) {
            return "Visa";
        } else if (radioMasterCard.isSelected()) {
            return "MasterCard";
        } else if (radioAmerican.isSelected()){
            return "American Express";
        } else {
            throw new IllegalStateException("No se ha seleccionado ningún facilitador de tarjeta");
        }
    }

}

