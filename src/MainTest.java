import org.junit.Test;
///test git what's wrong with it?!
public class MainTest extends mathHelper {
    mathHelper math = new mathHelper();
    @Test
    public void myFirstTest() {
        System.out.println(math.calc(5, 10, '-'));
    }
}
