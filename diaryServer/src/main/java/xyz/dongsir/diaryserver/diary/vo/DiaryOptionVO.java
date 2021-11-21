package xyz.dongsir.diaryserver.diary.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 *
 * @author dongxingyu
 * @version 2.0.0
 *
 * <p>
 * History:
 * Date                Author         Version     Description
 * --------------------------------------------------------------------
 * 2021/11/19 2:02     dongxingyu        2.0.0       To create
 * </p>
 */
public class DiaryOptionVO implements Serializable {

    @ApiModelProperty("日记是那一天的")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date dateDiary;

    public Date getDateDiary() {
        return dateDiary;
    }

    public void setDateDiary(Date dateDiary) {
        this.dateDiary = dateDiary;
    }
}