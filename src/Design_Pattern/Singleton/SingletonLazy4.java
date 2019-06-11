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
