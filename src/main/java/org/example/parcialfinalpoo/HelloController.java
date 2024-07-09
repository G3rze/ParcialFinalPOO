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
import java.util.Objects;
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

                    idCompraM.clear(); //00026223 se liberan los campos del javafx
                    textFechaCompra.clear(); //00026223 se liberan los campos del javafx
                    textMontoCompra.clear(); //00026223 se liberan los campos del javafx
                    textDescripcionCompra.clear(); //00026223 se liberan los campos del javafx
                    textTarjetaCompra.clear(); //00026223 se liberan los campos del javafx

                }catch (Exception e){ //00055623 Atraba el posible error

                    throw new RuntimeException(e); //00055623 Devuelve el error

                }
            }
        }
    }

    @FXML
    void addTarjeta(ActionEvent event) { //00055623 Funcion llamada cuando se acciona el boton de agregar Tarjeta

        for (Cliente c: clientes) { //00055623 Itera sobre la lista de clientes

            if (c.getId() == Integer.parseInt(textClienteTarjeta.getText())) { //00055623 Verifica si el ID del cliente coincide con el ID ingresado

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //00055623 Formato de fecha

                try {
                    Date date = format.parse(textExpDate.getText()); //00055623 Parsea la fecha de vencimiento de la tarjeta
                    Tarjeta tarjeta = new Tarjeta(-1, textNumTarjeta.getText(), date, getTipoTarjeta(), getFacilitadorTarjeta(), c); //00055623 Crea una nueva instancia de Tarjeta

                    DBController.getDBInstance().insertTarjeta(tarjeta); //00055623 Inserta la tarjeta en la base de datos

                    tarjetas = DBController.getDBInstance().getTarjetas(); //00055623 Actualiza la lista de tarjetas

                    textClienteTarjeta.clear(); //00055623 Limpia el campo de texto para el ID del cliente
                    idTarjetaM.clear(); //00055623 Limpia el campo de texto del ID de la tarjeta
                    textNumTarjeta.clear(); //00055623 Limpia el campo de texto del número de tarjeta
                    textExpDate.clear(); //00055623 Limpia el campo de texto de la fecha de expiración
                    typeToggleGroup.selectToggle(null); //00055623 Desmarca el grupo de botones de tipo de tarjeta
                    facilitatorToggleGroup.selectToggle(null); //00055623 Desmarca el grupo de botones de facilitador de tarjeta
                } catch (ParseException e) { //00055623 Maneja la excepción de análisis de fecha
                    throw new RuntimeException(e); //00055623 Lanza una excepción en caso de error

                }
            }
        }


    }

    @FXML
    void createAReport(ActionEvent event) { //00055623 Crea un reporte A cuando se acciona el botón generar
        String content = ""; //00055623 Contenido del reporte
        String title = "A-"; //00055623 Título del reporte
        String idcompra=idClienteA.getText(); //00055623 Obtiene el ID del cliente
        Date actual = new Date(); //00055623 Obtiene la fecha actual
        boolean exist = false; //00055623 Verifica si existen compras en el rango de fechas

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss"); //00055623 Formato de fecha para el título del archivo

        for(Compra compra: compras){ //00055623 Itera sobre la lista de compras

            if(compra.getTarjeta().getCliente().getId()==Integer.parseInt(idcompra)){ //00055623 Verifica si el ID del cliente coincide
                if ( (compra.getFechaCompra().after(Datepickerconvertorinicio(Fechainicio)) || compra.getFechaCompra().equals(Datepickerconvertorinicio(Fechainicio))) && (compra.getFechaCompra().before(Datepickerconvertorinicio(Fechafinal)) || compra.getFechaCompra().equals(Datepickerconvertorinicio(Fechafinal)))){ //00055623 Verifica si la compra está dentro del rango de fechas
                    content = content + ("ID: " + compra.getId() + " Fecha: " + compra.getFechaCompra() + " Descripción " + compra.getDescripcion() + " Tarjeta: " + compra.getTarjeta().getNumeroTarjeta() + "\n"); //00055623 Añade información de la compra al contenido del reporte
                    exist = true; //00055623 Marca que existe al menos una compra en el rango de fechas
                }
            }
        }
        if(exist){ //00055623 Si existen compras en el rango de fechas
            title += format.format(actual) + ".txt"; //00055623 Formatea el título del archivo con la fecha y hora actual
            fl.CreateFile(title, content); //00055623 Crea el archivo con el contenido del reporte
        }
    }

    @FXML
    void createBReport(ActionEvent event) { //00055623 Crea un reporte B cuando se acciona el botón generar

        String idCliente = idCLienteb.getText(); //00055623 Obtiene el ID del cliente
        String content = ""; //00055623 Contenido del reporte
        String title = "B-"; //00055623 Título del reporte

        float total = 0; //00055623 Total de gastos

        boolean exist = false; //00055623 Verifica si existen compras en el mes

        Date actual = new Date(); //00055623 Obtiene la fecha actual
        Date cDate; //00055623 Fecha del reporte

        SimpleDateFormat format = new SimpleDateFormat("MM-yyyy"); //00055623 Formato de mes y año
        SimpleDateFormat titleFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss"); //00055623 Formato de fecha y hora para el título del archivo
        SimpleDateFormat contentFortmat = new SimpleDateFormat("MMMM-yyyy"); //00055623 Formato de mes y año para el contenido del reporte

        try {
            cDate = format.parse(String.valueOf(comboMes.getValue()) + "-" + textAnio.getText()); //00055623 Parsea la fecha seleccionada
            String cDateString = format.format(cDate); //00055623 Formatea la fecha seleccionada
            for (Compra c: compras){ //00055623 Itera sobre la lista de compras

                String cTableDate = format.format(c.getFechaCompra()); //00055623 Formatea la fecha de la compra

                if((c.getTarjeta().getCliente().getId() == Integer.parseInt(idCliente)) && cDateString.equals(cTableDate) ){ //00055623 Verifica si la compra corresponde al cliente y al mes seleccionado
                    exist = true; //00055623 Marca que existe al menos una compra en el mes
                    total += (float) c.getMontoTotal(); //00055623 Suma el monto total de la compra al total
                }
            }
            if(exist){ //00055623 Si existen compras en el mes
                for (Cliente c: clientes) { //00055623 Itera sobre la lista de clientes
                    if (c.getId() == Integer.parseInt(idCliente) ) { //00055623 Verifica si el ID del cliente coincide
                        title += titleFormat.format(actual) + ".txt"; //00055623 Formatea el título del archivo con la fecha y hora actual
                        content += "El cliente " + c.getNombreCompleto() + " ha gastado $" + total + " durante el mes de " + contentFortmat.format(cDate); //00055623 Añade información del gasto del cliente al contenido del report
                        fl.CreateFile(title,content); //00055623 Crea el archivo con el contenido del reporte
                    }
                }
            }


        } catch (ParseException e) { //00055623 Maneja la excepción de análisis de fecha
            throw new RuntimeException(e); //00055623 Lanza una excepción en caso de error
        }


    }

    @FXML
    void createCReport(ActionEvent event) { //00055623 Crea un reporte C cuando se acciona el botón generar

        String header1 = "Tarjetas de crédito:"; //00055623 Encabezado para tarjetas de crédito
        String header2 = "Tarjetas de Débito:"; //00055623 Encabezado para tarjetas de débito
        String notFound = "\t" + "N/A"; //00055623 Texto para tarjetas no encontradas
        String censored = "XXXX XXXX XXXX "; //00055623 Texto para censurar números de tarjeta
        String creditContent = ""; //00055623 Contenido del reporte de tarjetas de crédito
        String debitContent = ""; //00055623 Contenido del reporte de tarjetas de débito
        String content = ""; //00055623 Contenido del reporte
        String title = "C-"; //00055623 Título del reporte

        boolean dExist = false; //00055623 Verifica si existen tarjetas de débito
        boolean cExist = false; //00055623 Verifica si existen tarjetas de crédito

        SimpleDateFormat titleFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss"); //00055623 Formato de fecha y hora para el título del archivo

        int idCliente = Integer.parseInt(idClienteC.getText()); //00055623 Obtiene el ID del cliente

        for (Tarjeta t: tarjetas){ //00055623 Itera sobre la lista de tarjetas
            if(t.getCliente().getId() == idCliente){ //00055623 Verifica si la tarjeta pertenece al cliente
                if(t.getTipoTarjeta() == 'C'){ //00055623 Verifica si la tarjeta es de crédito
                    creditContent += "\t" + censored + getLastDigits(t.getNumeroTarjeta()) + "\n"; //00055623 Añade la tarjeta de crédito al contenido del reporte
                    cExist = true; //00055623 Marca que existe al menos una tarjeta de crédito
                } else if (t.getTipoTarjeta() == 'D'){ //00055623 Verifica si la tarjeta es de débito
                    debitContent += "\t" + censored + getLastDigits(t.getNumeroTarjeta()) + "\n"; //00055623 Añade la tarjeta de débito al contenido del reporte
                    dExist = true; //00055623 Marca que existe al menos una tarjeta de débito
                }
            }
        }

        if(cExist){ //00055623 Si existen tarjetas de crédito
            content += header1 + "\n" + creditContent + "\n"; //00055623 Añade el encabezado y el contenido de tarjetas de crédito al reporte
        } else {
            content += header1 + "\n" + notFound + "\n"; //00055623 Añade el encabezado y la indicación de no encontrado al reporte
        }

        if(dExist){ //00055623 Si existen tarjetas de débito
            content += header2 + "\n" + debitContent + "\n"; //00055623 Añade el encabezado y el contenido de tarjetas de débito al reporte
        } else {
            content += header2 + "\n" + notFound + "\n"; //00055623 Añade el encabezado y la indicación de no encontrado al reporte
        }

        title += titleFormat.format(new Date())  + ".txt"; //00055623 Formatea el título del archivo con la fecha y hora actual

        fl.CreateFile(title, content); //00055623 Crea el archivo con el contenido del reporte

    }

    @FXML
    void createDReport(ActionEvent event) { //00026223 metodo se hará al apretar el botón de crear reporte D
        double totalcompras=0; //00026223 declaracion de variable

        Date actual = new Date(); //00026223 creacion de un objeto tipo Date

        int cantidadcompras=0; //00026223 declaracion de variable

        String cliente =""; //00026223 declaracion de variable
        String tittle = "D-"; //00026223 declaracion de variable
        String content= ""; //00026223 declaracion de variable

        SimpleDateFormat titleFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss"); //00026223 creacion de in objeto tipo simpledateformat que tiene ya establecido el orden de los datos

        boolean exist = false; //00026223 inicializacion de variable

        for (Cliente c: clientes){ //00026223 bucle para recorrer la lista clientes

            for (Tarjeta t: tarjetas){ //00026223 bucle para recorrer la lista tarjetas

                if (c.getId()==t.getCliente().getId()){ //00026223 se comprara el cliente en su tabla con la llave foranea en la tabla tarjetas

                    if (t.getFacilitadorTarjeta().equals(getFacilitador(comboFacilitadorTarjeta.getValue()))){ //00026223 se comprara el facilitador en su tabla con la llave foranea en la tabla compras

                        for (Compra compra: compras){ //00026223 bucle para recorrer la lista compras
                            if (compra.getTarjeta().getId() == t.getId()) { //00055623 Valida si la compra tiene la tarjeta actual
                                totalcompras+=compra.getMontoTotal(); //00026223 se aumenta el valor de totalcompras por cada iteracion
                                cantidadcompras++; //00026223 contador que lleva el total de iteraciones
                            }
                        }
                        content=content+(" Cliente :"+c.getNombreCompleto()+" Total gastado: $"+totalcompras+" cantidad de compras realizadas: "+cantidadcompras+"\n"); //00026223 se almacena la informacion en un string
                        exist=true; //00026223 se inicializa en true este booleano para poder crear un archivo
                    }

                }
            }
            totalcompras=0; //00026223 se reinicia el valor de la variable
            cantidadcompras=0; //00026223 se reinicia el valor de la variable
        }

        if (exist){ //00026223 cuando existe sea verdadero se va a ejecutar esto
            tittle += titleFormat.format(actual) + ".txt"; //00026223 se añade la fecha al titulo quemado D
            fl.CreateFile(tittle, content); //00026223 se escribe el txt con ese formato titulo y contenido

        }
    }

    @FXML
    void deleteCliente(ActionEvent event) { //00055623 Funcion llamada cuando se acciona el boton de borrar Cliente

        try {

            String clienteID = String.valueOf(textIdCliente.getText()); //00055623 Obtiene el ID del cliente

            if (clienteID.isEmpty()){ //00055623 Verifica si el campo de ID del cliente está vacío
                System.out.println("Tiene que digitar un ID para buscar el cliente que se busca"); //00055623 Imprime un mensaje si el campo está vacío
            }

            for (Cliente cliente: clientes){ //00055623 Itera sobre la lista de clientes

                if (cliente.getId() == Integer.parseInt(textIdCliente.getText())){ //00055623 Verifica si el ID del cliente coincide

                    DBController.getDBInstance().deleteCliente(Integer.parseInt(clienteID)); //00055623 Elimina el cliente de la base d
                    clientes = DBController.getDBInstance().getClientes(); //00055623 Actualiza la lista de clientes
                    textIdCliente.clear(); //00055623 Limpia el campo de texto del ID del cliente
                }

            }

        }catch (Exception e){ //00055623 Maneja cualquier excepción

            System.out.println(e); //00055623 Imprime la excepción

        }

    }

    @FXML
    void deleteCompra(ActionEvent event) { //00055623 Funcion llamada cuando se acciona el boton de borrar Compra

        try {

            String compraID = String.valueOf(textIdCompra.getText()); //00055623 Obtiene el ID de la compra

            if (compraID.isEmpty()){ //00055623 Verifica si el campo de ID de la compra está vacío
                System.out.println("Tiene que digitar un ID para encontrar la compra que se busca"); //00055623 Imprime un mensaje si el campo está vacío
            }

            for (Compra compra: compras){ //00055623 Itera sobre la lista de compras

                if (compra.getId() == Integer.parseInt(textIdCompra.getText())){ //00055623 Verifica si el ID de la compra coincide

                    DBController.getDBInstance().deleteCompras(Integer.parseInt(compraID)); //00055623 Elimina la compra de la base de datos
                    compras = DBController.getDBInstance().getCompras(); //00055623 Actualiza la lista de compras
                    textIdCompra.clear(); //00055623 Limpia el campo de texto del ID de la compra
                }

            }

        }catch (Exception e){ //00055623 Maneja cualquier excepción

            System.out.println(e); //00055623 Imprime la excepción

        }

    }

    @FXML
    void deleteTarjeta(ActionEvent event) { //00055623 Funcion llamada cuando se acciona el boton de borrar Tarjeta

        try {

            String tarjetaID = String.valueOf(textIdTarjeta.getText()); //00055623 Obtiene el ID de la tarjeta

            if (tarjetaID.isEmpty()){ //00055623 Verifica si el campo de ID de la tarjeta está vacío
                System.out.println("Tiene que digitar un ID para encontrar la tarjeta que se busca"); //00055623 Imprime un mensaje si el campo está vacío
            }

            for (Tarjeta tarjeta: tarjetas){ //00055623 Itera sobre la lista de tarjetas

                if (tarjeta.getId() == Integer.parseInt(textIdTarjeta.getText())){ //00055623 Verifica si el ID de la tarjeta coincide

                    DBController.getDBInstance().deleteTarjeta(Integer.parseInt(tarjetaID)); //00055623 Elimina la tarjeta de la base de datos
                    tarjetas = DBController.getDBInstance().getTarjetas(); //00055623 Actualiza la lista de tarjetas
                    textIdTarjeta.clear(); //00055623 Limpia el campo de texto del ID de la tarjeta
                }

            }

        }catch (Exception e){ //00055623 Maneja cualquier excepción

            System.out.println(e); //00055623 Imprime la excepción

        }

    }

    @FXML
    void updateCliente(ActionEvent event) { //00026223 metodo que se hara cuando se presione el boton updateCliente

        DBController.getDBInstance().updateCliente(Integer.parseInt(idClienteM.getText()), textNombreCliente.getText(), textDireccion.getText(), textTel.getText()); //00026223 se obtiene los datos por el controller

        clientes = DBController.getDBInstance().getClientes(); //00026223 se meten los datos en la base de datos en tabla clientes

        idClienteM.clear(); //00026223 se liberan los campos del javafx
        textNombreCliente.clear(); //00026223 se liberan los campos del javafx
        textDireccion.clear(); //00026223 se liberan los campos del javafx
        textTel.clear(); //00026223 se liberan los campos del javafx
    }

    @FXML
    void updateCompra(ActionEvent event) { //00026223 metodo que se hara cuando se presione el boton updateCompra

        for (Tarjeta t: tarjetas) {//00026223 se recorre la lista tarjetas
            if (t.getId() == Integer.parseInt(textTarjetaCompra.getText())) { //00026223 se comparan las id
                DBController.getDBInstance().updateCompra(Integer.parseInt(idCompraM.getText()), textFechaCompra.getText(), textMontoCompra.getText(), textDescripcionCompra.getText(), Integer.parseInt(textTarjetaCompra.getText())); //00026223 se obtiene los datos por el controller
                compras = DBController.getDBInstance().getCompras(); //00026223 se meten los datos en la base de datos en tabla compras
                idCompraM.clear(); //00026223 se liberan los campos del javafx
                textFechaCompra.clear(); //00026223 se liberan los campos del javafx
                textMontoCompra.clear(); //00026223 se liberan los campos del javafx
                textDescripcionCompra.clear(); //00026223 se liberan los campos del javafx
                textTarjetaCompra.clear(); //00026223 se liberan los campos del javafx
            }
        }

    }

    @FXML
    void updateTarjeta(ActionEvent event) { //00026223 metodo que se hara cuando se presione el boton updateTarjeta
        for (Cliente c: clientes){ //00026223 se recorre la lista clientes
            if(c.getId() == Integer.parseInt(textClienteTarjeta.getText())){ //00026223 se comparan las id
                DBController.getDBInstance().updateTarjeta(Integer.parseInt(idTarjetaM.getText()),textNumTarjeta.getText(), textExpDate.getText(), getTipoTarjeta(), getFacilitadorTarjeta(), Integer.parseInt(textClienteTarjeta.getText())); //00026223 se obtiene los datos por el controller
                tarjetas = DBController.getDBInstance().getTarjetas(); //00026223 se meten los datos en la base de datos en tabla tarjetas
                textClienteTarjeta.clear(); //00026223 se liberan los campos del javafx
                idTarjetaM.clear(); //00026223 se liberan los campos del javafx
                textNumTarjeta.clear(); //00026223 se liberan los campos del javafx
                textExpDate.clear(); //00026223 se liberan los campos del javafx
                typeToggleGroup.selectToggle(null); //00026223 se liberan los campos del javafx
                facilitatorToggleGroup.selectToggle(null); //00026223 se liberan los campos del javafx
                Fechainicio.getValue(); //00026223 se liberan los campos del javafx
            }
        }
    }

    public char getTipoTarjeta() { //00073123 - Función para saber el tipo de tarjeta que se usará
        if (radioTipoCredito.isSelected()) { //00073123 - Condición que indica que si se selecciona "radioTipoCredito" será una tarjeta de crédito
            return 'C'; //00073123 - Retorna 'C' que es igual a "Crédito"
        } else if (radioTipoDebito.isSelected()) { //00073123 - Condición que indica que si se selecciona "radioTipoDebito" será una tarjeta de débito
            return 'D'; //00073123 - Retorna 'D' que es igual a "Débito"
        } else { //00073123 - Continuación del if si ninguna de las condiciones anteriores se cumple
            throw new IllegalStateException("No se ha seleccionado ningún tipo de tarjeta"); //00073123 - Si ninguna condición se cumple lanzará la excepción indicando que no se seleccionó ningún tipo de tarjeta
        }
    }

    public String getFacilitadorTarjeta() { //00073123 - Función para saber el facilitador de la tarjeta que se usará
        if (radioVisa.isSelected()) { //00073123 - Condición que indica que si se selecciona "radioVisa" será Visa
            return "Visa"; //00073123 - Retorna 'Visa' que es igual a "Visa"
        } else if (radioMasterCard.isSelected()) { //00073123 - Condición que indica que si se selecciona "radioMasterCard" será Master Card
            return "MC"; //00073123 - Retorna 'MC' que es igual a "Master Card"
        } else if (radioAmerican.isSelected()){//00073123 - Condición que indica que si se selecciona "radioAmerican" será American Express
            return "AE"; //00073123 - Retorna 'AE' que es igual a "American Express"
        } else { //00073123 - Continuación del if si ninguna de las condiciones anteriores se cumple
            throw new IllegalStateException("No se ha seleccionado ningún facilitador de tarjeta"); //00073123 - Si ninguna condición se cumple lanzará la excepción indicando que no se seleccionó ningún faciltador
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

    public String getLastDigits(String s){ //00073123 - Función que selecciona los últimos digitos de la tarjeta, usados para el reporte C
        String lastDigits = ""; //00073123 - Inicializa una cadena vacía para almacenar los últimos dígitos
        try { //00073123 - Inicio del manejo de errores con try catch
            for (int i = 15;i<19; i++ ){ //00073123 - Ciclo for que recorre los lugares desde el 15 al 18 de los números de la tarjeta
                lastDigits += s.charAt(i); //00073123 - Añade el carácter en la posición i de la cadena s a la variable lastDigits
            }
        } catch (Exception e) { //En caso de error hay una excepción
            throw new RuntimeException(e); //00073123 - Lanza la excepción "RuntimeException"
        }

        return lastDigits; //00073123 - Retorna los valores de los últimos digitos como "lastDigits"
    }

    public String getFacilitador(String facilitador){ //00026223 declaracion del metodo
        String newFacilitador=""; //00026223 creacion de una variable tipo String

        switch (facilitador){ //00026223 un switch que usara la variable facilitador

            case "visa": //00026223 un case que compara el texto de facilitador
                newFacilitador="visa"; //00026223 se quema el valor en la variable

            case "MasterCard": //00026223 un case que compara el texto de facilitador
                newFacilitador="MC"; //00026223 se quema el valor en la variable

            case "American Express": //00026223 un case que compara el texto de facilitador
                newFacilitador="AE"; //00026223 se quema el valor en la variable

            default: //00026223 si no se cumple ninguna condicion previa
                break; //00026223 se detiene el proceso
        }
        return newFacilitador; //00026223 se retorna la variable
    }


}

