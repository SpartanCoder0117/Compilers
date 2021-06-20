
import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.*;
import javax.vecmath.*;

public class CubeCreator /*implements Dibujable*/ {
   
    public static BranchGroup create(
      double x, double  y, double  z, double  size, Appearance apariencia){
      TransformGroup tg=new TransformGroup();
      Transform3D t3d=new Transform3D();
      t3d.set(1,new Vector3d(x + 0.5f, y + 0.5f ,z + 0.5f));
      tg=new TransformGroup(t3d);
      tg.addChild(new Box((float)size, (float)size, (float)size, apariencia));
      BranchGroup cubeBg = new BranchGroup();
      cubeBg.addChild(tg);
      return cubeBg;
    }
    //public TransformGroup getTransformGroup(){
    //  return tg;
    //}
}
