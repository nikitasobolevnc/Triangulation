package utils;

import java.util.ArrayList;

import structure.DPoint;

public class DPolygon {

    public ArrayList<DPoint> polygon;

    public DPolygon(ArrayList<DPoint> set){
        polygon = ConvexHull.getPolygon(set);
    }
    public String toString(){
        return polygon.toString();
    }
}