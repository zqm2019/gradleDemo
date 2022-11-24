package com.zqm.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * TODO: description
 * Date: 2019-11-27
 *
 * @author zhaqianming
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsCommentEntity {
    private int id;
    protected List<TCommentInfo> comments;
}
