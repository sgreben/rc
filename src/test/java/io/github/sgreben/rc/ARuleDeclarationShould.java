package io.github.sgreben.rc;

import io.github.sgreben.rc.declarations.RuleDeclaration;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ARuleDeclarationShould {

    private Context context = new Context();

    @Test public void
    compile_to_a_rule() {
        Variable x = context.buildExpression().variable("x").ofType(context.buildType().integer());
        Variable y = context.buildExpression().variable("y").ofType(context.buildType().integer());
        Map<String, Variable> variables = new HashMap<>();
        variables.put("x", x);
        variables.put("y", y);
        RuleDeclaration ruleDeclaration = new RuleDeclaration();
        ruleDeclaration.setName("TestRule");
        ruleDeclaration.setWhen("x > 0");
        ruleDeclaration.setThen(Collections.singletonMap(
                "y", "0"
        ));
        Rule rule = ruleDeclaration.compile(context, variables);
        assertThat(rule.getName(), is(equalTo("TestRule")));
    }

    @Test public void
    load_from_a_file() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("rule1.yaml").getFile());
        RuleDeclaration ruleDeclaration = RuleDeclaration.load(file);
        assertThat(ruleDeclaration.getName(), is(equalTo("rule1")));
        assertThat(ruleDeclaration.getWhen(), is(equalTo("y > 0 && x < 10")));
        assertThat(ruleDeclaration.getThen(), is(equalTo(Collections.singletonMap(
                "z", "123"
        ))));
    }

}
