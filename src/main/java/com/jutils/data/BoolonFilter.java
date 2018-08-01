package com.jutils.data;

public class BoolonFilter {

	/**
	 * 布隆过滤
	 *
	 * 一个布隆过滤器精确的表示一个集合，可以精确判断一个元素是否在集合中。精确只取决于设计。
	 *
	 * 布隆过滤器的优势在于使用很少的空间就可以将准确率提高到很高的水平。
	 *
	 * 哈希函数，输入域是非常大的范围，比如字符串，但是输出域是固定范围的值
	 *
	 * <li>典型的哈希函数有无限的输入域</li>
	 * <li>输入相同值输出相同</li>
	 * <li>当输入不同值，返回可能一样，也可能不一致</li>
	 * <li>很多不同的输入值所得到的返回值会均匀的分布</li>
	 *
	 * 第4点是评价一个哈希函数优劣的关键
	 */
	public static void main(String[] args) {

	}
}
