package com.dianping.cat.consumer.metric.config.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.consumer.metric.config.BaseEntity;
import com.dianping.cat.consumer.metric.config.IVisitor;

public class MetricConfig extends BaseEntity<MetricConfig> {
   private Map<String, MetricItemConfig> m_metricItemConfigs = new LinkedHashMap<String, MetricItemConfig>();

   public MetricConfig() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitMetricConfig(this);
   }

   public MetricConfig addMetricItemConfig(MetricItemConfig metricItemConfig) {
      m_metricItemConfigs.put(metricItemConfig.getId(), metricItemConfig);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof MetricConfig) {
         MetricConfig _o = (MetricConfig) obj;

         if (!equals(getMetricItemConfigs(), _o.getMetricItemConfigs())) {
            return false;
         }


         return true;
      }

      return false;
   }

   public MetricItemConfig findMetricItemConfig(String id) {
      return m_metricItemConfigs.get(id);
   }

   public MetricItemConfig findOrCreateMetricItemConfig(String id) {
      MetricItemConfig metricItemConfig = m_metricItemConfigs.get(id);

      if (metricItemConfig == null) {
         synchronized (m_metricItemConfigs) {
            metricItemConfig = m_metricItemConfigs.get(id);

            if (metricItemConfig == null) {
               metricItemConfig = new MetricItemConfig(id);
               m_metricItemConfigs.put(id, metricItemConfig);
            }
         }
      }

      return metricItemConfig;
   }

   public Map<String, MetricItemConfig> getMetricItemConfigs() {
      return m_metricItemConfigs;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_metricItemConfigs == null ? 0 : m_metricItemConfigs.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(MetricConfig other) {
   }

   public MetricItemConfig removeMetricItemConfig(String id) {
      return m_metricItemConfigs.remove(id);
   }

}
