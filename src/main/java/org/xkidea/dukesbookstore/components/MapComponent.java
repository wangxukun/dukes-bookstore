package org.xkidea.dukesbookstore.components;

import org.xkidea.dukesbookstore.listeners.AreaSelectedEvent;

import javax.faces.component.FacesComponent;
import javax.faces.component.UICommand;

@FacesComponent("DemoMap")
public class MapComponent extends UICommand {

    // 声明枚举类型变量PropertyKeys
    private enum PropertyKeys {
        current;
    }
    private final String current = null;

    public MapComponent() {
        super();
    }

    /**
     * @return 当前所选子AreaComponent的备用文本标签
     */
    public String getCurrent() {
        return (String) getStateHelper().eval(PropertyKeys.current, null);
    }

    /**
     * 为当前选定的孩子（area）设置替代文本标签。 如果这与先前的值不同，则将AreaSelectedEvent触发给感兴趣的侦听器。
     * @param current 新的替代文本标签。
     */
    public void setCurrent(String current) {
        if (this.getParent() == null) {
            return;
        }

        // 返回当前与指定键关联的值（如果有）。
        String previous = (String) getStateHelper().get(current);
        // 返回先前存储的值并存储指定的键
        getStateHelper().put(PropertyKeys.current, current);

        // 如果合适，触发AreaSelectedEvent
        if ((previous == null) && (current == null)) {
            // do nothing
        } else if ((previous != null) && (current != null) && (previous.equals(current))) {
            // do nothing
        } else {
            this.queueEvent(new AreaSelectedEvent(this));
        }
    }

    /**
     * @return 这个组件的组件族
     */
    @Override
    public String getFamily() {
        return ("Map");
    }
}
