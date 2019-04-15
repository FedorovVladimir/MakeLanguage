package visitor;

public class Node {

    private TypeLexeme typeLexeme;
    private String text;

    public Node(TypeLexeme typeLexeme) {
        this.typeLexeme = typeLexeme;
    }

    public Node(TypeLexeme type, String text) {
        this.typeLexeme = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Node " + typeLexeme + " " + text;
    }

    public String getText() {
        return text;
    }

    public Node add(Node node) {
        double a = Double.parseDouble(text);
        double b = Double.parseDouble(node.getText());
        TypeLexeme typeLexeme = getType(node.typeLexeme);
        return new Node(typeLexeme, String.valueOf(a + b));
    }

    private TypeLexeme getType(TypeLexeme type) {
        TypeLexeme typeLexeme;
        if (this.typeLexeme == TypeLexeme.DOUBLE || type == TypeLexeme.DOUBLE) {
            typeLexeme = TypeLexeme.DOUBLE;
        } else {
            typeLexeme = TypeLexeme.INT;
        }
        return typeLexeme;
    }

    public Node sub(Node node) {
        double a = Double.parseDouble(text);
        double b = Double.parseDouble(node.getText());
        TypeLexeme typeLexeme = getType(node.typeLexeme);
        return new Node(typeLexeme, String.valueOf(a - b));
    }

    public Node mul(Node node) {
        double a = Double.parseDouble(text);
        double b = Double.parseDouble(node.getText());
        TypeLexeme typeLexeme = getType(node.typeLexeme);
        return new Node(typeLexeme, String.valueOf(a * b));
    }

    public Node div(Node node) {
        double a = Double.parseDouble(text);
        double b = Double.parseDouble(node.getText());
        TypeLexeme typeLexeme = getType(node.typeLexeme);
        return new Node(typeLexeme, String.valueOf(a / b));
    }

    public boolean lessEqual(Node node) {
        double a = Double.parseDouble(text);
        double b = Double.parseDouble(node.getText());
        return a <= b;
    }

    public boolean largerEqual(Node node) {
        double a = Double.parseDouble(text);
        double b = Double.parseDouble(node.getText());
        return a >= b;
    }

    public boolean larger(Node node) {
        double a = Double.parseDouble(text);
        double b = Double.parseDouble(node.getText());
        return a > b;
    }

    public boolean less(Node node) {
        double a = Double.parseDouble(text);
        double b = Double.parseDouble(node.getText());
        return a < b;
    }

    public boolean equal(Node node) {
        double a = Double.parseDouble(text);
        double b = Double.parseDouble(node.getText());
        return a == b;
    }

    public boolean notEqual(Node node) {
        double a = Double.parseDouble(text);
        double b = Double.parseDouble(node.getText());
        return a != b;
    }

    public TypeLexeme getType() {
        return typeLexeme;
    }
}