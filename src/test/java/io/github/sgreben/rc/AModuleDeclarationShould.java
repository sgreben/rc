package io.github.sgreben.rc;

import com.sun.org.apache.xpath.internal.operations.Mod;
import io.github.sgreben.rc.declarations.ModuleDeclaration;
import io.github.sgreben.rc.declarations.RuleDeclaration;
import io.github.sgreben.rc.declarations.RuleSetDeclaration;
import io.github.sgreben.rc.declarations.TypeDeclaration;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AModuleDeclarationShould {

    Context context = new Context();

    @Test public void
    compile_to_a_module() {
        ModuleDeclaration moduleDeclaration = new ModuleDeclaration();
        moduleDeclaration.setName("module1");
        Map<String, TypeDeclaration> typeDeclarations = new HashMap<>();

        TypeDeclaration typeDeclaration = new TypeDeclaration();
        typeDeclaration.setValues(Arrays.asList(
                "DUMMY1",
                "DUMMY2"
        ));
        typeDeclarations.put("Dummy", typeDeclaration);
        moduleDeclaration.setTypes(typeDeclarations);
        List<RuleSetDeclaration> ruleSets = new LinkedList<>();
        RuleSetDeclaration ruleSet = new RuleSetDeclaration();
        RuleDeclaration rule1 = new RuleDeclaration();
        rule1.setName("rule1");
        rule1.setWhen("x + y < z && dummy = DUMMY1");
        moduleDeclaration.setRuleSets(ruleSets);
        moduleDeclaration.compile(context);
    }

    @Test public void
    load_from_a_file() throws IOException {
        ModuleDeclaration expectedModuleDeclaration = new ModuleDeclaration();
        expectedModuleDeclaration.setName("module1");
        Map<String, TypeDeclaration> typeDeclarations = new HashMap<>();

        TypeDeclaration typeDeclaration = new TypeDeclaration();
        typeDeclaration.setValues(Arrays.asList(
                "DUMMY1",
                "DUMMY2"
        ));
        typeDeclarations.put("Dummy", typeDeclaration);
        expectedModuleDeclaration.setTypes(typeDeclarations);
        List<RuleSetDeclaration> ruleSets = new LinkedList<>();
        RuleSetDeclaration ruleSet = new RuleSetDeclaration();
        Map<String, String> variables = new HashMap<>();
        variables.put("x", "int");
        variables.put("y", "real");
        variables.put("z", "bool");
        variables.put("dummy", "Dummy");
        ruleSet.setVariables(variables);
        ruleSet.setName("ruleSet1");
        RuleDeclaration rule1 = new RuleDeclaration();
        rule1.setWhen("x < y && z");
        rule1.setThen(Collections.singletonMap(
                "dummy", "DUMMY1"
        ));
        ruleSet.setRules(Collections.singletonList(rule1));
        expectedModuleDeclaration.setRuleSets(Collections.singletonList(ruleSet));

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("module1.yaml").getFile());
        ModuleDeclaration moduleDeclaration = ModuleDeclaration.load(file);

        assertThat(moduleDeclaration, is(equalTo(expectedModuleDeclaration)));
    }
}
