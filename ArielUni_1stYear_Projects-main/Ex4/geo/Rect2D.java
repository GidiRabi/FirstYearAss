package Exe.Ex4.geo;

/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect2D implements GeoShapeable {

	private Point2D p1;
	private Point2D p2;
	private Point2D p3;
	private Point2D p4;

	public Rect2D(Point2D p1, Point2D p2) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = new Point2D(p1.x(), p2.y());
		this.p4 = new Point2D(p2.x(), p1.y());
	}

	@Override
	public boolean contains(Point2D ot) {
		double max_x = Math.max(p1.x(),p2.x());
		double min_x = Math.min(p1.x(),p2.x());
		double max_y = Math.max(p1.y(),p2.y());
		double min_y = Math.min(p1.y(),p2.y());

		if (ot.x() <= max_x && ot.x() >= min_x && ot.y() >= min_y && ot.y() <= max_y){
			return true;
		}
		return false;
	}

	@Override
	public double area() {
		double max_x = Math.max(p1.x(),p2.x());
		double min_x = Math.min(p1.x(),p2.x());
		double max_y = Math.max(p1.y(),p2.y());
		double min_y = Math.min(p1.y(),p2.y());
		double height = max_y - min_y;
		double width = max_x - min_x;

		return height * width;
	}

	@Override
	public double perimeter() {
		double max_x = Math.max(p1.x(),p2.x());
		double min_x = Math.min(p1.x(),p2.x());
		double max_y = Math.max(p1.y(),p2.y());
		double min_y = Math.min(p1.y(),p2.y());
		double height = max_y - min_y;
		double width = max_x - min_x;

		return (height + width)*2;
	}

	@Override
	public void move(Point2D vec) {
		p1.move(vec);
		p2.move(vec);
		p3.move(vec);
		p4.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		return new Rect2D(p1,p2);
	}

	@Override
	public void scale(Point2D center, double ratio) {

		this.p1.scale(center,ratio);
		this.p2.scale(center,ratio);
		this.p3.scale(center,ratio);
		this.p4.scale(center,ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		for (int i = 0; i < getPoints().length; i++) {
			this.getPoints()[i].rotate(this.getPoints()[i],angleDegrees);
		}
	}

	@Override
	public Point2D[] getPoints() {
		Point2D[] rectPoints = new Point2D[4];
		rectPoints[0] = new Point2D(p1);
		rectPoints[1] = new Point2D(p2);
		rectPoints[2] = new Point2D(p1.x(), p2.y());
		rectPoints[3] = new Point2D(p2.x(), p1.y());
		return rectPoints;
	}

	@Override
	public String toString() {
		return p1.toString() + " ," + p2.toString();

	}

	public static void main(String[] args) {
		Point2D p1 = new Point2D(1,4);
		Point2D p2 = new Point2D(4,1);
		Rect2D rect = new Rect2D(p1,p2);
		Point2D ptest = new Point2D( 4,1);
		boolean ans = rect.contains(ptest);
		double rectArea = rect.area();
		System.out.println(ans);
		System.out.println(rectArea);
		System.out.println(rect.perimeter());
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(!(obj instanceof Rect2D)) return false;
		Rect2D r = (Rect2D) obj;
		return p1.equals(r.p1) && p2.equals(r.p2);
	}
}
