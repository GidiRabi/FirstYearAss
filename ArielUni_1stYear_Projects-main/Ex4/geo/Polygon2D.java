package Exe.Ex4.geo;

import java.util.ArrayList;

/**
 * This class represents a 2D polygon, as in https://en.wikipedia.org/wiki/Polygon
 * This polygon can be assumed to be simple in terms of area and contains.
 * 
 * You should update this class!
 * @author boaz.benmoshe
 *
 */
public class Polygon2D implements GeoShapeable{
	ArrayList<Point2D> points;

	public Polygon2D(ArrayList<Point2D> points) {
		this.points = points;
	}

	@Override
	public boolean contains(Point2D ot) {
		// Check if the point is inside the polygon using the ray casting algorithm
		int i, j;
		boolean result = false;
		for (i = 0, j = points.size() - 1; i < points.size(); j = i++) {
			if ((points.get(i).y() > ot.y()) != (points.get(j).y() > ot.y()) &&
					(ot.x() < (points.get(j).x() - points.get(i).x()) * (ot.y() - points.get(i).y()) / (points.get(j).y() - points.get(i).y()) + points.get(i).x())) {
				result = !result;
			}
		}
		return result;
	}

	@Override
	public double area() {
		// Calculate the area of the polygon using the Shoelace formula
		double area = 0;
		int i, j;
		for (i = 0, j = points.size() - 1; i < points.size(); j = i++) {
			area += (points.get(i).x() + points.get(j).x()) * (points.get(i).y() - points.get(j).y());
		}
		return Math.abs(area / 2);
	}

	@Override
	public double perimeter() {
		// Calculate the perimeter of the polygon by adding the distances between consecutive points
		double perimeter = 0;
		for (int i = 0; i < points.size() - 1; i++) {
			perimeter += points.get(i).distance(points.get(i + 1));
		}
		perimeter += points.get(points.size() - 1).distance(points.get(0));
		return perimeter;
	}

	@Override
	public void move(Point2D vec) {
		// Move each point of the polygon by the given vector
		for (Point2D point : points) {
			point.move(vec);
		}
	}

	@Override
	public GeoShapeable copy() {
		// Create a copy of the polygon by creating new Point2D objects for each point in the polygon
		ArrayList<Point2D> newPoints = new ArrayList<>();
		for (Point2D point : points) {
			newPoints.add(new Point2D(point.x(), point.y()));
		}
		return new Polygon2D(newPoints);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		for (Point2D point : points) {
			point.scale(center, ratio);
		}
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point2D[] getPoints() {
		// Create an array of Point2D objects and fill it with the points in the ArrayList
		Point2D[] pointsArray = new Point2D[points.size()];
		for (int i = 0; i < points.size(); i++) {
			pointsArray[i] = points.get(i);
		}
		return pointsArray;
	}
}
