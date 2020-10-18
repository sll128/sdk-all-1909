package com.test.sdk.core.cache;

import com.test.sdk.common.pojo.PromotionChannel;
import com.test.sdk.core.dao.PromotionChannelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 徒有琴
 */
@Component
public class PromotionChannelCache extends AbstractCache {
    private static Map<Integer,Integer> channelMap=new HashMap<>();

    @Autowired
    private PromotionChannelDAO promotionChannelDAO;
    @Override
    public void update() {
        List<PromotionChannel> channelList=promotionChannelDAO.getAllChannel();
        for (PromotionChannel promotionChannel : channelList) {
            channelMap.put(promotionChannel.getId(),promotionChannel.getLanguageType());
        }
    }

    public static Integer getLanguageType(Integer id){
        return channelMap.get(id);
    }
}
