%{
import java.io.*;
import java.util.StringTokenizer;
%}
%token NUMBER
%left '+' '-'
%left '*' '/'
%% 
list:   
   | list'\n'
   | list exp '\n'  { ((Matriz)$2.obj).imprime(); }
	;
exp: rac { $$=$1; }       
   | enlace 
   | exp '+' exp     {
                            //((Matriz)$1.obj).imprime(); 
                            //((Matriz)$3.obj).imprime(); 
                            $$ = new ParserVal(
                            ((Matriz)$1.obj).sumar( (Matriz)$3.obj ) ); 
                            //((Matriz)$$.obj).imprime(); 
                     }
   | exp '*' exp {
                            //((Matriz)$1.obj).imprime(); 
                            //((Matriz)$3.obj).imprime(); 
                            $$ = new ParserVal(
                            ((Matriz)$1.obj).mult( (Matriz)$3.obj ) ); 
                            //((Matriz)$$.obj).imprime(); 
                     }
   | '(' enlace ')'     { $$ = $2;}
   ;
rac:  '#'  NUMBER '#' NUMBER { 
                //System.out.println("gram rac"); 
                i = 0; j = 0; 
                auxiliar = new double[$1.ival][$3.ival];
                $$ = new ParserVal(new Matriz($1.ival,$3.ival));
                       }
        ;
enlace: {
             //System.out.println("gram ENLACE");
             //auxiliar = new double[(int)$1.ival][(int)$3.ival]; i=0; j=0;
             auxiliar = new double[2][2]; i=0; j=0;
        } 
        listaDeListas { $$ = new ParserVal(new Matriz(auxiliar)); 
                        
                        }
   ;
listaDeListas: lista { 
               //System.out.println("gram des lista"); 
               j = 0; i++;}
   | listaDeListas '|' lista { i++; j = 0;}
   ;
lista:	NUMBER { 
                 //System.out.println("gram des NUMBER "+$1); 
                 auxiliar[i][j] = $1.ival; j++;}
   | lista ',' NUMBER { 
                 //System.out.println("gram des NUMBER "+$3); 
                 auxiliar[i][j] = $3.ival; j++;}
   ;
%%
String ins;
StringTokenizer st;
void yyerror(String s){
   System.out.println("parser error:"+s);
}
int i = 0;
int j = 0;
double[][] auxiliar;
//Matriz matrizAux = new Matriz();

boolean newline;
int yylex(){
   String s;
   int tok;
   Integer ii;
   if (!st.hasMoreTokens())
   if (!newline){
      newline=true;
      return '\n';
   }
   else
      return 0;
   s = st.nextToken();
   //System.out.println("tok:"+s);
   try{
      ii = Integer.valueOf(s);
      yylval = new ParserVal(ii.intValue()); 
      tok = NUMBER;
   } catch (Exception e){ tok = s.charAt(0); }
   return tok;
}
void dotest(){
   BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
   while (true){
      System.out.print("expression:");
      try {
         ins = in.readLine();
      } catch (Exception e){}
      st = new StringTokenizer(ins);
      newline=false;
      yyparse();
   }
}
public static void main(String args[]){
   Parser par = new Parser(false);
   par.dotest();
}




