package answer;

import rule.Rule;
import rule.Rules;

import java.util.ArrayList;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Rules rules = new Rules();
        rules.addRule(new Rule("1", "a"));
        rules.addRule(new Rule("2", "b"));
        rules.addRule(new Rule("3", "c"));
        rules.addRule(new Rule("4", "d"));

        do {
            System.out.print(rules.getCurrentRule());
            String input = sc.nextLine().toLowerCase();
            rules.check(input.equals("y"));
        } while (rules.advance());

        ArrayList<String> resultId = rules.getResultId();
        System.out.println(resultId);

        Answer an1 = new Answer(
                new ArrayList<>() {{
                        add("1");
                        add("3");
                        add("4");
                    }
                },
                "acd",
                "acd answer"
        );
        Answer an2 = new Answer(
                new ArrayList<>() {{
                    add("1");
                    add("2");
                }
                },
                "ab",
                "ab answer"
        );
        Answers answers = new Answers();
        answers.addAnswer(an1);
        answers.addAnswer(an2);
        ArrayList<Answer> result = answers.validate(resultId);
        for (Answer answer: result) {
            System.out.println(answer.description());
        }
    }
}
