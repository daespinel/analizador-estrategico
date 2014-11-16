/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package analizadorestrategias;

/**
 *
 * @author USUARIO
 */
public class Motor {
    public static Organizacion organizacion1;
    public static Organizacion organizacion2;
    public static Organizacion organizacion3;
    public static Organizacion organizacion4;
    public static Organizacion organizacion5;
    public static Analizador ui;
    public static int generaciones=0;
    public static double tasaRiesgoLibre =5;
    public static double volatilidadMercado =20;
    public static void main(String[] args) {
        
            organizacion2= new Organizacion("7-Eleven",0);
            organizacion3= new Organizacion("Family Mart",0);
            organizacion4= new Organizacion("Exito Express",0);
            organizacion5= new Organizacion("Oxxo",0);
            ui = new Analizador();
            
            ui.iniciarDatos();
            
            while(generaciones<100000){
                ui.continuar();
                generaciones++;
                ui.colocarGeneraciones(getGen());
                
            }

	}
    
    public static String getGen(){
        return Integer.toString(generaciones);
    }
}
