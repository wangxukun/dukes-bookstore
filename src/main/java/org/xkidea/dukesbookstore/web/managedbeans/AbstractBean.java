package org.xkidea.dukesbookstore.web.managedbeans;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * 托管bean共享实用程序方法的抽象基类。
 */
@Named
@SessionScoped
public class AbstractBean implements Serializable {

    private static final long serialVersionUID = 5786648996320514448L;
    @Inject
    ShoppingCart cart;
    /**
     * @return 当前请求的FacesContext实例。
     */
    protected FacesContext context(){
        return (FacesContext.getCurrentInstance());
    }

    /**
     * 为当前请求增加一个国际化的信息到FacesContext实例中
     * @param clientId 此消息涉及的组件的客户端标识符，对于全局消息为null
     * @param key 要包含的信息的信息键
     */
    protected void message(String clientId, String key) {
        // 查找所需的文本信息
        String text;
        try {
            // 根据当前的语言环境，获取对应的资源包
            ResourceBundle bundle = ResourceBundle.getBundle("messages.Messages", context().getViewRoot().getLocale());
            text = bundle.getString(key);
        } catch (Exception e) {
            text = "???" + key + "???";
        }
        // 构造并包含一个FaceMessage到当前FaceContext实例中
        context().addMessage(clientId, new FacesMessage(text));
    }

    /**
     * 为当前请求增加一个国际化的信息到FacesContext实例中
     * @param clientId 此消息涉及的组件的客户端标识符，对于全局消息为null
     * @param key 要包含的信息的信息键
     * @param params 替换本地化文本的参数
     */
    protected void message(String clientId, String key, Object[] params) {
        // 查找所需的文本信息
        String text;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("messages.Messages", context().getViewRoot().getLocale());
            text = bundle.getString(key);
        } catch (Exception e) {
            text = "???" + key + "???";
        }
        // 执行信息的替换
        if ((params != null) && (params.length > 0)) {
            text = MessageFormat.format(text, params);
        }
        // 构造并包含一个FaceMessage到当前FaceContext实例中
        context().addMessage(clientId, new FacesMessage(text));
    }
}
