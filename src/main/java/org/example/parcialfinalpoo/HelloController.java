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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private List<Compra> compras;

    private List<Tarjeta> tarjetas;

    private List<Cliente> clientes;

    private final Integer[] meses = {1,2,3,4,5,6,7,8,9,10,11,12};

    private final String[] facilitadores = {"visa", "MasterCard", "American Express"};

    @FXML
    private DatePicker Fechafinal;

    @FXML
    private DatePicker Fechainicio;

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

        comboMes.getItems().addAll(meses);

        comboFacilitadorTarjeta.getItems().addAll(facilitadores);
    }

    @FXML
    void addCliente(ActionEvent event) {

        Cliente cliente = new Cliente(-1, textNombreCliente.getText(), textDireccion.getText(), textTel.getText());

        DBController.getDBInstance().insertClient(cliente);

        clientes = DBController.getDBInstance().getClientes();
        idClienteM.clear();
        textNombreCliente.clear();
        textDireccion.clear();
        textTel.clear();

    }

    @FXML
    void addCompra(ActionEvent event) {

        for (Tarjeta tarjeta: tarjetas){

            if (tarjeta.getId() == Integer.parseInt(textTarjetaCompra.getText())){

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                try {

                    Date date = format.parse(textFechaCompra.getText());
                    Compra compra = new Compra(-1, date, Double.parseDouble(textMontoCompra.getText()), textDescripcionCompra.getText(), tarjeta);

                    DBController.getDBInstance().insertCompra(compra);
                    compras = DBController.getDBInstance().getCompras();


                }catch (Exception e){

                    throw new RuntimeException(e);

                }
            }
        }
    }

    @FXML
    void addTarjeta(ActionEvent event) {

        for (Cliente c: clientes) {

            if (c.getId() == Integer.parseInt(textClienteTarjeta.getText())) {

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    Date date = format.parse(textExpDate.getText());
                    Tarjeta tarjeta = new Tarjeta(-1, textNumTarjeta.getText(), date, getTipoTarjeta(), getFacilitadorTarjeta(), c);

                    DBController.getDBInstance().insertTarjeta(tarjeta);

                    tarjetas = DBController.getDBInstance().getTarjetas();

                    textClienteTarjeta.clear();
                    idTarjetaM.clear();
                    textNumTarjeta.clear();
                    textExpDate.clear();
                    typeToggleGroup.selectToggle(null);
                    facilitatorToggleGroup.selectToggle(null);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

    @FXML
    void createAReport(ActionEvent event) {
        String idcompra=textIdCompra.getText();

        for(Compra compra: compras){

            if(compra.getId()==Integer.parseInt(idcompra)){
                if ( compra.getFechaCompra().after(Datepickerconvertorinicio(Fechainicio)) && compra.getFechaCompra().before(Datepickerconvertorinicio(Fechafinal))){
                    System.out.println("ID: "+compra.getId()+" Fecha: "+compra.getFechaCompra()+" Descripción "+compra.getDescripcion()+" Tarjeta: "+compra.getTarjeta());
                }
            }
        }
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

        try {

            String clienteID = String.valueOf(textIdCliente.getText());

            if (clienteID.isEmpty()){
                System.out.println("Tiene que digitar un ID para buscar el cliente que se busca");
            }

            for (Cliente cliente: clientes){

                if (cliente.getId() == Integer.parseInt(textIdCliente.getText())){

                    DBController.getDBInstance().deleteCliente(Integer.parseInt(clienteID));
                    clientes = DBController.getDBInstance().getClientes();
                    textIdCliente.clear();
                }

            }

        }catch (Exception e){

            System.out.println(e);

        }

    }

    @FXML
    void deleteCompra(ActionEvent event) {

        try {

            String compraID = String.valueOf(textIdCompra.getText());

            if (compraID.isEmpty()){
                System.out.println("Tiene que digitar un ID para encontrar la compra que se busca");
            }

            for (Compra compra: compras){

                if (compra.getId() == Integer.parseInt(textIdCompra.getText())){

                    DBController.getDBInstance().deleteCompras(Integer.parseInt(compraID));
                    compras = DBController.getDBInstance().getCompras();
                    textIdCompra.clear();
                }

            }

        }catch (Exception e){

            System.out.println(e);

        }

    }

    @FXML
    void deleteTarjeta(ActionEvent event) {

        try {

            String tarjetaID = String.valueOf(textIdCompra.getText());

            if (tarjetaID.isEmpty()){
                System.out.println("Tiene que digitar un ID para encontrar la tarjeta que se busca");
            }

            for (Tarjeta tarjeta: tarjetas){

                if (tarjeta.getId() == Integer.parseInt(textIdTarjeta.getText())){

                    DBController.getDBInstance().deleteTarjeta(Integer.parseInt(tarjetaID));
                    tarjetas = DBController.getDBInstance().getTarjetas();
                    textIdCompra.clear();
                }

            }

        }catch (Exception e){

            System.out.println(e);

        }

    }

    @FXML
    void updateCliente(ActionEvent event) {

        DBController.getDBInstance().updateCliente(Integer.parseInt(idClienteM.getText()), textNombreCliente.getText(), textDireccion.getText(), textTel.getText());

        clientes = DBController.getDBInstance().getClientes();

        idClienteM.clear();
        textNombreCliente.clear();
        textDireccion.clear();
        textTel.clear();
    }

    @FXML
    void updateCompra(ActionEvent event) {

        for (Tarjeta t: tarjetas) {
            if (t.getId() == Integer.parseInt(textTarjetaCompra.getText())) {
                DBController.getDBInstance().updateCompra(Integer.parseInt(idCompraM.getText()), textFechaCompra.getText(), textMontoCompra.getText(), textDescripcionCompra.getText(), Integer.parseInt(textTarjetaCompra.getText()));
                compras = DBController.getDBInstance().getCompras();
                idCompraM.clear();
                textFechaCompra.clear();
                textMontoCompra.clear();
                textDescripcionCompra.clear();
                textTarjetaCompra.clear();
            }
        }

    }

    @FXML
    void updateTarjeta(ActionEvent event) {
        for (Cliente c: clientes){
            if(c.getId() == Integer.parseInt(textClienteTarjeta.getText())){
                DBController.getDBInstance().updateTarjeta(Integer.parseInt(idTarjetaM.getText()),textNumTarjeta.getText(), textExpDate.getText(), getTipoTarjeta(), getFacilitadorTarjeta(), Integer.parseInt(textClienteTarjeta.getText()));
                tarjetas = DBController.getDBInstance().getTarjetas();
                textClienteTarjeta.clear();
                idTarjetaM.clear();
                textNumTarjeta.clear();
                textExpDate.clear();
                typeToggleGroup.selectToggle(null);
                facilitatorToggleGroup.selectToggle(null);
                Fechainicio.getValue();
            }
        }
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
            return "MC";
        } else if (radioAmerican.isSelected()){
            return "AE";
        } else {
            throw new IllegalStateException("No se ha seleccionado ningún facilitador de tarjeta");
        }
    }


    public Date Datepickerconvertorinicio(DatePicker fecha){
        LocalDate localDate =fecha.getValue();
        if (localDate!=null){
            return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        else{
        return null;
        }
    }



}

