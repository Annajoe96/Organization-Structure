import java.util.HashMap;
import java.util.ArrayList;

public class Organization {

	//hashmap of employees key(id) value(employee object: id, name, manager)
  static HashMap<String, Employee> employees = new HashMap<String, Employee>();

	//hashmap of managers key(id) value(arraylist of team members)
  	static HashMap<String, ArrayList<String>> managers = new HashMap<String, ArrayList<String>>();

	public static void main(String args[]){

	   add_employee("10", "anna", "-1");
		add_employee("7", "daniel", "10");
		add_employee("3", "lawana","10");
		add_employee("34","lissetee","3");
		add_employee("55","Leo","-1");
		add_employee("5","lans puls","34");
	
	    print_org();
	}

	//adding employees to employee and manager hashmap
	public static void add_employee(String id, String name, String manager){

		employees.put(id, new Employee(id, name, manager));

		ArrayList<String> team = new ArrayList<String>();
		managers.put(id, team);


		if(managers.get(manager) != null) {
			managers.get(manager).add(id);
		} else {
			ArrayList<String> team_add = new ArrayList<String>();
			managers.put(manager, team_add);
			managers.get(manager).add(id);
		}

	}


	//method to change manager
	public static void change_manager(String id, String new_manager){
		//return if input id does not exist
		if(employees.get(id) != null){
			return;
		}
			//remove employee from manager team
			managers.get(employees.get(id).manager).remove(id);
			//setting employee manager to new manager
			employees.get(id).manager = new_manager;
			//adding employee to new manager team
			managers.get(new_manager).add(id);
	}

	public static void remove_employee(String id){

		//return early if given id is invalid
		if(employees.get(id) == null){
			return;
		}

		// shifting target employee's team to new_manager
		if(managers.get(id).size() > 0){

			//new manager = target employee's amnager
			String new_manager = employees.get(id).manager;

			ArrayList<String> newList = new ArrayList<String>();
			newList.addAll(managers.get(new_manager));
			newList.addAll(managers.get(id));
			managers.remove(new_manager);
			//adding manager with updated list of team
			managers.put(new_manager,newList);

			int length = (managers.get(new_manager).size()) - 1;

			ArrayList<String> team_list = new ArrayList<String>();
			team_list = managers.get(new_manager);

			//updating target employees manager
			for(int i = 0; i <= length; i++){
				employees.get(team_list.get(i)).manager = new_manager;
			}

		}


		//remove employee from employee's manager list
		managers.get(employees.get(id).manager).remove(id);

		//remove from managers list
		managers.remove(id);

		//remove from employee record
		employees.remove(id);



	}


	public static Integer team_size(String id){

		//return 0 if input id is invalid or is not a manager
		if(managers.get(id) == null || managers.get(id).size() == 0){
			return 0;
		}

		int sum = 0;
		// finding employee's team size
		sum += managers.get(id).size();

		ArrayList<String> team_list = new ArrayList<String>();
		team_list = managers.get(id);


		for(String a : team_list) {
			sum += team_size(a);
		}

		return sum;

	}


	public static void print_org(){

		HashMap<String, Boolean> seen = new HashMap<String, Boolean>();

		for(String top_level_employee : managers.get("-1")) {
			manager_list(top_level_employee, seen, 0);
		}

	}

	public static void manager_list(String id, HashMap seen, int level){

		if(seen.get(id) != null){
			return;
		}

		print_employee(id, level);
		seen.put(id, true);

		ArrayList<String> team = managers.get(id);

		for(String a : team) {
			manager_list(a, seen, level + 1);
		}


	}

	public static void  print_employee(String id, int level){

		Employee e = employees.get(id);

		String result = " ";

		if(level>0){
			result +=  " ".repeat(level);
		}

		result += e.name + '['+ e.id + ']';

		System.out.println(result);
	}


}
