# 理解Spring源码

## 补充知识

### 理解HashMap和ConcurrentHashMap

https://blog.csdn.net/weixin_44460333/article/details/86770169

> [ConcurrentHashMap](https://so.csdn.net/so/search?q=ConcurrentHashMap&spm=1001.2101.3001.7020)是J.U.C(java.util.concurrent包)的重要成员，它是HashMap的一个线程安全的、支持高效并发的版本。在默认理想状态下，ConcurrentHashMap可以支持16个线程执行并发写操作及任意数量线程的读操作 

### isAssignableFrom()方法与instanceof关键字用法

参考：https://blog.csdn.net/qq_36666651/article/details/81215221

 区别总结为以下两个点： 

- isAssignableFrom()方法是从类继承的角度去判断，instanceof关键字是从实例继承的角度去判断。
- isAssignableFrom()方法是判断是否为某个类的父类，instanceof关键字是判断是否某个类的子类。

使用方法：

```
父类.class.isAssignableFrom(子类.class)

子类实例 instanceof 父类类型
```

isAssignableFrom()方法的调用者和参数都是Class对象，调用者为父类，参数为本身或者其子类。

instanceof关键字两个参数，前一个为类的实例，后一个为其本身或者父类的类型。

