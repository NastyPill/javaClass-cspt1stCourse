package longArithm;

import java.util.ArrayList;

public class DecimalNumber {

    private static final int BETA = 10000;

    private String fractionalPart;
    private String integerPart;
    private StringBuilder number;

    private Boolean moreThanOne = false;

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

    private String addZerosToString(String number, int zeros, Boolean isInt) {
        StringBuilder result = new StringBuilder();
        if (isInt) {
            for (int i = 0; i < zeros; i++) {
                result.append("0");
            }
            result.append(number);
        } else {
            result.append(number);
            for (int i = 0; i < zeros; i++) {
                result.append("0");
            }

        }
        return result.toString();
    }

    //array represents number like xxxx * 1000^i

    private int[] setPowArray(String number, int zeros, Boolean isInt) {
        StringBuilder editedNum = new StringBuilder(addZerosToString(number, zeros, isInt));
        Number n = editedNum.length();
        int size = (int) Math.ceil(n.doubleValue() / 4);
        int[] powArray = new int[size];

        for (int i = size - 1; i >= 0; i--) {
            StringBuilder digits = new StringBuilder();
            for (int j = 0; j < 4 && editedNum.length() > 0; j++) {
                digits.insert(0, editedNum.charAt(editedNum.length() - 1));
                editedNum.deleteCharAt(editedNum.length() - 1);
            }
            powArray[size - i - 1] = Integer.parseInt(digits.toString());
        }

        return powArray;
    }

    //returns a + b as an arrayList

    private ArrayList<Integer> summator(int[] thisArray, int[] otherArray) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < thisArray.length; i++) {
            int sum = thisArray[i] + otherArray[i];
            if (i == thisArray.length - 1 && sum >= BETA) {
                moreThanOne = true;
                result.add(sum);
            } else {
                if (sum >= BETA) {
//                    System.out.println("not ok ->" + thisArray[i] + " " + otherArray[i]);
//                    System.out.println(thisArray[i-1]);
                    thisArray[i + 1] += 1;
//                    System.out.println(thisArray[i-1]);
                    result.add(sum);
                } else {
                    //System.out.print("ok ->");
                    result.add(sum);
                }
            }
            System.out.println(thisArray[i] + " + " + otherArray[i] + " = " + sum);
        }
        return result;
    }

    public String sum(DecimalNumber other, int accuracy) {
        StringBuilder result = new StringBuilder();

        //fill list for fractPart
        int zeros = this.fractionalPart.length() - other.fractionalPart.length();
        int[] thisArray = setPowArray(this.fractionalPart, zeros > 0 ? 0 : -zeros, false);
        int[] otherArray = setPowArray(other.fractionalPart, zeros > 0 ? zeros : 0, false);
        ArrayList<Integer> fractList = summator(thisArray, otherArray);

        //fill list for intPart
        zeros = this.integerPart.length() - other.integerPart.length();
        thisArray = setPowArray(this.integerPart, zeros > 0 ? 0 : -zeros, true);
        otherArray = setPowArray(other.integerPart, zeros > 0 ? zeros : 0, true);
        ArrayList<Integer> intList = summator(thisArray, otherArray);

        //adding int part
        for (int i = intList.size() - 1; i >= 0; i--) {
            if (moreThanOne && i == 0) {
                result.append(intList.get(i) + 1);
            } else {
                result.append(intList.get(i));
            }
            if (intList.get(i) >= BETA && intList.size() > 1) {
                result.deleteCharAt(result.length() - 5);
            }
        }

        //if there's no int part
        if (result.length() == 0) {
            if (moreThanOne) {
                result.append(1);
            } else {
                result.append(0);
            }
        }
        //if fract part exists
        if (!fractList.isEmpty())
            result.append('.');

        //adding fract part
        for (int i = fractList.size() - 1; i >= 0; i--) {
            result.append(fractList.get(i));
            if (fractList.get(i) >= BETA) {
                result.deleteCharAt(result.length() - 5);
            }
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
