package com.example.security.demo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Knox
 */
@Data
@TableName("tb_role_permission")
@Accessors(chain = true)
public class TbRolePermission implements Serializable {

    private static final long serialVersionUID = 6841432556002220108L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField("role_id")
    private String roleId;

    @TableField("permission_id")
    private String permissionId;
}
