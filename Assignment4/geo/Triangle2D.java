package Exe.Ex4.geo;

import java.awt.*;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */

public class Triangle2D implements GeoShapeable {

	private Point2D p1;
	private Point2D p2;
	private Point2D p3;

	public Triangle2D(Point2D p1, Point2D p2, Point2D p3) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}

	@Override
	public String toString() {
		return p1.toString() + " ," + p2.toString() + " ," + p3.toString();
	}

	@Override
	public boolean contains(Point2D ot) {

		double areaABC = 0.5 * (-p2.y() * p3.x() + p1.y() * (-p2.x() + p3.x()) + p1.x() * (p2.y() - p3.y()) + p2.x() * p3.y());

		// Compute the barycentric coordinates of the point P
		double a = 0.5 * ((-p2.y() * p3.x()) + (p2.y() - p3.y()) * ot.x() + (p3.x() - p2.x()) * ot.y()) / areaABC;
		double b = 0.5 * ((p1.y() * p3.x()) - p1.x() * p3.y() + (p3.y() - p1.y()) * ot.x() + (p1.x() - p3.x()) * ot.y()) / areaABC;
		double c = 1 - a - b;

		// Check that the barycentric coordinates are all between 0 and 1
		if ((a >= 0) && (a <= 1) && (b >= 0) && (b <= 1) && (c >= 0) && (c <= 1)) {
			return true;
		}
		return false;
	}

	@Override
	public double area() {
		double area = 0.5 * Math.abs(p1.x() * (p2.y() - p3.y()) + p2.x() * (p3.y() - p1.y()) + p3.x() * (p1.y() - p2.y()));
		return area;
	}

	@Override
	public double perimeter() {
		double perimeter = p1.distance(p2) + p2.distance(p3) + p3.distance(p1);
		return perimeter;
	}

	@Override
	public void move(Point2D vec) {
		p1.move(vec);
		p2.move(vec);
		p3.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		return new Triangle2D(p1, p2, p3);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		this.p1.scale(center,ratio);
		this.p2.scale(center,ratio);
		this.p3.scale(center,ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		for (int i = 0; i < getPoints().length; i++) {
			this.getPoints()[i].rotate(this.getPoints()[i],angleDegrees);
		}
	}

	@Override
	public Point2D[] getPoints() {
		Point2D[] TriPoints = new Point2D[]{this.p1, this.p2, this.p3};
		return TriPoints;
	}




	public static void main(String[] args) {
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(0,8);
		Point2D p3 = new Point2D(7,4);
		Point2D p4 = new Point2D(8,0);

		Triangle2D triangletest = new Triangle2D(p1,p2,p3);
		double triArea = triangletest.area();
		System.out.println(triArea);
		System.out.println(triangletest.perimeter());
		System.out.println(triangletest.contains(p4));


	}
	
}
