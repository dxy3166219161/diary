package xyz.dongsir.diaryserver.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.dongsir.diaryserver.api.service.ApiService;
import xyz.dongsir.diaryserver.api.vo.WebInterfaceAccessVO;
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
 * 2021/12/18 10:26     dongxingyu        2.0.0       To create
 * </p>
 */
@Api(tags = "Api接口模块")
@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    ApiService apiService;

    @ApiOperation(value = "测试前端接口接入")
    @PostMapping(value = "/test/web/interface/access")
    public ResultMsg<WebInterfaceAccessVO> testWebInterfaceAccess(@RequestBody WebInterfaceAccessVO webInterfaceAccessVO) {
        return apiService.testWebInterfaceAccess(webInterfaceAccessVO);
    }

    @ApiOperation(value = "测试前端接口接入")
    @PostMapping(value = "/test/web/access1")
    public ResultMsg<WebInterfaceAccessVO> testWebInterfaceAccess1(@RequestBody WebInterfaceAccessVO webInterfaceAccessVO) {
        return apiService.testWebInterfaceAccess(webInterfaceAccessVO);
    }
}