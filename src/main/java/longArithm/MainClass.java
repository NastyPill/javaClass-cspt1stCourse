package longArithm;

public class MainClass {
    public static void main(String[] args) {
        DecimalNumber num1 = new DecimalNumber("125", "2512345");
        DecimalNumber num2 = new DecimalNumber("2314", "9328473");
        DecimalNumber num3 = num1.difference(num2, 3);
        System.out.println(num3.getNumber());
    }
}
