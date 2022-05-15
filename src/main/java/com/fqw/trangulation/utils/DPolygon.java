package com.fqw.trangulation.utils;

import java.util.ArrayList;

import com.fqw.trangulation.structure.DPoint;

public class DPolygon {
    public ArrayList<DPoint> polygon;

    public DPolygon(ArrayList<DPoint> set){
        polygon = ConvexHull.getPolygon(set);
    }

    public String toString(){
        return polygon.toString();
    }
}