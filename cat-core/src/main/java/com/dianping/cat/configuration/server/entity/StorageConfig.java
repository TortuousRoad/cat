package com.dianping.cat.configuration.server.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.configuration.server.BaseEntity;
import com.dianping.cat.configuration.server.IVisitor;

public class StorageConfig extends BaseEntity<StorageConfig> {
   private String m_localBaseDir = "target/bucket";

   private Boolean m_hdfsDisabled;

   private Map<String, HdfsConfig> m_hdfses = new LinkedHashMap<String, HdfsConfig>();

   private Map<String, Property> m_properties = new LinkedHashMap<String, Property>();

   private int m_uploadThread = 5;

   private int m_maxHdfsStorageTime = 15;

   private int m_localReportStorageTime = 3;

   private int m_localLogivewStorageTime = 7;

   public StorageConfig() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitStorage(this);
   }

   public StorageConfig addHdfs(HdfsConfig hdfs) {
      m_hdfses.put(hdfs.getId(), hdfs);
      return this;
   }

   public StorageConfig addProperty(Property property) {
      m_properties.put(property.getName(), property);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof StorageConfig) {
         StorageConfig _o = (StorageConfig) obj;

         if (!equals(getLocalBaseDir(), _o.getLocalBaseDir())) {
            return false;
         }

         if (!equals(getHdfsDisabled(), _o.getHdfsDisabled())) {
            return false;
         }

         if (!equals(getHdfses(), _o.getHdfses())) {
            return false;
         }

         if (!equals(getProperties(), _o.getProperties())) {
            return false;
         }

         if (getUploadThread() != _o.getUploadThread()) {
            return false;
         }

         if (getMaxHdfsStorageTime() != _o.getMaxHdfsStorageTime()) {
            return false;
         }

         if (getLocalReportStorageTime() != _o.getLocalReportStorageTime()) {
            return false;
         }

         if (getLocalLogivewStorageTime() != _o.getLocalLogivewStorageTime()) {
            return false;
         }


         return true;
      }

      return false;
   }

   public HdfsConfig findHdfs(String id) {
      return m_hdfses.get(id);
   }

   public Property findProperty(String name) {
      return m_properties.get(name);
   }

   public Boolean getHdfsDisabled() {
      return m_hdfsDisabled;
   }

   public Map<String, HdfsConfig> getHdfses() {
      return m_hdfses;
   }

   public String getLocalBaseDir() {
      return m_localBaseDir;
   }

   public int getLocalLogivewStorageTime() {
      return m_localLogivewStorageTime;
   }

   public int getLocalReportStorageTime() {
      return m_localReportStorageTime;
   }

   public int getMaxHdfsStorageTime() {
      return m_maxHdfsStorageTime;
   }

   public Map<String, Property> getProperties() {
      return m_properties;
   }

   public int getUploadThread() {
      return m_uploadThread;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_localBaseDir == null ? 0 : m_localBaseDir.hashCode());
      hash = hash * 31 + (m_hdfsDisabled == null ? 0 : m_hdfsDisabled.hashCode());
      hash = hash * 31 + (m_hdfses == null ? 0 : m_hdfses.hashCode());
      hash = hash * 31 + (m_properties == null ? 0 : m_properties.hashCode());
      hash = hash * 31 + m_uploadThread;
      hash = hash * 31 + m_maxHdfsStorageTime;
      hash = hash * 31 + m_localReportStorageTime;
      hash = hash * 31 + m_localLogivewStorageTime;

      return hash;
   }

   public boolean isHdfsDisabled() {
      return m_hdfsDisabled != null && m_hdfsDisabled.booleanValue();
   }

   @Override
   public void mergeAttributes(StorageConfig other) {
      if (other.getLocalBaseDir() != null) {
         m_localBaseDir = other.getLocalBaseDir();
      }

      if (other.getHdfsDisabled() != null) {
         m_hdfsDisabled = other.getHdfsDisabled();
      }

      m_uploadThread = other.getUploadThread();

      m_maxHdfsStorageTime = other.getMaxHdfsStorageTime();

      m_localReportStorageTime = other.getLocalReportStorageTime();

      m_localLogivewStorageTime = other.getLocalLogivewStorageTime();
   }

   public HdfsConfig removeHdfs(String id) {
      return m_hdfses.remove(id);
   }

   public Property removeProperty(String name) {
      return m_properties.remove(name);
   }

   public StorageConfig setHdfsDisabled(Boolean hdfsDisabled) {
      m_hdfsDisabled = hdfsDisabled;
      return this;
   }

   public StorageConfig setLocalBaseDir(String localBaseDir) {
      m_localBaseDir = localBaseDir;
      return this;
   }

   public StorageConfig setLocalLogivewStorageTime(int localLogivewStorageTime) {
      m_localLogivewStorageTime = localLogivewStorageTime;
      return this;
   }

   public StorageConfig setLocalReportStorageTime(int localReportStorageTime) {
      m_localReportStorageTime = localReportStorageTime;
      return this;
   }

   public StorageConfig setMaxHdfsStorageTime(int maxHdfsStorageTime) {
      m_maxHdfsStorageTime = maxHdfsStorageTime;
      return this;
   }

   public StorageConfig setUploadThread(int uploadThread) {
      m_uploadThread = uploadThread;
      return this;
   }

}
