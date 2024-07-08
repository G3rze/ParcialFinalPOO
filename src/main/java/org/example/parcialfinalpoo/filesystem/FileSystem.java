package org.example.parcialfinalpoo.filesystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSystem { //00073123 - Clase llamada "FileSytem" para la creación de archivos txt

    public boolean CreateFile(String fileName, String content) { //00073123 - Función de tipo boolean para crear los archivos txt que acepta parámetros como el nombre y el contenido del archivo
        FileWriter fw = null; //00073123 - Declaración de variable FileWriter como "fw" inicializada en null

        if (fileName.charAt(0) == 'A' || fileName.charAt(0) == 'B' || fileName.charAt(0) == 'C' || fileName.charAt(0) == 'D') { //00073123 - Condición que marca que el archivo tiene que tener como primer caracter una 'A', 'B', 'C' o 'D'

            File file = new File("Reportes/" + fileName); // 00073123 - Crea un objeto de tipo File en el directorio "Reportes" con el nombre dado por fileName

            try { //00073123 - Inicio del manejo de errores usando try catch

                fw = new FileWriter(file);//00073123 - Inicializa el FileWriter mediante el objeto File para poder escribir en el
                fw.write(content); //00073123 - Escribe el contenido que se le pasa como parámetro

                fw.close(); // 0007312 - Cierra el FileWriter archivo luego de escribir en el

                return true; //00073123 - Retorna true indicando que la operación se realizó exitósamente

            } catch (IOException e) { //00073123 - Parte del try catch donde se captura el error e indica la excepción

                throw new RuntimeException(e); //00073123 - Lanza una excepción RuntimeException en caso de que haya un error

            }
        }

        return false; //00073123 - Retorna false si el archivo no comienza con ninguna letra de la condición

    }

}
