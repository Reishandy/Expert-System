package json;

import rule.Rule;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        Json jsonHandler = new Json();
        ArrayList<Rule> rules;
        try {
            rules = jsonHandler.getRuleJson("rule.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        for(Rule rule : rules) {
            System.out.println(rule);
        }

        rules.add(new Rule("ayam", "Goreng?"));

        try {
            jsonHandler.storeRuleJson(rules, "rule.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            jsonHandler.clear("rule.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
