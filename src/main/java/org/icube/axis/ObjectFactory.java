package org.icube.axis;

import org.icube.helper.DatabaseConnectionHelper;

public class ObjectFactory {
	static DatabaseConnectionHelper dch;

	static public DatabaseConnectionHelper getDBHelper() {
		if (dch == null) {
			dch = new DatabaseConnectionHelper();
		}
		return dch;
	}

}
