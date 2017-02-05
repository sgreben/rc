package io.github.sgreben.rc;

import io.github.sgreben.rc.builders.ModuleBuilder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CompletenessCheckFeature {
    private Context context = new Context();

    @Test
    public void
    trivial_incomplete_rule_with_zero_variables_is_not_complete() throws SolverException {
        RuleSet ruleSet = TestFixtures.trivialIncompleteRuleSetWithZeroVariables(context);
        assertThat(ruleSet.isComplete(), is(false));
    }

    @Test
    public void
    trivial_incomplete_rule_with_zero_variables_is_complete() throws SolverException {
        RuleSet ruleSet = TestFixtures.trivialCompleteRuleSetWithZeroVariables(context);
        assertThat(ruleSet.isComplete(), is(true));
    }

    @Test
    public void
    trivial_incomplete_rule_with_one_variable_is_not_complete() throws SolverException {
        RuleSet ruleSet = TestFixtures.trivialIncompleteRuleWithOneVariable(context);
        assertThat(ruleSet.isComplete(), is(false));
    }

}
