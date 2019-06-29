package Coding_Interviews_Java._02____实现Singleton模式;

/**
 * Created by SongWei on 2019/5/4.
 *
 * 1、饿汉式（静态常量）[可用]
 *
 * 优点：这种写法比较简单，就是在类装载的时候就完成实例化。避免了线程同步问题。
 * 缺点：在类装载的时候就完成实例化，没有达到Lazy Loading的效果。如果从始至终从未使用过这个实例，则会造成内存的浪费。
 */
public class SingletonHungry1 {

    private static final SingletonHungry1 instance = new SingletonHungry1();

    private SingletonHungry1(){}

    public static SingletonHungry1 getInstance(){
        return instance;
    }
}
