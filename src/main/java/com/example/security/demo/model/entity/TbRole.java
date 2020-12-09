package com.example.security.demo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 角色
 *
 * @author Knox
 */
@Data
@TableName("tb_role")
@Accessors(chain = true)
public class TbRole implements Serializable {
    private static final long serialVersionUID = 7824693669858106664L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @TableField("`value`")
    private String value;

    @TableField("`description`")
    private String description;
}
