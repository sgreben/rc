package io.github.sgreben.rc;

import io.github.sgreben.rc.declarations.RuleDeclaration;
import io.github.sgreben.rc.declarations.RuleSetDeclaration;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ARuleSetDeclarationShould {

    private Context context = new Context();

    @Test
    public void
    compile_to_a_rule_set() {
        RuleSetDeclaration ruleSetDeclaration = new RuleSetDeclaration();
        ruleSetDeclaration.setName("ruleSet1");
        Map<String, String> variablesMap = new HashMap<>();
        variablesMap.put("x", "int");
        variablesMap.put("y", "real");
        variablesMap.put("z", "bool");
        ruleSetDeclaration.setVariables(variablesMap);
        ruleSetDeclaration.setRules(Collections.emptyList());

        RuleSet ruleSet = ruleSetDeclaration.compile(context);

        assertThat(ruleSet.getName(), is(equalTo("ruleSet1")));
    }

    @Test
    public void
    load_from_a_file() throws IOException {
        Map<String, String> variablesMap = new HashMap<>();
        variablesMap.put("x", "int");
        variablesMap.put("y", "real");
        variablesMap.put("z", "bool");

        List<RuleDeclaration> rules = new LinkedList<>();
        RuleDeclaration rule1 = new RuleDeclaration();
        rule1.setName("rule1");
        rule1.setWhen("x > y");
        rule1.setThen(Collections.singletonMap(
                "z", "true"
        ));
        RuleDeclaration rule2 = new RuleDeclaration();
        rule2.setWhen("x <= y");
        rule2.setThen(Collections.singletonMap(
                "z", "x = y"
        ));
        rules.add(rule1);
        rules.add(rule2);

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("ruleSet1.yaml").getFile());
        RuleSetDeclaration ruleSetDeclaration = RuleSetDeclaration.load(file);

        assertThat(ruleSetDeclaration.getVariables(), is(equalTo(variablesMap)));
        assertThat(ruleSetDeclaration.getRules(), is(equalTo(rules)));
    }
}

