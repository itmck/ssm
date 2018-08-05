package com.qf.shop.search.service;

import com.qf.shop.search.pojo.dto.TbItemIndexResult;

/**
 * Create by it_mck 2018/8/2 19:57
 *
 * @Description:
 * @Version: 1.0
 */
public interface SearchIndexService {
    TbItemIndexResult searchIndex(String keyword, int pageIndex, int pageSize);
}
