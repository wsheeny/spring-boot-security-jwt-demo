package com.example.security.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.security.demo.model.entity.TbUserRole;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author knox
 * @date 2020/12/10
 * @since 1.0.0
 */
@Mapper
@Repository
public interface UserRoleMapper extends BaseMapper<TbUserRole> {
}
