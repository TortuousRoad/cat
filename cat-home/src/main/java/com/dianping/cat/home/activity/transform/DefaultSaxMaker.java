package com.dianping.cat.home.activity.transform;

import static com.dianping.cat.home.activity.Constants.ATTR_DOMAIN;
import static com.dianping.cat.home.activity.Constants.ATTR_NAME;
import static com.dianping.cat.home.activity.Constants.ATTR_TITLE;
import static com.dianping.cat.home.activity.Constants.ATTR_TYPE;

import org.xml.sax.Attributes;

import com.dianping.cat.home.activity.entity.Activity;
import com.dianping.cat.home.activity.entity.ActivityConfig;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public Activity buildActivity(Attributes attributes) {
      String domain = attributes.getValue(ATTR_DOMAIN);
      String type = attributes.getValue(ATTR_TYPE);
      String name = attributes.getValue(ATTR_NAME);
      String title = attributes.getValue(ATTR_TITLE);
      Activity activity = new Activity();

      if (domain != null) {
         activity.setDomain(domain);
      }

      if (type != null) {
         activity.setType(type);
      }

      if (name != null) {
         activity.setName(name);
      }

      if (title != null) {
         activity.setTitle(title);
      }

      return activity;
   }

   @Override
   public ActivityConfig buildActivityConfig(Attributes attributes) {
      ActivityConfig activityConfig = new ActivityConfig();

      return activityConfig;
   }

   @SuppressWarnings("unchecked")
   protected <T> T convert(Class<T> type, String value, T defaultValue) {
      if (value == null || value.length() == 0) {
         return defaultValue;
      }

      if (type == Boolean.class || type == Boolean.TYPE) {
         return (T) Boolean.valueOf(value);
      } else if (type == Integer.class || type == Integer.TYPE) {
         return (T) Integer.valueOf(value);
      } else if (type == Long.class || type == Long.TYPE) {
         return (T) Long.valueOf(value);
      } else if (type == Short.class || type == Short.TYPE) {
         return (T) Short.valueOf(value);
      } else if (type == Float.class || type == Float.TYPE) {
         return (T) Float.valueOf(value);
      } else if (type == Double.class || type == Double.TYPE) {
         return (T) Double.valueOf(value);
      } else if (type == Byte.class || type == Byte.TYPE) {
         return (T) Byte.valueOf(value);
      } else if (type == Character.class || type == Character.TYPE) {
         return (T) (Character) value.charAt(0);
      } else {
         return (T) value;
      }
   }
}
