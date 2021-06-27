package DAO;

import java.sql.*;

public abstract class Dao<T> implements IDao<T> {
	public Connection connection() { return Singleton.instanciate().getConnection(); }
}