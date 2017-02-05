package io.github.sgreben.rc;

import io.github.sgreben.rc.builders.ExpressionBuilder;
import io.github.sgreben.rc.builders.RuleSetBuilder;
import io.github.sgreben.rc.expressions.Expression;
import io.github.sgreben.rc.values.EnumValue;
import io.github.sgreben.rc.values.RealValue;
import io.github.sgreben.rc.values.Value;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.assertThat;

public class ARuleSetShould {
    private Context context = new Context();

    @Test
    public void
    be_complete_when_preconditions_n_greater_0_and_n_less_than_0_and_n_equals_0_are_given() throws SolverException {
        RuleSetBuilder ruleSetBuilder = context.buildRuleSet();
        ExpressionBuilder expressionBuilder = context.buildExpression();
        Variable n = TestFixtures.getIntVariableN(expressionBuilder, context);

        Rule nGreater0 = context.buildRule()
                .withName("n>0")
                .withPrecondition(expressionBuilder.Greater(n, expressionBuilder.Zero()))
                .withPostcondition(expressionBuilder.True())
                .build();

        Rule nLessThan0 = context.buildRule()
                .withName("n<0")
                .withPrecondition(expressionBuilder.Less(n, expressionBuilder.Zero()))
                .withPostcondition(expressionBuilder.True())
                .build();

        Rule nEquals0 = context.buildRule()
                .withName("n=0")
                .withPrecondition(expressionBuilder.Equal(n, expressionBuilder.Zero()))
                .withPostcondition(expressionBuilder.True())
                .build();

        RuleSet ruleSet = ruleSetBuilder
                .withName("DummyRuleSet")
                .withRule(nGreater0)
                .withRule(nLessThan0)
                .withRule(nEquals0)
                .build();

        assertThat(ruleSet.isComplete(), is(true));
    }

    @Test
    public void
    be_incomplete_when_preconditions_n_greater_0_and_n_less_than_0_are_given() throws SolverException {
        RuleSetBuilder ruleSetBuilder = context.buildRuleSet();
        ExpressionBuilder expressionBuilder = context.buildExpression();
        Variable n = TestFixtures.getIntVariableN(expressionBuilder, context);

        Rule nGreater0 = context.buildRule()
                .withName("n>0")
                .withPrecondition(expressionBuilder.Greater(n, expressionBuilder.Zero()))
                .withPostcondition(expressionBuilder.True())
                .build();

        Rule nLessThan0 = context.buildRule()
                .withName("n<0")
                .withPrecondition(expressionBuilder.Less(n, expressionBuilder.Zero()))
                .withPostcondition(expressionBuilder.True())
                .build();
        RuleSet ruleSet = ruleSetBuilder
                .withName("DummyRuleSet")
                .withRule(nGreater0)
                .withRule(nLessThan0)
                .build();

        assertThat(ruleSet.isComplete(), is(false));
    }

    @Test
    public void
    have_the_completeness_counterexample_n_equals_0_when_the_preconditions_n_gt_0_and_n_lt_0_are_given() throws SolverException {
        RuleSetBuilder ruleSetBuilder = context.buildRuleSet();
        ExpressionBuilder expressionBuilder = context.buildExpression();
        Variable n = TestFixtures.getIntVariableN(expressionBuilder, context);

        Rule nGreater0 = context.buildRule()
                .withName("n>0")
                .withPrecondition(expressionBuilder.Greater(n, expressionBuilder.Zero()))
                .withPostcondition(expressionBuilder.True())
                .build();

        Rule nLessThan0 = context.buildRule()
                .withName("n<0")
                .withPrecondition(expressionBuilder.Less(n, expressionBuilder.Zero()))
                .withPostcondition(expressionBuilder.True())
                .build();
        RuleSet ruleSet = ruleSetBuilder
                .withName("DummyRuleSet")
                .withRule(nGreater0)
                .withRule(nLessThan0)
                .build();

        Map<Variable, Value> counterExample = ruleSet.completenessCounterExample();
        assertThat(counterExample, is(equalTo(Collections.singletonMap(n, context.buildValue().integer(0)))));
    }


    @Test
    public void
    have_the_completeness_counterexample_x_equals_FALSE_when_the_precondition_n_equals_TRUE_is_given() throws SolverException {
        RuleSetBuilder ruleSetBuilder = context.buildRuleSet();
        ExpressionBuilder expressionBuilder = context.buildExpression();
        Variable x = TestFixtures.getEnumVariableX(expressionBuilder, context);
        EnumValue TRUE = context.buildValue().enumeration("TRUE");
        EnumValue FALSE = context.buildValue().enumeration("FALSE");

        Rule xEqualsTRUE = context.buildRule()
                .withName("x=TRUE")
                .withPrecondition(expressionBuilder.Equal(x, TRUE))
                .withPostcondition(expressionBuilder.True())
                .build();

        RuleSet ruleSet = ruleSetBuilder
                .withName("DummyRuleSet")
                .withRule(xEqualsTRUE)
                .build();

        Map<Variable, Value> counterExample = ruleSet.completenessCounterExample();
        assertThat(counterExample, is(equalTo(Collections.singletonMap(x, context.buildValue().enumeration("FALSE")))));
    }

    @Test
    public void
    have_the_completeness_counterexample_b_equals_False_when_the_precondition_b_equals_True_is_given() throws SolverException {
        RuleSetBuilder ruleSetBuilder = context.buildRuleSet();
        ExpressionBuilder expressionBuilder = context.buildExpression();
        Variable b = TestFixtures.getBoolVariableB(expressionBuilder, context);

        Rule bEqualsTrue = context.buildRule()
                .withName("b=True")
                .withPrecondition(expressionBuilder.Equal(b, context.buildValue().bool(true)))
                .withPostcondition(expressionBuilder.True())
                .build();

        RuleSet ruleSet = ruleSetBuilder
                .withName("DummyRuleSet")
                .withRule(bEqualsTrue)
                .build();

        Map<Variable, Value> counterExample = ruleSet.completenessCounterExample();
        assertThat(counterExample, is(equalTo(Collections.singletonMap(b, context.buildValue().bool(false)))));
    }

    @Test
    public void
    have_the_completeness_counterexample_alpha_greater_one_half_when_the_precondition_alpha_less_than_or_equal_to_one_half_is_given() throws SolverException {
        RuleSetBuilder ruleSetBuilder = context.buildRuleSet();
        ExpressionBuilder expressionBuilder = context.buildExpression();
        Variable alpha = TestFixtures.getRealVariable("alpha", expressionBuilder, context);

        Rule alphaLeqOneHalf = context.buildRule()
                .withName("alpha <= 0.5")
                .withPrecondition(expressionBuilder.LessOrEqual(alpha, context.buildValue().real(0.5)))
                .withPostcondition(expressionBuilder.True())
                .build();

        RuleSet ruleSet = ruleSetBuilder
                .withName("DummyRuleSet")
                .withRule(alphaLeqOneHalf)
                .build();

        Map<Variable, Value> counterExample = ruleSet.completenessCounterExample();
        assertThat(counterExample.get(alpha), is(instanceOf(RealValue.class)));
        assertThat(((RealValue) counterExample.get(alpha)).getValue(), is(greaterThan(0.5)));
    }

    @Test
    public void
    satisfy_the_constraint_alpha_less_than_beta_when_rules_implying_this_are_given() throws SolverException {
        RuleSetBuilder ruleSetBuilder = context.buildRuleSet();
        ExpressionBuilder expressionBuilder = context.buildExpression();
        Variable alpha = TestFixtures.getRealVariable("alpha", expressionBuilder, context);
        Variable beta = TestFixtures.getRealVariable("beta", expressionBuilder, context);

        Rule betaLeqOneHalf = context.buildRule()
                .withName("0 <= beta <= 0.5 => alpha = beta / 2")
                .withPrecondition(expressionBuilder.LessOrEqual(beta, context.buildValue().real(0.5)))
                .withPrecondition(expressionBuilder.GreaterOrEqual(beta, context.buildValue().real(0)))
                .withPostcondition(expressionBuilder.Equal(
                        alpha,
                        expressionBuilder.Div(beta, context.buildValue().integer(2))))
                .build();
        Rule betaGtOneHalf = context.buildRule()
                .withName("beta > 0.5 => alpha = 0.5")
                .withPrecondition(expressionBuilder.Greater(beta, context.buildValue().real(0.5)))
                .withPostcondition(expressionBuilder.Equal(alpha, context.buildValue().real(0.5)))
                .build();

        RuleSet ruleSet = ruleSetBuilder
                .withName("DummyRuleSet")
                .withRule(betaLeqOneHalf)
                .withRule(betaGtOneHalf)
                .build();

        Expression constraint = expressionBuilder.LessOrEqual(alpha, beta);
        assertThat(ruleSet.satisfiesConstraint(constraint), is(true));
    }

    @Test
    public void
    not_satisfy_the_constraint_alpha_less_than_beta_when_rules_not_implying_this_are_given() throws SolverException {
        RuleSetBuilder ruleSetBuilder = context.buildRuleSet();
        ExpressionBuilder expressionBuilder = context.buildExpression();
        Variable alpha = TestFixtures.getRealVariable("alpha", expressionBuilder, context);
        Variable beta = TestFixtures.getRealVariable("beta", expressionBuilder, context);

        Rule betaLeqOneHalf = context.buildRule()
                .withName("0 <= beta <= 0.5 => alpha = beta + 1")
                .withPrecondition(expressionBuilder.LessOrEqual(beta, context.buildValue().real(0.5)))
                .withPrecondition(expressionBuilder.GreaterOrEqual(beta, context.buildValue().real(0)))
                .withPostcondition(expressionBuilder.Equal(
                        alpha,
                        expressionBuilder.Add(beta, context.buildValue().integer(1))))
                .build();
        Rule betaGtOneHalf = context.buildRule()
                .withName("beta > 0.5 => alpha = 0.5")
                .withPrecondition(expressionBuilder.Greater(beta, context.buildValue().real(0.5)))
                .withPostcondition(expressionBuilder.Equal(alpha, context.buildValue().real(0.5)))
                .build();

        RuleSet ruleSet = ruleSetBuilder
                .withName("DummyRuleSet")
                .withRule(betaLeqOneHalf)
                .withRule(betaGtOneHalf)
                .build();

        Expression constraint = expressionBuilder.LessOrEqual(alpha, beta);
        List<Map<Variable, Value>> constraintCounterExamples = ruleSet.constraintCounterExamples(constraint);
        Map<Variable, Value> counterExample = constraintCounterExamples.get(0);

        assertThat(ruleSet.satisfiesConstraint(constraint), is(false));
        assertThat(constraintCounterExamples, is(notNullValue()));
        assertThat(constraintCounterExamples.size(), is(equalTo(1)));
        assertThat(((RealValue) counterExample.get(alpha)).getValue(), is(greaterThan(((RealValue) counterExample.get(beta)).getValue())));
    }
}
