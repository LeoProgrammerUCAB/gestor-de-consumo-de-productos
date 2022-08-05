package com.client.productor;

import java.io.IOException;

public class Productor{
    
    private static String crearArteAscii(){
        return"""
                            
          
            ______              _            _             
            | ___ \\            | |          | |            
            | |_/ / __ ___   __| |_   _  ___| |_ ___  _ __ 
            |  __/ '__/ _ \\ / _` | | | |/ __| __/ _ \\| '__|
            | |  | | | (_) | (_| | |_| | (__| || (_) | |   
            \\_|  |_|  \\___/ \\__,_|\\__,_|\\___|\\__\\___/|_|   
                                                                             
         
                         """;

    }
    private static void llenar(){
        ServicioLlenado servicioLlenado = new ServicioLlenado();        
        try {
            servicioLlenado.llenar('A', 60);
            servicioLlenado.llenar('B', 40);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void respaldar(){
        ServicioRespaldo servicioRespaldo = new ServicioRespaldo();
        try {
            servicioRespaldo.respaldar();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        int opcion = 1;
        while (opcion != 0) {
            try{
                System.out.println(crearArteAscii());
                System.out.println("Bienvenido al productor");
                System.out.println("Ingrese la accion a realizar");
                System.out.println("1.- Llenar");
                System.out.println("2.- Solicitar Respaldo");
                System.out.println("3.- Solicitar Restauración");
                System.out.println("\n0.- Salir");
                opcion = Integer.parseInt(System.console().readLine());                       
                System.out.print("\033[H\033[2J");    
                switch (opcion) {
                    case 1:
                        llenar();
                        break;
                    case 2:
                        System.out.println("Solicitando respaldo");
                        respaldar();
                        break;
                    case 3:
                        System.out.println("Solicitando restauración");
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
