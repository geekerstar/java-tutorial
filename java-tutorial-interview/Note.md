## 单例设计模式
### 什么是singleton?
Singleton：在Java中即指单例设计模式，它是软件开发中最常用的设计模式之一。

- 单：唯一
- 例：实例

单例设计模式，即某个类在整个系统中只能有一个实例对象可被获取和使用的代码模式。

- 例如：代表JVM运行环境的Runtime类

### 要点
- 某个类只能有一个实例（构造器私有化）
- 它必须自行创建这个实例（含有一个该类 的静态变量来保存整个唯一的实例）
- 它必须自行向整个系统提供这个实例（对外提供获取该实例对象的方式：1、直接暴露 2、用静态变量的get方法获取

### 几种常见的形式
### 饿汉式：直接创建对象，不存在线程安全问题
- 直接实例化饿汉式（简洁直观）
- 枚举式（最简洁）
- 静态代码块饿汉式（适合复杂实例化）

### 懒汉式：延迟创建对象
- 线程不安全（适用于单线程）
- 线程安全（适用于多线程）
- 静态内部类形式（适用于多线程）

### 总结
- 如果是饿汉式，枚举形式最简单
- 如果是懒汉式，静态内部类形式最简单

## 类初始化和实例初始化
![](https://github.com/geekerstar/dive-in-interview/blob/master/img/1.jpg)

![](https://github.com/geekerstar/dive-in-interview/blob/master/img/2.jpg)

![](https://github.com/geekerstar/dive-in-interview/blob/master/img/3.jpg)

### 考点？
- 类的初始化过程
- 实例初始化过程
- 方法的重写

### 类初始化的过程
#### 一个类要创建实例需要先加载并初始化该类
- main方法所在的类需要先加载和初始化
#### 一个子类要初始化需要先初始化父类
#### 一个雷初始化就是执行<clinit>()方法
- <clinit>()方法由静态类变量显示赋值代码和静态代码块组成
- 类变量显示赋值代码和静态代码块代码从上到下顺序执行
- <clinit>()方法只执行一次

### 实例初始化过程
#### 实例初始化就是执行<init>()方法
- <init>()方法可能重载有多个，有几个构造器就有几个<init>方法
- <init>()方法由非静态实例变量显示赋值代码和非静态代码块、对应构造器代码组成
- 非静态实例变量显示赋值代码和非静态代码块代码从上到下顺序执行，而对应构造器的代码最后执行
- 每次创建实例对象，调用对应构造器，执行的就是对应的<init>方法
- <init>方法的首行是super()或super(实参列表)，即对应父类的<init>方法

### 方法的重写Override
#### 哪些方法不可以被重写

- final方法
- 静态方法
- private等子类中不可见方法

#### 对象的多态性
- 子类如果重写了父类的方法，通过子类对象调用的一定是子类重写过的代码
- 非静态方法默认的调用对象是this
- this对象在构造器或者说<init>方法中就是正在创建的对象

### 总结
- Override和Overload的区别？
- Override重写的要求？（方法名、形参列表、返回值类型、抛出的异常列表、修饰符）
- 了解《JVM虚拟机规范》中关于<clinit>和<init>方法的说明、invokespecial指令

## 方法的参数传递机制
![](https://github.com/geekerstar/dive-in-interview/blob/master/img/4.jpg)

![](https://github.com/geekerstar/dive-in-interview/blob/master/img/5.jpg)

### 考点
- 方法的参数传递机制
- String、包装类等对象的不可变性

### 方法的参数传递机制
#### 形参是基本数据类型
- 传递数据值
#### 实参是引用数据类型
- 传递地址值
- 特殊的类型：String、包装类等对象不可变性

![](https://github.com/geekerstar/dive-in-interview/blob/master/img/8.png)


## i++和++i
![](https://github.com/geekerstar/dive-in-interview/blob/master/img/7.jpg)

- 赋值=，最后计算
- =右边的从左到右加载值依次压入操作数栈
- 实际先算哪个，看运算符优先级
- 自增、自减操作都是直接修改变量的值，不经过操作数栈
- 最后的赋值之前，临时结果也是存储在操作数栈中

> 建议：《JVM虚拟机规范》关于指令的部分

## 编程题：有n步台阶，一次只能上1步或2步，共有多少种走法？

- 递归
- 循环迭代

### 总结
方法调用自身称为递归，利用变量的原值推出新值称为迭代。

#### 递归
- 优点：大问题转化为小问题，可以减少代码量，同时代码精简，可读性好；
- 缺点：递归调用浪费了空间，而且递归太深容易造成堆栈的溢出。
#### 迭代
- 优点：代码运行效率好，因为时间只因循环次数增加而增加，而且没有额外的空间开销；
- 缺点：代码不如递归简洁，可读性好


## 成员变量与局部变量
```java
public class Variable {
    /**
     * 成员变量，类变量
     */
    static int s;
    /**
     * 成员变量，实例变量
     */
    int i;
    /**
     * 成员变量，实例变量
     */
    int j;
    {
        //非静态代码块中的局部变量 i
        int i = 1;
        i++;
        j++;
        s++;
    }
    public void test(int j){//形参，局部变量,j
        j++;
        i++;
        s++;
    }
    public static void main(String[] args) {//形参，局部变量，args
        //局部变量，obj1
        Variable obj1 = new Variable();
        //局部变量，obj1
        Variable obj2 = new Variable();
        obj1.test(10);
        obj1.test(20);
        obj2.test(30);
        System.out.println(obj1.i + "," + obj1.j + "," + obj1.s);
        System.out.println(obj2.i + "," + obj2.j + "," + obj2.s);
    }
}

```

```text
2,1,5
1,1,5
```

### 考点
#### 就近原则
#### 变量的分类
- 成员变量：类变量、实例变量
- 局部变量
#### 非静态代码块的执行：每次创建实例对象都会执行
##### 方法的调用规则：调用一次执行一次

### 局部变量与成员变量的区别
#### 1、声明的位置
局部变量：方法体{}中，形参，代码块{}中

成员变量：类中方法外
- 类变量：有static修饰
- 实例变量：没有static修饰
#### 2、修饰符
局部变量：final

成员变量：public、protected、private、final、static、volatile、transient
#### 3、值存储的位置
局部变量：栈

实例变量：堆

类变量：方法区

#### 4、作用域
局部变量：从声明处开始，到所属的}结束

实例变量：在当前类中“this.”(有时this.可以缺省)，在其他类中“对象名.”访问

类变量：在当前类中“类名.”(有时类名.可以省略)，在其他类中“类名.”或“对象名.”访问

#### 5、生命周期
局部变量：每一个线程，每一次调用执行都是新的生命周期

实例变量：随着对象的创建而初始化，随着对象的被回收而消亡，每一个对象的实例变量是独立的

类变量：随着类的初始化而初始化，随着类的卸载而消亡，该类的所有对象的类变量是共享的


### 当局部变量与xx变量重名时，如何区分
#### 局部变量与实例变量重名
- 在实例变量前面加“this.”
#### 局部变量与类变量重名
- 在类变量前面加“类名.”

![](https://github.com/geekerstar/dive-in-interview/blob/master/img/9.png)


## bean的作用域之间有什么区别？

可以通过scope属性来指定bean的作用域
- singleton：默认值。当IOC容器一创建就会创建bean的实例，而且是单例的，每次得到的都是同一个
- prototype：原型的。当IOC容器一创建不再实例化该bean，每次调用getBean方法时再实例化该bean，而且每调用一次创建一个对象
- request：每次请求实例化一个bean
- session：在一次会话中共享一个bean


## Spring支持的常用数据库事务传播属性和事务隔离级别
@Transactional注解
 * 	该注解可以添加到类上，也可以添加到方法上
 * 	如果添加到类上，那么类中所有的方法都添加上了事务
 * 	如果添加到方法上，只有添加了该注解的方法才添加了事务
 
### 请简单介绍Spring支持的常用数据库事务传播属性和事务隔离级别？
事务的属性：

1.propagation：用来设置事务的传播行为
- 事务的传播行为：一个方法运行在了一个开启了事务的方法中时，当前方法是使用原来的事务还是开启一个新的事务
- Propagation.REQUIRED：默认值，使用原来的事务
- Propagation.REQUIRES_NEW：将原来的事务挂起，开启一个新的事务

2.isolation：用来设置事务的隔离级别
- Isolation.REPEATABLE_READ：可重复读，MySQL默认的隔离级别
- Isolation.READ_COMMITTED：读已提交，Oracle默认的隔离级别，开发时通常使用的隔离级别

## SpringMVC中如何解决POST请求中文乱码问题
TODO

## Spring Bean的作用域之间有什么区别
TODO

## Spring支持的常用数据库事务传播属性
TODO

## 简单的谈一下SpringMVC的工作流程
TODO

## Linux常用服务类相关命令
### Service（CentOS6）
注册在系统中的标准化程序

有方便统一的管理方式（常用的方法）
- service 服务名 start
- service 服务名 stop
- service 服务名 restart
- service 服务名 reload
- service 服务名 status

查看服务的方法 /etc/init.d/服务名

通过chkconfig命令设置自启动
- 查看服务 chkconfig --list|grep xxx
- chkconfig --level 5 服务名 on

### 运行级别（centos6)

![](https://github.com/geekerstar/dive-in-interview/blob/master/img/10.jpg)

### systemctl(centos7)
注册在系统中的标准化程序

有方便统一的管理昂是（常用的方法）
- systemctl start 服务名（XXXX.service)
- systemctl start 服务名（XXXX.service)
- systemctl stop 服务名（XXXX.service)
- systemctl reload 服务名（XXXX.service)
- systemctl status 服务名（XXXX.service)

查看服务的方法 /usr/lib/systemd/system

查看服务的命令
- systemctl list-unit-files
- systemctl --type service

通过systemctl命令设置自启动
- 自启动systemctl enable service_name
- 不自启动systemctl disable service_name

## git分支相关命令
创建分支
- git branch <分支名>
- git branch -v 查看分支

切换分支
- git checkout <分支名>
- 一步完成：git checkout -b <分支名>

合并分支
- 先切换到主干 git checkout master
- git merge <分支名>

删除分支
- 先切换到主干 git checkout master
- git branch -D <分支名>

## Redis持久化
![](https://github.com/geekerstar/dive-in-interview/blob/master/img/11.png)

![](https://github.com/geekerstar/dive-in-interview/blob/master/img/12.jpg)

![](https://github.com/geekerstar/dive-in-interview/blob/master/img/13.jpg)

![](https://github.com/geekerstar/dive-in-interview/blob/master/img/14.jpg)

## 单点登录的实现

- 单点登录：一处登录多处使用！
- 前提：单点登录多使用在分布式系统中

![](https://github.com/geekerstar/dive-in-interview/blob/master/img/15.jpg)

```text
Demo：
参观动物园流程：
检票员=认证中心模块
1.	我直接带着大家进动物园，则会被检票员拦住【看我们是否有门票】，没有[售票处买票]
登录=买票
2.	我去买票【带着票，带着大家一起准备进入动物园】检票员check【有票】
Token=piao
3.	我们手中有票就可以任意观赏动物的每处景点。
京东：单点登录，是将token放入到cookie中的。
	案例：将浏览器的cookie禁用，则在登录京东则失败！无论如何登录不了！
```

## 购物车实现过程
```text
购物车：
1.	购物车跟用户的关系?
    a)	一个用户必须对应一个购物车【一个用户不管买多少商品，都会存在属于自己的购物车中。】
    b)	单点登录一定在购物车之前。
2.	跟购物车有关的操作有哪些?
    a)	添加购物车
        i.	用户未登录状态
            1.	添加到什么地方?未登录将数据保存到什么地方?
                a)	Redis? --- 京东
                b)	Cookie? --- 自己开发项目的时候【如果浏览器禁用cookie】
        ii.	用户登录状态
            1.	Redis 缓存中 【读写速度快】
                a)	Hash ：hset(key,field,value)
                    i.	Key:user:userId:cart
                    ii.	Hset(key,skuId,value);
            2.	存在数据库中【oracle，mysql】
     b)	展示购物车
        i.	未登录状态展示
            1.	直接从cookie 中取得数据展示即可
        ii.	登录状态
            1.	用户一旦登录：必须显示数据库【redis】+cookie 中的购物车的数据
                a)	Cookie 中有三条记录
                b)	Redis中有五条记录
                c)	真正展示的时候应该是八条记录
```

## 消息队列在项目中的使用

背景：在分布式系统中是如何处理高并发的。 	

由于在高并发的环境下，来不及同步处理用户发送的请求，则会导致请求发生阻塞。比如说，大量的insert，update之类的请求同时到达数据库MYSQL，直接导致无数的行锁表锁，甚至会导致请求堆积很多。从而触发 too many connections 错误。使用消息队列可以解决【异步通信】

1、异步

![](https://github.com/geekerstar/dive-in-interview/blob/master/img/16.jpg)

2、并行

![](https://github.com/geekerstar/dive-in-interview/blob/master/img/17.jpg)

3、排队

![](https://github.com/geekerstar/dive-in-interview/blob/master/img/18.jpg)

### 消息队列电商使用场景

![](https://github.com/geekerstar/dive-in-interview/blob/master/img/19.jpg)

### 消息队列的弊端：
消息的不确定性：延迟队列，轮询技术来解决该问题即可！

推荐大家使用activemq！环境都是java。

## Elasticsearch 和 solr 的区别
```text
背景：它们都是基于Lucene搜索服务器基础之上开发，一款优秀的，高性能的企业级搜索服务器。【是因为他们都是基于分词技术构建的倒排索引的方式进行查询】
开发语言：java语言开发
诞生时间：
Solr ：2004年诞生。
Es：2010年诞生。
Es 更新【功能越强大】

区别：
1.	当实时建立索引的时候，solr会产生io阻塞，而es则不会，es查询性能要高于solr。
2.	在不断动态添加数据的时候，solr的检索效率会变的低下，而es则没有什么变化。
3.	Solr利用zookeeper进行分布式管理，而es自身带有分布式系统管理功能。Solr一般都要部署到web服务器上，比如tomcat。启动tomcat的时候需要配置tomcat与solr的关联。【Solr 的本质 是一个动态web项目】
4.	Solr支持更多的格式数据[xml,json,csv等]，而es仅支持json文件格式。
5.	Solr是传统搜索应用的有力解决方案，但是es更适用于新兴的实时搜索应用。
    a)	单纯的对已有数据进行检索的时候，solr效率更好，高于es。
6.	Solr官网提供的功能更多，而es本身更注重于核心功能，高级功能多有第三方插件。

```

## Redis在项目中的使用场景
![](https://github.com/geekerstar/dive-in-interview/blob/master/img/20.png)

