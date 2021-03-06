package com.dianping.cat.consumer.cross.model.transform;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dianping.cat.consumer.cross.model.IVisitor;
import com.dianping.cat.consumer.cross.model.entity.CrossReport;
import com.dianping.cat.consumer.cross.model.entity.Local;
import com.dianping.cat.consumer.cross.model.entity.Name;
import com.dianping.cat.consumer.cross.model.entity.Remote;
import com.dianping.cat.consumer.cross.model.entity.Type;

public class DefaultNativeBuilder implements IVisitor {

   private IVisitor m_visitor;

   private DataOutputStream m_out;

   public DefaultNativeBuilder(OutputStream out) {
      this(out, null);
   }

   public DefaultNativeBuilder(OutputStream out, IVisitor visitor) {
      m_out = new DataOutputStream(out);
      m_visitor = (visitor == null ? this : visitor);
   }

   public static byte[] build(CrossReport crossReport) {
      ByteArrayOutputStream out = new ByteArrayOutputStream(8192);

      build(crossReport, out);
      return out.toByteArray();
   }

   public static void build(CrossReport crossReport, OutputStream out) {
      crossReport.accept(new DefaultNativeBuilder(out));
   }

   @Override
   public void visitCrossReport(CrossReport crossReport) {
      writeTag(63, 0);

      if (crossReport.getDomain() != null) {
         writeTag(1, 1);
         writeString(crossReport.getDomain());
      }

      if (crossReport.getStartTime() != null) {
         writeTag(2, 1);
         writeDate(crossReport.getStartTime());
      }

      if (crossReport.getEndTime() != null) {
         writeTag(3, 1);
         writeDate(crossReport.getEndTime());
      }

      if (crossReport.getDomainNames() != null) {
         writeTag(4, 2);
         writeInt(crossReport.getDomainNames().size());

         for (String domain : crossReport.getDomainNames()) {
            writeString(domain);
         }
      }

      if (crossReport.getIps() != null) {
         writeTag(5, 2);
         writeInt(crossReport.getIps().size());

         for (String ip : crossReport.getIps()) {
            writeString(ip);
         }
      }

      if (!crossReport.getLocals().isEmpty()) {
         writeTag(33, 2);
         writeInt(crossReport.getLocals().size());

         for (Local local : crossReport.getLocals().values()) {
            local.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitLocal(Local local) {
      if (local.getId() != null) {
         writeTag(1, 1);
         writeString(local.getId());
      }

      if (!local.getRemotes().isEmpty()) {
         writeTag(33, 2);
         writeInt(local.getRemotes().size());

         for (Remote remote : local.getRemotes().values()) {
            remote.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   @Override
   public void visitName(Name name) {
      if (name.getId() != null) {
         writeTag(1, 1);
         writeString(name.getId());
      }

      writeTag(2, 0);
      writeLong(name.getTotalCount());

      writeTag(3, 0);
      writeInt(name.getFailCount());

      writeTag(4, 0);
      writeDouble(name.getFailPercent());

      writeTag(5, 0);
      writeDouble(name.getAvg());

      writeTag(6, 0);
      writeDouble(name.getSum());

      writeTag(7, 0);
      writeDouble(name.getTps());

      writeTag(63, 3);
   }

   @Override
   public void visitRemote(Remote remote) {
      if (remote.getId() != null) {
         writeTag(1, 1);
         writeString(remote.getId());
      }

      if (remote.getRole() != null) {
         writeTag(2, 1);
         writeString(remote.getRole());
      }

      if (remote.getApp() != null) {
         writeTag(3, 1);
         writeString(remote.getApp());
      }

      if (remote.getIp() != null) {
         writeTag(4, 1);
         writeString(remote.getIp());
      }

      if (remote.getType() != null) {
         writeTag(33, 1);
         remote.getType().accept(m_visitor);
      }

      writeTag(63, 3);
   }

   @Override
   public void visitType(Type type) {
      if (type.getId() != null) {
         writeTag(1, 1);
         writeString(type.getId());
      }

      writeTag(2, 0);
      writeLong(type.getTotalCount());

      writeTag(3, 0);
      writeInt(type.getFailCount());

      writeTag(4, 0);
      writeDouble(type.getFailPercent());

      writeTag(5, 0);
      writeDouble(type.getAvg());

      writeTag(6, 0);
      writeDouble(type.getSum());

      writeTag(7, 0);
      writeDouble(type.getTps());

      if (!type.getNames().isEmpty()) {
         writeTag(33, 2);
         writeInt(type.getNames().size());

         for (Name name : type.getNames().values()) {
            name.accept(m_visitor);
         }
      }

      writeTag(63, 3);
   }

   private void writeDate(java.util.Date value) {
      try {
         writeVarint(value.getTime());
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeDouble(double value) {
      try {
         writeVarint(Double.doubleToLongBits(value));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeInt(int value) {
      try {
         writeVarint(value & 0xFFFFFFFFL);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeLong(long value) {
      try {
         writeVarint(value);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeString(String value) {
      try {
         m_out.writeUTF(value);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private void writeTag(int field, int type) {
      try {
         m_out.writeByte((field << 2) + type);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   protected void writeVarint(long value) throws IOException {
      while (true) {
         if ((value & ~0x7FL) == 0) {
            m_out.writeByte((byte) value);
            return;
         } else {
            m_out.writeByte(((byte) value & 0x7F) | 0x80);
            value >>>= 7;
         }
      }
   }
}
