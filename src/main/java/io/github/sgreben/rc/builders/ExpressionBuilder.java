package io.github.sgreben.rc.builders;

import com.microsoft.z3.ArithExpr;
import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import io.github.sgreben.rc.Variable;
import io.github.sgreben.rc.expressions.ArithmeticExpression;
import io.github.sgreben.rc.expressions.BooleanExpression;
import io.github.sgreben.rc.expressions.Expression;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ExpressionBuilder {
    private final Context z3Context;

    public ExpressionBuilder(Context z3Context) {
        this.z3Context = z3Context;
    }

    public BooleanExpression False() {
        return new BooleanExpression(z3Context.mkFalse(), Collections.emptySet());
    }

    public BooleanExpression True() {
        return new BooleanExpression(z3Context.mkTrue(), Collections.emptySet());
    }

    public VariableBuilder variable(String name) {
        return new VariableBuilder(z3Context, name);
    }

    public ArithmeticExpression Zero() {
        return new ArithmeticExpression(z3Context.mkInt(0), Collections.emptySet());
    }

    public ArithmeticExpression Add(Expression left, Expression right) {
        Set<Variable> variables = new HashSet<>(left.freeVariables());
        variables.addAll(right.freeVariables());
        return new ArithmeticExpression(z3Context.mkAdd((ArithExpr) left.toZ3Expr(), (ArithExpr) right.toZ3Expr()), variables);
    }

    public ArithmeticExpression Sub(Expression left, Expression right) {
        Set<Variable> variables = new HashSet<>(left.freeVariables());
        variables.addAll(right.freeVariables());
        return new ArithmeticExpression(z3Context.mkSub((ArithExpr) left.toZ3Expr(), (ArithExpr) right.toZ3Expr()), variables);
    }

    public ArithmeticExpression Mul(Expression left, Expression right) {
        Set<Variable> variables = new HashSet<>(left.freeVariables());
        variables.addAll(right.freeVariables());
        return new ArithmeticExpression(z3Context.mkMul((ArithExpr) left.toZ3Expr(), (ArithExpr) right.toZ3Expr()), variables);
    }

    public ArithmeticExpression Div(Expression left, Expression right) {
        Set<Variable> variables = new HashSet<>(left.freeVariables());
        variables.addAll(right.freeVariables());
        return new ArithmeticExpression(z3Context.mkDiv((ArithExpr) left.toZ3Expr(), (ArithExpr) right.toZ3Expr()), variables);
    }

    public BooleanExpression Equal(Expression left, Expression right) {
        Set<Variable> variables = new HashSet<>(left.freeVariables());
        variables.addAll(right.freeVariables());
        return new BooleanExpression(z3Context.mkEq(left.toZ3Expr(), right.toZ3Expr()), variables);
    }

    public BooleanExpression NotEqual(Expression left, Expression right) {
        Set<Variable> variables = new HashSet<>(left.freeVariables());
        variables.addAll(right.freeVariables());
        return new BooleanExpression(z3Context.mkNot(z3Context.mkEq(left.toZ3Expr(), right.toZ3Expr())), variables);
    }

    public BooleanExpression Greater(Expression left, Expression right) {
        Set<Variable> variables = new HashSet<>(left.freeVariables());
        variables.addAll(right.freeVariables());
        return new BooleanExpression(z3Context.mkGt((ArithExpr) left.toZ3Expr(), (ArithExpr) right.toZ3Expr()), variables);
    }

    public BooleanExpression Less(Expression left, Expression right) {
        Set<Variable> variables = new HashSet<>(left.freeVariables());
        variables.addAll(right.freeVariables());
        return new BooleanExpression(z3Context.mkLt((ArithExpr) left.toZ3Expr(), (ArithExpr) right.toZ3Expr()), variables);
    }

    public BooleanExpression LessOrEqual(Expression left, Expression right) {
        Set<Variable> variables = new HashSet<>(left.freeVariables());
        variables.addAll(right.freeVariables());
        return new BooleanExpression(z3Context.mkLe((ArithExpr) left.toZ3Expr(), (ArithExpr) right.toZ3Expr()), variables);
    }

    public BooleanExpression GreaterOrEqual(Expression left, Expression right) {
        Set<Variable> variables = new HashSet<>(left.freeVariables());
        variables.addAll(right.freeVariables());
        return new BooleanExpression(z3Context.mkGe((ArithExpr) left.toZ3Expr(), (ArithExpr) right.toZ3Expr()), variables);
    }

    public BooleanExpression Or(Expression left, Expression right) {
        Set<Variable> variables = new HashSet<>(left.freeVariables());
        variables.addAll(right.freeVariables());
        return new BooleanExpression(z3Context.mkOr((BoolExpr) left.toZ3Expr(), (BoolExpr) right.toZ3Expr()), variables);
    }

    public BooleanExpression And(Expression left, Expression right) {
        Set<Variable> variables = new HashSet<>(left.freeVariables());
        variables.addAll(right.freeVariables());
        return new BooleanExpression(z3Context.mkAnd((BoolExpr) left.toZ3Expr(), (BoolExpr) right.toZ3Expr()), variables);
    }

    public BooleanExpression Implies(Expression left, Expression right) {
        Set<Variable> variables = new HashSet<>(left.freeVariables());
        variables.addAll(right.freeVariables());
        return new BooleanExpression(z3Context.mkImplies((BoolExpr) left.toZ3Expr(), (BoolExpr) right.toZ3Expr()), variables);
    }

    public BooleanExpression Not(BooleanExpression expression) {
        return new BooleanExpression(z3Context.mkNot(expression.toZ3BoolExpr()), expression.freeVariables());
    }

    public Expression ITE(BooleanExpression condition, Expression then, Expression _else) {
        Set<Variable> variables = new HashSet<>(condition.freeVariables());
        variables.addAll(then.freeVariables());
        variables.addAll(_else.freeVariables());
        if(then instanceof ArithmeticExpression) {
            return new ArithmeticExpression((ArithExpr) z3Context.mkITE(condition.toZ3BoolExpr(), then.toZ3Expr(), _else.toZ3Expr()), variables);
        } else {
            return new BooleanExpression((BoolExpr) z3Context.mkITE(condition.toZ3BoolExpr(), then.toZ3Expr(), _else.toZ3Expr()), variables);
        }
    }

    public ArithmeticExpression Power(Expression left, Expression right) {
        Set<Variable> variables = new HashSet<>(left.freeVariables());
        variables.addAll(right.freeVariables());
        return new ArithmeticExpression(z3Context.mkPower((ArithExpr) left.toZ3Expr(), (ArithExpr) right.toZ3Expr()), variables);
    }
}
