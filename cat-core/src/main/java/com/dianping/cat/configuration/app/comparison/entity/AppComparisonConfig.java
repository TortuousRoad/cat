package com.dianping.cat.configuration.app.comparison.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.configuration.app.comparison.BaseEntity;
import com.dianping.cat.configuration.app.comparison.IVisitor;

public class AppComparisonConfig extends BaseEntity<AppComparisonConfig> {
   private Map<String, AppComparison> m_appComparisons = new LinkedHashMap<String, AppComparison>();

   public AppComparisonConfig() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitAppComparisonConfig(this);
   }

   public AppComparisonConfig addAppComparison(AppComparison appComparison) {
      m_appComparisons.put(appComparison.getId(), appComparison);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof AppComparisonConfig) {
         AppComparisonConfig _o = (AppComparisonConfig) obj;

         if (!equals(getAppComparisons(), _o.getAppComparisons())) {
            return false;
         }


         return true;
      }

      return false;
   }

   public AppComparison findAppComparison(String id) {
      return m_appComparisons.get(id);
   }

   public Map<String, AppComparison> getAppComparisons() {
      return m_appComparisons;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_appComparisons == null ? 0 : m_appComparisons.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(AppComparisonConfig other) {
   }

   public AppComparison removeAppComparison(String id) {
      return m_appComparisons.remove(id);
   }

}
