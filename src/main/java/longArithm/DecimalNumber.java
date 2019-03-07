package longArithm;

public class DecimalNumber {

    private long fractPartLength;
    private String fractionalPart;
    private long intPartLength;
    private String integerPart;
    private StringBuilder wholeNumber;

    public DecimalNumber(String fractionalPart, String integerPart) {
        this.fractionalPart = fractionalPart;
        this.integerPart = integerPart;

        wholeNumber.append(integerPart);
        wholeNumber.append('.');
        wholeNumber.append(fractionalPart);
    }

    public DecimalNumber(long fractPartLength, long intPartLength) {
        this.fractPartLength = fractPartLength;
        this.intPartLength = intPartLength;
    }

    public void setNumber(Number number) {
        if(number instanceof Long) {
            integerPart = Long.toString(number.longValue());
        }
        if(number instanceof Integer) {
            integerPart = Integer.toString(number.intValue());
        }
        if(number instanceof Float) {
            String[] arr = Float.toString(number.floatValue()).split(".");
            integerPart = arr[0];
            fractionalPart = arr[1];
        }
        if(number instanceof Float) {
            String[] arr = Double.toString(number.doubleValue()).split(".");
            integerPart = arr[0];
            fractionalPart = arr[1];
        }
    }

    public String getNumber() {
        return wholeNumber.toString();
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
        //TODO()
        throw new UnsupportedOperationException();
    }
}
