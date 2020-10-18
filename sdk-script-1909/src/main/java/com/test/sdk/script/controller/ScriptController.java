package com.test.sdk.script.controller;

import com.test.sdk.script.pojo.Scripts;
import com.test.sdk.script.service.ScriptService;
import com.test.sdk.script.util.AjaxResult;
import com.test.sdk.script.util.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 徒有琴
 */
@Controller
public class ScriptController {
    @Autowired
    private ScriptService scriptService;

    //负责跳转页面
    @RequestMapping("{page}.html")
    public String page(@PathVariable String page) {
        return page;
    }

    @RequestMapping("list")
    @ResponseBody
    public TableData list() {
        List<Scripts> list = scriptService.getScriptList(null);
        //第一个参数是总条数，用于分页，此处未分页
        return new TableData(list.size(), list);
    }

    @RequestMapping("update")
    @ResponseBody
    public AjaxResult update(Scripts scripts) {
        scriptService.updateScripts(scripts);
        return AjaxResult.ok(null);
    }

    @RequestMapping("delete")
    @ResponseBody
    public AjaxResult delete(Integer id) {
        scriptService.deleteJob(id);
        return AjaxResult.ok(null);
    }
    @RequestMapping("deletes")
    @ResponseBody
    public AjaxResult deletes(Integer[] ids) {
        for (Integer id : ids) {
            scriptService.deleteJob(id);
        }
        return AjaxResult.ok(null);
    }
}
