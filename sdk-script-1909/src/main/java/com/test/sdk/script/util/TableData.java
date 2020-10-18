package com.test.sdk.script.util;

import lombok.Data;

import java.util.List;

/**
 * @author 徒有琴
 * layui要求的表格数据格式
 */
@Data
public class TableData {
    private int code = 0;//代表成功
    private int count;//总条数
    private List data;//数据列表
    private String msg;

    public TableData(int count, List data) {
        this.count = count;
        this.data = data;
    }

    public TableData() {
    }
}
