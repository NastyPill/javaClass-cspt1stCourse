package longArithm;

public class MainClass {
    public static void main(String[] args) {
        DecimalNumber num1 = new DecimalNumber("0", "5");
        DecimalNumber num2 = new DecimalNumber("0", "6");
        DecimalNumber num3 = num1.multiplication(num2, -1);
        System.out.println(num3.getNumber());

        //bug rep
        // 0.5 * 0.5 -> .25 here should be a zero!
    }
}
