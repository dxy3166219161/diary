/*
 * FileName: DocumentController
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2020 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package xyz.dongsir.diaryserver.document.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.dongsir.diaryserver.diary.vo.DiaryOptionVO;
import xyz.dongsir.diaryserver.document.service.DocumentService;
import xyz.dongsir.diaryserver.document.vo.DocumentAddVO;
import xyz.dongsir.diaryserver.document.vo.DocumentUpdateVO;
import xyz.dongsir.diaryserver.util.rest.ResultMsg;

/**
 * Description: 文档控制层
 *
 * @author dongxingyu
 * @version 2.0.0
 *
 * <p>
 * History:
 * Date                Author         Version     Description
 * --------------------------------------------------------------------
 * 2021/11/23 18:08     dongxingyu        2.0.0       To create
 * </p>
 */
@Api(tags = "文档管理模块")
@RestController
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    DocumentService documentService;

    @ApiOperation(value = "创建文件夹")
    @PostMapping(value = "/folder/create")
    public ResultMsg<String> createFolder(@RequestBody DocumentAddVO documentAddVO) {
        return documentService.createFolder(documentAddVO);
    }

    @ApiOperation(value = "创建文件")
    @PostMapping(value = "/create")
    public ResultMsg<String> createDocument(@RequestBody DocumentAddVO documentAddVO) {
        return documentService.createDocument(documentAddVO);
    }

    @ApiOperation(value = "重命名")
    @PostMapping(value = "/rename")
    public ResultMsg<String> renameDocument(@RequestBody DocumentUpdateVO documentUpdateVO) {
        return documentService.renameDocument(documentUpdateVO);
    }

    @ApiOperation(value = "移动文件/文件夹")
    @PostMapping(value = "/move")
    public ResultMsg<String> moveDocument(@RequestBody DocumentUpdateVO documentUpdateVO) {
        return documentService.moveDocument(documentUpdateVO);
    }

    @ApiOperation(value = "移入回收站")
    @GetMapping(value = "/move/trash")
    public ResultMsg<String> moveTrashDocument(@RequestParam Long id) {
        return documentService.moveTrashDocument(id);
    }


}