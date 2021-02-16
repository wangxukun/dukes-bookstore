package org.xkidea.dukesbookstore.renderers;

import org.xkidea.dukesbookstore.components.AreaComponent;
import org.xkidea.dukesbookstore.components.MapComponent;
import org.xkidea.dukesbookstore.model.ImageArea;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;
import java.io.IOException;

@FacesRenderer(componentFamily = "Area", rendererType = "DemoArea")
public class AreaRenderer extends Renderer {

    public AreaRenderer() {
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }
    }

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }
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

        AreaComponent area = (AreaComponent) component;
        String targetImageId = area.findComponent(area.getTargetImage()).getClientId(context);
        ImageArea iarea = (ImageArea) area.getValue();
        ResponseWriter writer = context.getResponseWriter();
        StringBuilder sb;

        writer.startElement("area",area);
        writer.writeAttribute("alt",iarea.getAlt(),"alt");
        writer.writeAttribute("coords",iarea.getCoords(),"coords");
        writer.writeAttribute("shape",iarea.getShape(),"shape");
        sb = new StringBuilder("document.forms[0]['").append(targetImageId).append("'].src='");
        sb.append(getURI(context, (String) area.getAttributes().get("onmouseout")));
        sb.append("'");
        writer.writeAttribute("onmouseout", sb.toString(), "onmouseout");
        sb = new StringBuilder("document.forms[0]['").append(targetImageId)
                .append("'].src='");
        sb.append(
                getURI(context,
                        (String) area.getAttributes().get("onmouseover")));
        sb.append("'");
        writer.writeAttribute("onmouseover", sb.toString(), "onmouseover");
        sb = new StringBuilder("document.forms[0]['");
        sb.append(getName(context, area));
        sb.append("'].value='");
        // TODO AreaRenderer()-encode()：设置input的value
        sb.append(iarea.getAlt());
        sb.append("'; document.forms[0].submit()");
        writer.writeAttribute("onclick", sb.toString(), "value");
        writer.endElement("area");
    }

    private String getName(FacesContext context, UIComponent component) {
        while (component != null) {
            if (component instanceof MapComponent) {
                // TODO AreaRenderer component.getId = component.getParent()
                System.out.println("--- AreaRenderer component.getId() = " + component.getId());
                return (component.getId() + "_current");
            }

            component = component.getParent();
        }

        throw new IllegalArgumentException();
    }

    private String getURI(FacesContext context, String value) {
        if (value.startsWith("/")) {
            return context.getExternalContext().getRequestContextPath() + value;
        } else {
            return value;
        }
    }
}
