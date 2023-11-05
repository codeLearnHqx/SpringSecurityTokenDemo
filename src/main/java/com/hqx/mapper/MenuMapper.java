package com.hqx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hqx.domain.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 查询用户的权限信息
     * @param id 用户id
     */
    List<String> selectPermsByUserId(@Param("userId") Long userId);

}
