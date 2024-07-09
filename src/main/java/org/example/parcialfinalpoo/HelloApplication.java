package org.example.parcialfinalpoo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.parcialfinalpoo.DB.DBController;
import org.example.parcialfinalpoo.filesystem.FileSystem;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException { //0007123 - Método usado en Java para configurar y mostrar la interfaz gráfica del usuario
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));//00073123 - Carga la ventana principal definida por el usuario en un archivo FXML
        Scene scene = new Scene(fxmlLoader.load(), 400, 300); //Establece las dimensiones y genera la ventana principal
        stage.setTitle("Hello!"); //00073123 - Establece el título de la ventana principal de la interfaz
        stage.setScene(scene); //00073123 - Establece la escena de la ventana principal de la interfaz
        stage.show(); //00073123 - Muestra la ventana principal de la interfaz
    }

    public static void main(String[] args) { //00073123 - Método main donde se encuentran los métodos para correr la interfaz
        DBController.getDBInstance(); //00073123 - Establece la conexión con la base de datos
        launch(); //00073123 - Método para correr la interfaz creada por el usuario
    }
}