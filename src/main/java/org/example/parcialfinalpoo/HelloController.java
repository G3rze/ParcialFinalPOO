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
import org.example.parcialfinalpoo.filesystem.FileSystem;

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

    private final FileSystem fl = new FileSystem();

    @FXML
    private DatePicker Fechafinal;

    @FXML
    private DatePicker Fechainicio;

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
    private TextField textNombreCliente;

    @FXML
    private TextField textNumTarjeta;

    @FXML
    private TextField textTarjetaCompra;

    @FXML
    private TextField textTel;

    @FXML
    private ToggleGroup typeToggleGroup;

    @FXML
    private ToggleGroup facilitatorToggleGroup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //00055623 el la primera función que corre al inicial el programa

        typeToggleGroup = new ToggleGroup(); //00055623 Crea un toggle group para manejar los tipos de tarjetas
        facilitatorToggleGroup = new ToggleGroup(); //00055623 Crea un toggle group para manejar los facilitadores de la tarjetas

        radioTipoDebito.setToggleGroup(typeToggleGroup); //00055623 Agrega el tipo credito al toggle group
        radioTipoCredito.setToggleGroup(typeToggleGroup); //00055623 Agrega el tipo debito al toggle group

        radioAmerican.setToggleGroup(facilitatorToggleGroup); //00055623 Agrega el facilitador American Express a su toggle group
        radioVisa.setToggleGroup(facilitatorToggleGroup); //00055623 Agrega el facilitador vida a su toggle group
        radioMasterCard.setToggleGroup(facilitatorToggleGroup); //00055623Agrega el facilitador MasterCard a su toggle group

        compras = DBController.getDBInstance().getCompras(); //00055623 Inicializa la lista de compras con las tablas existente en la DB

        tarjetas = DBController.getDBInstance().getTarjetas(); //00055623 Inicializa la lista de Tarjetas con la tabla existente en la DB

        clientes = DBController.getDBInstance().getClientes(); //00055623 Inicializa la lista de Clientes con la tabla existente en la DB

        comboMes.getItems().addAll(meses); //00055623 Establece las opciones a mostrar de los meses

        comboFacilitadorTarjeta.getItems().addAll(facilitadores); //00055623 Establece las opciones a mostar en los facilitadores de tarjetas
    }

    @FXML
    void addCliente(ActionEvent event) { //00055623 Funcion llamada cuando se acciona el boton de agregar cliente

        Cliente cliente = new Cliente(-1, textNombreCliente.getText(), textDireccion.getText(), textTel.getText()); //00055623 se crea el nuevo cliente a ingresar

        DBController.getDBInstance().insertClient(cliente); //00055623 Se ingresa el nuevo cliente a la base de datos

        clientes = DBController.getDBInstance().getClientes(); //00055623 Se actualiza la lista clientes con los cambios hechos en la base de datos
        idClienteM.clear(); //00055623 Se limpia el campo
        textNombreCliente.clear(); //00055623 Se limpia el campo
        textDireccion.clear(); //00055623 Se limpia el campo
        textTel.clear(); //00055623 Se limpia el campo

    }

    @FXML
    void addCompra(ActionEvent event) { //00055623 Funcion llamada cuando se acciona el boton de agregar compra

        for (Tarjeta tarjeta: tarjetas){ //00055623 Se inicia una busqueda en la lista de Tarjetas

            if (tarjeta.getId() == Integer.parseInt(textTarjetaCompra.getText())){ //00055623 Se valida que la tarjeta ingresada exista

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //00055623 Se establece el formato de la fecha de compra

                try {

                    Date date = format.parse(textFechaCompra.getText()); //00055623 Se crea un objeto fecha para ingresarlo al nuevo objeto compra
                    Compra compra = new Compra(-1, date, Double.parseDouble(textMontoCompra.getText()), textDescripcionCompra.getText(), tarjeta); //00055623 Se prepara un nuevo objeto compra para ingresarlo a la base de datos

                    DBController.getDBInstance().insertCompra(compra); //00055623 Se ingresa el objeto a la base de datos
                    compras = DBController.getDBInstance().getCompras(); //00055623 Se actualiza la lista compras con el nuevo registro en la base de datos


                }catch (Exception e){ //00055623 Atraba el posible error

                    throw new RuntimeException(e); //00055623 Devuelve el error

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
        String content = "";
        String title = "A-";
        String idcompra=idClienteA.getText();
        Date actual = new Date();
        boolean exist = false;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");

        for(Compra compra: compras){

            if(compra.getTarjeta().getCliente().getId()==Integer.parseInt(idcompra)){
                if ( (compra.getFechaCompra().after(Datepickerconvertorinicio(Fechainicio)) || compra.getFechaCompra().equals(Datepickerconvertorinicio(Fechainicio))) && (compra.getFechaCompra().before(Datepickerconvertorinicio(Fechafinal)) || compra.getFechaCompra().equals(Datepickerconvertorinicio(Fechafinal)))){
                    content = content + ("ID: " + compra.getId() + " Fecha: " + compra.getFechaCompra() + " Descripción " + compra.getDescripcion() + " Tarjeta: " + compra.getTarjeta().getNumeroTarjeta() + "\n");
                    exist = true;
                }
            }
        }
        if(exist){
            title += format.format(actual) + ".txt";
            fl.CreateFile(title, content);
        }
    }

    @FXML
    void createBReport(ActionEvent event) {

        String idCliente = idCLienteb.getText();
        String content = "";
        String title = "B-";

        float total = 0;

        boolean exist = false;

        Date actual = new Date();
        Date cDate;

        SimpleDateFormat format = new SimpleDateFormat("MM-yyyy");
        SimpleDateFormat titleFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
        SimpleDateFormat contentFortmat = new SimpleDateFormat("MMMM-yyyy");

        try {
            cDate = format.parse(String.valueOf(comboMes.getValue()) + "-" + textAnio.getText());
            String cDateString = format.format(cDate);
            for (Compra c: compras){

                String cTableDate = format.format(c.getFechaCompra());

                if((c.getTarjeta().getCliente().getId() == Integer.parseInt(idCliente)) && cDateString.equals(cTableDate) ){
                    exist = true;
                    total += (float) c.getMontoTotal();
                }
            }
            if(exist){
                for (Cliente c: clientes) {
                    if (c.getId() == Integer.parseInt(idCliente) ) {
                        title += titleFormat.format(actual) + ".txt";
                        content += "El cliente " + c.getNombreCompleto() + " ha gastado $" + total + " durante el mes de " + contentFortmat.format(cDate);
                        fl.CreateFile(title,content);
                    }
                }
            }


        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void createCReport(ActionEvent event) {

        String header1 = "Tarjetas de crédito:";
        String header2 = "Tarjetas de Débito:";
        String notFound = "N/A";
        String censored = "XXXX XXXX XXXX ";
        String creditContent = "";
        String debitContent = "";
        String content = "";
        String title = "C-";

        boolean dExist = false;
        boolean cExist = false;

        SimpleDateFormat titleFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");

        int idCliente = Integer.parseInt(idClienteC.getText());

        for (Tarjeta t: tarjetas){
            if(t.getCliente().getId() == idCliente){
                if(t.getTipoTarjeta() == 'C'){
                    creditContent += censored + getLastDigits(t.getNumeroTarjeta()) + "\n";
                    cExist = true;
                } else if (t.getTipoTarjeta() == 'D'){
                    debitContent += censored + getLastDigits(t.getNumeroTarjeta()) + "\n";
                    dExist = true;
                }
            }
        }

        if(cExist){
            content += header1 + "\n" + creditContent + "\n";
        } else {
            content += header1 + "\n" + notFound + "\n";
        }

        if(dExist){
            content += header2 + "\n" + debitContent + "\n";
        } else {
            content += header2 + "\n" + notFound + "\n";
        }

        title += titleFormat.format(new Date())  + ".txt";

        fl.CreateFile(title, content);

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

            String tarjetaID = String.valueOf(textIdTarjeta.getText());

            if (tarjetaID.isEmpty()){
                System.out.println("Tiene que digitar un ID para encontrar la tarjeta que se busca");
            }

            for (Tarjeta tarjeta: tarjetas){

                if (tarjeta.getId() == Integer.parseInt(textIdTarjeta.getText())){

                    DBController.getDBInstance().deleteTarjeta(Integer.parseInt(tarjetaID));
                    tarjetas = DBController.getDBInstance().getTarjetas();
                    textIdTarjeta.clear();
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


    public Date Datepickerconvertorinicio(DatePicker fecha){ //00026223 se declara un metodo para modificar el formato de la fecha
        LocalDate localDate =fecha.getValue(); //00026223 se captura la variable Localdate del Date Picker
        if (localDate!=null){ //00026223 se compara si la variable tiene datos
            return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()); //00026223 se transforma el valor LocalDate a un valor Date
        }
        else{ //00026223 sino se cumple la condicion
        return null; //00026223 se regresa el campo como nulo
        }
    }

    public String getLastDigits(String s){
        String lastDigits = "";
        try {
            for (int i = 15;i<19; i++ ){
                lastDigits += s.charAt(i);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return lastDigits;
    }



}

