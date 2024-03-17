package Exe.Ex4.geo;


/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment2D implements GeoShapeable{

	private Point2D p1;
	private Point2D p2;
	
	public Segment2D(Point2D p1 , Point2D p2) {
		this.p1 = p1;
		this.p2 = p2;	
	}
	@Override
	public boolean contains(Point2D ot) {
		if(p1.y()==p2.y() &&ot.x()<=Math.max(p1.x(), p2.x()) && ot.x()>=Math.min(p1.x(), p2.x()))
			return true;
		if(p1.x()==p2.x() &&ot.y()<=Math.max(p1.y(), p2.y()) && ot.x()>=Math.min(p1.y(), p2.y()))
			return true;
			
		
		double m = (p2.y() - p1.y())-(p2.x() - p1.x());
		double b = p1.y() - m*p1.x();
		
		if(ot.x() <= Math.max(p1.x(), p2.x()) && ot.x() >= Math.min(p1.x(), p2.x()) 
				&& ot.y() <= Math.max(p1.y(), p2.y()) && ot.y() >= Math.min(p1.y(), p2.y()))
			if(ot.y() == m*ot.x() + b) 
				return true;
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double perimeter() {
		// TODO Auto-generated method stub
		return 2*p1.distance(p2);
	}

	@Override
	public void move(Point2D vec) {
		// TODO Auto-generated method stub
		p1.move(vec);
		p2.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		Point2D _p1 = new Point2D(p1);
		Point2D _p2 = new Point2D(p2);
		return new Segment2D(_p1,_p2);
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
		Point2D[] arr = {p1,p2};
		return arr;
	}
	
}