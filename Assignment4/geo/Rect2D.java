package Exe.Ex4.geo;

import java.util.ArrayList;

import org.junit.jupiter.params.provider.EnumSource.Mode;

/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect2D implements GeoShapeable {

	private Point2D p1;
	private Point2D p2;
	private Point2D p3;
	private Point2D p4;

	
	public Rect2D(Point2D p1 , Point2D p2) {
		this.p1 = p1;
		this.p2 = new Point2D(p2.x(), p1.y());
		this.p3 = p2;
		this.p4 = new Point2D(p1.x(), p2.y());

	}
	@Override
	public boolean contains(Point2D ot) {
		// TODO Auto-generated method stub
		/*Point2D[] arr = this.getPoints();
		if(this.getPoints()[0].y() == this.getPoints()[1].y()) {
			double maxX = Math.max(arr[0].x(), arr[2].x());
			double maxY = Math.max(arr[0].x(), arr[2].x());
			double minX = Math.min(arr[0].x(), arr[2].x());
			double minY = Math.min(arr[0].x(), arr[2].x());
			if(ot.x() >= minX && ot.x() <= maxX && ot.y() <= maxY && ot.y() >= minY)
				return true;
		}*/

		ArrayList<Segment2D> s = new ArrayList<Segment2D>();
		s.add(new Segment2D(this.getPoints()[0] , this.getPoints()[1]));
		s.add(new Segment2D(this.getPoints()[0] , this.getPoints()[3]));
		s.add(new Segment2D(this.getPoints()[2] , this.getPoints()[1]));
		s.add(new Segment2D(this.getPoints()[2] , this.getPoints()[3]));

		// y = ot.y();
	    int count = 0;
		int size = s.size();
	    for(int i = 0 ; i < size ; i++) {
	    	Point2D[] ps = s.get(i).getPoints();
	    	
	    	if(s.get(i).getPoints()[0].x() == s.get(i).getPoints()[1].x()) {
	    		double upY = Math.max(s.get(i).getPoints()[0].y(), s.get(i).getPoints()[1].y());
			    double downY = Math.min(s.get(i).getPoints()[0].y(), s.get(i).getPoints()[1].y());
			    double X = Math.max(s.get(i).getPoints()[0].x(), s.get(i).getPoints()[1].x());
			
			    if(ot.y() <= upY && ot.y() >= downY && ot.x() >= X) {
	    			count++;
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
		// TODO Auto-generated method stub
		double length = p1.distance(p2);
		double height = p1.distance(p4);
		double area = height * length;
		return area;
	}

	@Override
	public double perimeter() {
		double length = p1.distance(p2);
		double height = p1.distance(p4);
		double perimeter = length*2 + height*2;
		return perimeter;
	}

	@Override
	public void move(Point2D vec) {
		// TODO Auto-generated method stub
		p1.move(vec);
		p2.move(vec);
		p3.move(vec);
		p4.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		ArrayList<Point2D> pp = new ArrayList<Point2D>();
		Point2D _p1 = new Point2D(p1);
		Point2D _p2 = new Point2D(p2);
		Point2D _p3 = new Point2D(p3);
		Point2D _p4 = new Point2D(p4);
		pp.add(_p1);
		pp.add(_p2);
		pp.add(_p3);
		pp.add(_p4);
		
		return new Polygon2D(pp);
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
		ArrayList<Point2D> points = new ArrayList<Point2D>();
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);
		Polygon2D p = new Polygon2D(points);
		for(int i = 0; i < this.getPoints().length ; i++)
			p.rotate(center, angleDegrees);
	}
	
	

	@Override
	public Point2D[] getPoints() {
		// TODO Auto-generated method stub
		Point2D[] arr = {p1,p2,p3,p4};
		return arr;
	}

}