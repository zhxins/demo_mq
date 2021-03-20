package com.example.demo.test;

import java.util.Arrays;
import java.util.Vector;

/**
 * Created by admins on 2021/2/25.
 */
public class Test {

	/**
	 * [1,4,2] 3
	 * [2,4,1] 2
	 *
	 * [1,2,3,2,2,2,5,4,2]   2
	 * @return
	 */
	public int MoreThanHalfNum_Solution(int [] array) {

		int md = 0;
		for (int i =0; i<array.length; i++) {
			for (int j=0; j< array.length-1-i; j++) {
				if (array[j] > array[j+1]) {
					md = array[j];
					array[j] = array[j+1];
					array[j+1] = md;
				}
			}
		}
		System.out.println(Arrays.toString(array));
		return 0;
	}


	public static void main(String[] args) {

//		int[] array = new int[]{1, 7, 4, 5, 2 ,3};

//		new Test().MoreThanHalfNum_Solution(array);


		String s1 = "aaa";
		String s2 = "cccx";

		s1 = s1 + s2;
		s2 = s1.substring(0, s1.length()-s2.length());
		s1 = s1.substring(s2.length());

		System.out.println(s1+" - "+s2);

	}
}
