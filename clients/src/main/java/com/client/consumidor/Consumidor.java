package com.client.consumidor;

import java.io.IOException;

public class Consumidor{
    
    private static void consumir(char tipo, int cantidad){
        ServicioConsumir servicio = new ServicioConsumir();
        try {
            servicio.consumir(tipo, cantidad);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void consultar(){
        ServicioConsultar servicio = new ServicioConsultar();
        try {
            servicio.consultar();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static String crearArteAscii(){
        return """
        
         _____                                 _     _            
        /  __ \\                               (_)   | |           
        | /  \\/ ___  _ __  ___ _   _ _ __ ___  _  __| | ___  _ __ 
        | |    / _ \\| '_ \\/ __| | | | '_ ` _ \\| |/ _` |/ _ \\| '__|
        | \\__/\\ (_) | | | \\__ \\ |_| | | | | | | | (_| | (_) | |   
         \\____/\\___/|_| |_|___/\\__,_|_| |_| |_|_|\\__,_|\\___/|_|   
                                                           
                                                           
 
                        """;
    }


    public static void main(String[] args) {
        
        int opcion = 1;
        while (opcion != 0) {
            try{
                System.out.println(crearArteAscii());  
                System.out.println("Bienvenido al consumidor");
                System.out.println("Ingrese la accion a realizar");
                System.out.println("1.- Consumir un producto");
                System.out.println("2.- Consultar las transacciones del tarro");
                System.out.println("0.- Salir");
                opcion = Integer.parseInt(System.console().readLine());                       
                System.out.print("\033[H\033[2J");    
                switch (opcion) {
                    case 1:

                        //Request tipo until valid input
                        char tipo = ' ';
                        while(tipo != 'A' && tipo != 'B'){
                            System.out.println("Ingrese el tipo de producto a consumir");
                            System.out.println("A.- Producto A");
                            System.out.println("B.- Producto B");
                            tipo = System.console().readLine().charAt(0);
                        }
                        System.out.println("Ingrese la cantidad de productos");
                        int cantidad = Integer.parseInt(System.console().readLine());
                        System.out.println("\n\n Se consumiran " + cantidad + " productos de tipo " + tipo);
                        System.out.print("\033[H\033[2J");  
                        consumir(tipo, cantidad);
                        break;
                    case 2:
                        consultar();
                        break;
                    case 0:
                        System.out.println("Saliendo");

                        break;
                    default:
                        System.out.println("Escoge una opción valida");
                }
                if(opcion != 0){
                    System.out.println("\n\n Presione cualquier tecla para continuar");
                    System.console().readLine();                       
                    System.out.print("\033[H\033[2J");
                }
            }
            catch(NumberFormatException e){
                System.out.print("\033[H\033[2J");
                System.out.println("Escoge una opción valida");
                System.console().readLine(); 
                System.out.print("\033[H\033[2J"); 
            }
            catch (Exception e){
                e.getMessage();
                System.out.println("Ha ocurrido un error inesperado");
            }      
        }        
    }
}

