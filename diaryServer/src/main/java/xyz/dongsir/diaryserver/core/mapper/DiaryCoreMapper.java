package xyz.dongsir.diaryserver.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.dongsir.diaryserver.core.bean.DiaryCore;

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
 * 2021/11/16 10:19     dongxingyu        2.0.0       To create
 * </p>
 */
@Mapper
public interface DiaryCoreMapper extends BaseMapper<DiaryCore> {
    List<DiaryCore> selectByParentId(String purposeId);

//    DiaryCore selectByUid(String uid);

    List<DiaryCore> findByParentIdAndUserAccount(String purposeId, String userAccount);

    DiaryCore selectByUidAndUserAccount(String uid, String userAccount);

    List<DiaryCore> findByUidAndTitleAndTypeAndUserAccount(String uid,String title, String type, String userAccount);

    DiaryCore findByUid(String uid);

    List<DiaryCore> findByUserAccountAndTypeAndDate(String userAccount, String type, Date startDate, Date endDate);
}