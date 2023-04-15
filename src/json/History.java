package json;

import answer.Answer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class History {
    private HashMap<String, ArrayList<Answer>> history;

    public History() {
        this.history = new HashMap<>();
    }

    public void addHistory(ArrayList<Answer> answers) {
        history.put(
                new SimpleDateFormat("HH:mm:ss | dd/MM/yyyy")
                        .format(Calendar.getInstance().getTime()),
                answers);
    }

    public void addHistory(HashMap<String, ArrayList<Answer>> history) {
        if (history == null) return;
        this.history = history;
    }

    public HashMap<String, ArrayList<Answer>> getHistory() {
        return history;
    }

    public void clear() {
        history.clear();
    }

    public boolean isEmpty() {
        return history == null || history.isEmpty();
    }
}
