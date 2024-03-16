package Ex3;

import java.awt.Color;

/**
 * This class is a simple "inter-layer" connecting (aka simplifing) the
 * StdDraw_Ex3 with the Map2D interface.
 * Written for 101 java course it uses simple static functions to allow a
 * "Singleton-like" implementation.
 * You should change this class!
 * @authors - Gal ben ami - 206374498, Elad avnet - 313373367.
 */

public class Ex3 {
	private static  Map2D _map = null;
	private static Color _color = Color.blue;
	private static String _mode = "";
	static Point2D _p0;
	private static double rad;
	public static final int WHITE = Color.WHITE.getRGB();
	public static final int BLACK = Color.BLACK.getRGB();
	public static final int RED = Color.RED.getRGB();
	public static final int GREEN = Color.GREEN.getRGB();
	public static final int BLUE = Color.BLUE.getRGB();
	public static final int YELLOW = Color.YELLOW.getRGB();

	public static int getColor() {
		return _color.getRGB();
	}

	public static void main(String[] args) {
		int dim = 10;  // init matrix (map) 10*10
		init(dim);
	}

	private static void init(int x) {
		StdDraw_Ex3.clear();
		_map = new MyMap2D(x);
		StdDraw_Ex3.setScale(-0.5, _map.getHeight()-0.5);
		StdDraw_Ex3.enableDoubleBuffering();
		_map.fill(WHITE);
		drawArray(_map);
	}

	 public static void drawGrid(Map2D map) {
		 int w = map.getWidth();
		 int h = map.getHeight();
		 for(int i=0;i<w;i++) {
			 StdDraw_Ex3.line(i, 0, i, h);
		 }

		 for(int i=0;i<h;i++) {
			 StdDraw_Ex3.line(0, i, w, i);
		 }

	}
	static public void drawArray(Map2D a) {
		StdDraw_Ex3.clear();
		StdDraw_Ex3.setPenColor(Color.gray);
		drawGrid(_map);
		for(int y=0;y<a.getWidth();y++) {
			for(int x=0;x<a.getHeight();x++) {
				int c = a.getPixel(x, y);
				StdDraw_Ex3.setPenColor(new Color(c));
				drawPixel(x,y);
			}
		}
		StdDraw_Ex3.show();
	}

	public static void actionPerformed(String p) {
		_mode = p;
		if(p.equals("Blue")) {_color = Color.BLUE; }
		if(p.equals("White")) {_color = Color.WHITE; }
		if(p.equals("Black")) {_color = Color.BLACK; }
		if(p.equals("Red")) {_color = Color.RED; }
		if(p.equals("Yellow")) {_color = Color.YELLOW; }
		if(p.equals("Green")) {_color = Color.GREEN; }

		/*
		if the user clicked on Clear, the map resets to be filled with WHITE.
		 */
		if(p.equals("Clear")) {
			_map.fill(WHITE);
		}

		if(p.equals("20x20")) {init(20);}
		if(p.equals("40x40")) {init(40);}
		if(p.equals("80x80")) {init(80);}
		if(p.equals("160x160")) {init(160);}

		drawArray(_map);



	}

	public static void mouseClicked(Point2D p) {
		System.out.println(p);
		int col = _color.getRGB();
		if(_mode.equals("Point")) {
			_map.setPixel(p,col );
		}

		/* if the mode has changed to Rect, first click saved as _p0,
		and the mode set to be Rect2, the next time we will click on a pixel
		drawRect method will be called with the two pixel we clicked on and with a colour set by the user.
		after that the mode resets back to none.

				///// for further information about the "drawRect" method go to MyMap2D class. /////

		 */
		if(_mode.equals("Rect2")) {
			_map.drawRect(p, _p0, col);
			_mode = "none";
		}
		if(_mode.equals("Rect")) {
			_p0 = new Point2D(p);
			_mode = "Rect2";
		}
		/*
		if the mode changed to ShortestPath, the next clicked pixel will be set to _p0
		and the mode will change to ShortestPath2, and after that the shortestPath method
		is called on map with _p0 which is the first pixel that we clicked on, and with p which is the second chosen pixel.
		after that the mode resets back to none.

				///// for further information about the "shortestPath" method go to MyMap2D class. /////

		 */
		if (_mode.equals("ShortestPath2")) {
			//Point2D[] path =
			_map.shortestPath(_p0, p);
			_mode = "none";

		}
		if (_mode.equals("ShortestPath")) {
			_p0 = new Point2D(p);
			_mode = "ShortestPath2";
		}

		/*
		if the mode changed to Segment, the next clicked pixel will be set to _p0
		and the mode will change to Segment2, and after that drawSegment is called on map with _p0 and p which is the
		next clicked pixel and a colour chose by the user.
		after that the mode resets back to none.

				///// for further information about the "drawSegment" method go to MyMap2D class. /////

		 */
		if(_mode.equals("Segment2")) {
			_map.drawSegment(p, _p0, col);
			_mode = "none";
		}
		if(_mode.equals("Segment")) {
			_p0 = new Point2D(p);
			_mode = "Segment2";
		}

		/* if the mode has changed to Circle, first click saved as _p0,
		and the mode set to be Circle2, the next time we will click on a pixel
		rad will get the value of the distance between the first chosen pixel to the second chosen pixel.
		then the method drawCircle be called no map with _p0 as center point and rad as the radius of the circle,
		and with a colour set by the user.
		after that the mode resets back to none.

			 ///// for further information about the "drawCircle" method go to MyMap2D class. /////

		 */

		if(_mode.equals("Circle2")) {
			rad = Math.sqrt((Math.pow((p.ix() - _p0.ix()), 2)) + Math.pow((p.iy() - _p0.iy()), 2));
			_map.drawCircle(_p0,rad,col);
			_mode = "none";
		}
		if(_mode.equals("Circle")) {
			_p0 = new Point2D(p);
			_mode = "Circle2";
		}

		/* if the mode has changed to Fill, fill method is called on map
		with the clicked pixel and a colour selected by the user.
		after that the mode resets back to none.

		    ///// for further information about the "fill" method go to MyMap2D class. /////

		 */

		if(_mode.equals("Fill")) {
			System.out.println("Filled "+_map.fill(p,col)+" points.");
			_mode = "none";
		}

		/*
		if the mode has changed to Gol
		nextGenGol method is being called on map.
		the mode is not changing to none because Gol keeps going as long as the user clicks
		on the map to create a new generation.

				///// for further information about the "nextGenGol" method go to MyMap2D class. /////

		 */
		if(_mode.equals("Gol")) {
			_map.nextGenGol();
		}
		drawArray(_map);


	}

	static private void drawPixel(int x, int y) {
		StdDraw_Ex3.filledCircle(x, y, 0.3);
	}
}
