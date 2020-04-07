import com.google.gson.InstanceCreator;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;

/**
 * @author One
 * @date 2019/09/07
 */
public class NestedClassesTest {
    @Test
    void name() {

    }
}

class A {
    public String a;

    class B {

        public String b;

        public B() {
            // No args constructor for B
        }
    }
}

class InstanceCreatorForB implements InstanceCreator<A.B> {
    private final A a;

    public InstanceCreatorForB(A a) {
        this.a = a;
    }

    public A.B createInstance(Type type) {
        return a.new B();
    }
}
