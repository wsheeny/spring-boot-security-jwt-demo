package com.example.security.demo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户-角色关系表
 *
 * @author knox
 * @date 2020/12/10
 * @since 1.0.0
 */
@Data
@TableName("tb_user_role")
@AllArgsConstructor
@NoArgsConstructor
public class TbUserRole implements Serializable {

    private static final long serialVersionUID = 4481496962900498130L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField("user_id")
    private String userId;

    @TableField("role_id")
    private String roleId;
}
