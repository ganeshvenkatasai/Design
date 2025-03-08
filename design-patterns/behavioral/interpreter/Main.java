

interface Expression {
    int interpret();
}

class NumberExpression implements Expression {

    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret() {
        return number;
    }
}

class AdditionExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public AdditionExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int interpret(){
        return leftExpression.interpret() + rightExpression.interpret();
    }
}

class SubtractionExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public SubtractionExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int interpret(){
        return leftExpression.interpret() - rightExpression.interpret();
    }
}

class MultiplicatonExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public MultiplicatonExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int interpret(){
        return leftExpression.interpret() * rightExpression.interpret();
    }
}

public class Main {
    public static void main(String[] args) {
        Expression additionExpression = new AdditionExpression(new NumberExpression(10), new NumberExpression(20));
        Expression subtractionExpression = new SubtractionExpression(additionExpression, new NumberExpression(5));
        Expression multiplicationExpression= new MultiplicatonExpression(subtractionExpression, new NumberExpression(2));
        int result = multiplicationExpression.interpret();
        System.out.println(result);
    }
}
