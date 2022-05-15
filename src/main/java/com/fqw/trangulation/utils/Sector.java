package com.fqw.trangulation.utils;

import com.fqw.trangulation.structure.DPoint;
import com.fqw.trangulation.structure.DTriangle;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Sector {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    private ArrayList<DPolygon> polygons;
    private final int sectorID;

    private double startX;
    private double startY;
    private double endX;
    private double endY;

    public Sector(double startX, double startY, double endX, double endY){
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;

        sectorID = COUNTER.getAndIncrement();
        polygons = new ArrayList<DPolygon>();
    }

    public int getSectorID(){
        return sectorID;
    }

    public ArrayList<DPolygon> getPolygons(){
        return polygons;
    }

    public void addPolygon(DPolygon polygon){
        this.polygons.add(polygon);
    }

    public static double calculateSectorSize(double size){
        do{
            size = size / 3;
        }
        while (size > 200);

        return size;
    }

    public boolean isPointInSector(DPoint point){
        return point.x >= startX && point.x < endX && point.y >= startY && point.y < endY;
    }

    public boolean isPolygonInSector(DTriangle triangle){
        return isPointInSector(triangle.points[0]) || isPointInSector(triangle.points[1]) || isPointInSector(triangle.points[2]);
    }

}
