package longArithm;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class DecimalNumber {

    private String fractionalPart;
    private String integerPart;
    private StringBuilder number;

    /*
     *   Construct Decimal number from two parts
     *   which are strings
     */

    public DecimalNumber(String fractionalPart, String integerPart) {
        this.fractionalPart = fractionalPart;
        this.integerPart = integerPart;
        setNumber();
    }

    //  Construct Decimal number from primitive

    public DecimalNumber(Number number) {
        setParts(number);
        setNumber();
    }

    private void setParts(Number number) {
        if (number instanceof Long) {
            integerPart = Long.toString(number.longValue());
        }
        if (number instanceof Integer) {
            integerPart = Integer.toString(number.intValue());
        }
        if (number instanceof Float) {
            String[] arr = Float.toString(number.floatValue()).split("\\.");
            integerPart = arr[0];
            fractionalPart = arr[1];
        }
        if (number instanceof Double) {
            String[] arr = Double.toString(number.doubleValue()).split("\\.");
            integerPart = arr[0];
            fractionalPart = arr[1];
        }
    }

    private void setNumber() {
        number = new StringBuilder();
        number.append(integerPart);
        number.append(".");
        number.append(fractionalPart);
    }

    public String getNumber() {
        return number.toString();
    }

    private String reverseString(String number) {
        return new StringBuilder(number).reverse().toString();
    }

    //array represents number like xxxx * 10^i

    private int[] setPowArray(String number) {
        int size = number.length() / 4 + 1;
        int[] powArray = new int[size];
        String reversedNum = reverseString(number);
        for (int i = 0; i < size; i++) {
            int endIndex = (i + 1) * 4;
            if((i + 1) * 4 > number.length() - 1) {
                endIndex = number.length() - 1;
            }
            powArray[0] = Integer.parseInt(reversedNum.substring(i * 4, endIndex));
        }
        return powArray;
    }

    public String sum(DecimalNumber other, int accuracy) {
        //TODO()
        throw new UnsupportedOperationException();
    }

    public String difference(DecimalNumber other, int accuracy) {
        //TODO()
        throw new UnsupportedOperationException();
    }

    public String multiplication(DecimalNumber other, int accuracy) {
        //TODO()
        throw new UnsupportedOperationException();
    }

    public String round(DecimalNumber number, int accuracy) {
        //TODO()
        throw new UnsupportedOperationException();
    }

    public Number getStandartType(DecimalNumber number, standartTypes type) {
        switch (type) {
            case INT: {

            }
            case LONG: {

            }
            case DOUBLE: {

            }
            case FLOAT: {

            }
            default:
                throw new IllegalArgumentException();
        }
    }
}
