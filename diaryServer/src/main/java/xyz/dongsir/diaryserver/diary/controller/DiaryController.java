package xyz.dongsir.diaryserver.diary.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.dongsir.diaryserver.core.model.DiaryCoreTreeModel;
import xyz.dongsir.diaryserver.diary.service.DiaryService;
import xyz.dongsir.diaryserver.diary.vo.DiaryListViewVO;
import xyz.dongsir.diaryserver.diary.vo.DiaryOptionVO;
import xyz.dongsir.diaryserver.diary.vo.DiaryUpdateOptionVO;
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
 * 2021/11/19 1:32     dongxingyu        2.0.0       To create
 * </p>
 */
@Api(tags = "日记管理模块")
@RestController
@RequestMapping("/diary")
public class DiaryController {
    @Autowired
    DiaryService diaryService;

    @ApiOperation(value = "添加日记")
    @PostMapping(value = "/add")
    public ResultMsg<String> addDiary(@RequestBody DiaryOptionVO diaryOptionVO) {
        return diaryService.addDiary(diaryOptionVO);
    }

    @ApiOperation(value = "修改日记标题、内容、摘要通过id")
    @PostMapping(value = "/update")
    public ResultMsg<String> updateDiary(@RequestBody DiaryUpdateOptionVO diaryUpdateOptionVO) {
        return diaryService.updateDiary(diaryUpdateOptionVO);
    }

    @ApiOperation(value = "日记移入回收站")
    @GetMapping(value = "/move/trash")
    public ResultMsg<String> moveTrash(@RequestParam("uid") String uid) {
        return diaryService.moveTrash(uid);
    }

    @ApiOperation(value = "清空回收站")
    @GetMapping(value = "/clear/trash")
    public ResultMsg<String> clearTrash() {
        return diaryService.clearTrash();
    }

    @ApiOperation(value = "删除日记")
    @GetMapping(value = "/delete")
    public ResultMsg<String> deleteDiary(@RequestParam("uid") String uid) {
        return diaryService.deleteDiary(uid);
    }

    @ApiOperation(value = "获取日记树列表")
    @PostMapping(value = "/list")
    public ResultMsg<DiaryCoreTreeModel> list() {
        return diaryService.list();
    }

    @ApiOperation(value = "获取子节点列表")
    @GetMapping(value = "/children/list")
    public ResultMsg<List<DiaryListViewVO>> findChildrenList(@RequestParam("uid") String uid) {
        return diaryService.findChildrenList(uid);
    }
}