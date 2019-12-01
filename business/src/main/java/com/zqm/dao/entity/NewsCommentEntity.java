/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.dao.entity;

import java.util.List;

import com.zqm.dao.entity.TCommentInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
