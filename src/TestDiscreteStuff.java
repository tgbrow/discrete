import java.util.Random;

public class TestDiscreteStuff {

    public static void main(String[] args) {
        String[] testInputStr = {
            "123",
            "abc",
            "hbo275893",
        };
        for (String s : testInputStr) {
            stringTest(s);
        }

        Random r = new Random();
        int max = 999_999;
        for (int i = 0; i < 3; ++i) {
            // random in range [-max, max)
            int x = max - r.nextInt(2*max);
            intTest(x);
        }

        String s = "teddy madi";
        DiscreteString dStr = DiscreteString.create(DiscreteCharType.PIXEL_24_CHAR, s);
        print(s, dStr.toString());
    }

    private static void stringTest(String s) {
        DiscreteString dStr = DiscreteString.create(DiscreteCharType.SEVEN_SEGMENT_CHAR, s);
        print(s, dStr.toString());
    }

    private static void intTest(int n) {
        DiscreteString dStr = DiscreteString.create(DiscreteCharType.SEVEN_SEGMENT_CHAR, n);
        print(String.valueOf(n), dStr.toString());
    }

    private static void print(String in, String out) {
        System.out.print(String.format("%s:\n%s\n\n", in, out));
    }
}
