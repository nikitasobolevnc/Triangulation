package com.fqw.trangulation.structure;

public class DEdge implements Comparable<DEdge> {
    public DPoint[] points = new DPoint[2];
    public double weight;

    public DEdge(DPoint a, DPoint b){
        points[0] = a;
        points[1] = b;
    }

    public double getWeight(){
        weight = Math.abs(points[0].value - points[1].value);
        return weight;
    }

    public int hashCode() {
        int hash = points[0].hashCode();
        hash = hash * points[1].hashCode();
        return hash;
    }

    public boolean equals(Object o){
        return this.hashCode() == o.hashCode();
    }

    @Override
    public int compareTo(DEdge arg0) {
        int compare = this.weight > arg0.weight ? 1 : this.weight < arg0.weight ? -1 : 0;
        return compare;
    }

    public String toString(){
        return "[" + points[0] + "," + points[1] + "]\n";
    }
}
