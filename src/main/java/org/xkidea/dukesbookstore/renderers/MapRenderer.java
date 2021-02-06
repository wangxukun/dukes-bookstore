package org.xkidea.dukesbookstore.renderers;

import org.xkidea.dukesbookstore.components.MapComponent;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;
import java.io.IOException;

@FacesRenderer(componentFamily = "Map",rendererType = "DemoMap")
public class MapRenderer extends Renderer {

    public MapRenderer() {
    }

    /**
     * 对传入的请求参数进行解码，以确定已选择了哪个热点（如果有）。
     * @param context 当前请求的FacesContext。
     * @param component 被解码的组件。
 */
    @Override
    public void decode(FacesContext context, UIComponent component) {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }

        MapComponent map = (MapComponent) component;

        String key = getName(context, map);
    }

    private String getName(FacesContext context, UIComponent component) {
        return (component.getId() + "_current");
    }

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        super.encodeBegin(context, component);
    }

    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        super.encodeChildren(context, component);
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        super.encodeEnd(context, component);
    }
}
