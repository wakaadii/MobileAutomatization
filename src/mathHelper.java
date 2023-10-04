public class mathHelper {

    public int calc(int a, int b, char action) {
        if (action == '+') {
            return this.plus(a, b);
        } else if (action == '-') {
            return this.minus(a, b);
        } else if (action == '*') {
            return this.minus(a,b);
        } else if (action == '/') {
            return this.divide(a, b);
        } else {
            return ErrorMessage("wrong action");
        }
    }
    private int ErrorMessage(String ErrorMessage){
        System.out.println(ErrorMessage);
        return 0;
    }
    private int plus (int a, int b){
        return a + b;
    }
    private int minus (int a, int b){
        return a + b;
    }

    private int multiply (int a, int b){
        return a * b;
    }
    private int divide (int a, int b){
        if (b == 0) {
            this.ErrorMessage("Can't divide by zero");
            return 0;
        } else {
            return a / b;
        }
    }
}
