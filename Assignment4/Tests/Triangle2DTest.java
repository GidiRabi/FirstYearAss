/**
 * 
 */
package Exe.Ex4.Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Triangle2D;

/**
 * @author Gidi
 *
 */
class Triangle2DTest {
	private Triangle2D _triangle;
	private Point2D _p1;
	private Point2D _p2;
	private Point2D _p3;

	/**
	 * @throws java.lang.Exception
	 * SET up a new triangle before each test so we can change it and save original triangle
	 */
	@BeforeEach
	void setUp() throws Exception {
		_p1 = new Point2D(2,2);
		_p2 = new Point2D(4,2);
		_p3 = new Point2D(3,4);
		_triangle = new Triangle2D(_p1,_p2,_p3);
		
	}
	/**
	 * Test method for {@link Exe.Ex4.geo.Triangle2D#contains(Exe.Ex4.geo.Point2D)}.
		 * this test checks if a triangle has certain points
	 */
	@Test
	final void testContains() {
		Point2D p1 = new Point2D(3,2);
		Point2D p2 = new Point2D(3,3);
		Point2D p3 = new Point2D(7,3);
		Point2D p4 = new Point2D(2,6);
		
		assertTrue(_triangle.contains(p1));
		assertTrue(_triangle.contains(p2));
		assertFalse(_triangle.contains(p3));
		assertFalse(_triangle.contains(p4));
	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Triangle2D#area()}.
		 * this test checks if we get the right area of the triangle
		 * height*base/2
	 */
	@Test
	final void testArea() {
		double base = _p1.distance(_p2);
		double height = _p3.distance(new Point2D(((_p1.x()+_p2.x()) / 2) ,(( _p1.y() + _p2.y()) / 2)));
		double area = (base + height) / 2;
		assertEquals(_triangle.area(),area);
	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Triangle2D#perimeter()}.
	 	 * this test checks the perimeter of a triangle
	 */
	@Test
	final void testPerimeter() {
		double perimeter = _p1.distance(_p2) + _p2.distance(_p3) + _p3.distance(_p1);
		assertEquals(_triangle.perimeter(),perimeter);
	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Triangle2D#move(Exe.Ex4.geo.Point2D)}.
		 * this test checks a triangle after being moved by a "vector"
	 */
	@Test
	final void testMove() {
		Point2D vec = new Point2D(8,8);
		ArrayList<Point2D> points = new ArrayList<Point2D>();
		ArrayList<Point2D> moved_points = new ArrayList<Point2D>();
		for(int i=0; i<_triangle.getPoints().length; i++) {
			points.add(_triangle.getPoints()[i]);
		}
		_triangle.move(vec);
		for(int i=0; i<_triangle.getPoints().length; i++) {
			moved_points.add(_triangle.getPoints()[i]);
		}
		for(int i=0;i<_triangle.getPoints().length;i++) {
			assertEquals(points.get(i), moved_points.get(i));
		}
	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Triangle2D#copy()}.
	 * this test checks the triangle after it being scaled
	 */
	@Test
	final void testCopy() {
		GeoShapeable copy = null;
		copy = _triangle;
		
		for(int i=0; i<_triangle.getPoints().length;i++) {
			assertEquals(copy.getPoints()[i],_triangle.getPoints()[i]);
		}
	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Circle2D#scale(Exe.Ex4.geo.Point2D, double)}.
	 * testing to see if the scale function scales to 110% the circle by checking if a point is contained
	 * in the new circle after being scaled.
	 */
	@Test
	final void testScale90() {
		//scaling using a point from the top right of the _triangle and a 110 degree
	
		Point2D click = new Point2D(_triangle.getPoints()[2].x() +0.1,_triangle.getPoints()[2].y() +0.1);
		Point2D Ncontained = new Point2D(_triangle.getPoints()[2].x(),_triangle.getPoints()[2].y());
		Point2D Ycontained = new Point2D(_triangle.getPoints()[0].x()*1.5,_triangle.getPoints()[0].y()*1.5);

		_triangle.scale(click, 0.9);
		assertTrue(_triangle.contains(Ycontained));
		assertFalse(_triangle.contains(Ncontained)); 

	}
	/**
	 * Test method for {@link Exe.Ex4.geo.Circle2D#scale(Exe.Ex4.geo.Point2D, double)}.
	 * testing to see if the scale function scales to 90% the _triangle by checking if a point is contained
	 * in the new _triangle after being scaled.
	 */
	@Test
	final void testScale110() {
		Point2D click = new Point2D(_triangle.getPoints()[2].x() +0.1,_triangle.getPoints()[2].y() +0.1);
		Point2D Ncontained = new Point2D(_triangle.getPoints()[0].x()*0.8,_triangle.getPoints()[0].y()*0.8);
		Point2D Ycontained = new Point2D(_triangle.getPoints()[0].x()*1.1,_triangle.getPoints()[0].y()*0.95);

		_triangle.scale(click, 1.1);
		assertTrue(_triangle.contains(Ycontained));
		assertFalse(_triangle.contains(Ncontained)); 

	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Triangle2D#getPoints()}.
	 *this test checks if we are getting all the points of the triangle
	 *we created a temporary array and inserted manually the points of
	 *the triangle and compared them to the points after we used getPoints 
	 *function
	 */
	@Test
	final void testGetPoints() {
		
		ArrayList<Point2D> points = new ArrayList<Point2D>();
		points.add(_p1);
		points.add(_p2);
		points.add(_p3);

		assertTrue(_p1.equals(_p1));
		for(int i=0;i<points.size();i++) {
			assertTrue(_triangle.getPoints()[i].equals(points.get(i)));
		}
		
	}

}
