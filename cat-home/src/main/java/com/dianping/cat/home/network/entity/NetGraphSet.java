package com.dianping.cat.home.network.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.home.network.BaseEntity;
import com.dianping.cat.home.network.IVisitor;

public class NetGraphSet extends BaseEntity<NetGraphSet> {
   private Map<Integer, NetGraph> m_netGraphs = new LinkedHashMap<Integer, NetGraph>();

   public NetGraphSet() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitNetGraphSet(this);
   }

   public NetGraphSet addNetGraph(NetGraph netGraph) {
      m_netGraphs.put(netGraph.getMinute(), netGraph);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof NetGraphSet) {
         NetGraphSet _o = (NetGraphSet) obj;

         if (!equals(getNetGraphs(), _o.getNetGraphs())) {
            return false;
         }


         return true;
      }

      return false;
   }

   public NetGraph findNetGraph(Integer minute) {
      return m_netGraphs.get(minute);
   }

   public NetGraph findOrCreateNetGraph(Integer minute) {
      NetGraph netGraph = m_netGraphs.get(minute);

      if (netGraph == null) {
         synchronized (m_netGraphs) {
            netGraph = m_netGraphs.get(minute);

            if (netGraph == null) {
               netGraph = new NetGraph(minute);
               m_netGraphs.put(minute, netGraph);
            }
         }
      }

      return netGraph;
   }

   public Map<Integer, NetGraph> getNetGraphs() {
      return m_netGraphs;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_netGraphs == null ? 0 : m_netGraphs.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(NetGraphSet other) {
   }

   public NetGraph removeNetGraph(Integer minute) {
      return m_netGraphs.remove(minute);
   }

}
