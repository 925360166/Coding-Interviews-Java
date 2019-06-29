package Coding_Interviews_Java._02____实现Singleton模式;

/**
 * Created by SongWei on 2019/5/4.
 *
 * 5、懒汉式(线程安全，同步代码块)[不可用]
 *
 * 由于第四种实现方式同步效率太低，所以摒弃同步方法，改为同步产生实例化的的代码块。
 * 但是这种同步并不能起到线程同步的作用。
 * 跟第3种实现方式遇到的情形一致，
 * 假如一个线程进入了if (Singleton == null)判断语句块，
 * 还未来得及往下执行，
 * 另一个线程也通过了这个判断语句，
 * 这时便会产生多个实例。
 *
 */
public class SingletonLazy3 {

    private static SingletonLazy3 instance;

    private SingletonLazy3(){}

    public static SingletonLazy3 getInstance(){
        if(instance == null){
            synchronized (SingletonLazy3.class){
                instance = new SingletonLazy3();
            }
        }
        return instance;
    }
}
