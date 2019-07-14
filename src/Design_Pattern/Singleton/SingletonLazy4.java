package Design_Pattern.Singleton;

/**
 * Created by SongWei on 2019/5/4.
 *
 * Double-Check概念对于多线程开发者来说不会陌生，
 * 如代码中所示，我们进行了两次if (Singleton == null)检查，这样就可以保证线程安全了。
 * 这样，实例化代码只用执行一次，后面再次访问时，判断if (Singleton == null)，直接return实例化对象。
 *
 * 优点：线程安全；延迟加载；效率较高。
 */
public class SingletonLazy4 {

    /**
     * 【有关instance对象要用volatile修饰的解释】：
     * https://blog.csdn.net/weixin_37817685/article/details/80261549
     *
     *
     * 这段代码看起来有点复杂，注意其中有两次if(instance==null)的判断，这个叫做『双重检查 Double-Check』。
     *
     * 第一个 if(instance==null)，其实是为了解决代码二中的效率问题，只有instance为null的时候，才进入synchronized的代码段大大减少了几率。
     *
     * 第二个if(instance==null)，则是跟代码二一样，是为了防止可能出现多个实例的情况。
     *
     *
     *
     * 这段代码看起来已经完美无瑕了。当然，只是『看起来』，还是有小概率出现问题的。想要充分理解需要先弄清楚以下几个概念：原子操作、指令重排。
     *
     *
     *
     * 原子操作
     * 简单来说，原子操作（atomic）就是不可分割的操作，在计算机中，就是指不会因为线程调度被打断的操作。比如，简单的赋值是一个原子操作：
     *
     * m = 6; // 这是个原子操作
     *
     *
     * 假如m原先的值为0，那么对于这个操作，要么执行成功m变成了6，要么是没执行 m还是0，而不会出现诸如m=3这种中间态——即使是在并发的线程中。
     *
     *
     *
     * 但是，声明并赋值就不是一个原子操作：
     *
     * int  n=6;//这不是一个原子操作
     *
     *
     * 对于这个语句，至少有两个操作：①声明一个变量n ②给n赋值为6——这样就会有一个中间状态：变量n已经被声明了但是还没有被赋值的状态。这样，在多线程中，由于线程执行顺序的不确定性，如果两个线程都使用m，就可能会导致不稳定的结果出现。
     *
     *
     *
     * 指令重排
     * 简单来说，就是计算机为了提高执行效率，会做的一些优化，在不影响最终结果的情况下，可能会对一些语句的执行顺序进行调整。比如，这一段代码：
     *
     * int a ;   // 语句1
     * a = 8 ;   // 语句2
     * int b = 9 ;     // 语句3
     * int c = a + b ; // 语句4
     *
     *
     * 正常来说，对于顺序结构，执行的顺序是自上到下，也即1234。但是，由于指令重排
     * 的原因，因为不影响最终的结果，所以，实际执行的顺序可能会变成3124或者1324。
     *
     * 由于语句3和4没有原子性的问题，语句3和语句4也可能会拆分成原子操作，再重排。
     * ——也就是说，对于非原子性的操作，在不影响最终结果的情况下，其拆分成的原子操作可能会被重新排列执行顺序。
     *
     *
     *
     * OK，了解了原子操作和指令重排的概念之后，我们再继续看代码三的问题。
     *
     *
     *
     * 主要在于singleton = new Singleton()这句，这并非是一个原子操作，事实上在 JVM 中这句话大概做了下面 3 件事情。
     * 　　1. 给 singleton 分配内存
     * 　　2. 调用 Singleton 的构造函数来初始化成员变量，形成实例
     * 　　3. 将singleton对象指向分配的内存空间（执行完这步 singleton才是非 null了）
     *
     * 在JVM的即时编译器中存在指令重排序的优化。
     *
     * 也就是说上面的第二步和第三步的顺序是不能保证的，最终的执行顺序可能是 1-2-3 也可能是 1-3-2。
     * 如果是后者，则在 3 执行完毕、2 未执行之前，被线程二抢占了，
     * 这时 instance 已经是非 null 了（但却没有初始化），所以线程二会直接返回 instance，然后使用，然后顺理成章地报错。
     *
     * 再稍微解释一下，就是说，由于有一个『instance已经不为null但是仍没有完成初始化』的中间状态，
     * 而这个时候，如果有其他线程刚好运行到第一层if (instance ==null)这里，这里读取到的instance已经不为null了，
     * 所以就直接把这个中间状态的instance拿去用了，就会产生问题。
     * 这里的关键在于线程T1对instance的写操作没有完成，线程T2就执行了读操作。
     *
     * 对于代码三出现的问题，解决方案为：给instance的声明加上volatile关键字
     *
     *
     * 代码如下：
     *
     * public class Singleton {
     *    private static volatile Singleton instance = null;
     *    private Singleton() {}
     *
     *    public static Singleton getInstance() {
     *    if (instance == null){
     *      synchronized(Singleton.class){
     *        if (instance == null)
     *          instance = new Singleton();
     *      }
     *    }
     *    return instance;
     *    }
     * }
     *
     *
     * volatile关键字的一个作用是禁止指令重排，把instance声明为volatile之后，对它的写操作就会有一个内存屏障，
     * 这样，在它的赋值完成之前，就不用会调用读操作。
     *
     *
     *
     * 注意：volatile阻止的不是singleton = new Singleton()这句话内部[1-2-3]的指令重排，
     * 而是保证了在一个写操作（[1-2-3]）完成之前，不会调用读操作（if (instance == null)）。
     *
     * 加了volatile关键字去修饰instance的话，那么另一个线程去读instance的读操作就会排在写操作之后执行，
     * 也就是可以保证得到的要么是初始化完成的对象，要么是仍然为null的对象；
     *
     */
    private static volatile SingletonLazy4 instance;

    private SingletonLazy4(){}

    public static SingletonLazy4 getInstance(){
        if(instance == null){
            synchronized (SingletonLazy4.class){
                if(instance == null){
                    instance = new SingletonLazy4();
                }
            }
        }
        return instance;
    }
}
