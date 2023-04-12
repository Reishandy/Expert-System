/*
* Author: Reishandy
*/

package answer;

import java.util.ArrayList;

public record Answer(ArrayList<String> answerId, String title, String description) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (!answerId.equals(answer.answerId)) return false;
        if (!title.equals(answer.title)) return false;
        return description.equals(answer.description);
    }

}
