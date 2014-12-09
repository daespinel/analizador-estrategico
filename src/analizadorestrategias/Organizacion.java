/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package analizadorestrategias;

import static analizadorestrategias.Motor.organizacion1;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class Organizacion {
    
    private String nombre;
    private ArrayList<Tienda> tiendas = new ArrayList<Tienda>();
    private double ganancias;
    private double capital;
    public Estrategia[] estrategia;
    private double ingresos; 
    private double gastos;
    private int estrategiaActual;
    

    
    public Organizacion(){
    }
    
    public Organizacion(String nombre,double capital){
        setNombre(nombre);
        setCapital(capital);
        setEstrategiaActual(0);
    }
    /**
     * @return the nombre
     */
    public int getSize(){
        int a = tiendas.size();
        return a;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the tiendas
     */
    public ArrayList<Tienda> getTiendas() {
        return tiendas;
    }

    /**
     * @param tiendas the tiendas to set
     */
    public void setTiendas(ArrayList<Tienda> tiendas) {
        this.tiendas = tiendas;
    }
    
    public int getNumeroTiendas(){
        return tiendas.size();
    }

    /**
     * @return the ganancias
     */
    public double getGanancias() {
        return ganancias;
    }

    /**
     * @param ganancias the ganancias to set
     */
    public void setGanancias(double ganancias) {
        this.ganancias = ganancias;
    }

    /**
     * @return the capital
     */
    public double getCapital() {
        return capital;
    }

    /**
     * @param capital the capital to set
     */
    public void setCapital(double capital) {
        this.capital = capital;
    }

    /**
     * @return the estrategia
     */
    public Estrategia[] getEstrategia() {
        return estrategia;
    }

    /**
     * @param estrategia the estrategia to set
     */
    public void setEstrategia(Estrategia[] estrategia) {
        this.estrategia = estrategia;
    }
    
    public void agregarTienda(Tienda tienda){
        tiendas.add(tienda);
    }

    /**
     * @return the ingresos
     */
    public double getIngresos() {
        return ingresos;
    }

    /**
     * @param ingresos the ingresos to set
     */
    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }
    
    public void calcularIngresosTiendas(){
        double temporalDeIngresos=0;
        
        for(int i=0;i<tiendas.size();i++){
            temporalDeIngresos=temporalDeIngresos+(tiendas.get(i).getVentas()*Motor.valorFactura);
        }
        setIngresos(temporalDeIngresos);
        double temporalDeGastos=0;
        
        for(int i=0;i<tiendas.size();i++){
            temporalDeGastos=temporalDeGastos+(tiendas.get(i).getGastos());
        }
        setGastos(temporalDeGastos);
        double Ganancia = temporalDeIngresos - temporalDeGastos;
        setGanancias(Ganancia);
    }
    
    public double getTotalVentas(){
        double numVentas=0;
        for(int i=0;i<tiendas.size();i++){
            numVentas=numVentas+(tiendas.get(i).getVentas());
        }
        return numVentas;
    }
    
    public String getCapitalFormato(){
        DecimalFormat df = new DecimalFormat("#########################.##");
                return String.valueOf(df.format(getCapital()));
    }
    
    public String getIngresosFormato(){
            DecimalFormat df = new DecimalFormat("#########################.##");
                return String.valueOf(df.format(getIngresos()));
    }
    
        public String getGastosFormato(){
            DecimalFormat df = new DecimalFormat("#########################.##");
                return String.valueOf(df.format(getGastos()));
    }
    
    public String getGananciaFormato(){
            DecimalFormat df = new DecimalFormat("#########################.##");
                return String.valueOf(df.format(getGanancias()));
    }

    /**
     * @return the gastos
     */
    public double getGastos() {
        return gastos;
    }

    /**
     * @param gastos the gastos to set
     */
    public void setGastos(double gastos) {
        this.gastos = gastos;
    }

    /**
     * @return the estrategiaActual
     */
    public int getEstrategiaActual() {
        return estrategiaActual;
    }

    /**
     * @param estrategiaActual the estrategiaActual to set
     */
    public void setEstrategiaActual(int estrategiaActual) {
        this.estrategiaActual = estrategiaActual;
    }
    
    public void cambiarDatosTienda(Tienda tienda, int posicion){
        tiendas.set(posicion, tienda);
    }
    
}
