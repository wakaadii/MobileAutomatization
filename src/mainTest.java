import org.junit.Test;
public class mainTest extends mathHelper {
    mathHelper math = new mathHelper();
    @Test
    public void myFirstTest() {
        System.out.println(math.calc(5, 10, '-'));
    }
}
