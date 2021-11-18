package xyz.dongsir.diaryserver.diary.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.dongsir.diaryserver.diary.service.DiaryService;
import xyz.dongsir.diaryserver.diary.vo.DiaryOptionVO;
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
}