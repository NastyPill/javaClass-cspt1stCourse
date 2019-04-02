package longArithm;

import java.util.ArrayList;
import java.util.Objects;

public class DecimalNumber {

    private static final int BETA = 10000;

    private String fractionalPart;
    private String integerPart;
    /*
     *  full representation of number
     *  contains: intPart + "." + fractPart
     */
    private StringBuilder number;

    private int countOfDigits;
    private int zerosAfterMulti;

    private Boolean isInt = false;
    private Boolean moreThanOne = false;
    private Boolean lessThanOne = false;
    private Boolean negative = false;
    private Boolean thisBigger = false;
    private Boolean fracPartNull = false;

    /*
     *   Construct Decimal number from two parts
     *   which are strings
     */

    public DecimalNumber(String integerPart, String fractionalPart) {
        this.fractionalPart = fractionalPart;
        this.integerPart = integerPart;
        ifEmpty();
        setNumber();
    }

    public DecimalNumber() {

    }

    //  Construct Decimal number from primitive

    public DecimalNumber(Number number) {
        setPrimitive(number);
        ifEmpty();
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
            fractionalPart = "0";
        }
        setNumber();
    }

    private void ifEmpty() {
        if (this.fractionalPart.isEmpty()) {
            this.fractionalPart = "0";
        }
        if (this.integerPart.isEmpty()) {
            this.integerPart = "0";
            fracPartNull = true;
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
        setNumber();
        if (fracPartNull || fractPartIsZero(this.fractionalPart)) {
            return this.integerPart;
        }
        return this.number.toString();
    }

    private String addZerosToString(String number, int zeros, Boolean isInt) {
        if (zeros < 1) {
            return number;
        }
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
            countOfDigits = digits.length();
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
                if (!isInt) {
                    moreThanOne = true;
                }
                result.add(sum);
            } else {
                if (sum >= BETA) {
                    thisArray[i + 1] += 1;
                    result.add(sum);
                } else {
                    if (!isInt && i == thisArray.length - 1 && Integer.toString(sum).length() > countOfDigits) {
                        System.out.println(countOfDigits);
                        moreThanOne = true;
                        sum %= Math.pow(10, Integer.toString(thisArray[i]).length());
                    }
                    result.add(sum);
                }
            }
        }
        isInt = true;
        return result;
    }

    private Boolean whatIsBigger(DecimalNumber other) {
        if (this.integerPart.length() > other.integerPart.length()) {
            return true;
        } else if (this.integerPart.length() < other.integerPart.length()) {
            return false;
        } else {
            for (int i = 0; i < this.integerPart.length(); i++) {
                if (this.integerPart.charAt(i) > other.integerPart.charAt(i)) {
                    return true;
                } else if (this.integerPart.charAt(i) < other.integerPart.charAt(i)) {
                    return false;
                }
            }
            for (int i = 0; i < Math.min(this.fractionalPart.length(), other.fractionalPart.length()); i++) {
                if (this.fractionalPart.charAt(i) > other.fractionalPart.charAt(i)) {
                    return true;
                } else if (this.fractionalPart.charAt(i) < other.fractionalPart.charAt(i)) {
                    return false;
                }
            }
            return Math.min(this.fractionalPart.length(), other.fractionalPart.length()) != this.fractionalPart.length();
        }
    }

    private ArrayList<Integer> differentiator(int[] thisArray, int[] otherArray) {
        System.out.println(thisBigger);
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < thisArray.length; i++) {
            int res = thisArray[i] - otherArray[i];
            if (!thisBigger) {
                negative = true;
                res = Math.abs(otherArray[i] - thisArray[i]);
                System.out.println(otherArray[i] + " - " + thisArray[i] + " = " + res);
            } else {
                if (i == thisArray.length - 1 && res < 0) {
                    if (!this.integerPart.equals("0")) {
                        if (!isInt) {
                            lessThanOne = true;
                            if (thisArray[i] < 10) {
                                res = res + 10;
                            } else if (thisArray[i] < 100) {
                                res = res + 100;
                            } else if (thisArray[i] < 1000) {
                                res = res + 1000;
                            } else {
                                res = res + BETA;
                            }
                        } else {
                            res = res;
                        }
                    } else {
                        res = res * -1;
                    }
                } else {
                    if (res < 0) {
                        thisArray[i + 1] -= 1;
                        res = res + BETA;
                    }
                }
            }
            result.add(res);
        }

        isInt = true;
        return result;
    }

    private ArrayList<Integer> fillList(DecimalNumber other, Boolean isInt, OperationType type) {
        int[] thisArray;
        int[] otherArray;

        if (isInt) {
            int zeros = this.integerPart.length() - other.integerPart.length();
            thisArray = setPowArray(this.integerPart, zeros > 0 ? 0 : -zeros, true);
            otherArray = setPowArray(other.integerPart, zeros > 0 ? zeros : 0, true);
        } else {
            int zeros = this.fractionalPart.length() - other.fractionalPart.length();
            thisArray = setPowArray(this.fractionalPart, zeros > 0 ? 0 : -zeros, false);
            otherArray = setPowArray(other.fractionalPart, zeros > 0 ? zeros : 0, false);
        }

        switch (type) {
            case SUM: {
                return summator(thisArray, otherArray);
            }

            case DIFF: {
                return differentiator(thisArray, otherArray);
            }

            default: {
                throw new UnsupportedOperationException();
            }
        }
    }

    private DecimalNumber multiplicator(DecimalNumber other, int accuracy) {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<DecimalNumber> nums = new ArrayList<>();
        int zeros = this.integerPart.length() - other.integerPart.length();
        int[] thisArray = setPowArray(this.getNumber().replace(".", ""), zeros > 0 ? 0 : -zeros, true);
        int[] otherArray = setPowArray(other.getNumber().replace(".", ""), zeros > 0 ? zeros : 0, true);
        for (int value : otherArray) {
            for (int i = 0; i < thisArray.length; i++) {
                System.out.println(i + "-=>" + thisArray[i] + ",,," + value);
                int res = thisArray[i] * value;
                if (res >= BETA && i != thisArray.length - 1) {
                    res %= BETA;
                    thisArray[i + 1] += res / BETA;
                }
                result.add(res);
                System.out.println(i + "->" + res);
            }
            StringBuilder num = new StringBuilder();
            for (Integer integer : result) {
                num.append(integer);
            }
            nums.add(new DecimalNumber(num.toString()));
        }
        DecimalNumber resultNum = nums.get(0);
        for (int i = 1; i < nums.size(); i++) {
            resultNum = resultNum.sum(nums.get(i), -1);
        }
        return round(new DecimalNumber(new StringBuilder(resultNum.getNumber())
                .insert(resultNum.getNumber().length() - zerosAfterMulti, ".").toString()), accuracy);
    }

    private Boolean fractPartIsZero(String fractPart) {
        Boolean zero = true;
        for (int i = 0; i < fractPart.length(); i++) {
            if (fractPart.charAt(i) != '0') {
                zero = false;
            }
        }
        return zero;
    }

    private StringBuilder createIntPart(ArrayList<Integer> intList) {
        StringBuilder result = new StringBuilder();
        //adding int part
        for (int i = intList.size() - 1; i >= 0; i--) {
            String strValue;
            int value = intList.get(i);
            if (moreThanOne && i == 0) {
                value++;
            } else if (lessThanOne && i == 0) {
                value--;
            }
            if (i != intList.size() - 1) {
                strValue = addZerosToString(Integer.toString(Math.abs(value)),
                        4 - Integer.toString(Math.abs(value)).length(), true);
                result.append(strValue);
            } else {
                result.append(value);
            }

            if (value > BETA && intList.size() > 1) {
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

        if (negative) {
            result.insert(0, "-");
        }
        return result;
    }

    private StringBuilder createFractPart(ArrayList<Integer> fractList) {
        StringBuilder result = new StringBuilder();
        for (int i = fractList.size() - 1; i >= 0; i--) {
            int value = fractList.get(i);
            String strValue;
            if (i == fractList.size() - 1) {
                strValue = addZerosToString(Integer.toString(Math.abs(value)),
                        countOfDigits - Integer.toString(Math.abs(value)).length(), true);
            } else {
                strValue = addZerosToString(Integer.toString(Math.abs(value)),
                        4 - Integer.toString(Math.abs(value)).length(), true);
            }
            result.append(strValue);
            if (value >= BETA) {
                result.deleteCharAt(result.length() - 5);
            }
        }

        return result;
    }

    private void reinit() {
        lessThanOne = false;
        moreThanOne = false;
        isInt = false;
        negative = false;
    }

    private DecimalNumber resulting(DecimalNumber other, int accuracy, OperationType type) {
        DecimalNumber result;

        if (type == OperationType.MULTI) {
            return multiplicator(other, accuracy);
        } else {
            String fractString = createFractPart(fillList(other, false, type)).toString();
            String intString = createIntPart(fillList(other, true, type)).toString();
            result = round(new DecimalNumber(intString, fractString), accuracy);
        }

        reinit();
        return result;
    }

    public DecimalNumber sum(DecimalNumber other, int accuracy) {
        return resulting(other, accuracy, OperationType.SUM);
    }

    public DecimalNumber difference(DecimalNumber other, int accuracy) {
        thisBigger = whatIsBigger(other);
        return resulting(other, accuracy, OperationType.DIFF);
    }

    public DecimalNumber multiplication(DecimalNumber other, int accuracy) {
        zerosAfterMulti = 0;
        if (this.fractPartIsZero(this.fractionalPart)) {
            zerosAfterMulti -= 1;
        }
        if (other.fractPartIsZero(other.fractionalPart)) {
            zerosAfterMulti -= 1;
        }
        zerosAfterMulti += this.fractionalPart.length() + other.fractionalPart.length();
        return resulting(other, accuracy, OperationType.MULTI);
    }

    private DecimalNumber round(DecimalNumber num, int accuracy) {
        //-1 if no need in rounding
        if (accuracy == -1) {
            return num;
        }
        StringBuilder number = new StringBuilder(num.fractionalPart);
        if (accuracy >= num.fractionalPart.length()) {
            return new DecimalNumber(num.integerPart, num.fractionalPart);
        }
        if (number.charAt(accuracy) < '5') {
            num.fractionalPart = number.substring(0, accuracy);
            return num;
        }

        if (number.charAt(accuracy) >= '5' && number.charAt(accuracy - 1) < '9') {
            char c = (char) (number.charAt(accuracy - 1) + 1);
            number.replace(accuracy - 1, accuracy, Character.toString(c));
            num.fractionalPart = number.substring(0, accuracy);
            return num;
        } else {
            Boolean additionalDigit = false;
            Boolean posShift = false;
            int lengthOfInt = num.integerPart.length();
            int position = accuracy + num.integerPart.length();
            number = new StringBuilder(num.integerPart + num.fractionalPart);

            while (true) {
                if (number.charAt(position - 1) == '9' || posShift) {
                    posShift = false;
                    if (position > 1) {
                        // xxx.xxx9 -> xxx.xx(x+1)0
                        number.replace(position - 1, position, "0");
                        if (number.charAt(position - 2) == '9') {
                            posShift = true;
                            number.replace(position - 2, position - 1, "0");
                        } else {
                            char c = (char) (number.charAt(position - 2) + 1);
                            number.replace(position - 2, position - 1, Character.toString(c));
                        }
                    } else {
                        additionalDigit = true;
                        number.insert(0, '1');
                        break;
                    }
                } else {
                    break;
                }
                position--;
            }

            if (additionalDigit) {
                lengthOfInt++;
            }

            num.integerPart = number.substring(0, lengthOfInt);
            num.fractionalPart = number.substring(lengthOfInt, lengthOfInt + accuracy);
            return num;
        }
    }

    private Boolean shouldBeIncreased() {
        return this.fractionalPart.charAt(0) > '4';
    }

    public Number getStandartType(StandartTypes type) {
        switch (type) {
            case INT: {
                try {
                    if (this.shouldBeIncreased()) {
                        return Integer.parseInt(this.integerPart) + 1;
                    } else {
                        return Integer.parseInt(this.integerPart);
                    }
                } catch (NumberFormatException ex) {
                    ex.printStackTrace(System.err);
                }
            }
            break;

            case LONG: {
                try {
                    if (this.shouldBeIncreased()) {
                        return Long.parseLong(this.integerPart) + 1;
                    } else {
                        return Long.parseLong(this.integerPart);
                    }
                } catch (NumberFormatException ex) {
                    ex.printStackTrace(System.err);
                }
            }
            break;

            case DOUBLE: {
                try {
                    return Double.parseDouble(this.getNumber());
                } catch (NumberFormatException ex) {
                    ex.printStackTrace(System.err);
                }
            }
            break;

            case FLOAT: {
                try {
                    return Float.parseFloat(this.getNumber());
                } catch (NumberFormatException ex) {
                    ex.printStackTrace(System.err);
                }
            }
            break;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DecimalNumber that = (DecimalNumber) o;
        return fractionalPart.equals(that.fractionalPart) &&
                integerPart.equals(that.integerPart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fractionalPart, integerPart);
    }
}