package org.xkidea.dukesbookstore.listeners;

import org.xkidea.dukesbookstore.components.MapComponent;

import javax.faces.event.ActionEvent;

/**
 * 一个ActionEvent，指示指定的AreaComponent刚刚成为源MapComponent中当前选择的热点。
 */
public class AreaSelectedEvent extends ActionEvent {

    private static final long serialVersionUID = -1L;

    /**
     * 从指定的源映射构造一个新的AreaSelectedEvent。
     * @param map 发起此事件的MapComponent
     */
    public AreaSelectedEvent(MapComponent map) {
        super(map);
    }

    /**
     * @return 为其选择区域的地图的MapComponent。
     */
    public MapComponent getMapComponent() {
        return ((MapComponent) getComponent());
    }
}
