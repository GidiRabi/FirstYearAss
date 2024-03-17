package Exe.Ex4;
/**
 * This class implements the GUI_shape.
 * Ex4: you should implement this class!
 * @author I2CS
 */
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;


public class GUIShape implements GUI_Shapeable{
	private GeoShapeable _g = null;
	private boolean _fill;
	private Color _color;
	private int _tag;
	private boolean _isSelected;
	
	public GUIShape(GeoShapeable g, boolean f, Color c, int t) {
		_g = null;
		if (g!=null) {_g = g.copy();}
		_fill= f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}
	public GUIShape(GUIShape ot) {
		this(ot._g, ot._fill, ot._color, ot._tag);
	}
	
	@Override
	public GeoShapeable getShape() {
		return _g;
	}

	@Override
	public boolean isFilled() {
		return _fill;
	}

	@Override
	public void setFilled(boolean filled) {
		_fill = filled;
	}

	@Override
	public Color getColor() {
		return _color;
	}

	@Override
	public void setColor(Color cl) {
		_color = cl;
	}

	@Override
	public int getTag() {
		return _tag;
	}

	@Override
	public void setTag(int tag) {
		_tag = tag;
		
	}

	/*
	 * This method creates a copy of the object on which it is called and returns it.
	 *  It creates a new GUIShape object using the original object as the argument to 
	 *  the constructor and assigns it to a local variable cp. The method then returns cp.
	 *  
	 */
	@Override
	public GUI_Shapeable copy() {
		GUI_Shapeable cp = new GUIShape(this);
		return cp;
	}
	/*
	 * This method returns a string representation of a GUIShape object based on the
	 *  type of the _g object and the object's color, fill, and tag. It includes 
	-specific data, such as the points that make up the shape. If the type of _g is not 
	recognized, the method returns null.
	 */
	@Override
	public String toString() {
		String ans = null;
		
		//GUIShape,color,fill,tag,GeoShape,data1,data2,...
		if(_g instanceof Circle2D) {
			Point2D[] arr = this.getShape().getPoints();
			Circle2D c = (Circle2D) _g;
			
			return "GUIShape, " + this._color +", " + this._fill + ", " + this.getTag() + ",  "
					+ this.getShape() + ", x:" + arr[0].x() + ", y:" + arr[0].y() + ", radius:" 
					+ c.getRadius();
		}
		if(_g instanceof Segment2D) {
			Point2D[] arr = this.getShape().getPoints();

			return "GUIShape, " + this._color +", " + this._fill + ", " + this.getTag() + ",  "
					+ this.getShape() + ", P1x:" + arr[0].x() + ", P1y:" + arr[0].y() + ", P2x:"
					+ arr[0].x() + ", P2y:" + arr[0].y() ;
		}
		if(_g instanceof Triangle2D) {
			Point2D[] arr = this.getShape().getPoints();

			return "GUIShape, " + this._color +", " + this._fill + ", " + this.getTag() + ",  "
					+ this.getShape() + ", P1x:" + arr[0].x() + ", P1y:" + arr[0].y() 
					+ ", P2x:" + arr[1].x() + ", P2y:" + arr[1].y()
					+ ", P3x:" + arr[2].x() + ", P3y:" + arr[2].y();
		}		
		if(_g instanceof Rect2D) {
			Point2D[] arr = this.getShape().getPoints();

			return "GUIShape, " + this._color +", " + this._fill + ", " + this.getTag() + ",  "
			+ this.getShape() + ", P1x:" + arr[0].x() + ", P1y:" + arr[0].y() 
			+ ", P2x:" + arr[1].x() + ", P2y:" + arr[1].y()
			+ ", P3x:" + arr[2].x() + ", P3y:" + arr[2].y()
			+ ", P4x:" + arr[3].x() + ", P4y:" + arr[3].y();
		}
		if(_g instanceof Polygon2D) {
			ArrayList<Point2D> pp = new ArrayList<Point2D>();
			String temp = "";
			for (int i = 0; i < pp.size(); i++) {
			      temp += "x"+i+": " +pp.get(i).x() + "y"+i+": " + pp.get(i).y();
			    }

			return "GUIShape, " + this._color +", " + this._fill + ", " + this.getTag() + ",  "
					+ this.getShape() + temp; 
		}
		
		
		return ans;
	}
	
		private void init(String[] ww) {
			_color = new Color(Integer.parseInt(ww[1]));
			_fill = Boolean.parseBoolean(ww[2]);
			_tag = Integer.parseInt(ww[3]);
			String info = ww[4];

			Point2D p = new Point2D(0,0); // the files is saves by little pixel aka points. by this point we will represent the objects

			if(info.compareTo("Point2D") == 0) {
				p = new Point2D(Double.parseDouble(ww[5]),Double.parseDouble(ww[6])); // parse to the x,y values
			}

			else if (info.compareTo("Circle2D") == 0) {
				double x = 0, y = 0;
				x = Double.parseDouble(ww[5]);
				y = Double.parseDouble(ww[6]);
				Point2D center = new Point2D(x,y);
				double radius = Double.parseDouble(ww[7]);
				_g = new Circle2D(center,radius);
			}

			else if (info.compareTo("Rect2D") == 0) {

				double x1 = 0, y1 = 0, x2 = 0, y2 = 0, x3 = 0, y3 = 0, x4 = 0, y4= 0;

				x1=Double.parseDouble(ww[5]);
				y1=Double.parseDouble(ww[6]);
				x2=Double.parseDouble(ww[7]);
				y2=Double.parseDouble(ww[8]);
				x3=Double.parseDouble(ww[9]);
				y3=Double.parseDouble(ww[10]);
				x4=Double.parseDouble(ww[11]);
				y4=Double.parseDouble(ww[12]);

				Point2D p1,p2,p3,p4;
				p1 = new Point2D(x1,y1);
				p2 = new Point2D(x2,y2);
				p3 = new Point2D(x3,y3);
				p4 = new Point2D(x4,y4);

				ArrayList<Point2D> rectPoints = new ArrayList<Point2D>();
				rectPoints.add(p1);
				rectPoints.add(p2);
				rectPoints.add(p3);
				rectPoints.add(p4);

				_g = new Polygon2D(rectPoints);

				// to think on the conversion between the rect and the polygon
				// to see how boaz saved the rectangle
			}
		
		}
	@Override
	public boolean isSelected() {
		return this._isSelected;
	}
	@Override
	public void setSelected(boolean s) {
		this._isSelected = s;
	}
	/*
	 * This method sets the shape of the object to a new value.
	 */
	@Override
	public void setShape(GeoShapeable g) {
		// TODO Auto-generated method stub
		this._g = g;
	}
}