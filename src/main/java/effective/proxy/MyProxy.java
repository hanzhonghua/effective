package effective.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Author: HanZhonghua
 * @Date: Create in 下午7:43 18-9-18
 */
interface Hello {
    void print();
}

class HelloImpl implements Hello {

    @Override
    public void print() {
        System.out.println("Hello World");
    }
}

class MyInvocationHandler<T> implements InvocationHandler {

    private Object obj;

    public MyInvocationHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("hi I am cainiao");
        Object invoke = method.invoke(proxy, args);
        return invoke;
    }
}

public class MyProxy {

    public static void main(String[] args) {
        HelloImpl hello = new HelloImpl();
        MyInvocationHandler<HelloImpl> handler = new MyInvocationHandler<>(hello);
        Hello helloProxy = (Hello) Proxy.newProxyInstance(HelloImpl.class.getClassLoader(), HelloImpl.class.getInterfaces(), handler);
        helloProxy.print();
    }
}

