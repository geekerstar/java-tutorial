# 除了FastJson,你还有的选Gson简易指南

## 前言

这个周末被几个技术博主的同一篇公众号文章  [fastjson又被发现漏洞，这次危害可导致服务瘫痪！](https://mp.weixin.qq.com/s/yVzZTTR5R6QbspTg5WI5hg) 刷屏，离之前漏洞事件没多久，fastjson 又出现严重 Bug。目前项目中不少使用了 fastjson 做对象与JSON数据的转换，又需要更新版本重新部署，可以说是费时费力。与此同时，也带给我新的思考，面对大量功能强大的开源库，我们不能盲目地引入到项目之中，众多开源框架中某个不稳定因素就足以让一个项目遭受灭顶之灾。趁着周末，在家学习下同样具备JSON与对象转换功能的优秀开源框架 Gson，并且打算将今后项目使用 fastjson 的地方逐渐换成使用 Gson，记录下学习总结的内容，希望对小伙伴也有所帮助。

> 本文所涉及所有代码片段均在下面仓库中，感兴趣的小伙伴欢迎参考学习：
>
> https://github.com/wrcj12138aaa/gson-actions
>
> 版本支持：
>
> - JDK 8
> - Gson 2.8.5
> - JUnit 5.5.1
> - Lomok 1.18.8

## Gson 简介

在正式介绍 Gson 之前，我们可以先从[官方的wiki](https://github.com/google/gson/blob/master/UserGuide.md#TOC-Overview)看下 Gson 的描述，了解它是什么？

> Gson is a Java library that can be used to convert Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object。

从描述可以看出，Gson 是用于将 Java 对象与 JSON格式字符串数据相互转换的 Java 库。它起初在Google 内部广泛使用在 Android 平台 和 Java 服务端上。2008 年开源之后，成为了谷歌又一个被广泛使用的开源框架，截止目前*(2019.09.08)* 在GitHub 上已有1W6 多星，相同作用的类库还有 Spring Framework 中集成的 Jackson，以及阿里开源的 fastjson等。

在特性方面，Gson 提供简易的API `fromJson/toJson` 来实现 Java 与 JSON 之间的转换，并且能生成紧凑，可读的 JSON 字符串输出，还支持复杂对象转换和丰富的自定义表示，足以满足在日常开发中我们绝大部分的 JSON 数据处理需求。

> 我们通常将对象与JSON字符串间的转换称之为序列化和反序列化(Serialization/Deserialization)。将 对象转化成 JSON字符串的过程称为序列化，将JSON 字符串转化成对象的过程称为反序列化。![](https://tva1.sinaimg.cn/large/006y8mN6gy1g6s3u2bfxxj30oa0ewwfn.jpg)

## Gson 基本使用

使用 Gson 框架进行序列化与反序列操作，都离不开 `com.google.gson.Gson` 对象，它也是 Gson 框架的关键对象，提供的公共 API 具备了多种序列化和反序列方式。

![](https://tva1.sinaimg.cn/large/006y8mN6gy1g6s4ign9myj30w20swgs2.jpg)

Gson 对象的创建主要有两种方式： 

- 使用 **new** 关键字直接创建：`Gson gson = new Gson()`
- 由 GsonBuilder 对象构建：`Gson gson = new GsonBuilder().create()`

通常情况下，上面两种方式创建的 Gson 对象在进行序列化与反序列操作时行为都是一样的，但是第二种方式构建 Gson 对象时，允许进行额外的行为定制，比如格式化 JSON 字符串的输出内容，是否序列化 **null** 值等等。

###  Java 序列化

#### 简单对象的序列化

我们可以通过下面的例子来看下通过上述两种方式序列化 Java 对象的不同效果：

```java
public class ResultTest {
    @Test
    void test_serialization() {
        Gson gson = new Gson();
        Result result = new Result(200, "成功", null);
        String json = gson.toJson(result);
        System.out.println("json is " + json);
      
        Gson buildedGson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        String buildedJson = buildedGson.toJson(result);
        System.out.println("buildedJson is " + buildedJson);
    }

    class Result {
        private int code;
        private String message;
        private Object data;

        public Result(int code, String message, Object data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }
    }
}
```

运行该测试用例，在控制台可以看到如下日志输出：

![](https://tva1.sinaimg.cn/large/006y8mN6gy1g6s51fgqp5j30o2090q3h.jpg)

从结果可以看出，默认的 Gson 对象行为序列化对象时会将 `null` 值的字段忽略，而 `com.google.gson.GsonBuilder#serializeNulls` 方法将允许 Gson 对象序列化 `null` 字段；并且正常序列化后的 JSON 字符串是紧凑格式，节省字符串内存，使用 `com.google.gson.GsonBuilder#setPrettyPrinting` 方法之后最终输出的 JSON 字符串是更易读的格式。当然除了这两个方法，GsonBuilder 还提供了许多定制序列化和反序列化行为的API，我们将后面的内容进一步讲解。

#### JosnObject 生成 JSON

除了上述将自定义类的对象转换成  JSON 的方式之外，还可以使用 Gson 框架提供的 JsonObject 构建普通对象，然后使用 `toJson` 方法生成 JSON 字符串，在原测试类中补充下方测试类，并运行查看效果如下

```java
@Test
void test_jsonObject_serialization() {
  Gson gson = new Gson();
  JsonObject jsonObject = new JsonObject();
  jsonObject.addProperty("code", 400);
  jsonObject.addProperty("message", "参数错误");
  String toJson = gson.toJson(jsonObject);
  String exceptedJson = "{\"code\":400,\"message\":\"参数错误\"}";
  Assertions.assertEquals(exceptedJson, toJson); //true
}
```

JsonObject 使用 `addProperty(property,value)` 方法只能用来添加 String，Number，Boolean，Character这四类数据， 因为内部是调用 `com.google.gson.JsonObject#add`, 将 value 封装成了 JsonPrimitive 对象，然后保存到了内部自定义的 `LinkedTreeMap` 集合变量 members 中；如果需要在 JsonObject 对象上添加其他对象时，就需要直接使用 `add(String property, JsonElement value)` 方法添加一个 JsonElement 对象。这里的 JsonElement 是一个抽象类，JsonObject 和 JsonPrimitive 都继承了JsonElement，所以我们最终通过另外的 JsonObject 对象来作为原 JsonObject 上的属性对象：

```java
Gson gson = new Gson();
JsonObject jsonObject = new JsonObject();
//...
JsonObject nestJsonObject = new JsonObject();
nestJsonObject.addProperty("username", "one");
nestJsonObject.addProperty("score", 99);
jsonObject.add("data", nestJsonObject);
String toJson2 = gson.toJson(jsonObject);
System.out.println(toJson2);
// {"code":400,"message":"参数错误","data":{"username":"one","score":99}}
```

### JSON 反序列化

#### 简单对象的反序列化

现在我们再来看下 JSON 反序列化成 Java 对象用法，这里主要使用方法是 `com.google.gson.Gson#fromJson`，它最基础的用法就是 `fromJson(String json, Class<T> classOfT)`，尝试将 JSON 字符串转为指定 Class 的对象，如果转换失败，就会抛出 `JsonSyntaxException` 异常。我们可以在原来代码上新增一个测试用例，运行看下效果：

```java
@Test
void test_deserialization() {
    String json = "{\"code\":400,\"message\":\"参数错误\"}";
    Result result = new Gson().fromJson(json, Result.class);
    Assertions.assertEquals(400, result.code); // true
    Assertions.assertEquals("参数错误", result.message); // true
}
```

#### 反序列化 Map

除了将JSON 字符串序列化为自定义的Java 对象之外，我们还可以转为 Map 集合，Gson 提供了对 Map 集合的转换，使用起来也十分简单：

```java
@Test
void test_map() {
    String jsonString = "{'employee.name':'one','employee.salary':10}";
    Gson gson = new Gson();
    Map map = gson.fromJson(jsonString, Map.class);
    assertEquals(2, map.size());
		assertEquals("one", map.get("employee.name"));
    assertEquals(Double.class, map.get("employee.name").getClass());
}
```

需要注意的是转换后的 Map 对象真实类型并不是我们经常用的 HashMap，而是 Gson 自定义集合`LinkedTreeMap` ，它实现Map 接口来存储键值对，在新增和删除上实现上进行了优化，并且将存储键值对的顺序作为遍历顺序，也就是先存入的先被遍历到。除此之外，JSON 字符串里的数值型数据都会转转换为 Double 类型，而 `true/false` 数据被会被转换成 Boolean 类型，具体判断依据可以参考 `com.google.gson.internal.bind.ObjectTypeAdapter#read` 方法的实现。

### JSON 与 Array，List 转换

#### JSON 转换 Array

当我们正对 JSON 数据进行数组转换时，类似普通对象转换的方式即可， `toJson` 方法直接使用转为 JSON 数据，`fromJson` 指定数组类型转换为对应类型的数组。

```java
@Test
void test_array() {
  Gson gson = new Gson();
  int[] ints = {1, 2, 3, 4, 5};
  String[] strings = {"abc", "def", "ghi"};
  String s = gson.toJson(ints);// [1,2,3,4,5]
  assertEquals("[1,2,3,4,5]", s); // true

  String s1 = gson.toJson(strings);// ["abc", "def", "ghi"]
  assertEquals("[\"abc\",\"def\",\"ghi\"]", s1);
  String[] strings1 = gson.fromJson(s1, String[].class);
  assertEquals(strings.length, strings1.length); // true
  assertEquals(strings[0], strings1[0]); // true

  int[] ints2 = gson.fromJson("[1,2,3,4,5]", int[].class);
  assertEquals(1, ints2[0]); // true
  assertEquals(5, ints2[4]); // true
}
```

#### JSON 转换 List

要将 List 数据转换为 JSON数据，使用 Gson 的方式与处理 Array 数据一样；这里主要讲的是将JSON 数据转为 List 对象的操作略有不同，要将一个 JSON 数组数据转换为一个自定义类的List 时，我们按照原来的写法如下：

```java
@Test
public void givenJsonString_whenIncorrectDeserializing() {
    Gson gson = new Gson();
    String inputString = "[{\"id\":1,\"name\":\"one\"},{\"id\":2,\"name\":\"two\"}]";
    List<Person> outputList = gson.fromJson(inputString, List.class);
		outputList.get(0).getId();
}
```

但是不幸的是，运行这段代码后会抛出 `ClassCastException` 异常，具体描述如下：

```bash
java.lang.ClassCastException: com.google.gson.internal.LinkedTreeMap cannot be cast to com.one.learn.Person
...
```

从上述描述中我们可以知道执行 `fromJson` 之后，反序列化后得到的 List 元素类型为 LinkedTreeMap，而不是 Person，所以以 Person 对象方式访问 id 属性时就会抛出 `ClassCastException` 异常。那又该如何处理呢,   我们需要调用 Gson 的 另外一个 `fromJson` 方法：`fromJson(String json, Type typeOfT)` ，先看下使用方式

```java
@Test
public void givenJsonString_whenCorrectDeserializing_() {
  Gson gson = new Gson();
  String inputString = "[{\"id\":1,\"name\":\"one\"},{\"id\":2,\"name\":\"two\"}]";
  Type type = new TypeToken<List<Person>>(){}.getType();
  List<Person> outputList = gson.fromJson(inputString, type);
  int id = outputList.get(0).getId();
  assertEquals(1, id); // true
  assertEquals("one", outputList.get(0).getName()); // true
}
```

这个方法中的 Type 对象通过 TypeToken 对象的 `getType` 方法获取到，就是 TypeToken 对象所关联的泛型类型。而这里 TypeToken 是 Gson 为了支持泛型而引入的类，来解决 Java 无法提供泛型类型表示的问题，由于 TypeToken 的构造方法是`protected`修饰的，无法直接构造，使用就需要写成`new TypeToken<List<String>>() {}.getType()`  形式。

## Gson 进阶用法

接触了 Gson 基本的使用之后，我们接着进一步学习 Gson 的其他用法。

### 泛型对象的反序列化

上节内容简单接触了 Gson 对泛型的支持，接下来用代码来展示下它的强大之处，首先我们将上文的 Result 类调整下接受泛型参数：

```java
class Result<T> {
  private int code;
  private String message;
  private T data;

  public Result(int code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }
}
```

然后对一个有内嵌对象的 JSON字符串进行解析成 Result\<User\> 对象，示例代码如下：

```java
@Test
void test_genric_object() {
  String json = "{\"code\":200,\"message\":\"操作成功\",\"data\":{\"username\": \"one\",\"avater\": \"image.jpg\"" +
    "}}";
  Type type = new TypeToken<Result<User>>(){}.getType();
  Result<User> result = new Gson().fromJson(json, type);
  Assertions.assertEquals(200, result.code);
  Assertions.assertEquals("one", result.data.getUsername());
  Assertions.assertEquals("image.jpg", result.data.getAvater());
}

class User {
  private String username;
  private String avater;

  public String getUsername() {
    return username;
  }

  public String getAvater() {
    return avater;
  }
}
```

利用 TypeToken 对象获取具体泛型类型 Result\<User\> , 然后在 `fromJson` 方法中传入就会根据对应类型的执行反序列化操作。

### 自定义序列化

如果我们要对Java 对象的某些字段进行特殊处理，比如隐藏某些字段的序列化，对字段的数据格式化处理等，我们可以通过实现 JsonSerializer 接口，对序列化逻辑进行自定义。例如，我们需要对 Date 类型属性进行特定格式的处理，可以声明 DateSerializer 类实现如下：

```java
class DateSerializer implements JsonSerializer<Date> {
    SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(dateTime.format(src));
    }
}
```

然后在构建 Gson 对象前，利用 GsonBuilder 将 DateSerializer 实例进行注册，使用方式如下：

```java
Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateSerializer()).create();
```

这样一来，一旦遇到要序列化 Date 类型的字段时，都会通过自定义的 `serialize` 方法将日期以 `yyyy-MM-dd` 格式进行输出，如下方的示例代码：

```java
@Test
void test_dateSerializer() {
  MyObject myObject = new MyObject(new Date(), "one");
  Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateSerializer()).create();
  String json = gson.toJson(myObject);
  String exceptedJson = "{\"date\":\"2019-09-08\",\"name\":\"one\"}";
  Assertions.assertEquals(exceptedJson, json); // true
}

class MyObject {
    private Date date;
    private String name;

    public MyObject(Date date, String name) {
        this.date = date;
        this.name = name;
    }

    public MyObject() {
    }
}
```

### 自定义反序列化

与自定义序列化实现方式类似，想要自定义反序列化逻辑，就需要同样要实现一个叫 JsonDeserializer 的接口，进行自定义反序列化逻辑的实现。比如现在有个 JSON 字符串内容为 `{"CODE": 400, "MESSAGE": "参数错误"}`，需要被反序列化为前文提到的 Result 对象，由于字段名不一样，为了实现对应的转换，就需要自定义 ResultDeserializer 类，具体实现如下：

```java
class ResultDeserializer implements JsonDeserializer<Result> {
    @Override
    public Result deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        Result<Object> result = new Result<>(object.getAsJsonPrimitive("CODE").getAsInt(),object.getAsJsonPrimitive("MESSAGE").getAsString(), null);
        return result;
    }
}
```

接下来就是利用 GsonBuilder 注册 ResultDeserializer 实例，生成对应的 Gson 对象，并进行反序列化操作：

```java
@Test
void test_resultDeserializer() {
    //language=JSON
		String json = "{\"CODE\": 400,\"MESSAGE\": \"参数错误\"}";
    Gson gson = new GsonBuilder().registerTypeAdapter(Result.class, new ResultDeserializer())
            .create();
    Result result = gson.fromJson(json, Result.class);
    Assertions.assertEquals(400, result.code); // true
    Assertions.assertEquals("参数错误", result.message); // true
}
```

## Gson 常用注解

Gson 除了提供一些 API 供开发者使用之外，还有一些具有特性的注解可以使用，接下来就介绍在 Gson 中最常用的注解。

### @Expose 

这个注解只能用在字段上，作用就是注明对应的字段是否将在序列化或者反序列化时暴露出来，有两个属性 `serialize` 和 `deserialize` ，默认都为 true。当给一个字段加上 注解`@Expose(serialize = true, deserialize = false)`，则表示了该字段尽在序列化时可见，在反序列化时会忽略赋值。需要额外注意的一点是，@Expose 注解只有在用 GsonBuilder 方式构建 Gson 时有限，并且构建前必须调用 `excludeFieldsWithoutExposeAnnotation` 方法，下面是具体的使用示例：

```java

@Test
void test_expose() {
    MySubClass subclass = new MySubClass(42L, "the answer", "Verbose field not to serialize");
    MyClass source = new MyClass(1L, "foo", "bar", subclass);
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    String s = gson.toJson(source);
    System.out.println(s);
		// {"id":1,"name":"foo","subclass":{"id":42,"description":"the answer","otherVerboseInfo":"Verbose field not to serialize"}}
}

@Data
@AllArgsConstructor
class MyClass {
    private long id;
    @Expose(serialize = false, deserialize = true)
    private String name;
    private transient String other;
    @Expose
    private MySubClass subclass;
}

@Data
@AllArgsConstructor
class MySubClass {
    @Expose
    private long id;
    @Expose
    private String description;
    @Expose
    private String otherVerboseInfo;
}
```

> 在 Gson 中 `transient` 关键字修饰的字段默认不会被序列化和反序列化，这个行为是与 Java 原生的序列化和反序列化操作一致的。

### @Since

该注解用于标记对应字段或者类型的版本，让 Gson 可以指定版本号进行序列化和反序列化操作。当Web服务上的 JSON 数据对应的类存在多个版本的字段时，这个注解就十分有用。

同样地，该注解只针对使用 `GsonBuilder` 方式构建的 Gson 对象，并且使用 `setVersion` 方法指明版本号时有效，只解析对象中对应版本的字段，下面为具体示例：

```java
public class VersioningSupportTest {
    @Test
    void test() {
        VersionedClass versionedObject = new VersionedClass();
        Gson gson = new GsonBuilder().setVersion(1.0).create();
        String jsonOutput = gson.toJson(versionedObject);
        System.out.println(jsonOutput); // {"newField":"new","field":"old"}
    }
}

class VersionedClass {
    @Since(1.1)
    private final String newerField;
    @Since(1.0)
    private final String newField;
    private final String field;

    public VersionedClass() {
        this.newerField = "newer";
        this.newField = "new";
        this.field = "old";
    }
}
```

### @SerializedName

这个注解使用起来比较简单，也很有用。@SerializedName  指定了成员字段被序列化和反序列化时所采用的名称下面是具体使用方式：

```java
public class JSONFieldNamingSupportTest {
    private class SomeObject {
        @SerializedName("custom_naming")
        private final String someField;
        private final String someOtherField;

        public SomeObject(String a, String b) {
            this.someField = a;
            this.someOtherField = b;
        }
    }

    @Test
    void test() {
        SomeObject someObject = new SomeObject("first", "second");
        String jsonRepresentation = gson.toJson(someObject);
        System.out.println(jsonRepresentation);
      	// {"custom_naming":"first","someOtherField":"second"}
        SomeObject someObject1 = gson.fromJson(jsonRepresentation, SomeObject.class);
        System.out.println(someObject1);
      	// SomeObject{someField='first', someOtherField='second'}
    }
}
```

### @JsonAdapter

不同于上面的注解，`@JsonAdapter` 主要作用就是代替  `GsonBuilder.registerTypeAdapter` 方法的执行，直接通过 `@JsonAdapter(aClass.class)` 方式指定 JsonDeserializer 对象或者 JsonSerializer 对象，可以起到相同的想过，并且优先级比`GsonBuilder.registerTypeAdapter`的优先级更高，由于只是将 `registerTypeAdapter`方法执行简化成了注解方法，这里就不再演示，直接在前文**自定义反序列化**一节的 Result 类上使用就可以看到效果。

## 结语

本文主要学习总结了 Gson 框架的序列化和反序列操作使用方式，以及介绍了 Gson 多种特性用法，希望对处理 JSON 数据感到头疼的小伙伴有所帮助。

![](https://tva1.sinaimg.cn/large/006y8mN6gy1g6skkvbjgnj30p00dwjt5.jpg)

## 推荐阅读

- [一文掌握 Spring Boot Profiles](https://mp.weixin.qq.com/s/TfA8rSwYNpW3YV_N8oLJnw)
- [如何优雅关闭 Spring Boot 应用](https://mp.weixin.qq.com/s/-t2hrrVMBpPmVEzDcC8J5w)
- [需要接口管理的你了解一下？](https://mp.weixin.qq.com/s/uWRnRhH4et-XSD101Xh6LQ)
- [Java 之 Lombok 必知必会](https://mp.weixin.qq.com/s/2qkNz4VPgnixXjaVYUkvvQ)
- [Java 微服务新生代之 Nacos](https://mp.weixin.qq.com/s/vS36glyNoD26GL6cbNs5Qw)

## 参考资料

- https://github.com/google/gson/blob/master/UserGuide.md
- https://www.jianshu.com/p/e740196225a4
- https://juejin.im/post/5aad29f8518825558453c6c9
- https://www.baeldung.com/gson-deserialization-guide
- https://www.baeldung.com/gson-string-to-jsonobject
