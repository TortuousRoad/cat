package com.dianping.cat.consumer.heartbeat.model.transform;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Map;

import com.dianping.cat.consumer.heartbeat.model.IVisitor;
import com.dianping.cat.consumer.heartbeat.model.entity.Detail;
import com.dianping.cat.consumer.heartbeat.model.entity.Disk;
import com.dianping.cat.consumer.heartbeat.model.entity.Extension;
import com.dianping.cat.consumer.heartbeat.model.entity.HeartbeatReport;
import com.dianping.cat.consumer.heartbeat.model.entity.Machine;
import com.dianping.cat.consumer.heartbeat.model.entity.Period;

public class DefaultNativeParser implements IVisitor {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DataInputStream m_in;

   public DefaultNativeParser(InputStream in) {
      m_in = new DataInputStream(in);
   }

   public static HeartbeatReport parse(byte[] data) {
      return parse(new ByteArrayInputStream(data));
   }

   public static HeartbeatReport parse(InputStream in) {
      DefaultNativeParser parser = new DefaultNativeParser(in);
      HeartbeatReport heartbeatReport = new HeartbeatReport();

      try {
         heartbeatReport.accept(parser);
      } catch (RuntimeException e) {
         if (e.getCause() !=null && e.getCause() instanceof java.io.EOFException) {
            // ignore it
         } else {
            throw e;
         }
      }
      
      parser.m_linker.finish();
      return heartbeatReport;
   }

   @Override
   public void visitDetail(Detail detail) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitDetailChildren(detail, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitDetailChildren(Detail detail, int _field, int _type) {
      switch (_field) {
         case 1:
            detail.setId(readString());
            break;
         case 2:
            detail.setValue(readDouble());
            break;
      }
   }

   @Override
   public void visitDisk(Disk disk) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitDiskChildren(disk, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitDiskChildren(Disk disk, int _field, int _type) {
      switch (_field) {
         case 1:
            disk.setPath(readString());
            break;
         case 2:
            disk.setTotal(readLong());
            break;
         case 3:
            disk.setFree(readLong());
            break;
         case 4:
            disk.setUsable(readLong());
            break;
      }
   }

   @Override
   public void visitExtension(Extension extension) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitExtensionChildren(extension, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitExtensionChildren(Extension extension, int _field, int _type) {
      switch (_field) {
         case 1:
            extension.setId(readString());
            break;
         case 33:
            if (_type == 1) { 
              Detail detail = new Detail();

              visitDetail(detail);
              m_linker.onDetail(extension, detail);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Detail detail = new Detail();

                 visitDetail(detail);
                 m_linker.onDetail(extension, detail);
               }
            }
            break;
      }
   }

   @Override
   public void visitHeartbeatReport(HeartbeatReport heartbeatReport) {
      byte tag;

      if ((tag = readTag()) != -4) {
         throw new RuntimeException(String.format("Malformed payload, expected: %s, but was: %s!", -4, tag));
      }

      while ((tag = readTag()) != -1) {
         visitHeartbeatReportChildren(heartbeatReport, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitHeartbeatReportChildren(HeartbeatReport heartbeatReport, int _field, int _type) {
      switch (_field) {
         case 1:
            heartbeatReport.setDomain(readString());
            break;
         case 2:
            heartbeatReport.setStartTime(readDate());
            break;
         case 3:
            heartbeatReport.setEndTime(readDate());
            break;
         case 4:
            if (_type == 1) {
                  heartbeatReport.addDomain(readString());
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                  heartbeatReport.addDomain(readString());
               }
            }
            break;
         case 5:
            if (_type == 1) {
                  heartbeatReport.addIp(readString());
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                  heartbeatReport.addIp(readString());
               }
            }
            break;
         case 33:
            if (_type == 1) { 
              Machine machine = new Machine();

              visitMachine(machine);
              m_linker.onMachine(heartbeatReport, machine);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Machine machine = new Machine();

                 visitMachine(machine);
                 m_linker.onMachine(heartbeatReport, machine);
               }
            }
            break;
      }
   }

   @Override
   public void visitMachine(Machine machine) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitMachineChildren(machine, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitMachineChildren(Machine machine, int _field, int _type) {
      switch (_field) {
         case 1:
            machine.setIp(readString());
            break;
         case 2:
            machine.setClasspath(readString());
            break;
         case 33:
            if (_type == 1) { 
              Period period = new Period();

              visitPeriod(period);
              m_linker.onPeriod(machine, period);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Period period = new Period();

                 visitPeriod(period);
                 m_linker.onPeriod(machine, period);
               }
            }
            break;
      }
   }

   @Override
   public void visitPeriod(Period period) {
      byte tag;

      while ((tag = readTag()) != -1) {
         visitPeriodChildren(period, (tag & 0xFF) >> 2, tag & 0x3);
      }
   }

   protected void visitPeriodChildren(Period period, int _field, int _type) {
      switch (_field) {
         case 1:
            period.setMinute(readInt());
            break;
         case 2:
            period.setThreadCount(readInt());
            break;
         case 3:
            period.setDaemonCount(readInt());
            break;
         case 4:
            period.setTotalStartedCount(readInt());
            break;
         case 5:
            period.setCatThreadCount(readInt());
            break;
         case 6:
            period.setPigeonThreadCount(readInt());
            break;
         case 7:
            period.setHttpThreadCount(readInt());
            break;
         case 8:
            period.setNewGcCount(readLong());
            break;
         case 9:
            period.setOldGcCount(readLong());
            break;
         case 10:
            period.setMemoryFree(readLong());
            break;
         case 11:
            period.setHeapUsage(readLong());
            break;
         case 12:
            period.setNoneHeapUsage(readLong());
            break;
         case 13:
            period.setSystemLoadAverage(readDouble());
            break;
         case 14:
            period.setCatMessageProduced(readLong());
            break;
         case 15:
            period.setCatMessageOverflow(readLong());
            break;
         case 16:
            period.setCatMessageSize(readDouble());
            break;
         case 33:
            if (_type == 1) { 
              Disk disk = new Disk();

              visitDisk(disk);
              m_linker.onDisk(period, disk);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Disk disk = new Disk();

                 visitDisk(disk);
                 m_linker.onDisk(period, disk);
               }
            }
            break;
         case 34:
            if (_type == 1) { 
              Extension extension = new Extension();

              visitExtension(extension);
              m_linker.onExtension(period, extension);
            } else if (_type == 2) {
               for (int i = readInt(); i > 0; i--) {
                 Extension extension = new Extension();

                 visitExtension(extension);
                 m_linker.onExtension(period, extension);
               }
            }
            break;
         case 63:
            Map<String, String> dynamicAttribute = period.getDynamicAttributes();

            for (int i = readInt(); i > 0; i--) {
               String key = readString();
               String value = readString();

               dynamicAttribute.put(key, value);
            }
            break;
      }
   }

   private java.util.Date readDate() {
      try {
         return new java.util.Date(readVarint(64));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private double readDouble() {
      try {
         return Double.longBitsToDouble(readVarint(64));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private int readInt() {
      try {
         return (int) readVarint(32);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private long readLong() {
      try {
         return readVarint(64);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private String readString() {
      try {
         return m_in.readUTF();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private byte readTag() {
      try {
         return m_in.readByte();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   protected long readVarint(final int length) throws IOException {
      int shift = 0;
      long result = 0;

      while (shift < length) {
         final byte b = m_in.readByte();
         result |= (long) (b & 0x7F) << shift;
         if ((b & 0x80) == 0) {
            return result;
         }
         shift += 7;
      }

      throw new RuntimeException("Malformed variable int " + length + "!");
   }
}
