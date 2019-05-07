package visitor;

import antlr.CPP14Lexer;
import antlr.CPP14Parser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    private static String code = "" +
            "void main() {" +
            "   int a = 2 + 9;" +
            "   cout << a;" +
            "   a = 3;" +
            "   cout << a;" +
            "   g = 3;" +
            "   " +
            "   int b = 0;" +
            "   if (b < 1) {" +
            "       cout << 100 * 2;" +
            "       a = 4;" +
            "       int c = 0;" +
            "       if (c == 0) {" +
            "           c = 4;" +
            "           int d = 9;" +
            "           cout << d;" +
            "       }" +
            "       cout << c;" +
            "       cout << d;" +
            "   } else {" +
            "       cout << 200 / 3;" +
            "   }" +
            "   " +
            "   if (a == 4) {" +
            "       cout << 111;" +
            "   }" +
            "}";

    public static void main(String[] args) {
        CPP14Lexer lexer = new CPP14Lexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CPP14Parser parser = new CPP14Parser(tokens);
        ParseTree tree = parser.translationunit();
        MyVisitor myVisitor = new MyVisitor();
        myVisitor.visit(tree);
        // посдендняя строка - вывод дерева
//        myVisitor.vars.display();
    }
}

