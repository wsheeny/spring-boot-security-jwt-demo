package com.example.security.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.security.demo.model.entity.TbPermission;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户权限表
 *
 * @author knox
 * @date 2020/12/10
 * @since 1.0.0
 */
@Mapper
@Repository
public interface PermissionMapper extends BaseMapper<TbPermission> {
}
