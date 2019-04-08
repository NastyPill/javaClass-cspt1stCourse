package longArithm;

import org.junit.Assert;
import org.junit.Test;

public class DecimalNumberTest {


    @Test
    public void sumTest() {
        Assert.assertEquals(new DecimalNumber("20.20"),
                new DecimalNumber("10.10")
                        .sum(new DecimalNumber("10.10"), 10));

        Assert.assertEquals(new DecimalNumber("45.2"),
                new DecimalNumber("30.10")
                        .sum(new DecimalNumber("15.1000010276276738"), 1));

        Assert.assertEquals(new DecimalNumber("20.20202"),
                new DecimalNumber("10.10101010101010")
                        .sum(new DecimalNumber("10.10101"), 5));

        Assert.assertEquals(new DecimalNumber("0"), new DecimalNumber("").sum(new DecimalNumber(""), 5));

        Assert.assertEquals(new DecimalNumber("109999999999999999999999.20"),
                new DecimalNumber("99999999999999999999999.999999")
                        .sum(new DecimalNumber("9999999999999999999999.9999999999"), 2));

        Assert.assertEquals(new DecimalNumber("19999999999999999999999.19999989999"),
                new DecimalNumber("9999999999999999999999.999999")
                        .sum(new DecimalNumber("9999999999999999999999.9999999999"), -1));
    }

    @Test
    public void diffTest() {
        Assert.assertEquals(0,
                new DecimalNumber("10.10")
                        .difference(new DecimalNumber("10.10"), 2).getStandartType(StandartTypes.INT));

        Assert.assertEquals(new DecimalNumber("-0.11"),
                new DecimalNumber("101.8888")
                        .difference(new DecimalNumber("101.9999"), 2));

        Assert.assertEquals(new DecimalNumber("-0.111174536076418535544618034832024701234243"),
                new DecimalNumber("101.888823698713269047890714208957098701234243")
                        .difference(new DecimalNumber("101.9999971627896705123461322341251234"), -1));

        Assert.assertEquals(new DecimalNumber("1111111111111111123456788.13580"),
                new DecimalNumber("9999999999999999999999999.12345678")
                        .difference(new DecimalNumber("8888888888888888876543210.98765432"), 5));

        Assert.assertEquals(-410.0976,
                new DecimalNumber(162.29)
                        .difference(new DecimalNumber(572.1924), 5).getStandartType(StandartTypes.DOUBLE));
    }

    @Test
    public void multiTest() {
        Assert.assertEquals(new DecimalNumber("102.0100"),
                new DecimalNumber("10.10")
                        .multiplication(new DecimalNumber("10.10"), -1));

        Assert.assertEquals(new DecimalNumber("495027.5720"),
                new DecimalNumber("130.92123")
                        .multiplication(new DecimalNumber("3781.110"), 4));

        Assert.assertEquals(
                "0",
                new DecimalNumber("10.10")
                        .multiplication(new DecimalNumber("0"), 10).getNumber()
        );

        Assert.assertEquals(
                5.9f,
                new DecimalNumber(2.56)
                        .multiplication(new DecimalNumber(2.3), 1).getStandartType(StandartTypes.FLOAT)
        );

        Assert.assertEquals(
                new DecimalNumber("2.0"),
                new DecimalNumber("2.0")
                        .multiplication(new DecimalNumber("1.0"))
        );

    }

}
