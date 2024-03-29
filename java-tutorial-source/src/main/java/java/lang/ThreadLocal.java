/*
 * Copyright (c) 1997, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package java.lang;
import java.lang.ref.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * 提供线程局部变量，这种变量是线程自己独享的，线程之间互不影响
 * This class provides thread-local variables.  These variables differ from
 * their normal counterparts in that each thread that accesses one (via its {@code get} or {@code set} method) has its own,
 * independently initialized copy of the variable.  {@code ThreadLocal} instances are typically private
 * static fields in classes that wish to associate state with a thread (e.g.,
 * a user ID or Transaction ID).
 *
 * <p>For example, the class below generates unique identifiers local to each thread.
 * A thread's id is assigned the first time it invokes {@code ThreadId.get()} and remains unchanged on subsequent calls.
 * <pre>
 * import java.util.concurrent.atomic.AtomicInteger;
 *
 * public class ThreadId {
 *     // Atomic integer containing the next thread ID to be assigned
 *     private static final AtomicInteger nextId = new AtomicInteger(0);
 *
 *     // Thread local variable containing each thread's ID
 *     private static final ThreadLocal&lt;Integer&gt; threadId =
 *         new ThreadLocal&lt;Integer&gt;() {
 *             &#64;Override protected Integer initialValue() {
 *                 return nextId.getAndIncrement();
 *         }
 *     };
 *
 *     // Returns the current thread's unique ID, assigning it if necessary
 *     public static int get() {
 *         return threadId.get();
 *     }
 * }
 * </pre>
 * <p>Each thread holds an implicit reference to its copy of a thread-local
 * variable as long as the thread is alive and the {@code ThreadLocal}
 * instance is accessible; after a thread goes away, all of its copies of
 * thread-local instances are subject to garbage collection (unless other
 * references to these copies exist).
 *
 * @author  Josh Bloch and Doug Lea
 * @since   1.2
 */
// ThreadLocal 类是泛型的，可以放任意值
public class ThreadLocal<T> {
    /**
     * ThreadLocals rely on per-thread linear-probe hash maps attached
     * to each thread (Thread.threadLocals and
     * inheritableThreadLocals).  The ThreadLocal objects act as keys,
     * searched via threadLocalHashCode.  This is a custom hash code
     * (useful only within ThreadLocalMaps) that eliminates collisions
     * in the common case where consecutively constructed ThreadLocals
     * are used by the same threads, while remaining well-behaved in
     * less common cases.
     */
    private final int threadLocalHashCode = nextHashCode();

    /**
     * The next hash code to be given out. Updated atomically. Starts at zero.
     */
    // nextHashCode 主要作用是当前 ThreadLocal 赋唯一值，计算当前 ThreadLocal 在 ThreadLocalMap 中的索引位置
    // 被 static 修饰非常关键，因为一个线程在处理业务的过程中，ThreadLocalMap 是会被 set 多个 ThreadLocal 的，多个 ThreadLocal 就依靠 threadLocalHashCode 进行区分
    // static + AtomicInteger 保证了在一台机器上每个 ThreadLocal 的 threadLocalHashCode 是唯一的
    private static AtomicInteger nextHashCode =
        new AtomicInteger();

    /**
     * The difference between successively generated hash codes - turns
     * implicit sequential thread-local IDs into near-optimally spread
     * multiplicative hash values for power-of-two-sized tables.
     */
    // 生成hash code间隙为这个魔数，可以让生成出来的值或者说ThreadLocal的ID较为均匀地分布在2的幂大小的数组中。
    private static final int HASH_INCREMENT = 0x61c88647;

    /**
     * Returns the next hash code.
     */
    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }

    /**
     * Returns the current thread's "initial value" for this
     * thread-local variable.  This method will be invoked the first
     * time a thread accesses the variable with the {@link #get}
     * method, unless the thread previously invoked the {@link #set}
     * method, in which case the {@code initialValue} method will not
     * be invoked for the thread.  Normally, this method is invoked at
     * most once per thread, but it may be invoked again in case of
     * subsequent invocations of {@link #remove} followed by {@link #get}.
     *
     * <p>This implementation simply returns {@code null}; if the
     * programmer desires thread-local variables to have an initial
     * value other than {@code null}, {@code ThreadLocal} must be
     * subclassed, and this method overridden.  Typically, an
     * anonymous inner class will be used.
     *
     * @return the initial value for this thread-local
     */
    protected T initialValue() {
        return null;
    }

    /**
     * Creates a thread local variable. The initial value of the variable is
     * determined by invoking the {@code get} method on the {@code Supplier}.
     *
     * @param <S> the type of the thread local's value
     * @param supplier the supplier to be used to determine the initial value
     * @return a new thread local variable
     * @throws NullPointerException if the specified supplier is null
     * @since 1.8
     */
    public static <S> ThreadLocal<S> withInitial(Supplier<? extends S> supplier) {
        return new SuppliedThreadLocal<>(supplier);
    }

    /**
     * Creates a thread local variable.
     * @see #withInitial(java.util.function.Supplier)
     */
    public ThreadLocal() {
    }

    /**
     * Returns the value in the current thread's copy of this
     * thread-local variable.  If the variable has no value for the
     * current thread, it is first initialized to the value returned
     * by an invocation of the {@link #initialValue} method.
     *
     * @return the current thread's value of this thread-local
     */
    public T get() {
        // 因为 threadLocal 属于线程的属性，所以需要先把当前线程拿出来
        Thread t = Thread.currentThread();
        // 从线程中拿到 ThreadLocalMap
        ThreadLocalMap map = getMap(t);
        if (map != null) {
            // 从 map 中拿到 entry，由于 ThreadLocalMap 在 set 时的 hash 冲突的策略不同，导致拿的时候逻辑也不太一样
            ThreadLocalMap.Entry e = map.getEntry(this);
            // 如果不为空，读取当前 ThreadLocal 中保存的值
            if (e != null) {
                @SuppressWarnings("unchecked")
                T result = (T)e.value;
                return result;
            }
        }
        // 否则给当前线程的 ThreadLocal 初始化，并返回初始值 null
        return setInitialValue();
    }

    /**
     * Variant of set() to establish initialValue. Used instead
     * of set() in case user has overridden the set() method.
     *
     * @return the initial value
     */
    private T setInitialValue() {
        T value = initialValue();
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
        return value;
    }

    /**
     * Sets the current thread's copy of this thread-local variable
     * to the specified value.  Most subclasses will have no need to
     * override this method, relying solely on the {@link #initialValue}
     * method to set the values of thread-locals.
     *
     * @param value the value to be stored in the current thread's copy of
     *        this thread-local.
     */
    // set 操作每个线程都是串行的，不会有线程安全的问题
    public void set(T value) {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        // 当前 thradLocal 之前有设置值，直接设置，否则初始化
        if (map != null)
            map.set(this, value);
        // 初始化ThreadLocalMap
        else
            createMap(t, value);
    }

    /**
     * Removes the current thread's value for this thread-local
     * variable.  If this thread-local variable is subsequently
     * {@linkplain #get read} by the current thread, its value will be
     * reinitialized by invoking its {@link #initialValue} method,
     * unless its value is {@linkplain #set set} by the current thread
     * in the interim.  This may result in multiple invocations of the
     * {@code initialValue} method in the current thread.
     *
     * @since 1.5
     */
     public void remove() {
         ThreadLocalMap m = getMap(Thread.currentThread());
         if (m != null)
             m.remove(this);
     }

    /**
     * Get the map associated with a ThreadLocal. Overridden in
     * InheritableThreadLocal.
     *
     * @param  t the current thread
     * @return the map
     */
    ThreadLocalMap getMap(Thread t) {
        return t.threadLocals;
    }

    /**
     * Create the map associated with a ThreadLocal. Overridden in
     * InheritableThreadLocal.
     *
     * @param t the current thread
     * @param firstValue value for the initial entry of the map
     */
    void createMap(Thread t, T firstValue) {
        t.threadLocals = new ThreadLocalMap(this, firstValue);
    }

    /**
     * Factory method to create map of inherited thread locals.
     * Designed to be called only from Thread constructor.
     *
     * @param  parentMap the map associated with parent thread
     * @return a map containing the parent's inheritable bindings
     */
    // 父线程创建子线程时，就是通过 createInheritedMap 方法传递父线程中的 inheritableThreadLocals
    static ThreadLocalMap createInheritedMap(ThreadLocalMap parentMap) {
        return new ThreadLocalMap(parentMap);
    }

    /**
     * Method childValue is visibly defined in subclass
     * InheritableThreadLocal, but is internally defined here for the
     * sake of providing createInheritedMap factory method without
     * needing to subclass the map class in InheritableThreadLocal.
     * This technique is preferable to the alternative of embedding
     * instanceof tests in methods.
     */
    T childValue(T parentValue) {
        throw new UnsupportedOperationException();
    }

    /**
     * An extension of ThreadLocal that obtains its initial value from
     * the specified {@code Supplier}.
     */
    static final class SuppliedThreadLocal<T> extends ThreadLocal<T> {

        private final Supplier<? extends T> supplier;

        SuppliedThreadLocal(Supplier<? extends T> supplier) {
            this.supplier = Objects.requireNonNull(supplier);
        }

        @Override
        protected T initialValue() {
            return supplier.get();
        }
    }

    /**
     * ThreadLocalMap 是定制的 map，只适合用于维护线程的本地值
     * ThreadLocalMap is a customized hash map suitable only for maintaining thread local values.
     * No operations are exported outside of the ThreadLocal class.
     * The class is package private to allow declaration of fields in class Thread.
     * To help deal with very large and long-lived usages, the hash table entries use  WeakReferences for keys.
     * However, since reference queues are not used, stale entries are guaranteed to be removed only when  the table starts running out of space.
     */
    static class ThreadLocalMap {

        /**
         * The entries in this hash map extend WeakReference,
         * using its main ref field as the key (which is always a
         * ThreadLocal object).  Note that null keys (i.e. entry.get()
         * == null) mean that the key is no longer referenced, so the
         * entry can be expunged from table.  Such entries are referred to
         * as "stale entries" in the code that follows.
         */
        // 数组中的每个节点值，WeakReference 是弱引用，当没有引用指向时，会直接被回收
        static class Entry extends WeakReference<ThreadLocal<?>> {
            // 当前 ThreadLocal 关联的值
            Object value;
            // WeakReference 的引用 referent 就是 ThreadLocal
            Entry(ThreadLocal<?> k, Object v) {
                super(k);
                value = v;
            }
        }

        /**
         * The initial capacity -- MUST be a power of two.
         */
        // 初始容量，必须为2的幂
        private static final int INITIAL_CAPACITY = 16;

        /**
         * The table, resized as necessary.
         * table.length MUST always be a power of two.
         */
        // 存储 ThreadLocal 的数组大小
        // Entry表，大小必须为2的幂
        private Entry[] table;

        /**
         * The number of entries in the table.
         */
        // 表里entry的个数
        private int size = 0;

        /**
         * The next size value at which to resize.
         */
        // 扩容的阈值 重新分配表大小的阈值，默认为0
        private int threshold; // Default to 0

        /**
         * Set the resize threshold to maintain at worst a 2/3 load factor.
         */
        // 设置resize阈值以维持最坏2/3的装载因子
        private void setThreshold(int len) {
            threshold = len * 2 / 3;
        }

        /**
         * Increment i modulo len.
         */
        // 环形意义的下一个索引
        private static int nextIndex(int i, int len) {
            return ((i + 1 < len) ? i + 1 : 0);
        }

        /**
         * Decrement i modulo len.
         */
        // 环形意义的上一个索引
        private static int prevIndex(int i, int len) {
            return ((i - 1 >= 0) ? i - 1 : len - 1);
        }

        /**
         * Construct a new map initially containing (firstKey, firstValue).
         * ThreadLocalMaps are constructed lazily, so we only create
         * one when we have at least one entry to put in it.
         */
        // 构造一个包含firstKey和firstValue的map。
        // ThreadLocalMap是惰性构造的，所以只有当至少要往里面放一个元素的时候才会构建它。
        ThreadLocalMap(ThreadLocal<?> firstKey, Object firstValue) {
            // 初始化table数组
            table = new Entry[INITIAL_CAPACITY];
            // 用firstKey的threadLocalHashCode与初始大小16取模得到哈希值
            int i = firstKey.threadLocalHashCode & (INITIAL_CAPACITY - 1);
            // 初始化该节点
            table[i] = new Entry(firstKey, firstValue);
            // 设置节点表大小为1
            size = 1;
            // 设定扩容阈值
            setThreshold(INITIAL_CAPACITY);
        }

        /**
         * Construct a new map including all Inheritable ThreadLocals
         * from given parent map. Called only by createInheritedMap.
         *
         * @param parentMap the map associated with parent thread.
         */
        private ThreadLocalMap(ThreadLocalMap parentMap) {
            // 得到父线程的 table
            Entry[] parentTable = parentMap.table;
            int len = parentTable.length;
            setThreshold(len);
            table = new Entry[len];

            // 从父线程中拷贝值到子线程
            for (int j = 0; j < len; j++) {
                Entry e = parentTable[j];
                if (e != null) {
                    @SuppressWarnings("unchecked")
                    // 拿到 key
                    ThreadLocal<Object> key = (ThreadLocal<Object>) e.get();
                    if (key != null) {
                        // 拿到 value， childValue 方法子类是可以重新实现的
                        // InheritableThreadLocal 是 ThreadLocal 的子类，InheritableThreadLocal 的 childValue 方法是直接返回 e.value，这是一种浅拷贝。
                        // 如果你想做一些值的拷贝的话，可以重新实现 ThreadLocal.childValue 方法，做值的深度拷贝
                        Object value = key.childValue(e.value);
                        Entry c = new Entry(key, value);
                        // 计算 key 在 table 的索引位置，实际上就是取模运算
                        int h = key.threadLocalHashCode & (len - 1);
                        // 如果 h 索引位置中的值已经存在了，找 i 的下一个索引位置，直到找到为空的位置为止
                        // 这里的冲突是很有可能发生的，比如 len 是 3，那么 6、9 对 3 取模，算出来的索引位置都是 0，于是往下找到为空的位置，这是一种简单的冲突策略吧
                        while (table[h] != null)
                            h = nextIndex(h, len);
                        // 赋值
                        table[h] = c;
                        size++;
                    }
                }
            }
        }

        /**
         * Get the entry associated with key.
         * This method itself handles only the fast path: a direct hit of existing key.
         * It otherwise relays to getEntryAfterMiss.
         * This is designed to maximize performance for direct hits,
         * in part by making this method readily inlinable.
         *
         * @param  key the thread local object
         * @return the entry associated with key, or null if no such
         */
        // 得到当前 thradLocal 对应的值，值的类型是由 thradLocal 的泛型决定的
        // 由于 thradLocalMap set 时解决 Hash 冲突的逻辑，导致 thradLocalMap get 时的逻辑也会相应不同
        // 首先尝试根据 hashcode 取模数组大小 = 索引位置i 寻找，找不到的话，自旋把 i+1，直到找到 thradLocal 为至
        private Entry getEntry(ThreadLocal<?> key) {
            // 计算索引位置：ThreadLocal 的 hashCode 取模数组大小
            // 根据key这个ThreadLocal的ID来获取索引，也即哈希值
            int i = key.threadLocalHashCode & (table.length - 1);
            Entry e = table[i];
            // e 不为空，并且 e 的 ThreadLocal 的内存地址和 key 相同，直接返回，否则就是没有找到，继续通过 getEntryAfterMiss 方法找
            // 对应的entry存在且未失效且弱引用指向的ThreadLocal就是key，则命中返回
            if (e != null && e.get() == key)
                return e;
            else
                // 因为用的是线性探测，所以往后找还是有可能能够找到目标Entry的。
                return getEntryAfterMiss(key, i, e);
        }

        /**
         * Version of getEntry method for use when key is not found in
         * its direct hash slot.
         *
         * @param  key the thread local object
         * @param  i the table index for key's hash code
         * @param  e the entry at table[i]
         * @return the entry associated with key, or null if no such
         */
        // 自旋 i+1，直到找到为止
        // 调用getEntry未直接命中的时候调用此方法
        private Entry getEntryAfterMiss(ThreadLocal<?> key, int i, Entry e) {
            Entry[] tab = table;
            int len = tab.length;
            // 在大量使用不同 key 的 ThreadLocal 时，其实还蛮耗性能的
            // 基于线性探测法不断向后探测直到遇到空entry。
            while (e != null) {
                ThreadLocal<?> k = e.get();
                // 内存地址一样，表示找到了
                // 找到目标
                if (k == key)
                    return e;
                // 删除没用的 key
                if (k == null)
                    // 该entry对应的ThreadLocal已经被回收，调用expungeStaleEntry来清理无效的entry
                    expungeStaleEntry(i);
                // 继续使索引位置 + 1
                else
                    // 环形意义下往后面走
                    i = nextIndex(i, len);
                e = tab[i];
            }
            return null;
        }

        /**
         * Set the value associated with key.
         *ThreadLocal
         * @param key the thread local object
         * @param value the value to be set
         */
        private void set(ThreadLocal<?> key, Object value) {

            // We don't use a fast path as with get() because it is at
            // least as common to use set() to create new entries as
            // it is to replace existing ones, in which case, a fast
            // path would fail more often than not.

            Entry[] tab = table;
            int len = tab.length;
            // 计算 key 在数组中的下标
            int i = key.threadLocalHashCode & (len-1);

            // 查看 i 索引位置有没有值，有值的话，索引位置 + 1，直到找到没有值的位置
            // 这种解决 hash 冲突的策略，也导致了其在 get 时查找策略有所不同，体现在 getEntryAfterMiss 中
            // 线性探测
            for (Entry e = tab[i];
                 e != null;
                 // nextIndex 就是让在不超过数组长度的基础上，把数组的索引位置 + 1
                 e = tab[i = nextIndex(i, len)]) {
                ThreadLocal<?> k = e.get();
                // 找到内存地址一样的 ThreadLocal，直接替换
                // 找到对应的entry
                if (k == key) {
                    e.value = value;
                    return;
                }
                // 当前 key 是 null，说明 ThreadLocal 被清理了，直接替换掉
                // 替换失效的entry
                if (k == null) {
                    replaceStaleEntry(key, value, i);
                    return;
                }
            }
            // 当前 i 位置是无值的，可以被当前 thradLocal 使用
            tab[i] = new Entry(key, value);
            int sz = ++size;
            // 当数组大小大于等于扩容阈值(数组大小的三分之二)时，进行扩容
            if (!cleanSomeSlots(i, sz) && sz >= threshold)
                rehash();
        }

        /**
         * Remove the entry for key.
         */
        /**
         * 从map中删除ThreadLocal
         */
        private void remove(ThreadLocal<?> key) {
            Entry[] tab = table;
            int len = tab.length;
            int i = key.threadLocalHashCode & (len-1);
            for (Entry e = tab[i];
                 e != null;
                 e = tab[i = nextIndex(i, len)]) {
                if (e.get() == key) {
                    // 显式断开弱引用
                    e.clear();
                    // 进行段清理
                    expungeStaleEntry(i);
                    return;
                }
            }
        }

        /**
         * Replace a stale entry encountered during a set operation
         * with an entry for the specified key.  The value passed in
         * the value parameter is stored in the entry, whether or not
         * an entry already exists for the specified key.
         *
         * As a side effect, this method expunges all stale entries in the
         * "run" containing the stale entry.  (A run is a sequence of entries
         * between two null slots.)
         *
         * @param  key the key
         * @param  value the value to be associated with key
         * @param  staleSlot index of the first stale entry encountered while
         *         searching for key.
         */
        private void replaceStaleEntry(ThreadLocal<?> key, Object value,
                                       int staleSlot) {
            Entry[] tab = table;
            int len = tab.length;
            Entry e;

            // Back up to check for prior stale entry in current run.
            // We clean out whole runs at a time to avoid continual
            // incremental rehashing due to garbage collector freeing
            // up refs in bunches (i.e., whenever the collector runs).
            // 向前扫描，查找最前的一个无效slot
            int slotToExpunge = staleSlot;
            for (int i = prevIndex(staleSlot, len);
                 (e = tab[i]) != null;
                 i = prevIndex(i, len))
                if (e.get() == null)
                    slotToExpunge = i;

            // Find either the key or trailing null slot of run, whichever
            // occurs first
            // 向后遍历table
            for (int i = nextIndex(staleSlot, len);
                 (e = tab[i]) != null;
                 i = nextIndex(i, len)) {
                ThreadLocal<?> k = e.get();

                // If we find key, then we need to swap it
                // with the stale entry to maintain hash table order.
                // The newly stale slot, or any other stale slot
                // encountered above it, can then be sent to expungeStaleEntry
                // to remove or rehash all of the other entries in run.
                // 找到了key，将其与无效的slot交换
                if (k == key) {
                    // 更新对应slot的value值
                    e.value = value;

                    tab[i] = tab[staleSlot];
                    tab[staleSlot] = e;

                    // Start expunge at preceding stale entry if it exists
                    /*
                     * 如果在整个扫描过程中（包括函数一开始的向前扫描与i之前的向后扫描）
                     * 找到了之前的无效slot则以那个位置作为清理的起点，
                     * 否则则以当前的i作为清理起点
                     */
                    if (slotToExpunge == staleSlot)
                        slotToExpunge = i;
                    // 从slotToExpunge开始做一次连续段的清理，再做一次启发式清理
                    cleanSomeSlots(expungeStaleEntry(slotToExpunge), len);
                    return;
                }

                // If we didn't find stale entry on backward scan, the
                // first stale entry seen while scanning for key is the
                // first still present in the run.
                // 如果当前的slot已经无效，并且向前扫描过程中没有无效slot，则更新slotToExpunge为当前位置
                if (k == null && slotToExpunge == staleSlot)
                    slotToExpunge = i;
            }

            // If key not found, put new entry in stale slot
            // 如果key在table中不存在，则在原地放一个即可
            tab[staleSlot].value = null;
            tab[staleSlot] = new Entry(key, value);

            // If there are any other stale entries in run, expunge them
            // 在探测过程中如果发现任何无效slot，则做一次清理（连续段清理+启发式清理
            if (slotToExpunge != staleSlot)
                cleanSomeSlots(expungeStaleEntry(slotToExpunge), len);
        }

        /**
         * Expunge a stale entry by rehashing any possibly colliding entries
         * lying between staleSlot and the next null slot.  This also expunges
         * any other stale entries encountered before the trailing null.  See
         * Knuth, Section 6.4
         *
         * @param staleSlot index of slot known to have null key
         * @return the index of the next null slot after staleSlot
         * (all between staleSlot and this slot will have been checked
         * for expunging).
         */
        /**
         * 这个函数是ThreadLocal中核心清理函数，它做的事情很简单：
         * 就是从staleSlot开始遍历，将无效（弱引用指向对象被回收）清理，即对应entry中的value置为null，将指向这个entry的table[i]置为null，直到扫到空entry。
         * 另外，在过程中还会对非空的entry作rehash。
         * 可以说这个函数的作用就是从staleSlot开始清理连续段中的slot（断开强引用，rehash slot等）
         */
        private int expungeStaleEntry(int staleSlot) {
            Entry[] tab = table;
            int len = tab.length;

            // expunge entry at staleSlot
            // 因为entry对应的ThreadLocal已经被回收，value设为null，显式断开强引用
            tab[staleSlot].value = null;
            // 显式设置该entry为null，以便垃圾回收
            tab[staleSlot] = null;
            size--;

            // Rehash until we encounter null
            Entry e;
            int i;
            for (i = nextIndex(staleSlot, len);
                 (e = tab[i]) != null;
                 i = nextIndex(i, len)) {
                ThreadLocal<?> k = e.get();
                // 清理对应ThreadLocal已经被回收的entry
                if (k == null) {
                    e.value = null;
                    tab[i] = null;
                    size--;
                } else {
                    /*
                     * 对于还没有被回收的情况，需要做一次rehash。
                     *
                     * 如果对应的ThreadLocal的ID对len取模出来的索引h不为当前位置i，
                     * 则从h向后线性探测到第一个空的slot，把当前的entry给挪过去。
                     */
                    int h = k.threadLocalHashCode & (len - 1);
                    if (h != i) {
                        tab[i] = null;

                        /*
                         * 在原代码的这里有句注释值得一提，原注释如下：
                         *
                         * Unlike Knuth 6.4 Algorithm R, we must scan until
                         * null because multiple entries could have been stale.
                         *
                         * 这段话提及了Knuth高德纳的著作TAOCP（《计算机程序设计艺术》）的6.4章节（散列）
                         * 中的R算法。R算法描述了如何从使用线性探测的散列表中删除一个元素。
                         * R算法维护了一个上次删除元素的index，当在非空连续段中扫到某个entry的哈希值取模后的索引
                         * 还没有遍历到时，会将该entry挪到index那个位置，并更新当前位置为新的index，
                         * 继续向后扫描直到遇到空的entry。
                         *
                         * ThreadLocalMap因为使用了弱引用，所以其实每个slot的状态有三种也即
                         * 有效（value未回收），无效（value已回收），空（entry==null）。
                         * 正是因为ThreadLocalMap的entry有三种状态，所以不能完全套高德纳原书的R算法。
                         *
                         * 因为expungeStaleEntry函数在扫描过程中还会对无效slot清理将之转为空slot，
                         * 如果直接套用R算法，可能会出现具有相同哈希值的entry之间断开（中间有空entry）。
                         */
                        while (tab[h] != null)
                            h = nextIndex(h, len);
                        tab[h] = e;
                    }
                }
            }
            // 返回staleSlot之后第一个空的slot索引
            return i;
        }

        /**
         * Heuristically scan some cells looking for stale entries.
         * This is invoked when either a new element is added, or
         * another stale one has been expunged. It performs a
         * logarithmic number of scans, as a balance between no
         * scanning (fast but retains garbage) and a number of scans
         * proportional to number of elements, that would find all
         * garbage but would cause some insertions to take O(n) time.
         *
         * @param i a position known NOT to hold a stale entry. The
         * scan starts at the element after i.
         *
         * @param n scan control: {@code log2(n)} cells are scanned,
         * unless a stale entry is found, in which case
         * {@code log2(table.length)-1} additional cells are scanned.
         * When called from insertions, this parameter is the number
         * of elements, but when from replaceStaleEntry, it is the
         * table length. (Note: all this could be changed to be either
         * more or less aggressive by weighting n instead of just
         * using straight log n. But this version is simple, fast, and
         * seems to work well.)
         *
         * @return true if any stale entries have been removed.
         */
        /**
         * 启发式地清理slot,
         * i对应entry是非无效（指向的ThreadLocal没被回收，或者entry本身为空）
         * n是用于控制控制扫描次数的
         * 正常情况下如果log n次扫描没有发现无效slot，函数就结束了
         * 但是如果发现了无效的slot，将n置为table的长度len，做一次连续段的清理
         * 再从下一个空的slot开始继续扫描
         *
         * 这个函数有两处地方会被调用，一处是插入的时候可能会被调用，另外个是在替换无效slot的时候可能会被调用，
         * 区别是前者传入的n为元素个数，后者为table的容量
         */
        private boolean cleanSomeSlots(int i, int n) {
            boolean removed = false;
            Entry[] tab = table;
            int len = tab.length;
            do {
                // i在任何情况下自己都不会是一个无效slot，所以从下一个开始判断
                i = nextIndex(i, len);
                Entry e = tab[i];
                if (e != null && e.get() == null) {
                    // 扩大扫描控制因子
                    n = len;
                    removed = true;
                    // 清理一个连续段
                    i = expungeStaleEntry(i);
                }
            } while ( (n >>>= 1) != 0);
            return removed;
        }

        /**
         * Re-pack and/or re-size the table. First scan the entire
         * table removing stale entries. If this doesn't sufficiently
         * shrink the size of the table, double the table size.
         */
        private void rehash() {
            // 做一次全量清理
            expungeStaleEntries();

            // Use lower threshold for doubling to avoid hysteresis
            // 这里加了一个判断，当数组已用空间大于等于数组的四分之三时，扩容，刚才是判断三分之二，这里的判断更加严格，原因：Use lower threshold for doubling to avoid hysteresis
            /*
             * 因为做了一次清理，所以size很可能会变小。
             * ThreadLocalMap这里的实现是调低阈值来判断是否需要扩容，
             * threshold默认为len*2/3，所以这里的threshold - threshold / 4相当于len/2
             */
            if (size >= threshold - threshold / 4)
                resize();
        }

        /**
         * Double the capacity of the table.
         */
        /**
         * 扩容，因为需要保证table的容量len为2的幂，所以扩容即扩大2倍
         */
        private void resize() {
            // 拿出旧的数组
            Entry[] oldTab = table;
            int oldLen = oldTab.length;
            // 新数组的大小为老数组的两倍
            int newLen = oldLen * 2;
            // 初始化新数组
            Entry[] newTab = new Entry[newLen];
            int count = 0;
            // 老数组的值拷贝到新数组上
            for (int j = 0; j < oldLen; ++j) {
                Entry e = oldTab[j];
                if (e != null) {
                    ThreadLocal<?> k = e.get();
                    if (k == null) {
                        e.value = null; // Help the GC
                    } else {
                        // 线性探测来存放Entry
                        // 计算 ThreadLocal 在新数组中的位置
                        int h = k.threadLocalHashCode & (newLen - 1);
                        // 如果索引 h 的位置值不为空，往后+1，直到找到值为空的索引位置
                        while (newTab[h] != null)
                            h = nextIndex(h, newLen);
                        // 给新数组赋值
                        newTab[h] = e;
                        count++;
                    }
                }
            }
            // 给新数组初始化下次扩容阈值，为数组长度的三分之二
            setThreshold(newLen);
            size = count;
            table = newTab;
        }

        /**
         * Expunge all stale entries in the table.
         */
        /*
         * 做一次全量清理
         */
        private void expungeStaleEntries() {
            Entry[] tab = table;
            int len = tab.length;
            for (int j = 0; j < len; j++) {
                Entry e = tab[j];
                if (e != null && e.get() == null)
                    /*
                     * 个人觉得这里可以取返回值，如果大于j的话取了用，这样也是可行的。
                     * 因为expungeStaleEntry执行过程中是把连续段内所有无效slot都清理了一遍了。
                     */
                    expungeStaleEntry(j);
            }
        }
    }
}
