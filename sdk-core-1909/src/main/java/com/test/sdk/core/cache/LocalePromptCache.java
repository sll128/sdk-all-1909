package com.test.sdk.core.cache;

import com.test.sdk.common.pojo.LocalePrompt;
import com.test.sdk.core.dao.LocalePromptDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 徒有琴
 */
@Component
public class LocalePromptCache extends AbstractCache {
    @Autowired
    private LocalePromptDAO localePromptDAO;

    private static Map<String, LocalePrompt> promptMap = new HashMap<>();

    @Override
    public void update() {
        List<LocalePrompt> prompts = localePromptDAO.getPromptList();
        for (LocalePrompt prompt : prompts) {
            promptMap.put(prompt.getName() + "-" + prompt.getLanguageType(), prompt);
        }
    }

    public static LocalePrompt getPromopt(String name, Integer languageType) {
        return promptMap.get(name + "-" + languageType);
    }
}
