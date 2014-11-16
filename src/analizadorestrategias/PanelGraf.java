/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package analizadorestrategias;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author USUARIO
 */
class PanelGraf extends JPanel {

   @Override
   public void paintComponent (Graphics g){
       System.out.println("mierda");
    super.paintComponent(g);

    int width = getWidth();
    int height = getHeight();

    int drawCounter = 0; // counters for all the while statements 
    int drawCounter2 = 0;
    int drawCounter3 = 0;
    int drawCounter4 = 0;



    int x1 = 0; // cords change with the while statemetns
    int x2 = 0;
    int y1 = 0;
    int y2 = 0;     
    while (drawCounter <= 15){ // counter 
    y2 = 250;
    g.drawLine(x1, y1, x2, y2);
    x2 = x2 + 15;
    y1 = y1 + 15;
    drawCounter++;  } 


    int u1 = 0;
    int u2 = 0;
    int v1 = 0;
    int v2 = 0;
    while (drawCounter2 <= 15){
    u2 = 250;
    g.drawLine(u1, v1, u2, v2);
    u1 = u1 + 15;
    v2 = v2 + 15;
    drawCounter2++; 
    }

    int a1 = 0;
    int a2 = 0;
    int b1 = 0;
    int b2 = 0;

    while (drawCounter3 <= 15){
    a2 = 250;
    g.drawLine(a1, b1, a2, b2);
    b1 = b1 + 15;
    a2 = a2 - 15;
    drawCounter3++;

    }
 }
        
    
    
}
