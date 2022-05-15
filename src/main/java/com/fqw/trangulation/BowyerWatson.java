package com.fqw.trangulation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import com.fqw.trangulation.structure.DEdge;
import com.fqw.trangulation.structure.DPoint;
import com.fqw.trangulation.structure.DTriangle;
import com.fqw.trangulation.utils.DPolygon;
import com.fqw.trangulation.utils.Sector;

public class BowyerWatson {
    public ArrayList<DTriangle> triangles;
    public ArrayList<DPoint> points;
    public ArrayList<DPoint> initpoints;
    public ArrayList<Sector> sectors;

    private int columnCounts;
    private int rowCounts;

    public BowyerWatson(double width, double height, ArrayList<DPoint> dPoints){
        triangles = new ArrayList<DTriangle>();
        initial(width, height);
        for(DPoint point : dPoints){
            run(point);
        }
    }

    public int getColumnCounts() {
        return columnCounts;
    }

    public int getRowCounts() {
        return rowCounts;
    }

    public void initial(double width, double height){
        initpoints = new ArrayList<DPoint>();
        createSectors(width, height);
        double startX = 0;
        double startY = 0;
        DPoint a = new DPoint(startX, startY);
        DPoint b = new DPoint(startX, height);
        DPoint c = new DPoint(width, height);
        DPoint d = new DPoint(width, startY);
        DTriangle tri1 = new DTriangle(a, b, c);
        DTriangle tri2 = new DTriangle(a, c, d);
        triangles.add(tri1);
        triangles.add(tri2);
        initpoints.add(a);
        initpoints.add(b);
        initpoints.add(c);
        initpoints.add(d);
    }

    private void createSectors(double width, double height){
        sectors = new ArrayList<Sector>();
        double sectorWidth = Sector.calculateSectorSize(width);
        double sectorHeight = Sector.calculateSectorSize(height);
        columnCounts = (int) (width/sectorWidth);
        rowCounts = (int) (height/sectorHeight);

        double currentX = 0;
        double currentY = 0;
        for(int i = 0; i < rowCounts; i++){
            for(int j = 0; j < columnCounts; j++){
                if(!(i == (rowCounts - 1) || j == (columnCounts -1)))
                    sectors.add(new Sector(currentX, currentY, currentX + sectorWidth, currentY + sectorHeight));
                else
                    sectors.add(new Sector(currentX, currentY, currentX + sectorWidth + 1, currentY + sectorHeight + 1));
                currentX += sectorWidth;
                System.out.print(j + i*columnCounts + " ");
            }
            System.out.println();
            currentY += sectorHeight;
            currentX = 0;
        }

    }

    public void run(DPoint point){
        ArrayList<DTriangle> badTriangles = new ArrayList<DTriangle>();
        HashSet<DEdge> edges = new HashSet<DEdge>();
        HashSet<DEdge> edgesForRemove = new HashSet<DEdge>();

        for(int i = triangles.size()-1; i >= 0; i--){
            DTriangle triangle = triangles.get(i);
            if(triangle.inCircum(point)){
                badTriangles.add(triangle);
                triangles.remove(i);
            }
        }
        for(DTriangle triangle : badTriangles){
            DEdge e1 = new DEdge(triangle.points[0], triangle.points[1]);
            DEdge e2 = new DEdge(triangle.points[1], triangle.points[2]);
            DEdge e3 = new DEdge(triangle.points[0], triangle.points[2]);
            if(!edges.add(e1)){edgesForRemove.add(e1);};
            if(!edges.add(e2)){edgesForRemove.add(e2);};
            if(!edges.add(e3)){edgesForRemove.add(e3);};
        }

        for(DEdge edge : edgesForRemove){
            edges.remove(edge);
        }

        for(DEdge edge : edges){
            DTriangle triangle = new DTriangle(edge.points[0], edge.points[1], point);
            triangles.add(triangle);
        }
    }

    public String toString(){
        String output = "";
        for(DTriangle triangle : triangles){
            output += triangle.toString();
        }
        return output;
    }

    public void reMoveBoundary(HashSet<DEdge> edges){
        Collection<DEdge> removeCandidates = new ArrayList<DEdge>();
        for(DEdge edge : edges){
            boolean flag = false;
            if(edge.points[0] == initpoints.get(0) || edge.points[0] == initpoints.get(1) || edge.points[0] == initpoints.get(2) || edge.points[0] == initpoints.get(3)){
                flag=true;
            }
            if(edge.points[1] == initpoints.get(0) || edge.points[1] == initpoints.get(1) || edge.points[1] == initpoints.get(2) || edge.points[1] == initpoints.get(3)){
                flag=true;
            }
            if(flag == true){
                removeCandidates.add(edge);
            }
        }
        edges.removeAll(removeCandidates);
    }

    //public ArrayList<DPolygon> getPolygons(){
    public ArrayList<Sector> getPolygons(){
        ArrayList<DPolygon> polygons = new ArrayList<DPolygon>();
        for(DTriangle triangle : triangles){
            ArrayList<DPoint> set = new ArrayList<DPoint>();
            set.add(triangle.points[0]);
            set.add(triangle.points[1]);
            set.add(triangle.points[2]);
            DPolygon polygon = new DPolygon(set);

            for(Sector sector : sectors){
                if(sector.isPolygonInSector(triangle)){
                    sector.addPolygon(polygon);
                    break;
                }
            }

            //polygons.add(polygon);
        }
        return sectors;
        //return polygons;
    }

    public HashSet<DEdge> getPrunEdges(){
        HashSet<DEdge> edges = new HashSet<DEdge>();
        for(DTriangle triangle : triangles){
            DEdge e1 = new DEdge(triangle.points[0], triangle.points[1]);
            DEdge e2 = new DEdge(triangle.points[1], triangle.points[2]);
            DEdge e3 = new DEdge(triangle.points[0], triangle.points[2]);
            edges.add(e1);
            edges.add(e2);
            edges.add(e3);
        }
        reMoveBoundary(edges);
        return edges;
    }
}