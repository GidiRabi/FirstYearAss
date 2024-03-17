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
import Exe.Ex4.geo.Polygon2D;

/**
 * @author Gidi
 *
 */
class Polygon2DTest {
	private ArrayList<Point2D> points = new ArrayList<Point2D>();
	Polygon2D p = new Polygon2D(points);
	
	/**
	 * @throws java.lang.Exception
	 * creates a new polygon before each test
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		points.add(new Point2D(5,4));
		points.add(new Point2D(8,4.2));
		points.add(new Point2D(9.5,7));
		points.add(new Point2D(8,9));
		points.add(new Point2D(5,9.4));
		points.add(new Point2D(3,7));
			
		p = new Polygon2D(points);
	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Polygon2D#contains(Exe.Ex4.geo.Point2D)}.
	 	 * this test checks certain points in a polygon
	 */
	@Test
	final void testContains() {
		
		Point2D inside1 = new Point2D(6,5);
		Point2D outside1 = new Point2D(2,2);
		Point2D outside2 = new Point2D(9,10);
		

		if(!p.contains(inside1))
			fail("inside1 should be contained");
		if(p.contains(outside1))
			fail("outside1 shouldnt be contained");
		if(p.contains(outside2))
			fail("outside2 shouldnt be contained");
		
		

	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Polygon2D#area()}.
	 * https://www.mathopenref.com/coordpolygonareacalc.html
		 * this test checks if we get the right area of the triangle	
	 * 
	 */
	@Test
	final void testArea() {
		double areaInGoogle = 24.3;
		assertEquals(areaInGoogle,p.area(),0.001);
		
	}

	
	/**
	 * Test method for {@link Exe.Ex4.geo.Polygon2D#perimeter()}.
	 * i calculated the perimeter using a google regular online calculator
	 * and compared it to the perimeter out function  gives
	 */
	@Test
	final void testPerimeter() {
		double per = 29.5;
		assertEquals(per,p.perimeter(),0.5);
	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Polygon2D#move(Exe.Ex4.geo.Point2D)}.
		 * this test checks if we get the right area of the triangle

	 */
	@Test
	final void testMove() {

		Point2D vec = new Point2D(3,3);
		Point2D shouldBe = new Point2D(8,7);
		Point2D shouldBe2 = new Point2D(6,10);

			p.move(vec);
			assertTrue(p.getPoints()[0].equals(shouldBe));
			assertTrue(p.getPoints()[5].equals(shouldBe2));

			
		
	}
	/**
	 * Test method for {@link Exe.Ex4.geo.Polygon2D#copy()}.
	 * this test checks the interface copy of a polygon
	 * and for example set an equals for the first point and last
	 * points of the polygon and the copy
	 */
	@Test
	final void testCopy() {
		GeoShapeable co = p.copy();
		assertEquals(co.getPoints()[0],p.getPoints()[0]);
		assertEquals(co.getPoints()[co.getPoints().length -1],p.getPoints()[p.getPoints().length -1]);

	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Circle2D#scale(Exe.Ex4.geo.Point2D, double)}.
	 * testing to see if the scale function scales to 110% the circle by checking if a point is contained
	 * in the new circle after being scaled.
	 */
	@Test
	final void testScale90() {
		//scaling using a point from the top right of the p and a 110 degree
	
		Point2D click = new Point2D(p.getPoints()[2].x() +0.1,p.getPoints()[2].y() +0.1);
		Point2D Ncontained = new Point2D(p.getPoints()[0].x(),p.getPoints()[0].y());
		Point2D Ycontained = new Point2D(p.getPoints()[0].x()*1.5,p.getPoints()[0].y()*1.5);

		p.scale(click, 0.9);
		assertTrue(p.contains(Ycontained));
		assertFalse(p.contains(Ncontained)); 

	}
	/**
	 * Test method for {@link Exe.Ex4.geo.Circle2D#scale(Exe.Ex4.geo.Point2D, double)}.
	 * testing to see if the scale function scales to 90% the p by checking if a point is contained
	 * in the new p after being scaled.
	 */
	@Test
	final void testScale110() {
		Point2D click = new Point2D(p.getPoints()[2].x() +0.1,p.getPoints()[2].y() +0.1);
		Point2D Ncontained = new Point2D(p.getPoints()[0].x()*0.8,p.getPoints()[0].y()*0.8);
		Point2D Ycontained = new Point2D(p.getPoints()[0].x()*1.1,p.getPoints()[0].y()*0.95);

		p.scale(click, 1.1);
		assertTrue(p.contains(Ycontained));
		assertFalse(p.contains(Ncontained)); 

	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Polygon2D#getPoints()}.
	 in this test we created a new array and added all polygon points into it
	 then added it all to an Point2D arrat
	 
	 after that we compared each point and checked if all the points are equal
	 
	 */
	@Test
	final void testGetPoints() {

		ArrayList<Point2D> pp = new ArrayList<Point2D>();
		points.addAll(points);
		Point2D[] arr = new Point2D[pp.size()];
		for(int i = 0 ; i < arr.length ; i++) {
			arr[i] = pp.get(i);
		}
		
		for(int i = 0 ; i < arr.length ; i++) {
		assertEquals(arr[i], p.getPoints()[i]);
		}

			
	}

}
