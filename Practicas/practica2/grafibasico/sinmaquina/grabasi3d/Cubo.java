import java.awt.*;
import javax.vecmath.*;
import javax.media.j3d.*;

public class Cubo implements Dibujable {
	private double x=0;
	private double y=0;
	private double z=0;
	private double size;

	public Cubo(double x, double y, double z, double size){
		this.x=x;
		this.y=y;
		this.z=z;
		this.size=size;
	}
        public void transladar(double dx,double dy) {
        	this.x+=dx; this.y+=dy;
                //this.x2+=dx; this.y2+=dy;
    	}
        public void escalar(double dx, double dy){}
	public void dibujar(
           BranchGroup bg, Appearance branchApp){
                System.out.println("en dib Cubo");
System.out.println("IFIF cubo() d1= "+x+" d2 ="+y+" d3= "+z+" d4= "+size);
		//g.drawLine(x1,y1,x2,y2);
            bg.addChild( CubeCreator.create(x, y, z, size, branchApp));
	}
}
