package reflection;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.*;

public class DumpClasses {
	
	
	//shows detail about the className parameter
	public static void dump(String className)
	{
		
		write("class: " + className);	
		write("-----");
		write("fields:\n");
		try {
			Class <?> cls = Class.forName(className);
			Field fieldlist[]  = cls.getDeclaredFields();
			for (int i = 0; i < fieldlist.length; i++) {
				Field fld = fieldlist[i];
				write("field name  = " + fld.getName());
				write("declaring class = " + fld.getDeclaringClass());
				write("type  = " + fld.getType());
				int mod = fld.getModifiers();
				write("modifiers = " +  Modifier.toString(mod));
				write("\n");
			        
			}
		}
		catch (Throwable e) {
			System.err.println(e);
		}
		write("-----");
		write("constructors:\n");
		
		
        
         
         try {
 			Class <?> cls = Class.forName(className);
 			Constructor<?> ctorlist[] = cls.getDeclaredConstructors();
 			for (int i = 0; i < ctorlist.length; i++) {
 				Constructor<?> ct = ctorlist[i];
 				write("constructor name   = " + ct.getName());
 				write("declaring class = " +  ct.getDeclaringClass());
 				Class<?> pvec[] = ct.getParameterTypes();
 				for (int j = 0; j < pvec.length; j++)
 					write("param #" + j + " " + pvec[j]);
 				Class<?> evec[] = ct.getExceptionTypes();
 				for (int j = 0; j < evec.length; j++)
 				write(	"exc #" + j + " " + evec[j]);
 				write("\n");
 		        
 			}
 		}
 		catch (Throwable e) {
 			System.err.println(e);
 		}
 		
 		write("-----");
		
 		
 		 try {
 			Class<?> c = Class.forName(className);
             Method met[] = c.getDeclaredMethods();
             for (int i = 0; i < met.length; i++)
             {
             	Method m = met[i];
             	write("method name  = " + m.getName());
 			Class<?> pvec[] = m.getParameterTypes();
 			for (int j = 0; j < pvec.length; j++){
 				int mod = pvec[i].getModifiers();
 				write(" param #" + j + " " + pvec[j]+"with modifier"+Modifier.toString(mod));
 			}
 			Class<?> evec[] = m.getExceptionTypes();
 			for (int j = 0; j < evec.length; j++)
 			write("exc #" + j + " " + evec[j]);
 			write("return type = " + m.getReturnType());
 			write("\n");
 	        
             }
          }
          catch (Throwable e) {
             System.err.println(e);
          }
		
         write("-----");
			
         write("\n");
         
      }
	
	private static void write(String string)
	{
	try {
	    BufferedWriter out = new BufferedWriter(new FileWriter("reflection.txt",true));
	    out.write(string);		   
	    out.newLine();
	    out.close();
	} catch (IOException e) {System.err.println(e.getMessage());
	}
	
	}
}