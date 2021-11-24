package xyz.dongsir.diaryserver.memorandum.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.dongsir.diaryserver.constants.CoreDataConstant;
import xyz.dongsir.diaryserver.constants.MemorandumConstant;
import xyz.dongsir.diaryserver.core.bean.DiaryCore;
import xyz.dongsir.diaryserver.core.service.CoreService;
import xyz.dongsir.diaryserver.memorandum.vo.MemorandumListVO;
import xyz.dongsir.diaryserver.memorandum.vo.MemorandumOptionVO;
import xyz.dongsir.diaryserver.memorandum.vo.MemorandumSearchVO;
import xyz.dongsir.diaryserver.memorandum.vo.MemorandumViewVO;
import xyz.dongsir.diaryserver.util.DateUtils;
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
 * 2021/11/24 15:35     dongxingyu        2.0.0       To create
 * </p>
 */
@Service
public class MemorandumService {
    @Autowired
    CoreService coreService;

    /**
     * @description: 创建备忘录
     * @param: [memorandumOptionVO]
     * @return: xyz.dongsir.diaryserver.util.rest.ResultMsg<java.lang.String>
     * @author dongxingyu
     * @date: 2021/11/24 15:51
     */
    public ResultMsg<String> addMemorandum(MemorandumOptionVO memorandumOptionVO) {
        DiaryCore diaryCore = new DiaryCore();
        diaryCore.setParentId(CoreDataConstant.CORE_DATA_PARENT_FOOT_ID_MEMORANDUM);
        diaryCore.setSummary(DateUtils.formatDate(memorandumOptionVO.getDate(),DateUtils.DATE_DATE_FORMAT));
        diaryCore.setDetail(memorandumOptionVO.getDesc());
        diaryCore.setType(MemorandumConstant.MEMORANDUM_TYPE_MEMORANDUM);
        diaryCore.setTitle(memorandumOptionVO.getTitle());
        coreService.save(diaryCore);
        return ResponseMsg.setSuccessResult();
    }

    /**
     * @description: 修改备忘录
     * @param: [memorandumOptionVO]
     * @return: xyz.dongsir.diaryserver.util.rest.ResultMsg<java.lang.String>
     * @author dongxingyu
     * @date: 2021/11/24 15:54
     */
    public ResultMsg<String> updateMemorandum(MemorandumOptionVO memorandumOptionVO) {
        DiaryCore diaryCore = coreService.getById(memorandumOptionVO.getId());
        diaryCore.setSummary(DateUtils.formatDate(memorandumOptionVO.getDate(),DateUtils.DATE_DATE_FORMAT));
        diaryCore.setDetail(memorandumOptionVO.getDesc());
        diaryCore.setTitle(memorandumOptionVO.getTitle());
        coreService.updateCore(diaryCore);
        return ResponseMsg.setSuccessResult();
    }

    /**
     * @description: 删除备忘录
     * @param: [id]
     * @return: xyz.dongsir.diaryserver.util.rest.ResultMsg<java.lang.String>
     * @author dongxingyu
     * @date: 2021/11/24 15:56
     */
    public ResultMsg<String> deleteMemorandum(Long id) {
        coreService.removeById(id);
        return ResponseMsg.setSuccessResult();
    }

    /**
     * @description: 查看列表：日历视图
     * @param: [memorandumSearchVO]
     * @return: xyz.dongsir.diaryserver.util.rest.ResultMsg<java.util.List<xyz.dongsir.diaryserver.memorandum.vo.MemorandumViewVO>>
     * @author dongxingyu
     * @date: 2021/11/24 16:36
     */
    public ResultMsg<List<MemorandumViewVO>> calendarList(MemorandumSearchVO memorandumSearchVO) {
        // TODO 模拟用户
        String userAccount = "root";
        List<DiaryCore> diaryCoreList = coreService.findByUserAccountAndTypeAndDate(userAccount,MemorandumConstant.MEMORANDUM_TYPE_MEMORANDUM,memorandumSearchVO.getStartDate(),memorandumSearchVO.getEndDate());
        List<MemorandumListVO> memorandumListVOList = new ArrayList<>();
        diaryCoreList.forEach(diaryCore -> {
            MemorandumListVO memorandumListVO = new MemorandumListVO();
            BeanUtils.copyProperties(diaryCore,memorandumListVO);
            memorandumListVOList.add(memorandumListVO);
        });
        Map<String, List<MemorandumListVO>> collect = memorandumListVOList.stream().collect(Collectors.groupingBy(MemorandumListVO::getSummary));
        List<MemorandumViewVO> memorandumViewVOList = new ArrayList<>();
        collect.forEach((key,value) -> {
            MemorandumViewVO memorandumViewVO = new MemorandumViewVO();
            memorandumViewVO.setDate(key);
            memorandumViewVO.setMemorandumListVOList(value);
            memorandumViewVOList.add(memorandumViewVO);
        });
        return ResponseMsg.setSuccessResult(memorandumViewVOList);
    }

    /**
     * @description:  查看备忘录列表
     * @param: []
     * @return: xyz.dongsir.diaryserver.util.rest.ResultMsg<java.util.List<xyz.dongsir.diaryserver.memorandum.vo.MemorandumListVO>>
     * @author dongxingyu
     * @date: 2021/11/24 16:39
     */
    public ResultMsg<List<MemorandumListVO>> memorandumList() {
        // TODO 模拟用户
        String userAccount = "root";
        List<DiaryCore> diaryCoreList = coreService.findByParentIdAndUserAccount(CoreDataConstant.CORE_DATA_PARENT_FOOT_ID_MEMORANDUM, userAccount);
        List<MemorandumListVO> memorandumListVOList = new ArrayList<>();
        diaryCoreList.forEach(diaryCore -> {
            MemorandumListVO memorandumListVO = new MemorandumListVO();
            BeanUtils.copyProperties(diaryCore,memorandumListVO);
            memorandumListVOList.add(memorandumListVO);
        });
        return ResponseMsg.setSuccessResult(memorandumListVOList);
    }
}