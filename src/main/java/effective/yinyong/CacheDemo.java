package effective.yinyong;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 利用java软引用特性实现简单的缓存
 * @Author: HanZhonghua
 * @Date: Create in 下午6:21 18-9-14
 */
public class CacheDemo<T> {

    private Map<String, SoftReference<T>> cache = new HashMap<>();
    private T t;

    public CacheDemo(T t){
        this.t = t;
    }

    public void addCache(String key){

        // 对应添加软引用
        SoftReference<T> reference = new SoftReference<T>(t);

        cache.put(key, reference);
    }

    public T getCache(String key){
        SoftReference<T> reference = cache.get(key);
        // 判断是否存在软引用
        if(reference == null){
            return null;
        }

        // 取存储对象，内存不足时会被回收
        T t = reference.get();
        return t;
    }

    public static void main(String[] args) {
        CacheDemo<String> cache = new CacheDemo<>("哈哈,我是图片");
        String path = "d\\image\\demo";
        cache.addCache(path);
        System.out.println(cache.getCache(path));
    }
}
