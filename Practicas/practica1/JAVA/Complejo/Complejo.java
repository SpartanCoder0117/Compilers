
public class Complejo{
     private double real;
     private double imag;

  public Complejo() {
     real=0.0;
     imag=0.0;
  }
  public Complejo(double real, double imag){
     this.real=real;
     this.imag=imag;
  }
  public Complejo conjugado(Complejo c){
     return new Complejo(c.real, -c.imag);
  }
  public Complejo opuesto(Complejo c){
     return new Complejo(-c.real, -c.imag);
  }
  public double modulo(){
     return Math.sqrt(real*real+imag*imag);
  }
  public double argumento(){
     double angulo=Math.atan2(imag, real);   
     if(angulo<0)  angulo=2*Math.PI+angulo;
     return angulo*180/Math.PI;
  }
  public Complejo sumar(Complejo c){
     double x=real+c.real;
     double y=imag+c.imag;
     return new Complejo(x, y);
  }
 public Complejo mult(Complejo c){
     double x=real*c.real-imag*c.imag;
     double y=real*c.imag+imag*c.real;
     return new Complejo(x, y);
  }
  public Complejo producto(double d){
     double x=real*d;
     double y=imag*d;
     return new Complejo(x, y);
 }
  public Complejo division(Complejo c)/*throws ExcepcionDivideCero*/{
     double aux, x, y;
     /*if(c.modulo()==0.0){
          throw new ExcepcionDivideCero("Divide entre cero");
     }else{*/
          aux=c.real*c.real+c.imag*c.imag;
          x=(real*c.real+imag*c.imag)/aux;
          y=(imag*c.real-real*c.imag)/aux;
     //}
     return new Complejo(x, y);
  }
  public Complejo cociente(Complejo c, double d)throws ExcepcionDivideCero{
    double x, y;
    if(d==0.0){
          throw new ExcepcionDivideCero("Divide entre cero");
    }else{
        x=c.real/d;
        y=c.imag/d;
    }
     return new Complejo(x, y);
  }
  public Complejo exponencial(Complejo c){
     double x=Math.cos(c.imag)*Math.exp(c.real);
     double y=Math.sin(c.imag)*Math.exp(c.real);
     return new Complejo(x, y);
  }
  public Complejo csqrt(double d){
     if(d>=0) return new Complejo(Math.sqrt(d), 0);
     return new Complejo(0, Math.sqrt(-d));
  }
  private double potencia(double base, int exponente){
    double resultado=1.0;
    for(int i=0; i<exponente; i++){
        resultado*=base;
    }
    return resultado;
  }
  private double combinatorio(int m, int n){
    long num=1;
    long den=1;
    for(int i=m; i>m-n; i--){
        num*=i;
    }
    for(int i=2; i<=n; i++){
        den*=i;
    }
    return (double)num/den;
  }
  public Complejo potencia(Complejo c, int exponente){
    double x=0.0, y=0.0;
    int signo;
    for(int i=0; i<=exponente; i++){
        signo=(i%2==0)?+1:-1;
//parte real
        x+=combinatorio(exponente, 2*i)*potencia(c.real, exponente-2*i)*potencia(c.imag, 2*i)*signo;
        if(exponente==2*i)  break;
//parte imaginaria
        y+=combinatorio(exponente, 2*i+1)*potencia(c.real, exponente-(2*i+1))*potencia(c.imag, 2*i+1)*signo;
    }
    return new Complejo(x, y);
  }
  public String toString(){
     if(imag>0)     return new String((double)Math.round(100*real)/100+" + "+(double)Math.round(100*imag)/100+"*i");
     return new String((double)Math.round(100*real)/100+" - "+(double)Math.round(-100*imag)/100+"*i");
  }
  public void imprime(){
     System.out.println(toString());
  }
}
class ExcepcionDivideCero extends Exception {

  public ExcepcionDivideCero() {
         super();
  }
  public ExcepcionDivideCero(String s) {
         super(s);
  }
}
