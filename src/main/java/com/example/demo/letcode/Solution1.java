package com.example.demo.letcode;

public class Solution1 {

	public static void main(String[] args) {
		int[] nums = new int[]{2,7,11,15};
		int target = 9;
		new Solution1().twoSum(nums, target);
	}


//	nums = [2,7,11,15], target = 9
	public int[] twoSum(int[] nums, int target) {
		int index[] = new int[2];
		for (int i=0; i<nums.length; i++) {
			for (int j=nums.length -1 ; j>i;j--) {
				if (nums[i] + nums[j] == target) {
					System.out.println(i +"," + j);
					index[0] = i;
					index[1] = j;
				}
			}
		}
		return index;
	}


}
