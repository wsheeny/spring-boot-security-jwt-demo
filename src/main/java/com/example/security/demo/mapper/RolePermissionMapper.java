package com.example.security.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.security.demo.model.entity.TbRolePermission;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 角色权限关系表DAO
 *
 * @author knox
 * @date 2020/12/10
 * @since 1.0.0
 */
@Mapper
@Repository
public interface RolePermissionMapper extends BaseMapper<TbRolePermission> {
}
