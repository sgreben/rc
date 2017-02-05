package io.github.sgreben.rc.declarations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.github.sgreben.rc.Context;
import io.github.sgreben.rc.Module;
import io.github.sgreben.rc.RuleSet;
import io.github.sgreben.rc.TypeEnvironment;
import io.github.sgreben.rc.builders.ModuleBuilder;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ModuleDeclaration {
    private String name;
    private Map<String, TypeDeclaration> types;
    private List<RuleSetDeclaration> ruleSets;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, TypeDeclaration> getTypes() {
        return types;
    }

    public void setTypes(Map<String, TypeDeclaration> types) {
        this.types = types;
    }

    public List<RuleSetDeclaration> getRuleSets() {
        return ruleSets;
    }

    public void setRuleSets(List<RuleSetDeclaration> ruleSets) {
        this.ruleSets = ruleSets;
    }

    public Module compile(Context context) {
        TypeEnvironment typeEnvironment = new TypeEnvironment();
        for (Map.Entry<String, TypeDeclaration> type: types.entrySet()) {
            String typeName = type.getKey();
            TypeDeclaration typeDeclaration = type.getValue();
            typeEnvironment.register(typeDeclaration.compile(context, typeName));
        }
        ModuleBuilder moduleBuilder = context.buildModule().withName(name);
        for (RuleSetDeclaration ruleSetDeclaration: ruleSets) {
            moduleBuilder.withRuleSet(ruleSetDeclaration.compile(context));
        }
        return moduleBuilder.build();
    }

    public static ModuleDeclaration load(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(file, ModuleDeclaration.class);
    }

    @Override
    public String toString() {
        return "ModuleDeclaration{" +
                "name='" + name + '\'' +
                ", types=" + types +
                ", ruleSets=" + ruleSets +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModuleDeclaration that = (ModuleDeclaration) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (types != null ? !types.equals(that.types) : that.types != null) return false;
        return ruleSets != null ? ruleSets.equals(that.ruleSets) : that.ruleSets == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (types != null ? types.hashCode() : 0);
        result = 31 * result + (ruleSets != null ? ruleSets.hashCode() : 0);
        return result;
    }
}
