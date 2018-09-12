package effective.fanxing;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * 1.5之后提供的，可以将运行期错误提到编译期
 * 泛型的简单应用
 * 这样该类就可以应用于多个不同的类型，比如String，Integer，这也是泛型的好处，可以节省代码
 * 其中E叫做类型参数，
 */
public class FanXingClass<E> {

    private E[] obj;
    private int size = 0;
    private static final int DEFAULT_LENGTH = 16;

    public FanXingClass(){
        // 注意这种写法编译器是不能证明是安全的，只有自己可以证明，这种写法不能传到客户端或者其他方法，只能方法内部
        // 使用才可以保证安全
        obj = (E[]) new Object[DEFAULT_LENGTH];
    }

    public void print(){
        if(obj.length>0){
            for (E e : obj){
                System.out.print(e);
            }
        }
    }

    public void push(E e){
        if(size>=16){
            throw new RuntimeException("满了");
        }
        obj[size++] = e;
    }

    public void pushAll(Iterable<E> src){
        for(E e : src){
            push(e);
        }
    }

    // 使用E的子类
    public void realPushAll(Iterable<? extends E> src){
        for(E e : src){
            push(e);
        }
    }

    public E pop(){
        if(size==0){
            throw new EmptyStackException();
        }
        obj[size] = null;
        return obj[--size];
    }

    public static void main(String[] args) {
        // 这种写法叫做数据的协变，就是如果A数据B的子类，那么A[]同样属于B[],这种情况是一种绝对不允许的情况
        // 如下代码写，编译期是不会出现任何异常的，只有在运行期才有可能抛出ClassCastException,或者抛出其他异常
        /*Object[] o = new Integer[1];
        o[0]="1";
        System.out.println(o[0]);*/

        /*FanXingClass<String> s = new FanXingClass<>();
        s.push("s");s.push("t");
        s.print();
        System.out.println("---");
        System.out.println(s.pop());
        System.out.println(s.pop());*/

        // 测试通配符类型
        FanXingClass<Number> fx = new FanXingClass<>();
        Iterable<Integer> integers = new ArrayList<Integer>();
        // 以上类型pushAll方法需要的是一个E类型的参数，根据创建的信息，可以确定E是Number，
        //但是我们这里传递的是Integer，虽然Integer是Number的一个子类，但是在使用泛型后
        //这种关系就不存在了，那么如果我想要传递E的子类类型呢？Java提供了这种机制，有限通配符类型
        //fx.pushAll(integers);
        fx.realPushAll(integers);
    }
}
