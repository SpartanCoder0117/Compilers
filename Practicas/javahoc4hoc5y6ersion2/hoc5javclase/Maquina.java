import java.awt.*;
import java.util.*;
import java.lang.reflect.*;

class  Maquina {
//ver como automatizar esto
public final static short NUMBER=257;
public final static short PRINT=258;
public final static short VAR=259;
public final static short BLTIN=260;
public final static short INDEF=261;
public final static short WHILE=262;
public final static short IF=263;
public final static short ELSE=264;
public final static short OR=265;
public final static short AND=266;
public final static short GT=267;
public final static short LT=268;
public final static short UNARYMINUS=269;
public final static short NOT=270;
public final static short YYERRCODE=256;
//hasta aqui
Stack pila;
Stack marcos;
Vector prog;

static int pc=0;
int progbase=0;
boolean returning=false;

int numArchi=0;
Method metodo;
Method metodos[];
Class c;
Graphics g;
double angulo;
int x=0, y=0;
Class parames[];
//Algebra alge;
	Maquina(){
		
	}
	public Vector getProg(){
		return prog;
	}
	void initcode(){
        	pila=new Stack();
		prog=new Vector();
	}
	Object pop(){
		return pila.pop();
	}
	int code(Object f){
		//System.out.println("Gen ("+f+") size="+prog.size());
   		prog.addElement(f);
		return prog.size()-1;
	}
   void execute(int p){
      String inst;
      //System.out.println("progsize="+prog.size()+" p = "+p);
      for(pc=0;pc < prog.size(); pc=pc+1){
	  //System.out.println("pc="+pc+" inst "+prog.elementAt(pc));
      }
      for(pc = p; !(inst=(String)prog.elementAt(pc)).equals("STOP"); ){
		//for(pc=p;pc < prog.size();){
			try {
			//System.out.println("111 pc= "+pc);
			inst=(String)prog.elementAt(pc);
			pc=pc+1;
			//==System.out.println("222 pc= "+pc+" instr "+inst);
                        c=this.getClass();
			//System.out.println("clase "+c.getName());
          		metodo=c.getDeclaredMethod(inst, null);
			metodo.invoke(this, null);
			}
			catch(NoSuchMethodException e){
				System.out.println("No metodo "+e);
                        }

			catch(InvocationTargetException e){
   System.out.println(" inst = "+inst+"  "+e+"this "+this);
                        }
			catch(IllegalAccessException e){
				System.out.println(e);
                        }
		}
	}
	void constpush(){
	Simbolo s;
	Double d;

	s=(Simbolo)prog.elementAt(pc);
	pc=pc+1;
	pila.push(new Double(s.val));
	}
	void varpush(){
	Simbolo s;
	double d;
	//d.simb=(Simbolo *)(*pc++);
	s=(Simbolo)prog.elementAt(pc);
	pc=pc+1;
	pila.push(s);
	}
   void whilecode(){
      boolean d;
      int savepc = pc;
      execute(savepc+2);	/* condition */
      d=((Boolean)pila.pop()).booleanValue();
      while (d) {
	 execute(
            ((Integer)prog.elementAt(savepc)).intValue());
	 execute(savepc+2);
	 d=((Boolean)pila.pop()).booleanValue();	
      }
      pc=((Integer)prog.elementAt(savepc+1)).intValue();
   }
   void ifcode(){
      boolean d;
      //double d;
      int savepc=pc;
      System.out.println("en if code pc= "+pc);
      execute(savepc+3);
      System.out.println("en ant pop ifcode  pc= "+pc);
      //d=((Double)pila.pop()).doubleValue();
      d=((Boolean)pila.pop()).booleanValue();
      System.out.println("en ifcode  pc= "+pc+" val ="+d);
      if(d){
         System.out.println("en el if");
         execute(((Integer)prog.elementAt(savepc)).intValue());
      }
      else if(!prog.elementAt(savepc+1).toString().equals("STOP"))
         execute(((Integer)prog.elementAt(savepc+1)).intValue());
      pc=((Integer)prog.elementAt(savepc+2)).intValue();
   }
   void define(Simbolo s){
		s.defn=progbase;
		progbase=prog.size();
   }
   void add(){
        double d1, d2;
        d2=((Double)pila.pop()).doubleValue();
        d1=((Double)pila.pop()).doubleValue();
	d1+=d2;
        pila.push(new Double(d1));
   }
   void sub(){
        double d1, d2;
        d2=((Double)pila.pop()).doubleValue();
        d1=((Double)pila.pop()).doubleValue();
	d1-=d2;
        pila.push(new Double(d1));
   }
   void mul(){
        double d1, d2;
        d2=((Double)pila.pop()).doubleValue();
        d1=((Double)pila.pop()).doubleValue();
	d1*=d2;
        pila.push(new Double(d1));
   }
   void div(){
	double d1, d2;
        d2=((Double)pila.pop()).doubleValue();
        d1=((Double)pila.pop()).doubleValue();
	d1/=d2;
	pila.push(new Double(d1));
   }
   void power(){
        double d1, d2;
        d2=((Double)pila.pop()).doubleValue();
        d1=((Double)pila.pop()).doubleValue();
	d1= Math.pow(d1, d2);
        pila.push(new Double(d1));
   }
   void negate(){
	double d;
	d=((Double)pila.pop()).doubleValue();
	d=-d;
	pila.push(new Double(d));
   }
   void eq(){
      double d1, d2;
      boolean res;	        
         
      d2=((Double)pila.pop()).doubleValue();     
      d1=((Double)pila.pop()).doubleValue();
      res= (d1 == d2);
      pila.push(new Boolean(res));
   }
   void ne(){
      double d1, d2;
      boolean res;	        
         
      d2=((Double)pila.pop()).doubleValue();     
      d1=((Double)pila.pop()).doubleValue();
      res= (d1 != d2);
      pila.push(new Boolean(res));
   }
   void lt(){
      double d1, d2;
      boolean res;
	        
      d2=((Double)pila.pop()).doubleValue();
      d1=((Double)pila.pop()).doubleValue();
      res= (d1 < d2);
      pila.push(new Boolean(res));
   }
   void le(){
      double d1, d2;
      boolean res;
	        
      d2=((Double)pila.pop()).doubleValue();
      d1=((Double)pila.pop()).doubleValue();
      res= (d1 <= d2);
      pila.push(new Boolean(res));
   }
   void gt(){
      double d1, d2;
      boolean res;
	
      d2=((Double)pila.pop()).doubleValue();
      d1=((Double)pila.pop()).doubleValue();
      res= (d1 > d2);
      pila.push(new Boolean(res));
   }
   void ge(){
      double d1, d2;
      boolean res;
	
      d2=((Double)pila.pop()).doubleValue();
      d1=((Double)pila.pop()).doubleValue();
      res= (d1 >= d2);
      pila.push(new Boolean(res));
   }
   void or(){
      boolean d1, d2;
      d2=((Boolean)pila.pop()).booleanValue();
      d1=((Boolean)pila.pop()).booleanValue();
      d1=(d1 || d2);
      pila.push(new Boolean(d1));
   }
   void and(){
      boolean d1, d2;
      d2=((Boolean)pila.pop()).booleanValue();
      d1=((Boolean)pila.pop()).booleanValue();
      d1=(d1 && d2);
      pila.push(new Boolean(d1));
   }
   void not(){
      boolean d;
      d=((Boolean)pila.pop()).booleanValue();
      d=!d;
      pila.push(new Boolean(d));
   }
   void eval(){
      Simbolo s;
      s=(Simbolo)pila.pop();
      if(s.tipo == INDEF)
         System.out.println("variable no definida "+ s.nombre);
		//execerror("variable no definida", d.simb->nombre);
      pila.push(new Double(s.val));
   }
/*
	void eval()
	{
    		Simbolo s;
		s=(Simbolo)pila.pop();
		pila.push(new Double(s.val));
	}
*/
	void assign()
	{
	Simbolo s;
	Double d2;
	s=(Simbolo)pila.pop();
	d2=(Double)pila.pop();
		//execerror("asignacion  a una no variable", 
			//d1.simb->nombre);
	if(s.tipo != VAR && s.tipo != INDEF)
		System.out.println("asignacion  a una no variable "+s.nombre);
	s.val = d2.doubleValue();
	s.tipo= VAR;
	pila.push(new Double(s.val));
	}
	void print(){
	Double d;
	d=(Double)pila.pop();
	System.out.println(" "+d.doubleValue());
	}
	void prexpr(){
	Double d;
	d=(Double)pila.pop();
	System.out.print("["+d.doubleValue()+"]");
	}
	void bltin()
	{
	Double d;
	String inst;
	Class c;
	Double ret;

	Class paramC[]={java.lang.Double.TYPE};
	Object param[]=new Object[1];

	d=(Double)pila.pop();
	param[0]=new Double (d.doubleValue());
	inst=((Simbolo)prog.elementAt(pc)).nombre;
	c=java.lang.Math.class;
	try {
	metodo=c.getDeclaredMethod(inst, paramC);
	ret=(Double)metodo.invoke(this, param);
	pila.push(ret);
	}
	catch(NoSuchMethodException e){
		System.out.println("No metodo "+e+ "instru "+inst);
        }

	catch(InvocationTargetException e){
				System.out.println(e);
        }
	catch(IllegalAccessException e){
				System.out.println(e);
        }
	pc=pc+1;
	}

}
