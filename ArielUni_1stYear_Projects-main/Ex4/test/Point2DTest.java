package Exe.Ex4.test;

import static org.junit.jupiter.api.Assertions.*;

import Exe.Ex4.geo.Point2D;
import org.junit.jupiter.api.Test;

class Point2DTest {

    @Test
    void scale() {
    }


    // test for a regular case of a 90 degree rotation.
    @Test
    void testRotate() {
        Point2D cen = new Point2D(5, 5);
        Point2D point = new Point2D(10, 5);

        point.rotate(cen, 90);

        assertEquals(5, point.getX(), 0.1);
        assertEquals(10, point.getY(), 0.1);
    }

    // test for a special case where the center point provided
    // is the same as the Point2D object

    @Test
    void testRotate360() {
        Point2D cen = new Point2D(5, 5);
        Point2D point = new Point2D(10, 5);

        point.rotate(cen, 360);

        assertEquals(10, point.getX(), 0.1);
        assertEquals(5, point.getY(), 0.1);
    }
}



