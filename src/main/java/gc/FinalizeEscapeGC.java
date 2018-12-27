package gc;

/**
 * @Description:
 *  1.对应在GC时可以完成自救
 *  2.这种自救的机会只有一次，因为一个对象的finalize()最多只会被系统自动调用一次
 *  根据代码执行结果，两段相同的自救代码，第一次逃脱成功，第二次却失败，任何一个对象的finalize方法
 *  只会呗系统自动调用一次。如果对象面临下一次回收，它的finalize方法将不会呗再次执行，所以第二次失败
 *  真是环境中不建议使用该方法完成对象的自救，它运行代价高昂，不确定因素很大，无法保证各个对象的调用顺序，
 *  建议大家忘掉java中有这个方法的存在。
 * @Author: HanZhonghua
 * @Date: Create in 下午12:41 18-12-27
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("Yes,I am still alive :)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();

        // 对应第一次拯救自己
        SAVE_HOOK = null;
        System.gc();
        // 因为finaizle方法优先级很低，暂停0.5秒等待它
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("No,I am dead :(");
        }

        // 和以上代码完全相同，但是自救失败
        SAVE_HOOK = null;
        System.gc();
        // 因为finaizle方法优先级很低，暂停0.5秒等待它
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("No,I am dead :(");
        }
    }
}
