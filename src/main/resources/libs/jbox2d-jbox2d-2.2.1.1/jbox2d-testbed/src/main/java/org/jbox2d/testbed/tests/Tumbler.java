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

package org.jbox2d.testbed.tests;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.joints.RevoluteJoint;
import org.jbox2d.dynamics.joints.RevoluteJointDef;
import org.jbox2d.testbed.framework.TestbedSettings;
import org.jbox2d.testbed.framework.TestbedTest;

public class Tumbler extends TestbedTest {
  private static final int MAX_NUM = 800;
  RevoluteJoint m_joint;
  int m_count;

  @Override
  public void initTest(boolean deserialized) {
    {
      BodyDef bd = new BodyDef();
      bd.type = BodyType.DYNAMIC;
      bd.allowSleep = false;
      bd.position.set(0.0f, 10.0f);
      Body body = m_world.createBody(bd);

      PolygonShape shape = new PolygonShape();
      shape.setAsBox(0.5f, 10.0f, new Vec2(10.0f, 0.0f), 0.0f);
      body.createFixture(shape, 5.0f);
      shape.setAsBox(0.5f, 10.0f, new Vec2(-10.0f, 0.0f), 0.0f);
      body.createFixture(shape, 5.0f);
      shape.setAsBox(10.0f, 0.5f, new Vec2(0.0f, 10.0f), 0.0f);
      body.createFixture(shape, 5.0f);
      shape.setAsBox(10.0f, 0.5f, new Vec2(0.0f, -10.0f), 0.0f);
      body.createFixture(shape, 5.0f);

      RevoluteJointDef jd = new RevoluteJointDef();
      jd.bodyA = getGroundBody();
      jd.bodyB = body;
      jd.localAnchorA.set(0.0f, 10.0f);
      jd.localAnchorB.set(0.0f, 0.0f);
      jd.referenceAngle = 0.0f;
      jd.motorSpeed = 0.05f * MathUtils.PI;
      jd.maxMotorTorque = 1e8f;
      jd.enableMotor = true;
      m_joint = (RevoluteJoint) m_world.createJoint(jd);
    }
    m_count = 0;
  }

  @Override
  public synchronized void step(TestbedSettings settings) {
    super.step(settings);

    if (m_count < MAX_NUM) {
      BodyDef bd = new BodyDef();
      bd.type = BodyType.DYNAMIC;
      bd.position.set(0.0f, 10.0f);
      Body body = m_world.createBody(bd);

      PolygonShape shape = new PolygonShape();
      shape.setAsBox(0.125f, 0.125f);
      body.createFixture(shape, 1.0f);

      ++m_count;
    }
  }

  @Override
  public String getTestName() {
    return "Tumbler";
  }
}
