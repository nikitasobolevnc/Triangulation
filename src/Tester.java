import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import structure.DPoint;
import structure.DTriangle;
import utils.Polygons;

public class Tester {

    public static void main(String[] args) {
        double height = 700;
        double width = 700;
        ArrayList<DPoint> points=new ArrayList<DPoint>();

        Random r = new Random();
        for(int i=0; i<10; i++)
        {
            points.add(new DPoint( width*r.nextDouble(), height*r.nextDouble(),10000*r.nextDouble()));
        }

        BowyerWatson bw=new BowyerWatson(width,height,points);
        DTriangle x =new DTriangle(new DPoint(0,0),new DPoint(100,0),new DPoint(10,10));
        JFrame window = new JFrame();
        window.setBounds(0, 0, 510, 525);
        window.getContentPane().add(new Polygons(bw.getPolygons()));
        window.setVisible(true);
    }

}
