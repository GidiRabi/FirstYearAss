package Exe.Ex4.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DirectColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.ShapeComp;
import Exe.Ex4.geo.Triangle2D;

/**
 * 
 * This class is a simple "inter-layer" connecting (aka simplifying) the StdDraw
 * with the Map class. Written for 101 java course it uses simple static
 * functions to allow a "Singleton-like" implementation.
 * 
 * @author boaz.benmoshe
 *
 */
public class Ex4 implements Ex4_GUI {
	private ShapeCollectionable _shapes = new ShapeCollection();
	private GUI_Shapeable _gs;
	private Color _color = Color.blue;
	private boolean _fill = false;
	private String _mode = "";
	private Point2D _p1;
	private Point2D _p2;
	private ArrayList<Point2D> PPoints = new ArrayList<Point2D>();
	private int counter;

	private static Ex4 _winEx4 = null;

	private Ex4() {
		init(null);
	}

	public void init(ShapeCollectionable s) {
		if (s == null) {
			_shapes = new ShapeCollection();
		} else {
			_shapes = s.copy();
		}
		GUI_Shapeable _gs = null;
		Polygon2D _pp = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		Point2D _p1 = null;
	}

	public void show(double d) {
		StdDraw_Ex4.setScale(0, d);
		StdDraw_Ex4.show();
		drawShapes();
	}

	public static Ex4 getInstance() {
		if (_winEx4 == null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}

	public void drawShapes() {
		StdDraw_Ex4.clear();
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable sh = _shapes.get(i);
			counter++;
			sh.setTag(counter);
			drawShape(sh);
		}
		if (_gs != null) {
			drawShape(_gs);
		}
		StdDraw_Ex4.show();
	}

	private static void drawShape(GUI_Shapeable g) {
		StdDraw_Ex4.setPenColor(g.getColor());
		if (g.isSelected()) {
			StdDraw_Ex4.setPenColor(Color.gray);
		}
		GeoShapeable gs = g.getShape();
		boolean isFill = g.isFilled();
		if (gs instanceof Circle2D) {
			Circle2D c = (Circle2D) gs;
			Point2D cen = c.getPoints()[0];
			double rad = c.getRadius();
			if (isFill) {
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			} else {
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
			}
		}
		/*
		 * if its a circle then draw a line from point a to point b given the points
		 */
		if (gs instanceof Segment2D) {
			Segment2D s = (Segment2D) gs;
			Point2D[] p = s.getPoints();

			StdDraw_Ex4.line(p[0].x(), p[0].y(), p[1].x(), p[1].y());

		}
		/*
		 * if its a triangle then we draw a polygon with points a b c of the triangle
		 */
		if (gs instanceof Triangle2D) {
			Triangle2D t = (Triangle2D) gs;
			// array of points
			Point2D[] p = t.getPoints();
			double[] px = { p[0].x(), p[1].x(), p[2].x() };
			double[] py = { p[0].y(), p[1].y(), p[2].y() };

			if (isFill) {
				StdDraw_Ex4.filledPolygon(px, py);
			} else {
				StdDraw_Ex4.polygon(px, py);
			}
		}
		/*
		 * if its a rectangle then we draw a polygon with points a b c d of the
		 * rectangle by order
		 */
		if (gs instanceof Rect2D) {
			Rect2D r = (Rect2D) gs;
			// array of points
			Point2D[] p = r.getPoints();
			double[] px = { p[0].x(), p[1].x(), p[3].x(), p[2].x() };
			double[] py = { p[0].y(), p[1].y(), p[3].y(), p[2].y() };

			if (isFill) {
				StdDraw_Ex4.filledPolygon(px, py);
			} else {
				StdDraw_Ex4.polygon(px, py);
			}
		}

		/*
		 * if its a rectangle then we draw a polygon with points a b ... n of the
		 * rectangle by order
		 */
		if (gs instanceof Polygon2D) {
			Polygon2D poly = (Polygon2D) gs;
			// array of points
			Point2D[] p = poly.getPoints();

			double[] px = new double[p.length];
			double[] py = new double[p.length];

			for (int i = 0; i < p.length; i++) {
				px[i] = p[i].x();
				py[i] = p[i].y();
			}
			if (isFill) {
				StdDraw_Ex4.filledPolygon(px, py);
			} else {
				StdDraw_Ex4.polygon(px, py);
			}
		}

	}

	private void setColor(Color c) {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			if (s.isSelected()) {
				s.setColor(c);
			}
		}
	}

	private void setFill() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			if (s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}

	public void actionPerformed(String p) {
		_mode = p;
		if (p.equals("Blue")) {
			_color = Color.BLUE;
			setColor(_color);
		}
		if (p.equals("Red")) {
			_color = Color.RED;
			setColor(_color);
		}
		if (p.equals("Green")) {
			_color = Color.GREEN;
			setColor(_color);
		}
		if (p.equals("White")) {
			_color = Color.WHITE;
			setColor(_color);
		}
		if (p.equals("Black")) {
			_color = Color.BLACK;
			setColor(_color);
		}
		if (p.equals("Yellow")) {
			_color = Color.YELLOW;
			setColor(_color);
		}
		if (p.equals("Fill")) {
			_fill = true;
			setFill();
		}
		if (p.equals("Empty")) {
			_fill = false;
			setFill();
		}
		if (p.equals("Clear")) {
			_shapes.removeAll();
		}

		/*
		 * if equals one of the sort then send with _shapes the right comparator
		 */
		if (p.equals("ByArea")) {
			_shapes.sort(ShapeComp.CompByArea);
		}
		if (p.equals("ByAntiArea")) {
			_shapes.sort(ShapeComp.CompByAntiArea);
		}
		if (p.equals("ByToString")) {
			_shapes.sort(ShapeComp.CompByToString);
		}
		if (p.equals("ByAntiToString")) {
			_shapes.sort(ShapeComp.CompByAntiToString);
		}
		if (p.equals("ByPerimeter")) {
			_shapes.sort(ShapeComp.CompByPerimeter);
		}
		if (p.equals("ByAntiPerimeter")) {
			_shapes.sort(ShapeComp.CompByAntiPerimeter);
		}
		if (p.equals("ByTag")) {
			_shapes.sort(ShapeComp.CompByTag);
		}
		if (p.equals("ByAntiTag")) {
			_shapes.sort(ShapeComp.CompByAntiTag);
		}

		/*
		 * 
		 * here we want to click the one we want and itll call the right function and do
		 * it without the user having to click it
		 */
		if (_mode.equals("Remove")) {
			remove();
		}
		if (_mode.equals("All")) {
			selectAll();
		}
		if (_mode.equals("Anti")) {
			anti();
		}
		if (_mode.equals("Info")) {
			System.out.println(getInfo());
		}
		if (_mode.equals("None")) {
			if (_shapes != null)
				none();
		}

		
		  if (p.equals("Load")) {  
			  loadFile();
		  }
		 

		/*
		 * if (p.equals("Save")){ String filename; if (filename == null) throw new
		 * IllegalArgumentException(); File file = new File(filename); String suffix =
		 * filename.substring(filename.lastIndexOf('.') + 1);
		 * 
		 * // png files if ("png".equalsIgnoreCase(suffix)) { try {
		 * ImageIO.write(onscreenImage, suffix, file); } catch (IOException e) {
		 * e.printStackTrace(); } }
		 * 
		 * // reference:
		 * http://archives.java.sun.com/cgi-bin/wa?A2=ind0404&L=java2d-interest&D=0&P=
		 * 2727 else if ("jpg".equalsIgnoreCase(suffix)) { WritableRaster raster =
		 * onscreenImage.getRaster(); WritableRaster newRaster; newRaster =
		 * raster.createWritableChild(0, 0, width, height, 0, 0, new int[] {0, 1, 2});
		 * DirectColorModel cm = (DirectColorModel) onscreenImage.getColorModel();
		 * DirectColorModel newCM = new DirectColorModel(cm.getPixelSize(),
		 * cm.getRedMask(), cm.getGreenMask(), cm.getBlueMask()); BufferedImage
		 * rgbBuffer = new BufferedImage(newCM, newRaster, false, null); try {
		 * ImageIO.write(rgbBZuffer, suffix, file); } catch (IOException e) {
		 * e.printStackTrace(); } }
		 * 
		 * else { System.out.println("Invalid image file type: " + suffix); } }
		 */

		
		  //to create this i used the help of the website
		  //"https://www.codejava.net/java-se/swing/show-simple-open-file-dialog-using-jfilechooser"
		  if (p.equals("Save")) { 
			 JFileChooser fileChooser = new JFileChooser();
			  fileChooser.setCurrentDirectory (new File(System.getProperty("user.dir")));
			  int result = fileChooser.showSaveDialog(StdDraw_Ex4.getFrame()); 
			  if (result == JFileChooser.APPROVE_OPTION) {
			  
			  try { fileChooser.getSelectedFile().getAbsoluteFile().createNewFile(); }
			  catch (IOException e) { e.printStackTrace(); }
			  _shapes.save(fileChooser.getSelectedFile().getPath()); } 
		  }
			  
			  
		drawShapes();

	}
	
	//	This code will read the contents of the file line by line and print each line to the console. If the file is not found, a FileNotFoundException will be thrown.
	public void Load() throws FileNotFoundException{
		
	    
	    try {
	    	Scanner sc = new Scanner(System.in);
	    	System.out.println();
	    	
	    	String S = "C:/Users/Gidi/Desktop/Programming/Ex4 jars//";
	    	
	    	System.out.print("Enter the name of the file: ");
			String fn = sc.next();
			S += fn + ".txt";
			sc.close();
			
		      File myObj = new File(S);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        System.out.println(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }

	  }
	
	public void mouseClicked(Point2D p) {
		System.out.println("Mode: " + _mode + "  " + p);

		if (_mode.equals("Move")) {
			if (_p1 == null) {
				_p1 = new Point2D(p);
			} else {
				_p1 = new Point2D(p.x() - _p1.x(), p.y() - _p1.y());
				move();
				_p1 = null;
			}
		}

		// call the function scale 90% with all selected shapes
		if (_mode.equals("Scale_90%")) {
			for (int i = 0; i < _shapes.size(); i++) {
				if (_shapes.get(i).isSelected())
					_shapes.get(i).getShape().scale(p, 0.9);

			}
		}
		// call the function scale 110% with all selected shapes
		if (_mode.equals("Scale_110%")) {
			for (int i = 0; i < _shapes.size(); i++) {
				if (_shapes.get(i).isSelected())
					_shapes.get(i).getShape().scale(p, 1.1);

			}
		}
		// call the function rotate with all selected shapes by getting the degree and
		// rotating
		// each one with this degree
		if (_mode.equals("Rotate")) {
			if (_p1 == null) {
				_p1 = new Point2D(p);
			} else {
				for (int i = 0; i < _shapes.size(); i++) {
					GUI_Shapeable s = _shapes.get(i);
					GeoShapeable g = s.getShape();
					if (g != null && s.isSelected()) {
						double angle = _p1.calcAngel(p, _p1);
						g.rotate(p,angle);
					}
				}
				_p1 = null;
			}
			_gs = null;

		}
		/*
		 * for each shape create a sets _p1 as p and if is already set creates the shape
		 * with _p1 and the current click then resetting all to null
		 */
		if (_mode.equals("Circle")) {
			if (_gs == null) {
				_p1 = new Point2D(p);
			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_gs.setTag(counter);
				counter++;
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}
		if (_mode.equals("Rect")) {
			if (_gs == null) {
				_p1 = new Point2D(p);
			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_gs.setTag(counter);
				counter++;
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}

		if (_mode.equals("Segment")) {
			if (_gs == null) {
				_p1 = new Point2D(p);
			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_gs.setTag(counter);
				counter++;
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}

		if (_mode.equals("Triangle")) {
			if (_gs == null) {
				_p1 = new Point2D(p);
			} else if (_p2 == null) {
				_p2 = new Point2D(p);
			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_gs.setTag(counter);
				counter++;
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				_p2 = null;

			}
		}

		if (_mode.equals("Polygon")) {
			if (_gs == null) {
				_p1 = new Point2D(p);
				PPoints = new ArrayList<>();
				PPoints.add(_p1);
			} else {
				PPoints.add(p);
			}
		}

		// calls for the right function

		if (_mode.equals("Point")) {
			select(p);
		}
		if (_mode.equals("Copy")) {
			if (_p1 == null) {
				_p1 = new Point2D(p);
			} else {
				copy(_p1,p);
			}
		}

		drawShapes();
	}

	/*
	 * makes a copy of each selected shape and puts it into a GUIShape constructor
	 * then adds it to the _shapes.
	 */
	private void copy(Point2D p1,Point2D p2) {
		// TODO Auto-generated method stub
		int size = _shapes.size();
		int c = 0;
		Point2D diff = new Point2D(p2.x()-p1.x() , p2.y() - p1.y());
		for (int i = 0; i < size; i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if (g != null && s.isSelected()) {
				GUI_Shapeable new_gs = new GUIShape(s.getShape(), s.isFilled(), s.getColor(), counter);
				counter++;
				_shapes.add(new_gs);
				System.out.println(_shapes.get(i).getTag());
				_shapes.get(size + c).getShape().move(diff);
				System.out.println(_shapes.get(size+c).getTag());

				c++;
			}
		}
	}
	
	private void loadFile(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a file to load");

        String currentWorkingDirectory = System.getProperty("user.dir");
        File projectDirectory = new File(currentWorkingDirectory);
        fileChooser.setCurrentDirectory(projectDirectory);

        int userSelection = fileChooser.showSaveDialog(fileChooser);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();
            _shapes.removeAll();
            _shapes.load(fileToLoad.getAbsolutePath());
            
        }
	}

	/*
	 * using removeElementAtI for all selected shapes
	 */
	private void remove() {
		// TODO Auto-generated method stub
		int size = _shapes.size();
		for (int i = size - 1; i >= 0; i--) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if (g != null && s.isSelected()) {
				_shapes.removeElementAt(i);
			}
		}
	}

	private void select(Point2D p) {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if (g != null && g.contains(p)) {
				s.setSelected(!s.isSelected());
			}
		}
	}

	/*
	 * this function sets all shapes whether selected or not as selected == true
	 */
	private void selectAll() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if (g != null) {
				s.setSelected(true);
			}
		}
	}

	/*
	 * this function sets all shapes that are not selected as selected == true and
	 * those who are selected setting as false
	 */
	private void anti() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if (g != null)
				if (s.isSelected()) {
					s.setSelected(false);
				} else
					s.setSelected(true);
		}
	}

	/*
	 * this function setting all shapes whether selected or not as selected == false
	 */
	private void none() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if (g != null) {
				s.setSelected(false);
			}
		}
	}

	/*
	 * this function moves each shape with the given points (vec)
	 */
	private void move() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if (s.isSelected() && g != null) {
				g.move(_p1);
			}
		}
	}

	public void mouseRightClicked(Point2D p) {
		System.out.println("right click!");
		if (_shapes.size() != 0) {
			System.out.println(_shapes.getBoundingBox());
		}
		// if right click end polygon by setting the _gs to null and the shape and then
		// creating the polygon, and resetting all the points
		if (_mode.equals("Polygon") && _gs != null && _gs.getShape() != null) {
			_gs.setColor(_color);
			_gs.setFilled(_fill);
			_gs.setTag(counter);
			counter++;
			Polygon2D poly = new Polygon2D(PPoints);
			_gs = new GUIShape(poly, _fill, _color, 0);
			_shapes.add(_gs);
			_gs = null;
			_p1 = null;
			PPoints.clear();
			drawShapes();
		}
	}

	public void mouseMoved(MouseEvent e) {
		if (_p1 != null) {
			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			GeoShapeable gs = null;
			// System.out.println("M: "+x1+","+y1);
			Point2D p = new Point2D(x1, y1);
			if (_mode.equals("Circle")) {
				double r = _p1.distance(p);
				gs = new Circle2D(_p1, r);
			}

			if (_mode.equals("Rect")) {
				gs = new Rect2D(_p1, p);
			}

			if (_mode.equals("Segment")) {
				gs = new Segment2D(_p1, p);
			}
			if (_mode.equals("Triangle")) {
				if (_p2 == null) {
					gs = new Triangle2D(_p1, p, p);
				} else {
					gs = new Triangle2D(_p1, _p2, p);
				}
			}

			if (_mode.equals("Polygon")) {
				ArrayList<Point2D> copy = new ArrayList<Point2D>();
				copy.addAll(PPoints);
				copy.add(p);
				gs = new Polygon2D(copy);
			}

			_gs = new GUIShape(gs, false, Color.pink, counter);
			counter++;
			drawShapes();
		}
	}

	@Override
	public ShapeCollectionable getShape_Collection() {
		// TODO Auto-generated method stub
		return this._shapes;
	}

	@Override
	public void show() {
		show(Ex4_Const.DIM_SIZE);
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String ans = "";
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			ans += s.toString() + "\n";
		}
		return ans;
	}
}