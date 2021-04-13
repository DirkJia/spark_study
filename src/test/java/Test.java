import java.util.*;

public class Test {

    @org.junit.Test
    public void test1(){

        List<Integer> list = new ArrayList<>();
        list.remove(1);

        System.out.println(new Test().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
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
