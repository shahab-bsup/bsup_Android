package tk.medlynk.patient.android.Activity.Progress;

/**
 * Created by admin on 6/22/2018.
 */

public class ProgressInfo {
    String title;
    String details;
    int questionSetId;

    public int getQuestionSetId() {
        return questionSetId;
    }

    public void setQuestionSetId(int questionSetId) {
        this.questionSetId = questionSetId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
