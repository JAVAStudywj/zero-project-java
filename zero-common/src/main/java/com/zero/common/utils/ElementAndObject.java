package com.zero.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.regex.Pattern;

public class ElementAndObject {
	// Log
	private static Logger logger = LoggerFactory.getLogger(ElementAndObject.class);

	public static boolean getMyType(Object value,String fieldName, Object objectBean) {
		try {
			value = matcherValue(value);
			Class args[] = {String.class} ;
			if (null != value) {
				if (value instanceof Date) {
					args[0] = Date.class;
				} else if (value instanceof java.sql.Timestamp) {
					args[0] = java.sql.Timestamp.class;
				} else if (value instanceof Double) {
					args[0] = Double.TYPE;
//					args[0] =  Double.class;
				} else if (value instanceof Integer) {
					args[0] = Integer.TYPE;
				} else if (value instanceof Float) {
					args[0] = Float.TYPE;
				} else if (value instanceof Boolean) {
					args[0] = String.class;
					value = (Boolean)value==true?"X":"";
				}else if(value instanceof Short){
					args[0] = Short.TYPE;
				} else if (value instanceof BigDecimal) {
					args[0] = BigDecimal.class;
				} else if (value instanceof Long) {
					args[0] = Long.TYPE;
				} else if (value instanceof java.sql.Time) {
					args[0] = java.sql.Time.class;
				} else {
						args[0] = String.class;
				}
			}
			System.out.println("value"+value);
			Method method = objectBean.getClass().getMethod("set"+fieldName, args);
			method.invoke(objectBean, value);
			return true;
//		} catch (com.sap.tc.webdynpro.progmodel.context.ContextException e) {
//			return true;
		}catch (Exception e) {
			return false;
		}
	}

	/**
	 * �?索并处理对象中的特殊字符
	 *
	 * @param o
	 *            �?要处理的对象
	 * @return 处理后的对象
	 */
	public static Object matcherValue(Object o) {
		if (null != o) {
			if (o instanceof String) {
				String tempStr = o.toString();
				o = Pattern.compile("\\Q'\\E").matcher(tempStr)
						.replaceAll("''");
			}
		}
		return o;
	}

	public static boolean setMyType(Object value, Object object, String fieldName) {
		try {
			value = matcherValue(value);
			Class args[] = {String.class} ;
			if (null != value) {
				if (value instanceof Date) {
					args[0] = Date.class;
				} else if(value instanceof java.util.Date){
					args[0] = java.util.Date.class;
				}else if (value instanceof java.sql.Timestamp) {
					args[0] = java.sql.Timestamp.class;
				} else if (value instanceof Double) {
					args[0] = Double.TYPE;
				} else if (value instanceof Integer) {
					args[0] = Integer.class;
				} else if (value instanceof Float) {
					args[0] = Float.TYPE;
				} else if (value instanceof Boolean) {
					args[0] = String.class;
					value = (Boolean)value==true?"X":"";
				}else if(value instanceof Short){
					args[0] = Short.TYPE;
				} else if (value instanceof BigDecimal) {
					args[0] = BigDecimal.class;
				} else if (value instanceof Long) {
					args[0] = Long.TYPE;
				} else if (value instanceof java.sql.Time) {
					args[0] = java.sql.Time.class;
				} else {
					args[0] = String.class;
				}
			}
			Method method = object.getClass().getMethod("set"+fieldName, args); 
			method.invoke(object, value);
			return true;
//		} catch (com.sap.tc.webdynpro.progmodel.context.ContextException e) {
//			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @param object
	 * @param objectBean
	 */
	public static void getElementByObject(Object object, Object objectBean) {
		Method[] method = object.getClass().getDeclaredMethods();
		for (Method m : method) {
			String methodName = m.getName();
			if(!methodName.startsWith("get")) {//非get方法�?律放�?
				continue;
			}
			String str = "get";
			try {
				m.setAccessible(true);
				Object invoke = m.invoke(object, null);
				if(methodName!=null&&!"".equals(methodName)) {
					int index = methodName.indexOf(str);
					if(index!= -1) {
						String fieldName = methodName.substring(index+3);
						getMyType(invoke, fieldName, objectBean);
					}
				}
			} catch (IllegalArgumentException e) {
				logger.error("IllegalArgumentException while invoke:", e);
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException while invoke:", e);
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				logger.error("/error InvocationTargetException,methodName:"+methodName);
			}
		}
	}
	
	/**
	 * 根據傳入的objectBean，組裝成對應的object
	 * @param objectBean
	 * @param object
	 */
	public static void setObjectByElement( Object objectBean, Object object){
		try{
			Method[] methods = objectBean.getClass().getMethods();
			for (Method method : methods) {
				String methodName = method.getName();
				if(!methodName.startsWith("get")) {//非get方法�?律放�?
					continue;
				}
				String fieldName = null;
				Object getValue = null;
				int indexOf = methodName.indexOf("get");
				if(indexOf!= -1) {
					fieldName = methodName.substring(indexOf+3);
				}
				try {
					method.setAccessible(true);
					getValue = method.invoke(objectBean, null);
					if(getValue!=null){
						System.out.println(fieldName+": "+getValue);
					}
				} catch (Exception e) {
					continue;
				} finally {
				}
				setMyType(getValue,object,fieldName);
			}
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
}
