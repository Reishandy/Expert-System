/*
 * Author: Reishandy
 */

package answer;

import java.util.ArrayList;

public class Answers {
    private final ArrayList<Answer> answers;

    public Answers() {
        answers = new ArrayList<>();
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void addAnswer(ArrayList<Answer> answers) {
        if (answers == null) return;
        this.answers.addAll(answers);
    }

    public ArrayList<Answer> validate(ArrayList<String> resultId) {
        ArrayList<Answer> resultValidate = new ArrayList<>();
        for (Answer answer: answers) {
            if(resultId.containsAll(answer.answerId())) {
                resultValidate.add(answer);
            }
        }
        return resultValidate;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void clear() {
        answers.clear();
    }

    public boolean isEmpty() {
        return answers.isEmpty();
    }}
