package com.fqw.trangulation.structure;

public class DPoint {

    public double x;
    public double y;
    public double value;

    public DPoint(double x, double y){
        this.x = x;
        this.y = y;
    }
    public DPoint(double x, double y, double value){
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public String toString(){
        return "(" + x + "," + y + ") ";
    }
}
