/**
 * 
 */
package Exe.Ex4.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;

/**
 * @author Gidi & Lior
 *
 */
class Circle2DTest {
	
	private Circle2D circle;
	private Point2D Ccenter;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		circle = new Circle2D(new Point2D(2,2) , 3);
		Ccenter = new Point2D(2,2);
		
	}
	/**
	 * Test method for {@link Exe.Ex4.geo.Circle2D#getRadius()}
	 * this test checks if the getRadius function returns the radius of the circle
	 */
	@Test
	final void testGetRadius() {
		
		assertEquals(circle.getRadius() , 3);
	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Circle2D#toString()}.
	 * this test checks if the circle makes the toString as it should
	 */
	@Test
	final void testToString() {
		assertEquals("2.0,2.0, 3.0", circle.toString());
		
	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Circle2D#contains(Exe.Ex4.geo.Point2D)}.
	 * this test checks if a point is indeed inside the circle i created and one that is outside
	 * and checks if it indeed outside
	 */
	@Test
	final void testContains() {
		assertTrue(circle.contains(new Point2D(circle.getPoints()[0].x(),circle.getPoints()[0].y()+circle.getRadius()*0.25)));
		assertTrue(circle.contains(new Point2D(circle.getPoints()[0].x()+circle.getRadius()*0.6,circle.getPoints()[0].y())));
		assertFalse(circle.contains(new Point2D(circle.getPoints()[0].x(),circle.getPoints()[0].y()+circle.getRadius()*1.6)));
		assertFalse(circle.contains(new Point2D(circle.getPoints()[0].x()+circle.getRadius()*1.1,circle.getPoints()[0].y())));
	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Circle2D#area()}
	 * this test checks if the area function returns the right area of the circle
	 */
	@Test
	final void testArea() {
		//area of the circle is R^2 * pie
		double area = Math.pow(circle.getRadius(),2) * Math.PI;
		assertEquals(area,circle.area());
		area -= 0.5;
		assertFalse(area == circle.area());
		
		
	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Circle2D#perimeter()}.
	 * this tests checks if the perimeter function in circle2D returns the right perimeter
	 */
	@Test
	final void testPerimeter() {
		//perimeter of the circle is R* 2 * pie

		double perimeter = circle.getRadius()*2* Math.PI;
		assertEquals(perimeter,circle.perimeter());
		perimeter -= 0.5;
		assertFalse(perimeter == circle.perimeter());
	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Circle2D#move(Exe.Ex4.geo.Point2D)}.
	 * this tests checks if the move function works properly and moves the points in the circle right
	 */
	@Test
	final void testMove() {
		Point2D vec = new Point2D(4,4);
		Point2D newPoint = new Point2D(vec.x()+circle.getPoints()[0].x(), vec.y()+circle.getPoints()[0].y() );
		Point2D newCenter = circle.getPoints()[0];
		newCenter.move(vec);
		assertEquals(newPoint,newCenter);
	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Circle2D#copy()}.
	 * this test checks if the copy functions copy it right by checking every data of the circle
	 */
	@Test
	final void testCopy() {
		GeoShapeable copy = null;
		copy = circle.copy();
		
		double CPx = copy.getPoints()[0].x();
		double CPy = copy.getPoints()[0].y();
			
		assertEquals(CPx, circle.getPoints()[0].x());
		assertEquals(CPy, circle.getPoints()[0].y());
		assertEquals(copy.perimeter(),circle.perimeter());
		
		assertFalse(CPx +1 == circle.getPoints()[0].x());
		assertFalse(copy.perimeter()*2 == circle.perimeter());

	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Circle2D#getPoints()}.
	 * this tests checks if the function getpoints return all the points needed in a circle (center)
	 */
	@Test
	final void testGetPoints() {
		//{center , {(center), (center x ,center y + radius)}
		Point2D[] arrF = {new Point2D(Ccenter.x() - 0.1 , Ccenter.y()+0.1), new Point2D(Ccenter.x() + circle.getRadius() , Ccenter.y())};
		Point2D[] arrT = {Ccenter , new Point2D(Ccenter.x() , Ccenter.y() + circle.getRadius())};
		Point2D[] arrC = circle.getPoints();
		for(int i = 0 ; i < arrC.length ; i++) {
			double tempX1 = arrF[i].x();
			double tempY1 = arrF[i].y();
			
			//should fail 
			if(tempX1 == arrC[i].x())
				fail("we got a wrong x value from the function");
			
			if(tempY1 == arrC[i].y())
				fail("we got a wrong y value from the function ");
			
			
			if(arrT[i].x() != arrC[i].x())
				fail("not the same X value from the array that we should get the points, Wtrong Point ");
			
			if(arrT[i].y() != arrC[i].y())
				fail("not the same X value from the array that we should get the points, Wtrong Point");
			
		}
	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Circle2D#scale(Exe.Ex4.geo.Point2D, double)}.
	 * testing to see if the scale function scales to 110% the circle by checking if a point is contained
	 * in the new circle after being scaled.
	 */
	@Test
	final void testScale110() {
		//scaling using a point from the top right of the circle and a 110 degree
		double firstRad = circle.getRadius();
		circle.scale(new Point2D(Ccenter.x() + circle.getRadius() + 1 , Ccenter.y()) , 1.1);
		Point2D inside = new Point2D(Ccenter.x() - firstRad*1.05 ,Ccenter.y());
		Point2D outside = new Point2D(Ccenter.x() - (firstRad * 2) ,Ccenter.y());
		Point2D outside1 = new Point2D(Ccenter.x() - firstRad -1 ,Ccenter.y());  // input a point that is inside the circle after being scaled down
		assertTrue(circle.contains(inside));
		assertFalse(circle.contains(outside)); 
		assertFalse(circle.contains(outside1)); 

	}
	/**
	 * Test method for {@link Exe.Ex4.geo.Circle2D#scale(Exe.Ex4.geo.Point2D, double)}.
	 * testing to see if the scale function scales to 90% the circle by checking if a point is contained
	 * in the new circle after being scaled.
	 */
	@Test
	final void testScale90() {
		//scaling using a point from the top right of the circle and a 110 degree
		double firstRad = circle.getRadius();
		circle.scale(new Point2D(Ccenter.x() + circle.getRadius() + 1 , Ccenter.y()) , 0.9);
		Point2D outside = new Point2D(Ccenter.x() - firstRad*0.89 ,Ccenter.y());
		Point2D inside = new Point2D(Ccenter.x() + circle.getRadius()*0.91 ,Ccenter.y()); // input a point that is inside the circle after being scaled down
		assertTrue(circle.contains(inside));
		assertFalse(circle.contains(outside));
	}

	/**
	 * Test method for {@link Exe.Ex4.geo.Circle2D#rotate(Exe.Ex4.geo.Point2D, double)}.
	 * this test checks if the rotate function works as it should for a circle, and for circle is should
	 * not do anything. so, should be as same as its copy.
	 */
	@Test
	final void testRotate() {
		
		Point2D temp = new Point2D(1.5,2.3); //point inside the circle
		assertTrue(circle.contains(temp));
		circle.rotate(new Point2D(Ccenter.x() + circle.getRadius() + 1 , Ccenter.y() + circle.getRadius() +1 ), 15);
		temp.rotate(new Point2D(Ccenter.x() + circle.getRadius() + 1 , Ccenter.y() + circle.getRadius() +1 ), 15);
		
		if(!circle.contains(temp))
			fail("They are not the same after rotating, means rotate function changed the circle coordinations");
	}

}
