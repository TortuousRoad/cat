package com.dianping.cat.configuration.server.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.configuration.server.BaseEntity;
import com.dianping.cat.configuration.server.IVisitor;

public class LongConfig extends BaseEntity<LongConfig> {
   private Integer m_defaultUrlThreshold = 1000;

   private Integer m_defaultSqlThreshold = 100;

   private Integer m_defaultServiceThreshold = 50;

   private Map<String, Domain> m_domains = new LinkedHashMap<String, Domain>();

   public LongConfig() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitLongConfig(this);
   }

   public LongConfig addDomain(Domain domain) {
      m_domains.put(domain.getName(), domain);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof LongConfig) {
         LongConfig _o = (LongConfig) obj;

         if (!equals(getDefaultUrlThreshold(), _o.getDefaultUrlThreshold())) {
            return false;
         }

         if (!equals(getDefaultSqlThreshold(), _o.getDefaultSqlThreshold())) {
            return false;
         }

         if (!equals(getDefaultServiceThreshold(), _o.getDefaultServiceThreshold())) {
            return false;
         }

         if (!equals(getDomains(), _o.getDomains())) {
            return false;
         }


         return true;
      }

      return false;
   }

   public Domain findDomain(String name) {
      return m_domains.get(name);
   }

   public Integer getDefaultServiceThreshold() {
      return m_defaultServiceThreshold;
   }

   public Integer getDefaultSqlThreshold() {
      return m_defaultSqlThreshold;
   }

   public Integer getDefaultUrlThreshold() {
      return m_defaultUrlThreshold;
   }

   public Map<String, Domain> getDomains() {
      return m_domains;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_defaultUrlThreshold == null ? 0 : m_defaultUrlThreshold.hashCode());
      hash = hash * 31 + (m_defaultSqlThreshold == null ? 0 : m_defaultSqlThreshold.hashCode());
      hash = hash * 31 + (m_defaultServiceThreshold == null ? 0 : m_defaultServiceThreshold.hashCode());
      hash = hash * 31 + (m_domains == null ? 0 : m_domains.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(LongConfig other) {
      if (other.getDefaultUrlThreshold() != null) {
         m_defaultUrlThreshold = other.getDefaultUrlThreshold();
      }

      if (other.getDefaultSqlThreshold() != null) {
         m_defaultSqlThreshold = other.getDefaultSqlThreshold();
      }

      if (other.getDefaultServiceThreshold() != null) {
         m_defaultServiceThreshold = other.getDefaultServiceThreshold();
      }
   }

   public Domain removeDomain(String name) {
      return m_domains.remove(name);
   }

   public LongConfig setDefaultServiceThreshold(Integer defaultServiceThreshold) {
      m_defaultServiceThreshold = defaultServiceThreshold;
      return this;
   }

   public LongConfig setDefaultSqlThreshold(Integer defaultSqlThreshold) {
      m_defaultSqlThreshold = defaultSqlThreshold;
      return this;
   }

   public LongConfig setDefaultUrlThreshold(Integer defaultUrlThreshold) {
      m_defaultUrlThreshold = defaultUrlThreshold;
      return this;
   }

}
