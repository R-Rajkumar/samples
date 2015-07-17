package org.wso2.carbon.samples.handler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.synapse.MessageContext;
import org.apache.synapse.rest.AbstractHandler;
import org.wso2.carbon.ndatasource.core.DataSourceManager;

public class MyCustomHandler extends AbstractHandler {

	public boolean handleRequest(MessageContext messageContext) {

		boolean isJndiLookup = true;
		try {
			DataSource dataSource = null;
			if (isJndiLookup) {
				// Obtain the data source via a JNDI lookup, by passing the JNDI config name
				dataSource = (DataSource) InitialContext.doLookup("jdbc/WSO2CarbonDB");
			} else {
				// Obtain the data source by passing the data source name
				dataSource = (DataSource) DataSourceManager.getInstance()
						.getDataSourceRepository()
						.getDataSource("WSO2_CARBON_DB").getDSObject();
			}
			if (dataSource != null) {
				Connection conn = dataSource.getConnection();
				Statement stmt = conn.createStatement();
				String sql;
				sql = "SELECT id, name, email FROM MyTable";
				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String email = rs.getString("email");
					// Display values
					System.out.print("ID: " + id);
					System.out.print(", Name: " + name);
					System.out.println(", Email: " + email);
				}
				// STEP 6: Clean-up environment
				rs.close();
				stmt.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error " + e.getMessage());
		}
		
		return true;
	}

	public boolean handleResponse(MessageContext messageContext) {
		return true;
	}
}