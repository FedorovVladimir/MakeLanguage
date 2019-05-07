package visitor;

import java.util.LinkedList;
import java.util.List;

public class Tree {

    private Node root;
    private Node current;

    private List<Node> stack = new LinkedList<Node>();

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
        Node n = new Node();
        current.left = n;
        n.parent = current;
        current = n;
        stack.add(current);
        Node n2 = new Node();
        current.right = n2;
        n2.parent = current;
        current = n2;
    }

    public void out() {
        current = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);
    }

    public void display() {
        root.display(0);
    }
}
