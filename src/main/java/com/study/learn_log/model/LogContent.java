package com.study.learn_log.model;

import java.util.Arrays;
import java.util.Map;

public class LogContent {
        private Map data;
        private String sdata;
        private Map    user;

        private double[] loc;
        private String   version;
        private Integer      edition;
        private Long     timestamp;

        public Map getData() {
            return data;
        }

        public void setData(Map data) {
            this.data = data;
        }

        public String getSdata() {
            return sdata;
        }

        public void setSdata(String sdata) {
            this.sdata = sdata;
        }

        public Map getUser() {
            return user;
        }

        public void setUser(Map user) {
            this.user = user;
        }

        public double[] getLoc() {
            return loc;
        }

        public void setLoc(double[] loc) {
            this.loc = loc;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public Integer getEdition() {
            return edition;
        }

        public void setEdition(Integer edition) {
            this.edition = edition;
        }

        public Long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "LogContent [data=" + data + ", sdata=" + sdata + ", user=" + user + ", loc=" + Arrays.toString(loc)
                    + ", version=" + version + ", edition=" + edition + ", timestamp=" + timestamp + "]";
        }
    }