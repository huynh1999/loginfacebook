/*
	@author:Quang Truong
	@date: Jan 21, 2020
*/

package com.jwatgroupb.util;

import java.util.UUID;

public class RandomStringUtil {

	public static String Random() {
		return UUID.randomUUID().toString();
	}
}
