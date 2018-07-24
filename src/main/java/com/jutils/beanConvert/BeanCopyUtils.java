package com.jutils.beanConvert;

import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class BeanCopyUtils {

	/**
	 * Dozer 是一个 Java Bean mapper 可以很容易将一个对象按照属性map到另外一个对象
	 *
	 * 如果遇到对象属性名不一致，可以在 get方法上使用 @Mapping 注解
	 *
	 * 或者使用 BeanMappingBuilder() 来构造自定义 Mapping
	 *
	 * 或者可以实现 CustomConverter 来自己做转换
	 */
	private static final Mapper mapper = new DozerBeanMapper();

	public static <T> T map(Object src, Class<T> dstClass) {
		return mapper.map(src, dstClass);
	}

	public static <T> List<T> mapList(Collection srcList, Class<T> dstClass) {
		List<T> dstList = Lists.newArrayList();
		Iterator iterator = srcList.iterator();
		while (iterator.hasNext()) {
			Object srcObject = iterator.next();
			T dstObject = mapper.map(srcObject, dstClass);
			dstList.add(dstObject);
		}
		return dstList;
	}

}
