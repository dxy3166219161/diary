package xyz.dongsir.diaryserver.diary.service;

import org.springframework.stereotype.Service;
import xyz.dongsir.diaryserver.diary.vo.DiaryOptionVO;
import xyz.dongsir.diaryserver.util.rest.ResponseMsg;
import xyz.dongsir.diaryserver.util.rest.ResultMsg;

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
 * 2021/11/19 1:33     dongxingyu        2.0.0       To create
 * </p>
 */
@Service
public class DiaryService {
    public ResultMsg<String> addDiary(DiaryOptionVO diaryOptionVO) {
        return ResponseMsg.setSuccessResult();
    }
}