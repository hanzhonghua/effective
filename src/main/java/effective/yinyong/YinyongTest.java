package effective.yinyong;


import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

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
 *  特点是一个java实例保存一个对Java对象的引用，该软引用的存在不会影响该对象的回收
 * 3:弱引用
 *  比软引用生命周期更低，只要在垃圾回收器运行的过程中，发现了弱引用就会回收，不管内存是否充足，应为垃圾回收器的线程优先级都比较低，
 *  所以弱引用不会很快的被回收，同样也可以使用在内存命敏感的缓存中
 * 4:虚引用
 *  也叫做幻象引用，无法通过虚引用访问对象的任何属性和方法，虚引用仅提供严重确保对象被回收以后，做某些事的机制。如果一个对象仅持有幻象引用
 *  那么它就和没有任何引用一样，任何时候都可以被垃圾回收器回收。必须和引用队列ReferenceQueue联合使用，当垃圾回收器准备回收一个对象时
 *  如果发现它还有虚引用，会在回收对象之前加入到与之关联的引用队列中
 *  可以用来跟踪垃圾回收器回收的活动，当一个虚引用关联的对象被垃圾收集器回收前会收到一条系统通知
 * @Author: HanZhonghua
 * @Date: Create in 下午1:50 18-9-13
 */
public class YinyongTest {

    public static void main(String[] args) {
        YinyongTest t = new YinyongTest();
        //t.test1();
        //t.test2();
        //t.test3();
        t.test4();
    }

    // 强引用
    private void test1(){
        Object obj = new Object();
        Object[] obj2 = new Object[1024];
    }

    // 软引用,在java中主要用SoftReference表示，当内存OOM之前会回收，所以可以使用于页面/图片缓存,内存充足的时候不会回收
    private void test2(){
        /*SoftReference<String> s = new SoftReference<>(new String("hello"));
        System.out.println(s.get());
        System.gc();
        System.out.println(s.get());*/

        Object obj = new Object();

        // 此时obj相对于r就相当于软引用
        SoftReference<Object> r = new SoftReference<>(obj);

        // r.get()将返回对应的强引用，如果该对应已被垃圾回收器回收，将返回null
        Object newObj = r.get();

        // 作为一个对象，SoftReference同样满足对象的一切特征，如果get()返回null时，SoftReference也就失去了它的意义，
        // 这时候就需要一个清理机制，防止大量SoftReference对象造成内存泄漏，提供了一个ReferenceQueue，用法
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        SoftReference reference = new SoftReference<>(obj, queue);

        // 当reference所引用的软引用obj被回收时，reference的强引用将被列入ReferenceQueue，可以看出ReferenceQueue存储的
        // 是失去了它的软引用的SoftReference，利用队列的poll()可以检查哪个SoftReference所引用的软引用对象已被回收。可以将之清楚
        SoftReference ref = null;
        while((ref = (SoftReference) queue.poll()) != null){
            // 清除ref
            ref = null;
        }
    }

    // 弱引用,使用WeakReference表示，JVM进行垃圾回收时，不管内存是否充足，都会进行回收
    private void test3(){
        WeakReference<String> w = new WeakReference<>(new String("hello"));
        System.out.println(w.get());
        System.gc();
        System.out.println(w.get());
    }

    // 虚引用,PhantomReference表示，必须结合和引用队列结合使用，当垃圾回收器回收一个对象时，发现存在虚引用，就会把这个虚引用
    // 加入与之关联的队列中。可以通过判断引用队列总是否已经加入了虚引用，来了解被引用的对象是否将要被回收，如果发现某个虚引用
    // 已经被加入了引用队列，即可在所引用的对象的内存被回收之前搞一些事情
    private void test4(){
        ReferenceQueue<String> queue = new ReferenceQueue<>();
        PhantomReference<String> reference = new PhantomReference<>(new String("hello"), queue);
        System.out.println(reference.get());
    }

}


