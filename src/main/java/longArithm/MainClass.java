package longArithm;

public class MainClass {
    public static void main(String[] args) {
        DecimalNumber num1 = new DecimalNumber("", "9");
        DecimalNumber num2 = new DecimalNumber("", "9");
        DecimalNumber num3 = num1.sum(num2, 10);
        System.out.println(num3.getStandartType(StandartTypes.DOUBLE));
    }
}
