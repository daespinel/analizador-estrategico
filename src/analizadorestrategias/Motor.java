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
 * @author Lorena
 * @author David
 */
public class Motor {
    
    public static Estrategia[] estrategias1 = new Estrategia[4];
    public static Estrategia[] estrategias2 = new Estrategia[4];
    public static Estrategia[] estrategias3 = new Estrategia[4];
    public static Estrategia[] estrategias4 = new Estrategia[4];
    public static Estrategia[] estrategias5 = new Estrategia[4];
    
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
    
    public static final double GASTOBASE = 64000000;
    
    public static Random rnd = new Random();
    
    public static void main(String[] args) {
        
            organizacion2= new Organizacion("7-Eleven",0);
            organizacion3= new Organizacion("Family Mart",0);
            organizacion4= new Organizacion("Exito Express",0);
            organizacion5= new Organizacion("Oxxo",0);
            ui = new Analizador();
            inicializarOrganizacionesCompetencia();
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
                Tienda tiendaTemp=new Tienda(nombreTemporal,ventas,GASTOBASE);
                System.out.println(tiendaTemp.getZona());
                organizacion1.agregarTienda(tiendaTemp);
            
            }
            
            estrategias1[0]= new Estrategia("Abrir Tienda",5,10,8,9);
            estrategias1[1]= new Estrategia("Cerrar Tienda",0,0,0,0);
            estrategias1[2]= new Estrategia("Publicidad",4,7,6,8);
            estrategias1[3]= new Estrategia("No invertir",2,3,2,3);
            
            organizacion1.estrategia = estrategias1; 
            
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
    
    public static void inicializarOrganizacionesCompetencia(){
        double capitalT=Normal(50000000,2000000);
        organizacion2= new Organizacion("7-Eleven",capitalT);
            int tiendasTemp=Normal(20,5);
            for(int i=0;i<tiendasTemp;i++){
                int zona=rnd.nextInt(4);
                String nombreTemporal="";
                double ventasT=0;
                switch(zona){
                    case 0:
                        nombreTemporal=ZONAN;
                        ventasT=VZONAN;
                        break;
                    case 1:
                        nombreTemporal=ZONAS;
                        ventasT=VZONAS;
                        break;
                    case 2:
                        nombreTemporal=ZONAO;
                        ventasT=VZONAO;
                        break;
                    case 3:
                        nombreTemporal=ZONAOCC;
                        ventasT=VZONAOCC;
                        break;
                }
                Tienda tiendaTemp=new Tienda(nombreTemporal,ventasT,GASTOBASE);
                organizacion2.agregarTienda(tiendaTemp);
            }
                       
            estrategias2[0]= new Estrategia("Abrir Tienda",5,10,8,9);
            estrategias2[1]= new Estrategia("Cerrar Tienda",0,0,0,0);
            estrategias2[2]= new Estrategia("Publicidad",4,7,6,8);
            estrategias2[3]= new Estrategia("No invertir",2,3,2,3);
            
            organizacion2.estrategia = estrategias2;
            
        capitalT=Normal(50000000,2000000);
        organizacion3= new Organizacion("FamilyMart",capitalT);
            tiendasTemp=Normal(20,5);
            for(int i=0;i<tiendasTemp;i++){
                int zona=rnd.nextInt(4);
                String nombreTemporal="";
                double ventasT=0;
                switch(zona){
                    case 0:
                        nombreTemporal=ZONAN;
                        ventasT=VZONAN;
                        break;
                    case 1:
                        nombreTemporal=ZONAS;
                        ventasT=VZONAS;
                        break;
                    case 2:
                        nombreTemporal=ZONAO;
                        ventasT=VZONAO;
                        break;
                    case 3:
                        nombreTemporal=ZONAOCC;
                        ventasT=VZONAOCC;
                        break;
                }
                Tienda tiendaTemp=new Tienda(nombreTemporal,ventasT,GASTOBASE);
                organizacion3.agregarTienda(tiendaTemp);
            }
            
            estrategias3[0]= new Estrategia("Abrir Tienda",5,10,8,9);
            estrategias3[1]= new Estrategia("Cerrar Tienda",0,0,0,0);
            estrategias3[2]= new Estrategia("Publicidad",4,7,6,8);
            estrategias3[3]= new Estrategia("No invertir",2,3,2,3);
            
            organizacion3.estrategia = estrategias3;
            
        capitalT=Normal(50000000,2000000);
        organizacion4= new Organizacion("EXITO Express",capitalT);
            tiendasTemp=Normal(20,5);
            for(int i=0;i<tiendasTemp;i++){
                int zona=rnd.nextInt(4);
                String nombreTemporal="";
                double ventasT=0;
                switch(zona){
                    case 0:
                        nombreTemporal=ZONAN;
                        ventasT=VZONAN;
                        break;
                    case 1:
                        nombreTemporal=ZONAS;
                        ventasT=VZONAS;
                        break;
                    case 2:
                        nombreTemporal=ZONAO;
                        ventasT=VZONAO;
                        break;
                    case 3:
                        nombreTemporal=ZONAOCC;
                        ventasT=VZONAOCC;
                        break;
                }
                Tienda tiendaTemp=new Tienda(nombreTemporal,ventasT,GASTOBASE);
                organizacion4.agregarTienda(tiendaTemp);
            }
            
            estrategias4[0]= new Estrategia("Abrir Tienda",5,10,8,9);
            estrategias4[1]= new Estrategia("Cerrar Tienda",0,0,0,0);
            estrategias4[2]= new Estrategia("Publicidad",4,7,6,8);
            estrategias4[3]= new Estrategia("No invertir",2,3,2,3);
            
            organizacion4.estrategia = estrategias4;
            
        capitalT=Normal(50000000,2000000);
        organizacion5= new Organizacion("Oxxo",capitalT);
            tiendasTemp=Normal(20,5);
            for(int i=0;i<tiendasTemp;i++){
                int zona=rnd.nextInt(4);
                String nombreTemporal="";
                double ventasT=0;
                switch(zona){
                    case 0:
                        nombreTemporal=ZONAN;
                        ventasT=VZONAN;
                        break;
                    case 1:
                        nombreTemporal=ZONAS;
                        ventasT=VZONAS;
                        break;
                    case 2:
                        nombreTemporal=ZONAO;
                        ventasT=VZONAO;
                        break;
                    case 3:
                        nombreTemporal=ZONAOCC;
                        ventasT=VZONAOCC;
                        break;
                }
                Tienda tiendaTemp=new Tienda(nombreTemporal,ventasT,GASTOBASE);
                organizacion5.agregarTienda(tiendaTemp);
            }
           
            estrategias5[0]= new Estrategia("Abrir Tienda",5,10,8,9);
            estrategias5[1]= new Estrategia("Cerrar Tienda",0,0,0,0);
            estrategias5[2]= new Estrategia("Publicidad",4,7,6,8);
            estrategias5[3]= new Estrategia("No invertir",2,3,2,3);
            
            organizacion5.estrategia = estrategias5;
    }
    
    public static void fitness(Organizacion org1, Organizacion org2){
        
    }       
    
    public static void evaluarEstrategia(){
        
    }
}