
package ru.nalog.npchk;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="NP" maxOccurs="10000" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="INN" use="required" type="{http://ws.unisoft/FNSNDSCAWS2/Response}ИННТип" /&gt;
 *                 &lt;attribute name="KPP" type="{http://ws.unisoft/FNSNDSCAWS2/Response}КППТип" /&gt;
 *                 &lt;attribute name="DT" type="{http://ws.unisoft/FNSNDSCAWS2/Response}ДатаТип" /&gt;
 *                 &lt;attribute name="State" use="required"&gt;
 *                   &lt;simpleType&gt;
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                       &lt;enumeration value="0"/&gt;
 *                       &lt;enumeration value="1"/&gt;
 *                       &lt;enumeration value="2"/&gt;
 *                       &lt;enumeration value="3"/&gt;
 *                       &lt;enumeration value="4"/&gt;
 *                       &lt;enumeration value="5"/&gt;
 *                       &lt;enumeration value="6"/&gt;
 *                       &lt;enumeration value="7"/&gt;
 *                       &lt;enumeration value="8"/&gt;
 *                       &lt;enumeration value="9"/&gt;
 *                       &lt;enumeration value="10"/&gt;
 *                       &lt;enumeration value="11"/&gt;
 *                       &lt;enumeration value="12"/&gt;
 *                     &lt;/restriction&gt;
 *                   &lt;/simpleType&gt;
 *                 &lt;/attribute&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="DTActUL" type="{http://ws.unisoft/FNSNDSCAWS2/Response}ДатаТип" /&gt;
 *       &lt;attribute name="DTActFL" type="{http://ws.unisoft/FNSNDSCAWS2/Response}ДатаТип" /&gt;
 *       &lt;attribute name="errMsg" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "np"
})
@XmlRootElement(name = "NdsResponse2")
public class NdsResponse2 {

    @XmlElement(name = "NP")
    protected List<NdsResponse2 .NP> np;
    @XmlAttribute(name = "DTActUL")
    protected String dtActUL;
    @XmlAttribute(name = "DTActFL")
    protected String dtActFL;
    @XmlAttribute(name = "errMsg")
    @XmlSchemaType(name = "anySimpleType")
    protected String errMsg;

    /**
     * Gets the value of the np property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the np property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNP().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NdsResponse2 .NP }
     * 
     * 
     */
    public List<NdsResponse2 .NP> getNP() {
        if (np == null) {
            np = new ArrayList<NdsResponse2 .NP>();
        }
        return this.np;
    }

    /**
     * Gets the value of the dtActUL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDTActUL() {
        return dtActUL;
    }

    /**
     * Sets the value of the dtActUL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDTActUL(String value) {
        this.dtActUL = value;
    }

    /**
     * Gets the value of the dtActFL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDTActFL() {
        return dtActFL;
    }

    /**
     * Sets the value of the dtActFL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDTActFL(String value) {
        this.dtActFL = value;
    }

    /**
     * Gets the value of the errMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * Sets the value of the errMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrMsg(String value) {
        this.errMsg = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;attribute name="INN" use="required" type="{http://ws.unisoft/FNSNDSCAWS2/Response}ИННТип" /&gt;
     *       &lt;attribute name="KPP" type="{http://ws.unisoft/FNSNDSCAWS2/Response}КППТип" /&gt;
     *       &lt;attribute name="DT" type="{http://ws.unisoft/FNSNDSCAWS2/Response}ДатаТип" /&gt;
     *       &lt;attribute name="State" use="required"&gt;
     *         &lt;simpleType&gt;
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *             &lt;enumeration value="0"/&gt;
     *             &lt;enumeration value="1"/&gt;
     *             &lt;enumeration value="2"/&gt;
     *             &lt;enumeration value="3"/&gt;
     *             &lt;enumeration value="4"/&gt;
     *             &lt;enumeration value="5"/&gt;
     *             &lt;enumeration value="6"/&gt;
     *             &lt;enumeration value="7"/&gt;
     *             &lt;enumeration value="8"/&gt;
     *             &lt;enumeration value="9"/&gt;
     *             &lt;enumeration value="10"/&gt;
     *             &lt;enumeration value="11"/&gt;
     *             &lt;enumeration value="12"/&gt;
     *           &lt;/restriction&gt;
     *         &lt;/simpleType&gt;
     *       &lt;/attribute&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class NP {

        @XmlAttribute(name = "INN", required = true)
        protected String inn;
        @XmlAttribute(name = "KPP")
        protected String kpp;
        @XmlAttribute(name = "DT")
        protected String dt;
        @XmlAttribute(name = "State", required = true)
        protected String state;

        /**
         * Gets the value of the inn property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getINN() {
            return inn;
        }

        /**
         * Sets the value of the inn property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setINN(String value) {
            this.inn = value;
        }

        /**
         * Gets the value of the kpp property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getKPP() {
            return kpp;
        }

        /**
         * Sets the value of the kpp property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setKPP(String value) {
            this.kpp = value;
        }

        /**
         * Gets the value of the dt property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDT() {
            return dt;
        }

        /**
         * Sets the value of the dt property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDT(String value) {
            this.dt = value;
        }

        /**
         * Gets the value of the state property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getState() {
            return state;
        }

        /**
         * Sets the value of the state property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setState(String value) {
            this.state = value;
        }

    }

}
