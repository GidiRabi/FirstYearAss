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
import Exe.Ex4.geo.Segment2D;

/**
 * @author Gidi
 *
 */
class Segment2DTest {

	private Point2D _p1;
	private Point2D _p2;
	private Segment2D _segment;
	
	/**
	 * @throws java.lang.Exception
	 * in this function we are setting a new segment before each test
	 * so we can change it and still test the same segment at each test
	 * 
	 */
	@BeforeEach
	void setUp() throws Exception {
		_p1 = new Point2D(5,5);
		_p2 = new Point2D(9,5);
		_segment = new Segment2D(_p1,_p2);
	}
//	 * this test checks if a segment contains a point
	@Test
	final void testContains() {
		Point2D p1 = new Point2D(6,5);
		Point2D p2 = new Point2D(5.2,5);
		Point2D p3 = new Point2D(2,6);
		Point2D p4 = new Point2D(10,6);
		
		assertTrue(_segment.contains(p1));
		assertTrue(_segment.contains(p2));
		assertFalse(_segment.contains(p3));
		assertFalse(_segment.contains(p4));
	}

//	 * this test checks the perimeter of a segment
	@Test
	final void testPerimeter() {
		double perimeter = _p1.distance(_p2);
		assertEquals(_segment.perimeter(),perimeter*2);
	}
	//	 * this test checks when a segment moves by a "vector" (point)
	@Test
	final void testMove() {
		Point2D vec = new Point2D(3,3);
		Point2D temp = new Point2D(6.7,5);
		Point2D p1 = new Point2D(_p1);
		temp.move(vec);
		Point2D shouldBe = new Point2D(8,8);
		_segment.move(vec);
		
		assertFalse(_segment.contains(p1));	
		assertTrue(_segment.contains(temp));
		assertTrue(_segment.contains(shouldBe));
		
		
		
		
	}
	//* this test checks the interface copy of a segment
	@Test
	final void testCopy() {
		GeoShapeable copy = null;
		copy = _segment;
		
		for(int i=0;i<_segment.getPoints().length;i++) {
			assertEquals(copy.getPoints()[i], _segment.getPoints()[i]);
		}
	}
//	 * this test checks the segment after being scaled
	@Test
	final void testScale() {
		Point2D contained = new Point2D(6.7,5);
		contained.scale(_p1,0.9);
		_segment.scale(_p1, 0.9);
		assertTrue(_segment.contains(contained));
		contained.move(contained);
		assertFalse(_segment.contains(contained));

		
	}
	/*
	 * in this test we created a new array and added all polygon points into it
	 then added it all to an Point2D arrat
	 
	 after that we compared each point and checked if all the points are equal
	 
	 */
	final void testGetPoints() {
		ArrayList<Point2D> points = new ArrayList<Point2D>();
		points.add(_p1);
		points.add(_p2);
		for(int i=0;i<_segment.getPoints().length;i++) {
			assertTrue(points.contains(_segment.getPoints()[i]));
		}
	}

}
