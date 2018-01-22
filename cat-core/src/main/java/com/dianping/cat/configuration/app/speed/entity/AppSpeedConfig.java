package com.dianping.cat.configuration.app.speed.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.configuration.app.speed.BaseEntity;
import com.dianping.cat.configuration.app.speed.IVisitor;

public class AppSpeedConfig extends BaseEntity<AppSpeedConfig> {
   private Map<Integer, Speed> m_speeds = new LinkedHashMap<Integer, Speed>();

   public AppSpeedConfig() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitAppSpeedConfig(this);
   }

   public AppSpeedConfig addSpeed(Speed speed) {
      m_speeds.put(speed.getId(), speed);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof AppSpeedConfig) {
         AppSpeedConfig _o = (AppSpeedConfig) obj;

         if (!equals(getSpeeds(), _o.getSpeeds())) {
            return false;
         }


         return true;
      }

      return false;
   }

   public Speed findSpeed(int id) {
      return m_speeds.get(id);
   }

   public Speed findOrCreateSpeed(int id) {
      Speed speed = m_speeds.get(id);

      if (speed == null) {
         synchronized (m_speeds) {
            speed = m_speeds.get(id);

            if (speed == null) {
               speed = new Speed(id);
               m_speeds.put(id, speed);
            }
         }
      }

      return speed;
   }

   public Map<Integer, Speed> getSpeeds() {
      return m_speeds;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_speeds == null ? 0 : m_speeds.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(AppSpeedConfig other) {
   }

   public Speed removeSpeed(int id) {
      return m_speeds.remove(id);
   }

}
