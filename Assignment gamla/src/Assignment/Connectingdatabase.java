package Assignment;

import java.sql.*;

public class Connectingdatabase {	
	
	// Building a BASE constructor for class
	public Connectingdatabase(){
			}
		// Connecting to database
		public static Connection makeConnection() throws Exception{
			//
			Statement stmt = null;
			try{
				// JDBC driver name and database URL
				final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
				final String DB_URL = "jdbc:mysql://localhost/";
				final String DISABLE_SSL = "?useSSL=false";
				final String USER  = "root";
				final String PASS = "Korsband321";
				
				Class.forName(JDBC_DRIVER);

				Connection con = DriverManager.getConnection(DB_URL + DISABLE_SSL,USER, PASS);
				stmt = con.createStatement();
				//System.out.println("Connected");
				
				String sql = "CREATE DATABASE IF NOT EXISTS " + "PowerSystem3";
				stmt.executeUpdate(sql);
				
				//System.out.println("PowerSystem3 Database created successfully...");
				
				con = DriverManager.getConnection(DB_URL + "PowerSystem3" + DISABLE_SSL , USER, PASS);
			    stmt = con.createStatement();
				
			    // Create table for BaseVoltage
			    
				sql = "CREATE TABLE IF NOT EXISTS BaseVoltage" +
						"(rdfId VARCHAR(255) not NULL, " + 
						" nom_val DECIMAL(7,2), " + 
						" PRIMARY KEY (rdfId ))";
				stmt.executeUpdate(sql);
				
				// Create table for Substation
				
				sql = "CREATE TABLE IF NOT EXISTS Substation" +
						"(rdfId VARCHAR(255) not NULL, " + 
						" name VARCHAR(255), " +
						" regionRdfId VARCHAR(255), " + 
						" PRIMARY KEY (rdfId))";
				stmt.executeUpdate(sql);
				
				// Create table for VoltageLevel
				
				sql = "CREATE TABLE IF NOT EXISTS VoltageLevel" + 
						"(rdfId VARCHAR(255) not NULL, " + 
						" name VARCHAR(255), " + 						
						" substationRdfId VARCHAR(255), " + 
						" baseVoltageRdfId VARCHAR(255), " +
						" PRIMARY KEY (rdfId)," +
						" FOREIGN KEY (substationRdfId) REFERENCES Substation(rdfId)," +
						" FOREIGN KEY (baseVoltageRdfId) REFERENCES BaseVoltage(rdfId))";
				stmt.executeUpdate(sql);
				
				// Creat table for GeneratingUnit
				
				sql = "CREATE TABLE IF NOT EXISTS GeneratingUnit" +
						"(rdfId VARCHAR(255) not NULL, " + 
						" name VARCHAR(255), " +
						" maxP DECIMAL(7,2), " + 
						" minP DECIMAL(7,2), " + 
						" equimentContainerRdfId VARCHAR(255), " +
						" PRIMARY KEY (rdfId), " +
						" FOREIGN KEY (equimentContainerRdfId) REFERENCES Substation(rdfId))";
				stmt.executeUpdate(sql);
				
				// Creat table for RegulatingControl
				//System.out.println("Created tables..");
				sql = "CREATE TABLE IF NOT EXISTS RegulatingControl " +
								"(rdfId VARCHAR(255), " +
								" name VARCHAR(255), " +
								" targetValue DECIMAL(7,2), " +
								" PRIMARY KEY (rdfId))";
						stmt.executeUpdate(sql);
				
				// Creat table for SynchronousMachine
						
				sql = "CREATE TABLE IF NOT EXISTS SynchronousMachine" +
						"(rdfId VARCHAR(255) not NULL, " + 
						" name VARCHAR(255), " +
						" ratedS DECIMAL(7,2), " +
						" P DECIMAL(7,2), " +
						" Q DECIMAL(7,2), " +
						" genUnit_rdfID VARCHAR(255), " +
						" regControl_rdfID VARCHAR(255), " +
						" equimentContainerRdfId VARCHAR(255), " +						
						" PRIMARY KEY (rdfId), " +
						" FOREIGN KEY (genUnit_rdfID) REFERENCES GeneratingUnit(rdfId), " +
						" FOREIGN KEY (regControl_rdfID) REFERENCES RegulatingControl(rdfId), " +
						" FOREIGN KEY (equimentContainerRdfId) REFERENCES VoltageLevel(rdfId))" ;
				stmt.executeUpdate(sql);
				
				//System.out.println("Created tables2..");
				// Creat table for PowerTransformer
				
				sql = "CREATE TABLE IF NOT EXISTS PowerTransformer " +
						"(rdfId VARCHAR(255), " +
						" name VARCHAR(255), " +
						" equimentContainerRdfId VARCHAR(255), " +
						" PRIMARY KEY (rdfId), " +
						" FOREIGN KEY (equimentContainerRdfId) REFERENCES Substation(rdfId))";
				stmt.executeUpdate(sql);
				
				// Creat table for EnergyConsumer
				
				sql = "CREATE TABLE IF NOT EXISTS EnergyConsumer " +
						"(rdfId VARCHAR(255), " +
						" name VARCHAR(255), " +
						" P_fixed DECIMAL(7,2), " +
						" Q_fixed DECIMAL(7,2), " +
						" equimentContainerRdfId VARCHAR(255), " +						
						" PRIMARY KEY (rdfId), " +
						" FOREIGN KEY (equimentContainerRdfId) REFERENCES VoltageLevel(rdfId))";
				stmt.executeUpdate(sql);
				
				// Creat table for TransformerWinding
				
				sql = "CREATE TABLE IF NOT EXISTS TransformerWinding " +
						"(rdfId VARCHAR(255), " +
						" name VARCHAR(255), " +
						" r DECIMAL(7,4), " +
						" x DECIMAL(7,4), " +
						" transformerRdfId VARCHAR(255), " +
						" baseVoltageRdfId VARCHAR(255), " +
						" PRIMARY KEY (rdfId), " +
						" FOREIGN KEY (transformerRdfID) REFERENCES PowerTransformer(rdfID), " +
						" FOREIGN KEY (baseVoltageRdfID) REFERENCES BaseVoltage(rdfID))";
				stmt.executeUpdate(sql);
				
				// Creat table for Breaker
				sql = "CREATE TABLE IF NOT EXISTS Breaker " +
						"(rdfId VARCHAR(255), " +
						" name VARCHAR(255), " +
						" state VARCHAR(255), " +
						" equimentContainerRdfId VARCHAR(255), " +						
						" PRIMARY KEY (rdfId), " +
						" FOREIGN KEY (equimentContainerRdfId) REFERENCES VoltageLevel(rdfId))";
				stmt.executeUpdate(sql);
				
				// Creat table for RatioTapChanger				
				sql = "CREATE TABLE IF NOT EXISTS RatioTapChanger " +
						"(rdfId VARCHAR(255), " +
						" name VARCHAR(255), " +
						" step DECIMAL(7,4), " +						
						" PRIMARY KEY (rdfId))";
				stmt.executeUpdate(sql);	
				
				// Creat table for ACLine				
				sql = "CREATE TABLE IF NOT EXISTS ACLine " +
						"(rdfId VARCHAR(255), " +
						" name VARCHAR(255), " +
						" r DECIMAL(7,4), " +	
						" x DECIMAL(7,4), " +
						" PRIMARY KEY (rdfId))";
				stmt.executeUpdate(sql);
				
				return con;
			} 
			catch(Exception e){System.out.println(e);}
			return null;
			}
	}




