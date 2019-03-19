package longArithm;

public class MainClass {
    public static void main(String[] args) {
        DecimalNumber num1 = new DecimalNumber("", "");
        DecimalNumber num2 = new DecimalNumber("", "");
        DecimalNumber num3 = num1.sum(num2, 0);
        System.out.println(num3.getNumber());
    }
}
