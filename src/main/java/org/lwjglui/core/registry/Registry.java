/*
 * Copyright (c) 2015.
 */

package org.lwjglui.core.registry;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by ben on 07/05/15.
 * <p>
 * JGUILibrary
 */
public class Registry {

    public static Logger logger = LoggerFactory.getLogger(Registry.class);

    /**
     * Resister Map for holding the objects
     */
    public static Map<Class<? extends Object>, Object> register = Maps.newConcurrentMap();

    /**
     * Put objects into register
     * @param type - Class of objects type example Element.class
     * @param object - Actual object example new Element()
     * @param <T>
     * @param <U>
     * @return - returns sent object
     */
    public static <T, U extends T>U put(Class<T> type, U object) {
        register.put(type, object);
        logger.info("Registered {} into registry", type.toString());
        return object;
    }

    /**
     * Retreives Object of type Class<T> and casts it.
     * @param type - Class name example Element.class
     * @param <T>
     * @return - returns cast object of type Class<T>
     */
    public static <T> T get(Class<T> type) {
        logger.info("Cast {} from registry", type.toString());
        return type.cast(register.get(type));
    }

    public static <T> void remove(Class<T> type) {
        logger.info("Removed {} from registry", type.toString());
        register.remove(type);
    }
}
