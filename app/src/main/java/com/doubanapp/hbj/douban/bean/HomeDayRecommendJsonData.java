package com.doubanapp.hbj.douban.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/2 0002.
 */
public class HomeDayRecommendJsonData {

    /**
     * category : ["Android","iOS","拓展资源","福利","休息视频","前端","App"]
     * error : false
     * results : {"Android":[{"_id":"58ef1a55421aa9544ed88936","createdAt":"2017-04-13T14:27:33.889Z","desc":"java8 以下，使用Stream Api。","publishedAt":"2017-04-14T11:46:48.47Z","source":"web","type":"Android","url":"https://github.com/aNNiMON/Lightweight-Stream-API","used":true,"who":"瘦纸好过夏"},{"_id":"58ef1b1a421aa9544b773fd3","createdAt":"2017-04-13T14:30:50.522Z","desc":"Android Toast 原理分析","publishedAt":"2017-04-14T11:46:48.47Z","source":"web","type":"Android","url":"http://qlm.pw/2017/04/13/android-toast-%E5%8E%9F%E7%90%86%E5%88%86%E6%9E%90/","used":true,"who":"Linmin Qiu"},{"_id":"58efa248421aa954511ebe7e","createdAt":"2017-04-14T00:07:36.366Z","desc":"展示 Android 程序方法调用链的 gralde 插件，支持输出html文件和方法折叠","images":["http://img.gank.io/9be64918-c259-4f81-9ab5-341293a74fe4"],"publishedAt":"2017-04-14T11:46:48.47Z","source":"web","type":"Android","url":"https://github.com/CoXier/AppMethodTracking","used":true,"who":"CoXier"},{"_id":"58f01f07421aa954511ebe7f","createdAt":"2017-04-14T08:59:51.702Z","desc":"一个简单的 SQLite 和 Excel 互相转换的库子","publishedAt":"2017-04-14T11:46:48.47Z","source":"web","type":"Android","url":"https://github.com/li-yu/SQLiteToExcel","used":true,"who":"liyu"},{"_id":"58f04302421aa9544b773fdc","createdAt":"2017-04-14T11:33:22.130Z","desc":"遮罩对比图效果组件","images":["http://img.gank.io/6becd3af-df53-4935-bbd9-1099aae74ec6"],"publishedAt":"2017-04-14T11:46:48.47Z","source":"chrome","type":"Android","url":"https://github.com/pavel163/BifacialView","used":true,"who":"代码家"}],"App":[{"_id":"58eae3b8421aa9544825f83f","createdAt":"2017-04-10T09:45:28.617Z","desc":"Unsplash 的三方壁纸客户端","images":["http://img.gank.io/da234724-80ab-4059-ae3b-52831508e45c"],"publishedAt":"2017-04-10T12:11:14.794Z","source":"chrome","type":"App","url":"https://github.com/WangDaYeeeeee/Mysplash","used":true,"who":"咕咚"}],"iOS":[{"_id":"58ef3e73421aa9544ed88938","createdAt":"2017-04-13T17:01:39.812Z","desc":"FFmpeg，SDL2.0 C语言跨平台播放器实现（iOS Demo）","publishedAt":"2017-04-14T11:46:48.47Z","source":"web","type":"iOS","url":"http://www.jidongchen.com/post/ffmpeg-sdl2.0","used":true,"who":"Jidong Chen"},{"_id":"58f04362421aa9544b773fdd","createdAt":"2017-04-14T11:34:58.272Z","desc":"Swift 实现遮罩擦除效果，搞个红包抽奖刮刮卡什么效果的可能用得到。","images":["http://img.gank.io/6e914269-3cc5-4b36-b46e-814bebea1d73"],"publishedAt":"2017-04-14T11:46:48.47Z","source":"chrome","type":"iOS","url":"https://github.com/pgorzelany/ScratchCardView","used":true,"who":"啊呆"}],"休息视频":[{"_id":"58f0456a421aa9544825f875","createdAt":"2017-04-14T11:43:38.824Z","desc":"【实测】耳机之间的差别有多大?","publishedAt":"2017-04-14T11:46:48.47Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av8823143/","used":true,"who":"daimajia"}],"前端":[{"_id":"58e44c68421aa90d6119869a","createdAt":"2017-04-05T09:46:16.168Z","desc":"纯 JS 实现的 Tooltip 库","images":["http://img.gank.io/b0065957-6d0a-424e-869e-9e2aa42c909d"],"publishedAt":"2017-04-05T11:50:18.748Z","source":"chrome","type":"前端","url":"https://atomiks.github.io/tippyjs/","used":true,"who":"带马甲"}],"拓展资源":[{"_id":"58f04228421aa9544b773fdb","createdAt":"2017-04-14T11:29:44.766Z","desc":"基于 scrapy 实现的爬虫管理 Web UI。","images":["http://img.gank.io/abc37f88-d064-44c7-88bf-6201051d1dd6"],"publishedAt":"2017-04-14T11:46:48.47Z","source":"chrome","type":"拓展资源","url":"https://github.com/DormyMo/SpiderKeeper","used":true,"who":"代码家"},{"_id":"58f0426c421aa9544ed88940","createdAt":"2017-04-14T11:30:52.178Z","desc":"Chrome Headless 模式下的控制封装 Api","publishedAt":"2017-04-14T11:46:48.47Z","source":"chrome","type":"拓展资源","url":"https://github.com/cyrus-and/chrome-remote-interface","used":true,"who":"棒呆"}],"福利":[{"_id":"58f0438f421aa9544825f873","createdAt":"2017-04-14T11:35:43.995Z","desc":"4-14","publishedAt":"2017-04-14T11:46:48.47Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-14-17881962_1329090457138411_8289893708619317248_n.jpg","used":true,"who":"代码家"}]}
     */

    private boolean error;
    private ResultsBean results;
    private List<String> category;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public static class ResultsBean {
        private List<AndroidBean> Android;
        private List<AppBean> App;
        private List<IOSBean> iOS;
        private List<休息视频Bean> 休息视频;
        private List<前端Bean> 前端;
        private List<拓展资源Bean> 拓展资源;
        private List<福利Bean> 福利;

        public List<AndroidBean> getAndroid() {
            return Android;
        }

        public void setAndroid(List<AndroidBean> Android) {
            this.Android = Android;
        }

        public List<AppBean> getApp() {
            return App;
        }

        public void setApp(List<AppBean> App) {
            this.App = App;
        }

        public List<IOSBean> getIOS() {
            return iOS;
        }

        public void setIOS(List<IOSBean> iOS) {
            this.iOS = iOS;
        }

        public List<休息视频Bean> get休息视频() {
            return 休息视频;
        }

        public void set休息视频(List<休息视频Bean> 休息视频) {
            this.休息视频 = 休息视频;
        }

        public List<前端Bean> get前端() {
            return 前端;
        }

        public void set前端(List<前端Bean> 前端) {
            this.前端 = 前端;
        }

        public List<拓展资源Bean> get拓展资源() {
            return 拓展资源;
        }

        public void set拓展资源(List<拓展资源Bean> 拓展资源) {
            this.拓展资源 = 拓展资源;
        }

        public List<福利Bean> get福利() {
            return 福利;
        }

        public void set福利(List<福利Bean> 福利) {
            this.福利 = 福利;
        }

        public static class AndroidBean {
            /**
             * _id : 58ef1a55421aa9544ed88936
             * createdAt : 2017-04-13T14:27:33.889Z
             * desc : java8 以下，使用Stream Api。
             * publishedAt : 2017-04-14T11:46:48.47Z
             * source : web
             * type : Android
             * url : https://github.com/aNNiMON/Lightweight-Stream-API
             * used : true
             * who : 瘦纸好过夏
             * images : ["http://img.gank.io/9be64918-c259-4f81-9ab5-341293a74fe4"]
             */

            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;
            private List<String> images;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getWho() {
                return who;
            }

            public void setWho(String who) {
                this.who = who;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }

        public static class AppBean {
            /**
             * _id : 58eae3b8421aa9544825f83f
             * createdAt : 2017-04-10T09:45:28.617Z
             * desc : Unsplash 的三方壁纸客户端
             * images : ["http://img.gank.io/da234724-80ab-4059-ae3b-52831508e45c"]
             * publishedAt : 2017-04-10T12:11:14.794Z
             * source : chrome
             * type : App
             * url : https://github.com/WangDaYeeeeee/Mysplash
             * used : true
             * who : 咕咚
             */

            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;
            private List<String> images;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getWho() {
                return who;
            }

            public void setWho(String who) {
                this.who = who;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }

        public static class IOSBean {
            /**
             * _id : 58ef3e73421aa9544ed88938
             * createdAt : 2017-04-13T17:01:39.812Z
             * desc : FFmpeg，SDL2.0 C语言跨平台播放器实现（iOS Demo）
             * publishedAt : 2017-04-14T11:46:48.47Z
             * source : web
             * type : iOS
             * url : http://www.jidongchen.com/post/ffmpeg-sdl2.0
             * used : true
             * who : Jidong Chen
             * images : ["http://img.gank.io/6e914269-3cc5-4b36-b46e-814bebea1d73"]
             */

            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;
            private List<String> images;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getWho() {
                return who;
            }

            public void setWho(String who) {
                this.who = who;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }

        public static class 休息视频Bean {
            /**
             * _id : 58f0456a421aa9544825f875
             * createdAt : 2017-04-14T11:43:38.824Z
             * desc : 【实测】耳机之间的差别有多大?
             * publishedAt : 2017-04-14T11:46:48.47Z
             * source : chrome
             * type : 休息视频
             * url : http://www.bilibili.com/video/av8823143/
             * used : true
             * who : daimajia
             */

            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getWho() {
                return who;
            }

            public void setWho(String who) {
                this.who = who;
            }
        }

        public static class 前端Bean {
            /**
             * _id : 58e44c68421aa90d6119869a
             * createdAt : 2017-04-05T09:46:16.168Z
             * desc : 纯 JS 实现的 Tooltip 库
             * images : ["http://img.gank.io/b0065957-6d0a-424e-869e-9e2aa42c909d"]
             * publishedAt : 2017-04-05T11:50:18.748Z
             * source : chrome
             * type : 前端
             * url : https://atomiks.github.io/tippyjs/
             * used : true
             * who : 带马甲
             */

            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;
            private List<String> images;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getWho() {
                return who;
            }

            public void setWho(String who) {
                this.who = who;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }

        public static class 拓展资源Bean {
            /**
             * _id : 58f04228421aa9544b773fdb
             * createdAt : 2017-04-14T11:29:44.766Z
             * desc : 基于 scrapy 实现的爬虫管理 Web UI。
             * images : ["http://img.gank.io/abc37f88-d064-44c7-88bf-6201051d1dd6"]
             * publishedAt : 2017-04-14T11:46:48.47Z
             * source : chrome
             * type : 拓展资源
             * url : https://github.com/DormyMo/SpiderKeeper
             * used : true
             * who : 代码家
             */

            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;
            private List<String> images;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getWho() {
                return who;
            }

            public void setWho(String who) {
                this.who = who;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }

        public static class 福利Bean {
            /**
             * _id : 58f0438f421aa9544825f873
             * createdAt : 2017-04-14T11:35:43.995Z
             * desc : 4-14
             * publishedAt : 2017-04-14T11:46:48.47Z
             * source : chrome
             * type : 福利
             * url : http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-14-17881962_1329090457138411_8289893708619317248_n.jpg
             * used : true
             * who : 代码家
             */

            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getWho() {
                return who;
            }

            public void setWho(String who) {
                this.who = who;
            }
        }
    }
}
