package longArithm;

public class MainClass {
    public static void main(String[] args) {
        DecimalNumber num1 = new DecimalNumber("10", "71111111111111111");
        DecimalNumber num2 = new DecimalNumber("20", "923542345213");
        DecimalNumber num3 = num1.difference(num2, 1);
        System.out.println(num3.getNumber());
    }
}
