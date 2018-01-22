package com.dianping.cat.home.router.entity;

import static com.dianping.cat.home.router.Constants.ATTR_ID;
import static com.dianping.cat.home.router.Constants.ENTITY_DOMAIN;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.router.BaseEntity;
import com.dianping.cat.home.router.IVisitor;

public class Domain extends BaseEntity<Domain> {
   private String m_id;

   private double m_sample = 1d;

   private List<Server> m_servers = new ArrayList<Server>();

   public Domain() {
   }

   public Domain(String id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitDomain(this);
   }

   public Domain addServer(Server server) {
      m_servers.add(server);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Domain) {
         Domain _o = (Domain) obj;

         if (!equals(getId(), _o.getId())) {
            return false;
         }

         return true;
      }

      return false;
   }

   public String getId() {
      return m_id;
   }

   public double getSample() {
      return m_sample;
   }

   public List<Server> getServers() {
      return m_servers;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Domain other) {
      assertAttributeEquals(other, ENTITY_DOMAIN, ATTR_ID, m_id, other.getId());

      m_sample = other.getSample();
   }

   public Domain setId(String id) {
      m_id = id;
      return this;
   }

   public Domain setSample(double sample) {
      m_sample = sample;
      return this;
   }

}
