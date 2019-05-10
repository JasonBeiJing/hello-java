package com.hello.java.test;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class TagParserProxyTest {

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        //System.err.println(JSONObject.toJSONString(getContracts()));

        //String key = "id"; //最后
        //String key = "name"; //最后
        //String key = " address . country "; //最后
        //String key = "sources";//最后
        //String key = "maps{B}";//最后
        //String key = "lines[number]";//最后
        //String key = "mapLines{HAHA}[number]";
        String key = "mapListLines{Y}[number]";

        System.err.println("===> " + JSONObject.toJSONString(TagParserUtil.getTagValues(getContracts(), key)));
    }



    private static List<Contract> getContracts() {
        List<Contract> contracts = new ArrayList<>();
        Contract e = new Contract();
        e.setName("X");
        e.setSources(Arrays.asList("a", "b"));
        e.setLines(Arrays.asList(new Line(1), new Line(2)));
        e.setAddress(new Address("china", "shandong"));
        Map<String, Line> mapLines1 = new HashMap<>();
        mapLines1.put("HAHA", new Line(5));
        mapLines1.put("HEIHEI", new Line(6));
        e.setMapLines(mapLines1);
        Map<String, String> maps = new HashMap<>();
        maps.put("A", "111");
        maps.put("B", "222");
        e.setMaps(maps);
        Map<String, List<Line>> mapListLines1 = new HashMap<>();
        mapListLines1.put("X", Arrays.asList(new Line(66), new Line(77)));
        mapListLines1.put("Y", Arrays.asList(new Line(88), new Line(99)));
        e.setMapListLines(mapListLines1);
        contracts.add(e);
        Contract s = new Contract();
        s.setName("Y");
        s.setSources(Arrays.asList("c", "d"));
        s.setLines(Arrays.asList(new Line(3), new Line(4)));
        s.setAddress(new Address("德国", "巴伐利亚"));
        Map<String, Line> mapLines2 = new HashMap<>();
        mapLines2.put("HAHA", new Line(7));
        mapLines2.put("HEHE", new Line(8));
        s.setMapLines(mapLines2);
        Map<String, String> maps2 = new HashMap<>();
        maps2.put("A", "333");
        maps2.put("B", "444");
        s.setMaps(maps2);
        Map<String, List<Line>> mapListLines2 = new HashMap<>();
        mapListLines2.put("Y", Arrays.asList(new Line(11)));
        s.setMapListLines(mapListLines2);
        contracts.add(s);
        return contracts;
    }

    static class Contract implements Serializable {
        private String id = UUID.randomUUID().toString();
        private String name;
        private List<String> sources;
        private  Map<String, String> maps;
        private Address address;
        private List<Line> lines;
        private Map<String, Line> mapLines;

        public Map<String, List<Line>> getMapListLines() {
            return mapListLines;
        }

        public void setMapListLines(Map<String, List<Line>> mapListLines) {
            this.mapListLines = mapListLines;
        }

        private Map<String, List<Line>> mapListLines;

        public Map<String, String> getMaps() {
            return maps;
        }

        public void setMaps(Map<String, String> maps) {
            this.maps = maps;
        }

        public Map<String, Line> getMapLines() {
            return mapLines;
        }

        public void setMapLines(Map<String, Line> mapLines) {
            this.mapLines = mapLines;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getSources() {
            return sources;
        }

        public void setSources(List<String> sources) {
            this.sources = sources;
        }

        public List<Line> getLines() {
            return lines;
        }

        public void setLines(List<Line> lines) {
            this.lines = lines;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    static class Line implements Serializable{
        private String id = UUID.randomUUID().toString();;
        private Integer number;

        public Line() {
        }

        public Line(Integer number) {
            this.number = number;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }
    }

    static class Address implements Serializable{
        private String country;
        private String state;

        public Address() {
        }

        public Address(String country, String state) {
            this.country = country;
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
