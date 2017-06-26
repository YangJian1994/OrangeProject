package com.example.orangeproject.bean;

import java.util.List;

/**
 * Created by 杨健 on 2017/6/26.
 * function: 返回的json数据
 */

public class Data {


    /**
     * state : true
     * modules : [{"type":512,"state":true,"AQI":0,"PM25":0,"frame_battery":50,"step_count":0},{"type":1024,"state":false,"AQI":0,"PM25":0,"frame_battery":0,"step_count":0},{"type":1280,"state":false,"AQI":0,"PM25":0,"frame_battery":0,"step_count":0},{"type":1792,"state":true,"AQI":0,"PM25":0,"frame_battery":0,"step_count":98},{"type":768,"state":false,"AQI":0,"PM25":0,"frame_battery":0,"step_count":0}]
     */

    private boolean state;
    private List<ModulesBean> modules;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public List<ModulesBean> getModules() {
        return modules;
    }

    public void setModules(List<ModulesBean> modules) {
        this.modules = modules;
    }

    public static class ModulesBean {
        /**
         * type : 512
         * state : true
         * AQI : 0
         * PM25 : 0
         * frame_battery : 50
         * step_count : 0
         */

        private int type;
        private boolean state;
        private int AQI;
        private int PM25;
        private int frame_battery;
        private int step_count;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public int getAQI() {
            return AQI;
        }

        public void setAQI(int AQI) {
            this.AQI = AQI;
        }

        public int getPM25() {
            return PM25;
        }

        public void setPM25(int PM25) {
            this.PM25 = PM25;
        }

        public int getFrame_battery() {
            return frame_battery;
        }

        public void setFrame_battery(int frame_battery) {
            this.frame_battery = frame_battery;
        }

        public int getStep_count() {
            return step_count;
        }

        public void setStep_count(int step_count) {
            this.step_count = step_count;
        }
    }
}
