/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bitlab.utilidades;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
/**
 *
 * @author Oscar
 */
public class Validaciones {
    //Habilitando la clase scanner
    Scanner scanner = new Scanner(System.in);
    
    //Habilitando motores de entrada y salida de escritura.
    PrintWriter salida = null;
    BufferedReader entrada = null;
    
    //Validando que nos ingresen un string
    public String validarString(Socket socketCliente, String opcion) throws IOException {
        salida = new PrintWriter(socketCliente.getOutputStream(), true);
        entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        String dato = "";
        //Mientras el dato sea vacio o con caracteres numericos seguira pidiendo el dato
        while (dato.equals("")) {
            salida.println(opcion);
            //System.out.println(opcion);
            dato = entrada.readLine();
            //matches verifica si es o no una expresion regular comprendida entre el A-Z mayusculas y minusculas
            if (!dato.matches("^[A-Za-z ]*$")) {
                salida.println("'" + dato + "'. No es un caracter valido porfavor ingrese unicamente letras");
                //System.out.println("'"+dato+"'. No es un caracter valido porfavor ingrese unicamente letras");
                dato = "";
            }
        }
        return dato;
    }
    //Validando que solo se ingresen numeros
    public int validarInt(Socket socketCliente, String opcion) throws IOException {
        salida = new PrintWriter(socketCliente.getOutputStream(), true);
        entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        String dato = "";
        //Mientras el dato sea vacio o con caracteres numericos seguira pidiendo el dato
        while (dato.equals("")) {
            salida.printf(opcion);
            dato = entrada.readLine();
            //matches verifica si es o no una expresion regular comprendida entre el 0-9
            if (!dato.matches("^[0-9]*")) {
                salida.println("'" + dato + "'. No es un numero valido porfavor ingrese unicamente numeros enteros");
                dato = "";
            }
        }
        return Integer.parseInt(dato);
    }
    //Validando que solo se ingresen numeros
    public byte validarByte(Socket socketCliente, String opcion) throws IOException {
        salida = new PrintWriter(socketCliente.getOutputStream(), true);
        entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        String dato = "";
        //Mientras el dato sea vacio o con caracteres numericos seguira pidiendo el dato
        while (dato.equals("")) {
            salida.println(opcion);
            dato = entrada.readLine();
            //matches verifica si es o no una expresion regular comprendida entre el 0-9
            if (!dato.matches("^[0-9]")) {
                salida.println("'" + dato + "'. No es un numero valido porfavor ingrese unicamente numeros enteros");
                dato = "";
            }
        }
        return Byte.parseByte(dato);
    }
    
    /* Validando que nos ingresen un correo valido
    * Matches     joe@aol.com | a@b.c | correo@mail.com | as.asdas@mail.com
    * Non-Matches asdf | 1234
    */
    public String validarCorreo(Socket socketCliente, String opcion) throws IOException {
        salida = new PrintWriter(socketCliente.getOutputStream(), true);
        entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        String dato = "";
        //Mientras el dato sea vacio o con caracteres numericos seguira pidiendo el dato
        while (dato.equals("")) {
            salida.println(opcion);
            dato = entrada.readLine();
            dato.toLowerCase(); //Pasando el correo a minusculas por si el usuario se equivoca
            //matches verifica si es o no una expresion regular comprendida entre el a-z minusculas, numeros, gion bajo, arroba y puntos
            if (!dato.matches("[\\w\\-.]+@{1}([\\w-]+\\.)+[\\w-\\.]+")) {
                salida.println("'" + dato + "'. No es un correo valido porfavor ingrese un correo como: ejemplo@sudominio.com");
                dato = "";
            }
        }
        return dato;
    }
    
    
}
