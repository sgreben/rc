package io.github.sgreben.rc;

import com.microsoft.z3.Context;

import java.util.Iterator;
import java.util.List;

public class Module implements Iterable<RuleSet> {
    private final String name;
    private final List<RuleSet> ruleSets;

    public Module(String name, List<RuleSet> ruleSets) {
        this.name = name;
        this.ruleSets = ruleSets;
    }

    public RuleSet ruleSet(String name) {
        for (RuleSet ruleSet : ruleSets) {
            if (ruleSet.getName().equals(name)) {
                return ruleSet;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    @Override
    public Iterator<RuleSet> iterator() {
        return ruleSets.iterator();
    }
}
