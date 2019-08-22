
package com.zqm.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.zqm.dao.entity.TNewsInfo;
import com.zqm.vo.OneToManyVo;

/**
 * TODO: description
 * Date: 2019-08-06
 *
 * @author zhaqianming
 */
public interface OneToMany {
    OneToManyVo selectCommentByNewId();
    TNewsInfo selectNewsInfo(@Param("id") Integer id);
}
