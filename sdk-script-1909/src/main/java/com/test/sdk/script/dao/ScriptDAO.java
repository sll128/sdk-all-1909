package com.test.sdk.script.dao;

import com.test.sdk.script.pojo.Scripts;

import java.util.List;

public interface ScriptDAO {
    List<Scripts> getScriptsList(Integer status);

    void updateScripts(Scripts scripts);

    Scripts getScriptById(Integer id);
}
