package algorithm;

/**
 * @Description: 有一个由int组成的数组，计算任意两个元素相加=目标值，并返回元素的索引，相同的元素不可用两次
 * @Author: HanZhonghua
 * @Date: Create in 下午8:18 18-10-31
 */
public class Solution {

    public int[] twoSum(int[] sum, int target){
        int result[] = {};
        int length = sum.length;
        if(length<2){
            return result;
        }
        for(int i=0 ; i<length; i++){
            for(int j=0 ; j<length ; j++){
                if(sum[i] + sum[j] == target && i != j){
                    int r[] = {i,j};
                    result = r;
                    return result;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int a[] = {1,2,4,5,6,7};
        int[] ints = s.twoSum(a, 5);
        for (int i : ints){
            System.out.print(i);
        }
    }
}
