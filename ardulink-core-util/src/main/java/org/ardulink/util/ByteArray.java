/**
Copyright 2013 project Ardulink http://www.ardulink.org/
 
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
 
    http://www.apache.org/licenses/LICENSE-2.0
 
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package org.ardulink.util;

import static org.ardulink.util.Preconditions.checkNotNull;
import static org.ardulink.util.Preconditions.checkState;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class ByteArray {

	private byte[] byteArray;
	private int lastFoundIndex;

	public ByteArray(byte[] byteArray) {
		checkNotNull(byteArray, "Array can't be null");
		this.byteArray = byteArray;
	}

	public ByteArray(ByteArrayOutputStream os) {
		this(os.toByteArray());
	}

	public void resetWith(byte[] byteArray) {
		checkNotNull(byteArray, "Array can't be null");

		this.byteArray = byteArray;
		this.lastFoundIndex = 0;
	}

	public boolean contains(byte[] delimiter) {
		checkNotNull(delimiter, "delimiter must not be null");
		checkState(delimiter.length > 0, "delimiter must not be empty");

		for (int i = 0; i < byteArray.length - delimiter.length + 1; i++) {
			if (inByteArray(delimiter, i)) {
				return true;
			}
		}

		return false;
	}

	private boolean inByteArray(byte[] delimiter, int pos) {
		for (int i = 0; i < delimiter.length; i++) {
			if (byteArray[pos + i] != delimiter[i]) {
				return false;
			}

		}
		lastFoundIndex = pos;
		return true;
	}

	public byte[] next(byte[] delimiter) {

		if (!contains(delimiter)) {
			return null;
		}

		byte[] retvalue = Arrays.copyOfRange(byteArray, 0, lastFoundIndex);
		byteArray = Arrays.copyOfRange(byteArray, lastFoundIndex
				+ delimiter.length, byteArray.length);

		return retvalue;
	}

	public int size() {
		return byteArray.length;
	}

	/**
	 * 
	 * @return the array (WARNING modifies to the returned array are shared with
	 *         the ByteArray object)
	 */
	public byte[] getRemainingBytes() {
		return byteArray;
	}

}