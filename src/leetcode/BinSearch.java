package leetcode;

public class BinSearch {

    public int mySqrt(int x) {
        int left = 0, right = x;
        while(left <= right) {
            int mid = left + (right - left)/2;
            long sqrt = (long)mid * mid;
            if(sqrt == x) {
                return mid;
            } else if(sqrt < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    public int findMin(int[] nums) {
        if(nums == null || nums.length==0) return -1;
        if(nums.length == 1) return nums[0];

        int left = 0, right = nums.length-1;
        if(nums[left] < nums[right]) return nums[0];

        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] > nums[right]) {
                left = mid+1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        BinSearch bs = new BinSearch();
        int x = 2147395599;
        int res = bs.mySqrt(x);
        System.out.println(res);

        int[] nums = new int[] {4,5,6,7,0,1,2};
        System.out.println(bs.findMin(nums));
    }
}
