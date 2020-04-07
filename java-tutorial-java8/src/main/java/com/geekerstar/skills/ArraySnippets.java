package com.geekerstar.skills;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author geekerstar
 * @date 2020/4/7 10:00
 * @description
 */
public abstract class ArraySnippets {

    /**
     * 将数组分割成特定大小的小数组
     *
     * @param numbers
     * @param size
     * @return
     */
    public static int[][] chunk(int[] numbers, int size) {
        return IntStream.iterate(0, i -> i + size)
                .limit((long) Math.ceil((double) numbers.length / size))
                .mapToObj(cur -> Arrays.copyOfRange(numbers, cur, cur + size > numbers.length ? numbers.length : cur + size))
                .toArray(int[][]::new);
    }


    /**
     * @param first
     * @param second
     * @param <T>
     * @return
     */
    public static <T> T[] concat(T[] first, T[] second) {
        return Stream.concat(
                Stream.of(first),
                Stream.of(second)
        ).toArray(i -> (T[]) Arrays.copyOf(new Object[0], i, first.getClass()));
    }


    /**
     * 计算数组中某个值出现的次数。使用 Arrays.stream().filter().count() 计算等于指定值的值的总数
     *
     * @param numbers
     * @param value
     * @return
     */
    public static long countOccurrences(int[] numbers, int value) {
        return Arrays.stream(numbers)
                .filter(number -> number == value)
                .count();
    }

    /**
     * 数组扁平化。使用递归实现，Arrays.stream().flatMapToInt()
     *
     * @param input
     * @return
     */
    public static int[] deepFlatten(Object[] input) {
        return Arrays.stream(input)
                .flatMapToInt(o -> {
                    if (o instanceof Object[]) {
                        return Arrays.stream(deepFlatten((Object[]) o));
                    }
                    return IntStream.of((Integer) o);
                }).toArray();
    }

    /**
     * 返回两个数组之间的差异。从 b 中创建一个集合，然后在 a 上使用 Arrays.stream().filter() 只保留 b 中不包含的值。
     *
     * @param first
     * @param second
     * @return
     */
    public static int[] difference(int[] first, int[] second) {
        Set<Integer> set = Arrays.stream(second).boxed().collect(Collectors.toSet());
        return Arrays.stream(first)
                .filter(v -> !set.contains(v))
                .toArray();
    }

    /**
     * 从比较器函数不返回true的数组中筛选出所有值。int的比较器是使用IntbinaryPerator函数来实现的。
     * 使用 Arrays.stream().filter() 和 Arrays.stream().noneMatch() 查找相应的值。
     *
     * @param first
     * @param second
     * @param comparator
     * @return
     */
    public static int[] differenceWith(int[] first, int[] second, IntBinaryOperator comparator) {
        return Arrays.stream(first)
                .filter(a ->
                        Arrays.stream(second)
                                .noneMatch(b -> comparator.applyAsInt(a, b) == 0)
                ).toArray();
    }

    /**
     * 返回数组的所有不同值。使用 Arrays.stream().distinct() 去除所有重复的值。
     *
     * @param elements
     * @return
     */
    public static int[] distinctValuesOfArray(int[] elements) {
        return Arrays.stream(elements).distinct().toArray();
    }

    /**
     * 移除数组中的元素，直到传递的函数返回true为止。返回数组中的其余元素。
     * 使用数组循环遍历数组，将数组的第一个元素删除，直到函数返回的值为真为止。返回其余的元素。
     *
     * @param elements
     * @param condition
     * @return
     */
    public static int[] dropElements(int[] elements, IntPredicate condition) {
        while (elements.length > 0 && !condition.test(elements[0])) {
            elements = Arrays.copyOfRange(elements, 1, elements.length);
        }
        return elements;
    }

    /**
     * 返回一个新数组，从右边移除n个元素。
     * 检查n是否短于给定的数组，并使用 Array.copyOfRange() 以便对其进行相应的切片或返回一个空数组。
     *
     * @param elements
     * @param n
     * @return
     */
    public static int[] dropRight(int[] elements, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n is less than 0");
        }
        return n < elements.length
                ? Arrays.copyOfRange(elements, 0, elements.length - n)
                : new int[0];
    }


    /**
     * 返回数组中的每个第n个元素。使用 IntStream.range().filter() 创建一个新数组，该数组包含给定数组的每个第n个元素。
     *
     * @param elements
     * @param nth
     * @return
     */
    public static int[] everyNth(int[] elements, int nth) {
        return IntStream.range(0, elements.length)
                .filter(i -> i % nth == nth - 1)
                .map(i -> elements[i])
                .toArray();
    }

    /**
     * 查找数组中元素的索引，在不存在元素的情况下返回-1。
     * 使用 IntStream.range().filter() 查找数组中元素的索引。
     *
     * @param elements
     * @param el
     * @return
     */
    public static int indexOf(int[] elements, int el) {
        return IntStream.range(0, elements.length)
                .filter(idx -> elements[idx] == el)
                .findFirst()
                .orElse(-1);
    }

    /**
     * 查找数组中元素的最后索引，在不存在元素的情况下返回-1。
     * 使用 IntStream.iterate().limit().filter() 查找数组中元素的索引。
     *
     * @param elements
     * @param el
     * @return
     */
    public static int lastIndexOf(int[] elements, int el) {
        return IntStream.iterate(elements.length - 1, i -> i - 1)
                .limit(elements.length)
                .filter(idx -> elements[idx] == el)
                .findFirst()
                .orElse(-1);
    }

    /**
     * 筛选出数组中的非唯一值。
     * 对只包含唯一值的数组使用 Arrays.stream().filter()。
     *
     * @param elements
     * @return
     */
    public static int[] filterNonUnique(int[] elements) {
        return Arrays.stream(elements)
                .filter(el -> indexOf(elements, el) == lastIndexOf(elements, el))
                .toArray();
    }

    /**
     * 使数组扁平。使用 Arrays.stream().flatMapToInt().toArray() 创建一个新数组。
     *
     * @param elements
     * @return
     */
    public static int[] flatten(Object[] elements) {
        return Arrays.stream(elements)
                .flatMapToInt(el -> el instanceof int[]
                        ? Arrays.stream((int[]) el)
                        : IntStream.of((int) el)
                ).toArray();
    }

    /**
     * 将数组压平到指定的深度。
     *
     * @param elements
     * @param depth
     * @return
     */
    public static Object[] flattenDepth(Object[] elements, int depth) {
        if (depth == 0) {
            return elements;
        }
        return Arrays.stream(elements)
                .flatMap(el -> el instanceof Object[]
                        ? Arrays.stream(flattenDepth((Object[]) el, depth - 1))
                        : Arrays.stream(new Object[]{el})
                ).toArray();
    }

    /**
     * 根据给定函数对数组元素进行分组
     * 使用 Arrays.stream().collect(Collectors.groupingBy()) 分组。
     *
     * @param elements
     * @param func
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> Map<R, List<T>> groupBy(T[] elements, Function<T, R> func) {
        return Arrays.stream(elements).collect(Collectors.groupingBy(func));
    }

    /**
     * 返回数组中除去最后一个的所有元素
     * 使用 Arrays.copyOfRange() 返回除最后一个之外的所有元素。
     *
     * @param elements
     * @param <T>
     * @return
     */
    public static <T> T[] initial(T[] elements) {
        return Arrays.copyOfRange(elements, 0, elements.length - 1);
    }

    /**
     * 初始化一个数组，该数组包含在指定范围内的数字，传入 start 和 end
     *
     * @param end
     * @param start
     * @return
     */
    public static int[] initializeArrayWithRange(int end, int start) {
        return IntStream.rangeClosed(start, end).toArray();
    }

    /**
     * 使用指定的值初始化并填充数组。
     *
     * @param n
     * @param value
     * @return
     */
    public static int[] initializeArrayWithValues(int n, int value) {
        return IntStream.generate(() -> value).limit(n).toArray();
    }

    /**
     * 返回两个数组中存在的元素列表。
     * 从第二步创建一个集合，然后在 a 上使用 Arrays.stream().filter() 来保存包含在 b 中的值。
     *
     * @param first
     * @param second
     * @return
     */
    public static int[] intersection(int[] first, int[] second) {
        Set<Integer> set = Arrays.stream(second).boxed().collect(Collectors.toSet());
        return Arrays.stream(first)
                .filter(set::contains)
                .toArray();
    }

    /**
     * 如果数组按升序排序，则返回 1，如果数组按降序排序，返回 -1，如果没有排序，则返回 0。
     * 计算前两个元素的排序 direction。使用for循环对数组进行迭代，并对它们进行成对比较。如果 direction 发生变化，则返回 0， 如果到达最后一个元素，则返回 direction。
     *
     * @param arr
     * @param <T>
     * @return
     */
    public static <T extends Comparable<? super T>> int isSorted(T[] arr) {
        final int direction = arr[0].compareTo(arr[1]) < 0 ? 1 : -1;
        for (int i = 0; i < arr.length; i++) {
            T val = arr[i];
            if (i == arr.length - 1) return direction;
            else if ((val.compareTo(arr[i + 1]) * direction > 0)) return 0;
        }
        return direction;
    }

    /**
     * 将数组的所有元素连接到字符串中，并返回此字符串。
     * 使用 IntStream.range 创建一个指定索引的数组。然后，使用 Stream.reduce 将元素组合成字符串
     *
     * @param arr
     * @param separator
     * @param end
     * @param <T>
     * @return
     */
    public static <T> String join(T[] arr, String separator, String end) {
        return IntStream.range(0, arr.length)
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(i, arr[i]))
                .reduce("", (acc, val) -> val.getKey() == arr.length - 2
                        ? acc + val.getValue() + end
                        : val.getKey() == arr.length - 1 ? acc + val.getValue() : acc + val.getValue() + separator, (fst, snd) -> fst);
    }

    public static <T> String join(T[] arr, String separator) {
        return join(arr, separator, separator);
    }

    public static <T> String join(T[] arr) {
        return join(arr, ",");
    }

    /**
     * 返回数组的第n个元素。
     * Use Arrays.copyOfRange() 优先得到包含第n个元素的数组。
     *
     * @param arr
     * @param n
     * @param <T>
     * @return
     */
    public static <T> T nthElement(T[] arr, int n) {
        if (n > 0) {
            return Arrays.copyOfRange(arr, n, arr.length)[0];
        }
        return Arrays.copyOfRange(arr, arr.length + n, arr.length)[0];
    }

    /**
     * 从对象中选择与给定键对应的键值对。
     * 使用 Arrays.stream 过滤 arr 中存在的所有键。然后，使用 Collectors.toMap 将所有的key转换为Map。
     *
     * @param obj
     * @param arr
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> Map<T, R> pick(Map<T, R> obj, T[] arr) {
        return Arrays.stream(arr)
                .filter(obj::containsKey)
                .collect(Collectors.toMap(k -> k, obj::get));
    }

    /**
     * 根据条件筛选对象数组，同时筛选出未指定的键。
     * 使用 Arrays.stream().filter() 根据谓词 fn 过滤数组，以便返回条件为真的对象。 对于每个过滤的Map对象，创建一个新的Map，其中包含 keys 中的键。最后，将Map对象收集到一个数组中。
     *
     * @param data
     * @param keys
     * @param fn
     * @return
     */
    public static Map<String, Object>[] reducedFilter(Map<String, Object>[] data, String[] keys, Predicate<Map<String, Object>> fn) {
        return Arrays.stream(data)
                .filter(fn)
                .map(el -> Arrays.stream(keys).filter(el::containsKey)
                        .collect(Collectors.toMap(Function.identity(), el::get)))
                .toArray((IntFunction<Map<String, Object>[]>) Map[]::new);
    }

    /**
     * 从数组中返回一个随机元素。
     * 使用 Math.Randoman() 生成一个随机数，然后将它乘以数组的 length，然后使用 Math.floor() 获得一个最近的整数，该方法也适用于字符串。
     *
     * @param arr
     * @param <T>
     * @return
     */
    public static <T> T sample(T[] arr) {
        return arr[(int) Math.floor(Math.random() * arr.length)];
    }

    /**
     * 从 array 到 array 大小的唯一键获取 n 个随机元素。
     * 根据Fisher-Yates算法，使用 Array.copyOfRange() 获得优先的 n 个元素。
     *
     * @param input
     * @param n
     * @param <T>
     * @return
     */
    public static <T> T[] sampleSize(T[] input, int n) {
        T[] arr = Arrays.copyOf(input, input.length);
        int length = arr.length;
        int m = length;
        while (m > 0) {
            int i = (int) Math.floor(Math.random() * m--);
            T tmp = arr[i];
            arr[i] = arr[m];
            arr[m] = tmp;
        }
        return Arrays.copyOfRange(arr, 0, n > length ? length : n);
    }


    /**
     * 将数组值的顺序随机化，返回一个新数组。
     * 根据 Fisher-Yates 算法 重新排序数组的元素。
     *
     * @param input
     * @param <T>
     * @return
     */
    public static <T> T[] shuffle(T[] input) {
        T[] arr = Arrays.copyOf(input, input.length);
        int length = arr.length;
        int m = length;
        while (m > 0) {
            int i = (int) Math.floor(Math.random() * m--);
            T tmp = arr[i];
            arr[i] = arr[m];
            arr[m] = tmp;
        }
        return arr;
    }


    /**
     * 返回出现在两个数组中的元素数组
     * 使用 Arrays.stream().filter() 移除，然后使用 Arrays.stream().anyMatch() 匹配 second 部分的值。
     *
     * @param first
     * @param second
     * @param <T>
     * @return
     */
    public static <T> T[] similarity(T[] first, T[] second) {
        return Arrays.stream(first)
                .filter(a -> Arrays.stream(second).anyMatch(b -> Objects.equals(a, b)))
                // Make a new array of first's runtime type, but empty content:
                .toArray(i -> (T[]) Arrays.copyOf(new Object[0], i, first.getClass()));
    }

    public static <T> T[] emptyArray(Class<T> clz) {
        return (T[]) Array.newInstance(clz, 0);
    }

    /**
     * 返回值应该插入到数组中的最低索引，以保持其排序顺序。
     * 检查数组是否按降序（松散地）排序。 使用 IntStream.range().filter() 来找到元素应该被插入的合适的索引。
     *
     * @param arr
     * @param el
     * @param <T>
     * @return
     */
    public static <T extends Comparable<? super T>> int sortedIndex(T[] arr, T el) {
        boolean isDescending = arr[0].compareTo(arr[arr.length - 1]) > 0;
        return IntStream.range(0, arr.length)
                .filter(i -> isDescending ? el.compareTo(arr[i]) >= 0 : el.compareTo(arr[i]) <= 0)
                .findFirst()
                .orElse(arr.length);
    }

    /**
     * 返回两个数组之间的对称差异。
     * 从每个数组中创建一个 Set，然后使用 Arrays.stream().filter() 来保持其他值不包含的值。最后，连接两个数组并创建一个新数组并返回。
     *
     * @param first
     * @param second
     * @param <T>
     * @return
     */
    public static <T> T[] symmetricDifference(T[] first, T[] second) {
        Set<T> sA = new HashSet<>(Arrays.asList(first));
        Set<T> sB = new HashSet<>(Arrays.asList(second));

        return Stream.concat(
                Arrays.stream(first).filter(a -> !sB.contains(a)),
                Arrays.stream(second).filter(b -> !sA.contains(b))
        ).toArray(i -> (T[]) Arrays.copyOf(new Object[0], i, first.getClass()));
    }

    /**
     * 返回数组中除第一个元素外的所有元素。
     * 如果数组的长度大于1，则返回 Arrays.copyOfRange(1)，否则返回整个数组。
     *
     * @param arr
     * @param <T>
     * @return
     */
    public static <T> T[] tail(T[] arr) {
        return arr.length > 1
                ? Arrays.copyOfRange(arr, 1, arr.length)
                : arr;
    }

    /**
     * 返回一个从开头删除n个元素的数组。
     *
     * @param arr
     * @param n
     * @param <T>
     * @return
     */
    public static <T> T[] take(T[] arr, int n) {
        return Arrays.copyOfRange(arr, 0, n);
    }

    /**
     * 返回从末尾移除n个元素的数组。
     * 使用 Arrays.copyOfRange() 用从末尾取来的 N 个元素来创建一个数组。
     *
     * @param arr
     * @param n
     * @param <T>
     * @return
     */
    public static <T> T[] takeRight(T[] arr, int n) {
        return Arrays.copyOfRange(arr, arr.length - n, arr.length);
    }

    /**
     * 返回两个数组中任何一个中存在的每个元素一次。
     * 使用 a 和 b 的所有值创建一个 Set，并将其转换为数组。
     *
     * @param first
     * @param second
     * @param <T>
     * @return
     */
    public static <T> T[] union(T[] first, T[] second) {
        Set<T> set = new HashSet<>(Arrays.asList(first));
        set.addAll(Arrays.asList(second));
        return set.toArray((T[]) Arrays.copyOf(new Object[0], 0, first.getClass()));
    }

    /**
     * 筛选出具有指定值之一的数组的元素。
     * 使用 Arrays.strean().filter() 创建一个数组，排除(使用 !Arrays.asList(elements).contains())所有命中的值。
     *
     * @param arr
     * @param elements
     * @param <T>
     * @return
     */
    public static <T> T[] without(T[] arr, T... elements) {
        List<T> excludeElements = Arrays.asList(elements);
        return Arrays.stream(arr)
                .filter(el -> !excludeElements.contains(el))
                .toArray(i -> (T[]) Arrays.copyOf(new Object[0], i, arr.getClass()));
    }


    /**
     * 根据原始数组中的位置创建元素数组。
     *
     * @param arrays
     * @return
     */
    public static List<Object[]> zip(Object[]... arrays) {
        OptionalInt max = Arrays.stream(arrays).mapToInt(arr -> arr.length).max();
        return IntStream.range(0, max.getAsInt())
                .mapToObj(i -> Arrays.stream(arrays)
                        .map(arr -> i < arr.length ? arr[i] : null)
                        .toArray())
                .collect(Collectors.toList());
    }

    /**
     * 给定有效的属性标识符数组和值数组，返回将属性与值关联的对象。
     *
     * @param props
     * @param values
     * @return
     */
    public static Map<String, Object> zipObject(String[] props, Object[] values) {
        return IntStream.range(0, props.length)
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(props[i], i < values.length ? values[i] : null))
                .collect(
                        HashMap::new, (m, v) -> m.put(v.getKey(), v.getValue()), HashMap::putAll);
    }
}
