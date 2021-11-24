package xyz.dongsir.diaryserver.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import xyz.dongsir.diaryserver.core.bean.DiaryCore;
import xyz.dongsir.diaryserver.core.model.DiaryCoreTreeModel;

import java.util.Date;
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
 * 2021/11/17 9:32     dongxingyu        2.0.0       To create
 * </p>
 */
@Service
public interface CoreService extends IService<DiaryCore> {
    // TODO 关于列表的查询需要各个不同的系统进行组合，重新查询

    /**
     * @description: 新增核心
     * @param: [diaryCore]
     * @return: String
     * @author dongxingyu
     * @date: 2021/11/18 9:22
     */
    String addCore(DiaryCore diaryCore);

    /**
     * @description: 修改核心数据
     * @param: [diaryCore]
     * @return: void
     * @author dongxingyu
     * @date: 2021/11/18 9:22
     */
    void updateCore(DiaryCore diaryCore);

    /**
     * @description: 移入回收站
     * @param: [id, recycleBinId]
     * @return: void
     * @author dongxingyu
     * @date: 2021/11/18 9:29
     */
    void moveToTrash(Long id , String recycleBinId);

    /**
     * @description: 移动
     * @param: [id, purposeId]
     * @return: void
     * @author dongxingyu
     * @date: 2021/11/18 9:30
     */
    void moveCore(Long id,String purposeId);

    /** 
     * @description: 删除核心 
     * @param: [id] 
     * @return: void 
     * @author dongxingyu
     * @date: 2021/11/18 9:36
     */ 
    void deleteCore(Long id) throws Exception;
    
    /** 
     * @description: 清空回收站 
     * @param: [purposeId] 
     * @return: void 
     * @author dongxingyu
     * @date: 2021/11/18 9:37
     */ 
    void clearRecycleBin(String purposeId);

    /** 
     * @description: 获取核心树 
     * @param: [parentId] 
     * @return: java.util.List<xyz.dongsir.diaryserver.core.model.DiaryCoreTreeModel> 
     * @author dongxingyu
     * @date: 2021/11/18 10:10
     */ 
    DiaryCoreTreeModel findCoreTree(String parentId);

    /**
     * 根绝标题、类型查询核心
     * @param title
     * @param type
     * @return
     */
    List<DiaryCore> findCoreByUidAndTitleAndType(String uid,String title,String type);

    /**
     * 查询日记通过uid
     * @param uid
     * @return
     */
    DiaryCore findByUid(String uid);

    /**
     * 通过父节点和用户查询列表
     * @param purposeId
     * @param userAccount
     * @return
     */
    List<DiaryCore> findByParentIdAndUserAccount(String purposeId, String userAccount);

    List<DiaryCore> findByUserAccountAndTypeAndDate(String userAccount,String type, Date startDate, Date endDate);
}