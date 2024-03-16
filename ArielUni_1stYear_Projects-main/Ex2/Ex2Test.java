package Matala2;
import static Matala2.Ex2.ZERO;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**
 * This JUnit class represents a very simple unit testing for Ex2.
 * This class should be improved and generalized significantly.
 * Make sure you add documentations to this Tesing class.
 * @author boaz.ben-moshe
 *
 */

class Ex2Test {
	static double[] po1 = {2, 0, 3, -1, 0}, po2 = {0.1, 0, 1, 0.1, 3};
/*
 * we test each function in a regular case and at a worst case that make sure that the function
 * won't cause a problem at the unusual cases.
 */

	@Test
	void testequals() {
		// good cases
		double[] p1 = new double[]{2, 6, 8, 10};
		double[] p2 = new double[]{2, 6, 8, 10};
		assertTrue(Ex2.equals(p1, p2));

	}

   /*
    * we test the case that may be causing a problem
    */

	@Test
	void testEqualsWorst() {
		double[] p3 = new double[]{};
		double[] p4 = new double[]{0};
		assertFalse(Ex2.equals(p3, p4));

	}

	@Test
	void testF() {
		//good cases

		double fx0 = Ex2.f(po1, 0);
		double fx1 = Ex2.f(po1, 1);
		double fx2 = Ex2.f(po1, 2);
		assertEquals(fx0, 2);
		assertEquals(fx1, 4);
		assertEquals(fx2, 6);
	}

	@Test
	void testFworst(){
		//worst case
		double fx3 = Ex2.f(null, 2);
		assertEquals(0, fx3);
	}


	@Test
	void poly() {
		String fx0 = Ex2.poly(po1);
		assertEquals("-1.0x^3+3.0x^2+2.0", fx0);
		assertNotEquals("-1.0x^3+3.0x^2-2.0", fx0);
	}

	@Test
	void polyWorst() {
		assertEquals("", Ex2.poly(null));
	}

	@Test
	void testRoot() {
		double x12 = Ex2.root(po1, 0, 10, Ex2.EPS);
		assertEquals(x12, 3.1958, Ex2.EPS);
	}


	@Test
	void testRootWorst(){
		double[] p = new double[]{2, 3, 1};
		assertEquals(-9999999, Ex2.root_rec(p, -4, 4, Ex2.EPS));
	}


	@Test
	void testRootRec() {
		double[] p = new double[]{-2, -4, 2, 3};
		double x1 = -1.5;
		double x2 = 1.5;
		double eps = 0.001;
		assertEquals(1.09881591796875, Ex2.root_rec(p, x1, x2, eps));
	}

	@Test
	void testAdd() {

		double[] p12 = Ex2.add(po1, po2);
		double[] minus1 = {-1};
		double[] pp2 = Ex2.mul(po2, minus1);
		double[] p1 = Ex2.add(p12, pp2);
		assertEquals(Ex2.poly(po1), Ex2.poly(p1));

		double[] po1=new double[]{1,2,3};
		double[] po2=new double[]{1,2,3,1};
		assertArrayEquals(new double[]{2,4,6,1}, Ex2.add(po1,po2));
	}

	@Test
	void testMulDoubleArrayDoubleArray() {
		double[] p12 = Ex2.add(po1, po2);
		double dd = Ex2.f(p12, 5);
		assertEquals(dd, 1864.6, Ex2.EPS);
	}

	@Test
	void testDerivativeArrayDoubleArray() {
		double[] p = {1, 2, 3}; // 3X^2+2x+1
		double[] dp1 = {2, 6}; // 6x+2
		double[] dp2 = Ex2.derivative(p);
		assertEquals(dp1[0], dp2[0], Ex2.EPS);
		assertEquals(dp1[1], dp2[1], Ex2.EPS);
		assertEquals(dp1.length, dp2.length);
	}


	@Test
	void testArea() {
		assertEquals(1703.53, Ex2.area(po1, po2, 1, 5, 15), 1);
	}


	@Test
	void testAreaScnd(){
		assertFalse(Math.abs(1650 - Ex2.area(po1, po2, 1, 5, 15)) < 1);
	}


	@Test
	void testFromString() {
		double[] p = {-1.1, 2.3, 3.1}; // 3.1X^2.3x-1.1
		String sp = Ex2.poly(p);
		double[] p1 = Ex2.getPolynomFromString(sp);
		boolean isSame = Ex2.equals(p1, p);
		if (!isSame) {
			fail();
		}
		assertEquals(sp, Ex2.poly(p1));
	}


	@Test
	void testFromStringScnd() {
		assertEquals(ZERO, Ex2.getPolynomFromString(""));
	}


	@Test
	void sameValue() {
		double[] p1 = {-8, -4, 5};
		double[] p2 = {2, 4, -7};
		double x1 = 2;
		double x2 = 0;
		assertEquals(1.30517578125, Ex2.sameValue(p1, p2, x1, x2, Ex2.EPS));
	}

  /*
   * we test a case that should fail
   */
	@Test
	void samevalue2nd() {
		double[] p1 = {-8, -4, 5};
		double[] p2 = {2, 4, 7};
		double x1 = 2;
		double x2 = 0;
	assertEquals(-999999.0 , Ex2.sameValue(p1,p2,x1,x2,Ex2.EPS));

}

	@Test
	void testPolyFromPoints() {
		double[] xx = new double[]{0, 1, 2};
		double[] yy = new double[]{2, 0, 2};
		double[] trueResult = new double[]{2, -4, 2};
		assertArrayEquals(trueResult, Ex2.PolynomFromPoints(xx, yy));
	}


	 /*
	  * second case, this time an unusual input
	  * also gets a right answer
	  */

	@Test
	void testPolyFromPointsWorst() {
		double[] xx2 = new double[]{0, 1, 2};
		double[] yy2 = new double[]{0, 2};
		assertNull(Ex2.PolynomFromPoints(xx2, yy2));
	}
}



