package org.example.parcialfinalpoo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.parcialfinalpoo.Clases.Cliente;
import org.example.parcialfinalpoo.Clases.Compra;
import org.example.parcialfinalpoo.Clases.Tarjeta;
import org.example.parcialfinalpoo.DB.DBController;
import org.example.parcialfinalpoo.filesystem.FileSystem;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable { //00073123 - Controlador de la parte gráfica que implemente una interfaz para inicializar procesos

    private List<Compra> compras; //00073123 - Lista "compras" que almacena objetos de tipo Compra

    private List<Tarjeta> tarjetas; //00073123 - Lista "tarjetas" que almacena objetos de tipo Tarjeta

    private List<Cliente> clientes; //00073123 - Lista "clientes" que almacena objetos de tipo Cliente

    private final Integer[] meses = {1,2,3,4,5,6,7,8,9,10,11,12}; //00073123 - Arreglo tipo Integer que almacena los valores de los meses

    private final String[] facilitadores = {"visa", "MasterCard", "American Express"}; //00073123 - Arreglo tipo String que almacena los valores de los faciltiadores de la tarjeta

    private final FileSystem fl = new FileSystem(); //00073123 - Crea una instancia de tipo FileSystem

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private DatePicker Fechafinal; //00073123 - Variable utilizada para seleccionar la fecha final en el reporte A

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private DatePicker Fechainicio; //00073123 - Variable utilizada para seleccionar la fecha de inicio en el reporte A

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private ComboBox<String> comboFacilitadorTarjeta; //00073123 - Variable utilizada para almacenar todos los tipos de facilitadores de tarjeta en el reporte D

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private ComboBox<Integer> comboMes; //00073123 - Variable utilizada para almacenar todos los meses en el reporte B

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private TextField idCLienteb; //00073123 - Variable en donde se coloca el ID del cliente para eliminarlo en el reporte A

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private TextField idClienteA; //00073123 - Variable en donde se coloca el ID del cliente para listar las compras que realizó en el reporte A

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private TextField idClienteC; //00073123 - Variable en donde se coloca el ID del cliente para listar las tarjetas que tiene en el reporte C

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private TextField idClienteM; //00073123 - Variable en donde se coloca el ID del cliente para modificar la información del mismo

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private TextField idCompraM; //00073123 - Variable en donde se coloca el ID de la compra para modificar la información de la compra

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private TextField idTarjetaM; //00073123 - Variable en donde se coloca el ID de la tarjeta para modificar la información de la misma

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private RadioButton radioAmerican; //00073123 - Variable para almacenar el botón del facilitador de la tarjeta correspondiente a American Express

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private RadioButton radioMasterCard; //00073123 - Variable para almacenar el botón del facilitador de la tarjeta correspondiente a Master Card

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private RadioButton radioTipoCredito; //00073123 - Variable para almacenar el botón del tipo de la tarjeta correspondiente a Crédito

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private RadioButton radioTipoDebito; //00073123 - Variable para almacenar el botón del tipo de la tarjeta correspondiente a Débito

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private RadioButton radioVisa; //00073123 - Variable para almacenar el botón del facilitador de la tarjeta correspondiente a Visa

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private TextField textAnio; //00073123 - Variable para colocar el año en el reporte B para imprimir el dinero gastado por un cliente

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private TextField textClienteTarjeta; //00073123 - Variable para colocar el ID del cliente en la información de la tarjeta para añadirla

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private TextField textDescripcionCompra; //00073123 - Variable para almacenar la descripción de la compra

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private TextField textDireccion; //00073123 - Variable para almacenar la dirección del cliente

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private TextField textExpDate; //00073123 - Variable para almacenar la fecha de expiración de la tarjeta

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private TextField textFechaCompra; //00073123 - Variable para almacenar la fecha de la compra

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private TextField textIdCliente; //00073123 - Variable para almacenar el ID del cliente a la hora de eliminarlo

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private TextField textIdCompra; //00073123 - Variable para almacenar el ID de la compra a la hora de eliminarla

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private TextField textIdTarjeta; //00073123 - Variable para almacenar el ID de la tarjeta a la hora de eliminarla

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private TextField textMontoCompra; //00073123 - Variable para almacenar el monto de la compra

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private TextField textNombreCliente; //00073123 - Variable para almacenar el nombre del cliente

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private TextField textNumTarjeta; //00073123 - Variabla para almacenar el numero de la tarjeta

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private TextField textTarjetaCompra; //00073123 - Variable para almacenar el tipo de tarjeta con la que se realiza la compra

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private TextField textTel; //00073123 - Variable para almacenar el telefono del cliente

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private ToggleGroup typeToggleGroup; //00073123 - Selecciona los raddio button que permiten escoger el tipo de la tarjeta

    @FXML //00073123 - Anotador para marcar métodos conectados a componentes definidos en un archivo FXML
    private ToggleGroup facilitatorToggleGroup; //00073123 - Selecciona los raddio button que permiten escoger el facilitador de la tarjeta

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
        double totalcompras=0;
        int cantidadcompras=0;


        for (Compra compra: compras){

            if (compra.getTarjeta().getFacilitadorTarjeta()==comboFacilitadorTarjeta.getValue()){

                System.out.println(" Cliente: "+compra.getTarjeta().getCliente());
                totalcompras=totalcompras+compra.getMontoTotal();
                cantidadcompras++;
            }
        }
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

