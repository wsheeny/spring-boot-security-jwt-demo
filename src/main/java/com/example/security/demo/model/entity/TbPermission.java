package com.example.security.demo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 权限
 *
 * @author Knox
 */
@Data
@TableName("tb_permission")
@Accessors(chain = true)
public class TbPermission implements Serializable {
    private static final long serialVersionUID = -2694960432845360318L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField("`value`")
    private String value;

    @TableField("`description`")
    private String description;

    @TableField("p_id")
    private Integer pid;
}
