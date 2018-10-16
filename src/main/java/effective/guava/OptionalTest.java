package effective.guava;

import com.google.common.base.Optional;

import java.util.Set;

/**
 * @Description: 处理null值，使代码更加简洁，无需在处理各种null判断
 * @Author: HanZhonghua
 * @Date: Create in 下午5:09 18-10-16
 */
public class OptionalTest {

    public void test1() throws Exception{
        Optional<Integer> of = Optional.of(6);
        // 获取一个新的对象，其中包含了空值
        Optional<Object> ab = Optional.absent();
        Optional<Object> ob = Optional.fromNullable(null);
        Optional<Integer> it = Optional.fromNullable(10);
        // 如果Optional<T>不为null，返回true
        if(of.isPresent()){
            System.out.println(of.isPresent());
            // 获取Optional<T>的实例，如果为null，则抛异常IllegalStateException
            System.out.println(of.get());
        }
        if(ab.isPresent()){
            System.out.println(ab.isPresent());
        }
        if(ob.isPresent()){
            System.out.println(ob.isPresent());
        }if(it.isPresent()){
            System.out.println(it.isPresent());
        }
    }

    public void testMethodReturn(){
        Optional<Long> method = method();
        if(method.isPresent()){
            System.out.println(method.get());
        }else {
            System.out.println(method.or(-12L));
        }
        // 获取Optional<T>的实例值，如果为空则返回null
        System.out.println(method.orNull());

        Optional<Long> notNull = methodNotNull();
        if(notNull.isPresent()){
            // 返回一个不可修改的Set
            Set<Long> set = notNull.asSet();
            System.out.println(set.size());
            System.out.println(notNull.get());
        }else {
            System.out.println(notNull.or(-13L));
        }

        // 获取Optional<T>的实例值，如果为空则返回null
        System.out.println(notNull.orNull());
    }

    private Optional<Long> method(){
        return Optional.fromNullable(null);
    }

    private Optional<Long> methodNotNull(){
        return Optional.fromNullable(15L);
    }

    public static void main(String[] args) throws Exception {
        OptionalTest t = new OptionalTest();
        //t.test1();
        t.testMethodReturn();
    }
}
