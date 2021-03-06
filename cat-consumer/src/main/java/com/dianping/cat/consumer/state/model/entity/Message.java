package com.dianping.cat.consumer.state.model.entity;

import static com.dianping.cat.consumer.state.model.Constants.ATTR_ID;
import static com.dianping.cat.consumer.state.model.Constants.ENTITY_MESSAGE;

import com.dianping.cat.consumer.state.model.BaseEntity;
import com.dianping.cat.consumer.state.model.IVisitor;

public class Message extends BaseEntity<Message> {
   private Long m_id;

   private java.util.Date m_time;

   private long m_total;

   private long m_totalLoss;

   private long m_dump;

   private long m_dumpLoss;

   private double m_size;

   private double m_delaySum;

   private int m_delayCount;

   private long m_pigeonTimeError;

   private long m_networkTimeError;

   private long m_blockTotal;

   private long m_blockLoss;

   private long m_blockTime;

   public Message() {
   }

   public Message(Long id) {
      m_id = id;
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitMessage(this);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Message) {
         Message _o = (Message) obj;

         if (!equals(getId(), _o.getId())) {
            return false;
         }

         return true;
      }

      return false;
   }

   public long getBlockLoss() {
      return m_blockLoss;
   }

   public long getBlockTime() {
      return m_blockTime;
   }

   public long getBlockTotal() {
      return m_blockTotal;
   }

   public int getDelayCount() {
      return m_delayCount;
   }

   public double getDelaySum() {
      return m_delaySum;
   }

   public long getDump() {
      return m_dump;
   }

   public long getDumpLoss() {
      return m_dumpLoss;
   }

   public Long getId() {
      return m_id;
   }

   public long getNetworkTimeError() {
      return m_networkTimeError;
   }

   public long getPigeonTimeError() {
      return m_pigeonTimeError;
   }

   public double getSize() {
      return m_size;
   }

   public java.util.Date getTime() {
      return m_time;
   }

   public long getTotal() {
      return m_total;
   }

   public long getTotalLoss() {
      return m_totalLoss;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Message other) {
      assertAttributeEquals(other, ENTITY_MESSAGE, ATTR_ID, m_id, other.getId());

      if (other.getTime() != null) {
         m_time = other.getTime();
      }

      m_total = other.getTotal();

      m_totalLoss = other.getTotalLoss();

      m_dump = other.getDump();

      m_dumpLoss = other.getDumpLoss();

      m_size = other.getSize();

      m_delaySum = other.getDelaySum();

      m_delayCount = other.getDelayCount();

      m_pigeonTimeError = other.getPigeonTimeError();

      m_networkTimeError = other.getNetworkTimeError();

      m_blockTotal = other.getBlockTotal();

      m_blockLoss = other.getBlockLoss();

      m_blockTime = other.getBlockTime();
   }

   public Message setBlockLoss(long blockLoss) {
      m_blockLoss = blockLoss;
      return this;
   }

   public Message setBlockTime(long blockTime) {
      m_blockTime = blockTime;
      return this;
   }

   public Message setBlockTotal(long blockTotal) {
      m_blockTotal = blockTotal;
      return this;
   }

   public Message setDelayCount(int delayCount) {
      m_delayCount = delayCount;
      return this;
   }

   public Message setDelaySum(double delaySum) {
      m_delaySum = delaySum;
      return this;
   }

   public Message setDump(long dump) {
      m_dump = dump;
      return this;
   }

   public Message setDumpLoss(long dumpLoss) {
      m_dumpLoss = dumpLoss;
      return this;
   }

   public Message setId(Long id) {
      m_id = id;
      return this;
   }

   public Message setNetworkTimeError(long networkTimeError) {
      m_networkTimeError = networkTimeError;
      return this;
   }

   public Message setPigeonTimeError(long pigeonTimeError) {
      m_pigeonTimeError = pigeonTimeError;
      return this;
   }

   public Message setSize(double size) {
      m_size = size;
      return this;
   }

   public Message setTime(java.util.Date time) {
      m_time = time;
      return this;
   }

   public Message setTotal(long total) {
      m_total = total;
      return this;
   }

   public Message setTotalLoss(long totalLoss) {
      m_totalLoss = totalLoss;
      return this;
   }

}
