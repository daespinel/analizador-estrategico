/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package analizadorestrategias;

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

    
    public Organizacion(){
    }
    
    public Organizacion(String nombre,double capital){
        setNombre(nombre);
        setCapital(capital);
    }
    /**
     * @return the nombre
     */
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
    
}
