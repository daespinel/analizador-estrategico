/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package analizadorestrategias;

import java.text.DecimalFormat;
import java.util.Random;

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
    public static double valorFactura=0;
    
    public static final String ZONAN="NORTE";
    public static final String ZONAS="SUR";
    public static final String ZONAO="ORIENTE";
    public static final String ZONAOCC="OCCIDENTE";
    
    public static final double VZONAN=9000;
    public static final double VZONAS=6000;
    public static final double VZONAO=4300;
    public static final double VZONAOCC=13800;
    
    public static Random rnd = new Random();
    
    public static void main(String[] args) {
        
            organizacion2= new Organizacion("7-Eleven",0);
            organizacion3= new Organizacion("Family Mart",0);
            organizacion4= new Organizacion("Exito Express",0);
            organizacion5= new Organizacion("Oxxo",0);
            ui = new Analizador();
            
            ui.iniciarDatos();
            ui.continuarDatosNuevos();
            
            valorFactura=ui.valorFactura;
            organizacion1= new Organizacion(ui.nombre,ui.capitalI);
            
            for(int i=0;i<ui.numeroTiendas;i++){
                int zona=rnd.nextInt(4);
                String nombreTemporal="";
                double ventas=0;
                
                switch(zona){
                    case 0:
                        nombreTemporal=ZONAN;
                        ventas=VZONAN;
                        break;
                    case 1:
                        nombreTemporal=ZONAS;
                        ventas=VZONAS;
                        break;
                    case 2:
                        nombreTemporal=ZONAO;
                        ventas=VZONAO;
                        break;
                    case 3:
                        nombreTemporal=ZONAOCC;
                        ventas=VZONAOCC;
                        break;
                }
                Tienda tiendaTemp=new Tienda(nombreTemporal,ventas);
                System.out.println(tiendaTemp.getZona());
                organizacion1.agregarTienda(tiendaTemp);
            
            }
            
            organizacion1.setEstrategia(new Estrategia("INVERTIR"));
            
            while(generaciones<100000){
                
                organizacion1.calcularIngresosTiendas();
                organizacion2.calcularIngresosTiendas();
                organizacion3.calcularIngresosTiendas();
                organizacion4.calcularIngresosTiendas();
                organizacion5.calcularIngresosTiendas();
                
                ui.continuar();
                generaciones++;
                ui.colocarGeneraciones(getGen());
                ui.mostrarDatosOrganizacion1();
                
            }

	}
    
    public static String getGen(){
        return Integer.toString(generaciones);
    }
    
    public static int Normal(double Media, double DStd) {
        double Suma = 0;
        for (int i = 1; i <= 12; i++) {
            double r = Math.random();
            Suma = Suma + r;
        }
        double x = DStd * (Suma - 6) + Media;
        return (int)x;
    }
}
