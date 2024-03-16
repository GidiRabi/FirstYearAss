package Ex3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyMap2DTest {

    Map2D map;

    /*
        [0, 0, 0, 1],
        [0, 0, 1, 0],
        [0, 1, 0, 0],
        [1, 0, 0, 0]

         this method will run before each test
         in order for us to operate the tests.
         */
    @BeforeEach
    void setUp() {

        map = new MyMap2D(4);
        map.fill(Map2D.WHITE);
    }

    /*
    this test creates a line diagonally using the drawSegment method and paints it with black
    the test need to make sure that only the essential pixels are being painted without any excess pixels.
     */
    @Test
    void drawSegment() {
        map.drawSegment(new Point2D(0, 0), new Point2D(3, 3), Map2D.BLACK);

        for (int i = 0; i < map.getHeight(); i++) {
            assertEquals(Map2D.BLACK, map.getPixel(i, i));
        }
        assertNotEquals(Map2D.BLACK, map.getPixel(0, 1));

    }

    /*
    this test make sure that the drawRect method fills the exact pixels that we chose
    without any excess pixels being painted.
     */
    @Test
    void drawRect() {
        map.drawRect(new Point2D(0, 0), new Point2D(3, 3), Map2D.BLACK);
        //  SHOULD BE
        // [1, 1, 1, 1],
        // [1, 1, 1, 1],
        // [1, 1, 1, 1],
        // [1, 1, 1, 1]
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                assertEquals(Map2D.BLACK, map.getPixel(i, j));
            }
        }

        map.fill(Map2D.WHITE);
        /*
            [0, 0, 0, 0],
            [1, 1, 1, 0],
            [1, 1, 1, 0],
            [1, 1, 1, 0]
         */
        map.drawRect(new Point2D(0, 0), new Point2D(2, 2), Map2D.BLACK);
        for (int i = 0; i < map.getWidth() - 1; i++) {
            for (int j = 0; j < map.getHeight() - 1; j++) {
                assertEquals(Map2D.BLACK, map.getPixel(i, j));
            }
        }
    }

    /*
    This test is checking that our function correctly fills the area inside the radius,
    and also that it is not paint the pixels that are outside the radius.
     */
    @Test
    void drawCircle() {
        map = new MyMap2D(1000);
        map.fill(Map2D.WHITE);
        Point2D p1 = new Point2D(0, 0);
        map.drawCircle(p1, 10, Map2D.BLACK);
        assertEquals(Map2D.BLACK, map.getPixel(p1.ix(), p1.iy()));
        assertEquals(Map2D.BLACK, map.getPixel(p1.ix(), p1.iy()));

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                assertEquals(Map2D.BLACK, map.getPixel(p1.ix() + x, p1.iy() + y));
            }
        }
        assertNotEquals(Map2D.BLACK, map.getPixel(9, 10));
        assertNotEquals(Map2D.BLACK, map.getPixel(10, 9));
    }


    /*
    this test make sure that the Fill method does not paint pixels that are
    painted with a different colour than the colour we want to paint,
    for instance if i choose a white pixel to paint and this specific pixel is surrounded
    by red pixels, the method needs to paint only the white pixel.
     */

    @Test
    void testFill() {
        /*
            [0, 0, 2, 0]
            [2, 2, 2, 0]
            [1, 1, 2, 0]
            [1, 1, 2, 0]
         */

        for (int i = 0; i < map.getHeight(); i++) {
            map.setPixel(2, i, Ex3.BLUE);
        }
        map.setPixel(0, 2, Ex3.BLUE);
        map.setPixel(1, 2, Ex3.BLUE);

        map.fill(new Point2D(0, 0), Ex3.RED);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(Ex3.RED, map.getPixel(i, j));
            }
        }
    }


    /*
    this test checks two different scenarios.
    first we see if the method really creates he shortest path between two points at opposite
    ends, and then we check if the method returns null in case that the destination of the starting point
    is unreachable.
     */
    @Test
    void shortestPath() {
        /*
            [1, 1, 1, 1]
            [1, 0, 0, 0]
            [1, 0, 0, 0]
            [1, 0, 0, 0]
         */
        // Case 1
        map.shortestPath(new Point2D(0, 0), new Point2D(3, 3));
        for (int i = 0; i < map.getHeight(); i++) {
            assertEquals(Ex3.BLUE, map.getPixel(0, i));
        }
        for (int i = 0; i < map.getWidth(); i++) {
            assertEquals(Ex3.BLUE, map.getPixel(i, 3));
        }

        // Case 2 if the endpoint isnt reachable
        map.fill(Map2D.WHITE);
        map.setPixel(2, 0, Ex3.BLACK);
        map.setPixel(2, 1, Ex3.BLACK);
        map.setPixel(2, 2, Ex3.BLACK);
        map.setPixel(2, 3, Ex3.BLACK);

        Point2D[] path = map.shortestPath(new Point2D(0, 0), new Point2D(3, 3));
        assertNull(path);
    }


    /*
    this test checks if shortestPathDist returns the accurate steps it took to get from the
    starting point to the end point.
     */

    @Test
    void shortestPathDist() {
        int shortestResult = map.shortestPath(new Point2D(0, 0), new Point2D(3, 3)).length-1;
        assertEquals(6, shortestResult);
    }

    @Test
    void nextGenGol() {

        /*
        this test checks a worst case of the nextGenGol method to see if it
        really abides by the laws of Conway's game of life.

               BEFORE              AFTER
            [0, 0, 0, 0]        [0, 0, 0, 0]
            [0, 1, 1, 0]        [0, 1, 1, 0]
            [0, 1, 1, 0]        [0, 1, 1, 0]
            [0, 0, 0, 0]        [0, 0, 0, 0]
         */
        map.fill(Map2D.WHITE);
        map.setPixel(1, 1, Ex3.BLACK);
        map.setPixel(1, 2, Ex3.BLACK);
        map.setPixel(2, 1, Ex3.BLACK);
        map.setPixel(2, 2, Ex3.BLACK);
        map.nextGenGol();
        for (int i = 1; i < map.getWidth()-1 ; i++) {
            for (int j = 1; j < map.getHeight()-1 ; j++) {
                assertEquals(Ex3.BLACK, map.getPixel(i,j));
            }

        }

    }

}