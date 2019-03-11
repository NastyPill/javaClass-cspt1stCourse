package longArithm;

public class MainClass {
    public static void main(String[] args) {
        DecimalNumber num = new DecimalNumber("123", "1111222233334444555560000000000000000000000");
        System.out.println(num.sum(new DecimalNumber("123", "1111222233334444555569999999999999999999911"), 1));
    }
}
