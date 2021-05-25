package ua.kharkiv.rsyrtsov.db.model;

public class Schedule {
    private int recordId;
    private String date;
    private String time;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "recordId=" + recordId +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
