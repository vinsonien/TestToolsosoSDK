package com.test.sdk.toolsoso.eventbus;

public class EventTestBean {

    String beanStr;
    int beanInt;
    boolean beanBoolean;
    EventIn beanIn;

    public EventTestBean() {
    }

    public EventTestBean(String beanStr, int beanInt, boolean beanBoolean, EventIn beanIn) {
        this.beanStr = beanStr;
        this.beanInt = beanInt;
        this.beanBoolean = beanBoolean;
        this.beanIn = beanIn;
    }

    public static class  EventIn{
        String inStr;
        int inInt;
        boolean inBoolean;

        public EventIn() {
        }

        public EventIn(String inStr, int inInt, boolean inBoolean) {
            this.inStr = inStr;
            this.inInt = inInt;
            this.inBoolean = inBoolean;
        }

        public String getInStr() {
            return inStr;
        }

        public void setInStr(String inStr) {
            this.inStr = inStr;
        }

        public int getInInt() {
            return inInt;
        }

        public void setInInt(int inInt) {
            this.inInt = inInt;
        }

        public boolean isInBoolean() {
            return inBoolean;
        }

        public void setInBoolean(boolean inBoolean) {
            this.inBoolean = inBoolean;
        }
    }

    public String getBeanStr() {
        return beanStr;
    }

    public void setBeanStr(String beanStr) {
        this.beanStr = beanStr;
    }

    public int getBeanInt() {
        return beanInt;
    }

    public void setBeanInt(int beanInt) {
        this.beanInt = beanInt;
    }

    public boolean isBeanBoolean() {
        return beanBoolean;
    }

    public void setBeanBoolean(boolean beanBoolean) {
        this.beanBoolean = beanBoolean;
    }

    public EventIn getBeanIn() {
        return beanIn;
    }

    public void setBeanIn(EventIn beanIn) {
        this.beanIn = beanIn;
    }
}
