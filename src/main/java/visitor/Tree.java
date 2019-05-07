package visitor;

public class Tree {

    private Node root;
    private Node current;

    public Tree() {
        root = new Node();
        current = root;
    }

    public Node get(String name) {
        Node n = current;
//        System.out.println("Ищем " + name);
        while (n != null) {
            if (n.name.equals(name)) {
                return n;
            }
            n = n.parent;
        }
        return null;
    }

    public Node put(String name, Node node) {
//        System.out.println("Задаем " + name);
        if (get(name) != null) {
            Node node1 = get(name);
            node1.text = node.text;
            return node1;
        } else {
            node.name = name;
            node.parent = current;
            current.left = node;
            current = node;
            return node;
        }
    }

    public void in() {

    }

    public void out() {

    }

    public void display() {
        root.display(0);
    }
}
