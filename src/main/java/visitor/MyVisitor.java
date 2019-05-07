package visitor;


import antlr.CPP14BaseVisitor;
import antlr.CPP14Parser;

import java.util.HashMap;
import java.util.Map;

public class MyVisitor extends CPP14BaseVisitor<Node> {

//    private Map<String, Node> vars = new HashMap<String, Node>();
    private Tree vars = new Tree();
    private String type;

    @Override
    public Node visitShiftexpression(CPP14Parser.ShiftexpressionContext ctx) {
        if (ctx.getChildCount() > 1) {
            System.out.println(super.visit(ctx.getChild(2)).getText());
        }
        return super.visitShiftexpression(ctx);
    }

    @Override
    public Node visitLiteral(CPP14Parser.LiteralContext ctx) {
        String strConst = ctx.getChild(0).getText();
        int constant = strConst.indexOf(".");
        if (constant > 0)
            return new Node(TypeLexeme.DOUBLE, ctx.getChild(0).getText());
        else {
            return new Node(TypeLexeme.INT, ctx.getChild(0).getText());
        }
    }

    @Override
    public Node visitSimpledeclaration(CPP14Parser.SimpledeclarationContext ctx) {
        type = ctx.getChild(0).getText();
        return super.visitSimpledeclaration(ctx);
    }

    @Override
    public Node visitInitdeclarator(CPP14Parser.InitdeclaratorContext ctx) {
        String value = "0";
        String name = ctx.getChild(0).getText();
        if (ctx.getChildCount() > 1) {
            Node node = super.visit(ctx.getChild(1));
            value = node.getText();
        }
        TypeLexeme typeLexeme;
        if (type.equals("int")) {
            typeLexeme = TypeLexeme.INT;
        } else {
            typeLexeme = TypeLexeme.DOUBLE;
        }
        Node node = new Node(typeLexeme, value);
        if (vars.get(name) == null) {
            vars.put(name, node);
        } else {
            System.out.println("Переменная " + name + " уже определена!");
        }
        return super.visitInitdeclarator(ctx);
    }

    @Override
    public Node visitSelectionstatement(CPP14Parser.SelectionstatementContext ctx) {
        try {
            if (Math.abs(Double.parseDouble(super.visit(ctx.getChild(2)).getText())) > 0.001) {
                return super.visit(ctx.getChild(4));
            } else {
                if (ctx.getChildCount() > 6) {
                    return super.visit(ctx.getChild(6));
                } else {
                    return new Node(TypeLexeme.VOID);
                }
            }
        } catch (Exception e) {
            System.out.println("Выражение в if не корректно!");
            System.exit(1);
        }
        return new Node(TypeLexeme.VOID);
    }


    @Override
    public Node visitIdexpression(CPP14Parser.IdexpressionContext ctx) {
        if (vars.get(ctx.getText()) != null) {
            return vars.get(ctx.getText());
        }
        return super.visitIdexpression(ctx);
    }

    @Override
    public Node visitAdditiveexpression(CPP14Parser.AdditiveexpressionContext ctx) {
        if (ctx.getChildCount() == 1) {
            return super.visit(ctx.getChild(0));
        } else {
            Node a = super.visit(ctx.getChild(0));
            Node b = super.visit(ctx.getChild(2));
            if (ctx.getChild(1).getText().equals("+")) {
                return a.add(b);
            } else {
                return a.sub(b);
            }
        }
    }

    @Override
    public Node visitMultiplicativeexpression(CPP14Parser.MultiplicativeexpressionContext ctx) {
        if (ctx.getChildCount() == 1) {
            return super.visit(ctx.getChild(0));
        } else {
            Node a = super.visit(ctx.getChild(0));
            Node b = super.visit(ctx.getChild(2));
            if (ctx.getChild(1).getText().equals("*")) {
                return a.mul(b);
            } else {
                return a.div(b);
            }
        }
    }

    @Override
    public Node visitRelationalexpression(CPP14Parser.RelationalexpressionContext ctx) {
        Node trueNode = new Node(TypeLexeme.INT, "1");
        Node falseNode = new Node(TypeLexeme.INT, "0");
        if (ctx.getChildCount() > 1) {
            if (ctx.getChild(1).getText().equals("<=")) {
                if (super.visit(ctx.getChild(0)).lessEqual(super.visit(ctx.getChild(2)))) {
                    return trueNode;
                } else {
                    return falseNode;
                }
            } else if (ctx.getChild(1).getText().equals(">=")) {
                if (super.visit(ctx.getChild(0)).largerEqual(super.visit(ctx.getChild(2)))) {
                    return trueNode;
                } else {
                    return falseNode;
                }
            } else if (ctx.getChild(1).getText().equals(">")) {
                if (super.visit(ctx.getChild(0)).larger(super.visit(ctx.getChild(2)))) {
                    return trueNode;
                } else {
                    return falseNode;
                }
            } else if (ctx.getChild(1).getText().equals("<")) {
                if (super.visit(ctx.getChild(0)).less(super.visit(ctx.getChild(2)))) {
                    return trueNode;
                } else {
                    return falseNode;
                }
            }
        }
        return super.visitRelationalexpression(ctx);
    }

    @Override
    public Node visitEqualityexpression(CPP14Parser.EqualityexpressionContext ctx) {
        Node trueNode = new Node(TypeLexeme.INT, "1");
        Node falseNode = new Node(TypeLexeme.INT, "0");
        if (ctx.getChildCount() > 1) {
            if (ctx.getChild(1).getText().equals("==")) {
                if (super.visit(ctx.getChild(0)).equal(super.visit(ctx.getChild(2)))) {
                    return trueNode;
                } else {
                    return falseNode;
                }
            } else if (ctx.getChild(1).getText().equals("!=")) {
                if (super.visit(ctx.getChild(0)).notEqual(super.visit(ctx.getChild(2)))) {
                    return trueNode;
                } else {
                    return falseNode;
                }
            }
        }
        return super.visitEqualityexpression(ctx);
    }

    @Override
    public Node visitAssignmentexpression(CPP14Parser.AssignmentexpressionContext ctx) {
        if (ctx.getChildCount() > 1) {
            String name = ctx.getChild(0).getText();
            Node node = super.visit(ctx.getChild(2));
            if (vars.get(name) != null) {
                vars.put(name, node);
            } else {
                System.out.println("Переменная " + name + " не определена!");
            }
            return node;
        } else {
            return super.visitAssignmentexpression(ctx);
        }
    }
}
