package json;

import answer.Answer;

import java.util.ArrayList;
import java.util.HashMap;

public class History {
    private HashMap<Integer, ArrayList<Answer>> history;

    public History() {
        this.history = new HashMap<>();
    }

    public void addHistory(ArrayList<Answer> answers) {
        int count = history.size();
        history.put(count + 1, answers);
    }

    public void addHistory(HashMap<Integer, ArrayList<Answer>> history) {
        if (history == null) return;
        this.history = history;
    }

    public HashMap<Integer, ArrayList<Answer>> getHistory() {
        return history;
    }

    public void clear() {
        history.clear();
    }

    public boolean isEmpty() {
        return history == null || history.isEmpty();
    }
}
