package Exe.Ex4.geo;

import java.util.ArrayList;

import Exe.Ex4.gui.Ex4;

/**
 * This class represents a 2D polygon, as in https://en.wikipedia.org/wiki/Polygon
 * This polygon can be assumed to be simple in terms of area and contains.
 * 
 * You should update this class!
 * @author boaz.benmoshe
 *
 */
public class Polygon2D implements GeoShapeable{
	private ArrayList<Point2D> points = new ArrayList<Point2D>();
	
	public Polygon2D(ArrayList<Point2D> arr) {
		this.points.addAll(arr);
	}
	@Override
	public boolean contains(Point2D ot) {
		// TODO Auto-generated method stub	
		
		/*Point2D[] arr = this.getPoints();
		if(arr.length == 4)
			if(this.getPoints()[0].y() == this.getPoints()[1].y()) {
				double maxX = Math.max(arr[0].x(), arr[2].x());
				double maxY = Math.max(arr[0].x(), arr[2].x());
				double minX = Math.min(arr[0].x(), arr[2].x());
				double minY = Math.min(arr[0].x(), arr[2].x());
				if(ot.x() >= minX && ot.x() <= maxX && ot.y() <= maxY && ot.y() >= minY)
					return true;
			}*/
		
		
		
		
		
		
		ArrayList<Segment2D> s = new ArrayList<Segment2D>();
		for(int i = 1 ; i < points.size() ; i++) {
			Segment2D newS = new Segment2D(points.get(i-1) , points.get(i));
			s.add(newS);
			if(i == points.size() -1) {
				newS = new Segment2D(points.get(0) , points.get(i));
				s.add(newS);	
			}
		}
		
		
		
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
		int size = points.size();
		double area = 0;
		for (int i = 0; i < size; i++) {
	    	area += points.get(i).x() * points.get((i + 1) % size).y();
	    	area -= points.get(i).y() * points.get((i + 1) % size).x(); 
	    }
	    
	    return area/2;
		}

	@Override
	public double perimeter() {
		// TODO Auto-generated method stub
		ArrayList<Segment2D> s = new ArrayList<Segment2D>();
		for(int i = 1 ; i < points.size() ; i++) {
			Segment2D newS = new Segment2D(points.get(i-1) , points.get(i));
			s.add(newS);
		}
		double per = 0;
		for(int i = 0 ; i < s.size() ; i++) {
			per += s.get(i).perimeter();
		}

		return per;
	}

	@Override
	public void move(Point2D vec) {
		// TODO Auto-generated method stub
		for(int i = 0; i < this.getPoints().length ; i++)
			this.getPoints()[i].move(vec);
	}

	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		 ArrayList<Point2D> pp = new ArrayList<Point2D>();
		 for(int i = 0 ; i <points.size() ; i++) {
			 Point2D newP = new Point2D(points.get(i));
			 pp.add(newP);
		 }
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
		for(int i = 0; i < this.getPoints().length ; i++)
			this.getPoints()[i].rotate(center, angleDegrees);
	}

	@Override
	public Point2D[] getPoints() {
		// TODO Auto-generated method stub
		Point2D[] arr = new Point2D[points.size()];
		for(int i = 0 ; i < arr.length ; i++)
		{
			arr[i] = points.get(i);
		}
		
		return arr;
		
		
	}
	
}