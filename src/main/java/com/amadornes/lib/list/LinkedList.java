package com.amadornes.lib.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinkedList<T> extends ArrayList<T> {
    
    private static final long                     serialVersionUID = 1L;
    
    Map<List<Object>, ObjectConverter<T, Object>> lists            = new HashMap<List<Object>, ObjectConverter<T, Object>>();
    
    public LinkedList() {
    
    }
    
    @SuppressWarnings("unchecked")
    public void link(List<T> l) {
    
        lists.put((List<Object>) l, null);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void link(List l, ObjectConverter converter) {
    
        lists.put(l, converter);
    }
    
    public void unLink(List<Object> l) {
    
        lists.remove(l);
    }
    
    @Override
    public void add(int index, T element) {
    
        super.add(index, element);
        for (List<Object> l : lists.keySet())
            if (lists.get(l) != null) {
                l.add(index, lists.get(l).convert(element));
            } else {
                l.add(index, element);
            }
    }
    
    @Override
    public boolean add(T e) {
    
        for (List<Object> l : lists.keySet())
            if (lists.get(l) != null) {
                l.add(lists.get(l).convert(e));
            } else {
                l.add(e);
            }
        
        return super.add(e);
    }
    
    @Override
    public boolean addAll(Collection<? extends T> c) {
    
        for (List<Object> l : lists.keySet())
            if (lists.get(l) != null) {
                ObjectConverter<T, Object> con = lists.get(l);
                for (T e : c)
                    l.add(con.convert(e));
            } else {
                l.addAll(c);
            }
        
        return super.addAll(c);
    }
    
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
    
        for (List<Object> l : lists.keySet())
            if (lists.get(l) != null) {
                ObjectConverter<T, Object> con = lists.get(l);
                int i = 0;
                for (T e : c) {
                    l.add(index + i, con.convert(e));
                    i++;
                }
            } else {
                l.addAll(index, c);
            }
        
        return super.addAll(index, c);
    }
    
    @Override
    public LinkedList<T> clone() {
    
        LinkedList<T> ll = new LinkedList<T>();
        for (List<Object> l : lists.keySet()) {
            ll.lists.put(l, lists.get(l));
        }
        ll.addAll(this);
        return ll;
    }
    
    @Override
    public void clear() {
    
        for (List<Object> l : lists.keySet())
            for (T e : this)
                if (lists.get(l) != null) {
                    ObjectConverter<T, Object> con = lists.get(l);
                    l.remove(con.convert(e));
                } else {
                    l.remove(e);
                }
        
        super.clear();
    }
    
    @Override
    public T remove(int index) {
    
        T e = get(index);
        for (List<Object> l : lists.keySet())
            if (lists.get(l) != null) {
                ObjectConverter<T, Object> con = lists.get(l);
                l.remove(con.convert(e));
            } else {
                l.remove(e);
            }
        
        return super.remove(index);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(Object o) {
    
        for (List<Object> l : lists.keySet())
            if (lists.get(l) != null) {
                ObjectConverter<T, Object> con = lists.get(l);
                l.remove(con.convert((T) o));
            } else {
                l.remove(o);
            }
        
        return super.remove(o);
    }
    
    @Override
    public boolean removeAll(Collection<?> c) {
    
        boolean changed = false;
        
        for (Object o : c) {
            changed = changed || remove(o);
        }
        
        return changed;
    }
    
    @Override
    public boolean retainAll(Collection<?> c) {
    
        List<T> li = new ArrayList<T>();
        Collections.copy(li, this);
        li.removeAll(c);
        
        for (List<Object> l : lists.keySet())
            for (T e : li)
                if (lists.get(l) != null) {
                    ObjectConverter<T, Object> con = lists.get(l);
                    l.remove(con.convert(e));
                } else {
                    l.remove(e);
                }
        
        return super.retainAll(c);
    }
    
    @Override
    public T set(int index, T element) {
    
        for (List<Object> l : lists.keySet())
            if (lists.get(l) != null) {
                ObjectConverter<T, Object> con = lists.get(l);
                l.set(index, con.convert(element));
            } else {
                l.set(index, element);
            }
        
        return super.set(index, element);
    }
    
}
