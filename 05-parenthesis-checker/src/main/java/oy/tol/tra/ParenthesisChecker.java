package oy.tol.tra;


public class ParenthesisChecker {

   private ParenthesisChecker() {
   }

   public static int checkParentheses(StackInterface<Character> stack, String fromString) throws ParenthesesException {
      int count = 0;

      for (int i = 0; i < fromString.length(); i++) {
         char currentChar = fromString.charAt(i);

         if (currentChar == '(' || currentChar == '[' || currentChar == '{') {
            try {
               stack.push(currentChar);
               count++;
            } catch (Exception e) {
               throw new ParenthesesException("Stack is full. Cannot push more characters.",
                     ParenthesesException.STACK_FAILURE);
            }
         } else if (currentChar == ')' || currentChar == ']' || currentChar == '}') {
            if (stack.isEmpty()) {
               throw new ParenthesesException("There are too many closing parentheses.",
                     ParenthesesException.TOO_MANY_CLOSING_PARENTHESES);
            }
            Character popped = stack.pop();
            count++;

            if ((currentChar == ')' && popped != '(') || (currentChar == ']' && popped != '[') || (currentChar == '}' && popped != '{')) {
               throw new ParenthesesException("Wrong kind of parenthesis were in the text.",
                     ParenthesesException.PARENTHESES_IN_WRONG_ORDER);
            }
         }
      }

      if (!stack.isEmpty()) {
         throw new ParenthesesException("There are more opening than closing parentheses.",
               ParenthesesException.TOO_FEW_CLOSING_PARENTHESES);
      }

      return count;
   }
}
