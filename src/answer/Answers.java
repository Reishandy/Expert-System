package answer;

import java.util.ArrayList;

public class Answers {
    private ArrayList<Answer> answers;

    public Answers() {
        answers = new ArrayList<>();
    }

    public void addAnswer(Answer answer) {answers.add(answer);}

    public ArrayList<Answer> validate(ArrayList<String> resultId) {
        ArrayList<Answer> resultValidate = new ArrayList<>();
        for (Answer answer: answers) {
            if(answer.answerId().containsAll(resultId)) {
                resultValidate.add(answer);
            }
        }
        return resultValidate;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }
}
