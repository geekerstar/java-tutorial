/*
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

/*
 *
 *
 *
 *
 *
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

package java.util.concurrent;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.ref.WeakReference;
import java.util.Spliterators;
import java.util.Spliterator;

/**
 * A bounded {@linkplain BlockingQueue blocking queue} backed by an
 * array.  This queue orders elements FIFO (first-in-first-out).  The
 * <em>head</em> of the queue is that element that has been on the
 * queue the longest time.  The <em>tail</em> of the queue is that
 * element that has been on the queue the shortest time. New elements
 * are inserted at the tail of the queue, and the queue retrieval
 * operations obtain elements at the head of the queue.
 *
 * 有界的阻塞数组，按照先入先出进行排序，从队尾插入数据数据，从队头拿数据
 *
 *
 * <p>This is a classic &quot;bounded buffer&quot;, in which a
 * fixed-sized array holds elements inserted by producers and
 * extracted by consumers.  Once created, the capacity cannot be
 * changed.  Attempts to {@code put} an element into a full queue
 * will result in the operation blocking; attempts to {@code take} an
 * element from an empty queue will similarly block.
 *
 * 容量一旦创建，后续大小无法修改
 * ，尝试往满队列中 put 数据会被阻塞，从空队列中拿数据也会被阻塞
 *
 * <p>This class supports an optional fairness policy for ordering
 * waiting producer and consumer threads.  By default, this ordering
 * is not guaranteed. However, a queue constructed with fairness set
 * to {@code true} grants threads access in FIFO order. Fairness
 * generally decreases throughput but reduces variability and avoids
 * starvation.
 *
 * 公平性减低吞吐量
 *
 * <p>This class and its iterator implement all of the
 * <em>optional</em> methods of the {@link Collection} and {@link
 * Iterator} interfaces.
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @since 1.5
 * @author Doug Lea
 * @param <E> the type of elements held in this collection
 */

/**
 * （1）ArrayBlockingQueue不需要扩容，因为是初始化时指定容量，并循环利用数组；
 * （2）ArrayBlockingQueue利用takeIndex和putIndex循环利用数组；
 * （3）入队和出队各定义了四组方法为满足不同的用途；
 * （4）利用重入锁和两个条件保证并发安全；
 *
 * https://mp.weixin.qq.com/s?__biz=MzI5NTYwNDQxNA==&mid=2247485328&idx=2&sn=b955b6134bdbd8a863f1676223ce7d77&chksm=ec505e41db27d75727e7035c8ecf1feaf39c95b26b48650338fb01586ef8388f180b6b241671&mpshare=1&scene=1&srcid=&sharer_sharetime=1567245167185&sharer_shareid=535c00d0d7095600f2fcdf96cc5a31ba#rd
 */
public class ArrayBlockingQueue<E> extends AbstractQueue<E>
        implements BlockingQueue<E>, java.io.Serializable {

    /**
     * Serialization ID. This class relies on default serialization
     * even for the items array, which is default-serialized, even if
     * it is empty. Otherwise it could not be declared final, which is
     * necessary here.
     */
    private static final long serialVersionUID = -817911632652898426L;

    //（1）利用数组存储元素；
    //（2）通过放指针和取指针来标记下一次操作的位置；
    //（3）利用重入锁来保证并发安全；

    // 队列存放在 object 的数组里面
    // 数组大小必须在初始化的时候手动设置，没有默认大小
    final Object[] items;

    // 下次拿数据的时候的索引位置
    int takeIndex;

    // 下次放数据的索引位置
    int putIndex;

    // 当前已有元素的大小
    int count;

    // 可重入的锁
    final ReentrantLock lock;

    // take的队列 非空条件
    private final Condition notEmpty;

    // put的队列 非满条件
    private final Condition notFull;


    /**
     * Shared state for currently active iterators, or null if there
     * are known not to be any.  Allows queue operations to update
     * iterator state.
     */
    transient Itrs itrs = null;

    // Internal helper methods

    /**
     * Circularly decrement i.
     */
    final int dec(int i) {
        return ((i == 0) ? items.length : i) - 1;
    }

    /**
     * Returns item at index i.
     */
    @SuppressWarnings("unchecked")
    final E itemAt(int i) {
        return (E) items[i];
    }

    /**
     * Throws NullPointerException if argument is null.
     *
     * @param v the element
     */
    private static void checkNotNull(Object v) {
        if (v == null)
            throw new NullPointerException();
    }

    /**
     * Inserts element at current put position, advances, and signals.
     * Call only when holding lock.
     */


    /**
     * Extracts element at current take position, advances, and signals.
     * Call only when holding lock.
     */


    /**
     * Deletes item at array index removeIndex.
     * Utility for remove(Object) and iterator.remove.
     * Call only when holding lock.
     */
    // 一共有两种情况：
    // 1：如果下次要拿数据的位置是 2， 而要删除的位置正好也是 2，那么就把位置 2 的数据置为
    // null ,并标识下次拿数据的位置是 3。
    // 2：找到要删除元素的下一个
    // 如果下一个元素不是 putIndex，就把下一个元素往前移动一位
    // 如果下一个元素是 putIndex，把 putIndex 的值修改成删除的位置
    void removeAt(final int removeIndex) {
        final Object[] items = this.items;
        // 情况1 如果删除位置正好等于下次要拿数据的位置
        if (removeIndex == takeIndex) {
            // 下次要拿数据的位置直接置空
            items[takeIndex] = null;
            // 要拿数据的位置往后移动一位
            if (++takeIndex == items.length)
                takeIndex = 0;
            count--;
            if (itrs != null)
                itrs.elementDequeued();
        // 情况 2
        } else {
            final int putIndex = this.putIndex;
            for (int i = removeIndex;;) {
                // 找到要删除元素的下一个
                int next = i + 1;
                if (next == items.length)
                    next = 0;
                // 下一个元素不是 putIndex
                if (next != putIndex) {
                    // 下一个元素往前移动一位
                    items[i] = items[next];
                    i = next;
                // 下一个元素是 putIndex
                } else {
                    // 删除元素
                    items[i] = null;
                    // 下次放元素时，应该从本次删除的元素放
                    this.putIndex = i;
                    break;
                }
            }
            count--;
            if (itrs != null)
                itrs.removedAt(removeIndex);
        }
        notFull.signal();
    }

    /**
     * Creates an {@code ArrayBlockingQueue} with the given (fixed)
     * capacity and default access policy.
     *
     * @param capacity the capacity of this queue
     * @throws IllegalArgumentException if {@code capacity < 1}
     */
    public ArrayBlockingQueue(int capacity) {
        this(capacity, false);
    }


    /**
     * Creates an {@code ArrayBlockingQueue} with the given (fixed)
     * capacity and the specified access policy.
     *
     * @param capacity the capacity of this queue
     * @param fair if {@code true} then queue accesses for threads blocked
     *        on insertion or removal, are processed in FIFO order;
     *        if {@code false} the access order is unspecified.
     * @throws IllegalArgumentException if {@code capacity < 1}
     */
    // 公平和非公平指的是读写锁的，比如说现在队列是满的，还有很多线程执行
    // put 操作，必然会有很多线程等待，在队列不满时，会唤醒等待的线程
    // fair 如果是 true 话，就会按照线程等待的排队顺序唤醒线程
    // 如果是 false 的话，就会随机唤醒线程
    // 通过利用锁的公平和非公平，来实现了 put 和 take 阻塞被唤醒时的公平和非公平

    //（1）ArrayBlockingQueue初始化时必须传入容量，也就是数组的大小；
    //（2）可以通过构造方法控制重入锁的类型是公平锁还是非公平锁；
    public ArrayBlockingQueue(int capacity, boolean fair) {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        // 初始化数组
        this.items = new Object[capacity];
        // 创建重入锁及两个条件
        lock = new ReentrantLock(fair);
        // 队列不为空 Condition，在 put 成功时使用
        notEmpty = lock.newCondition();
        // 队列不满 Condition，在 take 成功时使用
        notFull =  lock.newCondition();
    }

    /**
     * Creates an {@code ArrayBlockingQueue} with the given (fixed)
     * capacity, the specified access policy and initially containing the
     * elements of the given collection,
     * added in traversal order of the collection's iterator.
     *
     * @param capacity the capacity of this queue
     * @param fair if {@code true} then queue accesses for threads blocked
     *        on insertion or removal, are processed in FIFO order;
     *        if {@code false} the access order is unspecified.
     * @param c the collection of elements to initially contain
     * @throws IllegalArgumentException if {@code capacity} is less than
     *         {@code c.size()}, or less than 1.
     * @throws NullPointerException if the specified collection or any
     *         of its elements are null
     */
    public ArrayBlockingQueue(int capacity, boolean fair,
                              Collection<? extends E> c) {
        this(capacity, fair);

        final ReentrantLock lock = this.lock;
        lock.lock(); // Lock only for visibility, not mutual exclusion
        try {
            // i 代表插入的位置
            int i = 0;
            try {
                // 此时需要注意的是，如果 c 的大小超过了数组的大小
                // 是会抛异常的
                for (E e : c) {
                    checkNotNull(e);
                    items[i++] = e;
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new IllegalArgumentException();
            }
            count = i;
            // 如果插入的位置，正好是队尾了，下次需要从 0 开始插入
            putIndex = (i == capacity) ? 0 : i;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Inserts the specified element at the tail of this queue if it is
     * possible to do so immediately without exceeding the queue's capacity,
     * returning {@code true} upon success and throwing an
     * {@code IllegalStateException} if this queue is full.
     *
     * @param e the element to add
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws IllegalStateException if this queue is full
     * @throws NullPointerException if the specified element is null
     */
    //（1）add(e)时如果队列满了则抛出异常；
    //（2）offer(e)时如果队列满了则返回false；
    //（3）put(e)时如果队列满了则使用notFull等待；
    //（4）offer(e, timeout, unit)时如果队列满了则等待一段时间后如果队列依然满就返回false；
    //（5）利用放指针循环使用数组来存储元素；
    public boolean add(E e) {
        // 调用父类的add(e)方法
        return super.add(e);
    }

    /**
     * Inserts the specified element at the tail of this queue if it is
     * possible to do so immediately without exceeding the queue's capacity,
     * returning {@code true} upon success and {@code false} if this queue
     * is full.  This method is generally preferable to method {@link #add},
     * which can fail to insert an element only by throwing an exception.
     *
     * @throws NullPointerException if the specified element is null
     */
    // 新增
    public boolean offer(E e) {
        // 元素不可为空
        checkNotNull(e);
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            // 如果队列是满的，直接返回 false
            if (count == items.length)
                return false;
            else {
                // 如果数组没满就调用入队方法并返回true
                enqueue(e);
                return true;
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Inserts the specified element at the tail of this queue, waiting
     * for space to become available if the queue is full.
     *
     * @throws InterruptedException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    // 新增，如果队列满，无限阻塞
    public void put(E e) throws InterruptedException {
        // 元素不能为空
        checkNotNull(e);
        final ReentrantLock lock = this.lock;
        // 加锁，如果线程中断了抛出异常
        lock.lockInterruptibly();
        try {
            // 队列如果是满的，就无限等待
            // 一直等待队列中有数据被拿走时，自己被唤醒
            // 如果数组满了，使用notFull等待
            // notFull等待的意思是说现在队列满了
            // 只有取走一个元素后，队列才不满
            // 然后唤醒notFull，然后继续现在的逻辑
            // 这里之所以使用while而不是if
            // 是因为有可能多个线程阻塞在lock上
            // 即使唤醒了可能其它线程先一步修改了队列又变成满的了
            // 这时候需要再次等待
            while (count == items.length)
                notFull.await();
            // 入队
            enqueue(e);
        } finally {
            lock.unlock();
        }
    }

    private void enqueue(E x) {
        // assert lock.getHoldCount() == 1; 同一时刻只能一个线程进行操作此方法
        // assert items[putIndex] == null;
        final Object[] items = this.items;
        // putIndex 为本次插入的位置
        // 把元素直接放在放指针的位置上
        items[putIndex] = x;
        // ++ putIndex 计算下次插入的位置
        // 如果下次插入的位置，正好等于队尾，下次插入就从 0 开始
        // 如果放指针到数组尽头了，就返回头部
        if (++putIndex == items.length)
            putIndex = 0;
        // 数量加1
        count++;
        // 唤醒因为队列空，所以等待的线程
        // 唤醒notEmpty，因为入队了一个元素，所以肯定不为空了
        notEmpty.signal();
    }

    /**
     * Inserts the specified element at the tail of this queue, waiting
     * up to the specified wait time for space to become available if
     * the queue is full.
     *
     * @throws InterruptedException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean offer(E e, long timeout, TimeUnit unit)
        throws InterruptedException {

        checkNotNull(e);
        long nanos = unit.toNanos(timeout);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            // 如果数组满了，就阻塞nanos纳秒
            // 如果唤醒这个线程时依然没有空间且时间到了就返回false
            while (count == items.length) {
                if (nanos <= 0)
                    return false;
                nanos = notFull.awaitNanos(nanos);
            }
            // 入队
            enqueue(e);
            return true;
        } finally {
            lock.unlock();
        }
    }

    public E poll() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return (count == 0) ? null : dequeue();
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            // 如果队列为空，无限等待
            // 直到队列中有数据被 put 后，自己被唤醒
            while (count == 0)
                notEmpty.await();
            // 从队列中拿数据
            return dequeue();
        } finally {
            lock.unlock();
        }
    }

    private E dequeue() {
        // assert lock.getHoldCount() == 1;
        // assert items[takeIndex] != null;
        final Object[] items = this.items;
        // takeIndex 代表本次拿数据的位置，是上一次拿数据时计算好的
        E x = (E) items[takeIndex];
        // 帮助 gc
        items[takeIndex] = null;
        // ++ takeIndex 计算下次拿数据的位置
        // 如果正好等于队尾的话，下次就从 0 开始拿数据
        if (++takeIndex == items.length)
            takeIndex = 0;
        // 队列实际大小减 1
        count--;
        if (itrs != null)
            itrs.elementDequeued();
        // 唤醒被队列满所阻塞的线程
        notFull.signal();
        return x;
    }

    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == 0) {
                if (nanos <= 0)
                    return null;
                nanos = notEmpty.awaitNanos(nanos);
            }
            return dequeue();
        } finally {
            lock.unlock();
        }
    }

    public E peek() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return itemAt(takeIndex); // null when queue is empty
        } finally {
            lock.unlock();
        }
    }

    // this doc comment is overridden to remove the reference to collections
    // greater in size than Integer.MAX_VALUE
    /**
     * Returns the number of elements in this queue.
     *
     * @return the number of elements in this queue
     */
    public int size() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    // this doc comment is a modified copy of the inherited doc comment,
    // without the reference to unlimited queues.
    /**
     * Returns the number of additional elements that this queue can ideally
     * (in the absence of memory or resource constraints) accept without
     * blocking. This is always equal to the initial capacity of this queue
     * less the current {@code size} of this queue.
     *
     * <p>Note that you <em>cannot</em> always tell if an attempt to insert
     * an element will succeed by inspecting {@code remainingCapacity}
     * because it may be the case that another thread is about to
     * insert or remove an element.
     */
    public int remainingCapacity() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return items.length - count;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Removes a single instance of the specified element from this queue,
     * if it is present.  More formally, removes an element {@code e} such
     * that {@code o.equals(e)}, if this queue contains one or more such
     * elements.
     * Returns {@code true} if this queue contained the specified element
     * (or equivalently, if this queue changed as a result of the call).
     *
     * <p>Removal of interior elements in circular array based queues
     * is an intrinsically slow and disruptive operation, so should
     * be undertaken only in exceptional circumstances, ideally
     * only when the queue is known not to be accessible by other
     * threads.
     *
     * @param o element to be removed from this queue, if present
     * @return {@code true} if this queue changed as a result of the call
     */
    public boolean remove(Object o) {
        if (o == null) return false;
        final Object[] items = this.items;
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            // 如果队列不为空，才能有数据可以删除
            if (count > 0) {
                final int putIndex = this.putIndex;
                int i = takeIndex;
                // 从拿数据位置开始，到放数据位置结束
                do {
                    // 如果值相同，通过下表进行删除
                    if (o.equals(items[i])) {
                        removeAt(i);
                        return true;
                    }
                    // 如果已经循环到队尾了，从头开始循环
                    if (++i == items.length)
                        i = 0;
                } while (i != putIndex);
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Returns {@code true} if this queue contains the specified element.
     * More formally, returns {@code true} if and only if this queue contains
     * at least one element {@code e} such that {@code o.equals(e)}.
     *
     * @param o object to be checked for containment in this queue
     * @return {@code true} if this queue contains the specified element
     */
    public boolean contains(Object o) {
        if (o == null) return false;
        final Object[] items = this.items;
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            if (count > 0) {
                final int putIndex = this.putIndex;
                int i = takeIndex;
                do {
                    if (o.equals(items[i]))
                        return true;
                    if (++i == items.length)
                        i = 0;
                } while (i != putIndex);
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Returns an array containing all of the elements in this queue, in
     * proper sequence.
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this queue.  (In other words, this method must allocate
     * a new array).  The caller is thus free to modify the returned array.
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all of the elements in this queue
     */
    public Object[] toArray() {
        Object[] a;
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            final int count = this.count;
            a = new Object[count];
            int n = items.length - takeIndex;
            if (count <= n)
                System.arraycopy(items, takeIndex, a, 0, count);
            else {
                System.arraycopy(items, takeIndex, a, 0, n);
                System.arraycopy(items, 0, a, n, count - n);
            }
        } finally {
            lock.unlock();
        }
        return a;
    }

    /**
     * Returns an array containing all of the elements in this queue, in
     * proper sequence; the runtime type of the returned array is that of
     * the specified array.  If the queue fits in the specified array, it
     * is returned therein.  Otherwise, a new array is allocated with the
     * runtime type of the specified array and the size of this queue.
     *
     * <p>If this queue fits in the specified array with room to spare
     * (i.e., the array has more elements than this queue), the element in
     * the array immediately following the end of the queue is set to
     * {@code null}.
     *
     * <p>Like the {@link #toArray()} method, this method acts as bridge between
     * array-based and collection-based APIs.  Further, this method allows
     * precise control over the runtime type of the output array, and may,
     * under certain circumstances, be used to save allocation costs.
     *
     * <p>Suppose {@code x} is a queue known to contain only strings.
     * The following code can be used to dump the queue into a newly
     * allocated array of {@code String}:
     *
     *  <pre> {@code String[] y = x.toArray(new String[0]);}</pre>
     *
     * Note that {@code toArray(new Object[0])} is identical in function to
     * {@code toArray()}.
     *
     * @param a the array into which the elements of the queue are to
     *          be stored, if it is big enough; otherwise, a new array of the
     *          same runtime type is allocated for this purpose
     * @return an array containing all of the elements in this queue
     * @throws ArrayStoreException if the runtime type of the specified array
     *         is not a supertype of the runtime type of every element in
     *         this queue
     * @throws NullPointerException if the specified array is null
     */
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        final Object[] items = this.items;
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            final int count = this.count;
            final int len = a.length;
            if (len < count)
                a = (T[])java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), count);
            int n = items.length - takeIndex;
            if (count <= n)
                System.arraycopy(items, takeIndex, a, 0, count);
            else {
                System.arraycopy(items, takeIndex, a, 0, n);
                System.arraycopy(items, 0, a, n, count - n);
            }
            if (len > count)
                a[count] = null;
        } finally {
            lock.unlock();
        }
        return a;
    }

    public String toString() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            int k = count;
            if (k == 0)
                return "[]";

            final Object[] items = this.items;
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (int i = takeIndex; ; ) {
                Object e = items[i];
                sb.append(e == this ? "(this Collection)" : e);
                if (--k == 0)
                    return sb.append(']').toString();
                sb.append(',').append(' ');
                if (++i == items.length)
                    i = 0;
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Atomically removes all of the elements from this queue.
     * The queue will be empty after this call returns.
     */
    public void clear() {
        final Object[] items = this.items;
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            int k = count;
            if (k > 0) {
                final int putIndex = this.putIndex;
                int i = takeIndex;
                do {
                    items[i] = null;
                    if (++i == items.length)
                        i = 0;
                } while (i != putIndex);
                takeIndex = putIndex;
                count = 0;
                if (itrs != null)
                    itrs.queueIsEmpty();
                for (; k > 0 && lock.hasWaiters(notFull); k--)
                    notFull.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegalArgumentException      {@inheritDoc}
     */
    public int drainTo(Collection<? super E> c) {
        return drainTo(c, Integer.MAX_VALUE);
    }

    /**
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegalArgumentException      {@inheritDoc}
     */
    public int drainTo(Collection<? super E> c, int maxElements) {
        checkNotNull(c);
        if (c == this)
            throw new IllegalArgumentException();
        if (maxElements <= 0)
            return 0;
        final Object[] items = this.items;
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            int n = Math.min(maxElements, count);
            int take = takeIndex;
            int i = 0;
            try {
                while (i < n) {
                    @SuppressWarnings("unchecked")
                    E x = (E) items[take];
                    c.add(x);
                    items[take] = null;
                    if (++take == items.length)
                        take = 0;
                    i++;
                }
                return n;
            } finally {
                // Restore invariants even if c.add() threw
                if (i > 0) {
                    count -= i;
                    takeIndex = take;
                    if (itrs != null) {
                        if (count == 0)
                            itrs.queueIsEmpty();
                        else if (i > take)
                            itrs.takeIndexWrapped();
                    }
                    for (; i > 0 && lock.hasWaiters(notFull); i--)
                        notFull.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Returns an iterator over the elements in this queue in proper sequence.
     * The elements will be returned in order from first (head) to last (tail).
     *
     * <p>The returned iterator is
     * <a href="package-summary.html#Weakly"><i>weakly consistent</i></a>.
     *
     * @return an iterator over the elements in this queue in proper sequence
     */
    public Iterator<E> iterator() {
        return new Itr();
    }

    /**
     * Shared data between iterators and their queue, allowing queue
     * modifications to update iterators when elements are removed.
     *
     * This adds a lot of complexity for the sake of correctly
     * handling some uncommon operations, but the combination of
     * circular-arrays and supporting interior removes (i.e., those
     * not at head) would cause iterators to sometimes lose their
     * places and/or (re)report elements they shouldn't.  To avoid
     * this, when a queue has one or more iterators, it keeps iterator
     * state consistent by:
     *
     * (1) keeping track of the number of "cycles", that is, the
     *     number of times takeIndex has wrapped around to 0.
     * (2) notifying all iterators via the callback removedAt whenever
     *     an interior element is removed (and thus other elements may
     *     be shifted).
     *
     * These suffice to eliminate iterator inconsistencies, but
     * unfortunately add the secondary responsibility of maintaining
     * the list of iterators.  We track all active iterators in a
     * simple linked list (accessed only when the queue's lock is
     * held) of weak references to Itr.  The list is cleaned up using
     * 3 different mechanisms:
     *
     * (1) Whenever a new iterator is created, do some O(1) checking for
     *     stale list elements.
     *
     * (2) Whenever takeIndex wraps around to 0, check for iterators
     *     that have been unused for more than one wrap-around cycle.
     *
     * (3) Whenever the queue becomes empty, all iterators are notified
     *     and this entire data structure is discarded.
     *
     * So in addition to the removedAt callback that is necessary for
     * correctness, iterators have the shutdown and takeIndexWrapped
     * callbacks that help remove stale iterators from the list.
     *
     * Whenever a list element is examined, it is expunged if either
     * the GC has determined that the iterator is discarded, or if the
     * iterator reports that it is "detached" (does not need any
     * further state updates).  Overhead is maximal when takeIndex
     * never advances, iterators are discarded before they are
     * exhausted, and all removals are interior removes, in which case
     * all stale iterators are discovered by the GC.  But even in this
     * case we don't increase the amortized complexity.
     *
     * Care must be taken to keep list sweeping methods from
     * reentrantly invoking another such method, causing subtle
     * corruption bugs.
     */
    class Itrs {

        /**
         * Node in a linked list of weak iterator references.
         */
        private class Node extends WeakReference<Itr> {
            Node next;

            Node(Itr iterator, Node next) {
                super(iterator);
                this.next = next;
            }
        }

        /** Incremented whenever takeIndex wraps around to 0 */
        int cycles = 0;

        /** Linked list of weak iterator references */
        private Node head;

        /** Used to expunge stale iterators */
        private Node sweeper = null;

        private static final int SHORT_SWEEP_PROBES = 4;
        private static final int LONG_SWEEP_PROBES = 16;

        Itrs(Itr initial) {
            register(initial);
        }

        /**
         * Sweeps itrs, looking for and expunging stale iterators.
         * If at least one was found, tries harder to find more.
         * Called only from iterating thread.
         *
         * @param tryHarder whether to start in try-harder mode, because
         * there is known to be at least one iterator to collect
         */
        void doSomeSweeping(boolean tryHarder) {
            // assert lock.getHoldCount() == 1;
            // assert head != null;
            int probes = tryHarder ? LONG_SWEEP_PROBES : SHORT_SWEEP_PROBES;
            Node o, p;
            final Node sweeper = this.sweeper;
            boolean passedGo;   // to limit search to one full sweep

            if (sweeper == null) {
                o = null;
                p = head;
                passedGo = true;
            } else {
                o = sweeper;
                p = o.next;
                passedGo = false;
            }

            for (; probes > 0; probes--) {
                if (p == null) {
                    if (passedGo)
                        break;
                    o = null;
                    p = head;
                    passedGo = true;
                }
                final Itr it = p.get();
                final Node next = p.next;
                if (it == null || it.isDetached()) {
                    // found a discarded/exhausted iterator
                    probes = LONG_SWEEP_PROBES; // "try harder"
                    // unlink p
                    p.clear();
                    p.next = null;
                    if (o == null) {
                        head = next;
                        if (next == null) {
                            // We've run out of iterators to track; retire
                            itrs = null;
                            return;
                        }
                    }
                    else
                        o.next = next;
                } else {
                    o = p;
                }
                p = next;
            }

            this.sweeper = (p == null) ? null : o;
        }

        /**
         * Adds a new iterator to the linked list of tracked iterators.
         */
        void register(Itr itr) {
            // assert lock.getHoldCount() == 1;
            head = new Node(itr, head);
        }

        /**
         * Called whenever takeIndex wraps around to 0.
         *
         * Notifies all iterators, and expunges any that are now stale.
         */
        void takeIndexWrapped() {
            // assert lock.getHoldCount() == 1;
            cycles++;
            for (Node o = null, p = head; p != null;) {
                final Itr it = p.get();
                final Node next = p.next;
                if (it == null || it.takeIndexWrapped()) {
                    // unlink p
                    // assert it == null || it.isDetached();
                    p.clear();
                    p.next = null;
                    if (o == null)
                        head = next;
                    else
                        o.next = next;
                } else {
                    o = p;
                }
                p = next;
            }
            if (head == null)   // no more iterators to track
                itrs = null;
        }

        /**
         * Called whenever an interior remove (not at takeIndex) occurred.
         *
         * Notifies all iterators, and expunges any that are now stale.
         */
        void removedAt(int removedIndex) {
            for (Node o = null, p = head; p != null;) {
                final Itr it = p.get();
                final Node next = p.next;
                if (it == null || it.removedAt(removedIndex)) {
                    // unlink p
                    // assert it == null || it.isDetached();
                    p.clear();
                    p.next = null;
                    if (o == null)
                        head = next;
                    else
                        o.next = next;
                } else {
                    o = p;
                }
                p = next;
            }
            if (head == null)   // no more iterators to track
                itrs = null;
        }

        /**
         * Called whenever the queue becomes empty.
         *
         * Notifies all active iterators that the queue is empty,
         * clears all weak refs, and unlinks the itrs datastructure.
         */
        void queueIsEmpty() {
            // assert lock.getHoldCount() == 1;
            for (Node p = head; p != null; p = p.next) {
                Itr it = p.get();
                if (it != null) {
                    p.clear();
                    it.shutdown();
                }
            }
            head = null;
            itrs = null;
        }

        /**
         * Called whenever an element has been dequeued (at takeIndex).
         */
        void elementDequeued() {
            // assert lock.getHoldCount() == 1;
            if (count == 0)
                queueIsEmpty();
            else if (takeIndex == 0)
                takeIndexWrapped();
        }
    }

    /**
     * Iterator for ArrayBlockingQueue.
     *
     * To maintain weak consistency with respect to puts and takes, we
     * read ahead one slot, so as to not report hasNext true but then
     * not have an element to return.
     *
     * We switch into "detached" mode (allowing prompt unlinking from
     * itrs without help from the GC) when all indices are negative, or
     * when hasNext returns false for the first time.  This allows the
     * iterator to track concurrent updates completely accurately,
     * except for the corner case of the user calling Iterator.remove()
     * after hasNext() returned false.  Even in this case, we ensure
     * that we don't remove the wrong element by keeping track of the
     * expected element to remove, in lastItem.  Yes, we may fail to
     * remove lastItem from the queue if it moved due to an interleaved
     * interior remove while in detached mode.
     */
    private class Itr implements Iterator<E> {
        /** Index to look for new nextItem; NONE at end */
        private int cursor;

        /** Element to be returned by next call to next(); null if none */
        private E nextItem;

        /** Index of nextItem; NONE if none, REMOVED if removed elsewhere */
        private int nextIndex;

        /** Last element returned; null if none or not detached. */
        private E lastItem;

        /** Index of lastItem, NONE if none, REMOVED if removed elsewhere */
        private int lastRet;

        /** Previous value of takeIndex, or DETACHED when detached */
        private int prevTakeIndex;

        /** Previous value of iters.cycles */
        private int prevCycles;

        /** Special index value indicating "not available" or "undefined" */
        private static final int NONE = -1;

        /**
         * Special index value indicating "removed elsewhere", that is,
         * removed by some operation other than a call to this.remove().
         */
        private static final int REMOVED = -2;

        /** Special value for prevTakeIndex indicating "detached mode" */
        private static final int DETACHED = -3;

        Itr() {
            // assert lock.getHoldCount() == 0;
            lastRet = NONE;
            final ReentrantLock lock = ArrayBlockingQueue.this.lock;
            lock.lock();
            try {
                if (count == 0) {
                    // assert itrs == null;
                    cursor = NONE;
                    nextIndex = NONE;
                    prevTakeIndex = DETACHED;
                } else {
                    final int takeIndex = ArrayBlockingQueue.this.takeIndex;
                    prevTakeIndex = takeIndex;
                    nextItem = itemAt(nextIndex = takeIndex);
                    cursor = incCursor(takeIndex);
                    if (itrs == null) {
                        itrs = new Itrs(this);
                    } else {
                        itrs.register(this); // in this order
                        itrs.doSomeSweeping(false);
                    }
                    prevCycles = itrs.cycles;
                    // assert takeIndex >= 0;
                    // assert prevTakeIndex == takeIndex;
                    // assert nextIndex >= 0;
                    // assert nextItem != null;
                }
            } finally {
                lock.unlock();
            }
        }

        boolean isDetached() {
            // assert lock.getHoldCount() == 1;
            return prevTakeIndex < 0;
        }

        private int incCursor(int index) {
            // assert lock.getHoldCount() == 1;
            if (++index == items.length)
                index = 0;
            if (index == putIndex)
                index = NONE;
            return index;
        }

        /**
         * Returns true if index is invalidated by the given number of
         * dequeues, starting from prevTakeIndex.
         */
        private boolean invalidated(int index, int prevTakeIndex,
                                    long dequeues, int length) {
            if (index < 0)
                return false;
            int distance = index - prevTakeIndex;
            if (distance < 0)
                distance += length;
            return dequeues > distance;
        }

        /**
         * Adjusts indices to incorporate all dequeues since the last
         * operation on this iterator.  Call only from iterating thread.
         */
        private void incorporateDequeues() {
            // assert lock.getHoldCount() == 1;
            // assert itrs != null;
            // assert !isDetached();
            // assert count > 0;

            final int cycles = itrs.cycles;
            final int takeIndex = ArrayBlockingQueue.this.takeIndex;
            final int prevCycles = this.prevCycles;
            final int prevTakeIndex = this.prevTakeIndex;

            if (cycles != prevCycles || takeIndex != prevTakeIndex) {
                final int len = items.length;
                // how far takeIndex has advanced since the previous
                // operation of this iterator
                long dequeues = (cycles - prevCycles) * len
                    + (takeIndex - prevTakeIndex);

                // Check indices for invalidation
                if (invalidated(lastRet, prevTakeIndex, dequeues, len))
                    lastRet = REMOVED;
                if (invalidated(nextIndex, prevTakeIndex, dequeues, len))
                    nextIndex = REMOVED;
                if (invalidated(cursor, prevTakeIndex, dequeues, len))
                    cursor = takeIndex;

                if (cursor < 0 && nextIndex < 0 && lastRet < 0)
                    detach();
                else {
                    this.prevCycles = cycles;
                    this.prevTakeIndex = takeIndex;
                }
            }
        }

        /**
         * Called when itrs should stop tracking this iterator, either
         * because there are no more indices to update (cursor < 0 &&
         * nextIndex < 0 && lastRet < 0) or as a special exception, when
         * lastRet >= 0, because hasNext() is about to return false for the
         * first time.  Call only from iterating thread.
         */
        private void detach() {
            // Switch to detached mode
            // assert lock.getHoldCount() == 1;
            // assert cursor == NONE;
            // assert nextIndex < 0;
            // assert lastRet < 0 || nextItem == null;
            // assert lastRet < 0 ^ lastItem != null;
            if (prevTakeIndex >= 0) {
                // assert itrs != null;
                prevTakeIndex = DETACHED;
                // try to unlink from itrs (but not too hard)
                itrs.doSomeSweeping(true);
            }
        }

        /**
         * For performance reasons, we would like not to acquire a lock in
         * hasNext in the common case.  To allow for this, we only access
         * fields (i.e. nextItem) that are not modified by update operations
         * triggered by queue modifications.
         */
        public boolean hasNext() {
            // assert lock.getHoldCount() == 0;
            if (nextItem != null)
                return true;
            noNext();
            return false;
        }

        private void noNext() {
            final ReentrantLock lock = ArrayBlockingQueue.this.lock;
            lock.lock();
            try {
                // assert cursor == NONE;
                // assert nextIndex == NONE;
                if (!isDetached()) {
                    // assert lastRet >= 0;
                    incorporateDequeues(); // might update lastRet
                    if (lastRet >= 0) {
                        lastItem = itemAt(lastRet);
                        // assert lastItem != null;
                        detach();
                    }
                }
                // assert isDetached();
                // assert lastRet < 0 ^ lastItem != null;
            } finally {
                lock.unlock();
            }
        }

        public E next() {
            // assert lock.getHoldCount() == 0;
            final E x = nextItem;
            if (x == null)
                throw new NoSuchElementException();
            final ReentrantLock lock = ArrayBlockingQueue.this.lock;
            lock.lock();
            try {
                if (!isDetached())
                    incorporateDequeues();
                // assert nextIndex != NONE;
                // assert lastItem == null;
                lastRet = nextIndex;
                final int cursor = this.cursor;
                if (cursor >= 0) {
                    nextItem = itemAt(nextIndex = cursor);
                    // assert nextItem != null;
                    this.cursor = incCursor(cursor);
                } else {
                    nextIndex = NONE;
                    nextItem = null;
                }
            } finally {
                lock.unlock();
            }
            return x;
        }

        public void remove() {
            // assert lock.getHoldCount() == 0;
            final ReentrantLock lock = ArrayBlockingQueue.this.lock;
            lock.lock();
            try {
                if (!isDetached())
                    incorporateDequeues(); // might update lastRet or detach
                final int lastRet = this.lastRet;
                this.lastRet = NONE;
                if (lastRet >= 0) {
                    if (!isDetached())
                        removeAt(lastRet);
                    else {
                        final E lastItem = this.lastItem;
                        // assert lastItem != null;
                        this.lastItem = null;
                        if (itemAt(lastRet) == lastItem)
                            removeAt(lastRet);
                    }
                } else if (lastRet == NONE)
                    throw new IllegalStateException();
                // else lastRet == REMOVED and the last returned element was
                // previously asynchronously removed via an operation other
                // than this.remove(), so nothing to do.

                if (cursor < 0 && nextIndex < 0)
                    detach();
            } finally {
                lock.unlock();
                // assert lastRet == NONE;
                // assert lastItem == null;
            }
        }

        /**
         * Called to notify the iterator that the queue is empty, or that it
         * has fallen hopelessly behind, so that it should abandon any
         * further iteration, except possibly to return one more element
         * from next(), as promised by returning true from hasNext().
         */
        void shutdown() {
            // assert lock.getHoldCount() == 1;
            cursor = NONE;
            if (nextIndex >= 0)
                nextIndex = REMOVED;
            if (lastRet >= 0) {
                lastRet = REMOVED;
                lastItem = null;
            }
            prevTakeIndex = DETACHED;
            // Don't set nextItem to null because we must continue to be
            // able to return it on next().
            //
            // Caller will unlink from itrs when convenient.
        }

        private int distance(int index, int prevTakeIndex, int length) {
            int distance = index - prevTakeIndex;
            if (distance < 0)
                distance += length;
            return distance;
        }

        /**
         * Called whenever an interior remove (not at takeIndex) occurred.
         *
         * @return true if this iterator should be unlinked from itrs
         */
        boolean removedAt(int removedIndex) {
            // assert lock.getHoldCount() == 1;
            if (isDetached())
                return true;

            final int cycles = itrs.cycles;
            final int takeIndex = ArrayBlockingQueue.this.takeIndex;
            final int prevCycles = this.prevCycles;
            final int prevTakeIndex = this.prevTakeIndex;
            final int len = items.length;
            int cycleDiff = cycles - prevCycles;
            if (removedIndex < takeIndex)
                cycleDiff++;
            final int removedDistance =
                (cycleDiff * len) + (removedIndex - prevTakeIndex);
            // assert removedDistance >= 0;
            int cursor = this.cursor;
            if (cursor >= 0) {
                int x = distance(cursor, prevTakeIndex, len);
                if (x == removedDistance) {
                    if (cursor == putIndex)
                        this.cursor = cursor = NONE;
                }
                else if (x > removedDistance) {
                    // assert cursor != prevTakeIndex;
                    this.cursor = cursor = dec(cursor);
                }
            }
            int lastRet = this.lastRet;
            if (lastRet >= 0) {
                int x = distance(lastRet, prevTakeIndex, len);
                if (x == removedDistance)
                    this.lastRet = lastRet = REMOVED;
                else if (x > removedDistance)
                    this.lastRet = lastRet = dec(lastRet);
            }
            int nextIndex = this.nextIndex;
            if (nextIndex >= 0) {
                int x = distance(nextIndex, prevTakeIndex, len);
                if (x == removedDistance)
                    this.nextIndex = nextIndex = REMOVED;
                else if (x > removedDistance)
                    this.nextIndex = nextIndex = dec(nextIndex);
            }
            else if (cursor < 0 && nextIndex < 0 && lastRet < 0) {
                this.prevTakeIndex = DETACHED;
                return true;
            }
            return false;
        }

        /**
         * Called whenever takeIndex wraps around to zero.
         *
         * @return true if this iterator should be unlinked from itrs
         */
        boolean takeIndexWrapped() {
            // assert lock.getHoldCount() == 1;
            if (isDetached())
                return true;
            if (itrs.cycles - prevCycles > 1) {
                // All the elements that existed at the time of the last
                // operation are gone, so abandon further iteration.
                shutdown();
                return true;
            }
            return false;
        }

//         /** Uncomment for debugging. */
//         public String toString() {
//             return ("cursor=" + cursor + " " +
//                     "nextIndex=" + nextIndex + " " +
//                     "lastRet=" + lastRet + " " +
//                     "nextItem=" + nextItem + " " +
//                     "lastItem=" + lastItem + " " +
//                     "prevCycles=" + prevCycles + " " +
//                     "prevTakeIndex=" + prevTakeIndex + " " +
//                     "size()=" + size() + " " +
//                     "remainingCapacity()=" + remainingCapacity());
//         }
    }

    /**
     * Returns a {@link Spliterator} over the elements in this queue.
     *
     * <p>The returned spliterator is
     * <a href="package-summary.html#Weakly"><i>weakly consistent</i></a>.
     *
     * <p>The {@code Spliterator} reports {@link Spliterator#CONCURRENT},
     * {@link Spliterator#ORDERED}, and {@link Spliterator#NONNULL}.
     *
     * @implNote
     * The {@code Spliterator} implements {@code trySplit} to permit limited
     * parallelism.
     *
     * @return a {@code Spliterator} over the elements in this queue
     * @since 1.8
     */
    public Spliterator<E> spliterator() {
        return Spliterators.spliterator
            (this, Spliterator.ORDERED | Spliterator.NONNULL |
             Spliterator.CONCURRENT);
    }

}
