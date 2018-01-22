package com.dianping.cat.consumer.metric.config.entity;

import com.dianping.cat.consumer.metric.config.BaseEntity;
import com.dianping.cat.consumer.metric.config.IVisitor;

public class Tag extends BaseEntity<Tag> {
   private String m_name;

   private String m_type;

   public Tag() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitTag(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Tag) {
         Tag _o = (Tag) obj;

         if (!equals(getName(), _o.getName())) {
            return false;
         }

         if (!equals(getType(), _o.getType())) {
            return false;
         }


         return true;
      }

      return false;
   }

   public String getName() {
      return m_name;
   }

   public String getType() {
      return m_type;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());
      hash = hash * 31 + (m_type == null ? 0 : m_type.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Tag other) {
      if (other.getName() != null) {
         m_name = other.getName();
      }

      if (other.getType() != null) {
         m_type = other.getType();
      }
   }

   public Tag setName(String name) {
      m_name = name;
      return this;
   }

   public Tag setType(String type) {
      m_type = type;
      return this;
   }

}
