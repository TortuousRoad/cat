package com.dianping.cat.home.activity.entity;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.home.activity.BaseEntity;
import com.dianping.cat.home.activity.IVisitor;

public class ActivityConfig extends BaseEntity<ActivityConfig> {
   private List<Activity> m_activities = new ArrayList<Activity>();

   public ActivityConfig() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitActivityConfig(this);
   }

   public ActivityConfig addActivity(Activity activity) {
      m_activities.add(activity);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof ActivityConfig) {
         ActivityConfig _o = (ActivityConfig) obj;

         if (!equals(getActivities(), _o.getActivities())) {
            return false;
         }


         return true;
      }

      return false;
   }

   public List<Activity> getActivities() {
      return m_activities;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      for (Activity e : m_activities) {
         hash = hash * 31 + (e == null ? 0 :e.hashCode());
      }


      return hash;
   }

   @Override
   public void mergeAttributes(ActivityConfig other) {
   }

}
