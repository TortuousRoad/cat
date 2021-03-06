package com.dianping.cat.core.dal;

import static com.dianping.cat.core.dal.HourlyReportContentEntity.CAPACITY;
import static com.dianping.cat.core.dal.HourlyReportContentEntity.CONTENT;
import static com.dianping.cat.core.dal.HourlyReportContentEntity.CONTENT_LENGTH;
import static com.dianping.cat.core.dal.HourlyReportContentEntity.CREATION_DATE;
import static com.dianping.cat.core.dal.HourlyReportContentEntity.KEY_REPORT_ID;
import static com.dianping.cat.core.dal.HourlyReportContentEntity.REPORT_ID;
import static com.dianping.cat.core.dal.HourlyReportContentEntity.START_ID;

import org.unidal.dal.jdbc.DataObject;

public class HourlyReportContent extends DataObject {
   private int m_reportId;

   private byte[] m_content;

   private java.util.Date m_creationDate;

   private int m_keyReportId;

   private long m_contentLength;

   private int m_startId;

   private double m_capacity;

   @Override
   public void afterLoad() {
      m_keyReportId = m_reportId;
      super.clearUsage();
   }

   public double getCapacity() {
      return m_capacity;
   }

   public byte[] getContent() {
      return m_content;
   }

   public long getContentLength() {
      return m_contentLength;
   }

   public java.util.Date getCreationDate() {
      return m_creationDate;
   }

   public int getKeyReportId() {
      return m_keyReportId;
   }

   public int getReportId() {
      return m_reportId;
   }

   public int getStartId() {
      return m_startId;
   }

   public HourlyReportContent setCapacity(double capacity) {
      setFieldUsed(CAPACITY, true);
      m_capacity = capacity;
      return this;
   }

   public HourlyReportContent setContent(byte[] content) {
      setFieldUsed(CONTENT, true);
      m_content = content;
      return this;
   }

   public HourlyReportContent setContentLength(long contentLength) {
      setFieldUsed(CONTENT_LENGTH, true);
      m_contentLength = contentLength;
      return this;
   }

   public HourlyReportContent setCreationDate(java.util.Date creationDate) {
      setFieldUsed(CREATION_DATE, true);
      m_creationDate = creationDate;
      return this;
   }

   public HourlyReportContent setKeyReportId(int keyReportId) {
      setFieldUsed(KEY_REPORT_ID, true);
      m_keyReportId = keyReportId;
      return this;
   }

   public HourlyReportContent setReportId(int reportId) {
      setFieldUsed(REPORT_ID, true);
      m_reportId = reportId;

      setFieldUsed(KEY_REPORT_ID, true);
      m_keyReportId = reportId;
      return this;
   }

   public HourlyReportContent setStartId(int startId) {
      setFieldUsed(START_ID, true);
      m_startId = startId;
      return this;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder(1024);

      sb.append("HourlyReportContent[");
      sb.append("capacity: ").append(m_capacity);
      sb.append(", content: ").append(m_content == null ? null : java.util.Arrays.asList(m_content));
      sb.append(", content-length: ").append(m_contentLength);
      sb.append(", creation-date: ").append(m_creationDate);
      sb.append(", key-report-id: ").append(m_keyReportId);
      sb.append(", report-id: ").append(m_reportId);
      sb.append(", start-id: ").append(m_startId);
      sb.append("]");
      return sb.toString();
   }

}
