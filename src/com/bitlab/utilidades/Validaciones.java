/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.utilidades;
import java.util.*;
/**
 *
 * @author Oscar
 */
public class Validaciones {
    //Habilitando la clase scanner
    Scanner scanner = new Scanner(System.in);
     
    
    //Validando que nos ingresen un string 
    public String validarString(String opcion){
        String dato ="";
        
        //Mientras el dato sea vacio o con caracteres numericos seguira pidiendo el dato
        while(dato.equals("")){
            System.out.println(opcion);
            dato = scanner.nextLine();
            //matches verifica si es o no una expresion regular comprendida entre el A-Z mayusculas y minusculas 
            if(!dato.matches("^[A-Za-z ]*$")){ 
                System.out.println("'"+dato+"'. No es un caracter valido porfavor ingrese unicamente letras");
                dato = "";
               
            }
        }
        return dato;
    }
    
    //Validando que solo se ingresen numeros
    public int validarInt(String opcion){
        String dato ="";
        
        //Mientras el dato sea vacio o con caracteres numericos seguira pidiendo el dato
        while(dato.equals("")){
            System.out.println(opcion);
            dato = scanner.nextLine();
            //matches verifica si es o no una expresion regular comprendida entre el 0-9 
            if(!dato.matches("^[0-9]*")){ 
                System.out.println("'"+dato+"'. No es un numero valido porfavor ingrese unicamente numeros enteros");
                dato = "";
            }
        }
        return Integer.parseInt(dato);
    }
    
     //Validando que solo se ingresen numeros
    public byte validarByte(String opcion){
        String dato ="";
        
        //Mientras el dato sea vacio o con caracteres numericos seguira pidiendo el dato
        while(dato.equals("")){
            System.out.println(opcion);
            dato = scanner.nextLine();
            //matches verifica si es o no una expresion regular comprendida entre el 0-9 
            if(!dato.matches("^[0-9]")){ 
                System.out.println("'"+dato+"'. No es un numero valido porfavor ingrese unicamente numeros enteros");
                dato = "";
            }
        }
        return Byte.parseByte(dato);
    }
    
    
     /* Validando que nos ingresen un correo valido 
      * Matches     joe@aol.com | a@b.c | correo@mail.com | as.asdas@mail.com
      * Non-Matches asdf | 1234
      */
    public String validarCorreo(String opcion){
        String dato ="";
        
        //Mientras el dato sea vacio o con caracteres numericos seguira pidiendo el dato
        while(dato.equals("")){
            System.out.println(opcion);
            dato = scanner.nextLine();
            dato.toLowerCase(); //Pasando el correo a minusculas por si el usuario se equivoca
            //matches verifica si es o no una expresion regular comprendida entre el a-z minusculas, numeros, gion bajo, arroba y puntos 
            if(!dato.matches("[\\w\\-.]+@{1}([\\w-]+\\.)+[\\w-\\.]+")){ 
                System.out.println("'"+dato+"'. No es un correo valido porfavor ingrese un correo como: ejemplo@sudominio.com");
                dato = "";             
            }
        }
        return dato;
    }
    
    
    
}
