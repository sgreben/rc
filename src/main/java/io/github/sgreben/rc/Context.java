package io.github.sgreben.rc;

import io.github.sgreben.rc.builders.*;
import io.github.sgreben.rc.types.EnumType;

public class Context {
    private final com.microsoft.z3.Context z3Context;
    private final TypeEnvironment typeEnvironment;

    public Context() {
        this(new com.microsoft.z3.Context());
    }

    public Context(com.microsoft.z3.Context z3Context) {
        this.z3Context = z3Context;
        this.typeEnvironment = new TypeEnvironment();
    }

    public EnumType enumType(String name) {
        return typeEnvironment.enumType(name);
    }

    public RuleSetBuilder buildRuleSet() {
        return new RuleSetBuilder(typeEnvironment, z3Context);
    }

    public RuleBuilder buildRule() {
        return new RuleBuilder(z3Context);
    }

    public TypeBuilder buildType() {
        return new TypeBuilder(typeEnvironment, z3Context);
    }

    public ExpressionBuilder buildExpression() {
        return new ExpressionBuilder(z3Context);
    }

    public ValueBuilder buildValue() {
        return new ValueBuilder(typeEnvironment, z3Context);
    }

    public ModuleBuilder buildModule() {
        return new ModuleBuilder(typeEnvironment, z3Context);
    }
}
