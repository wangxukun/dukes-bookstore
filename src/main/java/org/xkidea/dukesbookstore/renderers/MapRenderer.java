package org.xkidea.dukesbookstore.renderers;

import org.xkidea.dukesbookstore.components.MapComponent;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
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
        String value = (String)context.getExternalContext().getRequestParameterMap().get(key);

        if (value != null) {
            map.setCurrent(value);
        }
    }

    private String getName(FacesContext context, UIComponent component) {
        return (component.getId() + "_current");
    }

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }

        MapComponent map = (MapComponent) component;
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("map",map);
        writer.writeAttribute("name", map.getId(),"id");
    }

    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }

        MapComponent map = (MapComponent) component;
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("input",map);
        writer.writeAttribute("type","hidden",null);
        writer.writeAttribute("name",getName(context,map),"clientId");
        writer.endElement("map");
    }

    private String getURI(FacesContext context) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getExternalContext().getRequestContextPath());
        sb.append("/faces");
        sb.append(context.getViewRoot().getViewId());

        return (sb.toString());
    }
}
