package com.dianping.cat.configuration.app.comparison.transform;

import static com.dianping.cat.configuration.app.comparison.Constants.ATTR_COMMAND;
import static com.dianping.cat.configuration.app.comparison.Constants.ATTR_EMAILS;
import static com.dianping.cat.configuration.app.comparison.Constants.ATTR_ID;

import org.xml.sax.Attributes;

import com.dianping.cat.configuration.app.comparison.entity.AppComparison;
import com.dianping.cat.configuration.app.comparison.entity.AppComparisonConfig;
import com.dianping.cat.configuration.app.comparison.entity.Item;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public AppComparison buildAppComparison(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String emails = attributes.getValue(ATTR_EMAILS);
      AppComparison appComparison = new AppComparison(id);

      if (emails != null) {
         appComparison.setEmails(emails);
      }

      return appComparison;
   }

   @Override
   public AppComparisonConfig buildAppComparisonConfig(Attributes attributes) {
      AppComparisonConfig appComparisonConfig = new AppComparisonConfig();

      return appComparisonConfig;
   }

   @Override
   public Item buildItem(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String command = attributes.getValue(ATTR_COMMAND);
      Item item = new Item();

      if (id != null) {
         item.setId(id);
      }

      if (command != null) {
         item.setCommand(command);
      }

      return item;
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
