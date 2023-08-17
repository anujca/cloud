import java.sql.*;
import java.io.*;
 class OracleSql{
     public static void main(String args[]) throws SQLException {
         Connection con = null;
         try {
             Class.forName("oracle.jdbc.driver.OracleDriver");
             String dbURL0 = "jdbc:oracle:thin:@(DESCRIPTION = (enable=broken)(ADDRESS_LIST = (ADDRESS=(PROTOCOL=" + args[5] + " )(HOST=" + args[2] + ")(PORT=" + args[3] + ")))(CONNECT_DATA = (SERVICE_NAME= " + args[4] +")))";
             String dbURL1 = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS_LIST = (ADDRESS=(PROTOCOL=" + args[5] + " )(HOST=" + args[2] + ")(PORT=" + args[3] + ")))(CONNECT_DATA = (SERVICE_NAME= " + args[4] +")))";
             String dbURL2 = "jdbc:oracle:thin:@" + args[2] + ":" + args[3] + "/" + args[4];
             String strUserID = args[0];
             String strPassword = args[1];
             String dbURL = "";
             int type = Integer.parseInt(args[6]);

             System.out.println(type);
             if ( type == 0 ) {
			    dbURL = dbURL0;
			 } else {
				   if ( type == 1 ) {
				 			    dbURL = dbURL1;
			        } else {
						  dbURL = dbURL2;
					}

             }
             System.out.println(dbURL);
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

