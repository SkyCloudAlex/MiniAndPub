package com.eroad.project.model;

import javax.xml.bind.annotation.*;

@XmlType(propOrder = {
        "mid", "cpmid", "result"
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "MtResponse")
public class SmsResult {
    @XmlElement(name = "mid")
    private String mid;
    @XmlElement(name = "cpmid")
    private String cpmid;
    @XmlElement(name = "result")
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getCpmid() {
        return cpmid;
    }

    public void setCpmid(String cpmid) {
        this.cpmid = cpmid;
    }
}
