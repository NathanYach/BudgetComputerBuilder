/**
 * The ScrapperHelper class provides utility methods for managing hardware components in a catalog.
 * It includes a method to add a hardware component to the catalog, ensuring proper organization based on price.
 * This class is generic, allowing it to work with various types of hardware components that implement the Hardware interface.
 *
 * @author Nathan Yach
 */
package org.Controllers;

import org.Models.Hardware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScrapperHelper{


    /**
     * Adds a hardware component to the catalog.
     * If a component with the same price already exists in the catalog, the component is added to the existing list of components with that price.
     * If no component with the same price exists, a new list is created and the component is added to it, keyed by its price.
     * @param <T> The type of hardware component to add.
     * @param component The hardware component to add to the catalog.
     * @param catalog The catalog where the component will be added.
     */
    public  <T extends Hardware> void addComponent(T component, Map<Integer, List<T>> catalog){

        //Check if the catalog already contains the key
        if(catalog.containsKey(component.getPrice())){
            catalog.get(component.getPrice()).add(component);
        }
        else {
            List<T> list = new ArrayList<>();
            list.add(component);
            catalog.put(component.getPrice(), list);
        }
    }
}

