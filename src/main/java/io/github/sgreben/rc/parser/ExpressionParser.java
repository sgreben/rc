package io.github.sgreben.rc.parser;

import io.github.sgreben.rc.Context;
import io.github.sgreben.rc.Variable;
import io.github.sgreben.rc.expressions.BooleanExpression;
import io.github.sgreben.rc.expressions.Expression;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Map;

public class ExpressionParser {
    private final Map<String, Variable> variables;
    private Context context;

    public ExpressionParser(Context context, Map<String, Variable> variables) {
        this.context = context;
        this.variables = variables;
    }

    public Expression parse(String exprString) {
        return parse(new ANTLRInputStream(exprString));
    }

    private Expression parse(ANTLRInputStream input) {
        ConstraintLexer lexer = new ConstraintLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ConstraintParser parser = new ConstraintParser(tokens);
        ParseTree parseTree = parser.expr();
        ExprBuilderVisitor visitor = new ExprBuilderVisitor();
        return visitor.visit(parseTree);
    }


    private class ExprBuilderVisitor extends ConstraintBaseVisitor<Expression> {

        @Override
        public Expression visitIntExpr(ConstraintParser.IntExprContext ctx) {
            return context.buildValue().integer(Integer.valueOf(ctx.INT().getText()));
        }

        @Override
        public Expression visitGtExpr(ConstraintParser.GtExprContext ctx) {
            Expression left = ctx.expr().get(0).accept(this);
            Expression right = ctx.expr().get(1).accept(this);
            return context.buildExpression().Greater(left, right);
        }

        @Override
        public Expression visitTrueExpr(ConstraintParser.TrueExprContext ctx) {
            return context.buildExpression().True();
        }

        @Override
        public Expression visitOrExpr(ConstraintParser.OrExprContext ctx) {
            Expression left = ctx.expr().get(0).accept(this);
            Expression right = ctx.expr().get(1).accept(this);
            return context.buildExpression().Or(left, right);
        }

        @Override
        public Expression visitParensExpr(ConstraintParser.ParensExprContext ctx) {
            return ctx.expr().accept(this);
        }

        @Override
        public Expression visitSubExpr(ConstraintParser.SubExprContext ctx) {
            Expression left = ctx.expr().get(0).accept(this);
            Expression right = ctx.expr().get(1).accept(this);
            return context.buildExpression().Sub(left, right);
        }

        @Override
        public Expression visitFloatExpr(ConstraintParser.FloatExprContext ctx) {
            return context.buildValue().real(Double.valueOf(ctx.FLOAT().getText()));
        }

        @Override
        public Expression visitEqExpr(ConstraintParser.EqExprContext ctx) {
            Expression left = ctx.expr().get(0).accept(this);
            Expression right = ctx.expr().get(1).accept(this);
            return context.buildExpression().Equal(left, right);
        }

        @Override
        public Expression visitLtExpr(ConstraintParser.LtExprContext ctx) {
            Expression left = ctx.expr().get(0).accept(this);
            Expression right = ctx.expr().get(1).accept(this);
            return context.buildExpression().Less(left, right);
        }

        @Override
        public Expression visitNotExpr(ConstraintParser.NotExprContext ctx) {
            return context.buildExpression().Not((BooleanExpression) ctx.expr().accept(this));
        }

        @Override
        public Expression visitAddExpr(ConstraintParser.AddExprContext ctx) {
            Expression left = ctx.expr().get(0).accept(this);
            Expression right = ctx.expr().get(1).accept(this);
            return context.buildExpression().Add(left, right);
        }

        @Override
        public Expression visitMulExpr(ConstraintParser.MulExprContext ctx) {
            Expression left = ctx.expr().get(0).accept(this);
            Expression right = ctx.expr().get(1).accept(this);
            return context.buildExpression().Mul(left, right);
        }

        @Override
        public Expression visitLeqExpr(ConstraintParser.LeqExprContext ctx) {
            Expression left = ctx.expr().get(0).accept(this);
            Expression right = ctx.expr().get(1).accept(this);
            return context.buildExpression().LessOrEqual(left, right);
        }

        @Override
        public Expression visitDivExpr(ConstraintParser.DivExprContext ctx) {
            Expression left = ctx.expr().get(0).accept(this);
            Expression right = ctx.expr().get(1).accept(this);
            return context.buildExpression().Div(left, right);
        }

        @Override
        public Expression visitFalseExpr(ConstraintParser.FalseExprContext ctx) {
            return context.buildExpression().False();
        }

        @Override
        public Expression visitPowExpr(ConstraintParser.PowExprContext ctx) {
            Expression left = ctx.expr().get(0).accept(this);
            Expression right = ctx.expr().get(1).accept(this);
            return context.buildExpression().Power(left, right);
        }

        @Override
        public Expression visitGeqExpr(ConstraintParser.GeqExprContext ctx) {
            Expression left = ctx.expr().get(0).accept(this);
            Expression right = ctx.expr().get(1).accept(this);
            return context.buildExpression().GreaterOrEqual(left, right);
        }

        @Override
        public Expression visitIdExpr(ConstraintParser.IdExprContext ctx) {
            String id = ctx.ID().getText();
            if (context.buildValue().enumeration(id) != null) {
                return context.buildValue().enumeration(id);
            }
            if (variables.containsKey(id)) {
                return variables.get(id);
            }
            throw new IllegalArgumentException("Unknown identifier " + id + " at position " + ctx.start.getCharPositionInLine());
        }

        @Override
        public Expression visitIteExpr(ConstraintParser.IteExprContext ctx) {
            BooleanExpression condition = (BooleanExpression) ctx.expr().get(0).accept(this);
            Expression then = ctx.expr().get(1).accept(this);
            Expression _else = ctx.expr().get(2).accept(this);
            return context.buildExpression().ITE(condition, then, _else);
        }

        @Override
        public Expression visitAndExpr(ConstraintParser.AndExprContext ctx) {
            Expression left = ctx.expr().get(0).accept(this);
            Expression right = ctx.expr().get(1).accept(this);
            return context.buildExpression().And(left, right);
        }
    }
}
