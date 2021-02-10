package org.xkidea.dukesbookstore.components;

import org.xkidea.dukesbookstore.model.ImageArea;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;

@FacesComponent("DemoArea")
public class AreaComponent extends UIOutput {

    private enum PropertyKeys {
        alt, coords, shape, targetImage;
    }

    public String getAlt() {
        return (String) getStateHelper().eval(PropertyKeys.alt, null);
    }

    public void setAlt(String alt) {
        getStateHelper().put(PropertyKeys.alt, alt);
    }

    public String getCoords() {
        return (String) getStateHelper().eval(PropertyKeys.coords, null);
    }

    public void setCoords(String coords) {
        getStateHelper().put(PropertyKeys.coords, coords);
    }

    public String getShape() {
        return (String) getStateHelper().eval(PropertyKeys.shape, null);
    }

    public void setShape(String shape) {
        getStateHelper().put(PropertyKeys.shape, shape);
    }

    public String getTargetImage() {
        return (String) getStateHelper().eval(PropertyKeys.targetImage, null);
    }

    public void setTargetImage(String targetImage) {
        getStateHelper().put(PropertyKeys.targetImage, targetImage);
    }

    @Override
    public String getFamily() {
        return ("Area");
    }

    @Override
    public Object getValue() {
        if (super.getValue() == null) {
            setValue(new ImageArea(getAlt(),getCoords(),getShape()));
        }
        return (super.getValue());
    }
}
