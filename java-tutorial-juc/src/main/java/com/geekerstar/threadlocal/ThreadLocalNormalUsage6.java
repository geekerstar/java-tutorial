package com.geekerstar.threadlocal;

/**
 * @author geekerstar
 * @date 2020/2/7 19:59
 * @description 用法2，避免传递参数的麻烦
 */
public class ThreadLocalNormalUsage6 {
    public static void main(String[] args) {
        new Service1().process();
    }

}

class Service1 {
    public void process() {
        User user = new User("Geekerstar");
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}

class Service2 {
    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service2拿到用户名："+user.name);
        new Service3().process();
    }
}

class Service3 {
    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service3拿到用户名："+user.name);
        // 防止OOM，不用了就Remove，避免内存泄露
        UserContextHolder.holder.remove();
    }
}

class User{
    String name;

    public User(String name) {
        this.name = name;
    }
}

class UserContextHolder{
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}
