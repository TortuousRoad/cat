package com.dianping.cat.home.network.entity;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.network.BaseEntity;
import com.dianping.cat.home.network.IVisitor;

public class NetTopology extends BaseEntity<NetTopology> {
   private String m_name;

   private List<Anchor> m_anchors = new ArrayList<Anchor>();

   private List<Switch> m_switchs = new ArrayList<Switch>();

   private List<Connection> m_connections = new ArrayList<Connection>();

   public NetTopology() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitNetTopology(this);
   }

   public NetTopology addAnchor(Anchor anchor) {
      m_anchors.add(anchor);
      return this;
   }

   public NetTopology addConnection(Connection connection) {
      m_connections.add(connection);
      return this;
   }

   public NetTopology addSwitch(Switch _switch) {
      m_switchs.add(_switch);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof NetTopology) {
         NetTopology _o = (NetTopology) obj;

         if (!equals(getName(), _o.getName())) {
            return false;
         }

         if (!equals(getAnchors(), _o.getAnchors())) {
            return false;
         }

         if (!equals(getSwitchs(), _o.getSwitchs())) {
            return false;
         }

         if (!equals(getConnections(), _o.getConnections())) {
            return false;
         }


         return true;
      }

      return false;
   }

   public List<Anchor> getAnchors() {
      return m_anchors;
   }

   public List<Connection> getConnections() {
      return m_connections;
   }

   public String getName() {
      return m_name;
   }

   public List<Switch> getSwitchs() {
      return m_switchs;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());
      for (Anchor e : m_anchors) {
         hash = hash * 31 + (e == null ? 0 :e.hashCode());
      }

      for (Switch e : m_switchs) {
         hash = hash * 31 + (e == null ? 0 :e.hashCode());
      }

      for (Connection e : m_connections) {
         hash = hash * 31 + (e == null ? 0 :e.hashCode());
      }


      return hash;
   }

   @Override
   public void mergeAttributes(NetTopology other) {
      if (other.getName() != null) {
         m_name = other.getName();
      }
   }

   public NetTopology setName(String name) {
      m_name = name;
      return this;
   }

}
