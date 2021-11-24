package xyz.dongsir.diaryserver.memorandum.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.dongsir.diaryserver.document.vo.DocumentAddVO;
import xyz.dongsir.diaryserver.memorandum.service.MemorandumService;
import xyz.dongsir.diaryserver.memorandum.vo.MemorandumListVO;
import xyz.dongsir.diaryserver.memorandum.vo.MemorandumOptionVO;
import xyz.dongsir.diaryserver.memorandum.vo.MemorandumSearchVO;
import xyz.dongsir.diaryserver.memorandum.vo.MemorandumViewVO;
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
 * 2021/11/24 15:35     dongxingyu        2.0.0       To create
 * </p>
 */
@Api(tags = "文档管理模块")
@RestController
@RequestMapping("/memorandum")
public class MemorandumController {
    @Autowired
    MemorandumService memorandumService;

    @ApiOperation(value = "添加备忘录")
    @PostMapping(value = "/add")
    public ResultMsg<String> addMemorandum(@RequestBody MemorandumOptionVO memorandumOptionVO) {
        return memorandumService.addMemorandum(memorandumOptionVO);
    }

    @ApiOperation(value = "修改备忘录")
    @PostMapping(value = "/update")
    public ResultMsg<String> updateMemorandum(@RequestBody MemorandumOptionVO memorandumOptionVO) {
        return memorandumService.updateMemorandum(memorandumOptionVO);
    }

    @ApiOperation(value = "删除备忘录")
    @GetMapping(value = "/delete")
    public ResultMsg<String> deleteMemorandum(@RequestParam("id") Long id) {
        return memorandumService.deleteMemorandum(id);
    }

    @ApiOperation(value = "查看列表：日历视图")
    @PostMapping(value = "/calendar/list")
    public ResultMsg<List<MemorandumViewVO>> calendarList(@RequestBody MemorandumSearchVO memorandumSearchVO) {
        return memorandumService.calendarList(memorandumSearchVO);
    }

    @ApiOperation(value = "查看备忘录列表")
    @PostMapping(value = "/list")
    public ResultMsg<List<MemorandumListVO>> memorandumList() {
        return memorandumService.memorandumList();
    }
}