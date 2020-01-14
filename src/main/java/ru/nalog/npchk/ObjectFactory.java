
package ru.nalog.npchk;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the main package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: main
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NdsRequest2 }
     * 
     */
    public NdsRequest2 createNdsRequest2() {
        return new NdsRequest2();
    }

    /**
     * Create an instance of {@link NdsResponse2 }
     * 
     */
    public NdsResponse2 createNdsResponse2() {
        return new NdsResponse2();
    }

    /**
     * Create an instance of {@link NdsRequest2 .NP }
     * 
     */
    public NdsRequest2 .NP createNdsRequest2NP() {
        return new NdsRequest2 .NP();
    }

    /**
     * Create an instance of {@link NdsResponse2 .NP }
     * 
     */
    public NdsResponse2 .NP createNdsResponse2NP() {
        return new NdsResponse2 .NP();
    }

}
