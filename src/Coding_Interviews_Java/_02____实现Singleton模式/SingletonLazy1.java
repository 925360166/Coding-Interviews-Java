package Coding_Interviews_Java._02____实现Singleton模式;

/**
 * Created by SongWei on 2019/5/4.
 *
 * 3、懒汉式(线程不安全)[不可用]
 *
 * 这种写法起到了Lazy Loading的效果，但是只能在单线程下使用。
 * 如果在多线程下，一个线程进入了if (Singleton == null)判断语句块，还未来得及往下执行，
 * 另一个线程也通过了这个判断语句，这时便会产生多个实例。
 * 所以在多线程环境下不可使用这种方式。
 *
 */
public class SingletonLazy1 {

    private static SingletonLazy1 instance;

    private SingletonLazy1(){}

    public static SingletonLazy1 getInstance(){
        if(instance == null){
            instance = new SingletonLazy1();
        }
        return instance;
    }
}
