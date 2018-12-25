package com.example.yc.zhihudaily.Model;

import java.util.List;

public class Datas {

    /**
     * date : 20181126
     * top_stories : [{"image":"https://pic2.zhimg.com/v2-d010de74220be657d7025474e573f7c5.jpg","ga_prefix":"112621","id":9702432,"type":0,"title":"贝托鲁奇去世，他留下了最好的宫廷戏"},{"image":"https://pic2.zhimg.com/v2-09264e3914901de22047932bce682bf1.jpg","ga_prefix":"112619","id":9702428,"type":0,"title":"3.47 秒打破世界纪录，中国魔方界迎来史上最激动人心的一天"},{"image":"https://pic2.zhimg.com/v2-05c2d2850902a8f03f1835f787c0c55d.jpg","ga_prefix":"112615","id":9702404,"type":0,"title":"一个坏消息：世界首例基因编辑婴儿在中国诞生"},{"image":"https://pic2.zhimg.com/v2-f24506793034bcf7b2845865d2e95fa5.jpg","ga_prefix":"112607","id":9702374,"type":0,"title":"「鱼缸」里的京东"},{"image":"https://pic4.zhimg.com/v2-1773af9d13783d8ed8f8f8c7a59a10b3.jpg","ga_prefix":"112609","id":9701854,"type":0,"title":"一幅画就是一个，不，好多个故事"}]
     * stories : [{"images":["https://pic3.zhimg.com/v2-308b2df836368f33e7a31442ae27e4ba.jpg"],"ga_prefix":"112622","id":9702392,"type":0,"title":"小事 · 你有哪些秘密一直深埋心底？"},{"images":["https://pic4.zhimg.com/v2-8fec561e1e8489023683ceca7deceb67.jpg"],"ga_prefix":"112621","multipic":true,"id":9702432,"title":"贝托鲁奇去世，他留下了最好的宫廷戏","type":0},{"images":["https://pic1.zhimg.com/v2-f10b85acfcde4f16bf6ee56572fca288.jpg"],"ga_prefix":"112619","multipic":true,"id":9702428,"title":"3.47 秒打破世界纪录，中国魔方界迎来史上最激动人心的一天","type":0},{"images":["https://pic2.zhimg.com/v2-fa459d5f13d04ffecbaad27b9db633f5.jpg"],"ga_prefix":"112615","id":9702404,"type":0,"title":"一个坏消息：世界首例基因编辑婴儿在中国诞生"},{"images":["https://pic1.zhimg.com/v2-d24f19de9b73c4fb2f9472993179f6f4.jpg"],"ga_prefix":"112613","id":9702399,"type":0,"title":"「白水变牛奶」的视频有点可怕，市面上的奶是不是都这么来的？"},{"images":["https://pic4.zhimg.com/v2-1649c69f8e2b1d2eea78620ce52cd39b.jpg"],"ga_prefix":"112612","id":9702335,"type":0,"title":"大误 · 我是不是学了假英语"},{"images":["https://pic1.zhimg.com/v2-ab09a8bbdd4bf07f15b5c99f760fd514.jpg"],"ga_prefix":"112610","id":9702324,"type":0,"title":"在实验室绽放的爱情"},{"images":["https://pic1.zhimg.com/v2-7599bfccdbfc45acf42d7ccff06cddfc.jpg"],"ga_prefix":"112609","multipic":true,"id":9701854,"title":"一幅画就是一个，不，好多个故事","type":0},{"images":["https://pic2.zhimg.com/v2-2cc91b27f39f27a5c3ee1661b3470f29.jpg"],"ga_prefix":"112608","id":9702363,"type":0,"title":"工蜂一直这么勤勤恳恳，不会起兵造反吗？"},{"images":["https://pic3.zhimg.com/v2-9d7519473c98e44cb55a99c0316b776a.jpg"],"ga_prefix":"112607","id":9702366,"type":0,"title":"英国「租借」香港 99 年，其实 99 年和 999 年，差别不大"},{"images":["https://pic4.zhimg.com/v2-b4b995914d883fa63a7721952ccd4e7f.jpg"],"ga_prefix":"112607","id":9702374,"type":0,"title":"「鱼缸」里的京东"},{"images":["https://pic1.zhimg.com/v2-0a8e52419ff7e23932a9fe669bdafad8.jpg"],"ga_prefix":"112606","id":9702347,"type":0,"title":"瞎扯 · 如何正确地吐槽"}]
     */
    public String date;
    List<Top_storiesEntity> top_stories;
    List<StoriesEntity> stories;

    public void setDate(String date) {
        this.date = date;
    }

    public void setTop_stories(List<Top_storiesEntity> top_stories) {
        this.top_stories = top_stories;
    }

    public void setStories(List<StoriesEntity> stories) {
        this.stories = stories;
    }

    public String getDate() {
        return date;
    }

    public List<Top_storiesEntity> getTop_stories() {
        return top_stories;
    }

    public List<StoriesEntity> getStories() {
        return stories;
    }

    public class Top_storiesEntity {
        /**
         * image : https://pic2.zhimg.com/v2-d010de74220be657d7025474e573f7c5.jpg
         * ga_prefix : 112621
         * id : 9702432
         * type : 0
         * title : 贝托鲁奇去世，他留下了最好的宫廷戏
         */
        private String image;
        private String ga_prefix;
        private int id;
        private int type;
        private String title;

        public void setImage(String image) {
            this.image = image;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public int getId() {
            return id;
        }

        public int getType() {
            return type;
        }

        public String getTitle() {
            return title;
        }
    }

    public class StoriesEntity {
        /**
         * images : ["https://pic3.zhimg.com/v2-308b2df836368f33e7a31442ae27e4ba.jpg"]
         * ga_prefix : 112622
         * id : 9702392
         * type : 0
         * title : 小事 · 你有哪些秘密一直深埋心底？
         */
        private List<String> images;
        private String ga_prefix;
        public int id;
        private int type;
        private String title;

        public void setImages(List<String> images) {
            this.images = images;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public int getId() {
            return id;
        }

        public int getType() {
            return type;
        }

        public String getTitle() {
            return title;
        }
    }
}
