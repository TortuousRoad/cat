package com.dianping.cat.home.alert.thirdparty.entity;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.alert.thirdparty.BaseEntity;
import com.dianping.cat.home.alert.thirdparty.IVisitor;

public class Http extends BaseEntity<Http> {
   private String m_url;

   private String m_type;

   private String m_domain;

   private List<Par> m_pars = new ArrayList<Par>();

   public Http() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitHttp(this);
   }

   public Http addPar(Par par) {
      m_pars.add(par);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Http) {
         Http _o = (Http) obj;

         if (!equals(getUrl(), _o.getUrl())) {
            return false;
         }

         if (!equals(getType(), _o.getType())) {
            return false;
         }

         if (!equals(getDomain(), _o.getDomain())) {
            return false;
         }

         if (!equals(getPars(), _o.getPars())) {
            return false;
         }


         return true;
      }

      return false;
   }

   public String getDomain() {
      return m_domain;
   }

   public List<Par> getPars() {
      return m_pars;
   }

   public String getType() {
      return m_type;
   }

   public String getUrl() {
      return m_url;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_url == null ? 0 : m_url.hashCode());
      hash = hash * 31 + (m_type == null ? 0 : m_type.hashCode());
      hash = hash * 31 + (m_domain == null ? 0 : m_domain.hashCode());
      for (Par e : m_pars) {
         hash = hash * 31 + (e == null ? 0 :e.hashCode());
      }


      return hash;
   }

   @Override
   public void mergeAttributes(Http other) {
      if (other.getUrl() != null) {
         m_url = other.getUrl();
      }

      if (other.getType() != null) {
         m_type = other.getType();
      }

      if (other.getDomain() != null) {
         m_domain = other.getDomain();
      }
   }

   public Http setDomain(String domain) {
      m_domain = domain;
      return this;
   }

   public Http setType(String type) {
      m_type = type;
      return this;
   }

   public Http setUrl(String url) {
      m_url = url;
      return this;
   }

}
