package objetos3D;

import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.Box;
public class Suelo {
  private TransformGroup tPrincipal=new TransformGroup();
  public Suelo(Appearance apariencia, int tx, int tz){
/*    Transform3D t3d=new Transform3D();
    Transform3D t3d2=new Transform3D();
    t3d.rotX(Math.PI/2);
    t3d2.set(1,new Vector3d(tx/2,-0.50f,tz/2));
    t3d.rotX(Math.PI/2);
    t3d2.mul(t3d);
    tPrincipal=new TransformGroup(t3d);
    tPrincipal.addChild(new Box(tx/2,tz/2,0,apariencia));
    */
      
    Transform3D t3d=new Transform3D();
    t3d.set(1,new Vector3d(tx/2,-0.5f,tz/2));
    tPrincipal=new TransformGroup(t3d);
    
    t3d.rotX(Math.PI/2);
    TransformGroup tAux1=new TransformGroup(t3d);
    
    tPrincipal.addChild(tAux1);
    tAux1.addChild(new Box(tx/2,tz/2,0,apariencia));

  }
  public TransformGroup getTransformGroup(){
    return tPrincipal;
  }
}
