/**
 * 
 */
package Exe.Ex4.Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Triangle2D;
import Exe.Ex4.gui.Ex4;

/**
 * @author Gidi
 *
 */
class Ex4Test {

	private  ShapeCollectionable _shapes = new ShapeCollection();
	private  GUI_Shapeable _gs;
	ShapeCollectionable s;
	private  Color _color = Color.blue;
	private  boolean _fill = false;
	private  String _mode;
	MouseEvent e;
	private  Point2D _p1;
	private  Point2D _p2;
	private  Point2D p;
	private  Rect2D r;
	private  Triangle2D t;
	private  Circle2D c;
	private  ArrayList<Point2D> points = new ArrayList<Point2D>();
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		_shapes = new ShapeCollection();
		_fill = true;
		_mode = "Rect";
		r = new Rect2D(new Point2D(2,6), new Point2D(6,2));
		t = new Triangle2D(new Point2D(2,2), new Point2D(4,2),new Point2D(3,4));
		c = new Circle2D(new Point2D(2,2) , 2);
		p = new Point2D(5,5);
		_p1 = new Point2D(8,8);
		_p2 = new Point2D(2,2);
		GUI_Shapeable _gs1 = new GUIShape(r,_fill, Color.red, 0);
		GUI_Shapeable _gs2 = new GUIShape(t,false, _color, 1);
		GUI_Shapeable _gs3 = new GUIShape(c,false, _color, 2);
		_shapes.add(_gs1);
		_shapes.add(_gs2);
		_shapes.add(_gs3);

	}

	/**
	 * Test method for {@link Exe.Ex4.gui.Ex4#drawShapes()}.
	 */
	@Test
	final void testDrawShapes() {
	}

	/**
	 * Test method for {@link Exe.Ex4.gui.Ex4#actionPerformed(java.lang.String)}.
	 */
	@Test
	final void testActionPerformed() {
	}

	/**
	 * Test method for {@link Exe.Ex4.gui.Ex4#mouseClicked(Exe.Ex4.geo.Point2D)}.
	 */
	@Test
	final void testMouseClicked() {
		
	}

	/**
	 * Test method for {@link Exe.Ex4.gui.Ex4#mouseRightClicked(Exe.Ex4.geo.Point2D)}.
	 */
	@Test
	final void testMouseRightClicked() {
	}



}
