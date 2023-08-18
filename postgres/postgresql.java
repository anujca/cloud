import java.sql.*;
import java.io.*;
 class PostgresSql{
     public static void main(String args[]) throws SQLException {
         Connection con = null;
         try {
             Class.forName("org.postgresql.Driver");
             String dbURL = "jdbc:postgresql://" + args[2] + ":" + args[3] + "/" + args[4] + "?sslmode="+ args[5];
             String strUserID = args[0];
             String strPassword = args[1];
             con=DriverManager.getConnection(dbURL,strUserID,strPassword);
             System.out.println("Connected to the database.");
             Statement stmt=con.createStatement();
            // SELECT datname as "Database name", usename as "User name", ssl, client_addr, application_name, backend_type
		    //   FROM pg_stat_ssl
			//   JOIN pg_stat_activity
			//   ON pg_stat_ssl.pid = pg_stat_activity.pid
		    //  ORDER BY ssl
			// ;


             for(;;) {
				   try{
		             System.out.print("sql> ");
		             String strLine = null;
				     BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
	 				 StringBuilder buf = new StringBuilder();

						while (true)   {
	 	  					 strLine = in.readLine();
	 	   					 if ( strLine.contains(";") || strLine.equals("exit") || strLine.equals("quit") ) break;
	           			     buf.append(strLine).append(" ");
    								}

		             if ( strLine.equals("exit") || strLine.equals("quit")) break;
		            ResultSet resultSet=stmt.executeQuery(buf.toString());
  		 			ResultSetMetaData rsmd = resultSet.getMetaData();
		 			int columnsNumber = rsmd.getColumnCount();
		 			while (resultSet.next()) {
						System.out.println(" ");
		 				 for (int i = 1; i <= columnsNumber; i++) {
		 				      String columnValue = resultSet.getString(i);
		 				       System.out.println(rsmd.getColumnName(i) + "  :  " + columnValue);
		 				     }
		 	    	}
		 	    	} catch(Exception g){ System.out.println(g);}
		         }

         } catch(Exception e){ System.out.println(e);}

        finally {
             con.close();
         }
 }
 }

