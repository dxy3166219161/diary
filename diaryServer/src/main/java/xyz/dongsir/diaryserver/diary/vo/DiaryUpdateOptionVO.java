package xyz.dongsir.diaryserver.diary.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class DiaryUpdateOptionVO implements Serializable {

    @ApiModelProperty("uid_")
    private String uid;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("文档摘要 前端对文本内容进行截断")
    private String summary;

    @ApiModelProperty("详情：存储Json")
    private String detail;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
