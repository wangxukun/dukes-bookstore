package org.xkidea.dukesbookstore.components;

import org.xkidea.dukesbookstore.listeners.AreaSelectedEvent;

import javax.faces.component.FacesComponent;
import javax.faces.component.UICommand;

/**
 * MapComponent是与客户端图像映射相对应的JavaServer Faces组件。 它可以具有AreaComponent类型的多个子元素之一，每个子元素代表热点，用户可以单击它们并将其悬停在上面。
 * 此组件是AreaSelectedEvent事件的来源，每当更改当前区域时都会触发该事件。
 * 使用javax.faces.component.StateHelper接口可以使用表达式，也无需实现saveState（）和restoreState（）。
 */

// 注册组件
@FacesComponent(MapComponent.COMPONENT_TYPE)
public class MapComponent extends UICommand {

    public static final String COMPONENT_TYPE = "DemoMap";
    public static final String COMPONENT_FAMILY = "Map";
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
        String cur = (String) getStateHelper().eval(PropertyKeys.current, null);
        // TODO ---getCurrent()---
        System.out.println("----getCurrent()--- is :" + cur);
        return cur;
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
        return (MapComponent.COMPONENT_FAMILY);
    }
}
