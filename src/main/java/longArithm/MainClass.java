package longArithm;

public class MainClass {
    public static void main(String[] args) {
        DecimalNumber num = new DecimalNumber("27209872432345234523", "000020000200002000099009209002000020000200002");
        System.out.println(num.sum(new DecimalNumber("97208872434345234523", "19998707087832944444444444444444444444444444444444449"), 1));
    }
}
