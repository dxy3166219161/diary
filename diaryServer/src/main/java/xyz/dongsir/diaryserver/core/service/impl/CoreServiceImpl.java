/*
 * FileName: CoreServiceImpl
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2020 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package xyz.dongsir.diaryserver.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.dongsir.diaryserver.constants.CoreDataConstant;
import xyz.dongsir.diaryserver.constants.GlobalConstant;
import xyz.dongsir.diaryserver.core.bean.DiaryCore;
import xyz.dongsir.diaryserver.core.mapper.DiaryCoreMapper;
import xyz.dongsir.diaryserver.core.model.DiaryCoreTreeModel;
import xyz.dongsir.diaryserver.core.service.CoreService;
import xyz.dongsir.diaryserver.util.UUIDUtil;

import java.util.ArrayList;
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
 * 2021/11/17 10:03     dongxingyu        2.0.0       To create
 * </p>
 */
@Service
public class CoreServiceImpl extends ServiceImpl<DiaryCoreMapper, DiaryCore> implements CoreService {
    @Autowired
    DiaryCoreMapper diaryCoreMapper;

    /**
     * @description: 创建核心文档
     * @param: [diaryCore]
     * @return: void
     * @author dongxingyu
     * @date: 2021/11/16 10:20
     */
    @Override
    public void addCore(DiaryCore diaryCore){
        String uuid = UUIDUtil.getUUID();
        diaryCore.setUid(uuid);
        // TODO 后续登录加入后补充逻辑，目前默认root
        diaryCore.setUserAccount("root");
        diaryCore.setUserName("root");
        diaryCore.setCreateDate(new Date());
        diaryCore.setStatus(GlobalConstant.STATUS_Y);
        // 存入数据库
        diaryCoreMapper.insert(diaryCore);
    }

    /**
     * @description: 修改核心文档
     * @param: [diaryCore]
     * @return: void
     * @author dongxingyu
     * @date: 2021/11/17 9:39
     */
    @Override
    public void updateCore(DiaryCore diaryCore){
        DiaryCore updateCoreBean = new DiaryCore();
        updateCoreBean.setParentId(diaryCore.getParentId());
        updateCoreBean.setUpdateDate(new Date());
        updateCoreBean.setSummary(diaryCore.getSummary());
        updateCoreBean.setDetail(diaryCore.getDetail());
        updateCoreBean.setTitle(diaryCore.getTitle());
        updateById(updateCoreBean);
    }

    /** 
     * @description: 移入回收站
     * @param: [id, recycleBinId] 
     * @return: void 
     * @author dongxingyu
     * @date: 2021/11/18 9:29
     */ 
    @Override
    public void moveToTrash(Long id, String recycleBinId) {
        DiaryCore diaryCore = diaryCoreMapper.selectById(id);
        if(null != diaryCore){
            diaryCore.setStatus(GlobalConstant.STATUS_N);
            diaryCore.setParentId(recycleBinId);
            updateById(diaryCore);
        }
    }

    /** 
     * @description: 移动核心数据位置 
     * @param: [id, purposeId] 
     * @return: void 
     * @author dongxingyu
     * @date: 2021/11/18 9:31
     */ 
    @Override
    public void moveCore(Long id, String purposeId) {
        DiaryCore diaryCore = diaryCoreMapper.selectById(id);
        if(null != diaryCore){
            diaryCore.setParentId(purposeId);
            updateById(diaryCore);
        }
    }

    /** 
     * @description: 根据id删除核心数据 
     * @param: [id] 
     * @return: void 
     * @author dongxingyu
     * @date: 2021/11/18 9:56
     */ 
    @Override
    public void deleteCore(Long id) throws Exception {
        DiaryCore diaryCore = diaryCoreMapper.selectById(id);
        if(null != diaryCore){
            diaryCoreMapper.deleteById(diaryCore.getId());
        }else{
            throw new Exception("预删除的数据不存在");
        }
    }

    /** 
     * @description: 清空回收站
     * @param: [purposeId] 
     * @return: void 
     * @author dongxingyu
     * @date: 2021/11/18 9:56
     */ 
    @Override
    public void clearRecycleBin(String purposeId) {
        // TODO 集成登录后补充用户账号
        String userAccount = "";
        List<DiaryCore> diaryCoreList = diaryCoreMapper.selectByParentIdAndUserAccount(purposeId,userAccount);
        List<Long> idList = new ArrayList<>();
        diaryCoreList.forEach(diaryCore -> {
            idList.add(diaryCore.getId());
        });
        diaryCoreMapper.deleteBatchIds(idList);
    }

    /** 
     * @description: 获取核心树
     * @param: [parentId] 
     * @return: xyz.dongsir.diaryserver.core.model.DiaryCoreTreeModel 
     * @author dongxingyu
     * @date: 2021/11/18 10:22
     */ 
    @Override
    public DiaryCoreTreeModel findCoreTree(String parentId) {
        // TODO 集成登录后补充用户账号
        String userAccount = "";
        DiaryCore diaryCore = diaryCoreMapper.selectByUidAndUserAccount(parentId,userAccount);
        DiaryCoreTreeModel diaryCoreTreeModel = new DiaryCoreTreeModel();
        diaryCoreTreeModel.setId(diaryCore.getId());
        diaryCoreTreeModel.setUid(diaryCore.getUid());
        diaryCoreTreeModel.setParentId(diaryCore.getParentId());
        diaryCoreTreeModel.setTitle(diaryCore.getTitle());
        diaryCoreTreeModel.setType(diaryCore.getType());
        if(null != diaryCore){
            List<DiaryCoreTreeModel> diaryCoreTreeModelList = recursionCoreTree(parentId);
            diaryCoreTreeModel.setChildren(diaryCoreTreeModelList);
        }
        return diaryCoreTreeModel;
    }

    /**
     * @description: 递归核心树
     * @param: [parentId]
     * @return: java.util.List<xyz.dongsir.diaryserver.core.model.DiaryCoreTreeModel>
     * @author dongxingyu
     * @date: 2021/11/18 10:11
     */
    private List<DiaryCoreTreeModel> recursionCoreTree(String parentId){
        List<DiaryCore> diaryCoreList = diaryCoreMapper.selectByParentId(parentId);
        if(null != diaryCoreList && !diaryCoreList.isEmpty()){
            List<DiaryCoreTreeModel> diaryCoreTreeModelList = new ArrayList<>();
            for (DiaryCore diaryCore : diaryCoreList) {
                List<DiaryCoreTreeModel> childRenList = new ArrayList<>();
                DiaryCoreTreeModel diaryCoreTreeModel = new DiaryCoreTreeModel();
                diaryCoreTreeModel.setId(diaryCore.getId());
                diaryCoreTreeModel.setUid(diaryCore.getUid());
                diaryCoreTreeModel.setParentId(diaryCore.getParentId());
                diaryCoreTreeModel.setTitle(diaryCore.getTitle());
                diaryCoreTreeModel.setType(diaryCore.getType());
                childRenList = recursionCoreTree(diaryCore.getUid());
                diaryCoreTreeModel.setChildren(childRenList);
                diaryCoreTreeModelList.add(diaryCoreTreeModel);
            }
            return diaryCoreTreeModelList;
        }
        return null;
    }
}