package com.test.sdk.script.service;

import com.test.sdk.script.pojo.Scripts;

import java.util.List;

/**
 * @author 徒有琴
 */
public interface ScriptService {
    List<Scripts> getScriptList(Integer status);

    void updateScripts(Scripts scripts);

    void deleteJob(Integer id);
}
