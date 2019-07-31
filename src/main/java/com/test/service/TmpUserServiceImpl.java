package com.test.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.db.manager.DataSource;
import com.test.entity.TmpUser;
import com.test.mapper.TmpUserMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author song
 * @since 2019-07-01
 */
@Service
public class TmpUserServiceImpl extends ServiceImpl<TmpUserMapper, TmpUser> implements TmpUserService {

    @DataSource
    public void xx(){
        System.out.println("xxxxxxxxxxx");
    }

}
