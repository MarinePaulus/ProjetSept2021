import DAO.Singleton;

public class Program {

	public static void main(String[] args) {
		Singleton.instanciate().getConnection();
	}

}
