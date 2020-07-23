/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.constant   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月12日下午1:37:24
 * @version V1.0
 */

package com.modules.mail.constants;

/**
 * constants
 * 
 * @author: Frankjiu
 * @date: 2020年4月12日 下午1:37:24
 */

public class Constants {

	public class EmailConstants {
		public static final String EMAIL_TO = "xinbeijing@yeah.net";
	}

	public class ConstantClassField {
		public static final String SUNDAY = "SUNDAY";
		public static final String MONDAY = "MONDAY";
		public static final String TUESDAY = "TUESDAY";
		public static final String WEDNESDAY = "WEDNESDAY";
		public static final String THURSDAY = "THURSDAY";
		public static final String FRIDAY = "FRIDAY";
		public static final String SATURDAY = "SATURDAY";
	}

	public static enum YesOrNo {
		NO(0, "否"), YES(1, "是");
		private YesOrNo(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		private final Integer value;
		private final String name;

		public Integer getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
	}

	public enum FreezeType {
		// 枚举类实例(枚举元素)
		NO(0, "未冻结"), YES(1, "已冻结");
		// 私有枚举类属性
		private Integer code;
		private String desc;

		// 公有set/get方法
		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		// 私有带参构造
		private FreezeType(Integer code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		// 根据code获取枚举类实例
		public static FreezeType getTypeByCode(Integer code) {
			// 默认返回值
			FreezeType defaultType = FreezeType.NO;
			// 遍历枚举值(枚举实例)
			for (FreezeType ftype : FreezeType.values()) {
				if (ftype.code == code) {
					return ftype;
				}
			}
			return defaultType;
		}

		// 根据code获取描述
		public static String getDescByCode(Integer code) {
			return getTypeByCode(code).desc;
		}

		public static void main(String[] args) {
			System.out.println("----------------------------");
			System.out.println(FreezeType.getDescByCode(0));
		}
	}

}
