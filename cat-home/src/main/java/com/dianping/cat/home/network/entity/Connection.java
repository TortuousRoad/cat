package com.dianping.cat.home.network.entity;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.network.BaseEntity;
import com.dianping.cat.home.network.IVisitor;

public class Connection extends BaseEntity<Connection> {
   private String m_from;

   private String m_to;

   private Integer m_instate;

   private Integer m_outstate;

   private List<Interface> m_interfaces = new ArrayList<Interface>();

   private Double m_insum;

   private Double m_outsum;

   private Integer m_inDiscardsState;

   private Integer m_outDiscardsState;

   private Integer m_inErrorsState;

   private Integer m_outErrorsState;

   private Double m_inDiscards;

   private Double m_outDiscards;

   private Double m_inErrors;

   private Double m_outErrors;

   public Connection() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitConnection(this);
   }

   public Connection addInterface(Interface _interface) {
      m_interfaces.add(_interface);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Connection) {
         Connection _o = (Connection) obj;

         if (!equals(getFrom(), _o.getFrom())) {
            return false;
         }

         if (!equals(getTo(), _o.getTo())) {
            return false;
         }

         if (!equals(getInstate(), _o.getInstate())) {
            return false;
         }

         if (!equals(getOutstate(), _o.getOutstate())) {
            return false;
         }

         if (!equals(getInterfaces(), _o.getInterfaces())) {
            return false;
         }

         if (!equals(getInsum(), _o.getInsum())) {
            return false;
         }

         if (!equals(getOutsum(), _o.getOutsum())) {
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

   public String getFrom() {
      return m_from;
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

   public Double getInsum() {
      return m_insum;
   }

   public List<Interface> getInterfaces() {
      return m_interfaces;
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

   public Double getOutsum() {
      return m_outsum;
   }

   public String getTo() {
      return m_to;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_from == null ? 0 : m_from.hashCode());
      hash = hash * 31 + (m_to == null ? 0 : m_to.hashCode());
      hash = hash * 31 + (m_instate == null ? 0 : m_instate.hashCode());
      hash = hash * 31 + (m_outstate == null ? 0 : m_outstate.hashCode());
      for (Interface e : m_interfaces) {
         hash = hash * 31 + (e == null ? 0 :e.hashCode());
      }

      hash = hash * 31 + (m_insum == null ? 0 : m_insum.hashCode());
      hash = hash * 31 + (m_outsum == null ? 0 : m_outsum.hashCode());
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
   public void mergeAttributes(Connection other) {
      if (other.getFrom() != null) {
         m_from = other.getFrom();
      }

      if (other.getTo() != null) {
         m_to = other.getTo();
      }

      if (other.getInstate() != null) {
         m_instate = other.getInstate();
      }

      if (other.getOutstate() != null) {
         m_outstate = other.getOutstate();
      }

      if (other.getInsum() != null) {
         m_insum = other.getInsum();
      }

      if (other.getOutsum() != null) {
         m_outsum = other.getOutsum();
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

   public Connection setFrom(String from) {
      m_from = from;
      return this;
   }

   public Connection setInDiscards(Double inDiscards) {
      m_inDiscards = inDiscards;
      return this;
   }

   public Connection setInDiscardsState(Integer inDiscardsState) {
      m_inDiscardsState = inDiscardsState;
      return this;
   }

   public Connection setInErrors(Double inErrors) {
      m_inErrors = inErrors;
      return this;
   }

   public Connection setInErrorsState(Integer inErrorsState) {
      m_inErrorsState = inErrorsState;
      return this;
   }

   public Connection setInstate(Integer instate) {
      m_instate = instate;
      return this;
   }

   public Connection setInsum(Double insum) {
      m_insum = insum;
      return this;
   }

   public Connection setOutDiscards(Double outDiscards) {
      m_outDiscards = outDiscards;
      return this;
   }

   public Connection setOutDiscardsState(Integer outDiscardsState) {
      m_outDiscardsState = outDiscardsState;
      return this;
   }

   public Connection setOutErrors(Double outErrors) {
      m_outErrors = outErrors;
      return this;
   }

   public Connection setOutErrorsState(Integer outErrorsState) {
      m_outErrorsState = outErrorsState;
      return this;
   }

   public Connection setOutstate(Integer outstate) {
      m_outstate = outstate;
      return this;
   }

   public Connection setOutsum(Double outsum) {
      m_outsum = outsum;
      return this;
   }

   public Connection setTo(String to) {
      m_to = to;
      return this;
   }

}
