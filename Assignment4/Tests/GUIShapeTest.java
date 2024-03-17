/**
 * 
 */
package Exe.Ex4.Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Triangle2D;

	
/**
 * @author Gidi
 *
 */
class GUIShapeTest {

	private GeoShapeable _g;
	private GUI_Shapeable _gs;
	private boolean _isSelected;
	GUI_Shapeable s;
	private  ShapeCollectionable _shapes = new ShapeCollection();
	Rect2D r;


	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		_g = null;
		_isSelected = false;
		r = new Rect2D(new Point2D(2,6), new Point2D(6,2));
		Triangle2D t = new Triangle2D(new Point2D(2,2), new Point2D(4,2),new Point2D(3,4));
		_gs = new GUIShape(r,false, Color.blue, 0);
		_shapes.add(_gs);
		_gs = new GUIShape(t,false, Color.blue, 0);
		_shapes.add(_gs);
	}

	/**
	 * Test method for {@link Exe.Ex4.GUIShape#copy()}.
	 */
	@Test
	final void testCopy() {
		GUI_Shapeable _gs = _shapes.copy().get(0);
		assertEquals(_gs.getTag(),_shapes.copy().get(0).getTag());
	}

	/**
	 * Test method for {@link Exe.Ex4.GUIShape#isSelected()}.
	 */
	@Test
	final void testIsSelected() {
_shapes.get(0).setSelected(true);
		
		assertTrue(_shapes.get(0).isSelected());
		_shapes.get(0).setSelected(false);
		assertFalse(_shapes.get(0).isSelected());
	}

	/**
	 * Test method for {@link Exe.Ex4.GUIShape#setSelected(boolean)}.
	 */
	@Test
	final void testSetSelected() {
		_shapes.get(0).setSelected(true);
		
		assertTrue(_shapes.get(0).isSelected());
		_shapes.get(0).setSelected(false);
		assertFalse(_shapes.get(0).isSelected());

		
	}

	/**
	 * Test method for {@link Exe.Ex4.GUIShape#setShape(Exe.Ex4.geo.GeoShapeable)}.
	 */
	@Test
	final void testSetShape() {
		GUI_Shapeable gs = new GUIShape(r,true, Color.blue, 1);
		gs.setShape(_g);
		assertEquals(gs.getShape(),null);

	}

}
