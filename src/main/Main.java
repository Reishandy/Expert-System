package main;

import rule.*;
import json.*;
import answer.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Rules rules = new Rules();
    private static final Answers answers = new Answers();
    private static final Json jsonHandler = new Json();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //TODO: Cleanup code...
        System.out.println("Expert System Terminal test");

        loadRule();
        loadAnswer();
        
        while (true) {
            System.out.println("-------------------");
            System.out.println("1. Start");
            System.out.println("2. Add rule");
            System.out.println("3. Add answer");
            System.out.println("4. Display rules");
            System.out.println("5. Display answers");
            System.out.println("type q to quit program");
            System.out.println("-------------------");
            System.out.print("> ");
            String input = sc.nextLine();

            if (input.equals("q")) break;
            if (input.equals("1")) start();
            if (input.equals("2")) addRule();
            if (input.equals("3")) addAnswer();
            if (input.equals("4")) displayRules();
            if (input.equals("5")) displayAnswers();
        }
    }

    public static void start() {
        System.out.println("-------------------");
        if (rules.getRules().isEmpty()) {
            System.out.println("!!! Add rules first");
            return;
        }
        if (answers.getAnswers().isEmpty()) {
            System.out.println("!!! Add answers first");
            return;
        }

        rules.reset();
        do {
            System.out.printf("> %s (Y/n)\n> ", rules.getCurrentRule());
            String input = sc.nextLine().toLowerCase();
            rules.check(input.equals("y"));
        } while (rules.advance());

        ArrayList<Answer> result = answers.validate(rules.getResultId());
        System.out.println("-------------------");
        for (Answer answer: result) {
            System.out.printf("""
                    Title: %s
                    Description: %s
                    ---
                    """, answer.title(), answer.description());
        }
    }

    public static void displayRules() {
        System.out.println("-------------------");
        ArrayList<Rule> ruleArrayList = rules.getRules();
        if (ruleArrayList.isEmpty()) {
            System.out.println("!!! Add rules first");
            return;
        }

        for (Rule rule: ruleArrayList) {
            System.out.printf("""
                    Rule: %s
                    ID: %s
                    ---
                    """, rule.rule(), rule.id());
        }
    }

    public static void displayAnswers() {
        System.out.println("-------------------");
        ArrayList<Answer> answerArrayList = answers.getAnswers();
        if (answerArrayList.isEmpty()) {
            System.out.println("!!! Add answer first");
            return;
        }

        for (Answer answer: answerArrayList) {
            System.out.printf("""
                    Title: %s
                    Description: %s
                    IDs: %s
                    ---
                    """, answer.title(), answer.description(), answer.answerId());
        }
    }

    private static void loadRule() {
        ArrayList<Rule> ruleArrayList;
        try {
            ruleArrayList = jsonHandler.getRuleJson("rule.json");
        } catch (FileNotFoundException e) {
            System.out.println("!!! Add rules first...");
            return;
        }
        if (ruleArrayList.isEmpty()) {
            System.out.println("!!! Add rules first...");
            return;
        }
        rules.addRule(ruleArrayList);
    }

    public static void loadAnswer() {
        ArrayList<Answer> answerArrayList;
        try {
            answerArrayList = jsonHandler.getAnswerJson("answer.json");
        } catch (FileNotFoundException e) {
            System.out.println("!!! Add answers first...");
            return;
        }
        if (answerArrayList.isEmpty()) {
            System.out.println("!!! Add answers first...");
            return;
        }
        answers.addAnswer(answerArrayList);
    }

    public static void addRule() {
        System.out.println("-------------------");
        System.out.println("type q to quit");
        while (true) {
            System.out.println("---");
            System.out.print("Input ID: ");
            String id = sc.nextLine();
            if (id.equals("q")) break;

            System.out.print("Input Rule: ");
            String rule = sc.nextLine();
            if (rule.equals("q")) break;

            if (id.isEmpty() || rule.isEmpty()) {
                System.out.println("Must have a value");
                continue;
            }

            rules.addRule(new Rule(id, rule));
        }

        ArrayList<Rule> newRule = rules.getRules();
        try {
            jsonHandler.storeRuleJson(newRule, "rule.json");
        } catch (IOException e) {
            System.out.println("Something went wrong, cannot save to file");
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void addAnswer() {
        System.out.println("-------------------");
        System.out.println("type q to quit");
        while (true) {
            System.out.println("---");
            System.out.print("Input Title: ");
            String title = sc.nextLine();
            if (title.equals("q")) break;

            System.out.print("Input Description: ");
            String description = sc.nextLine();
            if (description.equals("q")) break;

            System.out.print("Input IDs (seperated by comma): ");
            String ids = sc.nextLine();
            if (ids.equals("q")) break;

            if (title.isEmpty() || description.isEmpty() || ids.isEmpty()) {
                System.out.println("Must have a value");
                continue;
            }

            ArrayList<String> answerId = new ArrayList<>(
                    Arrays.asList(ids.split(",")));
            answers.addAnswer(new Answer(answerId, title, description));
        }

        ArrayList<Answer> newAnswer = answers.getAnswers();
        try {
            jsonHandler.storeAnswerJson(newAnswer, "answer.json");
        } catch (IOException e) {
            System.out.println("Something went wrong, cannot save to file");
            System.out.println("Error: " + e.getMessage());
        }
    }
}
