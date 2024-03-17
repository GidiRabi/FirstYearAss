package Exe.Ex4.geo;

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
	//////////add your code below ///////////
	
public static Comparator<GUI_Shapeable> CompByToString = new ShapeComp(Ex4_Const.Sort_By_toString); 
public static Comparator<GUI_Shapeable> CompByAntiArea = new ShapeComp(Ex4_Const.Sort_By_Anti_Area); 
public static Comparator<GUI_Shapeable> CompByArea = new ShapeComp(Ex4_Const.Sort_By_Area); 
public static Comparator<GUI_Shapeable> CompByAntiToString = new ShapeComp(Ex4_Const.Sort_By_Anti_toString); 
public static Comparator<GUI_Shapeable> CompByPerimeter = new ShapeComp(Ex4_Const.Sort_By_Perimeter); 
public static Comparator<GUI_Shapeable> CompByTag = new ShapeComp(Ex4_Const.Sort_By_Tag); 
public static Comparator<GUI_Shapeable> CompByAntiTag = new ShapeComp(Ex4_Const.Sort_By_Anti_Tag); 
public static Comparator<GUI_Shapeable> CompByAntiPerimeter = new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter); 


		
	private int _flag;
	public ShapeComp(int flag) {
		_flag = flag;
		
	}
	/*
	 * this function gets 2 GUI_shapeable and them compares them given the flag the user chose
	 * then returns 1 if o1 has bigger value then o2 else -1
	 * 0 if theyre equals
	 * 
	 */
	@Override
	public int compare(GUI_Shapeable o1, GUI_Shapeable o2) {
		int ans=0;
		
		if(_flag == Ex4_Const.Sort_By_toString) {
			ans = o1.toString().compareTo(o2.toString());
		}
		//////////add your code below ///////////
		if(_flag == Ex4_Const.Sort_By_Anti_toString) {
			ans = o2.toString().compareTo(o1.toString());
		}
		
		if(_flag == Ex4_Const.Sort_By_Area) {
			
			if(o1.getShape().area() > o2.getShape().area())
				ans = 1;
			if(o1.getShape().area() == o2.getShape().area())
				ans = 0;
			if(o1.getShape().area() < o2.getShape().area())
				ans = -1;
			
		}
		if(_flag == Ex4_Const.Sort_By_Anti_Area) {
			
			if(o1.getShape().area() > o2.getShape().area())
				ans = -1;
			if(o1.getShape().area() == o2.getShape().area())
				ans = 0;
			if(o1.getShape().area() < o2.getShape().area())
				ans = 1;
		}
		if(_flag == Ex4_Const.Sort_By_Perimeter) {
			if(o1.getShape().perimeter() > o2.getShape().perimeter())
				ans = 1;
			if(o1.getShape().perimeter() == o2.getShape().perimeter())
				ans = 0;
			if(o1.getShape().perimeter() < o2.getShape().perimeter())
				ans = -1;
			
		}
		if(_flag == Ex4_Const.Sort_By_Anti_Perimeter) {
			if(o1.getShape().perimeter() > o2.getShape().perimeter())
				ans = -1;
			if(o1.getShape().perimeter() == o2.getShape().perimeter())
				ans = 0;
			if(o1.getShape().perimeter() < o2.getShape().perimeter())
				ans = 1;
		}
		if(_flag == Ex4_Const.Sort_By_Tag) {
			if(o1.getTag() > o2.getTag())
				ans = 1;
			if(o1.getTag() == o2.getTag())
				ans = 0;
			if(o1.getTag() < o2.getTag())
				ans = -1;
		}
		if(_flag == Ex4_Const.Sort_By_Anti_Tag) {
			if(o1.getTag() > o2.getTag())
				ans = -1;
			if(o1.getTag() == o2.getTag())
				ans = 0;
			if(o1.getTag() < o2.getTag())
				ans = 1;
		}

		//////////////////////////////////////////
		return ans;
	}
	
	
	
	

}