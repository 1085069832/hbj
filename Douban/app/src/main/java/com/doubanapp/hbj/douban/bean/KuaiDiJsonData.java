package com.doubanapp.hbj.douban.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20 0020.
 */
public class KuaiDiJsonData {

    /**
     * message : ok
     * nu : 500379523313
     * ischeck : 1
     * condition : F00
     * com : yuantong
     * status : 200
     * state : 3
     * data : [{"time":"2016-08-30 13:35:12","ftime":"2016-08-30 13:35:12","context":"客户 签收人: 邮件收发章 已签收 感谢使用圆通速递，期待再次为您服务","location":null},{"time":"2016-08-30 08:39:30","ftime":"2016-08-30 08:39:30","context":"广东省广州市越秀区北京路公司(点击查询电话)蔡** 派件中 派件员电话020-66677880","location":null},{"time":"2016-08-29 23:48:15","ftime":"2016-08-29 23:48:15","context":"广州转运中心 已发出,下一站 广东省广州市越秀区北京路","location":null},{"time":"2016-08-29 23:00:07","ftime":"2016-08-29 23:00:07","context":"广州转运中心 已收入","location":null},{"time":"2016-08-28 01:46:48","ftime":"2016-08-28 01:46:48","context":"沈阳转运中心 已发出,下一站 广州转运中心","location":null},{"time":"2016-08-27 22:38:08","ftime":"2016-08-27 22:38:08","context":"沈阳转运中心 已收入","location":null},{"time":"2016-08-27 17:03:28","ftime":"2016-08-27 17:03:28","context":"辽宁省丹东市公司 已发出,下一站 沈阳转运中心","location":null},{"time":"2016-08-27 16:45:17","ftime":"2016-08-27 16:45:17","context":"辽宁省丹东市第一分部公司(点击查询电话) 已揽收","location":null},{"time":"2016-08-27 16:31:50","ftime":"2016-08-27 16:31:50","context":"辽宁省丹东市第一分部公司 取件人: 刘学文 已收件","location":null}]
     */

    private String message;
    private String nu;
    private String ischeck;
    private String condition;
    private String com;
    private String status;
    private String state;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * time : 2016-08-30 13:35:12
         * ftime : 2016-08-30 13:35:12
         * context : 客户 签收人: 邮件收发章 已签收 感谢使用圆通速递，期待再次为您服务
         * location : null
         */

        private String time;
        private String ftime;
        private String context;
        private Object location;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getFtime() {
            return ftime;
        }

        public void setFtime(String ftime) {
            this.ftime = ftime;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public Object getLocation() {
            return location;
        }

        public void setLocation(Object location) {
            this.location = location;
        }
    }
}
