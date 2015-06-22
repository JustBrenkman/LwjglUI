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
/**
 * Created at 4:25:42 AM Jul 15, 2010
 */
package org.jbox2d.callbacks;

import org.jbox2d.dynamics.Filter;
import org.jbox2d.dynamics.Fixture;

// updated to rev 100
/**
 * Implement this class to provide collision filtering. In other words, you can implement
 * this class if you want finer control over contact creation.
 * @author Daniel Murphy
 */
public class ContactFilter {

	/**
	 * Return true if contact calculations should be performed between these two shapes.
	 * @warning for performance reasons this is only called when the AABBs begin to overlap.
	 * @param fixtureA
	 * @param fixtureB
	 * @return
	 */
	public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB){
		Filter filterA = fixtureA.getFilterData();
		Filter filterB = fixtureB.getFilterData();

		if (filterA.groupIndex == filterB.groupIndex && filterA.groupIndex != 0){
			return filterA.groupIndex > 0;
		}

		boolean collide = (filterA.maskBits & filterB.categoryBits) != 0 &&
						  (filterA.categoryBits & filterB.maskBits) != 0;
		return collide;
	}
}
