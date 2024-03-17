/**
 * 
 */
package Exe.Ex4.Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.ShapeComp;
import Exe.Ex4.geo.Triangle2D;

/**
 * @author Gidi
 *
 */
class ShapeCollectionTest {
	private ArrayList<GUI_Shapeable> _shapes = new ArrayList<GUI_Shapeable>();
	private GUI_Shapeable _gs;
	private GUI_Shapeable s;
	private Rect2D r;
	private Circle2D c ;
	private Triangle2D t;
	private ShapeCollectionable shape = new ShapeCollection();
	private Comparator<GUI_Shapeable> comp;

	/**
	 * @throws java.lang.Exception
	 * creates a shapecollection and 2 shapes and adds them to _shapes for each test
	 */
	@BeforeEach
	void setUp() throws Exception {
		r = new Rect2D(new Point2D(2,6), new Point2D(6,2));
		t = new Triangle2D(new Point2D(2,2), new Point2D(4,2),new Point2D(3,4));
		c = new Circle2D(new Point2D(2,2) , 2);
		_gs = new GUIShape(r,true, Color.red, 0);
		_shapes.add(_gs);
		_gs = new GUIShape(t,false, Color.blue, 1);
		_shapes.add(_gs);
		_gs = new GUIShape(c,false, Color.blue, 2);
		shape.add(_gs);

	}

	/**
	 * Test method for {@link Exe.Ex4.ShapeCollection#copy()}.
	 * this test represent 2 collections which are the same but do not have the 
	 * same memory
	 */
	@Test
	final void testCopy() {
		ShapeCollectionable copy = shape.copy();

		int i = shape.size();
		Random rand = new Random();
	    int r = rand.nextInt(i);
	    
		//testing if they have the same first point (random)
		assertEquals(copy.get(r).getShape().getPoints()[0].x(),shape.get(r).getShape().getPoints()[0].x());
		assertEquals(copy.get(r).getShape().getPoints()[0].y(),shape.get(r).getShape().getPoints()[0].y());
		//if theyre not the same means they dont have the same memory
		assertFalse(copy.get(0)==shape.get(0));
		
	}

	/**
	 * Test method for {@link Exe.Ex4.ShapeCollection#sort(java.util.Comparator)}.
	 * this tests shows if sort function works as it should
	 * given 3 examples
	 */
	@Test
	final void testSort() {
		double biggerP = _shapes.get(0).getShape().perimeter();
		double smallerP = _shapes.get(1).getShape().perimeter();
		double biggerA = _shapes.get(0).getShape().area();
		double smallerA = _shapes.get(1).getShape().area();
		_shapes.sort(ShapeComp.CompByPerimeter);
		assertEquals(_shapes.get(1).getShape().perimeter(),biggerP);
		assertEquals(_shapes.get(0).getShape().perimeter(),smallerP);
		_shapes.sort(ShapeComp.CompByAntiArea);
		assertEquals(_shapes.get(0).getShape().area(),biggerA);
		assertEquals(_shapes.get(1).getShape().area(),smallerA);
		_shapes.sort(ShapeComp.CompByArea);
		assertEquals(_shapes.get(1).getShape().area(),biggerA);
		assertEquals(_shapes.get(0).getShape().area(),smallerA);
		_shapes.sort(ShapeComp.CompByAntiPerimeter);
		assertEquals(_shapes.get(0).getShape().perimeter(),biggerP);
		assertEquals(_shapes.get(1).getShape().perimeter(),smallerP);	
	}

	/**
	 * Test method for {@link Exe.Ex4.ShapeCollection#removeAll()}.
	 * this tests shows if after using removeall function _shapes size is 0
	 * means it deleted everything
	 */
	@Test
	final void testRemoveAll() {
		_shapes.removeAll(_shapes);
		assertEquals(_shapes.size() ,0);
	}

	/**
	 * Test method for {@link Exe.Ex4.ShapeCollection#save(java.lang.String)}.
	 * this tests 
	 */
	@Test
	final void testSave() {
		if(true) System.out.println(true);
	}

	/**
	 * Test method for {@link Exe.Ex4.ShapeCollection#load(java.lang.String)}.
	 */
	@Test
	final void testLoad() {
	}

	/**
	 * Test method for {@link Exe.Ex4.ShapeCollection#getBoundingBox()}.
	 * this test shows if the rectangle given after using the function giving the right one
	 * we calculated given the radius and points the rectangle that we should get
	 */
	@Test
	final void testGetBoundingBox() {
		
		Rect2D b = new Rect2D(new Point2D(0,4), new Point2D(4,0));
		Rect2D bound = shape.getBoundingBox();
		System.out.println(b.getPoints()[0]);
		System.out.println(b.getPoints()[1]);
		System.out.println(b.getPoints()[2]);
		System.out.println(b.getPoints()[3]);
		System.out.println();
		System.out.println(bound.getPoints()[0]);
		System.out.println(bound.getPoints()[1]);
		System.out.println(bound.getPoints()[2]);
		System.out.println(bound.getPoints()[3]);

		assertEquals(b.getPoints()[0].x(),bound.getPoints()[0].x());
	}

}
