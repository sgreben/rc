package io.github.sgreben.rc;

import com.microsoft.z3.*;
import com.microsoft.z3.Context;
import io.github.sgreben.rc.expressions.Expression;
import io.github.sgreben.rc.values.BoolValue;
import io.github.sgreben.rc.values.IntegerValue;
import io.github.sgreben.rc.values.RealValue;
import io.github.sgreben.rc.values.Value;

import java.util.*;

public class RuleSet {
    private final TypeEnvironment typeEnvironment;
    private final Context z3Context;
    private final String name;
    private final List<Rule> rules;
    private final Solver solver;
    private final Set<Variable> variables;

    public RuleSet(Context z3Context, TypeEnvironment typeEnvironment, String name, List<Rule> rules) {
        this.typeEnvironment = typeEnvironment;
        this.z3Context = z3Context;
        this.name = name;
        this.rules = new ArrayList<>(rules);
        this.solver = z3Context.mkSolver();
        variables = new HashSet<>();
        for (Rule rule : rules) {
            variables.addAll(rule.variables());
        }
    }

    public String getName() {
        return name;
    }

    public boolean isComplete() throws SolverException {
        solver.push();
        assertConjunctionOfRuleNegations();
        Status status = solver.check();
        solver.pop();
        return isUnsatisfiable(status);
    }

    private void assertConjunctionOfRuleNegations() {
        for (Rule rule : rules) {
            rule.assertNegationOfPreconditions(solver);
        }
    }

    public boolean satisfiesConstraint(Expression constraint) throws SolverException {
        for (Rule rule : rules) {
            solver.push();
            rule.assertPreconditions(solver);
            rule.assertPostconditions(solver);
            solver.add(z3Context.mkNot((BoolExpr) constraint.toZ3Expr()));
            Status status = solver.check();
            solver.pop();
            if (!isUnsatisfiable(status)) {
                return false;
            }
        }
        return true;
    }

    public List<Map<Variable, Value>> constraintCounterExamples(Expression constraint) throws SolverException {
        List<Map<Variable, Value>> counterExamples = new LinkedList<>();
        for (Rule rule : rules) {
            solver.push();
            rule.assertPreconditions(solver);
            rule.assertPostconditions(solver);
            solver.add(z3Context.mkNot((BoolExpr) constraint.toZ3Expr()));
            Status status = solver.check();
            solver.pop();
            if (!isUnsatisfiable(status)) {
                counterExamples.add(getModel());
            }
        }
        return counterExamples;
    }

    private boolean isUnsatisfiable(Status status) throws SolverException {
        switch (status) {
            case SATISFIABLE:
                return false;
            case UNSATISFIABLE:
                return true;
            default:
                throw new SolverException();
        }
    }

    public Map<Variable, Value> completenessCounterExample() throws SolverException {
        solver.push();
        assertConjunctionOfRuleNegations();
        Status status = solver.check();
        solver.pop();
        switch (status) {
            case SATISFIABLE:
                return getModel();
            case UNSATISFIABLE:
                return null;
            default:
                throw new SolverException();
        }
    }

    private Map<Variable, Value> getModel() {
        HashMap<Variable, Value> result = new HashMap<>();
        Model model = solver.getModel();
        for (Variable variable : variables) {
            Expr modelValue = model.eval(z3Context.mkConst(variable.getConstDecl()), true);
            result.put(variable, translateModelValue(modelValue));
        }
        return result;
    }

    public boolean isOverlapping() throws SolverException {
        for (int i = 0; i < rules.size(); ++i) {
            for (int j = i + 1; j < rules.size(); ++j) {
                solver.push();
                rules.get(i).assertPreconditions(solver);
                rules.get(j).assertPreconditions(solver);
                Status status = solver.check();
                solver.pop();
                if (!isUnsatisfiable(status)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<RuleOverlap> overlaps() throws SolverException {
        List<RuleOverlap> overlaps = new LinkedList<>();
        for (int i = 0; i < rules.size(); ++i) {
            for (int j = i + 1; j < rules.size(); ++j) {
                solver.push();
                Rule first = rules.get(i);
                Rule second = rules.get(j);
                first.assertPreconditions(solver);
                second.assertPreconditions(solver);
                Status status = solver.check();
                solver.pop();
                if (!isUnsatisfiable(status)) {
                    Map<Variable, Value> model = getModel();
                    overlaps.add(new RuleOverlap(
                        new RuleOverlap.IndexedRule(first, i),
                        new RuleOverlap.IndexedRule(second, j),
                        model
                    ));
                }
            }
        }
        return overlaps;
    }

    private Value translateModelValue(Expr modelValue) {
        Value result;
        if (modelValue.isInt()) {
            result = new IntegerValue(z3Context, ((IntNum) modelValue).getInt());
        } else if (modelValue.isBool()) {
            result = new BoolValue(z3Context, modelValue.isTrue());
        } else if (modelValue.isConst()) {
            result = typeEnvironment.enumValueOfFuncDecl(modelValue.getFuncDecl());
        } else if (modelValue.isRatNum()) {
            RatNum ratNum = (RatNum) modelValue;
            double numerator = (double) ratNum.getNumerator().getInt64();
            double denominator = (double) ratNum.getDenominator().getInt64();
            double value = numerator / denominator;
            result = new RealValue(z3Context, value);
        } else if (modelValue.isAlgebraicNumber()) {
            result = new RealValue(z3Context, Double.valueOf(((AlgebraicNum) modelValue).toDecimal(20)));
        } else {
            throw new IllegalArgumentException("Unsupported model value type: " + modelValue.toString());
        }
        return result;
    }
}
