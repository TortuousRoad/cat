package com.dianping.cat.home.activity.entity;

import com.dianping.cat.home.activity.BaseEntity;
import com.dianping.cat.home.activity.IVisitor;

public class Activity extends BaseEntity<Activity> {
   private String m_domain;

   private String m_type;

   private String m_name;

   private String m_title;

   public Activity() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitActivity(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Activity) {
         Activity _o = (Activity) obj;

         if (!equals(getDomain(), _o.getDomain())) {
            return false;
         }

         if (!equals(getType(), _o.getType())) {
            return false;
         }

         if (!equals(getName(), _o.getName())) {
            return false;
         }

         if (!equals(getTitle(), _o.getTitle())) {
            return false;
         }


         return true;
      }

      return false;
   }

   public String getDomain() {
      return m_domain;
   }

   public String getName() {
      return m_name;
   }

   public String getTitle() {
      return m_title;
   }

   public String getType() {
      return m_type;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_domain == null ? 0 : m_domain.hashCode());
      hash = hash * 31 + (m_type == null ? 0 : m_type.hashCode());
      hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());
      hash = hash * 31 + (m_title == null ? 0 : m_title.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Activity other) {
      if (other.getDomain() != null) {
         m_domain = other.getDomain();
      }

      if (other.getType() != null) {
         m_type = other.getType();
      }

      if (other.getName() != null) {
         m_name = other.getName();
      }

      if (other.getTitle() != null) {
         m_title = other.getTitle();
      }
   }

   public Activity setDomain(String domain) {
      m_domain = domain;
      return this;
   }

   public Activity setName(String name) {
      m_name = name;
      return this;
   }

   public Activity setTitle(String title) {
      m_title = title;
      return this;
   }

   public Activity setType(String type) {
      m_type = type;
      return this;
   }

}
