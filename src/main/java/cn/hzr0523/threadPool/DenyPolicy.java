package cn.hzr0523.threadPool;

/**
 * hezhi
 * 2018/11/4 15:49
 */
@FunctionalInterface
public interface DenyPolicy {

    void reject();

    boolean equals(Object val1);

    static void staticMethod(){

    }

    default void defaultMethod() {
        System.out.println("接口中default修饰的方法可以有方法体");
    }

    //如果有两个抽象方法，注解就报错
    //void test();

}
