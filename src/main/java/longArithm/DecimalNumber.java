package longArithm;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Objects;

public class DecimalNumber {

    private static final int BETA = 10000;

    private String fractionalPart;
    private String integerPart;
    private StringBuilder number;

    /*
     *   Construct Decimal number from two parts
     *   which are strings
     */

    public DecimalNumber(String integerPart, String fractionalPart) {
        this.fractionalPart = fractionalPart;
        this.integerPart = integerPart;
        setNumber();
    }

    //  Construct Decimal number from primitive

    public DecimalNumber(Number number) {
        setPrimitive(number);
        setNumber();
    }

    //  Construct Decimal number from string

    public DecimalNumber(String number) {
        this.number = new StringBuilder(number);
        if (number.contains(".")) {
            String[] s = number.split("\\.");
            integerPart = s[0];
            fractionalPart = s[1];
        } else {
            integerPart = number;
            fractionalPart = null;
        }
    }

    private void setPrimitive(Number number) {
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

    private String addZerosToString(String number, int zeros) {
        StringBuilder result = new StringBuilder(number);
        for (int i = 0; i < zeros; i++) {
            result.append("0");
        }
        System.out.println(result);
        return result.toString();
    }

    //array represents number like xxxx * 1000^i

    private int[] setPowArray(String number, int zeros) {
        String editedNum = addZerosToString(number, zeros);
        Number n = editedNum.length();
        System.out.println(Math.ceil(n.doubleValue() / 4));
        int size = (int) Math.ceil(n.doubleValue() / 4);
        int[] powArray = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            int endIndex = (i + 1) * 4;
            if ((i + 1) * 4 > editedNum.length() - 1) {
                endIndex = editedNum.length() - 1;
            }

            if (endIndex != i * 4) {
                powArray[i] = Integer.parseInt(editedNum.substring(i * 4, endIndex));
            } else {
                powArray[i] = editedNum.charAt(endIndex) - '0';
            }
            System.out.println("pow arr " + powArray[i] + " " + i);
        }
        return powArray;
    }

    //returns a + b as a string

    public String sum(DecimalNumber other, int accuracy) {
        StringBuilder result = new StringBuilder();
        ArrayList<Integer> fractList = new ArrayList<>();
        ArrayList<Integer> intList = new ArrayList<>();

        //Summ fractal part of number
        {
            Boolean moreThanOne = false;
            int zeros = this.fractionalPart.length() - other.fractionalPart.length();
            int[] thisArray = setPowArray(this.fractionalPart, zeros > 0 ? 0 : -zeros);
            int[] otherArray = setPowArray(other.fractionalPart, zeros > 0 ? zeros : 0);

            for (int i = thisArray.length - 1; i >= 0; i--) {
                int sum = thisArray[i] + otherArray[i];
                if (i == 0 && sum >= BETA) {
                    moreThanOne = true;
                    sum -= BETA;
                    fractList.add(sum);
                } else {
                    if (sum >= BETA) {
                        System.out.println("not ok");
                        thisArray[i - 1] += 1;
                        sum -= BETA;
                        fractList.add(sum);
                    } else {
                        System.out.println("ok");
                        fractList.add(sum);
                    }
                }
                System.out.println(sum + "\t" + i);
            }
        }

        for (int i = intList.size() - 1; i >= 0; i--) {
            result.append(intList.get(i));
        }
        result.append('.');
        for (int i = fractList.size() - 1; i >= 0; i--) {
            result.append(fractList.get(i));
        }
        return result.toString();
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
