package io.github.sgreben.rc.declarations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.github.sgreben.rc.Context;
import io.github.sgreben.rc.RuleSet;
import io.github.sgreben.rc.Variable;
import io.github.sgreben.rc.builders.RuleSetBuilder;
import io.github.sgreben.rc.types.Type;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleSetDeclaration {
    private String name;
    private Map<String, String> variables;
    private List<RuleDeclaration> rules;

    public static RuleSetDeclaration load(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(file, RuleSetDeclaration.class);
    }

    public Map<String, String> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RuleDeclaration> getRules() {
        return rules;
    }

    public void setRules(List<RuleDeclaration> rules) {
        this.rules = rules;
    }

    public RuleSet compile(Context context) {
        Map<String, Variable> variables = new HashMap<>();
        for (Map.Entry<String, String> variableDeclaration : this.variables.entrySet()) {
            String declaredName = variableDeclaration.getKey();
            String declaredType = variableDeclaration.getValue();
            Type type = null;
            switch (declaredType) {
                case "bool":
                    type = context.buildType().bool();
                    break;
                case "int":
                    type = context.buildType().integer();
                    break;
                case "real":
                    type = context.buildType().real();
                    break;
                default:
                    type = context.enumType(declaredType);
            }
            Variable variable = context.buildExpression().variable(declaredName).ofType(type);
            variables.put(declaredName, variable);
        }
        RuleSetBuilder builder = context.buildRuleSet().withName(name);
        for (RuleDeclaration ruleDeclaration : rules) {
            builder.withRule(ruleDeclaration.compile(context, variables));
        }
        return builder.build();
    }

    @Override
    public String toString() {
        return "RuleSetDeclaration{" +
                "name='" + name + '\'' +
                ", variables=" + variables +
                ", rules=" + rules +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RuleSetDeclaration that = (RuleSetDeclaration) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (variables != null ? !variables.equals(that.variables) : that.variables != null) return false;
        return rules != null ? rules.equals(that.rules) : that.rules == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (variables != null ? variables.hashCode() : 0);
        result = 31 * result + (rules != null ? rules.hashCode() : 0);
        return result;
    }
}
