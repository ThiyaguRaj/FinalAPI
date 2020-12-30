package com.onebill.productbilling.JunitControllerTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.onebill.productbilling.service.ProductServiceImpl;
import com.onebill.productbilling.servicebuilder.ProductServiceFactory;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames="com.onebill.productbilling.servicebuilder")
public class TestServiceFactory {

	@Test
	public void testService() {
		PowerMockito.mockStatic(ProductServiceFactory.class);
		Mockito.when(ProductServiceFactory.createProductService()).thenReturn(new ProductServiceImpl());
		Assert.assertNotNull(ProductServiceFactory.createProductService());
	}

}
