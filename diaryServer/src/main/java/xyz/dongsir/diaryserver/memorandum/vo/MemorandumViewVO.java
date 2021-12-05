package xyz.dongsir.diaryserver.memorandum.vo;

import java.io.Serializable;
import java.util.List;

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
 * 2021/11/24 16:20     dongxingyu        2.0.0       To create
 * </p>
 */
public class MemorandumViewVO implements Serializable {
    private String date;

    private List<MemorandumListVO> memorandumListVOList ;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<MemorandumListVO> getMemorandumListVOList() {
        return memorandumListVOList;
    }

    public void setMemorandumListVOList(List<MemorandumListVO> memorandumListVOList) {
        this.memorandumListVOList = memorandumListVOList;
    }
}