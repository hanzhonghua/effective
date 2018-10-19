package effective.reflect;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: HanZhonghua
 * @Date: Create in 下午12:34 18-10-16
 */
public class Test {

    public static void target(int t){
        /*new Exception("#" + t).printStackTrace();*/
    }

    public static void main(String[] args) throws Exception {

        // 本地方法调用
        Class<?> test = Class.forName("effective.reflect.Test");
        // 遍历该类中所有公共方法，如果没有就找父类，在没有就抛异常：ClassNotFoundException
        // 返回的是查找结果的一份拷贝，所以热点代码应避免使用返回Method数据的getMethods或getDeclaredMethods
        // 为了减少不必要的堆空间消耗，通常在程序缓存Class.forName和Class.getMethod的结果
        // 所以反射的性能开销在反射方法调用
        Method method = test.getMethod("target", int.class);
        Method method2 = test.getMethod("target", int.class);
        System.out.println(method==method2);
        System.out.println(method.equals(method2));
        // 关闭权限检查，每次反射调用时都会检查方法的权限，关闭后可以提升性能
        method.setAccessible(true);
        /*for (int i=0;i<20;i++) {
            method.invoke(null, 0);
        }*/

        long current = System.currentTimeMillis();
        // 调用反射，查询性能对比，invoke方法第二个参数是一个可变参数，传入的128会自动装箱，这也是性能消耗
        for (int i=0;i<2000000000;i++) {
            //method.invoke(null, 0);
            if(i % 100000000==0){
                System.out.println("哈哈哈");
            }
            method.invoke(null, 128);
        }

        /*Object[] objects = new Object[1];
        objects[0] = 128;

        for (int i=0;i<2000000000;i++) {
            //method.invoke(null, 0);
            if(i % 100000000==0){
                System.out.println("哈哈哈");
            }
            method.invoke(null, objects);
        }*/
        long end = System.currentTimeMillis();
        System.out.println(end - current);

    }
}
