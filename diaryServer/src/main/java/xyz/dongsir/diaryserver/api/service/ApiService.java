package xyz.dongsir.diaryserver.api.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import xyz.dongsir.diaryserver.api.vo.WebInterfaceAccessVO;
import xyz.dongsir.diaryserver.util.rest.ResponseMsg;
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
 * 2021/12/18 10:27     dongxingyu        2.0.0       To create
 * </p>
 */
@Service
public class ApiService {
    public ResultMsg<WebInterfaceAccessVO> testWebInterfaceAccess(WebInterfaceAccessVO webInterfaceAccessVO) {
        String jsonString = JSONObject.toJSONString(webInterfaceAccessVO);
        System.out.println(jsonString);
        return ResponseMsg.setSuccessResult(webInterfaceAccessVO);
    }
}