package com.yi.mall.search.service;


import com.yi.mall.search.vo.SearchParam;
import com.yi.mall.search.vo.SearchResult;

public interface MallSearchService {

    SearchResult search(SearchParam param);
}
