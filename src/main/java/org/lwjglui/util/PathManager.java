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

package org.lwjglui.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by ben on 27/08/14.
 */
public class PathManager {

    private static PathManager instance;
    private static Path location;
    private static Path codeLocation;
    private static Logger logger;


    /**
     * Must be called or paths will not generate
     */
    public static void initialize() {
        // By default, the path should be the code location (where JVE.jar is)
        getInstance();
        try {
            URL urlToSource = PathManager.class.getProtectionDomain().getCodeSource().getLocation();

            codeLocation = Paths.get(urlToSource.toURI());
//            System.out.println("codeLocation: " + codeLocation);
            if (Files.isRegularFile(codeLocation)) {
                location = codeLocation.getParent().getParent();
//                System.out.println("Running from a file (jar). Setting installPath to: " + installPath);
            }
        } catch (URISyntaxException e) {
            // Can't use logger, because logger not set up when PathManager is used.
//            System.out.println("Failed to convert code location to uri");
        }
        // If terasology.jar's location could not be resolved (maybe running from an IDE) then fallback on working path
        if (location == null) {
            location = Paths.get("").toAbsolutePath();
//            System.out.println("installPath was null, running from IDE. Setting it to: " + installPath);
        }

        logger = LoggerFactory.getLogger(PathManager.class);

        logger.info("Location: " + getLocationPath() + "/");
    }

    public static PathManager getInstance() {
        if (instance == null) {
            instance = new PathManager();
        }
        return instance;
    }

    /**
     * Gets the applications main folder path. (Path to folder that holds .jar)
     * @return Path of the location of the main Jar file
     */
    public static Path getLocationPath() {
        if (codeLocation.toString().contains(".jar")) {
            return codeLocation.getParent();
        }
        return Paths.get("").toAbsolutePath();
    }

    public static String getLibraryPath() {
        return getLocationPath() + "/lib/";
    }

    public static String getOSNativePath() {
        return getLocationPath() + "/native/";
    }

    public static boolean fileExists(String file) {

        File dir = new File(getLocationPath() + "/" + file);

        if (dir.exists())
            return true;

        return false;
    }
}
