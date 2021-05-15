package ua.kharkiv.rsyrtsov.utils;

import ua.kharkiv.rsyrtsov.db.model.Master;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorter {

    public static final Comparator<Master> SORT_MASTERS_BY_NAME = Comparator.comparing(Master::getFirstname);
    public static final Comparator<Master> SORT_MASTERS_BY_RATE =  Comparator.comparingInt(Master::getMaster_rate);

    public static final void sortMastersByName(List<Master> masters) {
        Collections.sort(masters, SORT_MASTERS_BY_NAME);
    }

    public static final void sortMastersByRate(List<Master> masters) {
        Collections.sort(masters, SORT_MASTERS_BY_RATE.reversed());
    }

}
