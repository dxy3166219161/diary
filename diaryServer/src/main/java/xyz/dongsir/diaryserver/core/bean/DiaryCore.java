package xyz.dongsir.diaryserver.core.bean;

import com.baomidou.mybatisplus.annotation.*;
import jdk.nashorn.internal.runtime.GlobalConstants;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Description: 日记核心
 *
 * @author dongxingyu
 * @version 2.0.0
 *
 * <p>
 * History:
 * Date                Author         Version     Description
 * --------------------------------------------------------------------
 * 2021/11/15 10:48     dongxingyu        2.0.0       To create
 * </p>
 */
@Getter
@Setter
//@KeySequence(value = GlobalConstants.KEY_SEQUENCE, dbType = DbType.ORACLE)
@TableName("etl_process_elite")
public class DiaryCore {
    /** 属性名：主键 **/
    @TableId(value = "id_", type = IdType.INPUT)
    private Long id;

    /** 属性名：被加精工单id **/
    @TableField("process_id")
    private Long processId;

    /** 属性名：加精后知识id **/
    @TableField("knowledge_id")
    private Long knowledgeId;

    /** 属性名：操作人账号 **/
    @TableField("operator_account")
    private String operatorAccount;

    /** 属性名：操作人姓名 **/
    @TableField("operator_name")
    private String operatorName;

    /** 属性名：操作时间 **/
    @TableField("operator_time")
    private Date operatorTime;

    /** 属性名：状态 **/
    @TableField("status_")
    private Integer status;
}