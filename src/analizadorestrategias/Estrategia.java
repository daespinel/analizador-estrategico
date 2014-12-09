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
public class Estrategia {
    
    private String nombre;
    private int abrirTienda;
    private int cerrarTienda;
    private int publicidad;
    private int noInvertir;
            
    public Estrategia(){    
    }
    
    public Estrategia(String nombre , int abrirTienda ,int cerrarTienda, int publicidad, int pago4){
        this.setNombre(nombre);
        this.setAbrirTienda(abrirTienda);
        this.setCerrarTienda(cerrarTienda);
        this.setPublicidad(publicidad);
        this.setNoInvertir(noInvertir);
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    public int getAbrirTienda() {
        return abrirTienda;
    }
    
    public int getCerrarTienda() {
        return cerrarTienda;
    }
    
    public int getPublicidad() {
        return publicidad;
    }
    
    public int getNoInvertir() {
        return noInvertir;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setAbrirTienda(int abrirTienda) {
        this.abrirTienda = abrirTienda;
    }
    
    public void setCerrarTienda(int cerrarTienda) {
        this.cerrarTienda = cerrarTienda;
    }
    
    public void setPublicidad(int publicidad) {
        this.publicidad = publicidad;
    }
    
    public void setNoInvertir(int noInvertir) {
        this.noInvertir = noInvertir;
    }    
}
