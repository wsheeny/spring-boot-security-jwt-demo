package com.example.security.demo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author knox
 * @date 2020/12/10
 * @since 1.0.0
 */
@Data
@Builder
@TableName("tb_item")
@NoArgsConstructor
@AllArgsConstructor
public class TbItem implements Serializable {

    private static final long serialVersionUID = 6372924752020428954L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @TableField("`title`")
    private String title;

    @TableField("`description`")
    private String description;
}
