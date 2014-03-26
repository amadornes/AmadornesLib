package es.amadornes.lib.model;

import es.amadornes.lib.vec.Vector3;


public class Cube {
    
    private Face[] faces = new Face[0];
    
    public Cube(Face[] faces) {
        this.faces = faces;
    }
    
    public Cube(Vector3 a, Vector3 b){
        
    }
    
    public void render(){
        for(Face f : faces){
            if(f != null){
                f.render();
            }
        }
    }
    
}
