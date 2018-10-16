package effective.reflect;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: HanZhonghua
 * @Date: Create in 下午12:34 18-10-16
 */
public class Test {

    public static void target(int t){
        new Exception("#" + t).printStackTrace();
    }

    public static void main(String[] args) throws Exception {

        // 本地方法调用
        Class<?> test = Class.forName("effective.reflect.Test");
        // 遍历该类中所有公共方法，如果没有就找父类，在没有就抛异常：ClassNotFoundException
        // 返回的是查找结果的一份拷贝，所以热点代码应避免使用返回Method数据的getMethods或getDeclaredMethods
        // 为了减少不必要的堆空间消耗
        Method method = test.getMethod("target", int.class);
        for (int i=0;i<20;i++) {
            method.invoke(null, 0);
        }
    }
}
