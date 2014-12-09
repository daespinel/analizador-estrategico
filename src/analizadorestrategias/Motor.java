/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package analizadorestrategias;

import java.math.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Lorena
 * @author David
 */
public class Motor {
    
    public static ArrayList<Double> valores = new ArrayList<Double>();
    
    public static Estrategia[] estrategias1 = new Estrategia[4];
    public static Estrategia[] estrategias2 = new Estrategia[4];
    public static Estrategia[] estrategias3 = new Estrategia[4];
    public static Estrategia[] estrategias4 = new Estrategia[4];
    public static Estrategia[] estrategias5 = new Estrategia[4];
    
    public static Estrategia mejorEstrategia = new Estrategia();
    public static Estrategia estrategia1 = new Estrategia();
    public static Estrategia estrategia2 = new Estrategia();
    public static Estrategia estrategia3 = new Estrategia();
    public static Estrategia estrategia4 = new Estrategia();
        
    public static Organizacion organizacion1;
    public static Organizacion organizacion2;
    public static Organizacion organizacion3;
    public static Organizacion organizacion4;
    public static Organizacion organizacion5;
    
    public static int pagoEst1;
    public static int pagoEst2;
    public static int pagoEst3;
    public static int pagoEst4;
    
    public static Analizador ui;
    
    public static int generaciones=0;
    public static double tasaRiesgoLibre =5;
    public static double volatilidadMercado =10;
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
    public static int eComp = 0;
    
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
                organizacion1.agregarTienda(tiendaTemp);
            
            }
            
            estrategias1[0]= new Estrategia("Abrir Tienda",5,10,8,9);
            estrategias1[1]= new Estrategia("Cerrar Tienda",0,0,0,0);
            estrategias1[2]= new Estrategia("Publicidad",4,7,6,8);
            estrategias1[3]= new Estrategia("No invertir",2,3,2,3);
            
            organizacion1.estrategia = estrategias1; 
            
            while(generaciones<100000){
                ArrayList<Organizacion> temporalesDeAsignacion = new ArrayList<Organizacion>();
                inicializarEstrategias();
                variacionDelMercado();
                organizacion1.calcularIngresosTiendas();
                organizacion2.calcularIngresosTiendas();
                organizacion3.calcularIngresosTiendas();
                organizacion4.calcularIngresosTiendas();
                organizacion5.calcularIngresosTiendas();
                
                analizarVentasDeTiendasOrganizacion1();
                
                temporalesDeAsignacion=fitness(organizacion1, organizacion2);
                organizacion1=temporalesDeAsignacion.get(0);
                organizacion2=temporalesDeAsignacion.get(1);
                estrategia1 = evaluarEstrategias(organizacion1);
                pagoEst1 = pagoEstrategia(estrategia1,estrategiaAleatoria(organizacion2));
                                                
                temporalesDeAsignacion=fitness(organizacion1, organizacion3);
                organizacion1=temporalesDeAsignacion.get(0);
                organizacion3=temporalesDeAsignacion.get(1);
                estrategia2 = evaluarEstrategias(organizacion1);
                pagoEst2 = pagoEstrategia(estrategia2,estrategiaAleatoria(organizacion3));
                
                temporalesDeAsignacion=fitness(organizacion1, organizacion4);
                organizacion1=temporalesDeAsignacion.get(0);
                organizacion4=temporalesDeAsignacion.get(1);
                estrategia3 = evaluarEstrategias(organizacion1);
                pagoEst3 = pagoEstrategia(estrategia3,estrategiaAleatoria(organizacion4));
                
                temporalesDeAsignacion=fitness(organizacion1, organizacion5);
                organizacion1=temporalesDeAsignacion.get(0);
                organizacion5=temporalesDeAsignacion.get(1);
                estrategia4 = evaluarEstrategias(organizacion1);
                pagoEst4 = pagoEstrategia(estrategia4,estrategiaAleatoria(organizacion5));
                
                comparaEstrategias();
                valores.add(organizacion1.getGanancias());
                
                double capitalTemporal=0;
                capitalTemporal=organizacion1.getCapital()+organizacion1.getGanancias()-organizacion1.getGastos();
                organizacion1.setCapital(capitalTemporal);
                capitalTemporal=organizacion2.getCapital()+organizacion2.getGanancias()-organizacion1.getGastos();
                organizacion2.setCapital(capitalTemporal);
                capitalTemporal=organizacion3.getCapital()+organizacion3.getGanancias()-organizacion1.getGastos();
                organizacion3.setCapital(capitalTemporal);
                capitalTemporal=organizacion4.getCapital()+organizacion4.getGanancias()-organizacion1.getGastos();
                organizacion4.setCapital(capitalTemporal);
                capitalTemporal=organizacion5.getCapital()+organizacion5.getGanancias()-organizacion1.getGastos();
                organizacion5.setCapital(capitalTemporal);
                
                if(mejorEstrategia.getNombre().equals("Abrir Tienda")){
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
                    organizacion1.agregarTienda(tiendaTemp);
                    
                }
                else{
                    if(mejorEstrategia.getNombre().equals("Cerrar Tienda")){
                        ArrayList<Tienda> tiendasAEliminar =organizacion1.getTiendas();
                        Tienda tiendaAEliminar=tiendasAEliminar.get(0);
                        int posicionAEliminar=0;
                        for(int i=0;i<tiendasAEliminar.size();i++){
                            if(tiendaAEliminar.getVentas()>tiendasAEliminar.get(i).getVentas()){
                                tiendaAEliminar=tiendasAEliminar.get(i);
                                posicionAEliminar=i;
                                
                                
                            }
                        }
                        organizacion1.eliminarTienda(posicionAEliminar);
                    }
                    else{
                        if(mejorEstrategia.getNombre().equals("Publicidad")){
                            double capital=organizacion1.getCapital();
                            organizacion1.setCapital(capital-50000000);
                            int algo =rnd.nextInt(organizacion1.getSize());
                            Tienda cambio=organizacion1.getTiendas().get(algo);
                            double ventasActuales=cambio.getVentas();
                            cambio.setVentas(ventasActuales+ventasActuales*0.05);
                            
                            organizacion1.cambiarDatosTienda(cambio, algo);
                        }
                        else{
                            if(mejorEstrategia.getNombre().equals("No invertir")){
                                double capital=organizacion1.getCapital();
                                organizacion1.setCapital(capital-25000000);
                            }
                            }
                }
                
                ui.continuar();
                generaciones++;
                ui.colocarGeneraciones(getGen());
                ui.mostrarDatosOrganizacion1();
                System.out.println(generaciones+" "+ organizacion1.getGananciaFormato());
                
            }
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
    
    
    
    public static ArrayList<Organizacion> fitness(Organizacion org1, Organizacion org2){
        ArrayList<Organizacion> temporales = new ArrayList<Organizacion>();
        if (org1.getSize()> org2.getSize()){
            double diferencia = org1.getSize() - org2.getSize();
            org1.estrategia[0].setAbrirTienda(org1.estrategia[0].getAbrirTienda()-1);
            org1.estrategia[0].setCerrarTienda(org1.estrategia[0].getCerrarTienda());
            org1.estrategia[0].setPublicidad(org1.estrategia[0].getPublicidad()+1);
            org1.estrategia[0].setNoInvertir(org1.estrategia[0].getNoInvertir());
            org1.estrategia[1].setAbrirTienda(org1.estrategia[1].getAbrirTienda()+2);
            org1.estrategia[1].setCerrarTienda(org1.estrategia[1].getCerrarTienda()+3);
            org1.estrategia[1].setPublicidad(org1.estrategia[1].getPublicidad());
            org1.estrategia[1].setNoInvertir(org1.estrategia[1].getNoInvertir()+2);
            org1.estrategia[2].setAbrirTienda(org1.estrategia[2].getAbrirTienda());
            org1.estrategia[2].setCerrarTienda(org1.estrategia[2].getCerrarTienda()+1);
            org1.estrategia[2].setPublicidad(org1.estrategia[2].getPublicidad()+1);
            org1.estrategia[2].setNoInvertir(org1.estrategia[2].getNoInvertir()+1);
            org1.estrategia[3].setAbrirTienda(org1.estrategia[3].getAbrirTienda()+2);
            org1.estrategia[3].setCerrarTienda(org1.estrategia[3].getCerrarTienda());
            org1.estrategia[3].setPublicidad(org1.estrategia[3].getPublicidad()+1);
            org1.estrategia[3].setNoInvertir(org1.estrategia[3].getNoInvertir()+1);

            org2.estrategia[0].setAbrirTienda(org2.estrategia[0].getAbrirTienda()-1);
            org2.estrategia[0].setCerrarTienda(org2.estrategia[0].getCerrarTienda());
            org2.estrategia[0].setPublicidad(org2.estrategia[0].getPublicidad()+1);
            org2.estrategia[0].setNoInvertir(org2.estrategia[0].getNoInvertir());
            org2.estrategia[1].setAbrirTienda(org2.estrategia[1].getAbrirTienda()+2);
            org2.estrategia[1].setCerrarTienda(org2.estrategia[1].getCerrarTienda()+3);
            org2.estrategia[1].setPublicidad(org2.estrategia[1].getPublicidad());
            org2.estrategia[1].setNoInvertir(org2.estrategia[1].getNoInvertir()+2);
            org2.estrategia[2].setAbrirTienda(org2.estrategia[2].getAbrirTienda());
            org2.estrategia[2].setCerrarTienda(org2.estrategia[2].getCerrarTienda()+1);
            org2.estrategia[2].setPublicidad(org2.estrategia[2].getPublicidad()+1);
            org2.estrategia[2].setNoInvertir(org2.estrategia[2].getNoInvertir()+1);
            org2.estrategia[3].setAbrirTienda(org2.estrategia[3].getAbrirTienda()+2);
            org2.estrategia[3].setCerrarTienda(org2.estrategia[3].getCerrarTienda());
            org2.estrategia[3].setPublicidad(org2.estrategia[3].getPublicidad()+1);
            org2.estrategia[3].setNoInvertir(org2.estrategia[3].getNoInvertir()+1);
        }
        else{
            org1.estrategia[0].setAbrirTienda(org1.estrategia[0].getAbrirTienda()-1);
            org1.estrategia[0].setCerrarTienda(org1.estrategia[0].getCerrarTienda()+1);
            org1.estrategia[0].setPublicidad(org1.estrategia[0].getPublicidad());
            org1.estrategia[0].setNoInvertir(org1.estrategia[0].getNoInvertir());
            org1.estrategia[1].setAbrirTienda(org1.estrategia[1].getAbrirTienda());
            org1.estrategia[1].setCerrarTienda(org1.estrategia[1].getCerrarTienda());
            org1.estrategia[1].setPublicidad(org1.estrategia[1].getPublicidad());
            org1.estrategia[1].setNoInvertir(org1.estrategia[1].getNoInvertir());
            org1.estrategia[2].setAbrirTienda(org1.estrategia[2].getAbrirTienda()+3);
            org1.estrategia[2].setCerrarTienda(org1.estrategia[2].getCerrarTienda()-1);
            org1.estrategia[2].setPublicidad(org1.estrategia[2].getPublicidad()+1);
            org1.estrategia[2].setNoInvertir(org1.estrategia[2].getNoInvertir()-1);
            org1.estrategia[3].setAbrirTienda(org1.estrategia[3].getAbrirTienda()+2);
            org1.estrategia[3].setCerrarTienda(org1.estrategia[3].getCerrarTienda());
            org1.estrategia[3].setPublicidad(org1.estrategia[3].getPublicidad()+2);
            org1.estrategia[3].setNoInvertir(org1.estrategia[3].getNoInvertir()+1);

            org2.estrategia[0].setAbrirTienda(org2.estrategia[0].getAbrirTienda()-1);
            org2.estrategia[0].setCerrarTienda(org2.estrategia[0].getCerrarTienda()+1);
            org2.estrategia[0].setPublicidad(org2.estrategia[0].getPublicidad());
            org2.estrategia[0].setNoInvertir(org2.estrategia[0].getNoInvertir());
            org2.estrategia[1].setAbrirTienda(org2.estrategia[1].getAbrirTienda());
            org2.estrategia[1].setCerrarTienda(org2.estrategia[1].getCerrarTienda());
            org2.estrategia[1].setPublicidad(org2.estrategia[1].getPublicidad());
            org2.estrategia[1].setNoInvertir(org2.estrategia[1].getNoInvertir());
            org2.estrategia[2].setAbrirTienda(org2.estrategia[2].getAbrirTienda()+3);
            org2.estrategia[2].setCerrarTienda(org2.estrategia[2].getCerrarTienda()-1);
            org2.estrategia[2].setPublicidad(org2.estrategia[2].getPublicidad()+1);
            org2.estrategia[2].setNoInvertir(org2.estrategia[2].getNoInvertir()-1);
            org2.estrategia[3].setAbrirTienda(org2.estrategia[3].getAbrirTienda()+2);
            org2.estrategia[3].setCerrarTienda(org2.estrategia[3].getCerrarTienda());
            org2.estrategia[3].setPublicidad(org2.estrategia[3].getPublicidad()+2);
            org2.estrategia[3].setNoInvertir(org2.estrategia[3].getNoInvertir()+1);
        }
        if (org1.getGanancias()> org2.getGanancias()){
            double diferencia = org1.getGanancias() - org2.getGanancias();
            
            org1.estrategia[0].setAbrirTienda(org1.estrategia[0].getAbrirTienda()+1);
            org1.estrategia[0].setCerrarTienda(org1.estrategia[0].getCerrarTienda());
            org1.estrategia[0].setPublicidad(org1.estrategia[0].getPublicidad()+2);
            org1.estrategia[0].setNoInvertir(org1.estrategia[0].getNoInvertir()+1);
            org1.estrategia[1].setAbrirTienda(org1.estrategia[1].getAbrirTienda());
            org1.estrategia[1].setCerrarTienda(org1.estrategia[1].getCerrarTienda()+2);
            org1.estrategia[1].setPublicidad(org1.estrategia[1].getPublicidad()+1);
            org1.estrategia[1].setNoInvertir(org1.estrategia[1].getNoInvertir()+2);
            org1.estrategia[2].setAbrirTienda(org1.estrategia[2].getAbrirTienda()-1);
            org1.estrategia[2].setCerrarTienda(org1.estrategia[2].getCerrarTienda()+1);
            org1.estrategia[2].setPublicidad(org1.estrategia[2].getPublicidad()+2);
            org1.estrategia[2].setNoInvertir(org1.estrategia[2].getNoInvertir()+1);
            org1.estrategia[3].setAbrirTienda(org1.estrategia[3].getAbrirTienda()+2);
            org1.estrategia[3].setCerrarTienda(org1.estrategia[3].getCerrarTienda());
            org1.estrategia[3].setPublicidad(org1.estrategia[3].getPublicidad()+2);
            org1.estrategia[3].setNoInvertir(org1.estrategia[3].getNoInvertir()+1);
            
            org2.estrategia[0].setAbrirTienda(org2.estrategia[0].getAbrirTienda()+1);
            org2.estrategia[0].setCerrarTienda(org2.estrategia[0].getCerrarTienda());
            org2.estrategia[0].setPublicidad(org2.estrategia[0].getPublicidad()+2);
            org2.estrategia[0].setNoInvertir(org2.estrategia[0].getNoInvertir()+1);
            org2.estrategia[1].setAbrirTienda(org2.estrategia[1].getAbrirTienda());
            org2.estrategia[1].setCerrarTienda(org2.estrategia[1].getCerrarTienda()+2);
            org2.estrategia[1].setPublicidad(org2.estrategia[1].getPublicidad()+1);
            org2.estrategia[1].setNoInvertir(org2.estrategia[1].getNoInvertir()+1);
            org2.estrategia[2].setAbrirTienda(org2.estrategia[2].getAbrirTienda()-1);
            org2.estrategia[2].setCerrarTienda(org2.estrategia[2].getCerrarTienda()+1);
            org2.estrategia[2].setPublicidad(org2.estrategia[2].getPublicidad()+2);
            org2.estrategia[2].setNoInvertir(org2.estrategia[2].getNoInvertir()+1);
            org2.estrategia[3].setAbrirTienda(org2.estrategia[3].getAbrirTienda()+2);
            org2.estrategia[3].setCerrarTienda(org2.estrategia[3].getCerrarTienda());
            org2.estrategia[3].setPublicidad(org2.estrategia[3].getPublicidad()+2);
            org2.estrategia[3].setNoInvertir(org2.estrategia[3].getNoInvertir()+1);
        }
        else{
            org1.estrategia[0].setAbrirTienda(org1.estrategia[0].getAbrirTienda()-3);
            org1.estrategia[0].setCerrarTienda(org1.estrategia[0].getCerrarTienda());
            org1.estrategia[0].setPublicidad(org1.estrategia[0].getPublicidad()-2);
            org1.estrategia[0].setNoInvertir(org1.estrategia[0].getNoInvertir()-1);
            org1.estrategia[1].setAbrirTienda(org1.estrategia[1].getAbrirTienda());
            org1.estrategia[1].setCerrarTienda(org1.estrategia[1].getCerrarTienda());
            org1.estrategia[1].setPublicidad(org1.estrategia[1].getPublicidad());
            org1.estrategia[1].setNoInvertir(org1.estrategia[1].getNoInvertir());
            org1.estrategia[2].setAbrirTienda(org1.estrategia[2].getAbrirTienda()+1);
            org1.estrategia[2].setCerrarTienda(org1.estrategia[2].getCerrarTienda()+1);
            org1.estrategia[2].setPublicidad(org1.estrategia[2].getPublicidad()+1);
            org1.estrategia[2].setNoInvertir(org1.estrategia[2].getNoInvertir());
            org1.estrategia[3].setAbrirTienda(org1.estrategia[3].getAbrirTienda()+2);
            org1.estrategia[3].setCerrarTienda(org1.estrategia[3].getCerrarTienda()+1);
            org1.estrategia[3].setPublicidad(org1.estrategia[3].getPublicidad()+1);
            org1.estrategia[3].setNoInvertir(org1.estrategia[3].getNoInvertir());

            org2.estrategia[0].setAbrirTienda(org2.estrategia[0].getAbrirTienda()-3);
            org2.estrategia[0].setCerrarTienda(org2.estrategia[0].getCerrarTienda());
            org2.estrategia[0].setPublicidad(org2.estrategia[0].getPublicidad()-2);
            org2.estrategia[0].setNoInvertir(org2.estrategia[0].getNoInvertir()-1);
            org2.estrategia[1].setAbrirTienda(org2.estrategia[1].getAbrirTienda());
            org2.estrategia[1].setCerrarTienda(org2.estrategia[1].getCerrarTienda());
            org2.estrategia[1].setPublicidad(org2.estrategia[1].getPublicidad());
            org2.estrategia[1].setNoInvertir(org2.estrategia[1].getNoInvertir());
            org2.estrategia[2].setAbrirTienda(org2.estrategia[2].getAbrirTienda()+1);
            org2.estrategia[2].setCerrarTienda(org2.estrategia[2].getCerrarTienda()+1);
            org2.estrategia[2].setPublicidad(org2.estrategia[2].getPublicidad()+1);
            org2.estrategia[2].setNoInvertir(org2.estrategia[2].getNoInvertir());
            org2.estrategia[3].setAbrirTienda(org2.estrategia[3].getAbrirTienda()+2);
            org2.estrategia[3].setCerrarTienda(org2.estrategia[3].getCerrarTienda()+1);
            org2.estrategia[3].setPublicidad(org2.estrategia[3].getPublicidad()+1);
            org2.estrategia[3].setNoInvertir(org2.estrategia[3].getNoInvertir()); 
        }  
        temporales.add(org1);
        temporales.add(org2);
        
        return temporales;
    }
    
    public static Estrategia evaluarEstrategias(Organizacion org1){
            
             Estrategia estrategiaMaximin = new Estrategia();   
        
             int a = 0;
                          
             int min01 = Math.min(org1.estrategia[0].getAbrirTienda(),org1.estrategia[0].getCerrarTienda());
             int min02 = Math.min(org1.estrategia[0].getPublicidad(),org1.estrategia[0].getNoInvertir());
             int min0= Math.min(min01,min02);
             
             int min11 = Math.min(org1.estrategia[1].getAbrirTienda(),org1.estrategia[1].getCerrarTienda());
             int min12 = Math.min(org1.estrategia[1].getPublicidad(),org1.estrategia[1].getNoInvertir());
             int min1= Math.min(min11,min12);
             
             int min21 = Math.min(org1.estrategia[2].getAbrirTienda(),org1.estrategia[2].getCerrarTienda());
             int min22 = Math.min(org1.estrategia[2].getPublicidad(),org1.estrategia[2].getNoInvertir());
             int min2= Math.min(min21,min22);
             
             int min31 = Math.min(org1.estrategia[3].getAbrirTienda(),org1.estrategia[3].getCerrarTienda());
             int min32 = Math.min(org1.estrategia[3].getPublicidad(),org1.estrategia[3].getNoInvertir());
             int min3= Math.min(min31,min32);
             
             int[] minimos = {min0,min1,min2,min3}; 
             
             int max1 = Math.max(min0,min1);
             int max2 = Math.max(min2,min3);
             int maximo = Math.max(max1, max2);
             
             for(int i = 0; i < minimos.length; i++){
                 if (minimos[i] == maximo){
                     a = i;
                     break;
                 }
             }
             
             switch(a){
                 case 0:
                     estrategiaMaximin = org1.estrategia[0];
                     break;
                 case 1:
                     estrategiaMaximin = org1.estrategia[1];
                     break;
                 case 2:
                     estrategiaMaximin = org1.estrategia[2];
                     break;
                 case 3:
                     estrategiaMaximin = org1.estrategia[3];
                     break;
             }
             
             return estrategiaMaximin;
    }
    
    public static Estrategia estrategiaAleatoria(Organizacion org){
        
        Estrategia estrategiaCompetencia = new Estrategia();
        eComp = rnd.nextInt(4); 
        
        switch(eComp){
                 case 0:
                     estrategiaCompetencia = org.estrategia[0];
                     break;
                 case 1:
                     estrategiaCompetencia = org.estrategia[1];
                     break;
                 case 2:
                     estrategiaCompetencia = org.estrategia[2];
                     break;
                 case 3:
                     estrategiaCompetencia = org.estrategia[3];
                     break;
             }
        return estrategiaCompetencia;
        
    }
    
    public static int pagoEstrategia(Estrategia maximin, Estrategia competencia){
        int pago = 0;

        switch(eComp){
                 case 0:
                     pago = maximin.getAbrirTienda();
                     break;
                 case 1:
                     pago = maximin.getCerrarTienda();
                     break;
                 case 2:
                     pago = maximin.getPublicidad();
                     break;
                 case 3:
                     pago = maximin.getNoInvertir();
                     break;
             } 
        return pago;
    }
    
    public static void comparaEstrategias(){
        
        int max1 = Math.max(pagoEst1, pagoEst2);
        int max2 = Math.max(pagoEst3, pagoEst4);
        int max = Math.max(max1, max2);
        
        if (max == pagoEst1){
            mejorEstrategia = estrategia1;
        }
        else{
            if (max == pagoEst2){
                mejorEstrategia = estrategia2;
            }
            else{
                if (max == pagoEst3){
                    mejorEstrategia = estrategia3;
                }
                else{
                    mejorEstrategia = estrategia4;
                }
            }
        } 
    }
    
    public static void variacionDelMercado(){
        for(int i=0;i<organizacion1.getSize();i++){
            Tienda tiendaTemporal=organizacion1.getTiendas().get(i);
            double numeroVentasIniciales=tiendaTemporal.getVentas();
            double maximoDiezPorCiento=numeroVentasIniciales*.1;
            double nuevoValorDeVentas=0;
           
            do{
                int seleccionDeCrecimiento=rnd.nextInt(2);
                double valorDeCambio=(double)rnd.nextInt((int)maximoDiezPorCiento);
                switch(seleccionDeCrecimiento){
                    case(0):
                        nuevoValorDeVentas=numeroVentasIniciales+valorDeCambio;
                        break;
                    case(1):
                        nuevoValorDeVentas=numeroVentasIniciales-valorDeCambio;
                        break;
                }
            }while(numeroVentasIniciales<0);
            tiendaTemporal.setVentas(nuevoValorDeVentas);
            organizacion1.cambiarDatosTienda(tiendaTemporal, i);
            
            
        }
        
        for(int i=0;i<organizacion2.getSize();i++){
            Tienda tiendaTemporal=organizacion2.getTiendas().get(i);
            double numeroVentasIniciales=tiendaTemporal.getVentas();
            double maximoDiezPorCiento=numeroVentasIniciales*.1;
            double nuevoValorDeVentas=0;
           
            do{
                int seleccionDeCrecimiento=rnd.nextInt(2);
                double valorDeCambio=(double)rnd.nextInt((int)maximoDiezPorCiento);
                switch(seleccionDeCrecimiento){
                    case(0):
                        nuevoValorDeVentas=numeroVentasIniciales+valorDeCambio;
                        break;
                    case(1):
                        nuevoValorDeVentas=numeroVentasIniciales-valorDeCambio;
                        break;
                }
            }while(numeroVentasIniciales<0);
            tiendaTemporal.setVentas(nuevoValorDeVentas);
            organizacion2.cambiarDatosTienda(tiendaTemporal, i);
        }
        
        for(int i=0;i<organizacion3.getSize();i++){
            Tienda tiendaTemporal=organizacion3.getTiendas().get(i);
            double numeroVentasIniciales=tiendaTemporal.getVentas();
            double maximoDiezPorCiento=numeroVentasIniciales*.1;
            double nuevoValorDeVentas=0;
           
            do{
                int seleccionDeCrecimiento=rnd.nextInt(2);
                double valorDeCambio=(double)rnd.nextInt((int)maximoDiezPorCiento);
                switch(seleccionDeCrecimiento){
                    case(0):
                        nuevoValorDeVentas=numeroVentasIniciales+valorDeCambio;
                        break;
                    case(1):
                        nuevoValorDeVentas=numeroVentasIniciales-valorDeCambio;
                        break;
                }
            }while(numeroVentasIniciales<0);
            tiendaTemporal.setVentas(nuevoValorDeVentas);
            organizacion3.cambiarDatosTienda(tiendaTemporal, i);
        }
        
        for(int i=0;i<organizacion4.getSize();i++){
            Tienda tiendaTemporal=organizacion4.getTiendas().get(i);
            double numeroVentasIniciales=tiendaTemporal.getVentas();
            double maximoDiezPorCiento=numeroVentasIniciales*.1;
            double nuevoValorDeVentas=0;
           
            do{
                int seleccionDeCrecimiento=rnd.nextInt(2);
                double valorDeCambio=(double)rnd.nextInt((int)maximoDiezPorCiento);
                switch(seleccionDeCrecimiento){
                    case(0):
                        nuevoValorDeVentas=numeroVentasIniciales+valorDeCambio;
                        break;
                    case(1):
                        nuevoValorDeVentas=numeroVentasIniciales-valorDeCambio;
                        break;
                }
            }while(numeroVentasIniciales<0);
            tiendaTemporal.setVentas(nuevoValorDeVentas);
            organizacion4.cambiarDatosTienda(tiendaTemporal, i);
        }
        
        for(int i=0;i<organizacion5.getSize();i++){
            Tienda tiendaTemporal=organizacion5.getTiendas().get(i);
            double numeroVentasIniciales=tiendaTemporal.getVentas();
            double maximoDiezPorCiento=numeroVentasIniciales*.1;
            double nuevoValorDeVentas=0;
           
            do{
                int seleccionDeCrecimiento=rnd.nextInt(2);
                double valorDeCambio=(double)rnd.nextInt((int)maximoDiezPorCiento);
                switch(seleccionDeCrecimiento){
                    case(0):
                        nuevoValorDeVentas=numeroVentasIniciales+valorDeCambio;
                        break;
                    case(1):
                        nuevoValorDeVentas=numeroVentasIniciales-valorDeCambio;
                        break;
                }
            }while(numeroVentasIniciales<0);
            tiendaTemporal.setVentas(nuevoValorDeVentas);
            organizacion5.cambiarDatosTienda(tiendaTemporal, i);
        }
    }
    
    public static void inicializarEstrategias(){
            estrategias1[0]= new Estrategia("Abrir Tienda",5,10,8,9);
            estrategias1[1]= new Estrategia("Cerrar Tienda",0,0,0,0);
            estrategias1[2]= new Estrategia("Publicidad",4,7,6,8);
            estrategias1[3]= new Estrategia("No invertir",2,3,2,3);
            
            estrategias2[0]= new Estrategia("Abrir Tienda",5,10,8,9);
            estrategias2[1]= new Estrategia("Cerrar Tienda",0,0,0,0);
            estrategias2[2]= new Estrategia("Publicidad",4,7,6,8);
            estrategias2[3]= new Estrategia("No invertir",2,3,2,3);
            
            estrategias3[0]= new Estrategia("Abrir Tienda",5,10,8,9);
            estrategias3[1]= new Estrategia("Cerrar Tienda",0,0,0,0);
            estrategias3[2]= new Estrategia("Publicidad",4,7,6,8);
            estrategias3[3]= new Estrategia("No invertir",2,3,2,3);
            
            estrategias4[0]= new Estrategia("Abrir Tienda",5,10,8,9);
            estrategias4[1]= new Estrategia("Cerrar Tienda",0,0,0,0);
            estrategias4[2]= new Estrategia("Publicidad",4,7,6,8);
            estrategias4[3]= new Estrategia("No invertir",2,3,2,3);
            
            estrategias5[0]= new Estrategia("Abrir Tienda",5,10,8,9);
            estrategias5[1]= new Estrategia("Cerrar Tienda",0,0,0,0);
            estrategias5[2]= new Estrategia("Publicidad",4,7,6,8);
            estrategias5[3]= new Estrategia("No invertir",2,3,2,3);
    }
    
    public static void analizarVentasDeTiendasOrganizacion1(){
            
            ArrayList<Tienda> tiendasDeAnalisis=organizacion1.getTiendas();
            for(int i=0;i<tiendasDeAnalisis.size();i++){
                String analizador= tiendasDeAnalisis.get(i).getZona();
                if(analizador.equals(ZONAN)){
                    if(tiendasDeAnalisis.get(i).getVentas()*valorFactura<tiendasDeAnalisis.get(i).getGastos()){
                        ui.cambiarZonaNorteRojo();
                    }
                    else{
                        ui.cambiarZonaNorteVerde();
                    }
                }
                if(analizador.equals(ZONAS)){
                    if(tiendasDeAnalisis.get(i).getVentas()*valorFactura<tiendasDeAnalisis.get(i).getGastos()){
                        ui.cambiarZonaSurRojo();
                    }
                    else{
                        ui.cambiarZonaSurVerde();
                    }
                }
                if(analizador.equals(ZONAO)){
                    if(tiendasDeAnalisis.get(i).getVentas()*valorFactura<tiendasDeAnalisis.get(i).getGastos()){
                        ui.cambiarZonaOrienteRojo();
                    }
                    else{
                        ui.cambiarZonaOrienteVerde();
                    }
                }
                if(analizador.equals(ZONAOCC)){
                    if(tiendasDeAnalisis.get(i).getVentas()*valorFactura<tiendasDeAnalisis.get(i).getGastos()){
                        ui.cambiarZonaOccidenteRojo();
                    }
                    else{
                        ui.cambiarZonaOccidenteVerde();
                    }
                }
            }
    }
}