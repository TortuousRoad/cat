package com.dianping.cat.home.network.transform;

import static com.dianping.cat.home.network.Constants.ENTITY_ANCHOR;
import static com.dianping.cat.home.network.Constants.ENTITY_CONNECTION;
import static com.dianping.cat.home.network.Constants.ENTITY_INTERFACE;
import static com.dianping.cat.home.network.Constants.ENTITY_NETGRAPH;
import static com.dianping.cat.home.network.Constants.ENTITY_NETGRAPHSET;
import static com.dianping.cat.home.network.Constants.ENTITY_NETTOPOLOGY;
import static com.dianping.cat.home.network.Constants.ENTITY_SWITCH;
import static com.dianping.cat.home.network.Constants.ENTITY_ANCHORS;
import static com.dianping.cat.home.network.Constants.ENTITY_CONNECTIONS;
import static com.dianping.cat.home.network.Constants.ENTITY_SWITCHS;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.dianping.cat.home.network.IEntity;
import com.dianping.cat.home.network.entity.Anchor;
import com.dianping.cat.home.network.entity.Connection;
import com.dianping.cat.home.network.entity.Interface;
import com.dianping.cat.home.network.entity.NetGraph;
import com.dianping.cat.home.network.entity.NetGraphSet;
import com.dianping.cat.home.network.entity.NetTopology;
import com.dianping.cat.home.network.entity.Switch;

public class DefaultSaxParser extends DefaultHandler {

   private DefaultLinker m_linker = new DefaultLinker(true);

   private DefaultSaxMaker m_maker = new DefaultSaxMaker();

   private Stack<String> m_tags = new Stack<String>();

   private Stack<Object> m_objs = new Stack<Object>();

   private IEntity<?> m_entity;

   private StringBuilder m_text = new StringBuilder();

   public static NetGraphSet parse(InputStream in) throws SAXException, IOException {
      return parseEntity(NetGraphSet.class, new InputSource(removeBOM(in)));
   }

   public static NetGraphSet parse(Reader reader) throws SAXException, IOException {
      return parseEntity(NetGraphSet.class, new InputSource(removeBOM(reader)));
   }

   public static NetGraphSet parse(String xml) throws SAXException, IOException {
      return parseEntity(NetGraphSet.class, new InputSource(new StringReader(removeBOM(xml))));
   }

   @SuppressWarnings("unchecked")
   private static <T extends IEntity<?>> T parseEntity(Class<T> type, InputSource is) throws SAXException, IOException {
      try {
         DefaultSaxParser handler = new DefaultSaxParser();
         SAXParserFactory factory = SAXParserFactory.newInstance();

         factory.setValidating(false);
         factory.setFeature("http://xml.org/sax/features/validation", false);

         factory.newSAXParser().parse(is, handler);
         return (T) handler.getEntity();
      } catch (ParserConfigurationException e) {
         throw new IllegalStateException("Unable to get SAX parser instance!", e);
      }
   }

   public static <T extends IEntity<?>> T parseEntity(Class<T> type, InputStream in) throws SAXException, IOException {
      return parseEntity(type, new InputSource(removeBOM(in)));
   }

   public static <T extends IEntity<?>> T parseEntity(Class<T> type, String xml) throws SAXException, IOException {
      return parseEntity(type, new InputSource(new StringReader(removeBOM(xml))));
   }

   // to remove Byte Order Mark(BOM) at the head of windows utf-8 file
   @SuppressWarnings("unchecked")
   private static <T> T removeBOM(T obj) throws IOException {
      if (obj instanceof String) {
         String str = (String) obj;

         if (str.length() != 0 && str.charAt(0) == 0xFEFF) {
            return (T) str.substring(1);
         } else {
            return obj;
         }
      } else if (obj instanceof InputStream) {
         BufferedInputStream in = new BufferedInputStream((InputStream) obj);

         in.mark(3);

         if (in.read() != 0xEF || in.read() != 0xBB || in.read() != 0xBF) {
            in.reset();
         }

         return (T) in;
      } else if (obj instanceof Reader) {
         BufferedReader in = new BufferedReader((Reader) obj);

         in.mark(1);

         if (in.read() != 0xFEFF) {
            in.reset();
         }

         return (T) in;
      } else {
         return obj;
      }
   }

   @SuppressWarnings("unchecked")
   protected <T> T convert(Class<T> type, String value, T defaultValue) {
      if (value == null || value.length() == 0) {
         return defaultValue;
      }

      if (type == Boolean.class) {
         return (T) Boolean.valueOf(value);
      } else if (type == Integer.class) {
         return (T) Integer.valueOf(value);
      } else if (type == Long.class) {
         return (T) Long.valueOf(value);
      } else if (type == Short.class) {
         return (T) Short.valueOf(value);
      } else if (type == Float.class) {
         return (T) Float.valueOf(value);
      } else if (type == Double.class) {
         return (T) Double.valueOf(value);
      } else if (type == Byte.class) {
         return (T) Byte.valueOf(value);
      } else if (type == Character.class) {
         return (T) (Character) value.charAt(0);
      } else {
         return (T) value;
      }
   }

   @Override
   public void characters(char[] ch, int start, int length) throws SAXException {
      m_text.append(ch, start, length);
   }

   @Override
   public void endDocument() throws SAXException {
      m_linker.finish();
   }

   @Override
   public void endElement(String uri, String localName, String qName) throws SAXException {
      if (uri == null || uri.length() == 0) {
         m_objs.pop();
         m_tags.pop();

      }

      m_text.setLength(0);
   }

   private IEntity<?> getEntity() {
      return m_entity;
   }

   protected String getText() {
      return m_text.toString();
   }

   private void parseForAnchor(Anchor parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForConnection(Connection parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_INTERFACE.equals(qName)) {
         Interface interface_ = m_maker.buildInterface(attributes);

         m_linker.onInterface(parentObj, interface_);
         m_objs.push(interface_);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under connection!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForInterface(Interface parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseForNetGraph(NetGraph parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_NETTOPOLOGY.equals(qName)) {
         NetTopology netTopology = m_maker.buildNetTopology(attributes);

         m_linker.onNetTopology(parentObj, netTopology);
         m_objs.push(netTopology);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under netGraph!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForNetGraphSet(NetGraphSet parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_NETGRAPH.equals(qName)) {
         NetGraph netGraph = m_maker.buildNetGraph(attributes);

         m_linker.onNetGraph(parentObj, netGraph);
         m_objs.push(netGraph);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under netGraphSet!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForNetTopology(NetTopology parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      if (ENTITY_ANCHORS.equals(qName) || ENTITY_SWITCHS.equals(qName) || ENTITY_CONNECTIONS.equals(qName)) {
         m_objs.push(parentObj);
      } else if (ENTITY_ANCHOR.equals(qName)) {
         Anchor anchor = m_maker.buildAnchor(attributes);

         m_linker.onAnchor(parentObj, anchor);
         m_objs.push(anchor);
      } else if (ENTITY_SWITCH.equals(qName)) {
         Switch switch_ = m_maker.buildSwitch(attributes);

         m_linker.onSwitch(parentObj, switch_);
         m_objs.push(switch_);
      } else if (ENTITY_CONNECTION.equals(qName)) {
         Connection connection = m_maker.buildConnection(attributes);

         m_linker.onConnection(parentObj, connection);
         m_objs.push(connection);
      } else {
         throw new SAXException(String.format("Element(%s) is not expected under netTopology!", qName));
      }

      m_tags.push(qName);
   }

   private void parseForSwitch(Switch parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
      m_objs.push(parentObj);
      m_tags.push(qName);
   }

   private void parseRoot(String qName, Attributes attributes) throws SAXException {
      if (ENTITY_NETGRAPHSET.equals(qName)) {
         NetGraphSet netGraphSet = m_maker.buildNetGraphSet(attributes);

         m_entity = netGraphSet;
         m_objs.push(netGraphSet);
         m_tags.push(qName);
      } else if (ENTITY_NETGRAPH.equals(qName)) {
         NetGraph netGraph = m_maker.buildNetGraph(attributes);

         m_entity = netGraph;
         m_objs.push(netGraph);
         m_tags.push(qName);
      } else if (ENTITY_NETTOPOLOGY.equals(qName)) {
         NetTopology netTopology = m_maker.buildNetTopology(attributes);

         m_entity = netTopology;
         m_objs.push(netTopology);
         m_tags.push(qName);
      } else if (ENTITY_ANCHOR.equals(qName)) {
         Anchor anchor = m_maker.buildAnchor(attributes);

         m_entity = anchor;
         m_objs.push(anchor);
         m_tags.push(qName);
      } else if (ENTITY_SWITCH.equals(qName)) {
         Switch _switch = m_maker.buildSwitch(attributes);

         m_entity = _switch;
         m_objs.push(_switch);
         m_tags.push(qName);
      } else if (ENTITY_CONNECTION.equals(qName)) {
         Connection connection = m_maker.buildConnection(attributes);

         m_entity = connection;
         m_objs.push(connection);
         m_tags.push(qName);
      } else if (ENTITY_INTERFACE.equals(qName)) {
         Interface _interface = m_maker.buildInterface(attributes);

         m_entity = _interface;
         m_objs.push(_interface);
         m_tags.push(qName);
      } else {
         throw new SAXException("Unknown root element(" + qName + ") found!");
      }
   }

   @Override
   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
      if (uri == null || uri.length() == 0) {
         if (m_objs.isEmpty()) { // root
            parseRoot(qName, attributes);
         } else {
            Object parent = m_objs.peek();
            String tag = m_tags.peek();

            if (parent instanceof NetGraphSet) {
               parseForNetGraphSet((NetGraphSet) parent, tag, qName, attributes);
            } else if (parent instanceof NetGraph) {
               parseForNetGraph((NetGraph) parent, tag, qName, attributes);
            } else if (parent instanceof NetTopology) {
               parseForNetTopology((NetTopology) parent, tag, qName, attributes);
            } else if (parent instanceof Anchor) {
               parseForAnchor((Anchor) parent, tag, qName, attributes);
            } else if (parent instanceof Switch) {
               parseForSwitch((Switch) parent, tag, qName, attributes);
            } else if (parent instanceof Connection) {
               parseForConnection((Connection) parent, tag, qName, attributes);
            } else if (parent instanceof Interface) {
               parseForInterface((Interface) parent, tag, qName, attributes);
            } else {
               throw new RuntimeException(String.format("Unknown entity(%s) under %s!", qName, parent.getClass().getName()));
            }
         }

         m_text.setLength(0);
        } else {
         throw new SAXException(String.format("Namespace(%s) is not supported by %s.", uri, this.getClass().getName()));
      }
   }
}
