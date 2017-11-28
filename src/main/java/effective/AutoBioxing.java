package effective;

/**
 * 在jdk1.5之后，提供的有自动拆装箱，可以让基本类型和包装类型自动切换
 * 下面代码第一次订单m变量时使用包装类型Long，发现执行非常耗时，竟然高达
 * 8秒多，而换成long计算仅仅不到1秒，因为在计算的时候，需要对Long类型拆箱
 * 虽然一次拆箱耗费的性能不多，但是操作多的话，性能就体现出来了
 * 所以在代码中应该使用基本类型来计算
 */
public class AutoBioxing {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
//		Long m = 0L;
		long m = 0L;
		for (long i = 0; i < Integer.MAX_VALUE; i++) {
			m += i;
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		System.out.println(m);
	}
}
