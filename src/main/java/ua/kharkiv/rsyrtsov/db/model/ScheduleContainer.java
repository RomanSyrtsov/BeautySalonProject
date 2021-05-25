package ua.kharkiv.rsyrtsov.db.model;

import java.util.ArrayList;
import java.util.List;

public class ScheduleContainer {
    private List<Schedule> schedules;

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public int getIdByDateAndTime(String time, String date){
        int recordId = 0;
        for (Schedule schedule:schedules) {
            if(schedule.getDate().equals(date) && schedule.getTime().equals(time)){
                recordId = schedule.getRecordId();
            }
        }
        return recordId;
    }

    public List<String> getTimes(){
        List<String> times = new ArrayList<>();
        for (Schedule schedule:schedules) {
            if(!times.contains(schedule.getTime())){
                times.add(schedule.getTime());
            }
        }
        return times;
    }

    public List<String> getDates(){
        List<String> dates = new ArrayList<>();
        for (Schedule schedule:schedules) {
            if(!dates.contains(schedule.getDate())){
                dates.add(schedule.getDate());
            }
        }
        return dates;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Schedule schedule:schedules) {
            stringBuilder.append(schedule + "\n");
        }
        return stringBuilder.toString();
    }
}
