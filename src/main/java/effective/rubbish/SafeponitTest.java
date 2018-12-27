package effective.rubbish;

/**
 * @Description:
 * @Author: HanZhonghua
 * @Date: Create in 下午3:07 18-10-25
 */
public class SafeponitTest {

    static double sum = 0;

    public static void foo(){
        for (int i=0;i<0x77777777;i++){
            sum+=Math.sqrt(i);
        }
    }

    public static void main(String[] args) {
        SafeponitTest.foo();
        System.out.println(sum);
    }
}
