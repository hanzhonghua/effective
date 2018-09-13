package effective;

/**
 * @Description: java中除了基本类型之外，其它的都是指向各类对象的引用，java中根据其生命周期的长短，将引用分为四大类
 * 1:强引用
 *  Object obj = new Object();其中obj就是强引用，通关关键字new创建的对象所关联的引用都是强引用。当内存不足时，JVM
 *  宁愿抛出OutofMememoryError(OOM)运行时错误，使程序终止，也不会随意回收具有强引用的"存活"对象来解决内存不足问题。
 *  对应一个普通对象，如果没有其它引用关系，只要超过了引用的作用域或者引用值赋null，就可以让垃圾收集器回收了
 * 2:软引用
 *  比强引用的生命周期更短，只有当内存不足时，才会回收软引用，即JVM在确保抛出OutofMemoryError之前，会回收所有的软引用
 *  应用场景：通常用来实现内存敏感的缓存，如果有空闲内存，缓存就继续存在，如果内存不足，就清理掉缓存，这样保证了使用缓存的
 *  同时，不会耗尽内存
 * 3:弱引用
 *  比软引用生命周期更低，只要在垃圾回收器运行的过程中，发现了弱引用就会回收，不管内存是否充足，应为垃圾回收器的线程优先级都比较低，
 *  所以弱引用不会很快的被回收，同样也可以使用在内存命敏感的缓存中
 * 4:虚引用
 *
 *
 * @Author: HanZhonghua
 * @Date: Create in 下午1:50 18-9-13
 */
public class YinyongTest {

    public static void main(String[] args) {
        YinyongTest t = new YinyongTest();
        t.test1();
    }

    // 强引用
    private void test1(){
        Object obj = new Object();
    }

}


