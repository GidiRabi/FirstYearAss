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

	public Segment2D (Point2D p1, Point2D p2){
		this.p1 = p1;
		this.p2 = p2;
	}

	@Override
	public boolean contains(Point2D ot) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double area() {
		return 0;
	}

	@Override
	public double perimeter() {
		return (p1.distance(p2)) * 2;
	}

	@Override
	public void move(Point2D vec) {
		p1.move(vec);
		p2.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		return new Segment2D(p1,p2);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point2D[] getPoints() {
		Point2D[] Sarray = {p1,p2};
		return Sarray;
	}

	@Override
	public String toString() {
		return p1.toString() + " ," + p2.toString();

	}

}