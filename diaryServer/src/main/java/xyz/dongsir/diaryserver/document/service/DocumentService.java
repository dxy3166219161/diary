package xyz.dongsir.diaryserver.document.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.dongsir.diaryserver.constants.CoreDataConstant;
import xyz.dongsir.diaryserver.constants.DocumentConstant;
import xyz.dongsir.diaryserver.core.bean.DiaryCore;
import xyz.dongsir.diaryserver.core.model.DiaryCoreTreeModel;
import xyz.dongsir.diaryserver.core.service.CoreService;
import xyz.dongsir.diaryserver.diary.vo.DiaryListViewVO;
import xyz.dongsir.diaryserver.document.vo.DocumentAddVO;
import xyz.dongsir.diaryserver.document.vo.DocumentListViewVO;
import xyz.dongsir.diaryserver.document.vo.DocumentUpdateVO;
import xyz.dongsir.diaryserver.util.rest.ResponseMsg;
import xyz.dongsir.diaryserver.util.rest.ResultMsg;

/**
 * Description: 文档服务
 *
 * @author dongxingyu
 * @version 2.0.0
 *
 * <p>
 * History:
 * Date                Author         Version     Description
 * --------------------------------------------------------------------
 * 2021/11/23 18:09     dongxingyu        2.0.0       To create
 * </p>
 */
@Service
public class DocumentService {
    @Autowired
    CoreService coreService;

    /**
     * @description: 创建文件夹
     * @param: [documentAddVO]
     * @return: xyz.dongsir.diaryserver.util.rest.ResultMsg<java.lang.String>
     * @author dongxingyu
     * @date: 2021/11/23 18:13
     */
    public ResultMsg<String> createFolder(DocumentAddVO documentAddVO) {
        DiaryCore diaryCore = new DiaryCore();
        if(StringUtils.isBlank(documentAddVO.getParentId())){
            documentAddVO.setParentId(CoreDataConstant.CORE_DATA_PARENT_FOOT_ID_DOCUMENT);
        }else{
            // 校验父级id 父级必须是文件夹，或者是文件根
            DiaryCore parentDiaryCore = coreService.findByUid(documentAddVO.getParentId());
            if(DocumentConstant.DOCUMENT_TYPE_DOCUMENT.equals(parentDiaryCore.getType())){
                return ResponseMsg.setErrorResult("不允许在文件下创建文件夹");
            }
        }
        diaryCore.setParentId(documentAddVO.getParentId());
        diaryCore.setType(DocumentConstant.DOCUMENT_TYPE_FOLDER);
        diaryCore.setTitle(documentAddVO.getTitle());
        coreService.addCore(diaryCore);
        return ResponseMsg.setSuccessResult();
    }

    /** 
     * @description: 创建文档 
     * @param: [documentAddVO] 
     * @return: xyz.dongsir.diaryserver.util.rest.ResultMsg<java.lang.String> 
     * @author dongxingyu
     * @date: 2021/11/23 20:44
     */ 
    public ResultMsg<String> createDocument(DocumentAddVO documentAddVO) {
        DiaryCore diaryCore = new DiaryCore();
        if(StringUtils.isBlank(documentAddVO.getParentId())){
            documentAddVO.setParentId(CoreDataConstant.CORE_DATA_PARENT_FOOT_ID_DOCUMENT);
        }else {
            // 校验父级id 父级必须是文件夹，或者是文件根
            DiaryCore parentDiaryCore = coreService.findByUid(documentAddVO.getParentId());
            if (DocumentConstant.DOCUMENT_TYPE_DOCUMENT.equals(parentDiaryCore.getType())) {
                return ResponseMsg.setErrorResult("不允许在文件下创建文件");
            }
        }
        diaryCore.setParentId(documentAddVO.getParentId());
        diaryCore.setType(DocumentConstant.DOCUMENT_TYPE_DOCUMENT);
        diaryCore.setTitle(documentAddVO.getTitle());
        coreService.addCore(diaryCore);
        return ResponseMsg.setSuccessResult();
    }

    /** 
     * @description: 重命名 
     * @param: [documentUpdateVO] 
     * @return: xyz.dongsir.diaryserver.util.rest.ResultMsg<java.lang.String> 
     * @author dongxingyu
     * @date: 2021/11/23 20:48
     */ 
    public ResultMsg<String> renameDocument(DocumentUpdateVO documentUpdateVO) {
        DiaryCore diaryCore = coreService.getById(documentUpdateVO.getId());
        diaryCore.setTitle(documentUpdateVO.getTitle());
        coreService.updateCore(diaryCore);
        return ResponseMsg.setSuccessResult();
    }

    /**
     * @description: 移动文件/文件夹
     * @param: [documentUpdateVO]
     * @return: xyz.dongsir.diaryserver.util.rest.ResultMsg<java.lang.String>
     * @author dongxingyu
     * @date: 2021/11/23 20:56
     */
    public ResultMsg<String> moveDocument(DocumentUpdateVO documentUpdateVO) {
        DiaryCore diaryCore = coreService.getById(documentUpdateVO.getId());
        String type = diaryCore.getType();
        DiaryCore diaryCoreParent = coreService.findByUid(documentUpdateVO.getParentId());
        // 判断要移动的是文件夹还是文件
        if(DocumentConstant.DOCUMENT_TYPE_FOLDER.equals(type)){
            // 如果是文件夹，判断移动的目标是否是文件
            if(DocumentConstant.DOCUMENT_TYPE_DOCUMENT.equals(diaryCoreParent.getType())){
                return ResponseMsg.setErrorResult("不允许将文件夹移动到文件下");
            }
            diaryCore.setParentId(documentUpdateVO.getParentId());
        }else if(DocumentConstant.DOCUMENT_TYPE_DOCUMENT.equals(type)){
            // 如果是文件，判断移动的目标是否是文件
            if(DocumentConstant.DOCUMENT_TYPE_DOCUMENT.equals(diaryCoreParent.getType())){
                return ResponseMsg.setErrorResult("不允许将文件移动到文件下");
            }
            diaryCore.setParentId(documentUpdateVO.getParentId());
        }
        coreService.updateCore(diaryCore);
        return ResponseMsg.setSuccessResult();
    }

    /**
     * @description: 移入回收站
     * @param: [id]
     * @return: xyz.dongsir.diaryserver.util.rest.ResultMsg<java.lang.String>
     * @author dongxingyu
     * @date: 2021/11/23 21:05
     */
    public ResultMsg<String> moveTrashDocument(Long id) {
        coreService.moveToTrash(id,CoreDataConstant.CORE_DATA_PARENT_FOOT_ID_DOCUMENT_RECYCLE);
        return ResponseMsg.setSuccessResult();
    }

    /**
     * @description: 修改文档标题及内容
     * @param: [documentUpdateVO]
     * @return: xyz.dongsir.diaryserver.util.rest.ResultMsg<java.lang.String>
     * @author dongxingyu
     * @date: 2021/11/24 9:54
     */
    public ResultMsg<String> updateDocument(DocumentUpdateVO documentUpdateVO) {
        DiaryCore diaryCore = coreService.getById(documentUpdateVO.getId());
        if(!DocumentConstant.DOCUMENT_TYPE_DOCUMENT.equals(diaryCore.getType())){
            return ResponseMsg.setErrorResult("只允许修改文件");
        }
        diaryCore.setTitle(documentUpdateVO.getTitle());
        diaryCore.setDetail(documentUpdateVO.getDetail());
        coreService.updateCore(diaryCore);
        return ResponseMsg.setSuccessResult();
    }

    /**
     * @description: 清空回收站
     * @param: []
     * @return: xyz.dongsir.diaryserver.util.rest.ResultMsg<java.lang.String>
     * @author dongxingyu
     * @date: 2021/11/24 9:57
     */
    public ResultMsg<String> clearTrash() {
        coreService.clearRecycleBin(CoreDataConstant.CORE_DATA_PARENT_FOOT_ID_DOCUMENT_RECYCLE);
        return ResponseMsg.setSuccessResult();
    }

    /**
     * @description: 删除文件/文件夹
     * @param: [id]
     * @return: xyz.dongsir.diaryserver.util.rest.ResultMsg<java.lang.String>
     * @author dongxingyu
     * @date: 2021/11/24 10:13
     */
    public ResultMsg<String> deleteDocument(Long id) {
        DiaryCore diaryCore = coreService.getById(id);
        // 递归查询其所有子节点
        List<DiaryCore> diaryCoreList = getAllChildrenDocument(diaryCore.getUid());
        diaryCoreList.add(diaryCore);
        List<Long> idList = new ArrayList<>();
        diaryCoreList.forEach(item -> {
            idList.add(item.getId());
        });
        coreService.removeByIds(idList);
        return ResponseMsg.setSuccessResult();
    }

    /**
     * @description: 递归获取所有子节点并返回List
     * @param: [uid]
     * @return: java.util.List<xyz.dongsir.diaryserver.core.bean.DiaryCore>
     * @author dongxingyu
     * @date: 2021/11/24 10:13
     */
    private List<DiaryCore> getAllChildrenDocument(String uid){
        // TODO 集成用户登录模块后置入
        String userAccount = "root";
        List<DiaryCore> diaryCoreList = coreService.findByParentIdAndUserAccount(uid,userAccount);
        List<DiaryCore> diaryCoreChildrenList = new ArrayList<>();
        if(!diaryCoreList.isEmpty()){
            for (DiaryCore diaryCore : diaryCoreList) {
                List<DiaryCore> allChildren = getAllChildrenDocument(diaryCore.getUid());
                diaryCoreChildrenList.addAll(allChildren);
            }
            diaryCoreList.addAll(diaryCoreChildrenList);
        }
        return diaryCoreList;
    }

    /** 
     * @description: 获取文件树
     * @param: [] 
     * @return: xyz.dongsir.diaryserver.util.rest.ResultMsg<xyz.dongsir.diaryserver.core.model.DiaryCoreTreeModel> 
     * @author dongxingyu
     * @date: 2021/11/24 10:18
     */ 
    public ResultMsg<DiaryCoreTreeModel> list() {
        DiaryCoreTreeModel coreTree = coreService.findCoreTree(CoreDataConstant.CORE_DATA_PARENT_FOOT_ID_DOCUMENT);
        return ResponseMsg.setSuccessResult(coreTree);
    }

    /**
     * @description: 获取子节点列表
     * @param: [uid]
     * @return: xyz.dongsir.diaryserver.util.rest.ResultMsg<java.util.List<xyz.dongsir.diaryserver.document.vo.DocumentListViewVO>>
     * @author dongxingyu
     * @date: 2021/11/24 10:23
     */
    public ResultMsg<List<DocumentListViewVO>> findChildrenList(String uid) {
        // TODO 默认root，用户登录模块搭建后补充
        String userAccount = "root";
        List<DiaryCore> diaryCoreList = coreService.findByParentIdAndUserAccount(uid, userAccount);
        List<DocumentListViewVO> documentListViewVOArrayList = new ArrayList<>();
        diaryCoreList.forEach(diaryCore -> {
            DocumentListViewVO documentListViewVO = new DocumentListViewVO();
            documentListViewVO.setUid(diaryCore.getUid());
            documentListViewVO.setParentId(diaryCore.getParentId());
            documentListViewVO.setTitle(diaryCore.getTitle());
            documentListViewVO.setType(diaryCore.getType());
            documentListViewVO.setSummary(diaryCore.getSummary());
            documentListViewVO.setCreateDate(diaryCore.getCreateDate());
            documentListViewVOArrayList.add(documentListViewVO);
        });
        return ResponseMsg.setSuccessResult(documentListViewVOArrayList);
    }
}