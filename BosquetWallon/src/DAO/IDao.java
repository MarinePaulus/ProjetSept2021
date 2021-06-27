package DAO;

import java.util.ArrayList;

public interface IDao<T> {
	public boolean create(T obj);
	public boolean delete(T obj);
	public boolean update(T obj);
	public  T get(T obj);
	public ArrayList<T> getList();
}