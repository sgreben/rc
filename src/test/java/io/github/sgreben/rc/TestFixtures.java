package io.github.sgreben.rc;

import io.github.sgreben.rc.builders.*;
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
