package rule;

import java.util.ArrayList;

public class Rules {
    private final ArrayList<Rule> rules;
    private final ArrayList<String> resultId;
    private int current;

    public Rules() {
        this.rules = new ArrayList<>();
        this.resultId = new ArrayList<>();
        this.current = 0;
    }

    public int getMaxRule() {
        return rules.size();
    }

    public String getCurrentRule() {
        return rules.get(current).rule();
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public void addRule(ArrayList<Rule> rules) {
        this.rules.addAll(rules);
    }

    public void check(boolean status) {
        if (status) {
            String id = rules.get(current).id();
            resultId.add(id);
        }
    }

    public boolean advance() {
        if (current + 1 == getMaxRule()) {return false;}
        current++;
        return true;
    }

    public void reset() {
        current = 0;
        resultId.clear();
    }

    public ArrayList<String> getResultId() {
        return resultId;
    }

    public ArrayList<Rule> getRules() {
        return rules;
    }
}
