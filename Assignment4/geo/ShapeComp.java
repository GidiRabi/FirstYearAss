package Exe.Ex4.geo;

import java.util.Arrays;
import java.util.Comparator;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUI_Shapeable;

/**
 * This class represents a Comparator over GUI_Shapes - 
 * as a linear order over GUI_Shapes.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeComp implements Comparator<GUI_Shapeable>{

	public static final Comparator<GUI_Shapeable> CompByToString = new ShapeComp(Ex4_Const.Sort_By_toString);
	public static final Comparator<GUI_Shapeable> CompByAntiToString = new ShapeComp(Ex4_Const.Sort_By_Anti_toString);
	public static final Comparator<GUI_Shapeable> CompByArea = new ShapeComp(Ex4_Const.Sort_By_Area);
	public static final Comparator<GUI_Shapeable> CompByAntiArea = new ShapeComp(Ex4_Const.Sort_By_Anti_Area);
	public static final Comparator<GUI_Shapeable> CompByPerimeter = new ShapeComp(Ex4_Const.Sort_By_Perimeter);
	public static final Comparator<GUI_Shapeable> CompByAntiPerimeter = new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter);
	public static final Comparator<GUI_Shapeable> CompByTag = new ShapeComp(Ex4_Const.Sort_By_Tag);
	public static final Comparator<GUI_Shapeable> CompByAntiTag = new ShapeComp(Ex4_Const.Sort_By_Anti_Tag);
	
	private int _flag;
	public ShapeComp(int flag) {
		_flag = flag;
	}


	/*
			o1=o2 -> ans == 0
			o1>o2 -> ans >= 1
			o1<o2 -> ans <= -1
	 */
	@Override
	public int compare(GUI_Shapeable o1, GUI_Shapeable o2) {
		int ans=0;
		if(_flag == Ex4_Const.Sort_By_toString) {
			ans = o1.toString().compareTo(o2.toString());
		}
		if(_flag == Ex4_Const.Sort_By_Anti_toString) {
			ans = o2.toString().compareTo(o1.toString());
		}
		if(_flag == Ex4_Const.Sort_By_Tag) {
			ans = o1.getTag() - o2.getTag();
		}
		if(_flag == Ex4_Const.Sort_By_Anti_Tag) {
			ans = o2.getTag() - o1.getTag();
		}
		if(_flag == Ex4_Const.Sort_By_Area) {
			ans = (int)(o1.getShape().area() - o2.getShape().area());
		}
		if(_flag == Ex4_Const.Sort_By_Anti_Area) {
			ans = (int)(o2.getShape().area() - o1.getShape().area());
		}
		if(_flag == Ex4_Const.Sort_By_Perimeter) {
			ans = (int)(o1.getShape().perimeter() - o2.getShape().perimeter());
		}
		if(_flag == Ex4_Const.Sort_By_Anti_Perimeter) {
			ans = (int)(o2.getShape().perimeter() - o1.getShape().perimeter());
		}
		return ans;
	}

}
