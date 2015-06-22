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

package org.jbox2d.profile;

import org.jbox2d.common.Settings;

public abstract class SettingsPerformanceTest extends BasicPerformanceTest {

  private static int NUM_TESTS = 8;

  public SettingsPerformanceTest(int iters) {
    super(NUM_TESTS, iters);
  }

  @Override
  public void runTest(int testNum) {
    Settings.FAST_ABS = testNum == 1;
    Settings.FAST_ATAN2 = testNum == 2;
    Settings.FAST_CEIL = testNum == 3;
    Settings.FAST_FLOOR = testNum == 4;
    Settings.FAST_ROUND = testNum == 5;
    Settings.SINCOS_LUT_ENABLED = testNum == 6;

    if (testNum == 7) {
      Settings.FAST_ABS = true;
      Settings.FAST_ATAN2 = true;
      Settings.FAST_CEIL = true;
      Settings.FAST_FLOOR = true;
      Settings.FAST_ROUND = true;
      Settings.SINCOS_LUT_ENABLED = true;
    }

    runBenchmarkWorld();
  }

  public abstract void runBenchmarkWorld();

  @Override
  public String getTestName(int testNum) {
    switch (testNum) {
      case 0:
        return "No optimizations";
      case 1:
        return "Fast abs";
      case 2:
        return "Fast atan2";
      case 3:
        return "Fast ceil";
      case 4:
        return "Fast floor";
      case 5:
        return "Fast round";
      case 6:
        return "Sincos lookup table";
      case 7:
        return "All optimizations on";
      default:
        return "";
    }
  }
}
