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
    }

    private static void stringTest(String s) {
        DiscreteString dStr = DiscreteString.create(DiscreteCharType.SEVEN_SEGMENT_CHAR, s);
        System.out.print(String.format("%s:\n%s\n\n", s, dStr));
    }

    private static void intTest(int n) {
        DiscreteString dStr = DiscreteString.create(DiscreteCharType.SEVEN_SEGMENT_CHAR, n);
        System.out.print(String.format("%d:\n%s\n\n", n, dStr));
    }
}
