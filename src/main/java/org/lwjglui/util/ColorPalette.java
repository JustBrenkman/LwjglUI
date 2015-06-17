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

import java.awt.*;
import java.util.HashMap;

/**
 * Created by ben on 17/06/15.
 */
public class ColorPalette {

    private static HashMap<ColorName, ColorPatch> colorNames = new HashMap<>();

    public static void initialize() {
        ColorPatch red = new ColorPatch().addColor(new Color(0xFFEBEE), ColorGrade.P50).
                addColor(new Color(0xFFCDD2), ColorGrade.P100).
                addColor(new Color(0xEF9A9A), ColorGrade.P200).
                addColor(new Color(0xE57373), ColorGrade.P300).
                addColor(new Color(0xEF5350), ColorGrade.P400).
                addColor(new Color(0xF44336), ColorGrade.P500).
                addColor(new Color(0xE53935), ColorGrade.P600).
                addColor(new Color(0xD32F2F), ColorGrade.P700).
                addColor(new Color(0xC62828), ColorGrade.P800).
                addColor(new Color(0xB71C1C), ColorGrade.P900).

                addColor(new Color(0xFF8A80), ColorGrade.A100).
                addColor(new Color(0xFF5252), ColorGrade.A200).
                addColor(new Color(0xFF1744), ColorGrade.A400).
                addColor(new Color(0xD50000), ColorGrade.A700);
        addColorPatch(red, ColorName.RED);

        ColorPatch pink = new ColorPatch().addColor(new Color(0xFCE4EC), ColorGrade.P50).
                addColor(new Color(0xF8BBD0), ColorGrade.P100).
                addColor(new Color(0xF48FB1), ColorGrade.P200).
                addColor(new Color(0xF06292), ColorGrade.P300).
                addColor(new Color(0xEC407A), ColorGrade.P400).
                addColor(new Color(0xE91E63), ColorGrade.P500).
                addColor(new Color(0xD81B60), ColorGrade.P600).
                addColor(new Color(0xC2185B), ColorGrade.P700).
                addColor(new Color(0xAD1457), ColorGrade.P800).
                addColor(new Color(0x880E4F), ColorGrade.P900).

                addColor(new Color(0xFF80AB), ColorGrade.A100).
                addColor(new Color(0xFF4081), ColorGrade.A200).
                addColor(new Color(0xF50057), ColorGrade.A400).
                addColor(new Color(0xC51162), ColorGrade.A700);
        addColorPatch(pink, ColorName.PINK);

        ColorPatch purple = new ColorPatch().addColor(new Color(0xF3E5F5), ColorGrade.P50).
                addColor(new Color(0xE1BEE7), ColorGrade.P100).
                addColor(new Color(0xCE93D8), ColorGrade.P200).
                addColor(new Color(0xBA68C8), ColorGrade.P300).
                addColor(new Color(0xAB47BC), ColorGrade.P400).
                addColor(new Color(0x9C27B0), ColorGrade.P500).
                addColor(new Color(0x8E24AA), ColorGrade.P600).
                addColor(new Color(0x7B1FA2), ColorGrade.P700).
                addColor(new Color(0x6A1B9A), ColorGrade.P800).
                addColor(new Color(0x4A148C), ColorGrade.P900).

                addColor(new Color(0xEA80FC), ColorGrade.A100).
                addColor(new Color(0xE040FB), ColorGrade.A200).
                addColor(new Color(0xD500F9), ColorGrade.A400).
                addColor(new Color(0xAA00FF), ColorGrade.A700);
        addColorPatch(purple, ColorName.PURPLE);

        ColorPatch deepPurple = new ColorPatch().addColor(new Color(0xEDE7F6), ColorGrade.P50).
                addColor(new Color(0xD1C4E9), ColorGrade.P100).
                addColor(new Color(0xB39DDB), ColorGrade.P200).
                addColor(new Color(0x9575CD), ColorGrade.P300).
                addColor(new Color(0x7E57C2), ColorGrade.P400).
                addColor(new Color(0x673AB7), ColorGrade.P500).
                addColor(new Color(0x5E35B1), ColorGrade.P600).
                addColor(new Color(0x512DA8), ColorGrade.P700).
                addColor(new Color(0x4527A0), ColorGrade.P800).
                addColor(new Color(0x311B92), ColorGrade.P900).

                addColor(new Color(0xB388FF), ColorGrade.A100).
                addColor(new Color(0x7C4DFF), ColorGrade.A200).
                addColor(new Color(0x651FFF), ColorGrade.A400).
                addColor(new Color(0x6200EA), ColorGrade.A700);
        addColorPatch(deepPurple, ColorName.DEEPPURPLE);

        ColorPatch indigo = new ColorPatch().addColor(new Color(0xE8EAF6), ColorGrade.P50).
                addColor(new Color(0xC5CAE9), ColorGrade.P100).
                addColor(new Color(0x9FA8DA), ColorGrade.P200).
                addColor(new Color(0x7986CB), ColorGrade.P300).
                addColor(new Color(0x5C6BC0), ColorGrade.P400).
                addColor(new Color(0x3F51B5), ColorGrade.P500).
                addColor(new Color(0x3949AB), ColorGrade.P600).
                addColor(new Color(0x303F9F), ColorGrade.P700).
                addColor(new Color(0x283593), ColorGrade.P800).
                addColor(new Color(0x1A237E), ColorGrade.P900).

                addColor(new Color(0x8C9EFF), ColorGrade.A100).
                addColor(new Color(0x536DFE), ColorGrade.A200).
                addColor(new Color(0x3D5AFE), ColorGrade.A400).
                addColor(new Color(0x304FFE), ColorGrade.A700);
        addColorPatch(indigo, ColorName.INDIGO);

        ColorPatch blue = new ColorPatch().addColor(new Color(0xE3F2FD), ColorGrade.P50).
                addColor(new Color(0xBBDEFB), ColorGrade.P100).
                addColor(new Color(0x90CAF9), ColorGrade.P200).
                addColor(new Color(0x64B5F6), ColorGrade.P300).
                addColor(new Color(0x42A5F5), ColorGrade.P400).
                addColor(new Color(0x2196F3), ColorGrade.P500).
                addColor(new Color(0x1E88E5), ColorGrade.P600).
                addColor(new Color(0x1976D2), ColorGrade.P700).
                addColor(new Color(0x1565C0), ColorGrade.P800).
                addColor(new Color(0x0D47A1), ColorGrade.P900).

                addColor(new Color(0x82B1FF), ColorGrade.A100).
                addColor(new Color(0x448AFF), ColorGrade.A200).
                addColor(new Color(0x2979FF), ColorGrade.A400).
                addColor(new Color(0x2962FF), ColorGrade.A700);
        addColorPatch(blue, ColorName.BLUE);

        ColorPatch lightBlue = new ColorPatch().addColor(new Color(0xE1F5FE), ColorGrade.P50).
                addColor(new Color(0xB3E5FC), ColorGrade.P100).
                addColor(new Color(0x81D4FA), ColorGrade.P200).
                addColor(new Color(0x4FC3F7), ColorGrade.P300).
                addColor(new Color(0x29B6F6), ColorGrade.P400).
                addColor(new Color(0x03A9F4), ColorGrade.P500).
                addColor(new Color(0x039BE5), ColorGrade.P600).
                addColor(new Color(0x0288D1), ColorGrade.P700).
                addColor(new Color(0x0277BD), ColorGrade.P800).
                addColor(new Color(0x01579B), ColorGrade.P900).

                addColor(new Color(0x80D8FF), ColorGrade.A100).
                addColor(new Color(0x40C4FF), ColorGrade.A200).
                addColor(new Color(0x00B0FF), ColorGrade.A400).
                addColor(new Color(0x0091EA), ColorGrade.A700);
        addColorPatch(lightBlue, ColorName.LIGHTBLUE);

        ColorPatch cyan = new ColorPatch().addColor(new Color(0xE0F7FA), ColorGrade.P50).
                addColor(new Color(0xB2EBF2), ColorGrade.P100).
                addColor(new Color(0x80DEEA), ColorGrade.P200).
                addColor(new Color(0x4DD0E1), ColorGrade.P300).
                addColor(new Color(0x26C6DA), ColorGrade.P400).
                addColor(new Color(0x00BCD4), ColorGrade.P500).
                addColor(new Color(0x00ACC1), ColorGrade.P600).
                addColor(new Color(0x0097A7), ColorGrade.P700).
                addColor(new Color(0x00838F), ColorGrade.P800).
                addColor(new Color(0x006064), ColorGrade.P900).

                addColor(new Color(0x84FFFF), ColorGrade.A100).
                addColor(new Color(0x18FFFF), ColorGrade.A200).
                addColor(new Color(0x00E5FF), ColorGrade.A400).
                addColor(new Color(0x00B8D4), ColorGrade.A700);
        addColorPatch(cyan, ColorName.CYAN);

        ColorPatch teal = new ColorPatch().addColor(new Color(0xE0F2F1), ColorGrade.P50).
                addColor(new Color(0xB2DFDB), ColorGrade.P100).
                addColor(new Color(0x80CBC4), ColorGrade.P200).
                addColor(new Color(0x4DB6AC), ColorGrade.P300).
                addColor(new Color(0x26A69A), ColorGrade.P400).
                addColor(new Color(0x009688), ColorGrade.P500).
                addColor(new Color(0x00897B), ColorGrade.P600).
                addColor(new Color(0x00796B), ColorGrade.P700).
                addColor(new Color(0x00695C), ColorGrade.P800).
                addColor(new Color(0x004D40), ColorGrade.P900).

                addColor(new Color(0xA7FFEB), ColorGrade.A100).
                addColor(new Color(0x64FFDA), ColorGrade.A200).
                addColor(new Color(0x1DE9B6), ColorGrade.A400).
                addColor(new Color(0x00BFA5), ColorGrade.A700);
        addColorPatch(teal, ColorName.TEAL);
    }

    public static Color getColor(ColorName name, ColorGrade grade) {
        return colorNames.get(name).getColor(grade);
    }

    private static void addColorPatch(ColorPatch patch, ColorName name) {
        colorNames.put(name, patch);
    }

    public enum ColorName {
        RED, PINK, PURPLE,
        DEEPPURPLE, INDIGO, BLUE,
        LIGHTBLUE, CYAN, TEAL,
        GREEN, LIMEGREEN, LIME,
        YELLOW, AMBER, ORANGE,
        DEEPORANGE, BROWN, GREY,
        BLUEGREY, BLACK, WHITE
    }

    public enum ColorGrade {
        P50, P100, P200, P300, P400, P500, P600, P700, P800, P900,
        A100, A200, A400, A700
    }

    public static class ColorPatch {

        private HashMap<ColorGrade, Color> colorPatches = new HashMap<>();

        public ColorPatch() {

        }

        public ColorPatch addColor(Color color, ColorGrade grade) {
            colorPatches.put(grade, color);
            return this;
        }

        public Color getColor(ColorGrade grade) {
            return colorPatches.get(grade);
        }
    }
}
