package parsers;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

// Represents Task parser
public class TaskParser {

    // EFFECTS: iterates over every JSONObject in the JSONArray represented by the input
    // string and parses it as a task; each parsed task is added to the list of tasks.
    // Any task that cannot be parsed due to malformed JSON data is not added to the
    // list of tasks.
    // Note: input is a string representation of a JSONArray
    public List<Task> parse(String input) {

        List<Task> listTask = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(input);

        for (Object object : jsonArray) {

            Task tk = new Task(" ");

            JSONObject taskJson = (JSONObject) object;

            //set description
            String description = (String) taskJson.get("description");
            tk.setDescription(description);

            //set due day
            JSONObject dueDateJson = (JSONObject) taskJson.get("due-date");
            DueDate dd = jsonToDueDay(dueDateJson);
            tk.setDueDate(dd);

            //set tags
            setTags(tk, taskJson);

            //set priority
            setPriority(tk, taskJson);

            //set Status
            setStatus(tk, taskJson);


            listTask.add(tk);
        }

        return listTask;

    }

    private void setTags(Task tk, JSONObject taskJson) {
        JSONArray tags = (JSONArray) taskJson.get("tags");
        for (Object t :
                tags) {
            JSONObject tag = (JSONObject) t;
            String strTag = (String) tag.get("name");
            tk.addTag(strTag);
        }
    }

    private void setStatus(Task tk, JSONObject taskJson) {
        String statusBefore = (String) taskJson.get("status");
        String statusAfter = statusBefore.toUpperCase();
        if (statusAfter.equalsIgnoreCase(Status.TODO.name())) {
            tk.setStatus(Status.TODO);
        } else if (statusAfter.equalsIgnoreCase(Status.UP_NEXT.name())) {
            tk.setStatus(Status.UP_NEXT);
        } else if (statusAfter.equalsIgnoreCase(Status.IN_PROGRESS.name())) {
            tk.setStatus(Status.IN_PROGRESS);
        } else if (statusAfter.equalsIgnoreCase(Status.DONE.name())) {
            tk.setStatus(Status.DONE);
        }
    }

    private void setPriority(Task tk, JSONObject taskJson) {
        JSONObject priorityJson = (JSONObject) taskJson.get("priority");
        Priority p = new Priority();
        Boolean isImportant = (boolean) priorityJson.get("important");
        Boolean isUrgent = (boolean) priorityJson.get("urgent");
        p.setImportant(isImportant);
        p.setUrgent(isUrgent);
        tk.setPriority(p);
    }

    private DueDate jsonToDueDay(JSONObject dueDateJson) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, (Integer) dueDateJson.get("year"));
        cal.set(Calendar.MONTH, (Integer) dueDateJson.get("month"));
        cal.set(Calendar.DATE, (Integer) dueDateJson.get("day"));
        cal.set(Calendar.HOUR_OF_DAY, (Integer) dueDateJson.get("hour"));
        cal.set(Calendar.MINUTE, (Integer) dueDateJson.get("minute"));

        Date dt = cal.getTime();
        return (new DueDate(dt));
    }


}
