package visitor;


import antlr.CPP14BaseVisitor;
import antlr.CPP14Parser;

import java.util.HashMap;
import java.util.Map;

public class MyVisitor extends CPP14BaseVisitor<Node> {

    private Map<String, Node> vars = new HashMap<String, Node>();

    @Override
    public Node visitShiftexpression(CPP14Parser.ShiftexpressionContext ctx) {
        System.out.println(super.visit(ctx.getChild(2)).getText());
        return new Node(TypeLexeme.VOID);
    }

    @Override
    public Node visitLiteral(CPP14Parser.LiteralContext ctx) {
        String strConst = ctx.getChild(0).getText();
        int constant = 0;
        constant = strConst.indexOf(".");
        if (constant > 0)
            return new Node(TypeLexeme.DOUBLE, ctx.getChild(0).getText());
        else {
            return new Node(TypeLexeme.INT, ctx.getChild(0).getText());
        }
    }

    @Override
    public Node visitSimpledeclaration(CPP14Parser.SimpledeclarationContext ctx) {
        for (int i = 0; i < ctx.getChild(1).getChildCount(); i += 2) {
            String value = "0";
            String name = ctx.getChild(1).getChild(i).getChild(0).getText();
            if (ctx.getChild(1).getChild(i).getChildCount() > 1) {
                value = super.visit(ctx.getChild(1).getChild(i).getChild(2)).getText();
                System.out.println();
            }
            Node node = new Node(TypeLexeme.DOUBLE, value);
            System.out.println("node=" + node + " value=" + value);
            if (vars.get(name) == null) {
                vars.put(name, node);
            } else {
                System.out.println("Переменная " + name + " уже определена!");
            }
        }
        return new Node(TypeLexeme.VOID);
    }
}
