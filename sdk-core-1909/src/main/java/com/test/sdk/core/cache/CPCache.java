package com.test.sdk.core.cache;

import com.test.sdk.common.pojo.CP;
import com.test.sdk.core.dao.CPDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 徒有琴
 */
@Component
public class CPCache extends AbstractCache {
    private static Map<Integer, String> cpMap = new HashMap<>();

    @Autowired
    private CPDAO cpdao;

    @Override
    public void update() {
        System.out.println("===========CPCache============");
        List<CP> cps = cpdao.getAllCp();
        for (CP cp : cps) {
            cpMap.put(cp.getId(), cp.getSecretKey());
        }
        System.out.println(cpMap);
    }

    public static String getSecretKey(Integer id) {
        return cpMap.get(id);
    }
}
