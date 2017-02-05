package io.github.sgreben.rc;

import io.github.sgreben.rc.values.Value;

import java.util.Map;

public class RuleOverlap {
    private final IndexedRule first;
    private final IndexedRule second;
    private final Map<Variable, Value> example;

    public RuleOverlap(IndexedRule first, IndexedRule second, Map<Variable, Value> example) {
        this.first = first;
        this.second = second;
        this.example = example;
    }

    public static class IndexedRule {
        public final Rule rule;
        public final int index;

        public IndexedRule(Rule rule, int index) {
            this.rule = rule;
            this.index = index;
        }
    }

    public IndexedRule getFirst() {
        return first;
    }

    public IndexedRule getSecond() {
        return second;
    }

    public Map<Variable, Value> getExample() {
        return example;
    }
}
