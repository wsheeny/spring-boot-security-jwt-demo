package com.example.security.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.security.demo.mapper.ItemMapper;
import com.example.security.demo.model.entity.TbItem;
import com.example.security.demo.service.ItemService;
import org.springframework.stereotype.Service;

/**
 * @author knox
 * @date 2020/12/10
 * @since 1.0.0
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, TbItem> implements ItemService {
}
