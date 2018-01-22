package com.dianping.cat.home.network.entity;

import com.dianping.cat.home.network.BaseEntity;
import com.dianping.cat.home.network.IVisitor;

public class Interface extends BaseEntity<Interface> {
   private String m_group;

   private String m_domain;

   private String m_key;

   private Integer m_instate;

   private Integer m_outstate;

   private Double m_in;

   private Double m_out;

   private Integer m_inDiscardsState;

   private Integer m_outDiscardsState;

   private Integer m_inErrorsState;

   private Integer m_outErrorsState;

   private Double m_inDiscards;

   private Double m_outDiscards;

   private Double m_inErrors;

   private Double m_outErrors;

   public Interface() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitInterface(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Interface) {
         Interface _o = (Interface) obj;

         if (!equals(getGroup(), _o.getGroup())) {
            return false;
         }

         if (!equals(getDomain(), _o.getDomain())) {
            return false;
         }

         if (!equals(getKey(), _o.getKey())) {
            return false;
         }

         if (!equals(getInstate(), _o.getInstate())) {
            return false;
         }

         if (!equals(getOutstate(), _o.getOutstate())) {
            return false;
         }

         if (!equals(getIn(), _o.getIn())) {
            return false;
         }

         if (!equals(getOut(), _o.getOut())) {
            return false;
         }

         if (!equals(getInDiscardsState(), _o.getInDiscardsState())) {
            return false;
         }

         if (!equals(getOutDiscardsState(), _o.getOutDiscardsState())) {
            return false;
         }

         if (!equals(getInErrorsState(), _o.getInErrorsState())) {
            return false;
         }

         if (!equals(getOutErrorsState(), _o.getOutErrorsState())) {
            return false;
         }

         if (!equals(getInDiscards(), _o.getInDiscards())) {
            return false;
         }

         if (!equals(getOutDiscards(), _o.getOutDiscards())) {
            return false;
         }

         if (!equals(getInErrors(), _o.getInErrors())) {
            return false;
         }

         if (!equals(getOutErrors(), _o.getOutErrors())) {
            return false;
         }


         return true;
      }

      return false;
   }

   public String getDomain() {
      return m_domain;
   }

   public String getGroup() {
      return m_group;
   }

   public Double getIn() {
      return m_in;
   }

   public Double getInDiscards() {
      return m_inDiscards;
   }

   public Integer getInDiscardsState() {
      return m_inDiscardsState;
   }

   public Double getInErrors() {
      return m_inErrors;
   }

   public Integer getInErrorsState() {
      return m_inErrorsState;
   }

   public Integer getInstate() {
      return m_instate;
   }

   public String getKey() {
      return m_key;
   }

   public Double getOut() {
      return m_out;
   }

   public Double getOutDiscards() {
      return m_outDiscards;
   }

   public Integer getOutDiscardsState() {
      return m_outDiscardsState;
   }

   public Double getOutErrors() {
      return m_outErrors;
   }

   public Integer getOutErrorsState() {
      return m_outErrorsState;
   }

   public Integer getOutstate() {
      return m_outstate;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_group == null ? 0 : m_group.hashCode());
      hash = hash * 31 + (m_domain == null ? 0 : m_domain.hashCode());
      hash = hash * 31 + (m_key == null ? 0 : m_key.hashCode());
      hash = hash * 31 + (m_instate == null ? 0 : m_instate.hashCode());
      hash = hash * 31 + (m_outstate == null ? 0 : m_outstate.hashCode());
      hash = hash * 31 + (m_in == null ? 0 : m_in.hashCode());
      hash = hash * 31 + (m_out == null ? 0 : m_out.hashCode());
      hash = hash * 31 + (m_inDiscardsState == null ? 0 : m_inDiscardsState.hashCode());
      hash = hash * 31 + (m_outDiscardsState == null ? 0 : m_outDiscardsState.hashCode());
      hash = hash * 31 + (m_inErrorsState == null ? 0 : m_inErrorsState.hashCode());
      hash = hash * 31 + (m_outErrorsState == null ? 0 : m_outErrorsState.hashCode());
      hash = hash * 31 + (m_inDiscards == null ? 0 : m_inDiscards.hashCode());
      hash = hash * 31 + (m_outDiscards == null ? 0 : m_outDiscards.hashCode());
      hash = hash * 31 + (m_inErrors == null ? 0 : m_inErrors.hashCode());
      hash = hash * 31 + (m_outErrors == null ? 0 : m_outErrors.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Interface other) {
      if (other.getGroup() != null) {
         m_group = other.getGroup();
      }

      if (other.getDomain() != null) {
         m_domain = other.getDomain();
      }

      if (other.getKey() != null) {
         m_key = other.getKey();
      }

      if (other.getInstate() != null) {
         m_instate = other.getInstate();
      }

      if (other.getOutstate() != null) {
         m_outstate = other.getOutstate();
      }

      if (other.getIn() != null) {
         m_in = other.getIn();
      }

      if (other.getOut() != null) {
         m_out = other.getOut();
      }

      if (other.getInDiscardsState() != null) {
         m_inDiscardsState = other.getInDiscardsState();
      }

      if (other.getOutDiscardsState() != null) {
         m_outDiscardsState = other.getOutDiscardsState();
      }

      if (other.getInErrorsState() != null) {
         m_inErrorsState = other.getInErrorsState();
      }

      if (other.getOutErrorsState() != null) {
         m_outErrorsState = other.getOutErrorsState();
      }

      if (other.getInDiscards() != null) {
         m_inDiscards = other.getInDiscards();
      }

      if (other.getOutDiscards() != null) {
         m_outDiscards = other.getOutDiscards();
      }

      if (other.getInErrors() != null) {
         m_inErrors = other.getInErrors();
      }

      if (other.getOutErrors() != null) {
         m_outErrors = other.getOutErrors();
      }
   }

   public Interface setDomain(String domain) {
      m_domain = domain;
      return this;
   }

   public Interface setGroup(String group) {
      m_group = group;
      return this;
   }

   public Interface setIn(Double in) {
      m_in = in;
      return this;
   }

   public Interface setInDiscards(Double inDiscards) {
      m_inDiscards = inDiscards;
      return this;
   }

   public Interface setInDiscardsState(Integer inDiscardsState) {
      m_inDiscardsState = inDiscardsState;
      return this;
   }

   public Interface setInErrors(Double inErrors) {
      m_inErrors = inErrors;
      return this;
   }

   public Interface setInErrorsState(Integer inErrorsState) {
      m_inErrorsState = inErrorsState;
      return this;
   }

   public Interface setInstate(Integer instate) {
      m_instate = instate;
      return this;
   }

   public Interface setKey(String key) {
      m_key = key;
      return this;
   }

   public Interface setOut(Double out) {
      m_out = out;
      return this;
   }

   public Interface setOutDiscards(Double outDiscards) {
      m_outDiscards = outDiscards;
      return this;
   }

   public Interface setOutDiscardsState(Integer outDiscardsState) {
      m_outDiscardsState = outDiscardsState;
      return this;
   }

   public Interface setOutErrors(Double outErrors) {
      m_outErrors = outErrors;
      return this;
   }

   public Interface setOutErrorsState(Integer outErrorsState) {
      m_outErrorsState = outErrorsState;
      return this;
   }

   public Interface setOutstate(Integer outstate) {
      m_outstate = outstate;
      return this;
   }

}
