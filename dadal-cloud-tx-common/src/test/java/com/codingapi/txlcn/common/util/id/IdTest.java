package com.codingapi.txlcn.common.util.id;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Description: Date: 19-2-22 下午3:35
 *
 * @author ujued
 */
@Ignore
@RunWith(JUnit4.class)
public class IdTest {

	@Test
	public void testDefaultIdGen() {
		IdGenInit.applyDefaultIdGen(12, 1);

		for (int i = 0; i < 10; i++) {
			System.out.println(RandomUtils.randomKey());
		}
	}
}
