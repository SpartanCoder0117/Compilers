package objetos3D;
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;
public class Cama {
	TransformGroup tPrincipal;
	public Cama (Appearance ap, int x, int z){
		this(ap,ap,ap,x,z);
	}
	public Cama (Appearance apMadera,Appearance apColchon, Appearance apAlmohada, int x, int z){
                Transform3D t3d=new Transform3D();
                t3d.set(1,new Vector3d(x+0.5f,0,z+1));
                tPrincipal=new TransformGroup(t3d);                 
		float giro90=(float)(Math.PI/2);
		Pieza figuras[]={
			new Pieza(new Cylinder(0.01f,0.6f,apMadera),-0.4f,-0.2f,-0.8f ,0,0,0),		//Pata1
			new Pieza(new Cylinder(0.01f,0.3f,apMadera),-0.4f,-0.35f,0.8f ,0,0,0),		//Pata2
			new Pieza(new Cylinder(0.01f,0.6f,apMadera),0.4f,-0.2f,-0.8f ,0,0,0),		//Pata3
			new Pieza(new Cylinder(0.01f,0.3f,apMadera),0.4f,-0.35f,0.8f ,0,0,0),		//Pata4
			new Pieza(new Box(0.4f,0.01f,0.8f,apMadera),0,-0.2f,0,0,0,0),			//Somier
			new Pieza(new Box(0.4f,0.15f,0.01f,apMadera),0,0f,-0.8f,0,0,0),		//Cabecera
			new Pieza(new Box(0.4f,0.1f,0.01f,apMadera),0,-0.15f,0.8f,0,0,0),		//Pie
			new Pieza(new Box(0.39f,0.06f,0.78f,apColchon),0,-0.13f,0,0,0,0),		//Colchon
			new Pieza(new Box(0.38f,0.06f,0.15f,apAlmohada),0,-0.1f,-0.65f,0,0,0)		//Almohada
		};
		for (int n=0;n<figuras.length;n++){
			tPrincipal.addChild(figuras[n].getTransformGroup());
		}
	}
	public TransformGroup getTransformGroup(){
		return tPrincipal;
	}
}