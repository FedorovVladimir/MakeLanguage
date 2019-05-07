package visitor;

public class Node {

    private TypeLexeme typeLexeme;
    public String text;
    public String name = "";

    public Node parent = null;
    public Node left = null;
    public Node right = null;

    public Node() {
        typeLexeme = TypeLexeme.EMPTY;
    }

    public Node(TypeLexeme typeLexeme) {
        this.typeLexeme = typeLexeme;
    }

    public Node(TypeLexeme type, String text) {
        this.typeLexeme = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Node{" +
                "typeLexeme=" + typeLexeme +
                ", text='" + text + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getText() {
        if (typeLexeme == TypeLexeme.INT) {
            return String.valueOf((int)Double.parseDouble(text));
        }
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
        TypeLexeme typeLexeme = TypeLexeme.DOUBLE;
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

    public void display(int n) {
        if (right != null) {
            right.display(n + 1);
        }
        for (int i = 0; i < n; i++) {
            System.out.print("\t");
        }
        System.out.println(toString());
        if (left != null) {
            left.display(n);
        }
    }
}