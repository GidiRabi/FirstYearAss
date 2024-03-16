package Exe.Ex4.test;

import Exe.Ex4.GUIShape;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ShapeCollectionTest {



    @Test
    void get() {
        ShapeCollectionable collectionTest = new ShapeCollection();
        collectionTest.add(
                new GUIShape(
                        new Rect2D(new Point2D(0, 0), new Point2D(2, 2)),
                        false,
                        Color.BLUE,
                        0
                )
        );
        collectionTest.add(
                new GUIShape(
                        new Segment2D(new Point2D(0, 0), new Point2D(2, 2)),
                        false,
                        Color.BLUE,
                        0
                )
        );
        assertEquals(collectionTest.get(0).getShape(), new Rect2D(new Point2D(0, 0), new Point2D(2, 2)));
    }

    @Test
    void size() {
    }

    @Test
    void removeElementAt() {
    }

    @Test
    void addAt() {
    }

    @Test
    void add() {
    }

    @Test
    void copy() {
    }

    @Test
    void sort() {
    }

    @Test
    void removeAll() {
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }

    @Test
    void getBoundingBox() {
    }
}