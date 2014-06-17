package hdfjar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("generic")
public class HDFRestWSJar {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public HDFRestWSJar() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of HDFRestWSJar
     * @return an instance of String
     */
    @GET
    @Produces("application/x-javascript")
    @Path("hadoopPut/{hadoopInputDir}")
    public String hadoopPut(@QueryParam("callback") String callback, @PathParam("hadoopInputDir") String hadoopInputDir) {
        String line="";
    	
    	try
    	{
    	// Process process = Runtime.getRuntime().exec ("pwd");
    		Process process = Runtime.getRuntime().exec ("hadoop fs -put /home/idcuser/test/testfile.txt /"+hadoopInputDir);
    	 InputStreamReader ir=new InputStreamReader(process.getInputStream());
    	 LineNumberReader input = new LineNumberReader (ir);
    	 
    	 while ((line = input.readLine ()) != null){
    	  System.out.println(line);
    	   line = line+"\n";
    	   return line;
    	 
    	}
    	
    	
    	}
    	catch (java.io.IOException e){
    	 System.err.println ("IOException " + e.getMessage());
    	 return "IOException " + e.getMessage();
    	}
    	
    	return line;
    }
    
    @GET
    @Produces("application/x-javascript")
    @Path("hadoopRun/{jarPath:.+}")
    public String hadoopRun(@QueryParam("callback") String callback,@PathParam("jarPath") String jarPath) {
        String line="";
    	
    	try
    	{
    	//! Process process = Runtime.getRuntime().exec ("pwd");
    		Process process = Runtime.getRuntime().exec 
    				("hadoop jar "+jarPath);
    	 InputStreamReader ir=new InputStreamReader(process.getInputStream());
    	 LineNumberReader input = new LineNumberReader (ir);
    	 
    	 while ((line = input.readLine ()) != null){
    	  System.out.println(line);
    	   line = line+"\n";
    	   return line;
    	 
    	}
    	
    	
    	}
    	catch (java.io.IOException e){
    	 System.err.println ("IOException " + e.getMessage());
    	 line = "IOException " + e.getMessage();
    	
    	 return "IOException " + e.getMessage();
    	}
    	
    	return line;
    }
    
    
    @GET
    @Produces("application/x-javascript")
    @Path("viewResult/{hadoopOutputDir}")
    public String getResult(@QueryParam("callback") String callback, @PathParam("hadoopOutputDir") String hadoopOutputDir) {
        String line="";
    	
    	StringBuffer sb = new StringBuffer();
        
    	try
    	{
    	// Process process = Runtime.getRuntime().exec ("pwd");
    		Process process = Runtime.getRuntime().exec ("hadoop fs -cat "+hadoopOutputDir+"/*00");
    	    BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
    	    
    		
    		/*InputStreamReader ir=new InputStreamReader(process.getInputStream());
    	 LineNumberReader input = new LineNumberReader (ir);
    	 */
    	 while ((line = br.readLine ()) != null){
    	  //System.out.println(line);
    	   //line = line+"\n";
    		 sb.append(line).append("\n");
    	  // return line;
    	 
    	}
    	
    	
    	}
    	catch (java.io.IOException e){
    	 System.err.println ("IOException " + e.getMessage());
    	 return "IOException " + e.getMessage();
    	}
    	
    	return sb.toString();
    }


    /**
     * PUT method for updating or creating an instance of HDFRestWSJar
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

}