package ru.bedward70.neuron;

import junit.framework.TestCase;
import ru.bedward70.neuron.function.ResultFunctionType;
import ru.bedward70.neuron.layer.NeronLayer;


import java.util.ArrayList;

public class NeronLayerTest extends TestCase {

	public void test() throws Exception {

		final NeronLayer neronLayer = NeronLayer.create(ResultFunctionType.LINEAR, 2, new ArrayList<Number>() {{add(5.);add(4.);}}, true);

		System.out.println(neronLayer);

//		assertEquals(22., neuron.doubleValue());
	}
}
