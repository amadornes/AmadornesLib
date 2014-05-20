package es.amadornes.lib.list;


public interface ObjectConverter<A, B> {
    
    public B convert(A obj);
    
}
