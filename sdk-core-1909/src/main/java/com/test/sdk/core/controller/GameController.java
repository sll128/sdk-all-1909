package com.test.sdk.core.controller;

import com.test.sdk.common.pojo.Game;
import com.test.sdk.common.util.ResponseTO;
import com.test.sdk.core.cache.GameCache;
import com.test.sdk.core.exception.SdkException;
import com.test.sdk.core.util.ErrorConstants;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 徒有琴
 */
@RestController
public class GameController {

    @RequestMapping("/common/init.html")
    public ResponseTO init(Integer cpid, Integer seqnum) throws SdkException{
        Game game = GameCache.getGameByCpidAndSeqnum(cpid, seqnum);
        ResponseTO response=new ResponseTO();
        if(game==null){
            throw new SdkException(ErrorConstants.GAME_NOT_EXIST);
        }else{
            response.setSuccess(true);
            Map<String,String> result=new HashMap<>();
            result.put("status",game.getRepairStatus().toString());
            result.put("content",game.getRepairContent());
            response.setBusinessResult(result);
        }
        return response;
    }
}
