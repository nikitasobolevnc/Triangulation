package com.fqw.trangulation.utils;

import java.util.ArrayList;

import com.fqw.trangulation.structure.DPoint;

public class ConvexHull {
    private static double MINX = 1e10;
    private static double MAXCOS = -1e10;

    public static ArrayList<DPoint> getPolygon(ArrayList<DPoint> vertices){
        MINX = 1e10;
        ArrayList<DPoint> polygons = new ArrayList<DPoint>();
        DPoint start = null;

        for(DPoint point : vertices){
            if(point.x < MINX){
                start = point;
                MINX = point.x;
            }
        }
        polygons.add(start);
        vertices.remove(start);
        DPoint curr = start;
        DPoint vec1 = new DPoint(0,1);
        while(!vertices.isEmpty()){
            MAXCOS = -1e10;
            DPoint candidate = null;
            DPoint temp_vec = null;
            for(DPoint ver : vertices){
                DPoint vec2 = new DPoint(ver.x - curr.x,ver.y - curr.y);
                double sim = Similarity.cosinSimiality(vec1, vec2);
                if(sim > MAXCOS){
                    candidate = ver;
                    temp_vec = vec2;
                    MAXCOS = sim;
                }
            }
            polygons.add(candidate);
            curr = candidate;
            vec1 = temp_vec;
            vertices.remove(candidate);
        }
        return polygons;
    }

/*    public static void main(String[] args) {
        ArrayList<DPoint> vertices = new ArrayList<DPoint>();
        vertices.add(new DPoint(15,7));
        vertices.add(new DPoint(5,5));
        vertices.add(new DPoint(5,10));
        vertices.add(new DPoint(10,5));
        vertices.add(new DPoint(10,10));
        ArrayList<DPoint> polygon = null;
        polygon = getPolygon(vertices);
        System.out.println(polygon);
    }*/

}