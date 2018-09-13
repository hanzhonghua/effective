package effective.fanxing;


import java.util.*;

/**
 * 泛型方法
 * 静态工具的方法特别适合使用泛型方法
 * 泛型方法的一个明显好处就是不用明确指定类型参数的值，不像调用构造方法是必须明确指定的
 */
public class FaxingMethod<E> {
    // 创建方法工厂
    public static <K, V> HashMap<K, V> newHashMap(){
        return new HashMap<K, V>();
    }

    public static Set union1(Set s1, Set s2){
        Set result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

    // 改造以上方法,注意泛型方法需要声明类型参数列表：<E>
    // 根据方法中E的类型可以推出类型参数列表<E>的值，叫做类型推导
    public static <E> Set<E> union2(Set<E> s1, Set<E> s2){
        Set<E> result = new HashSet<E>(s1);
        result.addAll(s2);
        return result;
    }

    public static void main(String[] args) {
        Set<String> s1 = new HashSet<>(Arrays.asList("Tom", "Jerry","Rose"));
        Set<String> s2 = new HashSet<>(Arrays.asList("Jeke", "Rose"));
        Set<String> ss = union2(s1, s2);
        //System.out.println(ss);

        // 标准的泛型写法创建集合
        List<String> l = new ArrayList<String>();
        // 以上写法每次调用构造方法时都需要传递具体类型值，有点麻烦，可以使用如下创建
        // guava包下的Maps.newHashMap()就是使用这用方法创建的
        Map<Object, Object> map = newHashMap();

    }
}
