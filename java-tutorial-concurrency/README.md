# 多线程与并发
## 并发模拟

Postman：HTTP请求模拟工具

Apache Bench（AB）：Apache附带的工具，测试网站性能

JMeter：Apache组织开发的压力测试工具

### postman

![](https://github.com/geekerstar/high-concurrency/blob/master/img/post.jpg)

### 并发模拟 - CountDownLatch
![](https://github.com/geekerstar/high-concurrency/blob/master/img/cut.jpg)


## 线程安全性

当多个线程访问某个类时，不管运行时环境采用何种调度方式或者这些进程将如何交替执行，并且在主调代码中不需要任何额外的同步或协同，这个类都能表现出正确的行为，那么就称这个类是线程安全的。

### 体现

- 原子性：提供了互斥访问，同一时刻只能有一个线程来对它进行操作

- 可见性：一个线程对主内存的修改可以及时的被其他线程观察到

- 有序性：一个线程观察其他线程中的指令顺序，由于指令重排序的存在，该观察结果一般杂乱无序

### 原子性 - Atomic包

- AtomicXXX：CAS、Unsafe.compareAndSwapInt

- AtomicLong、LongAdder

- AtomicReference、AtomicReferenceFieldUpdater

- AtomicStampReference：CAS的ABA问题

### 原子性 - 锁

- synchronized：依赖JVM

- Lock：依赖特殊的CPU指令，代码实现，ReentrantLock

### 原子性 - synchronized

- 修饰代码块：大括号括起来的代码，作用于调用的对象

- 修饰方法：整个方法，作用与调用的对象

- 修饰静态方法：整个静态方法，作用于所有对象

- 修饰类：括号括起来的部分，作用于所有对象

### 原子性-对比
- synchronized:不可中断锁，适合竞争不激烈，可读性好
- Lock：可中断锁，多样化同步，竞争激烈时能维持常态
- Atomic:竞争激烈时能维持常态，比Lock性能好，只能同步一个值


### 可见性

导致共享变量在线程间不可见的原因：

- 线程交叉执行

- 重排序结合线程交叉执行

- 共享变量更新后的值没有在工作内存与主存间及时更新

### 可见性-synchronized
JMM关于synchronized的两条规定：

- 线程解锁前，必须把共享变量的最新值刷新到主内存

- 线程加锁时，将清空工作内存中共享变量的值，从而使用共享变量时需要从主内存中重新读取最新的值（注意：加锁与解锁是同一把锁）

### 可见性-volatile
通过加入【内存屏障】和【禁止重排序】优化来实现

- 1、对volatile变量写操作时，会在写操作后加入一条store屏障指令，将本地内存中的共享变量值刷新到主内存

- 2、对volatile变量读操作时，会在读操作前加入一条load屏障指令，从主内存中读取共享变量

### 可见性 - volatile写
![](https://github.com/geekerstar/high-concurrency/blob/master/img/vr.png)

### 可见性 - volatile读
![](https://github.com/geekerstar/high-concurrency/blob/master/img/vw.png)

### 可见性 - volatile使用
![](https://github.com/geekerstar/high-concurrency/blob/master/img/vol.png)

### 线程安全-有序性
Java内存模型中，允许编译器和处理器对指令进行重排序，但是重排序过程不会影响到单线程程序的执行，却会影响到多线程并发执行的正确性

- volatile、synchronized、Lock

### 有序性-happens-before原则
- 1、程序次序规则：一个线程内，按照代码顺序，书写在前面的操作先行发生于书写在后面的操作

- 2、锁定规则：一个unlock操作先行发生于后面对同一个锁的Lock操作

- 3、volatile变量规则：对一个变量的写操作先行发生于后面对这个变量的读操作

- 4、传递规则：如果操作A先行发生于操作B，而操作B又先行发生于操作C，则可以得出操作A先行发生于操作C

- 5、线程启动规则：Thread对象的start()方法先行发生于此线程的每一个动作

- 6、线程中断规则：对线程interrupt()方法的调用先行发生于被中断线程的代码检测到中断事件的发生

- 7、线程终结规则：线程中所有的操作都先行发生于线程的终止检测、我们可以通过Thread.join()方法结束、Thread.isAlive()的返回值手段检测到线程已经终止执行

- 8、对象终结规则：一个对象的初始化完成先行发生于他的finalize()方法的开始

### 线程安全性-总结
- 原子性：Atomic包、CAS算法、synchronized、Lock

- 可见性：synchronized、volatile

- 有序性：happens-before

## 发布对象
- 发布对象：使一个对象能够被当前范围之外的的代码所使用

- 对象逸出：一种错误的发布。当一个对象还没有构造完成时，就使它被其他线程所见

### 安全发布对象四个方法

- 1、在静态初始化函数中初始化一个对象引用

- 2、将对象的引用保存到volatile类型域或者AtomicReference对象中

- 3、将对象的引用保存到某个正确构造对象的final类型域中

- 4、将对象的引用保存到一个由锁保存的域中

## 不可变对象
不可变对象需要满足的条件

- 1、对象创建以后其状态就不能改变

- 2、对象所有域都是final类型

- 3、对象是正确创建的（在对象创建期间，this引用没有逸出）

final关键字：类、方法、变量

- 1、修饰类：不能被继承

- 2、修饰方法：1、锁定方法不能被继承修改；2、效率

- 3、修饰变量：基本数据类型变量（初始化后不能修改），引用类型变量(初始化不能指向另外的对象）

### 定义不可变对象的方法
- Collections.unmodifiableXXX:Collection、List、Set、Map…

- Guava：ImmutableXXX：Collection、List、Set、Map…

## 线程封闭
- Ad-hoc线程封闭：程序控制实现，最糟糕，忽略

- 堆栈封闭：局部变量，无并发问题

- ThreadLocal线程封闭：特别好的封闭方法

## 常见线程不安全类与写法

- StringBuilder -> StringBuffer

- SimpleDataFormat -> JodaTime

- ArrayList，HashSet，HashMap等Collections

- 先检查再执行：if(condition(a)){handle(a);}

## 线程安全 - 同步容器（不能完全做到线程安全）

- ArrayList -> Vector,Stack

- HashMap -> HashTable(key、value不能为null)

- Collections.synchronizedXXX(List、Set、Map)

## 线程安全 - 并发容器 J.U.C

- ArrayList -> CopyOnWriteArrayList

- HashSet -> CopyOnWriteArraySet

- TreeSet -> ConcurrentSkipListSet

- HashMap -> ConcurrentHashMap

- TreeMap -> ConcurrentSkipListMap

## 安全共享对象策略-总结
- 1、线程限制：一个线程限制的对象，由线程独占，并且只能被占有它的线程修改

- 2、共享只读：一个共享只读的对象，在没有任何额外同步的情况下，可以被多个线程并发访问，但是任何线程都不能修改它

- 3、线程安全对象：一个线程安全的对象或容器，在内部通过同步机制来保证线程安全，所以其他线程无需额外的同步就可以通过公共接口随意访问它

- 4、被守护对象：被守护对象只能通过获取特定的锁来访问

## J.U.C

![juc.png](https://github.com/geekerstar/high-concurrency/blob/master/img/juc.png)

## AbstractQueuedSynchronizer-AQS

![aqs.png](https://github.com/geekerstar/high-concurrency/blob/master/img/aqs.png)

- 使用Node实现FIFO队列，可以用于构建锁或者其他同步装置的基础框架

- 利用了一个Int类型表示状态

- 使用方法是继承

- 子类通过继承并通过实现它的方法管理其状态{acquire 和 release}的方法操纵状态

- 可以同时实现排它锁和共享锁模式（独占、共享）

## AQS 同步组件
- CountDownLatch

- Semaphore

- CyclicBarrier

- ReentrantLock

- Condition

- FutureTask

## Semaphore

![semaphore.png](https://github.com/geekerstar/high-concurrency/blob/master/img/semaphore.png)

## CyclicBarrier
![cyclicbarrier.png](https://github.com/geekerstar/high-concurrency/blob/master/img/cyclicbarrier1.png)


## ReentrantLock 与锁
ReentrantLock(可重入锁)和synchronized区别
- 可重入性
- 锁的实现
- 性能的区别
- 功能区别

ReentrantLock独有功能
- 可指定是公平锁还是非公平锁
- 提供了一个Condition类，可以分组唤醒需要唤醒的线程
- 提供能够中断等待锁的线程的机制，lock.lockInterruptibly()

## Fork/Join 框架
![fork.png](https://github.com/geekerstar/high-concurrency/blob/master/img/fork.png)

## BlockingQueue
![block.png](https://github.com/geekerstar/high-concurrency/blob/master/img/block.png)

![block2.png](https://github.com/geekerstar/high-concurrency/blob/master/img/block2.png)

- ArrayBlockingQueue
- DelayQueue
- LinkedBlockingQueue
- PriorityBlockingQueue
- SynchronousQueue

## 线程池
new Thread 弊端
- 每次 new Thread 新建对象，性能差
- 线程缺乏统一管理，可能无限制的新建线程，先过户竞争，有可能占用过多系统资源导致死机或OOM
- 缺少更多功能，如更多执行，定期执行，线程中断

线程池的好处
- 重用存在的线程，减少对象创建、消亡的开销，性能佳
- 可有效控制最大并发线程数，提高系的资源利用率，同时可以避免过多资源竞争，避免阻塞
- 提供定时执行。定期执行、单线程、并发数控制等功能

### 线程池 - ThreadPoolExecutor
- corePoolSize：核心线程数量
- maximumPoolSize：线程最大线程数
- workQueue：阻塞队列，存储等待执行的任务，很重要，会对线程池运行过程产生重大影响
- keepAliveTime：线程没有任务执行时最多保持多久时间终止
- unit：keepAliveTime的时间单位
- ThreadFactory：线程工厂，用来创建线程
- rejectHandler：当拒绝处理任务时的策略

![Thread](https://github.com/geekerstar/high-concurrency/blob/master/img/thread.png)

- execute()：提交任务，交给线程池执行
- submit()：提交任务，能够返回执行结果 execute+Future
- shutdown()：关闭线程池，等待任务都执行完
- shutdownNow()：关闭线程池，不等待任务执行完
- getTaskCount()：线程池已执行和未执行的任务总数
- getCompletedTaskCount()：已完成的任务数量
- getPoolSize()：线程池当前的线程数量
- getActiveCount()：当前线程池中正在执行任务的线程数量

![Thread](https://github.com/geekerstar/high-concurrency/blob/master/img/thread2.png)

### 线程池 - Executor 框架接口
- Executors.newCachedThreadPool
- Executors.newFixedThreadPool
- Executors.newScheduledThreadPool
- Executors.newSingleThreadExecutor

### 线程池 - 合理配置
- CPU密集型任务，就需要尽量压榨CPU，参考值可以设为NCPU+1
- IO密集型任务，参考值可以设置为2*NCPU

## 死锁 - 必要条件
- 互斥条件
- 请求和保持条件
- 不剥夺条件
- 环路等待条件

## 多线程并发最佳实践
- 使用本地变量
- 使用不可变类
- 最小化锁的作用域范围：S=1/(1-a+a/n)
- 使用线程池的Executor，而不是直接new Thread执行
- 宁可使用同步也不要使用线程的wait和notify
- 使用BlockingQueue实现生产-消费模式
- 使用并发集合而不是加了锁的同步集合
- 使用Semaphore创建有界的访问
- 宁可使用同步代码块，也不使用同步的方法
- 避免使用静态变量

## Spring与线程安全
- Spring bean：singleton、prototype
- 无状态对象

## HashMap与ConcurrentHashMap
### HashMap
![](https://github.com/geekerstar/high-concurrency/blob/master/img/hashmap.png)

![](https://github.com/geekerstar/high-concurrency/blob/master/img/hashmap2.png)

![](https://github.com/geekerstar/high-concurrency/blob/master/img/hashmap3.png)

![](https://github.com/geekerstar/high-concurrency/blob/master/img/hashmap4.png)

![](https://github.com/geekerstar/high-concurrency/blob/master/img/hashmap5.png)

![](https://github.com/geekerstar/high-concurrency/blob/master/img/hashmap6.png)

### ConcurrentHashMap
![](https://github.com/geekerstar/high-concurrency/blob/master/img/chm.png)

![](https://github.com/geekerstar/high-concurrency/blob/master/img/chm2.png)

## 多线程并发与线程安全总结
![](https://github.com/geekerstar/high-concurrency/blob/master/img/d.jpg)

----

# 高并发

## 扩容
- 垂直扩容（纵向扩展）：提高系统部件能力
- 水平扩容（横向扩展）：增加更多系统成员来实现

## 扩容 - 数据库
- 读操作扩展：memcache、Redis、CDN等缓存
- 写操作扩展：Cassandra、HBase等

## 缓存
![](https://github.com/geekerstar/high-concurrency/blob/master/img/huancun.png)


### 缓存特征
- 命中率：命中数/（命中数+没有命中数）
- 最大元素（空间）
- 清空策略：FIFO、LFU、LRU、过期时间、随机等

### 缓存命中率影响因素
- 业务场景和业务需求
- 缓存的设计（粒度和策略）
- 缓存容量和基础设施

### 缓存分类和应用场景
- 本地缓存：编程实现（成员变量、局部变量、静态变量）、Guava Cache
- 分布式缓存：Memcache、Redis

### 缓存 - Guava Cache

![](https://github.com/geekerstar/high-concurrency/blob/master/img/cache1.png)

### 缓存 - Memcache

![](https://github.com/geekerstar/high-concurrency/blob/master/img/cache2.jpg)

### Memcache 内存结构

![](https://github.com/geekerstar/high-concurrency/blob/master/img/cache3.png)

### 缓存 - Redis

![](https://github.com/geekerstar/high-concurrency/blob/master/img/cache4.png)

## 高并发场景下缓存常见问题
### 缓存一致性
![](https://github.com/geekerstar/high-concurrency/blob/master/img/h1.png)

### 缓存并发问题
![](https://github.com/geekerstar/high-concurrency/blob/master/img/h2.jpg)

### 缓存穿透问题
![](https://github.com/geekerstar/high-concurrency/blob/master/img/h3.jpg)

### 缓存的雪崩现象
![](https://github.com/geekerstar/high-concurrency/blob/master/img/h4.jpg)


## 消息队列

![](https://github.com/geekerstar/high-concurrency/blob/master/img/m.png)

### 消息队列特性
- 业务无关：只做消息分发
- FIFO：先投递先到达
- 容灾：节点的动态增删和消息的持久化
- 性能：吞吐量提升，系统内部通信效率提高

### 为什么需要消息队列
- 【生产】和【消费】的速度或稳定性等因素不一致

### 消息队列的好处
- 业务解耦
- 最终一致性
- 广播
- 错峰与流控


### 消息队列-Kafka
![](https://github.com/geekerstar/high-concurrency/blob/master/img/kafka.jpg)


### 消息队列-RabbitMQ
![](https://github.com/geekerstar/high-concurrency/blob/master/img/rabbit.png)

## 应用拆分
![](https://github.com/geekerstar/high-concurrency/blob/master/img/y.png)


### 应用拆分 - 原则
- 业务优先
- 循序渐进
- 兼顾技术：重构、分层
- 可靠测试

### 应用拆分 - 思考
- 应用之间通信：RPC（dubbo等）、消息队列
- 应用之间数据库设计：每个应用都有独立的数据库
- 避免事务操作跨应用

### Dubbo

![](https://github.com/geekerstar/high-concurrency/blob/master/img/dubbo.png)


### 微服务

![](https://github.com/geekerstar/high-concurrency/blob/master/img/w.png)

![](https://github.com/geekerstar/high-concurrency/blob/master/img/w2.png)


## 应用限流

![](https://github.com/geekerstar/high-concurrency/blob/master/img/x.png)

### 应用限流 - 算法
#### 计数器法

![](https://github.com/geekerstar/high-concurrency/blob/master/img/x2.png)

![](https://github.com/geekerstar/high-concurrency/blob/master/img/x3.png)

#### 滑动窗口

![](https://github.com/geekerstar/high-concurrency/blob/master/img/x4.png)

#### 漏桶算法 (Leaky Bucket)

![](https://github.com/geekerstar/high-concurrency/blob/master/img/x5.png)

#### 令牌桶算法(Token Bucket)

![](https://github.com/geekerstar/high-concurrency/blob/master/img/x6.png)

## 服务降级与服务熔断
- 服务降级
- 服务熔断

### 服务降级分类
- 自动降级：超时、失败次数、故障、限流
- 人工降级：秒杀、双11大促

### 共性与区别
- 共性：目的、最终表现、粒度4、自治
- 区别：触发原因、管理目标层次、实现方式

### 服务降级要考虑的问题
- 核心服务、非核心服务
- 是否支持降级、降级策略
- 业务放通场景，策略

### Hystrix
- 在通过第三方客户端访问（通常是通过网络）依赖服务出现高延迟或者失败时，为系统提供保护和控制
- 在分布式系统中防止级联失败
- 快速失败（Fail fast）同时能快速回复
- 提供失败回退（Fallback)和优雅的服务降级机制


## 数据库切库、分库、分表
数据库瓶颈
- 单个库数据量太大（1T~2T）：多个库
- 单个数据库服务器压力过大、读写瓶颈：多个库
- 单个表数据量过大：分表

### 数据库切库
- 切库的基础及实际运用：读写分离
- 支持多数据源、分库

### 数据库分表
- 什么时候考虑分表
- 横向（水平）分表与纵向（垂直）分表
- 数据库分表：mybatis分表插件 shardbatis2.0

## 高可用的一些手段
- 任务调度系统分布式：elastic-job + zookeeper
- 主备切换：Apache curator + zookeeper 分布式锁实现
- 监控报警机制
