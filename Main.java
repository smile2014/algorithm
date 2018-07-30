import java.util.Stack;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String a = "7,8,+,3,2,+,/";
        System.out.println(postfixEval(a));

        String  b ="+,+,3,2,*,6,5";
        System.out.println(prefixExpressionEval(b));
    }

   /*逆波兰或者叫后缀表达式*/
    private static  double  postfixEval(String postfixExpr){
        double result = 0;
        Stack operandStack = new Stack();
        String[] arr = postfixExpr.split(",");
        for (int i = 0; i < arr.length; i++) {
            String token = arr[i].trim();
            if (isNumeric(token)){
                operandStack.push(Double.parseDouble(token));
            }else{
                Double operand2 = (Double) operandStack.pop();
                Double operand1 = (Double) operandStack.pop();
                result = doMath(token,operand1,operand2);
                operandStack.push(result);
            }
        }
        return (double) operandStack.pop();
    }

    /*Prefix Expression polish expression*/
    private static  double  prefixExpressionEval(String postfixExpr) {
        double result = 0;
        Boolean pending_operand = false;
        Stack operandStack = new Stack();
        Stack operatorStack = new Stack();
        String[] arr = postfixExpr.split(",");
        for (int i = 0; i < arr.length; i++) {
            String token = arr[i].trim();
            if (isNumeric(token)){
                double operand = Double.parseDouble(token);
                if (pending_operand == true){
                    while( ! operandStack.isEmpty() ) {
                        Double operand1 = (Double) operandStack.pop();
                        String operator = (String) operatorStack.pop();
                        operand = doMath(operator,operand1,operand);
                    }
                }
                operandStack.push(operand);
                pending_operand = true;
            }else{
                operatorStack.push(token);
                pending_operand = false;
            }
        }
        return (double) operandStack.pop();
    }

    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
    private static  double doMath(String op, Double op1,Double op2) {
        if (op.equals("*") ) {
            return op1 * op2;
        } else if (op.equals("/")) {
            return op1 / op2;
        } else if (op.equals("+")) {
            return op1 + op2;
        } else {
            return op1 - op2;
        }
    }
}
