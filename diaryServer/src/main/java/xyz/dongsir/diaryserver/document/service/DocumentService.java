/*
 * FileName: DocumentService
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2020 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package xyz.dongsir.diaryserver.document.service;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.dongsir.diaryserver.constants.CoreDataConstant;
import xyz.dongsir.diaryserver.constants.DocumentConstant;
import xyz.dongsir.diaryserver.core.bean.DiaryCore;
import xyz.dongsir.diaryserver.core.service.CoreService;
import xyz.dongsir.diaryserver.document.vo.DocumentAddVO;
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
}