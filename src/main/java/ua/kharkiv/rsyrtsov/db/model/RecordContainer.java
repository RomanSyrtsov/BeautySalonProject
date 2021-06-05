package ua.kharkiv.rsyrtsov.db.model;

import java.util.ArrayList;
import java.util.List;

public class RecordContainer {
    private List<Record> schedules;

    public List<Record> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Record> schedules) {
        this.schedules = schedules;
    }

    public int getIdByDateAndTime(String time, String date) {
        int recordId = 0;
        for (Record schedule : schedules) {
            if (schedule.getDate().equals(date) && schedule.getTime().equals(time)) {
                recordId = schedule.getRecordId();
            }
        }
        return recordId;
    }

    public int getStatusByDateAndTime(String time, String date) {
        int statusId = 0;
        for (Record schedule : schedules) {
            if (schedule.getDate().equals(date) && schedule.getTime().equals(time)) {
                statusId = schedule.getStatusId();
            }
        }
        return statusId;
    }

    public double getPriceById(int id) {
        double price = 0;
        for (Record schedule : schedules) {
            if (schedule.getRecordId() == id) {
                price = schedule.getService_price();
            }
        }
        return price;
    }

    public int getPaymentInfoById(int id) {
        int isPayed = 0;
        for (Record schedule : schedules) {
            if (schedule.getRecordId() == id) {
                isPayed = schedule.getIsPayed();
            }
        }
        return isPayed;
    }

    public List<String> getFreeDates() {
        List<String> dates = new ArrayList<>();
        for (Record schedule : schedules) {
            if (schedule.getRecordId() == 0) {
                if (!dates.contains(schedule.getDate())) {
                    dates.add(schedule.getDate());
                }
            }
        }
        return dates;
    }

    public List<String> getFreeTimeByDate(String date) {
        List<String> times = new ArrayList<>();
        for (Record schedule : schedules) {
            if (schedule.getDate().equals(date) && schedule.getRecordId() == 0) {
                if (!times.contains(schedule.getTime())) {
                    times.add(schedule.getTime());
                }
            }
        }
        return times;
    }

    public List<String> getTimes() {
        List<String> times = new ArrayList<>();
        for (Record schedule : schedules) {
            if (!times.contains(schedule.getTime())) {
                times.add(schedule.getTime());
            }
        }
        return times;
    }

    public List<String> getDates() {
        List<String> dates = new ArrayList<>();
        for (Record schedule : schedules) {
            if (!dates.contains(schedule.getDate())) {
                dates.add(schedule.getDate());
            }
        }
        return dates;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Record schedule : schedules) {
            stringBuilder.append(schedule + "\n");
        }
        return stringBuilder.toString();
    }
}
