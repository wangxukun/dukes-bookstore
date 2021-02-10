package org.xkidea.dukesbookstore.model;

import java.io.Serializable;

public class ImageArea implements Serializable {
    private static final long serialVersionUID = -1L;
    private String alt = null;
    private String coords = null;
    private String shape = null;

    public ImageArea() {
    }
    public ImageArea(String alt, String coords, String shape) {
        this.alt = alt;
        this.coords = coords;
        this.shape = shape;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getCoords() {
        return coords;
    }

    public void setCoords(String coords) {
        this.coords = coords;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }
}
