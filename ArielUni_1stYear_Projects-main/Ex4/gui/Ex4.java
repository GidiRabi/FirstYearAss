package Exe.Ex4.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.*;
import org.w3c.dom.css.Rect;

/**
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a
 * "Singleton-like" implementation.
 *
 * @author boaz.benmoshe
 */
public class Ex4 implements Ex4_GUI {
    private ShapeCollectionable _shapes = new ShapeCollection();
    private GUI_Shapeable _gs;
    private Color _color = Color.blue;
    private boolean _fill = false;
    private String _mode = "";
    private Point2D _p1;
    private Point2D _p2; // Added this variable to draw the triangle
//    private ArrayList<Point2D> polyPoints; // Added this variable to draw polygon

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
        // my code from here :
        if (gs instanceof Segment2D) {
            Segment2D c = (Segment2D) gs;
            Point2D[] ps = c.getPoints();
            StdDraw_Ex4.line(ps[0].x(), ps[0].y(), ps[1].x(), ps[1].y());
        }
        if (gs instanceof Rect2D) {
            Rect2D r = (Rect2D) gs;
            Point2D[] ps = r.getPoints();
            double midX = (ps[0].x() + ps[1].x()) / 2;
            double midY = (ps[0].y() + ps[1].y()) / 2;
            double halfWidth = Math.abs(ps[0].x() - ps[1].x()) / 2;
            double halfHeight = Math.abs(ps[0].y() - ps[1].y()) / 2;
            if (isFill) {
                StdDraw_Ex4.filledRectangle(midX, midY, halfWidth, halfHeight);
            } else {
                StdDraw_Ex4.rectangle(midX, midY, halfWidth, halfHeight);
            }
        }
        if (gs instanceof Triangle2D) {
            Triangle2D t = (Triangle2D) gs;
            Point2D[] ps = t.getPoints();
            double[] xs = new double[]{ps[0].x(), ps[1].x(), ps[2].x()};
            double[] ys = new double[]{ps[0].y(), ps[1].y(), ps[2].y()};
            if(isFill){
                StdDraw_Ex4.filledPolygon(xs, ys);
            }else {
                StdDraw_Ex4.polygon(xs, ys);
            }
        }
        if (gs instanceof Polygon2D pol){
            Point2D[] ps = pol.getPoints();
            double[] xs = new double[ps.length];
            double[] ys = new double[ps.length];
            for (int i = 0; i < ps.length; i++) {
                xs[i] = ps[i].x();
                ys[i] = ps[i].y();
            }
            if (isFill){
                StdDraw_Ex4.filledPolygon(xs, ys);
            }else{
                StdDraw_Ex4.polygon(xs, ys);
            }
        }


        // ends here.

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
        if (_mode.equals("All")) {
            selectAll();
        }
        if (_mode.equals("None")) {
            disableAll();
        }

//		Sorts
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


        drawShapes();

    }
    public void mouseClicked(Point2D p) {
        System.out.println("Mode: " + _mode + "  " + p);

        if (_mode.equals("Circle")) {
            if (_gs == null) {
                _p1 = new Point2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
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
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
            }
        }
        if (_mode.equals("Triangle")) {
            if (_gs == null) {
                _p1 = new Point2D(p);
            } else if( _p2 == null){
                _p2 = new Point2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
                _p2 = null;
            }
        }
        if (_mode.equals("Polygon")){
            if (_gs == null) {
                _p1 = new Point2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
            }
        }




        if (_mode.equals("Move")) {
            if (_p1 == null) {
                _p1 = new Point2D(p);
            } else {
                _p1 = new Point2D(p.x() - _p1.x(), p.y() - _p1.y());
                move();
                _p1 = null;
            }
        }

        if (_mode.equals("Point")) {
            select(p);
        }
        if (_mode.equals("Scale_90%")) {
            for (int i = 0; i < _shapes.size(); i++) {
                if (_shapes.get(i).isSelected()) {
                    _shapes.get(i).getShape().scale(p, 0.9);
                }
            }
        }
        if (_mode.equals("Scale_110%")) {
            for (int i = 0; i < _shapes.size(); i++) {
                if (_shapes.get(i).isSelected()) {
                    _shapes.get(i).getShape().scale(p, 1.1);
                }
            }
        }


        drawShapes();
    }
    public void mouseMoved(MouseEvent e) {
        if (_p1 != null) {
            double x1 = StdDraw_Ex4.mouseX();
            double y1 = StdDraw_Ex4.mouseY();
            GeoShapeable gs = null;
//            	System.out.println("M: "+x1+","+y1);
            Point2D p = new Point2D(x1, y1);
            if (_mode.equals("Circle")) {
                double r = _p1.distance(p);
                gs = new Circle2D(_p1, r);
            }
            if (_mode.equals("Segment")) {
                gs = new Segment2D(_p1, p);
            }
            if (_mode.equals("Rect")) {
                gs = new Rect2D(_p1, p);
            }
            if(_mode.equals("Triangle")){
                if (_p2 == null){
                    gs = new Segment2D(_p1,p);
                }else{
                    gs = new Triangle2D(_p1, _p2, p);
                }
            }
            if(_mode.equals("Polygon")){
                if (_p2 == null){
                    gs = new Segment2D(_p1, p);
                }else{
                    gs = new Polygon2D(new ArrayList<>(List.of(_p1, _p2, p)));
                }

            }

            _gs = new GUIShape(gs, false, Color.pink, 0);
            drawShapes();
        }
    }
    private void disableAll() {
        for (int i = 0; i < _shapes.size(); i++) {
            _shapes.get(i).setSelected(false);
        }
    }
    private void selectAll() {
        for (int i = 0; i < _shapes.size(); i++) {
            _shapes.get(i).setSelected(true);
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
