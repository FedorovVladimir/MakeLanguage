package visitor;


import antlr.CPP14BaseVisitor;
import antlr.CPP14Parser;

public class MyVisitor extends CPP14BaseVisitor<Node> {

    @Override
    public Node visitShiftexpression(CPP14Parser.ShiftexpressionContext ctx) {
        System.out.println(super.visit(ctx.getChild(2)));
        return new Node(TypeLexeme.VOID);
    }
}
