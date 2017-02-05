package io.github.sgreben.rc;

import io.github.sgreben.rc.builders.ExpressionBuilder;
import io.github.sgreben.rc.builders.RuleBuilder;
import io.github.sgreben.rc.builders.RuleSetBuilder;
import io.github.sgreben.rc.builders.TypeBuilder;
import io.github.sgreben.rc.types.BoolType;
import io.github.sgreben.rc.types.EnumType;
import io.github.sgreben.rc.types.IntType;
import io.github.sgreben.rc.types.RealType;

public class TestFixtures {
    static RuleSet trivialIncompleteRuleWithOneVariable(Context context) {
        RuleSetBuilder ruleSetBuilder = context.buildRuleSet();
        RuleBuilder ruleBuilder = context.buildRule();
        ExpressionBuilder expressionBuilder = context.buildExpression();

        Variable n = getIntVariableN(expressionBuilder, context);

        Rule trivialIncompleteRule = ruleBuilder
                .withName("trivialRule")
                .withPrecondition(expressionBuilder.False())
                .withPostcondition(expressionBuilder.Equal(n, expressionBuilder.Zero()))
                .build();

        return ruleSetBuilder
                .withName("DummyRuleSetWithOneVariable")
                .withRule(trivialIncompleteRule)
                .build();
    }

    static Variable getEnumVariableX(ExpressionBuilder expressionBuilder, Context context) {
        EnumType enumType = getDummyEnumType(context);
        return expressionBuilder
                .variable("x")
                .ofType(enumType);
    }

    static Variable getIntVariableN(ExpressionBuilder expressionBuilder, Context context) {
        TypeBuilder typeBuilder = context.buildType();

        IntType intType = typeBuilder.integer();
        return expressionBuilder
                .variable("n")
                .ofType(intType);
    }


    static EnumType getDummyEnumType(Context context) {
        TypeBuilder typeBuilder = context.buildType();

        return typeBuilder
                .enumeration()
                .withName("Boolean")
                .withValue("TRUE")
                .withValue("FALSE")
                .build();
    }


    static RuleSet trivialIncompleteRuleSetWithZeroVariables(Context context) {
        RuleSetBuilder ruleSetBuilder = context.buildRuleSet();
        RuleBuilder ruleBuilder = context.buildRule();
        ExpressionBuilder expressionBuilder = context.buildExpression();

        Rule trivialIncompleteRule = ruleBuilder
                .withName("trivialRule")
                .withPrecondition(expressionBuilder.False())
                .withPostcondition(expressionBuilder.True())
                .build();

        RuleSet ruleSet = ruleSetBuilder
                .withName("DummyRuleSet")
                .withRule(trivialIncompleteRule)
                .build();

        return ruleSet;
    }


    static RuleSet trivialCompleteRuleSetWithZeroVariables(Context context) {
        RuleSetBuilder ruleSetBuilder = context.buildRuleSet();
        RuleBuilder ruleBuilder = context.buildRule();
        ExpressionBuilder expressionBuilder = context.buildExpression();

        Rule trivialCompleteRule = ruleBuilder
                .withName("trivialRule")
                .withPrecondition(expressionBuilder.True())
                .withPostcondition(expressionBuilder.True())
                .build();

        RuleSet ruleSet = ruleSetBuilder
                .withName("DummyRuleSet")
                .withRule(trivialCompleteRule)
                .build();

        return ruleSet;
    }

    static RuleSet readMeRuleSet(Context context) {
        EnumType stateType = context.buildType().enumeration()
                .withName("STATE")
                .withValue("ON")
                .withValue("OFF")
                .withValue("STANDBY")
                .build();
        Variable temperature = context.buildExpression()
                .variable("temperature")
                .ofType(context.buildType().integer());
        Variable temperatureGoal = context.buildExpression()
                .variable("temperatureGoal")
                .ofType(context.buildType().integer());
        Variable brightness = context.buildExpression()
                .variable("brightness")
                .ofType(context.buildType().real());
        Variable motion = context.buildExpression()
                .variable("motion")
                .ofType(context.buildType().real());
        Variable state = context.buildExpression()
                .variable("state")
                .ofType(stateType);
        Variable stateOut = context.buildExpression()
                .variable("stateOut")
                .ofType(stateType);

        ExpressionBuilder eb = context.buildExpression();

        // When `temperature > 23 && (motion < 0.3 || brightness < 0.1)`
        // Then `stateOut = OFF`
        Rule rule1 = context.buildRule()
                .withName("rule 1")
                .withPrecondition(eb.And(
                        eb.Greater(temperature, context.buildValue().integer(23)),
                        eb.Or(
                                eb.Less(motion, context.buildValue().real(0.3)),
                                eb.Less(brightness, context.buildValue().real(0.1))
                        )
                ))
                .withPostcondition(eb.Equal(
                        stateOut, context.buildValue().enumeration("OFF"))
                )
                .build();

        // When `temperature < temperatureGoal && motion >= 0.3`
        // Then `stateOut = ON`
        Rule rule2 = context.buildRule()
                .withName("rule 2")
                .withPrecondition(eb.And(
                        eb.Less(temperature, temperatureGoal),
                        eb.GreaterOrEqual(motion, context.buildValue().real(0.3))
                ))
                .withPostcondition(eb.Equal(
                        stateOut, context.buildValue().enumeration("ON"))
                )
                .build();

        // When `temperature >= temperatureGoal && motion < 0.1`
        // Then `stateOut = state`
        Rule rule3 = context.buildRule()
                .withName("rule 3")
                .withPrecondition(eb.And(
                        eb.GreaterOrEqual(temperature, temperatureGoal),
                        eb.Less(motion, context.buildValue().real(0.1))
                ))
                .withPostcondition(eb.Equal(stateOut, state))
                .build();

        // When `temperature >= temperatureGoal && motion > 0.1`
        // Then `stateOut = STANDBY`
        Rule rule4 = context.buildRule()
                .withName("rule 4")
                .withPrecondition(eb.And(
                        eb.GreaterOrEqual(temperature, temperatureGoal),
                        eb.Greater(motion, context.buildValue().real(0.1))
                ))
                .withPostcondition(eb.Equal(stateOut, state))
                .build();

        return context.buildRuleSet()
                .withName("my rule set")
                .withRule(rule1)
                .withRule(rule2)
                .withRule(rule3)
                .withRule(rule4)
                .build();
    }

    public static Variable getBoolVariableB(ExpressionBuilder expressionBuilder, Context context) {
        BoolType boolType = context.buildType().bool();
        return expressionBuilder
                .variable("b")
                .ofType(boolType);

    }

    public static Variable getRealVariable(String name, ExpressionBuilder expressionBuilder, Context context) {
        RealType realType = context.buildType().real();
        return expressionBuilder
                .variable(name)
                .ofType(realType);
    }
}
