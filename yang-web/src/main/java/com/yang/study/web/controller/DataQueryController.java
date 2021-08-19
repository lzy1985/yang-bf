package com.yang.study.web.controller;

import com.yang.study.base.util.JsonUtil;
import com.yang.study.service.IDataQueryService;
import com.yang.study.web.base.dto.BaseRequestParamsDTO;
import com.yang.study.web.base.dto.BaseResponseResultDTO;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lzy
 * @Version 1.0.0
 * @Date 2021/8/10 09:56
 * @Description TODO
 */
@Slf4j
@RestController
@RequestMapping("data")
public class DataQueryController {

    @Resource
    IDataQueryService dataQueryService;

    @RequestMapping(value = "query", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponseResultDTO queryData(@Validated @RequestBody BaseRequestParamsDTO requestParams) {
//        if("queryDataFor".equals(requestParams.getMethodName())) {
//            List list = new ArrayList<String>();
//            list.add("aa");
//            list.add("bb");
//            requestParams.setData(list);
//        }
        log.info(JsonUtil.toJson(requestParams));
        return BaseResponseResultDTO.SUCCESS(dataQueryService.queryData(requestParams.getData(),requestParams.getMethodName()));
    }

    @RequestMapping(value = "change", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponseResultDTO changeConfig(@RequestBody BaseRequestParamsDTO requestParams) {
        dataQueryService.changeConfiguration();
        return BaseResponseResultDTO.SUCCESS();
    }
}