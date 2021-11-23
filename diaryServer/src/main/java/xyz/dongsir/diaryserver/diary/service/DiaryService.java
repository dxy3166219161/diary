package xyz.dongsir.diaryserver.diary.service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.dongsir.diaryserver.constants.CoreDataConstant;
import xyz.dongsir.diaryserver.constants.DiaryConstant;
import xyz.dongsir.diaryserver.core.bean.DiaryCore;
import xyz.dongsir.diaryserver.core.model.DiaryCoreTreeModel;
import xyz.dongsir.diaryserver.core.service.CoreService;
import xyz.dongsir.diaryserver.diary.vo.DiaryListViewVO;
import xyz.dongsir.diaryserver.diary.vo.DiaryOptionVO;
import xyz.dongsir.diaryserver.diary.vo.DiaryUpdateOptionVO;
import xyz.dongsir.diaryserver.util.DateUtils;
import xyz.dongsir.diaryserver.util.rest.ResponseMsg;
import xyz.dongsir.diaryserver.util.rest.ResultMsg;

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
 * 2021/11/19 1:33     dongxingyu        2.0.0       To create
 * </p>
 */
@Service
public class DiaryService {
    @Autowired
    CoreService coreService;

    /**
     * 创建日记
     * @param diaryOptionVO
     * @return
     */
    public ResultMsg<String> addDiary(DiaryOptionVO diaryOptionVO) {
        // 截取年、月
        String year = DateUtils.getYear(diaryOptionVO.getDateDiary()) + "年";
        String month = DateUtils.getMonth(diaryOptionVO.getDateDiary()) + "月";
        String day = DateUtils.getDay(diaryOptionVO.getDateDiary()) + "日";
        String diaryTitle = DateUtils.formatDate(diaryOptionVO.getDateDiary(),DateUtils.DATE_DATE_FORMAT);
        // 查询是否数据库中已有当前的年、月。没有的进行创建
        List<DiaryCore> coreByYear = coreService.findCoreByUidAndTitleAndType(CoreDataConstant.CORE_DATA_PARENT_FOOT_ID_DIRAY,month, DiaryConstant.DIARY_TYPE_YEAR);
        if(coreByYear.isEmpty()){
            // 创建年
            String yearUid = createYear(year);
            // 创建月
            String monthUid = createMonth(month, yearUid);
            // 创建日记
            createDiary(diaryTitle,monthUid);
        }else{
            DiaryCore yearCore = coreByYear.get(0);
            List<DiaryCore> coreByMouth = coreService.findCoreByUidAndTitleAndType(yearCore.getUid(),month, DiaryConstant.DIARY_TYPE_MONTH);
            if(coreByMouth.isEmpty()){
                // 创建月
                String monthUid = createMonth(month, yearCore.getUid());
                // 创建日记
                createDiary(diaryTitle,monthUid);
            }else{
                DiaryCore monthCore = coreByMouth.get(0);
                // 创建日记
                createDiary(diaryTitle,monthCore.getUid());
            }
        }
        return ResponseMsg.setSuccessResult();
    }

    /**
     * 创建年份
     * @param year
     * @return
     */
    private String createYear(String year){
        DiaryCore yearCore  = new DiaryCore();
        yearCore.setParentId(CoreDataConstant.CORE_DATA_PARENT_FOOT_ID_DIRAY);
        yearCore.setType(DiaryConstant.DIARY_TYPE_YEAR);
        yearCore.setTitle(year);
        String yearUid = coreService.addCore(yearCore);
        return yearUid;
    }

    /**
     * 创建月份
     * @param month
     * @param uid
     * @return
     */
    private String createMonth(String month,String uid){
        DiaryCore monthCore  = new DiaryCore();
        monthCore.setParentId(uid);
        monthCore.setType(DiaryConstant.DIARY_TYPE_MONTH);
        monthCore.setTitle(month);
        String monthUid = coreService.addCore(monthCore);
        return monthUid;
    }

    /**
     * 创建日记
     * @param diaryTitle
     * @param uid
     * @return
     */
    private String createDiary(String diaryTitle,String uid){
        DiaryCore diary  = new DiaryCore();
        diary.setParentId(uid);
        diary.setType(DiaryConstant.DIARY_TYPE_DIARY);
        diary.setTitle(diaryTitle);
        String diaryUid = coreService.addCore(diary);
        return diaryUid;
    }

    /**
     * 修改日记标题、内容、摘要通过id
     * @param diaryUpdateOptionVO
     * @return
     */
    public ResultMsg<String> updateDiary(DiaryUpdateOptionVO diaryUpdateOptionVO) {
        DiaryCore diary  = coreService.findByUid(diaryUpdateOptionVO.getUid());
        diary.setTitle(diaryUpdateOptionVO.getTitle());
        diary.setSummary(diaryUpdateOptionVO.getSummary());
        diary.setDetail(diaryUpdateOptionVO.getDetail());
        coreService.updateCore(diary);
        return ResponseMsg.setSuccessResult();
    }

    /**
     * 日记移入回收站
     * @param uid
     * @return
     */
    public ResultMsg<String> moveTrash(String uid) {
        DiaryCore diary  = coreService.findByUid(uid);
        coreService.moveToTrash(diary.getId(),CoreDataConstant.CORE_DATA_PARENT_FOOT_ID_DIRAY_RECYCLE);
        return ResponseMsg.setSuccessResult();
    }

    /**
     * 清空回收站
     * void
     * @return
     */
    public ResultMsg<String> clearTrash() {
        coreService.clearRecycleBin(CoreDataConstant.CORE_DATA_PARENT_FOOT_ID_DIRAY_RECYCLE);
        return ResponseMsg.setSuccessResult();
    }

    /**
     * 删除日记
     * @param uid
     * @return
     */
    public ResultMsg<String> deleteDiary(String uid) {
        DiaryCore diary  = coreService.findByUid(uid);
        try {
            coreService.deleteCore(diary.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseMsg.setSuccessResult();
    }

    /**
     * 查询日记树列表
     * @return
     */
    public ResultMsg<DiaryCoreTreeModel> list() {
        DiaryCoreTreeModel coreTree = coreService.findCoreTree(CoreDataConstant.CORE_DATA_PARENT_FOOT_ID_DIRAY);
        return ResponseMsg.setSuccessResult(coreTree);
    }


    /** 
     * @description: 查询子节点列表 
     * @param: [uid] 
     * @return: xyz.dongsir.diaryserver.util.rest.ResultMsg<java.util.List<xyz.dongsir.diaryserver.diary.vo.DiaryListViewVO>> 
     * @author dongxingyu
     * @date: 2021/11/22 9:55
     */ 
    public ResultMsg<List<DiaryListViewVO>> findChildrenList(String uid) {
        // TODO 默认root，用户登录模块搭建后补充
        String userAccount = "root";
        List<DiaryCore> diaryCoreList = coreService.findByParentIdAndUserAccount(uid, userAccount);
        List<DiaryListViewVO> diaryListViewVOList = new ArrayList<>();
        diaryCoreList.forEach(diaryCore -> {
            DiaryListViewVO diaryListViewVO = new DiaryListViewVO();
            diaryListViewVO.setUid(diaryCore.getUid());
            diaryListViewVO.setParentId(diaryCore.getParentId());
            diaryListViewVO.setTitle(diaryCore.getTitle());
            diaryListViewVO.setType(diaryCore.getType());
            diaryListViewVO.setSummary(diaryCore.getSummary());
            diaryListViewVO.setCreateDate(diaryCore.getCreateDate());
            diaryListViewVOList.add(diaryListViewVO);
        });
        return ResponseMsg.setSuccessResult(diaryListViewVOList);
    }
}