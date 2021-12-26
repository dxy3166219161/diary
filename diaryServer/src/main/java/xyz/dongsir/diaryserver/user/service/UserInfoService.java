package xyz.dongsir.diaryserver.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import xyz.dongsir.diaryserver.user.bean.UserInfo;
import xyz.dongsir.diaryserver.user.vo.UaseInfoOptionVO;
import xyz.dongsir.diaryserver.util.rest.ResultMsg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author dongxingyu
 * @version 2.0.0
 * <p>
 * History:
 * <p>
 * Date                Author         Version     Description
 * --------------------------------------------------------------------
 * 2021/11/25 9:51     dongxingyu        2.0.0       To create
 * </p>
 */
@Service
public interface UserInfoService extends IService<UserInfo> {
    ResultMsg<String> registerUser(UaseInfoOptionVO uaseInfoOptionVO);

    ResultMsg<String> login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}