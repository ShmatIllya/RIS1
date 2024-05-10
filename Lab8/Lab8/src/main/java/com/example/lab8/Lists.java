package com.example.lab8;

import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Lists<VALUE> {
    private List<VALUE> values = new ArrayList<>();
    @XmlAnyElement(lax = true)
    public List<VALUE> getValues()
    {
        return values;
    }
}
