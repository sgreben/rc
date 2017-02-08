package io.github.sgreben.rc;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Solver;
import io.github.sgreben.rc.expressions.BooleanExpression;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rule {
    private final Context z3Context;
    private final String name;
    private final List<BooleanExpression> preconditions;
    private final List<BooleanExpression> postconditions;
    private final Set<Variable> variables;
    private String sourceString;

    public Rule(Context z3Context, String name, List<BooleanExpression> preconditions, List<BooleanExpression> postconditions, String sourceString) {
        this.z3Context = z3Context;
        this.name = name;
        this.preconditions = preconditions;
        this.postconditions = postconditions;
        this.sourceString = sourceString;
        this.variables = new HashSet<>();
        for (BooleanExpression precondition : this.preconditions) {
            variables.addAll(precondition.freeVariables());
        }
        for (BooleanExpression postcondition : this.postconditions) {
            variables.addAll(postcondition.freeVariables());
        }
    }

    public String getSourceString() {
        return sourceString;
    }

    public void assertNegationOfPreconditions(Solver solver) {
        BoolExpr[] assertions = new BoolExpr[preconditions.size()];
        int i = 0;
        for (BooleanExpression precondition : preconditions) {
            assertions[i++] = precondition.toZ3BoolExpr();
        }
        solver.add(z3Context.mkNot(z3Context.mkAnd(assertions)));
    }

    public void assertPreconditions(Solver solver) {
        for (BooleanExpression precondition : preconditions) {
            solver.add(precondition.toZ3BoolExpr());
        }
    }

    public void assertPostconditions(Solver solver) {
        for (BooleanExpression postcondition : postconditions) {
            solver.add(postcondition.toZ3BoolExpr());
        }
    }

    public Set<Variable> variables() {
        return variables;
    }

    public String getName() {
        return name;
    }
}
