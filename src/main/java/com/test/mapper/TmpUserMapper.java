package com.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.test.entity.TmpUser;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author song
 * @since 2019-06-26
 */
public interface TmpUserMapper extends BaseMapper<TmpUser> {

    IPage<TmpUser> queryList(Page page);

}
