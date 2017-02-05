package io.github.sgreben.rc.declarations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.github.sgreben.rc.Context;
import io.github.sgreben.rc.Rule;
import io.github.sgreben.rc.Variable;
import io.github.sgreben.rc.builders.RuleBuilder;
import io.github.sgreben.rc.expressions.BooleanExpression;
import io.github.sgreben.rc.expressions.Expression;
import io.github.sgreben.rc.parser.ExpressionParser;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RuleDeclaration {
    private String name;
    private String when;
    private Map<String, String> then;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public Map<String, String> getThen() {
        return then;
    }

    public void setThen(Map<String, String> then) {
        this.then = then;
    }

    public Rule compile(Context context, Map<String, Variable> variables) {
        RuleBuilder ruleBuilder = context.buildRule().withName(name);
        ExpressionParser parser = new ExpressionParser(context, variables);
        ruleBuilder.withPrecondition((BooleanExpression) parser.parse(when));
        for (Map.Entry<String, String> postcondition : then.entrySet()) {
            Expression expression = parser.parse(postcondition.getValue());
            BooleanExpression booleanExpression =
                    context.buildExpression().Equal(variables.get(postcondition.getKey()), expression);
            ruleBuilder.withPostcondition(booleanExpression);
        }
        return ruleBuilder.build();
    }

    public static RuleDeclaration load(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(file, RuleDeclaration.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RuleDeclaration that = (RuleDeclaration) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (when != null ? !when.equals(that.when) : that.when != null) return false;
        return then != null ? then.equals(that.then) : that.then == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (when != null ? when.hashCode() : 0);
        result = 31 * result + (then != null ? then.hashCode() : 0);
        return result;
    }
}
