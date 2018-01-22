package com.dianping.cat.consumer.company.model.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.consumer.company.model.BaseEntity;
import com.dianping.cat.consumer.company.model.IVisitor;

public class Company extends BaseEntity<Company> {
   private String m_name;

   private Map<String, ProductLine> m_productLines = new LinkedHashMap<String, ProductLine>();

   public Company() {
   }

   @Override
   public void accept(IVisitor visitor) {
      visitor.visitCompany(this);
   }

   public Company addProductLine(ProductLine productLine) {
      m_productLines.put(productLine.getId(), productLine);
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Company) {
         Company _o = (Company) obj;

         if (!equals(getName(), _o.getName())) {
            return false;
         }

         if (!equals(getProductLines(), _o.getProductLines())) {
            return false;
         }


         return true;
      }

      return false;
   }

   public ProductLine findProductLine(String id) {
      return m_productLines.get(id);
   }

   public ProductLine findOrCreateProductLine(String id) {
      ProductLine productLine = m_productLines.get(id);

      if (productLine == null) {
         synchronized (m_productLines) {
            productLine = m_productLines.get(id);

            if (productLine == null) {
               productLine = new ProductLine(id);
               m_productLines.put(id, productLine);
            }
         }
      }

      return productLine;
   }

   public String getName() {
      return m_name;
   }

   public Map<String, ProductLine> getProductLines() {
      return m_productLines;
   }

   @Override
   public int hashCode() {
      int hash = 0;

      hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());
      hash = hash * 31 + (m_productLines == null ? 0 : m_productLines.hashCode());

      return hash;
   }

   @Override
   public void mergeAttributes(Company other) {
      if (other.getName() != null) {
         m_name = other.getName();
      }
   }

   public ProductLine removeProductLine(String id) {
      return m_productLines.remove(id);
   }

   public Company setName(String name) {
      m_name = name;
      return this;
   }

}
