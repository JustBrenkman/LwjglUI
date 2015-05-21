/*
 * Copyright (c) 2015.
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice, this
 *     list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 *  ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of the FreeBSD Project.
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
//        logger.info("Registered {} into registry", type.toString());
        return object;
    }

    /**
     * Retreives Object of type Class<T> and casts it.
     * @param type - Class name example Element.class
     * @param <T>
     * @return - returns cast object of type Class<T>
     */
    public static <T> T get(Class<T> type) {
//        logger.info("Cast {} from registry", type.toString());
        return type.cast(register.get(type));
    }

    public static <T> void remove(Class<T> type) {
//        logger.info("Removed {} from registry", type.toString());
        register.remove(type);
    }
}
