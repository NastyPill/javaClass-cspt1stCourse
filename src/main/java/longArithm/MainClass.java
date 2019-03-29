package longArithm;

public class MainClass {
    public static void main(String[] args) {
        DecimalNumber num1 = new DecimalNumber("0", "099");
        DecimalNumber num2 = new DecimalNumber("0", "001");
        DecimalNumber num3 = num1.sum(num2, 15);
        System.out.println(num3.getNumber());
    }
}
