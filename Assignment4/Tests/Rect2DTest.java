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
import Exe.Ex4.geo.Rect2D;

/**
 * @author Gidi
 *
 */
class Rect2DTest {
	private Rect2D _rect;
	private Point2D _p1;
	private Point2D _p2;
	/**
	 * @throws java.lang.Exception
	 * creates a new recttangle for each test
	 */
	@BeforeEach
	void setUp() throws Exception {
		_p1 = new Point2D(5,5);
		_p2 = new Point2D(10,10);
		_rect = new Rect2D(_p1,_p2);
		
	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Rect2D#contains(Exe.Ex4.geo.Point2D)}.
	 * this test checks if a certain point is in a rectangle
	 */
	@Test
	final void testContains() {
		
		System.out.println(_rect.getPoints()[0]);
		System.out.println(_rect.getPoints()[1]);
		System.out.println(_rect.getPoints()[2]);
		System.out.println(_rect.getPoints()[3]);
		System.out.println(new Point2D(_rect.getPoints()[0].x() * 1.5,_rect.getPoints()[0].y()*1.5));
		
		assertTrue(_rect.contains(new Point2D(_rect.getPoints()[0].x() * 1.5,_rect.getPoints()[0].y()*1.5)));
		assertTrue(_rect.contains(new Point2D(_rect.getPoints()[0].x() * 1.2,_rect.getPoints()[0].y()*1.7)));
		assertFalse(_rect.contains(new Point2D(_rect.getPoints()[0].x() * 1.5,_rect.getPoints()[0].y()*3)));
		assertFalse(_rect.contains(new Point2D(_rect.getPoints()[0].x() * 3,_rect.getPoints()[0].y()*1.5)));
	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Rect2D#area()}.
		 * this test checks the area of a rectangle
	 */
	@Test
	final void testArea() {
		
		double area = 25; //5*5 height * length
		assertEquals(_rect.area() , area);
	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Rect2D#perimeter()}.
	 * this test checks the perimeter of a rectangle
	 */
	@Test
	final void testPerimeter() {
		double perimeter = (2*(_p1.distance(_rect.getPoints()[1])) + (2*(_p2.distance(_rect.getPoints()[1]))));
		double p = _rect.perimeter();
		assertEquals(p, perimeter);
	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Rect2D#move(Exe.Ex4.geo.Point2D)}.
	 	 * this test checks a rectangle after it moved by a "vector" (point)
	 */
	@Test
	final void testMove() {
		Point2D vec = new Point2D(8,8);
		ArrayList<Point2D> points = new ArrayList<Point2D>();
		ArrayList<Point2D> moved_points = new ArrayList<Point2D>();
		for(int i=0; i<_rect.getPoints().length; i++) {
			points.add(_rect.getPoints()[i]);
		}
		_rect.move(vec);
		for(int i=0; i<_rect.getPoints().length; i++) {
			moved_points.add(_rect.getPoints()[i]);
		}
		for(int i=0;i<_rect.getPoints().length;i++) {
			assertEquals(points.get(i), moved_points.get(i));
		}
	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Rect2D#copy()}.
	 * this test checks the interface copy of a rectangle and if it copys the value of the points from the shape
	 */
	@Test
	final void testCopy() {
		
		GeoShapeable copy = null;
		copy = _rect.copy();
		
		for(int i=0;i<copy.getPoints().length;i++) {
			assertEquals(copy.getPoints()[i], _rect.getPoints()[i]);
		}
	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Rect2D#scale(Exe.Ex4.geo.Point2D, double)}.
	 	 * this test checks a rectangle after being scaled to 110%
	 */
	@Test
	final void testScale110() {
		Point2D click = new Point2D(_rect.getPoints()[3].x() +0.1,_rect.getPoints()[3].y() +0.1);
		Point2D Ncontained = new Point2D(_rect.getPoints()[0].x() * 0.8,_rect.getPoints()[0].y()*0.8);

		_rect.scale(click, 1.1);
		assertFalse(_rect.contains(Ncontained));		
	}
	/**
	 * Test method for {@link Exe.Ex4.geo.Rect2D#scale(Exe.Ex4.geo.Point2D, double)}.
	 * this test checks a rectangle after being scaled to 90%

	 */
	@Test
	final void testScale90() {
		Point2D click = new Point2D(_rect.getPoints()[1].x() +1,_rect.getPoints()[1].y() +1);
		Point2D Ncontained = new Point2D(_rect.getPoints()[2].x(),_rect.getPoints()[2].y());
		Point2D Ycontained = new Point2D(_rect.getPoints()[0].x()*1.5,_rect.getPoints()[0].y()*1.5);

		_rect.scale(click, 0.9);
		assertTrue(_rect.contains(Ycontained));
		assertFalse(_rect.contains(Ncontained));
	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Rect2D#getPoints()}.
		 * this test checks if we actually getting the rectangle's points
	 */
	@Test
	final void testGetPoints() {
		
		ArrayList<Point2D> points = new ArrayList<Point2D>();
		points.add(_p1);
		points.add(new Point2D(_p2.x(),_p1.y()));
		points.add(_p2);
		points.add(new Point2D(_p1.x(),_p2.y()));

		
		assertTrue(_p1.equals(_p1));
		for(int i=0;i<points.size();i++) {
			assertTrue(_rect.getPoints()[i].x() == points.get(i).x());
			assertTrue(_rect.getPoints()[i].y() == points.get(i).y());
			assertTrue(_rect.getPoints()[i].equals(points.get(i)));


			
		}
	}
	
}
