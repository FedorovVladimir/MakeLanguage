package visitor;

import antlr.CPP14Lexer;
import antlr.CPP14Parser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    private static String code = "" +
            "void main(){" +
            "cout << 23;" +
            "}";

    public static void main(String[] args) {
        CPP14Lexer lexer = new CPP14Lexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CPP14Parser parser = new CPP14Parser(tokens);
        ParseTree tree = parser.translationunit();
        new MyVisitor().visit(tree);
    }
}

