package ua.kharkiv.rsyrtsov;

import ua.kharkiv.rsyrtsov.db.dao.MasterDao;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) {

        Map<String, Map<String,Integer>> map = new TreeMap<>();
        Map<String,Integer> map2 = new TreeMap<>();
        Map<String,Integer> map3 = new TreeMap<>();
        map2.put("08:00",1);
        map2.put("08:30",2);
        map2.put("09:00",0);
        map2.put("09:30",0);
        map.put("01.01.21",map2);

        map3.put("08:00",0);
        map3.put("08:30",0);
        map3.put("09:00",0);
        map3.put("09:30",0);
        map.put("02.01.21",map3);
        int count = 0;
        for (String key: map.keySet()) {
            System.out.println(key);
            for (String key1: map.get(key).keySet()) {
                if(count < map2.keySet().size()) {
                    System.out.println(key1 + ": " + map.get(key).get(key1));
                    ++count;
                }else {
                    System.out.println(map.get(key).get(key1));
                }
            }
        }


    }
}
