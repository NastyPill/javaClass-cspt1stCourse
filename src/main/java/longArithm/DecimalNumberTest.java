package longArithm;

import org.junit.Assert;
import org.junit.Test;

public class DecimalNumberTest {



    @Test
    public void sumTest() {
        Assert.assertEquals(new DecimalNumber("20.20"), new DecimalNumber("10.10")
                                                    .sum(new DecimalNumber("10.10"), 10));

        Assert.assertEquals(new DecimalNumber("45.2"), new DecimalNumber("30.10")
                                        .sum(new DecimalNumber("15.1000010276276738"), 1));

        Assert.assertEquals(new DecimalNumber("20.20202"), new DecimalNumber("10.10101010101010")
                                                    .sum(new DecimalNumber("10.10101"), 5));

        Assert.assertEquals(new DecimalNumber("0"), new DecimalNumber("")
                                                    .sum(new DecimalNumber(""), 5));
    }

}
