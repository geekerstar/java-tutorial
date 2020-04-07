import com.google.gson.InstanceCreator;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;

/**
 * @author One
 * @date 2019/09/07
 */
public class InstanceCreatorTest {
    @Test
    void test() {

    }
}

class Money {

    private final String usd;
    private final String s;

    public Money(String s, String usd) {
        this.s = s;
        this.usd = usd;
    }

    public String getUsd() {
        return usd;
    }

    public String getS() {
        return s;
    }
}

class MoneyInstanceCreator implements InstanceCreator<Money> {
    public Money createInstance(Type type) {
        return new Money("1000000", "USD");
    }
}
