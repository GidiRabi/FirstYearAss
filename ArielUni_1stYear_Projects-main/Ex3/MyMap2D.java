package Ex3;

import java.util.ArrayList;

/**
 * This class implements the Map2D interface.
 * You should change (implement) this class as part of Ex3.
 *
 * @authors - Gal ben ami - 206374498, Elad avnet - 313373367.
 */

public class MyMap2D implements Map2D {
    private int[][] _map;

    public MyMap2D(int w, int h) {init(w, h);}

    public MyMap2D(int size) {this(size, size);}

    public MyMap2D(int[][] data) {
        this(data.length, data[0].length);
        init(data);

    }

    @Override
    public void init(int w, int h) {_map = new int[w][h];}

    @Override
    public void init(int[][] arr) {
        init(arr.length, arr[0].length);
        for (int x = 0; x < this.getWidth() && x < arr.length; x++) {
            for (int y = 0; y < this.getHeight() && y < arr[0].length; y++) {
                this.setPixel(x, y, arr[x][y]);
            }
        }
    }

    @Override
    public int getWidth() {
        return _map.length;
    }

    @Override
    public int getHeight() {
        return _map[0].length;
    }

    @Override
    public int getPixel(int x, int y) {return _map[x][y];}

    @Override
    public int getPixel(Point2D p) {return this.getPixel(p.ix(), p.iy());}

    public void setPixel(int x, int y, int v) {_map[x][y] = v;}

    public void setPixel(Point2D p, int v) {setPixel(p.ix(), p.iy(), v);}

    @Override

   /*
     given p1 as start point and p2 as end point and a v integer representing the colour,
     The method first determines whether the line has a greater slope in the x or y direction,
     and then calls either the drawSegmentLow or drawSegmentHigh method to draw the line.
    */

    public void drawSegment(Point2D p1, Point2D p2, int v) {
        // TODO Auto-generated method stub

        int x0 = p1.ix();
        int y0 = p1.iy();
        int x1 = p2.ix();
        int y1 = p2.iy();
        if (Math.abs(y1 - y0) < Math.abs(x1 - x0)) {
            if(x0 >x1) {
                drawSegmentLow(x1, y1, x0, y0, v);

            } else {
                drawSegmentLow(x0, y0, x1, y1, v);
            }
        } else {
            if (y0 > y1) {
                drawSegmentHigh(x1, y1, x0, y0, v);
            } else {
                drawSegmentHigh(x0, y0, x1, y1, v);
            }
        }
    }

    /*
    This is a private method used by the drawSegment method to draw a line segment with a slope that is greater than 1.
    It takes the same arguments as drawSegment, including the two endpoints p1 and p2 and the color of the line v.
    The method uses a line drawing algorithm to iterate over the y values from the starting point y0 to the ending point y1,
    setting pixels along the line with the specified color v, using setPixel.
     */

    private void drawSegmentHigh(int x0, int y0, int x1, int y1, int v) {
        int dx = x1 - x0;
        int dy = y1 - y0;
        int xi = 1;
        if (dx < 0) {
            xi = -1;
            dx = -dx;
        }

        int D = 2 * dx - dy;
        int x = x0;
        for (int y = y0; y <= y1; y++) {
            this.setPixel(x, y, v);
            if (D > 0) {
                x = x + xi;
                D = D - 2 * dy;
            }
            D = D + 2 * dx;
        }
    }

    /*
    same as drawSegmentHigh, but this function iterates over the x values.
     */

    private void drawSegmentLow(int x0, int y0, int x1, int y1, int v) {
        int dx = x1 - x0;
        int dy = y1 - y0;
        int yi = 1;
        if (dy < 0) {
            yi = -1;
            dy = -dy;
        }

        int D = 2 * dy - dx;
        int y = y0;
        for (int x = x0; x <= x1; x++) {
            this.setPixel(x, y, v);
            if (D > 0) {
                y = y + yi;
                D = D - 2 * dx;
            }
            D = D + 2 * dy;
        }
    }

    @Override

    /*
     given the coordinates of two opposing corners p1 and p2 and an integer col representing the color of the rectangle.
     The method first determines the minimum and maximum x and y coordinates of the rectangle,
     and then iterates over the range of x and y values to set each pixel within the rectangle to the specified color col,
     using setPixel.
     */

    public void drawRect(Point2D p1, Point2D p2, int col) {
        int min_x = Math.min(p1.ix(), p2.ix());
        int min_y = Math.min(p1.iy(), p2.iy());
        int max_x = Math.max(p1.ix(), p2.ix());
        int max_y = Math.max(p1.iy(), p2.iy());

        for (int i = min_x; i <= max_x; i++) {
            for (int j = min_y; j <= max_y; j++) {
                this.setPixel(i, j, col);
            }
        }
    }

    /*
    This is  method draws a circle on the map given the center point p and radius rad of the circle,
    and a col integer representing the color we want to paint the circle with.
    The method first iterates over the x and y values for every pixel on the map
    and then uses the distance method to calculate the distance between each pixel and the center of the circle.
    If this distance is <= than the rounded radius value of the circle,
    the pixel is set to the specified color "col" using setPixel.
     */

    @Override
    public void drawCircle(Point2D p, double rad, int col) {
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                Point2D p1 = new Point2D(x, y);
                if (p.distance(p1) <= (int)Math.round(rad)) {
                    this.setPixel(x, y, col);
                }
            }
        }
    }



/*
This method is used to fill a connected area in the map,
it gets 'p' as starting point and new_v as the color to fill.
The function first gets the original color of the starting point and initializes a counter to zero.
It then creates an ArrayList to hold the points of the area that need to be filled and adds the starting point to the list.
The function then enters a While loop that continues until the list is empty.

Inside the loop, the function removes the first point in the list and then checks the four adjacent points (up, down, left, right),
to see if they are within the bounds of the map and have the same color as the starting point.
If both conditions are met, the point is added to the list and its color is set to the new fill value.
Otherwise, the point is skipped.
The counter is also incremented to keep track of the number of points that have been filled.

The function then returns the counter, which gives the total number of points that were filled.
 */
    @Override
    public int fill(Point2D p, int new_v) {
        // TODO Auto-generated method stub

        int original_color = this.getPixel(p.ix(), p.iy());
        int counter = 0;

        ArrayList<Point2D> holdNeighbours = new ArrayList<>();
        holdNeighbours.add(p);
        while (!holdNeighbours.isEmpty()) {
            Point2D currentPoint = holdNeighbours.remove(0);
            int x = currentPoint.ix();
            int y = currentPoint.iy();
            if (this.boundCheck(x, y + 1) && this.getPixel(x, y + 1) == original_color) {
                holdNeighbours.add(new Point2D(x, y + 1));
                this.setPixel(x, y + 1, new_v);
                counter++;

            }
            if (this.boundCheck(x, y - 1) && this.getPixel(x, y - 1) == original_color) {
                holdNeighbours.add(new Point2D(x, y - 1));
                this.setPixel(x, y - 1, new_v);
                counter++;
            }
            if (this.boundCheck(x + 1, y) && this.getPixel(x + 1, y) == original_color) {
                holdNeighbours.add(new Point2D(x + 1, y));
                this.setPixel(x + 1, y, new_v);
                counter++;
            }
            if (this.boundCheck(x - 1, y) && this.getPixel(x - 1, y) == original_color) {
                holdNeighbours.add(new Point2D(x - 1, y));
                this.setPixel(x - 1, y, new_v);
                counter++;
            }

        }
        return counter;
    }

    /*
    This method checks if the input coordinates x and y are within the bounds of the map.
    It returns true if the coordinates are valid, false otherwise.
     */

    private boolean boundCheck(int x, int y ) {
        return y<this.getHeight() && x>=0 && y>=0 && x<this.getWidth() ;
    }



    /*
    This method used to change the color of the selected x and y coordinates of the point, and change its color.
     */
    @Override
    public int fill(int x, int y, int new_v) {
        // TODO Auto-generated method stub
        Point2D p = new Point2D(x, y);
        fill(p, new_v);
        return 0;
    }

    /*
    The function first initializes a 2D int array = map, based on current values on the original map - if a pixel on the map
    is WHITE it gets a value of -1, else, it gets a value of -2.
    -1 means it is ok to travel through it, -2 means it cannot be stepped on.

     It then performs a breadth-first search (BFS) algorithm on the grid,
     starting at given p1 and storing the distances of the reachable white pixels from p1 in the map array.

    The function then determines the shortest path between given p1 and given p2 by creating an array called path
    and adding the coordinates of the pixels along the path to the array,
    starting at p2 and working backwards towards p1. The function does this by repeatedly checking the four adjacent pixels
    to the current ending point to see if they have a distance from p1 that is one less than the current length of the path.
    If an adjacent pixel meets this criterion, it is added to the path array and becomes the new ending point.

    When the starting point is reached, the path array will contain the shortest path from p1 to p2 as an array of Point2D objects.
    The function then returns the path array.
    in case the p2 destination is unreachable from p1 the method returns null.

     */

    @Override
    public Point2D[] shortestPath(Point2D p1, Point2D p2) {
        // TODO Auto-generated method stub
        int[][] map = new int[this.getHeight()][this.getWidth()];

        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight(); j++) {
                if (this.getPixel(i, j) == WHITE) {
                    map[i][j] = -1;
                } else {
                    map[i][j] = -2;
                }
            }
        }

        map[p1.ix()][p1.iy()] = 0;
        ArrayList<Point2D> holdNeighbours = new ArrayList<>();
        holdNeighbours.add(p1);
        while (!holdNeighbours.isEmpty()) {
            Point2D currentPoint = holdNeighbours.remove(0);
            int x = currentPoint.ix();
            int y = currentPoint.iy();
            if (this.boundCheck(x, y + 1) && map[x][y + 1] == -1) {
                holdNeighbours.add(new Point2D(x, y + 1));
                map[x][y + 1] = map[x][y] + 1;
            }
            if (this.boundCheck(x, y - 1) && map[x][y - 1] == -1) {
                holdNeighbours.add(new Point2D(x, y - 1));
                map[x][y - 1] = map[x][y] + 1;
            }
            if (this.boundCheck(x + 1, y) && map[x + 1][y] == -1) {
                holdNeighbours.add(new Point2D(x + 1, y));
                map[x + 1][y] = map[x][y] + 1;
            }
            if (this.boundCheck(x - 1, y) && map[x - 1][y] == -1) {
                holdNeighbours.add(new Point2D(x - 1, y));
                map[x - 1][y] = map[x][y] + 1;
            }
        }
        // if the destination is unreachable
        if (map[p2.ix()][p2.iy()] == -1) {
            return null;
        }
        int length = map[p2.ix()][p2.iy()];
        Point2D[] path = new Point2D[length + 1];
        path[length] = p2;
        this.setPixel(p2.ix(), p2.iy(), Ex3.getColor()); // draw the last point
        int x = p2.ix();
        int y = p2.iy();
        while (length > 0) {
            if (this.boundCheck(x + 1, y) && map[x + 1][y] == length - 1) {
                path[length - 1] = new Point2D(x + 1, y);
                length--;
                x++;
            } else if (this.boundCheck(x - 1, y) && map[x - 1][y] == length - 1) {
                path[length - 1] = new Point2D(x - 1, y);
                length--;
                x--;
            } else if (this.boundCheck(x, y + 1) && map[x][y + 1] == length - 1) {
                path[length - 1] = new Point2D(x, y + 1);
                length--;
                y++;
            } else if (this.boundCheck(x, y - 1) && map[x][y - 1] == length - 1) {
                path[length - 1] = new Point2D(x, y - 1);
                length--;
                y--;
            }
            this.setPixel(x, y, Ex3.getColor()); // draw the path
        }
        System.out.println("ShortestPath: " +"["+p1.ix()+"]" + "["+p1.iy()+"]" + " -> " + "["+p2.ix()+"]" + "["+p2.iy()+"]"+ "length: " + (path.length-1));
        for (int i = 0; i < path.length; i++) {
            System.out.println(i +")" +"  "+"["+path[i].ix()+ "," + path[i].iy()+"]");
        }

        return path;
    }


/*
This method returns the number of steps it took to get from the starting point
to the end point, by calculating the shortestPath array's length.
 */
    @Override
    public int shortestPathDist(Point2D p1, Point2D p2) {
        // TODO Auto-generated method stub
        System.out.println(shortestPath(p1, p2).length);
        return shortestPath(p1, p2).length;
    }

    /*
    This function implements "Conway's Game of Life".
    It creates a new array to store next generation and assigns a value of 0
    to each point if the current point in the grid is white,
    or 1 if the pixel is black. It then iterates through each point in the map and counts the number of black cells
    in the 8 adjacent cells. Based on the rules of the game, it sets the value of the current cell to white
    if it is currently black and has less than 2 or more than 3 black neighbors, or to black if it is currently
    white and has exactly 3 black neighbors.
     */

    @Override
    public void nextGenGol() {
        // TODO Auto-generated method stub
        // We need to create a new array to store the next generation
        int[][] map = new int[this.getHeight()][this.getWidth()];

        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight(); j++) {
                if (this.getPixel(i, j) == WHITE) {
                    map[i][j] = 0;
                } else {
                    map[i][j] = 1;
                }
            }
        }

        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                int count = 0;
                // We check all the 8 neighbors around the current cell
                // and count the number of black cells
                if (this.boundCheck(x, y + 1) && map[x][y + 1] == 1) {
                    count++;
                }
                if (this.boundCheck(x, y - 1) && map[x][y - 1] == 1) {
                    count++;
                }
                if (this.boundCheck(x + 1, y) && map[x + 1][y] == 1) {
                    count++;
                }
                if (this.boundCheck(x - 1, y) && map[x - 1][y] == 1) {
                    count++;
                }
                if (this.boundCheck(x + 1, y + 1) && map[x + 1][y + 1] == 1) {
                    count++;
                }
                if (this.boundCheck(x - 1, y - 1) && map[x - 1][y - 1] == 1) {
                    count++;
                }
                if (this.boundCheck(x + 1, y - 1) && map[x + 1][y - 1] == 1) {
                    count++;
                }
                if (this.boundCheck(x - 1, y + 1) && map[x - 1][y + 1] == 1) {
                    count++;
                }



                if (map[x][y] == 1 && (count < 2 || count > 3)) {
                    this.setPixel(x, y, WHITE);
                } else if (map[x][y] == 0 && count == 3) {
                    this.setPixel(x, y, BLACK);
                }
            }
        }
    }


    // this fill used to clear and initialize the map.
    @Override
    public void fill(int c) {
        for (int x = 0; x < this.getWidth(); x++) {

            for (int y = 0; y < this.getHeight(); y++) {
                this.setPixel(x, y, c);
            }
        }
    }
}
