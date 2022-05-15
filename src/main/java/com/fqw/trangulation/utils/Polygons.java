package com.fqw.trangulation.utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Polygons extends JComponent {
    private ArrayList<DPolygon> polygons = null;

    public Polygons(ArrayList<DPolygon> polygon){
        super();
        polygons = polygon;
    }
    public Polygons(){
        super();
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for(DPolygon polygon : polygons){
            Path2D path = new Path2D.Double();
            path.moveTo(polygon.polygon.get(0).x, polygon.polygon.get(0).y);
            for(int i = 1; i < polygon.polygon.size(); ++i) {
                path.lineTo(polygon.polygon.get(i).x, polygon.polygon.get(i).y);
            }
            path.closePath();
            g2.draw(path);
        }
    }
}
