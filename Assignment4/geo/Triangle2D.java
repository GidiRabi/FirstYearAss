package Exe.Ex4.geo;

import java.util.ArrayList;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle2D implements GeoShapeable{

	private Point2D p1;
	private Point2D p2;
	private Point2D p3;
	
	/*
	 * creates the 3 points of the triangle
	 */
	public Triangle2D(Point2D p1 ,Point2D p2,Point2D p3) {
		
		this.p1 = p1;
		this.p2 = p2; 
		this.p3 = p3;
	}
	
	/*
	 * 
	 * if we divide the triangle to 3 smaller triangles using the given point and add the 3 areas
	 * if they're equal to the triangle area is means its inside the triangle
	 * else its not 
	 */
	@Override
	public boolean contains(Point2D ot) {		
		 
		
		ArrayList<Segment2D> s = new ArrayList<Segment2D>();
		for(int i = 1 ; i < this.getPoints().length ; i++) {
			Segment2D newS = new Segment2D(this.getPoints()[i-1] , this.getPoints()[i]);
			s.add(newS);
			if(i == this.getPoints().length -1) {
				newS = new Segment2D(this.getPoints()[0] , this.getPoints()[i]);
				s.add(newS);	
			}
		}
		
		// y = ot.y();
	    int count = 0;
		int size = s.size();
	    for(int i = 0 ; i < size ; i++) {
	    	if(s.get(i).getPoints()[0].x() == s.get(i).getPoints()[1].x()) {
	    		double upY = Math.max(s.get(i).getPoints()[0].y(), s.get(i).getPoints()[1].y());
			    double downY = Math.min(s.get(i).getPoints()[0].y(), s.get(i).getPoints()[1].y());
			    double X = Math.max(s.get(i).getPoints()[0].x(), s.get(i).getPoints()[1].x());
			
			    if(ot.y() <= upY && ot.y() >= downY && ot.x() >= X) {
	    			count++;
				    System.out.println("x=x");

			    }
	    		continue;
	    	}
	    	if(s.get(i).getPoints()[0].y() == s.get(i).getPoints()[1].y()) {
	    		double Y = Math.max(s.get(i).getPoints()[0].y(), s.get(i).getPoints()[1].y());
			    double upX = Math.max(s.get(i).getPoints()[0].x(), s.get(i).getPoints()[1].x());
			    double downX = Math.min(s.get(i).getPoints()[0].x(), s.get(i).getPoints()[1].x());
			
			    if(ot.x() <= upX && ot.x() >= downX && ot.y() == Y) {
	    			count++;
				    System.out.println("y=y");
	
			    }
	    		continue;
	    	}
	    	
	    	Point2D[] ps = s.get(i).getPoints();
	    	
		    double m = (ps[1].y() - ps[0].y()) / (ps[1].x() - ps[0].x());
		    double b = ps[0].y() - m * ps[0].x();    
		    
		    //box
		    double maxX = Math.max(ps[0].x(), ps[1].x());
		    double minX = Math.min(ps[0].x(), ps[1].x());
		    double maxX1 = ot.x();
		    double minX1 = 0;
		    
		    double min = Math.max(minX,minX1);
		    double max = Math.min(maxX,maxX1);

		    
		    //(ot - b)/m == x -> box		    
		    if((ot.y() -b)/m <= max && (ot.y()-b)/m >= min )
		    		count++;
	    }
	    
	    return count%2!=0;
		  
		  
	}

	@Override
	public double area() {
		double a = Math.abs(0.5*( (p1.x()*(p3.y() - p2.y())) + p2.x()*(p1.y() - p3.y() + p3.x()*(p2.y() - p1.y())) ));
		// TODO Auto-generated method stub
		return a;
	}

	@Override
	public double perimeter() {
		double p = p1.distance(p2) + p1.distance(p3) + p2.distance(p3);
		// TODO Auto-generated method stub
		return p;
	}

	@Override
	public void move(Point2D vec) {
		p1.move(vec);
		p2.move(vec);
		p3.move(vec);
		// TODO Auto-generated method stub
		
	}

	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		Point2D _p1 = new Point2D(p1);
		Point2D _p2 = new Point2D(p2);
		Point2D _p3 = new Point2D(p3);

		return new Triangle2D(_p1,_p2,_p3);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		// TODO Auto-generated method stub
		for(int i = 0; i < this.getPoints().length ; i++)
			this.getPoints()[i].scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		// TODO Auto-generated method stub
		for(int i = 0; i < this.getPoints().length ; i++)
			this.getPoints()[i].rotate(center, angleDegrees);
	}

	@Override
	public Point2D[] getPoints() {
		Point2D[] arr = {p1,p2,p3};
		// TODO Auto-generated method stub
		return arr;
	}
	
}