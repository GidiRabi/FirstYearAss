package Exe.Ex4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.stream.DoubleStream;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements ShapeCollectionable{
	private ArrayList<GUI_Shapeable> _shapes;

	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shapeable>();
	}
	@Override
	public GUI_Shapeable get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		
		if(_shapes != null)
			return _shapes.size();
		
		return 0;
	}
	/*
	 * 
	 * this function removing a GUI_shapeable at index i 
	 */
	@Override
	public GUI_Shapeable removeElementAt(int i) {
		//////////add your code below ///////////
		GUI_Shapeable tmp = _shapes.get(i);
		_shapes.remove(i);
		
		return tmp;
		//////////////////////////////////////////
	}

	/*
	 * 
	 * this function adding a GUI_shapeable at index i 
	 */
	@Override
	public void addAt(GUI_Shapeable s, int i) {
		//////////add your code below ///////////
		if(s!=null && s.getShape()!=null) {
			_shapes.add(i,s); }
		//////////////////////////////////////////
	}
	@Override
	public void add(GUI_Shapeable s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}
	/*
	 * this method copys a shapecollection using an iterator
	 * theyre the same yet not pointing at the same memory value
	 */
	@Override
	public ShapeCollectionable copy() {
		//////////add your code below ///////////
		ShapeCollectionable copy = new ShapeCollection();
		for(int i = 0 ; i < _shapes.size() ; i++) {
			copy.add(new GUIShape(_shapes.get(i).getShape(),_shapes.get(i).isFilled(), _shapes.get(i).getColor() , _shapes.get(i).getTag()));	
		}
		return copy;	    
	    
		//////////////////////////////////////////
	}
	/*
	 * this method takes 2 shapes from _shapes and puts them inside the comparator
	 * and with the flag chosen it compares them to the certain one and returns here 
	 * the value -1/0/1 and switchs between the 2 if the j > i (sorting)
	 */
	@Override
	public void sort(Comparator<GUI_Shapeable> comp) {
		//////////add your code below ///////////
		for(int i = 0; i< _shapes.size() ; i++)
			for(int j =0; j <_shapes.size() ; j++) {
				if(i==j)
					continue;
				
				if(comp.compare(_shapes.get(i), _shapes.get(j)) >0) {
					 continue;
				}
				else if(comp.compare(_shapes.get(i), _shapes.get(j)) < 0) {
						GUI_Shapeable temp = _shapes.get(i);
					    _shapes.set(i, _shapes.get(j));
					    _shapes.set(j, temp);
				}
				else if(comp.compare(_shapes.get(i), _shapes.get(j)) == 0) {
					 continue;
				}
			}
		//////////////////////////////////////////
	}
	/*
	 * This method removes all the elements from a list one by one
	 */
	@Override
	public void removeAll() {
		//////////add your code below ///////////
		while(!_shapes.isEmpty()) {
			_shapes.remove(0);
		}
		//////////////////////////////////////////
	}

	@Override
	public void save(String file) {
		//////////add your code below ///////////
		
		
		//////////////////////////////////////////
	}

	@Override
	public void load(String file) {
		////////// add your code below ///////////
		
		
		//////////////////////////////////////////
	}
	
	/*
	 * 
	 * This method returns a Rect2D object that represents the minimum bounding rectangle
	 * that encloses all the GUI_Shapeable objects in the _shapes list.
	 * It does this by creating an ArrayList called arrL and adding the points of all 
	 * the GeoShapeable objects in the _shapes list to it. Then, it finds the minimum
	 * and maximum x and y values in arrL and creates a new Rect2D object using those 
	 * values as the top-left and bottom-right points. Finally, it returns the Rect2D
	 * object.


	 */
	@Override
	public Rect2D getBoundingBox() {
		Rect2D ans = null;
		//////////add your code below ///////////				
		ArrayList<Point2D> arrL = new ArrayList<Point2D>();
		
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shapeable s = _shapes.get(i);
				GeoShapeable g = s.getShape();
				if(g!=null) {
					if(g instanceof Circle2D) {
						Point2D[] arr = ((Circle2D) g).getPoints();
						double rad = ((Circle2D) g).getRadius();
						arrL.add(new Point2D(arr[0].x() - rad, arr[0].y() - rad));
						arrL.add(new Point2D(arr[0].x() - rad , arr[0].y() + rad));
						arrL.add(new Point2D(arr[0].x() + rad , arr[0].y() - rad));
						arrL.add(new Point2D(arr[0].x() + rad , arr[0].y() + rad));
					}
					else {
						Point2D[] arr = g.getPoints();
						for(Point2D x : arr)
							arrL.add(x);
					}
				}
			}
				double maxX = Math.max(arrL.get(0).x(),arrL.get(1).x());
				double minX = Math.max(arrL.get(0).x(),arrL.get(1).x());
				double maxY = Math.max(arrL.get(0).x(),arrL.get(1).x());
				double minY = Math.max(arrL.get(0).x(),arrL.get(1).x());
					
					for(int j=0;j< arrL.size();j++) {
					
					      if (arrL.get(j).x() > maxX) {
					    	  maxX = arrL.get(j).x();
					      }
					      if (arrL.get(j).x() < minX) {
						        minX = arrL.get(j).x();
						      }
					      if (arrL.get(j).y() > maxY) {
						        maxY = arrL.get(j).y();
						      }
					      if (arrL.get(j).y() < minY) {
						        minY = arrL.get(j).y();
						      }
					    }
				
		ans = new Rect2D(new Point2D(minX , maxY) , new Point2D(maxX,minY));
		//////////////////////////////////////////
		return ans;
	}
	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}
	

}