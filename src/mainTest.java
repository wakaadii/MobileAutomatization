import org.junit.Test;
///test git
public class mainTest extends mathHelper {
    mathHelper math = new mathHelper();
    @Test
    public void myFirstTest() {
        System.out.println(math.calc(5, 10, '-'));
    }
}
