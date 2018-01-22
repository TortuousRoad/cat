package com.dianping.cat.home.alert.thirdparty;

public interface IEntity<T> {
   public void accept(IVisitor visitor);

   public void mergeAttributes(T other);

}
