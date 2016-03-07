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

package com.github.pfichtner.core;

import java.net.URI;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.pfichtner.ardulink.core.linkmanager.LinkManager;
import com.github.pfichtner.ardulink.core.linkmanager.LinkManager.Configurer;

/**
 * [ardulinktitle] [ardulinkversion]
 * 
 * project Ardulink http://www.ardulink.org/
 * 
 * [adsense]
 *
 */
public class PiLinkTest {

	@Rule
	public ExpectedException exceptions = ExpectedException.none();

	@Test
	public void creatingInstanceWillFailOnX86withUnsatisfiedLinkError()
			throws Exception {
		// TODO should do a Assume if we are on a raspi or not
		Configurer configurer = LinkManager.getInstance().getConfigurer(
				new URI("ardulink://raspberry"));
		exceptions.expect(UnsatisfiedLinkError.class);
		configurer.newLink();
	}

}
