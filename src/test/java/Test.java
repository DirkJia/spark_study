import java.io.UnsupportedEncodingException;
import java.util.*;

public class Test {

    public static void quickSort(int[]arr,int low,int high){
        if (low < high) {
            int middle = getMiddle(arr, low, high);
            quickSort(arr, low, middle - 1);//递归左边
            quickSort(arr, middle + 1, high);//递归右边
        }
    }
    public static int getMiddle(int[] list, int low, int high) {
        int tmp = list[low];
        while (low < high) {
            while (low < high && list[high] >= tmp) {//大于关键字的在右边
                high--;
            }
            list[low] = list[high];//小于关键字则交换至左边
            while (low < high && list[low] <= tmp) {//小于关键字的在左边
                low++;
            }
            list[high] = list[low];//大于关键字则交换至左边
        }
        list[low] = tmp;
        return low;
    }
    @org.junit.Test
    public void test0(){
        int[] data = new int[]{45,28,80,90,50,16,100,10};
        quickSort(data,0,data.length-1);
        System.out.println(data);
    }

    @org.junit.Test
    public void test1() throws UnsupportedEncodingException {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.pop());
        System.out.println(stack.size());

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        System.out.println(queue.poll());
        System.out.println(queue.size());
    }

    public int trap(int[] height) {
        int size = height.length;
        int left =0;
        int right=size-1;
        int leftMax =0;
        int rightMax = 0;
        int result =0;
        while(left<right){
            if(leftMax <= rightMax){
                leftMax = Math.max(leftMax,height[left]);
                result += leftMax - height[left++];
            } else {
                rightMax = Math.max(rightMax,height[right]);
                result+=rightMax-height[right--];
            }
        }
        return result;
    }

    @org.junit.Test
    public void myAtoi() {
        String s = "00000-42a1234";
        char[] chars = s.toCharArray();
        // 存放合法的数字,栈
        List<Integer> stack = new ArrayList<>();
        //符号位
        boolean isMinus = false;
        for(int i=0;i<chars.length;i++){
            char c = chars[i];
            if(c=='-'){
                isMinus=true;
            }
            if(stack.isEmpty()&&(c=='0'|| c==' ')){
                continue;
            }

            if(!Character.isDigit(c)){
                if(isMinus){
                    isMinus = false;
                }
                break;

            }
            if(stack.size() >= 10){
                break;
            }
            stack.add((int)(c-'0'));
        }
        long result = 0;
        int count = stack.size()-1;
        for(int i=0;i<stack.size();i++){
            result = (long)(Math.pow(10,count))*stack.get(i) + result;
            count--;
        }
        if(isMinus){
            result = - result;
        }
        if(result > Integer.MAX_VALUE){
            result = Integer.MAX_VALUE;
        }
        if(result < Integer.MIN_VALUE){
            result = Integer.MIN_VALUE;
        }
        //return (int) result;


    }

    @org.junit.Test
    public void test4(){
StringBuilder stringBuilder;
        Set<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(5);
        set.add(1);
        set.add(3);
        for(int i : set){
            System.out.println(i);
        }
    }


    @org.junit.Test
    public void test5(){



    }

}
