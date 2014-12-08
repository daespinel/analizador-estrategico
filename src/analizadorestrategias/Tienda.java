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
public class Tienda {
    
    private String zona;
    private double ventas;
    private double ticket;
    private double gastos;
    
    public Tienda(String zona,double ventas,double gastos){
        setZona(zona);
        setVentas(ventas);
        setGastos(gastos);
    }

    /**
     * @return the zona
     */
    public String getZona() {
        return zona;
    }

    /**
     * @param zona the zona to set
     */
    public void setZona(String zona) {
        this.zona = zona;
    }

    /**
     * @return the ventas
     */
    public double getVentas() {
        return ventas;
    }

    /**
     * @param ventas the ventas to set
     */
    public void setVentas(double ventas) {
        this.ventas = ventas;
    }

    /**
     * @return the ticket
     */
    public double getTicket() {
        return ticket;
    }

    /**
     * @param ticket the ticket to set
     */
    public void setTicket(double ticket) {
        this.ticket = ticket;
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
    
}
