package com.configs;

/**
 * 解决@ResponseBody返回的响应中中文乱码问题.
 */
/*@Component
public class EncodingPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof RequestMappingHandlerAdapter) {
			List<HttpMessageConverter<?>> convs = ((RequestMappingHandlerAdapter) bean).getMessageConverters();
			for (HttpMessageConverter<?> conv : convs) {
				if (conv instanceof StringHttpMessageConverter) {
					((StringHttpMessageConverter) conv)
							.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "html", Charset.forName("UTF-8"))));
				}
			}
		}
		if (bean instanceof RequestResponseBodyMethodProcessor) {
			List<HttpMessageConverter<?>> convs = ((RequestMappingHandlerAdapter) bean).getMessageConverters();
			for (HttpMessageConverter<?> conv : convs) {
				if (conv instanceof StringHttpMessageConverter) {
					((StringHttpMessageConverter) conv)
							.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "html", Charset.forName("UTF-8"))));
				}
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
}*/