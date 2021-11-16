package xyz.dongsir.diaryserver.util;

import java.util.UUID;
/**
 * Description: UUID工具类
 *
 * @author dongxingyu
 * @version 2.0.0
 *
 * <p>
 * History:
 * Date                Author         Version     Description
 * --------------------------------------------------------------------
 * 2021/11/16 9:54     dongxingyu        2.0.0       To create
 * </p>
 */
public class UUIDUtil {

    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }
}