package cn.ofpp.core;

import cn.ofpp.Bootstrap;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import java.util.GregorianCalendar;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author DokiYolo
 * Date 2022-08-22
 */
public class Wx {
    private static final AtomicReference<WxMpService> serviceAtomicReference = new AtomicReference<>();

    public static void init() {
        WxMpDefaultConfigImpl mpConfig = new WxMpDefaultConfigImpl();
        mpConfig.setAppId(Bootstrap.APP_ID);
        
        
        mpConfig.setSecret(Bootstrap.SECRET);

        
        serviceAtomicReference.set(new WxMpServiceImpl());
        serviceAtomicReference.get().setWxMpConfigStorage(mpConfig);
    }

    public static Optional<WxMpService> getService() {
        return Optional.ofNullable(serviceAtomicReference.get());
    }

    public static Optional<WxMpTemplateMsgService> getTemplateMsgService() {
        return getService().map(WxMpService::getTemplateMsgService);
    }

    public static void sendTemplateMessage(WxMpTemplateMessage message) {
        getTemplateMsgService().ifPresent(service -> {

            try {
                String s = service.sendTemplateMsg(message);
            } catch (WxErrorException e) {
                throw new RuntimeException(e);
            }

        });


    }

}
