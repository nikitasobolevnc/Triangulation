package com.fqw.trangulation;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.xml.ws.Service;

import com.fqw.trangulation.structure.DPoint;
import com.fqw.trangulation.structure.DTriangle;
import com.fqw.trangulation.utils.DPolygon;
import com.fqw.trangulation.utils.Polygons;
import com.fqw.trangulation.utils.Sector;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Program {
//    double height = 1200;
//    double width = 2100;
    private static double height = 500;
    private static double width = 500;

    public void writeToJson(ArrayList list, String fileName){
        File file = new File("resources/json/" + fileName);
        try {
            BufferedWriter buffWriter = new BufferedWriter(new FileWriter(file, true));
            Gson gson = new Gson();

            String json = gson.toJson(list);

            buffWriter.append(json);
            buffWriter.newLine();
            buffWriter.close();
        } catch (IOException e) {
            System.out.println("Данные не могут быть записаны в файл, по причине: " + e);
        }
    }

    public ArrayList readFromJson(ArrayList list, String fileName, Type type) {
        File file = new File("resources/json/", fileName);
        try {

            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(file));
            list = gson.fromJson(reader, type);
        } catch (IOException e) {
            System.out.println("Данные не могут быть получены из файла, по причине: " + e);
        }

        return list;
    }

    public static void main(String[] args) {
        ArrayList<DPoint> points = new ArrayList<DPoint>();

        Random r = new Random();
        for(int i = 0; i < 30; i++)
        {
            points.add(new DPoint( width*r.nextDouble(), height*r.nextDouble(),10000*r.nextDouble()));
            //System.out.println("Point №" + i);
        }

        BowyerWatson bw = new BowyerWatson(width, height, points);
        DTriangle x = new DTriangle(new DPoint(0,0),
                                    new DPoint(100,0),
                                    new DPoint(10,10));

        int currnetID = 90;
        System.out.println(bw.getColumnCounts() + " " + bw.getRowCounts());
        ArrayList<Sector> sectors = bw.getPolygons();
        //ArrayList<DPolygon> polygons = bw.getPolygons();

        JFrame window = new JFrame();
        window.setBounds(0, 0, (int) width + 100, (int) height + 100);
        ArrayList<DPolygon> polygons = new ArrayList<DPolygon>();
        for (Sector sector : sectors) {
            polygons.addAll(sector.getPolygons());
        }

//        polygons.addAll(sectors.get(currnetID - bw.getColumnCounts() - 1).getPolygons());
//        polygons.addAll(sectors.get(currnetID - bw.getColumnCounts()).getPolygons());
//        polygons.addAll(sectors.get(currnetID - bw.getColumnCounts() + 1).getPolygons());
//        polygons.addAll(sectors.get(currnetID - 1).getPolygons());
//        polygons.addAll(sectors.get(currnetID).getPolygons());
//        polygons.addAll(sectors.get(currnetID + 1).getPolygons());
//        polygons.addAll(sectors.get(currnetID + bw.getColumnCounts() - 1).getPolygons());
//        polygons.addAll(sectors.get(currnetID + bw.getColumnCounts()).getPolygons());
//        polygons.addAll(sectors.get(currnetID + bw.getColumnCounts() + 1).getPolygons());

        window.getContentPane().add(new Polygons(polygons));
        window.setVisible(true);
    }

}
