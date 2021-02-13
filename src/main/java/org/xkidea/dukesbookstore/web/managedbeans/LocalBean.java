package org.xkidea.dukesbookstore.web.managedbeans;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 在每个页面上使用的检索当前语言环境的托管bean。
 */
@Named
@SessionScoped
public class LocalBean extends AbstractBean implements Serializable {

    // 创建或获取日志记录器
    private static final Logger logger = Logger.getLogger("dukesbookstore.web.managedbeans.LocaleBean");
    private static final long serialVersionUID = -1L;

    // 获取当前的语言环境
    private Locale locale = context().getViewRoot().getLocale();

    public LocalBean() {
    }

    public Locale getLocale() {
        logger.log(Level.INFO, "输入LocaleBean.getLocale");
        return locale;
    }

    public void setLocale(Locale locale) {
        logger.log(Level.INFO, "Entering LocaleBean.setLocale");
        this.locale = locale;
    }

    public String getLanguage() {
        Locale newlocale = null;
        logger.log(Level.INFO, "Entering LocaleBean.getLanguage");
        String lang = locale.getLanguage();
        Map map = context().getExternalContext().getSessionMap();
        if (map.containsKey("locale")) {
            newlocale = (Locale) map.get("locale");
        }
        if (!(newlocale == null)) {
            String newlang = newlocale.getLanguage();
            if (!newlang.equals(lang)) {
                return newlang;
            }
        }
        return lang;
    }

    public void setLanguage(String language) {
        logger.log(Level.INFO, "Entering LocaleBean.setLanguage");
        locale = new Locale(language);
        context().getViewRoot().setLocale(locale);
    }
}
