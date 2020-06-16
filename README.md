# 🧑‍🏭 Organization-Structure

 This is a program to manage the organizational structure in a company. The program allows us to :

## Add employees:

```ruby
employee.add_employee(id, name, manager)
```


Employees are added in the format employee id, employee name ,manager id as an employee object.
eg: 10, Sharilyn Gruber, 15
eg: 11, Dennice Mattice, -1 (Dennice Mattice has no manager)

The input is added to a Hash employees that consistes of employee id as key and employee object(params- id, name, manager id) as value. Another Hash called managers keeps track of all the managers and the employee under each of them.


##Remove employees:

```ruby
company.remove_employee(id)
```
Employee is removed from both employee hashmap and manager team. If the employee has a team, the team is transferred to employees manager's team. If employee has no manager the team's manager id will be changed to -1 indicating no manager.

##Move an employee from one manager to another:

```ruby
company.change_manager(id, manager)
```
The method takes in employee id and new manager id and updates manager id in the given employee id. Then removes the employee id from the respective manager hashmap.

##Count the number of employees that ultimately report upto a given manager:

```ruby
company. supervisor_team_count(id)
```
This includes the number of members in the employee team under the given manager and the number of team members in each team member's team. 


##Prints out the list of employees in hierarichal order : 

```ruby
company. print_org
```



