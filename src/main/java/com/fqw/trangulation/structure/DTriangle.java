package com.fqw.trangulation.structure;

public class DTriangle {

    public DPoint[] points = new DPoint[3];
    public DPoint center;
    public double radius;

    public DTriangle(DPoint a, DPoint b, DPoint c){
        points[0] = a;
        points[1] = b;
        points[2] = c;
        center = getCentre();
        radius = getRadius();
    }

    public DPoint getCentre(){
        double a = points[1].x - points[0].x;
        double b = points[1].y - points[0].y;
        double c = points[2].x - points[0].x;
        double d = points[2].y - points[0].y;

        double e = a * (points[0].x+ points[1].x) + b * (points[0].y+ points[1].y);
        double f = c * (points[0].x+ points[2].x) + d * (points[0].y+ points[2].y);
        double g = 2.0 * (a * (points[2].y- points[1].y) - b * (points[2].x- points[1].x));

        if(g == 0){
            return null;
        }

        double px = (d*e - b*f)/g;
        double py = (a*f - c*e)/g;

        return new DPoint(px, py);
    }

    public double getRadius(){
        double x = points[0].x - center.x;
        double y = points[0].y - center.y;

        return Math.sqrt(x*x + y*y);
    }

    public boolean inCircum(DPoint point){
        double x = point.x - center.x;
        double y = point.y - center.y;

        return Math.sqrt(x*x + y*y) < radius;
    }

    public boolean hasPoint(DPoint point){
        boolean flag = false;
        for(int i = 0; i < 3; i++){
            if(points[i] == point){
                flag = true;
            }
        }
        return flag;
    }

    public String toString(){
        return "[" + points[0] + "," + points[1] + "," + points[2] + "]\n";
    }
}
