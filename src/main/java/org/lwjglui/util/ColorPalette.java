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
        final ColorPatch red = new ColorPatch().addColor(new Color(0xFFEBEE), ColorGrade.P50).
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

        final ColorPatch pink = new ColorPatch().addColor(new Color(0xFCE4EC), ColorGrade.P50).
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

        final ColorPatch purple = new ColorPatch().addColor(new Color(0xF3E5F5), ColorGrade.P50).
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

        final ColorPatch deepPurple = new ColorPatch().addColor(new Color(0xEDE7F6), ColorGrade.P50).
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

        final ColorPatch indigo = new ColorPatch().addColor(new Color(0xE8EAF6), ColorGrade.P50).
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

        final ColorPatch blue = new ColorPatch().addColor(new Color(0xE3F2FD), ColorGrade.P50).
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

        final ColorPatch lightBlue = new ColorPatch().addColor(new Color(0xE1F5FE), ColorGrade.P50).
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

        final ColorPatch cyan = new ColorPatch().addColor(new Color(0xE0F7FA), ColorGrade.P50).
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

        final ColorPatch teal = new ColorPatch().addColor(new Color(0xE0F2F1), ColorGrade.P50).
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

        final ColorPatch green = new ColorPatch().addColor(new Color(0xE8F5E9), ColorGrade.P50).
                addColor(new Color(0xC8E6C9), ColorGrade.P100).
                addColor(new Color(0xA5D6A7), ColorGrade.P200).
                addColor(new Color(0x81C784), ColorGrade.P300).
                addColor(new Color(0x66BB6A), ColorGrade.P400).
                addColor(new Color(0x4CAF50), ColorGrade.P500).
                addColor(new Color(0x43A047), ColorGrade.P600).
                addColor(new Color(0x388E3C), ColorGrade.P700).
                addColor(new Color(0x2E7D32), ColorGrade.P800).
                addColor(new Color(0x1B5E20), ColorGrade.P900).

                addColor(new Color(0xB9F6CA), ColorGrade.A100).
                addColor(new Color(0x69F0AE), ColorGrade.A200).
                addColor(new Color(0x00E676), ColorGrade.A400).
                addColor(new Color(0x00C853), ColorGrade.A700);
        addColorPatch(green, ColorName.GREEN);

        final ColorPatch lightGreen = new ColorPatch().addColor(new Color(0xF1F8E9), ColorGrade.P50).
                addColor(new Color(0xDCEDC8), ColorGrade.P100).
                addColor(new Color(0xC5E1A5), ColorGrade.P200).
                addColor(new Color(0xAED581), ColorGrade.P300).
                addColor(new Color(0x9CCC65), ColorGrade.P400).
                addColor(new Color(0x8BC34A), ColorGrade.P500).
                addColor(new Color(0x7CB342), ColorGrade.P600).
                addColor(new Color(0x689F38), ColorGrade.P700).
                addColor(new Color(0x558B2F), ColorGrade.P800).
                addColor(new Color(0x33691E), ColorGrade.P900).

                addColor(new Color(0xCCFF90), ColorGrade.A100).
                addColor(new Color(0xB2FF59), ColorGrade.A200).
                addColor(new Color(0x76FF03), ColorGrade.A400).
                addColor(new Color(0x64DD17), ColorGrade.A700);
        addColorPatch(lightGreen, ColorName.LIGHTGREEN);

        final ColorPatch lime = new ColorPatch().addColor(new Color(0xF9FBE7), ColorGrade.P50).
                addColor(new Color(0xF0F4C3), ColorGrade.P100).
                addColor(new Color(0xE6EE9C), ColorGrade.P200).
                addColor(new Color(0xDCE775), ColorGrade.P300).
                addColor(new Color(0xD4E157), ColorGrade.P400).
                addColor(new Color(0xCDDC39), ColorGrade.P500).
                addColor(new Color(0xC0CA33), ColorGrade.P600).
                addColor(new Color(0xAFB42B), ColorGrade.P700).
                addColor(new Color(0x9E9D24), ColorGrade.P800).
                addColor(new Color(0x827717), ColorGrade.P900).

                addColor(new Color(0xF4FF81), ColorGrade.A100).
                addColor(new Color(0xEEFF41), ColorGrade.A200).
                addColor(new Color(0xC6FF00), ColorGrade.A400).
                addColor(new Color(0xAEEA00), ColorGrade.A700);
        addColorPatch(lime, ColorName.LIME);

        final ColorPatch yellow = new ColorPatch().addColor(new Color(0xFFFDE7), ColorGrade.P50).
                addColor(new Color(0xFFF9C4), ColorGrade.P100).
                addColor(new Color(0xFFF59D), ColorGrade.P200).
                addColor(new Color(0xFFF176), ColorGrade.P300).
                addColor(new Color(0xFFEE58), ColorGrade.P400).
                addColor(new Color(0xFFEB3B), ColorGrade.P500).
                addColor(new Color(0xFDD835), ColorGrade.P600).
                addColor(new Color(0xFBC02D), ColorGrade.P700).
                addColor(new Color(0xF9A825), ColorGrade.P800).
                addColor(new Color(0xF57F17), ColorGrade.P900).

                addColor(new Color(0xFFFF8D), ColorGrade.A100).
                addColor(new Color(0xFFFF00), ColorGrade.A200).
                addColor(new Color(0xFFEA00), ColorGrade.A400).
                addColor(new Color(0xFFD600), ColorGrade.A700);
        addColorPatch(yellow, ColorName.YELLOW);

        final ColorPatch amber = new ColorPatch().addColor(new Color(0xFFF8E1), ColorGrade.P50).
                addColor(new Color(0xFFE082), ColorGrade.P100).
                addColor(new Color(0xFFE082), ColorGrade.P200).
                addColor(new Color(0xFFD54F), ColorGrade.P300).
                addColor(new Color(0xFFCA28), ColorGrade.P400).
                addColor(new Color(0xFFC107), ColorGrade.P500).
                addColor(new Color(0xFFB300), ColorGrade.P600).
                addColor(new Color(0xFFA000), ColorGrade.P700).
                addColor(new Color(0xFF8F00), ColorGrade.P800).
                addColor(new Color(0xFF6F00), ColorGrade.P900).

                addColor(new Color(0xFFE57F), ColorGrade.A100).
                addColor(new Color(0xFFD740), ColorGrade.A200).
                addColor(new Color(0xFFC400), ColorGrade.A400).
                addColor(new Color(0xFFAB00), ColorGrade.A700);
        addColorPatch(amber, ColorName.AMBER);

        final ColorPatch orange = new ColorPatch().addColor(new Color(0xFFF3E0), ColorGrade.P50).
                addColor(new Color(0xFFE0B2), ColorGrade.P100).
                addColor(new Color(0xFFCC80), ColorGrade.P200).
                addColor(new Color(0xFFB74D), ColorGrade.P300).
                addColor(new Color(0xFFA726), ColorGrade.P400).
                addColor(new Color(0xFF9800), ColorGrade.P500).
                addColor(new Color(0xFB8C00), ColorGrade.P600).
                addColor(new Color(0xF57C00), ColorGrade.P700).
                addColor(new Color(0xEF6C00), ColorGrade.P800).
                addColor(new Color(0xE65100), ColorGrade.P900).

                addColor(new Color(0xFFD180), ColorGrade.A100).
                addColor(new Color(0xFFAB40), ColorGrade.A200).
                addColor(new Color(0xFF9100), ColorGrade.A400).
                addColor(new Color(0xFF6D00), ColorGrade.A700);
        addColorPatch(orange, ColorName.ORANGE);

        final ColorPatch deepOrange = new ColorPatch().addColor(new Color(0xFBE9E7), ColorGrade.P50).
                addColor(new Color(0xFFCCBC), ColorGrade.P100).
                addColor(new Color(0xFFAB91), ColorGrade.P200).
                addColor(new Color(0xFF8A65), ColorGrade.P300).
                addColor(new Color(0xFF7043), ColorGrade.P400).
                addColor(new Color(0xFF5722), ColorGrade.P500).
                addColor(new Color(0xF4511E), ColorGrade.P600).
                addColor(new Color(0xE64A19), ColorGrade.P700).
                addColor(new Color(0xD84315), ColorGrade.P800).
                addColor(new Color(0xBF360C), ColorGrade.P900).

                addColor(new Color(0xFF9E80), ColorGrade.A100).
                addColor(new Color(0xFF6E40), ColorGrade.A200).
                addColor(new Color(0xFF3D00), ColorGrade.A400).
                addColor(new Color(0xDD2C00), ColorGrade.A700);
        addColorPatch(deepOrange, ColorName.DEEPORANGE);

        final ColorPatch brow = new ColorPatch().addColor(new Color(0xEFEBE9), ColorGrade.P50).
                addColor(new Color(0xD7CCC8), ColorGrade.P100).
                addColor(new Color(0xBCAAA4), ColorGrade.P200).
                addColor(new Color(0xA1887F), ColorGrade.P300).
                addColor(new Color(0x795548), ColorGrade.P500).
                addColor(new Color(0x6D4C41), ColorGrade.P600).
                addColor(new Color(0x5D4037), ColorGrade.P700).
                addColor(new Color(0x4E342E), ColorGrade.P800).
                addColor(new Color(0x3E2723), ColorGrade.P900).

                addColor(new Color(0xFFFFFF), ColorGrade.A100).
                addColor(new Color(0xFFFFFF), ColorGrade.A200).
                addColor(new Color(0xFFFFFF), ColorGrade.A400).
                addColor(new Color(0xFFFFFF), ColorGrade.A700);
        addColorPatch(brow, ColorName.BROWN);

        final ColorPatch grey = new ColorPatch().addColor(new Color(0xFAFAFA), ColorGrade.P50).
                addColor(new Color(0xF5F5F5), ColorGrade.P100).
                addColor(new Color(0xEEEEEE), ColorGrade.P200).
                addColor(new Color(0xE0E0E0), ColorGrade.P300).
                addColor(new Color(0xBDBDBD), ColorGrade.P400).
                addColor(new Color(0x9E9E9E), ColorGrade.P500).
                addColor(new Color(0x757575), ColorGrade.P600).
                addColor(new Color(0x616161), ColorGrade.P700).
                addColor(new Color(0x424242), ColorGrade.P800).
                addColor(new Color(0x212121), ColorGrade.P900).

                addColor(new Color(0x303030), ColorGrade.A100).
                addColor(new Color(0xFFFFFF), ColorGrade.A200).
                addColor(new Color(0xFFFFFF), ColorGrade.A400).
                addColor(new Color(0xFFFFFF), ColorGrade.A700);
        addColorPatch(grey, ColorName.GREY);

        final ColorPatch blueGrey = new ColorPatch().addColor(new Color(0xECEFF1), ColorGrade.P50).
                addColor(new Color(0xCFD8DC), ColorGrade.P100).
                addColor(new Color(0xB0BEC5), ColorGrade.P200).
                addColor(new Color(0x90A4AE), ColorGrade.P300).
                addColor(new Color(0x78909C), ColorGrade.P400).
                addColor(new Color(0x607D8B), ColorGrade.P500).
                addColor(new Color(0x546E7A), ColorGrade.P600).
                addColor(new Color(0x455A64), ColorGrade.P700).
                addColor(new Color(0x37474F), ColorGrade.P800).
                addColor(new Color(0x263238), ColorGrade.P900).

                addColor(new Color(0xFFFFFF), ColorGrade.A100).
                addColor(new Color(0xFFFFFF), ColorGrade.A200).
                addColor(new Color(0xFFFFFF), ColorGrade.A400).
                addColor(new Color(0xFFFFFF), ColorGrade.A700);
        addColorPatch(blueGrey, ColorName.BLUEGREY);

        final ColorPatch black = new ColorPatch().addColor(new Color(0x000000), ColorGrade.P50);
        addColorPatch(black, ColorName.BLACK);

        final ColorPatch white = new ColorPatch().addColor(new Color(0xFFFFFF), ColorGrade.P50);
        addColorPatch(white, ColorName.WHITE);
    }

    public static Color getColor(ColorName name, ColorGrade grade) {
        return colorNames.get(name).getColor(grade);
    }

    private static void addColorPatch(ColorPatch patch, ColorName name) {
        colorNames.put(name, patch);
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
